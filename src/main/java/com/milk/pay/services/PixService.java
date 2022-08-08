package com.milk.pay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.dto.pix.PixPaymentResponseDto;
import com.milk.pay.entities.IspbCode;
import com.milk.pay.entities.Payment;
import com.milk.pay.entities.ReceiptInfo;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.enums.EnumInitiationType;
import com.milk.pay.entities.enums.EnumMovementCode;
import com.milk.pay.mapper.IPixMapper;
import com.milk.pay.utils.NumericUtil;
import com.milk.pay.utils.ReceiptUtil;
import com.milk.pay.utils.RequestUtil;

import org.apache.commons.codec.digest.DigestUtils;

@ApplicationScoped
public class PixService {

    @Inject
    IPixMapper pixMapper;

    @Inject
    RequestUtil requestUtil;

    public PixPaymentCelcoinDto createCelcoinDto(PixPaymentDto paymentDto) {

        var pixPaymentCelcoinDto = pixMapper.pixPaymentDtoToPixPaymentCelcoinDto(paymentDto);
        var milkPayDebitParty = requestUtil.getMilkPayDebitParty();

        pixPaymentCelcoinDto.setClientCode(paymentDto.getTxId().toString());
        pixPaymentCelcoinDto.setDebitParty(milkPayDebitParty);
        pixPaymentCelcoinDto.setInitiationType(EnumInitiationType.DICT);

        return pixPaymentCelcoinDto;

    }

    @Transactional
    public Payment prePersistPayment(Integer titleId) {

        var payment = new Payment();
        var title = Title.findById(titleId);
        var interestPercentage = NumericUtil.getInterestPercentage(title.getDueDate(), title.getDailyInterest());
        var interestAmount = NumericUtil.getInterestAmount(title.getAmount(), interestPercentage);

        payment.setTitle(title);
        payment.setInitiationType(EnumInitiationType.DICT);
        payment.setInterestPercentage(interestPercentage);
        payment.setInterestAmount(interestAmount);
        payment.setRequestedAmount(title.getAmount());
        payment.setReceivedAmount(payment.getRequestedAmount().subtract(interestAmount));

        payment.persistAndFlush();

        return payment;

    }

    @Transactional
    public void persistSuccessfulPayment(PixPaymentDto paymentDto, String endToEndId) {

        var payment = Payment.findById(paymentDto.getTxId());
        var title = payment.getTitle();

        title.setBalance(BigDecimal.ZERO);
        title.setLiquidated(true);
        title.setPaidAt(LocalDateTime.now());

        payment.setEndToEndId(endToEndId);
        payment.setPixKey(paymentDto.getReceiverKey());
        payment.setLiquidated(true);

        payment.persist();

    }

    @Transactional
    public void savePixPaymentCashOutError(Integer entityId) {

        Payment pixPayment = Payment.findById(entityId);

        pixPayment.setLiquidated(false);

        pixPayment.persistAndFlush();

    }

    @Transactional
    public ReceiptInfo savePaymentReceipt(PixPaymentResponseDto responseDto, PixPaymentDto paymentDto) {

        var receipt = new ReceiptInfo();
        var lastReceipt = ReceiptInfo.findLastReceipt();
        var milkPayDebitParty = requestUtil.getMilkPayDebitParty();
        var ispb = IspbCode.findByCode(paymentDto.getReceiverBank());
        var payment = Payment.findById(paymentDto.getTxId());

        receipt.setLastAuthentication(lastReceipt != null ? lastReceipt.getAuthentication() : "GENESIS_BLOCK");
        receipt.setEndToEndId(responseDto.getEndToEndId());
        receipt.setMovementCode(EnumMovementCode.TRANSF_INTERBANCARIA_PIX);
        receipt.setAmount(paymentDto.getAmount());
        receipt.setExternalAuth(responseDto.getSlipAuth());
        receipt.setIspbCode(ispb);
        receipt.setPayment(payment);
        receipt.setExternalTxid(responseDto.getTxId().toString());

        receipt.setReceiverName(paymentDto.getReceiverName());
        receipt.setReceiverAccountKey(paymentDto.getReceiverKey());
        receipt.setReceiverDocument(paymentDto.getReceiverDocument());
        receipt.setReceiverAccount(paymentDto.getReceiverAccount());
        receipt.setReceiverAccountType(paymentDto.getReceiverAccountType());
        receipt.setReceiverAccountBank(resolveIspbBank(ispb));
        receipt.setReceiverAccountBranch(paymentDto.getReceiverBranch().toString());

        receipt.setPayerName(milkPayDebitParty.getName().toUpperCase());
        receipt.setPayerDocument(milkPayDebitParty.getTaxId());
        receipt.setPayerAccountType(milkPayDebitParty.getAccountType());
        receipt.setPayerAccountBank(milkPayDebitParty.getBankISPB());
        receipt.setPayerAccountBranch(milkPayDebitParty.getBranch());
        receipt.setPayerAccount(milkPayDebitParty.getAccount());

        var authentication = DigestUtils.md5Hex(receipt.toString());
        receipt.setAuthentication(authentication.toUpperCase());

        var receiptResume = ReceiptUtil.handleCreate(receipt);
        receipt.setReceiptResume(receiptResume);

        receipt.persistAndFlush();

        return receipt;

    }

    public String resolveIspbBank(IspbCode ispb) {
        return ispb != null ? ispb.getName() : "INSTITUIÇÃO NÃO IDENTIFICADA";
    }

}
