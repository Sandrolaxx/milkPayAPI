package com.aktie.aktiepay.mapper;

import java.util.Arrays;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aktie.aktiepay.dto.user.CreateUserDto;
import com.aktie.aktiepay.dto.user.ListUserDto;
import com.aktie.aktiepay.entities.User;

/**
 *
 * @author SRamos
 */
@Mapper(componentModel = "cdi", imports = {Arrays.class})
public interface IUserMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public User listUserDtoToUser(ListUserDto dto);

    public User createUserDtoToUser(CreateUserDto dto);

    @Mapping(target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(target = "updatedAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    public ListUserDto toListUserDto(User user);

}
