package com.aktie.aktiepay.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aktie.aktiepay.dto.user.CreateUserDto;
import com.aktie.aktiepay.dto.user.UserInfoDto;
import com.aktie.aktiepay.entities.User;

/**
 *
 * @author SRamos
 */
@Mapper(componentModel = "cdi")
public interface IUserMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public User listUserDtoToUser(UserInfoDto dto);

    public User createUserDtoToUser(CreateUserDto dto);

    @Mapping(target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(target = "lastLogin", dateFormat = "dd/MM/yyyy HH:mm:ss")
    public UserInfoDto toUserInfoDto(User user);

}
