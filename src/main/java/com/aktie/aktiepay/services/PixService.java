package com.aktie.aktiepay.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;

import com.aktie.aktiepay.dto.pix.PixPaymentDto;
import com.aktie.aktiepay.dto.pix.PixPaymentResponseDto;
import com.aktie.aktiepay.entities.IspbCode;
import com.aktie.aktiepay.entities.Payment;
import com.aktie.aktiepay.entities.ReceiptInfo;
import com.aktie.aktiepay.entities.Title;
import com.aktie.aktiepay.entities.enums.EnumInitiationType;
import com.aktie.aktiepay.entities.enums.EnumMovementCode;
import com.aktie.aktiepay.mapper.IPixMapper;
import com.aktie.aktiepay.utils.NumericUtil;
import com.aktie.aktiepay.utils.ReceiptUtil;
import com.aktie.aktiepay.utils.RequestUtil;

@ApplicationScoped
public class PixService {

    @Inject
    IPixMapper pixMapper;

    @Inject
    RequestUtil requestUtil;

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
    public String savePaymentReceipt(PixPaymentResponseDto responseDto, PixPaymentDto paymentDto) {

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
        receipt.setPaidAt(payment.getCreatedAt());
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
        
        receipt.persistAndFlush();

        return ReceiptUtil.handleCreate(receipt);

    }

    public String resolveIspbBank(IspbCode ispb) {
        return ispb != null ? ispb.getName() : "INSTITUIÇÃO NÃO IDENTIFICADA";
    }

}
