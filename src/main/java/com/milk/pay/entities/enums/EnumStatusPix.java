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

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    public static EnumStatusPix parseByKey(String key) {
        return (EnumStatusPix) Utils.parseByKey(EnumStatusPix.class, key);
    }

}
