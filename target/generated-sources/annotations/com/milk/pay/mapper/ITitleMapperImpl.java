package com.milk.pay.mapper;

import com.milk.pay.dto.title.TitleCreateDto;
import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.entities.Title;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-26T21:19:34-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@ApplicationScoped
public class ITitleMapperImpl implements ITitleMapper {

    @Override
    public TitleDto titleToTitleDto(Title entity) {
        if ( entity == null ) {
            return null;
        }

        TitleDto titleDto = new TitleDto();

        titleDto.setAmount( entity.getAmount() );
        titleDto.setBalance( entity.getBalance() );
        if ( entity.getDueDate() != null ) {
            titleDto.setDueDate( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getDueDate() ) );
        }
        if ( entity.getPaidAt() != null ) {
            titleDto.setPaidAt( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getPaidAt() ) );
        }
        titleDto.setBarcode( entity.getBarcode() );
        titleDto.setDigitable( entity.getDigitable() );
        if ( entity.hasId() ) {
            titleDto.setId( entity.getId() );
        }
        titleDto.setLiquidated( entity.isLiquidated() );
        titleDto.setDailyInterest( entity.getDailyInterest() );
        titleDto.setPaymentType( entity.getPaymentType() );
        titleDto.setNfNumber( entity.getNfNumber() );
        titleDto.setInclusionDate( entity.getInclusionDate() );

        titleDto.setPixKey( getUserPixKey(entity.getUser(), entity.getPaymentType()) );

        return titleDto;
    }

    @Override
    public Title titleDtoToEntity(TitleCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Title title = new Title();

        title.setAmount( dto.getAmount() );
        title.setDailyInterest( dto.getDailyInterest() );
        title.setNfNumber( dto.getNfNumber() );
        title.setBarcode( dto.getBarcode() );
        title.setDigitable( dto.getDigitable() );
        title.setPaymentType( dto.getPaymentType() );

        title.setDueDate( parseLocalDate(dto.getDueDate()) );
        title.setInclusionDate( parseDate(dto.getInclusionDate()) );

        return title;
    }
}
