package com.milk.pay.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import org.apache.commons.jcs.utils.zip.CompressionUtil;
import org.jboss.resteasy.client.exception.WebApplicationExceptionWrapper;

/**
 *
 * @author SRamos
 */
public class WebApplicationExceptionConverter {

    public static <T> T convertExceptionToObject(WebApplicationException exception, Class<T> targetClass) {
        return convertExceptionToObject(exception, targetClass, false);
    }

    public static <T> T convertExceptionToObject(WebApplicationException exception, Class<T> targetClass, boolean isCollectionTypeReturn) {
        final WebApplicationExceptionWrapper wre = (WebApplicationExceptionWrapper) exception;

        if (wre != null) {

            final WebApplicationException wae = wre.unwrap();

            if (wae != null
                    && wae.getResponse() != null) {

                final Object response = wae.getResponse().getEntity();

                if (response != null
                        && response instanceof ByteArrayInputStream) {

                    try {
                        final ByteArrayInputStream bais = (ByteArrayInputStream) response;
                        int n = bais.available();
                        byte[] bytes = new byte[n];
                        bais.read(bytes, 0, n);

                        String completeError;

                        try {
                            completeError = new String(CompressionUtil.decompressGzipByteArray(bytes), StandardCharsets.UTF_8);
                        } catch (Exception e) {
                            completeError = new String(bytes, StandardCharsets.UTF_8);
                        }

                        if (!completeError.isEmpty()) {

                            if (targetClass.isAssignableFrom(String.class)) {
                                return (T) completeError;
                            }

                            final Gson gson = new Gson();

                            if (isCollectionTypeReturn) {
                                final Type listType = TypeToken.getParameterized(List.class, targetClass).getType();

                                final List<T> list = gson.fromJson(completeError, listType);

                                if (!list.isEmpty()) {
                                    return (T) list.get(0);
                                }
                                throw new MilkPayException("Erro na comunicação com o provedor de serviço.", "-999");
                            } else {
                                return gson.fromJson(completeError, targetClass);
                            }
                        }
                    } catch (Exception e) {
                        throw new MilkPayException("Erro na comunicação com o provedor de serviço.", "-999");
                    }
                }
            }
        }

        return null;
    }

}
