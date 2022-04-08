package com.milk.pay.entities.enums;

import com.milk.pay.utils.Utils;

/**
 *
 * @author SRamos
 */
public enum EnumAccountType implements IEnum {
    
    CACC("CACC", "Conta Salário");

    private EnumAccountType(String key, String value) {
        
    }

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T parseByKey(String key) {
        return (T) Utils.parseByKey(EnumAccountType.class, key);
    }

}
