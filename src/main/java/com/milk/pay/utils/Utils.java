package com.milk.pay.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.milk.pay.entities.Title;
import com.milk.pay.entities.enums.IEnum;

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

    public static <T extends IEnum> T parseByKey(Class<T> enumValue, String key) {
        try {
            if (key != null && !key.trim().isEmpty()) {
                for (var value : enumValue.getEnumConstants()) {
                    if (value.getKey().equalsIgnoreCase(key)) {
                        return value;
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }

        return null;
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
                        && tit.getDueDate().isBefore(LocalDateTime.now().plusDays(nextDays)))
                .map(Title::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Long countTotalNextDays(List<Title> listTitle, Integer nextDays) {
        return listTitle.stream()
                .filter(tit -> !tit.isLiquidated()
                        && tit.getDueDate().isBefore(LocalDateTime.now().plusDays(nextDays)))
                .count();
    }

}
