package com.milk.pay.utils;

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

}
