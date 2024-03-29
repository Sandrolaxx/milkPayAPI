package com.aktie.aktiepay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.aktie.aktiepay.dto.pix.PixPaymentCelcoinDto;
import com.aktie.aktiepay.dto.pix.PixPaymentDto;

/**
 *
 * @author SRamos
 */
@Mapper(componentModel = "cdi")
public interface IPixMapper {

    @Mappings({ 
            @Mapping(target = "creditParty.key", source = "receiverKey"),
            @Mapping(target = "creditParty.bank", source = "receiverBank"),
            @Mapping(target = "creditParty.account", source = "receiverAccount"),
            @Mapping(target = "creditParty.branch", source = "receiverBranch"),
            @Mapping(target = "creditParty.taxId", source = "receiverDocument"),
            @Mapping(target = "creditParty.accountType", source = "receiverAccountType"),
            @Mapping(target = "creditParty.name", source = "receiverName"),
            @Mapping(target = "clientCode", ignore = true) })
    public PixPaymentCelcoinDto pixPaymentDtoToPixPaymentCelcoinDto(PixPaymentDto dto);

}
