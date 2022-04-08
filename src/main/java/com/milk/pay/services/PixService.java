package com.milk.pay.services;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.dto.pix.PixPaymentResponseDto;
import com.milk.pay.entities.Payment;
import com.milk.pay.entities.ReceiptInfo;
import com.milk.pay.entities.enums.EnumMovementCode;
import com.milk.pay.mapper.IPixMapper;
import com.milk.pay.utils.ReceiptUtil;
import com.milk.pay.utils.RequestUtil;

import org.apache.commons.codec.digest.DigestUtils;

@ApplicationScoped
public class PixService {

    @Inject
    IPixMapper pixMapper;

    @Inject
    RequestUtil requestUtil;

    public PixPaymentCelcoinDto createCelcoinDto(PixPaymentDto paymentDto, String clientCode) {

        var pixPaymentCelcoinDto = pixMapper.pixPaymentDtoToPixPaymentCelcoinDto(paymentDto);
        var milkPayDebitParty = requestUtil.getMilkPayDebitParty();

        pixPaymentCelcoinDto.setClientCode(clientCode);
        pixPaymentCelcoinDto.setDebitParty(milkPayDebitParty);
        pixPaymentCelcoinDto.setInitiationType(paymentDto.getInitiationType());

        return pixPaymentCelcoinDto;

    }

    @Transactional
    public void persistPixPaymentResponse(Integer entityId, PixPaymentResponseDto dto) {

        Payment pixPayment = Payment.findById(entityId);

        pixPayment.setExternalTxId(dto.getTxId());

        pixPayment.persistAndFlush();

    }

    @Transactional
    public void savePixPaymentCashOutError(Integer entityId) {

        Payment pixPayment = Payment.findById(entityId);

        pixPayment.setLiquidated(false);

        pixPayment.persistAndFlush();

    }
    
    @Transactional
    public String savePaymentReceipt(PixPaymentResponseDto responseDto, PixPaymentDto paymentDto) {

        var receiptPix = new ReceiptInfo();
        var lastReceipt = ReceiptInfo.findLastReceipt();
        var milkPayDebitParty = requestUtil.getMilkPayDebitParty();

        receiptPix.setLastAuthentication(lastReceipt != null ? lastReceipt.getAuthentication() : "GENESIS_BLOCK");
        receiptPix.setEndToEndId(paymentDto.getEndToEndId());
        receiptPix.setMovementCode(EnumMovementCode.TRANSF_INTERBANCARIA_PIX);
        receiptPix.setAmount(BigDecimal.valueOf(paymentDto.getAmount()));
        receiptPix.setExternalAuth(responseDto.getSlipAuth());

        receiptPix.setReceiverName(paymentDto.getCreditAccountName());
        receiptPix.setReceiverDocument(paymentDto.getCreditAccountTaxId());
        receiptPix.setReceiverAccountType(paymentDto.getCreditAccountType());
        receiptPix.setReceiverAccountKey(paymentDto.getCreditAccountKey());
        receiptPix.setReceiverAccountBank(paymentDto.getCreditAccountBank());
        receiptPix.setReceiverAccountBranch(paymentDto.getCreditAccountBranch().toString());
        receiptPix.setReceiverAccount(paymentDto.getCreditAccount());

        receiptPix.setPayerName(milkPayDebitParty.getName().toUpperCase());
        receiptPix.setPayerDocument(milkPayDebitParty.getTaxId());
        receiptPix.setPayerAccountType(milkPayDebitParty.getAccountType());
        receiptPix.setPayerAccountBank(milkPayDebitParty.getBankISPB());
        receiptPix.setPayerAccountBranch(milkPayDebitParty.getBranch());
        receiptPix.setPayerAccount(milkPayDebitParty.getAccount());

        var receipt = ReceiptUtil.createReceiptPix(receiptPix);
        var authentication = DigestUtils.md5Hex(receipt);

        receiptPix.setAuthentication(authentication.toUpperCase());

        receipt = ReceiptUtil.addReceiptAuth(receipt, authentication.toUpperCase());

        receiptPix.persistAndFlush();

        return receipt;
    }

}
