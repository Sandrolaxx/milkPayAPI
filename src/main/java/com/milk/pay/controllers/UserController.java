package com.milk.pay.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.milk.pay.user.dto.CreateUserDto;
import com.milk.pay.user.dto.ListUserDto;
import com.milk.pay.user.dto.MilkPayExceptionResponseDto;
import com.milk.pay.user.dto.UpdateUserDto;
import com.milk.pay.user.service.UserService;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.security.identity.SecurityIdentity;

@Tag(name = "User")
@Path("/milkPay/v1/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @Inject
    SecurityIdentity identity;

    @GET()
    @Path("/all")
    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna a lista de usuários")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    public List<ListUserDto> listAll() {
        return userService.findAll();
    }

    @GET
    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna as informações usuário")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    public ListUserDto userInfo(@HeaderParam("userId") Integer userId) {
        return userService.findInfoById(userId);
    }

    @POST
    @APIResponse(responseCode = "201", description = "Caso seja cadastrado com sucesso.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    public Response createUser(CreateUserDto dto) {
        userService.persistUser(dto);

        return Response.status(Status.CREATED).build();
    }

    @PUT
    @APIResponse(responseCode = "204", description = "Caso sucesso, não retorna conteúdo.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    public void updateUser(@HeaderParam("userId") Integer userId, UpdateUserDto dto) {
        userService.updateUser(userId, dto);
    }

    @DELETE
    @APIResponse(responseCode = "204", description = "Caso sucesso, não retorna conteúdo.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    public void deleteUser(@HeaderParam("userId") Integer userId) {
        userService.deleteUser(userId, identity);
    }

}