package com.milk.pay.mapper;

import com.milk.pay.entities.PixPayment;
import com.milk.pay.pix.dto.PixPaymentCelcoinDto;
import com.milk.pay.pix.dto.PixPaymentCreditPartyDto;
import com.milk.pay.pix.dto.PixPaymentDto;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-08T20:22:20-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@ApplicationScoped
public class IPixMapperImpl implements IPixMapper {

    @Override
    public PixPaymentCelcoinDto pixPaymentDtoToPixPaymentCelcoinDto(PixPaymentDto dto) {
        if ( dto == null ) {
            return null;
        }

        PixPaymentCelcoinDto pixPaymentCelcoinDto = new PixPaymentCelcoinDto();

        pixPaymentCelcoinDto.setCreditParty( pixPaymentDtoToPixPaymentCreditPartyDto( dto ) );
        pixPaymentCelcoinDto.setAmount( dto.getAmount() );
        pixPaymentCelcoinDto.setEndToEndId( dto.getEndToEndId() );
        pixPaymentCelcoinDto.setTxId( dto.getTxId() );
        pixPaymentCelcoinDto.setInitiationType( dto.getInitiationType() );

        return pixPaymentCelcoinDto;
    }

    @Override
    public PixPayment pixPaymentDtoToPixPaymentEntity(PixPaymentDto dto) {
        if ( dto == null ) {
            return null;
        }

        PixPayment pixPayment = new PixPayment();

        pixPayment.setAmount( dto.getAmount() );
        pixPayment.setTxId( dto.getTxId() );
        pixPayment.setInitiationType( dto.getInitiationType() );
        pixPayment.setEndToEndId( dto.getEndToEndId() );
        pixPayment.setCreditAccountKey( dto.getCreditAccountKey() );
        pixPayment.setCreditAccountBank( dto.getCreditAccountBank() );
        pixPayment.setCreditAccount( dto.getCreditAccount() );
        pixPayment.setCreditAccountBranch( dto.getCreditAccountBranch() );
        pixPayment.setCreditAccountTaxId( dto.getCreditAccountTaxId() );
        pixPayment.setCreditAccountType( dto.getCreditAccountType() );
        pixPayment.setCreditAccountName( dto.getCreditAccountName() );

        return pixPayment;
    }

    protected PixPaymentCreditPartyDto pixPaymentDtoToPixPaymentCreditPartyDto(PixPaymentDto pixPaymentDto) {
        if ( pixPaymentDto == null ) {
            return null;
        }

        PixPaymentCreditPartyDto pixPaymentCreditPartyDto = new PixPaymentCreditPartyDto();

        pixPaymentCreditPartyDto.setKey( pixPaymentDto.getCreditAccountKey() );
        pixPaymentCreditPartyDto.setBank( pixPaymentDto.getCreditAccountBank() );
        pixPaymentCreditPartyDto.setAccount( pixPaymentDto.getCreditAccount() );
        pixPaymentCreditPartyDto.setBranch( pixPaymentDto.getCreditAccountBranch() );
        pixPaymentCreditPartyDto.setTaxId( pixPaymentDto.getCreditAccountTaxId() );
        pixPaymentCreditPartyDto.setAccountType( pixPaymentDto.getCreditAccountType() );
        pixPaymentCreditPartyDto.setName( pixPaymentDto.getCreditAccountName() );

        return pixPaymentCreditPartyDto;
    }
}
