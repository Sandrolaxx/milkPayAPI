package com.aktie.aktiepay.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.aktie.aktiepay.dto.user.CreateUserDto;
import com.aktie.aktiepay.dto.user.CreateUserKeycloakCredentialsDto;
import com.aktie.aktiepay.dto.user.CreateUserKeycloakDto;
import com.aktie.aktiepay.dto.user.UserInfoDto;
import com.aktie.aktiepay.entities.User;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;
import com.aktie.aktiepay.entities.enums.EnumUserType;
import com.aktie.aktiepay.mapper.IUserMapper;
import com.aktie.aktiepay.utils.AktiePayException;
import com.aktie.aktiepay.utils.EncryptUtil;
import com.aktie.aktiepay.utils.StringUtil;
import com.aktie.aktiepay.utils.Utils;

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

    @Transactional
    public UserInfoDto findInfoById(String userId) {

        var user = findAndValidadeUser(userId);

        user.setLastLogin(LocalDateTime.now());

        return userMapper.toUserInfoDto(user);

    }

    @Transactional
    public void create(CreateUserDto dto) {

        var newUser = persistNewUser(dto, dto.getPassword());

        createUserKeycloak(newUser);

    }

    @Transactional
    public UserInfoDto update(UserInfoDto dto, SecurityIdentity identity) {

        var user = findAndValidadeUser(Utils.resolveUserId(identity));

        if (!StringUtil.isNullOrEmpty(dto.getPassword())
                && !dto.getPassword().equals(user.getPassword())) {
            user.setPassword(dto.getPassword());
        }

        if (!StringUtil.isNullOrEmpty(dto.getName())
                && !dto.getName().equals(user.getName())) {
            user.setName(dto.getName());
        }

        if (!StringUtil.isNullOrEmpty(dto.getEmail())
                && !dto.getEmail().equals(user.getEmail())) {
            user.setEmail(dto.getEmail());
        }

        if (!StringUtil.isNullOrEmpty(dto.getPhone())
                && !dto.getPhone().equals(user.getPhone())) {
            user.setPhone(dto.getPhone());
        }

        if (!StringUtil.isNullOrEmpty(dto.getPixKey())
                && !dto.getPixKey().equals(user.getPixKey())) {
            user.setPixKey(dto.getPixKey());
        }

        if (!StringUtil.isNullOrEmpty(dto.getAddress())
                && !dto.getAddress().equals(user.getAddress())) {
            user.setAddress(dto.getAddress());
        }

        if (!StringUtil.isNullOrEmpty(dto.getPostalCode())
                && !dto.getPostalCode().equals(user.getPostalCode())) {
            user.setPostalCode(dto.getPostalCode());
        }

        if (dto.getType() != null) {
            user.setType(dto.getType());
        }

        updateUserKeycloak(user, dto, Utils.resolveKeyclockUserId(identity));

        return userMapper.toUserInfoDto(user);
    }

    public User persistNewUser(CreateUserDto userData, String password) {

        var newUser = userMapper.createUserDtoToUser(userData);

        newUser.setPassword(password);
        newUser.setActive(true);
        newUser.setType(EnumUserType.COMMON);

        newUser.persist();

        return newUser;

    }

    public void createUserKeycloak(User newUser) {

        var newUserKeycloak = new CreateUserKeycloakDto();
        var newCredencial = new CreateUserKeycloakCredentialsDto();

        newUserKeycloak.setUsername(newUser.getDocument());
        newUserKeycloak.setEnabled(true);

        newCredencial.setTemporary(false);
        newCredencial.setValue(EncryptUtil.textDecrypt(newUser.getPassword(), newUser.getSecret()));

        newUserKeycloak.setCredentials(List.of(newCredencial));
        newUserKeycloak.setAttributes(Map.of("userId", newUser.getId().toString()));

        try {
            keycloakService.createUserKeycloak(newUserKeycloak);
        } catch (Exception e) {
            throw new AktiePayException(EnumErrorCode.ERRO_AO_CADASTRAR_USUARIO);
        }

    }

    public void updateUserKeycloak(User user, UserInfoDto dto, String userId) {

        var updatedUserKeycloak = new CreateUserKeycloakDto();
        var newCredencial = new CreateUserKeycloakCredentialsDto();

        if (!StringUtil.isNullOrEmpty(dto.getPassword())
                && dto.getPassword().equals(user.getPassword())) {
            newCredencial.setTemporary(false);
            newCredencial.setValue(dto.getPassword());

            updatedUserKeycloak.setCredentials(List.of(newCredencial));
        }

        if (!StringUtil.isNullOrEmpty(dto.getName())
                && dto.getName().equals(user.getName())) {
            updatedUserKeycloak.setFirstName(dto.getName());
        }

        if (!StringUtil.isNullOrEmpty(dto.getEmail())
                && dto.getEmail().equals(user.getEmail())) {
            updatedUserKeycloak.setEmail(dto.getEmail());
        }

        if (updatedUserKeycloak.getCredentials() != null
                || !StringUtil.isNullOrEmpty(updatedUserKeycloak.getEmail())
                || !StringUtil.isNullOrEmpty(updatedUserKeycloak.getFirstName())) {
            updatedUserKeycloak.setEnabled(true);

            try {
                keycloakService.updateUserKeycloak(updatedUserKeycloak, userId);
            } catch (Exception e) {
                throw new AktiePayException(EnumErrorCode.ERRO_AO_ATUALIZAR_USUARIO);
            }
        }

    }

    private User findAndValidadeUser(String userId) {
        var user = User.findById(UUID.fromString(userId));

        if (user == null) {
            throw new AktiePayException(EnumErrorCode.USUARIO_JWT_NAO_ENCONTRADO);
        }

        return (User) user;
    }

}