package com.milk.pay.mapper;

import java.time.LocalDateTime;

import com.milk.pay.dto.bankslip.BankSlipCelcoinResponseConsultDto;
import com.milk.pay.dto.bankslip.BankSlipConsultResponseDto;
import com.milk.pay.utils.DateUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author SRamos
 */
@Mapper(componentModel = "cdi")
public interface IBankSlipMapper {

    @Mappings({
            @Mapping(target = "bank", source = "assignor"),
            @Mapping(target = "payer", source = "registerData.payer"),
            @Mapping(target = "recipient", source = "registerData.recipient"),
            @Mapping(target = "documentPayer", source = "registerData.documentPayer"),
            @Mapping(target = "documentRecipient", source = "registerData.documentRecipient"),
            @Mapping(target = "discount", source = "registerData.discountValue"),
            @Mapping(target = "fine", source = "registerData.fineValueCalculated"),
            @Mapping(target = "interest", source = "registerData.interestValueCalculated"),
            @Mapping(target = "amount", source = "registerData.totalUpdated"),
            @Mapping(target = "dueDate", expression = "java(parseDate(dto.getDueDate()))"),
    })
    public BankSlipConsultResponseDto bankSlipDtoToResponseDto(BankSlipCelcoinResponseConsultDto dto);

    default String parseDate(LocalDateTime date) {
        return DateUtil.formatDDMMYYYY(date.toLocalDate());
    }

}
