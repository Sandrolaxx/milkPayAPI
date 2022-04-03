package com.milk.pay.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.dto.user.CreateUserDto;
import com.milk.pay.dto.user.ListUserDto;
import com.milk.pay.entities.User;
import com.milk.pay.mapper.IUserMapper;

@ApplicationScoped
public class UserService {

    @Inject
    IUserMapper userMapper;

    public List<ListUserDto> findAll() {

        List<User> userList = User.listAll();

        return userList.stream()
                       .map(p -> userMapper.toListUserDto(p))
                       .collect(Collectors.toList());

    }

    public ListUserDto findInfoById(Integer idUser) {
        var user = (User) User.findById(idUser);

        return userMapper.toListUserDto(user);
    }

    @Transactional()
    public User persistUser(CreateUserDto dto) {
        User newUser = userMapper.createUserDtoToUser(dto);

        newUser.persistAndFlush();
        
        return newUser;
    }

}