package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.dto.pix.PixPaymentResponseDto;
import com.milk.pay.entities.IspbCode;
import com.milk.pay.entities.Payment;
import com.milk.pay.entities.ReceiptInfo;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.entities.enums.EnumInitiationType;
import com.milk.pay.entities.enums.EnumMovementCode;
import com.milk.pay.mapper.IPixMapper;
import com.milk.pay.utils.MilkPayException;
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
    public Payment persistPayment(PixPaymentDto dto) {

        var pixPayment = pixMapper.pixPaymentDtoToPixPaymentEntity(dto);
        var title = Title.findById(dto.getTitleId());

        if (title == null) {
            throw new MilkPayException(EnumErrorCode.ERRO_AO_ENCONTRAR_TITULO);
        }

        var interestPercentage = NumericUtil.getInterestPercentage(title.getDueDate(), title.getDailyInterest());

        pixPayment.setTitle(title);
        pixPayment.setAmount(title.getAmount());
        pixPayment.setInitiationType(EnumInitiationType.DICT);
        pixPayment.setInterestPercentage(interestPercentage);
        pixPayment.setInterestValue(NumericUtil.getInterestAmount(title.getAmount(), interestPercentage));

        try {
            pixPayment.persistAndFlush();
        } catch (PersistenceException e) {
            throw new MilkPayException(EnumErrorCode.PAGAMENTO_PIX_JA_REALIZADO);
        }

        return pixPayment;

    }

    @Transactional
    public void savePixPaymentCashOutError(Integer entityId) {

        Payment pixPayment = Payment.findById(entityId);

        pixPayment.setLiquidated(false);

        pixPayment.persistAndFlush();

    }
    
    @Transactional
    public ReceiptInfo savePaymentReceipt(PixPaymentResponseDto responseDto, PixPaymentDto paymentDto) {

        var receiptPix = new ReceiptInfo();
        var lastReceipt = ReceiptInfo.findLastReceipt();
        var milkPayDebitParty = requestUtil.getMilkPayDebitParty();
        var ispb = IspbCode.findByCode(paymentDto.getReceiverBank());
        var payment = Payment.findById(paymentDto.getTxId());

        receiptPix.setLastAuthentication(lastReceipt != null ? lastReceipt.getAuthentication() : "GENESIS_BLOCK");
        receiptPix.setEndToEndId(paymentDto.getEndToEndId());
        receiptPix.setMovementCode(EnumMovementCode.TRANSF_INTERBANCARIA_PIX);
        receiptPix.setAmount(paymentDto.getAmount());
        receiptPix.setExternalAuth(responseDto.getSlipAuth());
        receiptPix.setIspbCode(ispb);
        receiptPix.setPayment(payment);
        receiptPix.setExternalTxid(responseDto.getTxId().toString());

        receiptPix.setReceiverName(paymentDto.getReceiverName());
        receiptPix.setReceiverAccountKey(paymentDto.getReceiverKey());
        receiptPix.setReceiverDocument(paymentDto.getReceiverDocument());
        receiptPix.setReceiverAccount(paymentDto.getReceiverAccount());
        receiptPix.setReceiverAccountType(paymentDto.getReceiverAccountType());
        receiptPix.setReceiverAccountBank(resolveIspbBank(ispb));
        receiptPix.setReceiverAccountBranch(paymentDto.getReceiverBranch().toString());

        receiptPix.setPayerName(milkPayDebitParty.getName().toUpperCase());
        receiptPix.setPayerDocument(milkPayDebitParty.getTaxId());
        receiptPix.setPayerAccountType(milkPayDebitParty.getAccountType());
        receiptPix.setPayerAccountBank(milkPayDebitParty.getBankISPB());
        receiptPix.setPayerAccountBranch(milkPayDebitParty.getBranch());
        receiptPix.setPayerAccount(milkPayDebitParty.getAccount());

        var receiptResume = ReceiptUtil.createReceiptPix(receiptPix);
        var authentication = DigestUtils.md5Hex(receiptResume);
        receiptResume = ReceiptUtil.addReceiptAuth(receiptResume, authentication.toUpperCase());

        receiptPix.setReceiptResume(receiptResume);
        receiptPix.setAuthentication(authentication.toUpperCase());

        receiptPix.persistAndFlush();

        return receiptPix;
        
    }

    public String resolveIspbBank(IspbCode ispb) {
        return ispb != null ? ispb.getName() : "INSTITUIÇÃO NÃO IDENTIFICADA";
    }

}
