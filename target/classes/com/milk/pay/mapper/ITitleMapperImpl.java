package com.milk.pay.mapper;

import com.milk.pay.dto.title.TitleCreateDto;
import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.entities.Title;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-14T06:28:46-0300",
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
            titleDto.setDueDate( new SimpleDateFormat().format( entity.getDueDate() ) );
        }
        if ( entity.hasId() ) {
            titleDto.setId( entity.getId() );
        }
        titleDto.setLiquidated( entity.isLiquidated() );
        titleDto.setDailyFine( entity.getDailyFine() );

        return titleDto;
    }

    @Override
    public Title titleDtoToEntity(TitleCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Title title = new Title();

        title.setAmount( dto.getAmount() );
        title.setDailyFine( dto.getDailyFine() );
        try {
            if ( dto.getDueDate() != null ) {
                title.setDueDate( new SimpleDateFormat().parse( dto.getDueDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        title.setNfNumber( dto.getNfNumber() );

        return title;
    }
}
