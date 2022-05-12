package com.milk.pay.mapper;

import com.milk.pay.dto.user.CreateUserDto;
import com.milk.pay.dto.user.ListUserDto;
import com.milk.pay.entities.User;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-12T07:29:14-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@ApplicationScoped
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User listUserDtoToUser(ListUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setName( dto.getName() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setDocument( dto.getDocument() );
        user.setPhone( dto.getPhone() );
        user.setPixKey( dto.getPixKey() );
        user.setActive( dto.getActive() );

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
        user.setPixKey( dto.getPixKey() );
        user.setActive( dto.isActive() );

        return user;
    }

    @Override
    public ListUserDto toListUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        ListUserDto listUserDto = new ListUserDto();

        if ( user.getCreatedAt() != null ) {
            listUserDto.setCreatedAt( DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ).format( user.getCreatedAt() ) );
        }
        if ( user.getUpdatedAt() != null ) {
            listUserDto.setUpdatedAt( DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ).format( user.getUpdatedAt() ) );
        }
        listUserDto.setId( user.getId() );
        listUserDto.setName( user.getName() );
        listUserDto.setEmail( user.getEmail() );
        listUserDto.setPassword( user.getPassword() );
        listUserDto.setDocument( user.getDocument() );
        listUserDto.setPhone( user.getPhone() );
        listUserDto.setActive( user.isActive() );
        listUserDto.setPixKey( user.getPixKey() );

        return listUserDto;
    }
}
