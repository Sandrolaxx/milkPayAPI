package com.milk.pay.controllers;

import javax.inject.Inject;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.milk.pay.dto.user.CreateUserDto;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.services.UserService;
import com.milk.pay.utils.MilkPayException;
import com.milk.pay.utils.MilkPayExceptionResponseDto;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

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

    @POST
    @APIResponse(responseCode = "201", description = "Caso seja cadastrado com sucesso.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    public Response createUser(CreateUserDto dto) {

        var userIdentity = (OidcJwtCallerPrincipal) identity.getPrincipal();
        var resourceAccess = (JsonValue) userIdentity.getClaim("resource_access");
        var isAdmin = resourceAccess.asJsonObject().containsKey("realm-management");

        if (!isAdmin) {
            throw new MilkPayException(EnumErrorCode.USUARIO_SEM_CREDENCIAIS);
        }

        userService.persistUser(dto);
        
        return Response.status(Status.CREATED).build();
    }

}