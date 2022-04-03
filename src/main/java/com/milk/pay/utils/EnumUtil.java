package com.milk.pay.utils;

/**
 *
 * @author SRamos
 */
public class EnumUtil {

    public static boolean isEquals(Enum one, Enum another) {
        if (one == null && another == null) {
            return true;
        } else if (one != null && another != null) {
            return one.name().equals(another.name());
        } else {
            return false;
        }
    }

    public static boolean contains(Enum one, Enum... others) {
        if (others != null) {
            for (Enum en : others) {
                if (isEquals(one, en)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

}
