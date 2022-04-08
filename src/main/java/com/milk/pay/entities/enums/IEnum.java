package com.milk.pay.entities.enums;

import com.milk.pay.utils.Utils;

public interface IEnum {

    public boolean containsInEnum(String key);

    public static <T extends Enum<T>>T parseByKey(Class<T> enumValue, String key) {
        return (T) Utils.parseByKey(enumValue, key);
    };
    
}
