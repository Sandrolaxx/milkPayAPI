package com.milk.pay.mapper;

import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentCreditPartyCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-01T21:49:15-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
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

        return pixPaymentCelcoinDto;
    }

    protected PixPaymentCreditPartyCelcoinDto pixPaymentDtoToPixPaymentCreditPartyCelcoinDto(PixPaymentDto pixPaymentDto) {
        if ( pixPaymentDto == null ) {
            return null;
        }

        PixPaymentCreditPartyCelcoinDto pixPaymentCreditPartyCelcoinDto = new PixPaymentCreditPartyCelcoinDto();

        pixPaymentCreditPartyCelcoinDto.setKey( pixPaymentDto.getReceiverKey() );
        pixPaymentCreditPartyCelcoinDto.setBank( pixPaymentDto.getReceiverBank() );
        pixPaymentCreditPartyCelcoinDto.setAccount( pixPaymentDto.getReceiverAccount() );
        pixPaymentCreditPartyCelcoinDto.setBranch( pixPaymentDto.getReceiverBranch() );
        pixPaymentCreditPartyCelcoinDto.setTaxId( pixPaymentDto.getReceiverDocument() );
        if ( pixPaymentDto.getReceiverAccountType() != null ) {
            pixPaymentCreditPartyCelcoinDto.setAccountType( pixPaymentDto.getReceiverAccountType().name() );
        }
        pixPaymentCreditPartyCelcoinDto.setName( pixPaymentDto.getReceiverName() );

        return pixPaymentCreditPartyCelcoinDto;
    }
}
