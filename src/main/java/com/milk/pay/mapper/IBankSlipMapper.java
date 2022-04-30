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
        @Mapping(target = "bank", source = "assignor"),
        @Mapping(target = "dueDate", expression = "java(parseDate(dto.getDueDate()))"),
    })
    public BankSlipConsultResponseDto titleDtoToEntity(BankSlipCelcoinResponseConsultDto dto);

    default LocalDateTime parseDate(LocalDateTime date) {
        return LocalDateTime.from(DateUtil.DDMMYYYYHHMMSS.format(date));
    }

}
