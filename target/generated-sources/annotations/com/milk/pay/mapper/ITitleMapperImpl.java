package com.milk.pay.mapper;

import com.milk.pay.dto.title.TitleCreateDto;
import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.entities.Title;
import java.text.SimpleDateFormat;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-14T06:31:12-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.2 (Eclipse Adoptium)"
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
        titleDto.setDailyFine( entity.getDailyFine() );
        if ( entity.getDueDate() != null ) {
            titleDto.setDueDate( new SimpleDateFormat().format( entity.getDueDate() ) );
        }
        if ( entity.hasId() ) {
            titleDto.setId( entity.getId() );
        }
        titleDto.setLiquidated( entity.isLiquidated() );

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
        title.setDueDate( dto.getDueDate() );
        title.setNfNumber( dto.getNfNumber() );

        return title;
    }
}
