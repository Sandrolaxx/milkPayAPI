package com.aktie.aktiepay.utils;

import java.time.LocalDateTime;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpStatus;

import com.aktie.aktiepay.entities.enums.EnumErrorCode;

/**
 *
 * @author SRamos
 */
@Provider
public class ErrorResponseExceptionMapper implements ExceptionMapper<AktiePayException> {

    @Override
    public Response toResponse(AktiePayException ex) {

        int httpStatus;
        var error = EnumErrorCode.parseByKey(ex.getErrorCode());

        var formattedDate = DateUtil.formatDDMMYYYYHHMMSS(LocalDateTime.now());
        var exceptionResponse = new AktiePayExceptionResponseDto();

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
