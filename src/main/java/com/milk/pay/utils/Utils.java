package com.milk.pay.utils;

import java.time.LocalDateTime;
import java.util.List;

import com.milk.pay.entities.Title;

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

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T parseByKey(Class<T> enumValue, String key) {
        try {
            if (key != null && !key.trim().isEmpty()) {
                for (var enumStatusPix : enumValue.getDeclaringClass().getEnumConstants()) {
                    if (enumStatusPix.getClass().getField("key").toString().equalsIgnoreCase(key)) {
                        return (T) enumStatusPix;
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    public static Double getTotal(List<Title> listTitle, boolean isLiquidated) {
        return listTitle.stream()
                .filter(tit -> tit.isLiquidated() == isLiquidated)
                .mapToDouble(Title::getAmount)
                .sum();
    }

    public static Long countTotal(List<Title> listTitle, boolean isLiquidated) {
        return listTitle.stream()
                .filter(tit -> tit.isLiquidated() == isLiquidated)
                .count();
    }

    public static Double getTotalNextDays(List<Title> listTitle, Integer nextDays) {
        return listTitle.stream()
                .filter(tit -> !tit.isLiquidated()
                        && tit.getDueDate().isBefore(LocalDateTime.now().plusDays(nextDays)))
                .mapToDouble(Title::getAmount)
                .sum();
    }

    public static Long countTotalNextDays(List<Title> listTitle, Integer nextDays) {
        return listTitle.stream()
                .filter(tit -> !tit.isLiquidated()
                        && tit.getDueDate().isBefore(LocalDateTime.now().plusDays(nextDays)))
                .count();
    }

}
