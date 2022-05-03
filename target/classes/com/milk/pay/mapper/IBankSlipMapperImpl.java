package com.milk.pay.mapper;

import com.milk.pay.dto.bankslip.BankSlipCelcoinConsultDataDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinResponseConsultDto;
import com.milk.pay.dto.bankslip.BankSlipConsultResponseDto;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-02T21:52:05-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class IBankSlipMapperImpl implements IBankSlipMapper {

    @Override
    public BankSlipConsultResponseDto bankSlipDtoToResponseDto(BankSlipCelcoinResponseConsultDto dto) {
        if ( dto == null ) {
            return null;
        }

        BankSlipConsultResponseDto bankSlipConsultResponseDto = new BankSlipConsultResponseDto();

        bankSlipConsultResponseDto.setBank( dto.getAssignor() );
        bankSlipConsultResponseDto.setPayer( dtoRegisterDataPayer( dto ) );
        bankSlipConsultResponseDto.setRecipient( dtoRegisterDataRecipient( dto ) );
        bankSlipConsultResponseDto.setDocumentPayer( dtoRegisterDataDocumentPayer( dto ) );
        bankSlipConsultResponseDto.setDocumentRecipient( dtoRegisterDataDocumentRecipient( dto ) );
        bankSlipConsultResponseDto.setDiscount( dtoRegisterDataDiscountValue( dto ) );
        bankSlipConsultResponseDto.setFine( dtoRegisterDataFineValueCalculated( dto ) );
        bankSlipConsultResponseDto.setInterest( dtoRegisterDataInterestValueCalculated( dto ) );
        bankSlipConsultResponseDto.setAmount( dtoRegisterDataTotalUpdated( dto ) );
        bankSlipConsultResponseDto.setDigitable( dto.getDigitable() );
        bankSlipConsultResponseDto.setTransactionId( dto.getTransactionId() );

        bankSlipConsultResponseDto.setDueDate( parseDate(dto.getDueDate()) );

        return bankSlipConsultResponseDto;
    }

    private String dtoRegisterDataPayer(BankSlipCelcoinResponseConsultDto bankSlipCelcoinResponseConsultDto) {
        if ( bankSlipCelcoinResponseConsultDto == null ) {
            return null;
        }
        BankSlipCelcoinConsultDataDto registerData = bankSlipCelcoinResponseConsultDto.getRegisterData();
        if ( registerData == null ) {
            return null;
        }
        String payer = registerData.getPayer();
        if ( payer == null ) {
            return null;
        }
        return payer;
    }

    private String dtoRegisterDataRecipient(BankSlipCelcoinResponseConsultDto bankSlipCelcoinResponseConsultDto) {
        if ( bankSlipCelcoinResponseConsultDto == null ) {
            return null;
        }
        BankSlipCelcoinConsultDataDto registerData = bankSlipCelcoinResponseConsultDto.getRegisterData();
        if ( registerData == null ) {
            return null;
        }
        String recipient = registerData.getRecipient();
        if ( recipient == null ) {
            return null;
        }
        return recipient;
    }

    private String dtoRegisterDataDocumentPayer(BankSlipCelcoinResponseConsultDto bankSlipCelcoinResponseConsultDto) {
        if ( bankSlipCelcoinResponseConsultDto == null ) {
            return null;
        }
        BankSlipCelcoinConsultDataDto registerData = bankSlipCelcoinResponseConsultDto.getRegisterData();
        if ( registerData == null ) {
            return null;
        }
        String documentPayer = registerData.getDocumentPayer();
        if ( documentPayer == null ) {
            return null;
        }
        return documentPayer;
    }

    private String dtoRegisterDataDocumentRecipient(BankSlipCelcoinResponseConsultDto bankSlipCelcoinResponseConsultDto) {
        if ( bankSlipCelcoinResponseConsultDto == null ) {
            return null;
        }
        BankSlipCelcoinConsultDataDto registerData = bankSlipCelcoinResponseConsultDto.getRegisterData();
        if ( registerData == null ) {
            return null;
        }
        String documentRecipient = registerData.getDocumentRecipient();
        if ( documentRecipient == null ) {
            return null;
        }
        return documentRecipient;
    }

    private BigDecimal dtoRegisterDataDiscountValue(BankSlipCelcoinResponseConsultDto bankSlipCelcoinResponseConsultDto) {
        if ( bankSlipCelcoinResponseConsultDto == null ) {
            return null;
        }
        BankSlipCelcoinConsultDataDto registerData = bankSlipCelcoinResponseConsultDto.getRegisterData();
        if ( registerData == null ) {
            return null;
        }
        BigDecimal discountValue = registerData.getDiscountValue();
        if ( discountValue == null ) {
            return null;
        }
        return discountValue;
    }

    private BigDecimal dtoRegisterDataFineValueCalculated(BankSlipCelcoinResponseConsultDto bankSlipCelcoinResponseConsultDto) {
        if ( bankSlipCelcoinResponseConsultDto == null ) {
            return null;
        }
        BankSlipCelcoinConsultDataDto registerData = bankSlipCelcoinResponseConsultDto.getRegisterData();
        if ( registerData == null ) {
            return null;
        }
        BigDecimal fineValueCalculated = registerData.getFineValueCalculated();
        if ( fineValueCalculated == null ) {
            return null;
        }
        return fineValueCalculated;
    }

    private BigDecimal dtoRegisterDataInterestValueCalculated(BankSlipCelcoinResponseConsultDto bankSlipCelcoinResponseConsultDto) {
        if ( bankSlipCelcoinResponseConsultDto == null ) {
            return null;
        }
        BankSlipCelcoinConsultDataDto registerData = bankSlipCelcoinResponseConsultDto.getRegisterData();
        if ( registerData == null ) {
            return null;
        }
        BigDecimal interestValueCalculated = registerData.getInterestValueCalculated();
        if ( interestValueCalculated == null ) {
            return null;
        }
        return interestValueCalculated;
    }

    private BigDecimal dtoRegisterDataTotalUpdated(BankSlipCelcoinResponseConsultDto bankSlipCelcoinResponseConsultDto) {
        if ( bankSlipCelcoinResponseConsultDto == null ) {
            return null;
        }
        BankSlipCelcoinConsultDataDto registerData = bankSlipCelcoinResponseConsultDto.getRegisterData();
        if ( registerData == null ) {
            return null;
        }
        BigDecimal totalUpdated = registerData.getTotalUpdated();
        if ( totalUpdated == null ) {
            return null;
        }
        return totalUpdated;
    }
}
