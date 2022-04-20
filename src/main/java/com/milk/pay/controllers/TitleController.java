package com.milk.pay.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.milk.pay.dto.title.TitleCreateDto;
import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.dto.title.TotalizersDto;
import com.milk.pay.services.TitleService;
import com.milk.pay.services.UserService;
import com.milk.pay.utils.MilkPayExceptionResponseDto;
import com.milk.pay.utils.ValidateUtil;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

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
    public List<TitleDto> listByUser(@HeaderParam boolean liquidated, @QueryParam String offset, 
        @QueryParam String limit) {
        return titleService.findAll(userService.resolveUserId(identity), liquidated, offset, limit);        
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
