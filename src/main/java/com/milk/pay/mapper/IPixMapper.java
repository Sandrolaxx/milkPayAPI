package com.milk.pay.mapper;

import com.milk.pay.entities.PixPayment;
import com.milk.pay.pix.dto.PixPaymentCelcoinDto;
import com.milk.pay.pix.dto.PixPaymentDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "cdi")
public interface IPixMapper {

    @Mappings({ 
            @Mapping(target = "creditParty.key", source = "creditAccountKey"),
            @Mapping(target = "creditParty.bank", source = "creditAccountBank"),
            @Mapping(target = "creditParty.account", source = "creditAccount"),
            @Mapping(target = "creditParty.branch", source = "creditAccountBranch"),
            @Mapping(target = "creditParty.taxId", source = "creditAccountTaxId"),
            @Mapping(target = "creditParty.accountType", source = "creditAccountType"),
            @Mapping(target = "creditParty.name", source = "creditAccountName"),
            @Mapping(target = "clientCode", ignore = true) })
    public PixPaymentCelcoinDto pixPaymentDtoToPixPaymentCelcoinDto(PixPaymentDto dto);

    public PixPayment pixPaymentDtoToPixPaymentEntity(PixPaymentDto dto);

}
