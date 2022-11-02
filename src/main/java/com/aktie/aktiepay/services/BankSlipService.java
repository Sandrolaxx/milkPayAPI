package com.aktie.aktiepay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinPaymentDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinPaymentResposeDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinResponseConsultDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipConsultResponseDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipPaymentDto;
import com.aktie.aktiepay.entities.Payment;
import com.aktie.aktiepay.entities.ReceiptInfo;
import com.aktie.aktiepay.entities.Title;
import com.aktie.aktiepay.entities.enums.EnumMovementCode;
import com.aktie.aktiepay.mapper.IBankSlipMapper;
import com.aktie.aktiepay.utils.ReceiptUtil;
import com.aktie.aktiepay.utils.RequestUtil;
import com.aktie.aktiepay.utils.StringUtil;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class BankSlipService {

    @Inject
    IBankSlipMapper mapper;

    @Inject
    RequestUtil requestUtil;

    @ConfigProperty(name = "milk.taxId")
    String defaultDocument;

    @Transactional
    public Payment persistSuccessfulPayment(BankSlipCelcoinPaymentDto celcoinDto, Integer titleId) {

        var title = Title.findById(titleId);
        var payment = mapper.bankSlipCelcoinPaymentDtoToPaymentEntity(celcoinDto);

        title.setBalance(BigDecimal.ZERO);
        title.setLiquidated(true);
        title.setPaidAt(LocalDateTime.now());

        payment.setTitle(title);
        payment.setLiquidated(true);
        payment.setNotified(true);

        payment.persistAndFlush();

        return payment;

    }

    @Transactional
    public String persistReceipt(BankSlipCelcoinPaymentResposeDto responseDto, BankSlipPaymentDto dto) {

        var receipt = new ReceiptInfo();
        var lastReceipt = ReceiptInfo.findLastReceipt();
        var milkPayDebitParty = requestUtil.getMilkPayDebitParty();
        var payment = Payment.findByTitleId(dto.getTitleId());

        receipt.setLastAuthentication(lastReceipt != null ? lastReceipt.getAuthentication() : "GENESIS_BLOCK");
        receipt.setMovementCode(EnumMovementCode.PAGAMENTO_BOLETO);
        receipt.setAmount(dto.getAmount());
        receipt.setExternalAuth(responseDto.getAuthentication().getExternalAuth());
        receipt.setExternalTxid(responseDto.getTxId().toString());
        receipt.setDigitable(StringUtil.isNullOrEmpty(dto.getDigitable()) ? dto.getBarcode() : dto.getDigitable());
        receipt.setDueDate(dto.getDueDate());
        receipt.setPaidAt(LocalDateTime.now());
        receipt.setPayment(payment);

        receipt.setReceiverName(dto.getReceiverName());
        receipt.setReceiverAccountBank(dto.getReceiverBank());
        receipt.setReceiverDocument(dto.getReceiverDocument());

        receipt.setPayerName(milkPayDebitParty.getName().toUpperCase());
        receipt.setPayerDocument(milkPayDebitParty.getTaxId());
        receipt.setPayerAccount(milkPayDebitParty.getAccount());
        receipt.setPayerAccountType(milkPayDebitParty.getAccountType());
        receipt.setPayerAccountBank(milkPayDebitParty.getBankISPB());
        receipt.setPayerAccountBranch(milkPayDebitParty.getBranch());

        var authentication = DigestUtils.md5Hex(receipt.toString());
        receipt.setAuthentication(authentication.toUpperCase());

        receipt.persistAndFlush();

        return ReceiptUtil.handleCreate(receipt);

    }

    public BankSlipConsultResponseDto parseToConsultResponseDto(BankSlipCelcoinResponseConsultDto responseDto) {
        return mapper.bankSlipDtoToResponseDto(responseDto);
    }

    public BankSlipCelcoinPaymentDto parseToCelcoinPaymentDto(BankSlipPaymentDto dto) {
        return mapper.bankSlipPaymentDtoToCelcoinPaymentDto(dto);
    }

}
