package com.milk.pay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.dto.bankslip.BankSlipCelcoinPaymentDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinPaymentResposeDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinResponseConsultDto;
import com.milk.pay.dto.bankslip.BankSlipConsultResponseDto;
import com.milk.pay.dto.bankslip.BankSlipPaymentDto;
import com.milk.pay.entities.Payment;
import com.milk.pay.entities.ReceiptInfo;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.enums.EnumMovementCode;
import com.milk.pay.mapper.IBankSlipMapper;
import com.milk.pay.utils.ReceiptUtil;
import com.milk.pay.utils.RequestUtil;
import com.milk.pay.utils.StringUtil;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class BankSlipService {

    @Inject
    IBankSlipMapper mapper;

    @ConfigProperty(name = "milk.taxId")
    String defaultDocument;

    @Inject
    RequestUtil requestUtil;

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
    public ReceiptInfo persistReceipt(BankSlipCelcoinPaymentResposeDto responseDto, BankSlipPaymentDto dto) {

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
        receipt.setPayment(payment);

        receipt.setReceiverName(dto.getReceiverName());
        receipt.setReceiverAccountBank(dto.getReceiverBank());

        receipt.setPayerName(milkPayDebitParty.getName().toUpperCase());
        receipt.setPayerDocument(milkPayDebitParty.getTaxId());
        receipt.setPayerAccount(milkPayDebitParty.getAccount());
        receipt.setPayerAccountType(milkPayDebitParty.getAccountType());
        receipt.setPayerAccountBank(milkPayDebitParty.getBankISPB());
        receipt.setPayerAccountBranch(milkPayDebitParty.getBranch());

        var receiptResume = ReceiptUtil.createReceiptBankSlip(receipt);
        var authentication = DigestUtils.md5Hex(receiptResume);
        receiptResume = ReceiptUtil.addReceiptAuth(receiptResume, authentication.toUpperCase());

        receipt.setReceiptResume(receiptResume);
        receipt.setAuthentication(authentication.toUpperCase());

        receipt.persistAndFlush();

        return receipt;

    }

    public BankSlipConsultResponseDto parseToConsultResponseDto(BankSlipCelcoinResponseConsultDto responseDto) {
        return mapper.bankSlipDtoToResponseDto(responseDto);
    }

    public BankSlipCelcoinPaymentDto parseToCelcoinPaymentDto(BankSlipPaymentDto dto) {
        return mapper.bankSlipPaymentDtoToCelcoinPaymentDto(dto);
    }

}
