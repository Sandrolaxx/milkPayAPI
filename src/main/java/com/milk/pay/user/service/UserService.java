package com.milk.pay.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.entities.User;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.mapper.IUserMapper;
import com.milk.pay.user.dto.CreateUserDto;
import com.milk.pay.user.dto.ListUserDto;
import com.milk.pay.user.dto.UpdateUserDto;
import com.milk.pay.utils.MilkPayException;

import io.quarkus.security.identity.SecurityIdentity;

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

    @Transactional()
    public User updateUser(Integer idUser, UpdateUserDto dto) throws MilkPayException {

        User updatedUser = User.findById(idUser);

        if (updatedUser == null) {
            throw new MilkPayException(EnumErrorCode.USUARIO_NAO_ENCONTRADO);
        }

        updatedUser.setName(dto.getName() == null ? updatedUser.getName() : dto.getName());
        updatedUser.setEmail(dto.getEmail() == null ? updatedUser.getEmail() : dto.getEmail());
        updatedUser.setPhone(dto.getPhone() == null ? updatedUser.getPhone() : dto.getPhone());
        updatedUser.setDocument(dto.getDocument() == null ? updatedUser.getDocument() : dto.getDocument());
        updatedUser.setPassword(dto.getPassword() == null ? updatedUser.getPassword() : dto.getPassword());
        updatedUser.setActive(dto.isActive() == updatedUser.isActive()  ? 
                updatedUser.isActive() : dto.isActive());
        updatedUser.setAcceptTerms(dto.isAcceptTerms() == updatedUser.isAcceptTerms() ? 
                updatedUser.isAcceptTerms() : dto.isAcceptTerms());

        return updatedUser;
        
    }

    @Transactional()
    public void deleteUser(Integer idUser, SecurityIdentity identity) {
        
        Optional<User> userToDelete = User.findByIdOptional(idUser);

        userToDelete.ifPresentOrElse(User::delete, () -> {
            throw new MilkPayException(EnumErrorCode.USUARIO_NAO_ENCONTRADO);
        });

    }

}