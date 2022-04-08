package com.milk.pay.entities.enums;

import com.milk.pay.utils.Utils;

/**
 *
 * @author SRamos
 */
public enum EnumStatusPix implements IEnum {

    CANCELED("CANCELED"),
    PAID("PAID"),
    REVERSED("REVERSED"),
    ERROR_GENERATING("ERROR_GENERATING");

    private final String key;

    private EnumStatusPix(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T parseByKey(String key) {
        return (T) Utils.parseByKey(EnumStatusPix.class, key);
    }

}
