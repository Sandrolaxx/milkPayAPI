package com.milk.pay.mapper;

import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.entities.Payment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author SRamos
 */
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

    public Payment pixPaymentDtoToPixPaymentEntity(PixPaymentDto dto);

}
