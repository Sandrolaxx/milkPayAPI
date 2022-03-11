package com.milk.pay.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.milk.pay.title.dto.TitleDto;
import com.milk.pay.title.service.TitleService;
import com.milk.pay.utils.MilkPayExceptionResponseDto;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/milkPay/v1/title")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Title Management")
public class TitleController {

    @Inject
    TitleService titleService;

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna Chave Pix consultada.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @GET
    @Path("/{userId}")
    public List<TitleDto> consulTitleListUser(@PathParam("userId") Integer userId) {
        return titleService.findAll(null, userId);        
    }

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna Chave Pix consultada.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @GET
    @Path("/company/{companyId}")
    public List<TitleDto> consulTitleList(@HeaderParam("userId") Integer companyId) {
        return titleService.findAll(companyId, null);        
    }

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna comprovante de pagamento")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @POST
    public Response payment(TitleDto dto) {
        titleService.persistTitle(dto);     

        return Response.ok().build();
    }

}
