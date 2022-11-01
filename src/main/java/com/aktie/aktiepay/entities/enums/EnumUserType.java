package com.aktie.aktiepay.entities.enums;

import com.aktie.aktiepay.utils.EnumUtil;

/**
 *
 * @author SRamos
 */
public enum EnumUserType implements IEnum {

    ADMINISTRATIVE("Administrativo"),
    COMMON("Comum");

    private String value;

    private EnumUserType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    public static EnumUserType parseByKey(String key) {
        return (EnumUserType) EnumUtil.parseByKey(EnumUserType.class, key);
    }

}
