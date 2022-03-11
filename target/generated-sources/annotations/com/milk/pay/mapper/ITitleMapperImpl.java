package com.milk.pay.mapper;

import com.milk.pay.entities.Title;
import com.milk.pay.entities.User;
import com.milk.pay.title.dto.TitleDto;
import com.milk.pay.user.dto.CreateUserDto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-11T05:50:30-0300",
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
        titleDto.setCompanyId( entity.getCompanyId() );
        titleDto.setDailyFine( entity.getDailyFine() );
        if ( entity.getDueDate() != null ) {
            titleDto.setDueDate( new SimpleDateFormat().format( entity.getDueDate() ) );
        }
        if ( entity.hasId() ) {
            titleDto.setId( entity.getId() );
        }
        titleDto.setLiquidated( entity.isLiquidated() );
        if ( entity.getPaidDate() != null ) {
            titleDto.setPaidDate( new SimpleDateFormat().format( entity.getPaidDate() ) );
        }
        titleDto.setTxId( entity.getTxId() );
        titleDto.setUser( userToCreateUserDto( entity.getUser() ) );

        return titleDto;
    }

    @Override
    public Title titleDtoToEntity(TitleDto resDto) {
        if ( resDto == null ) {
            return null;
        }

        Title title = new Title();

        title.setAmount( resDto.getAmount() );
        title.setBalance( resDto.getBalance() );
        title.setCompanyId( resDto.getCompanyId() );
        title.setDailyFine( resDto.getDailyFine() );
        try {
            if ( resDto.getDueDate() != null ) {
                title.setDueDate( new SimpleDateFormat().parse( resDto.getDueDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        title.setId( resDto.getId() );
        title.setLiquidated( resDto.isLiquidated() );
        try {
            if ( resDto.getPaidDate() != null ) {
                title.setPaidDate( new SimpleDateFormat().parse( resDto.getPaidDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        title.setTxId( resDto.getTxId() );
        title.setUser( createUserDtoToUser( resDto.getUser() ) );

        return title;
    }

    protected CreateUserDto userToCreateUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        CreateUserDto createUserDto = new CreateUserDto();

        if ( user.getAcceptTerms() != null ) {
            createUserDto.setAcceptTerms( user.getAcceptTerms() );
        }
        if ( user.getActive() != null ) {
            createUserDto.setActive( user.getActive() );
        }
        createUserDto.setAddressName( user.getAddressName() );
        createUserDto.setDocument( user.getDocument() );
        createUserDto.setEmail( user.getEmail() );
        createUserDto.setName( user.getName() );
        createUserDto.setPassword( user.getPassword() );
        createUserDto.setPhone( user.getPhone() );
        createUserDto.setPixKey( user.getPixKey() );
        createUserDto.setPostalCode( user.getPostalCode() );

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
        user.setActive( createUserDto.isActive() );
        user.setAcceptTerms( createUserDto.isAcceptTerms() );
        user.setPostalCode( createUserDto.getPostalCode() );
        user.setAddressName( createUserDto.getAddressName() );
        user.setPixKey( createUserDto.getPixKey() );

        return user;
    }
}
