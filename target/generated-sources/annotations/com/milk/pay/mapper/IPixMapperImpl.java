package com.milk.pay.mapper;

import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentCreditPartyCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.entities.Payment;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-11T21:43:41-0300",
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

        pixPaymentCelcoinDto.setCreditParty( pixPaymentDtoToPixPaymentCreditPartyCelcoinDto( dto ) );
        pixPaymentCelcoinDto.setAmount( dto.getAmount() );
        pixPaymentCelcoinDto.setEndToEndId( dto.getEndToEndId() );
        pixPaymentCelcoinDto.setInitiationType( dto.getInitiationType() );
        pixPaymentCelcoinDto.setTxId( dto.getTxId() );

        return pixPaymentCelcoinDto;
    }

    @Override
    public Payment pixPaymentDtoToPixPaymentEntity(PixPaymentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setAmount( dto.getAmount() );
        payment.setEndToEndId( dto.getEndToEndId() );
        payment.setInitiationType( dto.getInitiationType() );

        return payment;
    }

    protected PixPaymentCreditPartyCelcoinDto pixPaymentDtoToPixPaymentCreditPartyCelcoinDto(PixPaymentDto pixPaymentDto) {
        if ( pixPaymentDto == null ) {
            return null;
        }

        PixPaymentCreditPartyCelcoinDto pixPaymentCreditPartyCelcoinDto = new PixPaymentCreditPartyCelcoinDto();

        pixPaymentCreditPartyCelcoinDto.setKey( pixPaymentDto.getCreditAccountKey() );
        pixPaymentCreditPartyCelcoinDto.setBank( pixPaymentDto.getCreditAccountBank() );
        pixPaymentCreditPartyCelcoinDto.setAccount( pixPaymentDto.getCreditAccount() );
        pixPaymentCreditPartyCelcoinDto.setBranch( pixPaymentDto.getCreditAccountBranch() );
        pixPaymentCreditPartyCelcoinDto.setTaxId( pixPaymentDto.getCreditAccountTaxId() );
        pixPaymentCreditPartyCelcoinDto.setAccountType( pixPaymentDto.getCreditAccountType() );
        pixPaymentCreditPartyCelcoinDto.setName( pixPaymentDto.getCreditAccountName() );

        return pixPaymentCreditPartyCelcoinDto;
    }
}
