package com.milk.pay.utils;

import java.time.LocalDateTime;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.milk.pay.entities.enums.EnumErrorCode;

import org.apache.http.HttpStatus;

/**
 *
 * @author SRamos
 */
@Provider
public class ErrorResponseExceptionMapper implements ExceptionMapper<MilkPayException> {

    @Override
    public Response toResponse(MilkPayException ex) {

        int httpStatus;
        var error = EnumErrorCode.parseByKey(ex.getErrorCode());

        var formattedDate = DateUtil.formatISO8601(LocalDateTime.now());
        MilkPayExceptionResponseDto exceptionResponse = new MilkPayExceptionResponseDto();

        exceptionResponse.setError(ex.getMessage());
        exceptionResponse.setErrorDate(formattedDate);

        if (error != null) {
            var errorPhrase = Status.fromStatusCode(error.getHttpStatus()).getReasonPhrase();
            httpStatus = error.getHttpStatus();

            exceptionResponse.setErrorCode(error.getKey());
            exceptionResponse.setHttpCodeMessage(errorPhrase);
        } else {
            exceptionResponse.setErrorCode(ex.getErrorCode());
            httpStatus = HttpStatus.SC_BAD_REQUEST;
        }

        return Response.status(httpStatus).entity(exceptionResponse).build();
    }

}
