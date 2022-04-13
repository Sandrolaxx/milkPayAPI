package com.milk.pay.mapper;

import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.dto.user.CreateUserDto;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-12T19:41:28-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
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
        titleDto.setUser( userToCreateUserDto( entity.getUser() ) );
        if ( entity.hasId() ) {
            titleDto.setId( entity.getId() );
        }
        titleDto.setLiquidated( entity.isLiquidated() );
        titleDto.setDailyFine( entity.getDailyFine() );

        return titleDto;
    }

    @Override
    public Title titleDtoToEntity(TitleDto resDto) {
        if ( resDto == null ) {
            return null;
        }

        Title title = new Title();

        title.setId( resDto.getId() );
        title.setAmount( resDto.getAmount() );
        title.setBalance( resDto.getBalance() );
        title.setUser( createUserDtoToUser( resDto.getUser() ) );
        title.setLiquidated( resDto.isLiquidated() );
        title.setDailyFine( resDto.getDailyFine() );
        try {
            if ( resDto.getDueDate() != null ) {
                title.setDueDate( new SimpleDateFormat().parse( resDto.getDueDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }

        return title;
    }

    protected CreateUserDto userToCreateUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        CreateUserDto createUserDto = new CreateUserDto();

        createUserDto.setName( user.getName() );
        createUserDto.setEmail( user.getEmail() );
        createUserDto.setPassword( user.getPassword() );
        createUserDto.setDocument( user.getDocument() );
        createUserDto.setPhone( user.getPhone() );
        createUserDto.setPixKey( user.getPixKey() );

        return createUserDto;
    }

    protected User createUserDtoToUser(CreateUserDto createUserDto) {
        if ( createUserDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( createUserDto.getName() );
        user.setEmail( createUserDto.getEmail() );
        user.setPassword( createUserDto.getPassword() );
        user.setDocument( createUserDto.getDocument() );
        user.setPhone( createUserDto.getPhone() );
        user.setPixKey( createUserDto.getPixKey() );

        return user;
    }
}
