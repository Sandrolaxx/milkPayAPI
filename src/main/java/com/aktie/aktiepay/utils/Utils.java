package com.aktie.aktiepay.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import com.aktie.aktiepay.dto.ErrorDto;
import com.aktie.aktiepay.entities.Title;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.identity.SecurityIdentity;

/**
 *
 * @author SRamos
 */
public class Utils {

    @SuppressWarnings("unchecked")
    public static <T> T nvl(Object arg0, T arg1) {
        if (arg0 instanceof String) {
            String a = (String) arg0;
            if (a.trim().isEmpty()) {
                return arg1;
            }
        }
        return (T) ((arg0 == null) ? arg1 : arg0);
    }

    public static BigDecimal getTotal(List<Title> listTitle, boolean isLiquidated) {
        return listTitle.stream()
                .filter(tit -> tit.isLiquidated() == isLiquidated)
                .map(Title::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Long countTotal(List<Title> listTitle, boolean isLiquidated) {
        return listTitle.stream()
                .filter(tit -> tit.isLiquidated() == isLiquidated)
                .count();
    }

    public static BigDecimal getTotalNextDays(List<Title> listTitle, Integer nextDays) {
        return listTitle.stream()
                .filter(tit -> !tit.isLiquidated()
                        && tit.getDueDate().isBefore(LocalDate.now().plusDays(nextDays)))
                .map(Title::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Long countTotalNextDays(List<Title> listTitle, Integer nextDays) {
        return listTitle.stream()
                .filter(tit -> !tit.isLiquidated()
                        && tit.getDueDate().isBefore(LocalDate.now().plusDays(nextDays)))
                .count();
    }

    public static AktiePayException handleException(WebApplicationException wae, EnumErrorCode defaultError) {
        final var resp = WebApplicationExceptionConverter.convertExceptionToObject(wae, ErrorDto.class);

        if (resp != null) {
            return new AktiePayException(
                    StringUtil.isNullOrEmpty(resp.getErrorCode()) ? resp.getCode() : resp.getErrorCode(),
                    StringUtil.isNullOrEmpty(resp.getMessage()) ? resp.getDescription() : resp.getMessage());
        } else {
            return new AktiePayException(defaultError);
        }
    }
    
    public static String resolveUserId(SecurityIdentity identity) {
        var tokenInfo = (OidcJwtCallerPrincipal) identity.getPrincipal();

        return (String) tokenInfo.getClaim("userId");
    }

}
