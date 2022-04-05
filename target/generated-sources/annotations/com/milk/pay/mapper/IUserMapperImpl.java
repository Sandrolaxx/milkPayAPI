package com.milk.pay.mapper;

import com.milk.pay.dto.user.CreateUserDto;
import com.milk.pay.dto.user.ListUserDto;
import com.milk.pay.entities.User;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-04T22:01:19-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@ApplicationScoped
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User listUserDtoToUser(ListUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setName( dto.getName() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setDocument( dto.getDocument() );
        user.setPhone( dto.getPhone() );
        user.setActive( dto.getActive() );
        user.setAcceptTerms( dto.isAcceptTerms() );
        user.setPostalCode( dto.getPostalCode() );
        user.setPixKey( dto.getPixKey() );

        return user;
    }

    @Override
    public User createUserDtoToUser(CreateUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setName( dto.getName() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setDocument( dto.getDocument() );
        user.setPhone( dto.getPhone() );
        user.setActive( dto.isActive() );
        user.setAcceptTerms( dto.isAcceptTerms() );
        user.setPostalCode( dto.getPostalCode() );
        user.setAddressName( dto.getAddressName() );
        user.setPixKey( dto.getPixKey() );

        return user;
    }

    @Override
    public ListUserDto toListUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        ListUserDto listUserDto = new ListUserDto();

        if ( user.getCreatedAt() != null ) {
            listUserDto.setCreatedAt( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( user.getCreatedAt() ) );
        }
        if ( user.getUpdatedAt() != null ) {
            listUserDto.setUpdatedAt( new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" ).format( user.getUpdatedAt() ) );
        }
        listUserDto.setId( user.getId() );
        listUserDto.setName( user.getName() );
        listUserDto.setEmail( user.getEmail() );
        listUserDto.setPassword( user.getPassword() );
        listUserDto.setDocument( user.getDocument() );
        listUserDto.setPhone( user.getPhone() );
        if ( user.getActive() != null ) {
            listUserDto.setActive( user.getActive() );
        }
        if ( user.getAcceptTerms() != null ) {
            listUserDto.setAcceptTerms( user.getAcceptTerms() );
        }
        listUserDto.setPostalCode( user.getPostalCode() );
        listUserDto.setPixKey( user.getPixKey() );

        return listUserDto;
    }
}
