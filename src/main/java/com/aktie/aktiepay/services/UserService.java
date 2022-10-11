package com.aktie.aktiepay.services;

import java.util.ArrayList;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.aktie.aktiepay.dto.user.CreateUserDto;
import com.aktie.aktiepay.dto.user.CreateUserKeycloakCredentialsDto;
import com.aktie.aktiepay.dto.user.CreateUserKeycloakDto;
import com.aktie.aktiepay.dto.user.ListUserDto;
import com.aktie.aktiepay.entities.User;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;
import com.aktie.aktiepay.mapper.IUserMapper;
import com.aktie.aktiepay.utils.EncryptUtil;
import com.aktie.aktiepay.utils.AktiePayException;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.identity.SecurityIdentity;

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
    public void create(CreateUserDto dto) {

        var newUser = persistNewUser(dto, dto.getPassword());

        createUserKeycloak(newUser);

    }

    public User persistNewUser(CreateUserDto userData, String password) {

        var newUser = userMapper.createUserDtoToUser(userData);

        newUser.setPassword(password);
        newUser.setActive(true);

        newUser.persist();

        return newUser;

    }

    public void createUserKeycloak(User newUser) {

        var newUserKeycloak = new CreateUserKeycloakDto();
        var newCredencial = new CreateUserKeycloakCredentialsDto();
        var credencialList = new ArrayList<CreateUserKeycloakCredentialsDto>();

        newUserKeycloak.setUsername(newUser.getDocument());
        newUserKeycloak.setEnabled(true);

        newCredencial.setTemporary(false);
        newCredencial.setValue(EncryptUtil.textDecrypt(newUser.getPassword(), newUser.getSecret()));

        credencialList.add(newCredencial);

        newUserKeycloak.setCredentials(credencialList);
        newUserKeycloak.setAttributes(Map.of("userId", newUser.getId().toString()));

        try {
            keycloakService.createUserKeycloak(newUserKeycloak);
        } catch (Exception e) {
            throw new AktiePayException(EnumErrorCode.ERRO_AO_CADASTRAR_USUARIO);
        }

    }

    public String resolveUserId(SecurityIdentity identity) {
        var tokenInfo = (OidcJwtCallerPrincipal) identity.getPrincipal();

        return (String) tokenInfo.getClaim("userId");
    }

}