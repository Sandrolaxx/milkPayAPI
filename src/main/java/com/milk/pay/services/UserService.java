package com.milk.pay.services;

import java.util.ArrayList;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.milk.pay.dto.user.CreateUserDto;
import com.milk.pay.dto.user.CreateUserKeycloakCredentialsDto;
import com.milk.pay.dto.user.CreateUserKeycloakDto;
import com.milk.pay.dto.user.ListUserDto;
import com.milk.pay.entities.User;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.mapper.IUserMapper;
import com.milk.pay.utils.EncryptUtil;
import com.milk.pay.utils.MilkPayException;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class UserService {

    @Inject
    IUserMapper userMapper;

    @Inject
    KeycloakService keycloakService;

    public ListUserDto findInfoById(Integer idUser) {
        return userMapper.toListUserDto((User) User.findById(idUser));
    }

    @Transactional()
    public User persistUser(CreateUserDto dto) {

        var newUser = userMapper.createUserDtoToUser(dto);

        newUser.setActive(true);
        newUser.persistAndFlush();

        saveUserKeycloak(newUser);

        return newUser;

    }

    public void saveUserKeycloak(User newUser) {

        var newUserKeycloak = new CreateUserKeycloakDto();
        var newCredencial = new CreateUserKeycloakCredentialsDto();
        var credencialList = new ArrayList<CreateUserKeycloakCredentialsDto>();

        newUserKeycloak.setUsername(EncryptUtil.textDecrypt(newUser.getEmail(), newUser.getSecret()));
        newUserKeycloak.setEnabled(true);

        newCredencial.setTemporary(false);
        newCredencial.setValue(EncryptUtil.textDecrypt(newUser.getPassword(), newUser.getSecret()));

        credencialList.add(newCredencial);

        newUserKeycloak.setCredentials(credencialList);
        newUserKeycloak.setAttributes(Map.of("userId", newUser.getId().toString()));

        try {
            keycloakService.createUserKeycloak(newUserKeycloak);
        } catch (Exception e) {
            throw new MilkPayException(EnumErrorCode.ERRO_AO_CADASTRAR_USUARIO);
        }

    }

}