package com.milk.pay.mapper;

import java.util.Arrays;

import com.milk.pay.dto.user.CreateUserDto;
import com.milk.pay.dto.user.ListUserDto;
import com.milk.pay.entities.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
