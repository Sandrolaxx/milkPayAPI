package com.aktie.aktiepay.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import com.aktie.aktiepay.dto.title.ListTitleDto;
import com.aktie.aktiepay.dto.title.TitleCreateDto;
import com.aktie.aktiepay.dto.title.TotalizersDto;
import com.aktie.aktiepay.services.TitleService;
import com.aktie.aktiepay.services.UserService;
import com.aktie.aktiepay.utils.MilkPayExceptionResponseDto;
import com.aktie.aktiepay.utils.ValidateUtil;

import io.quarkus.security.identity.SecurityIdentity;

/**
 *
 * @author SRamos
 */
@Path("/milkpay-api/v1/title")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Title Management")
public class TitleController {

    @Inject
    TitleService titleService;

    @Inject
    UserService userService;

    @Inject
    SecurityIdentity identity;

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna os totalizadores.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @GET
    @Path("/totalizers")
    public TotalizersDto fetchTotalizers() {
        return titleService.fetchTotalizers(userService.resolveUserId(identity));
    }

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna os titulos do usuário.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @GET
    public ListTitleDto listByUser(@HeaderParam boolean liquidated, @QueryParam Integer pageIndex, @QueryParam Integer pageSize,
            @QueryParam String offset, @QueryParam String limit) {
        return titleService.findAll(userService.resolveUserId(identity), liquidated, pageIndex, pageSize, offset,
                limit);
    }
    
    @APIResponse(responseCode = "201", description = "Caso sucesso, retorna Status 201 - CREATED.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @POST
    public Response create(TitleCreateDto newTitle) {
        ValidateUtil.validateNewTitleDto(newTitle);

        titleService.persistTitle(newTitle);

        return Response.status(Status.CREATED).build();
    }

}
