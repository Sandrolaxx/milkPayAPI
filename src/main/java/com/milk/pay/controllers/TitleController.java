package com.milk.pay.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.milk.pay.dto.title.TitleDto;
import com.milk.pay.services.TitleService;
import com.milk.pay.utils.MilkPayExceptionResponseDto;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 *
 * @author SRamos
 */
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
    public List<TitleDto> consulTitleListUser(@PathParam("userId") Integer userId) {
        return titleService.findAll(null, userId);        
    }

}
