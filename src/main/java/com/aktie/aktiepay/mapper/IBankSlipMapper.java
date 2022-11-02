package com.aktie.aktiepay.mapper;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinPaymentDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinResponseConsultDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipConsultResponseDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipPaymentDto;
import com.aktie.aktiepay.entities.Payment;
import com.aktie.aktiepay.utils.DateUtil;

/**
 *
 * @author SRamos
 */
@Mapper(componentModel = "cdi")
public interface IBankSlipMapper {

    @Mappings({
            @Mapping(target = "receiverBank", source = "assignor"),
            @Mapping(target = "payerName", source = "registerData.payer"),
            @Mapping(target = "receiverName", source = "registerData.recipient"),
            @Mapping(target = "payerDocument", source = "registerData.documentPayer"),
            @Mapping(target = "receiverDocument", source = "registerData.documentRecipient"),
            @Mapping(target = "discount", source = "registerData.discountValue"),
            @Mapping(target = "fine", source = "registerData.fineValueCalculated"),
            @Mapping(target = "interest", source = "registerData.interestValueCalculated"),
            @Mapping(target = "amount", source = "registerData.totalUpdated"),
            @Mapping(target = "dueDate", expression = "java(parseDate(dto.getDueDate()))"),
    })
    public BankSlipConsultResponseDto bankSlipDtoToResponseDto(BankSlipCelcoinResponseConsultDto dto);

    @Mappings({
            @Mapping(target = "barcode.barcode", source = "barcode"),
            @Mapping(target = "barcode.digitable", source = "digitable"),
            @Mapping(target = "billData.value", source = "amount"),
            @Mapping(target = "dueDate", ignore = true),
    })
    public BankSlipCelcoinPaymentDto bankSlipPaymentDtoToCelcoinPaymentDto(BankSlipPaymentDto dto);

    @Mappings({
            @Mapping(target = "barcode", source = "barcode.barcode"),
            @Mapping(target = "digitable", source = "barcode.digitable"),
            @Mapping(target = "requestedAmount", source = "billData.value")
    })
    public Payment bankSlipCelcoinPaymentDtoToPaymentEntity(BankSlipCelcoinPaymentDto dto);

    default String parseDate(LocalDateTime date) {
        return DateUtil.formatDDMMYYYY(date.toLocalDate());
    }

}
