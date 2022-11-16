package com.aktie.aktiepay.controllers;

import javax.inject.Inject;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.aktie.aktiepay.dto.user.CreateUserDto;
import com.aktie.aktiepay.dto.user.UserInfoDto;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;
import com.aktie.aktiepay.services.UserService;
import com.aktie.aktiepay.utils.AktiePayException;
import com.aktie.aktiepay.utils.AktiePayExceptionResponseDto;
import com.aktie.aktiepay.utils.Utils;
import com.aktie.aktiepay.utils.ValidateUtil;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.identity.SecurityIdentity;

/**
 *
 * @author SRamos
 */
@Tag(name = "User")
@Path("/milkpay-api/v1/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @Inject
    SecurityIdentity identity;

    @GET
    @APIResponse(responseCode = "200", description = "Caso usuário exista na base de dados, retorna seus dados.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    public UserInfoDto getUserInfo() {
        return userService.findInfoById(Utils.resolveUserId(identity));
    }

    @POST
    @APIResponse(responseCode = "201", description = "Caso seja cadastrado com sucesso.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    public Response createUser(CreateUserDto dto) {

        var userIdentity = (OidcJwtCallerPrincipal) identity.getPrincipal();
        var resourceAccess = (JsonValue) userIdentity.getClaim("resource_access");
        var isAdmin = resourceAccess.asJsonObject().containsKey("realm-management");

        if (!isAdmin) {
            throw new AktiePayException(EnumErrorCode.USUARIO_SEM_CREDENCIAIS);
        }

        ValidateUtil.validateNewUser(dto);

        userService.create(dto);

        return Response.status(Status.CREATED).build();
    }

    @PUT
    @APIResponse(responseCode = "200", description = "Atualiza e ativa os dados do usuário criado.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    public UserInfoDto updateUserInfo(UserInfoDto updateUserDto) {
        return userService.update(updateUserDto, identity);
    }

    @POST
    @Path("/restore-pass")
    @APIResponse(responseCode = "200", description = "Envia um e-mail com a senha do usuário para o e-mail do cadastrado.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    public Response sendPasswordToUserMail(@HeaderParam String email) {
        userService.sendEmailUserPassword(email);

        return Response.ok().build();
    }

}