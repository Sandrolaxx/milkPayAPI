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
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import com.aktie.aktiepay.dto.title.ListTitleDto;
import com.aktie.aktiepay.dto.title.TitleCreateDto;
import com.aktie.aktiepay.dto.title.TotalizersDto;
import com.aktie.aktiepay.entities.enums.EnumFilterTitle;
import com.aktie.aktiepay.services.TitleService;
import com.aktie.aktiepay.utils.AktiePayExceptionResponseDto;
import com.aktie.aktiepay.utils.Utils;
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
    SecurityIdentity identity;

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna os totalizadores.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    @GET
    @Path("/totalizers")
    public TotalizersDto fetchTotalizers() {
        return titleService.fetchTotalizers(Utils.resolveUserId(identity));
    }

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna os titulos do usuário.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    @GET
    public ListTitleDto listByUser(@QueryParam Boolean liquidated, @QueryParam Integer pageIndex,
            @QueryParam Integer pageSize, @QueryParam String offset, @QueryParam String limit,
            @QueryParam EnumFilterTitle filterBy, @QueryParam String filterValue, @QueryParam String filterValueAux) {
        return titleService.findAll(Utils.resolveUserId(identity), liquidated, pageIndex, pageSize,
                offset, limit, filterBy, filterValue, filterValueAux);
    }

    @APIResponse(responseCode = "201", description = "Caso sucesso, retorna Status 201 - CREATED.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    @POST
    public Response create(TitleCreateDto newTitle) {
        ValidateUtil.validateNewTitleDto(newTitle);

        titleService.persistTitle(newTitle);

        return Response.status(Status.CREATED).build();
    }

}
