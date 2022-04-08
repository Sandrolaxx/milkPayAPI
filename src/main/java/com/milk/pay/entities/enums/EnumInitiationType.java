package com.milk.pay.entities.enums;

import com.milk.pay.utils.Utils;

/**
 *
 * @author SRamos
 */
public enum EnumInitiationType implements IEnum {

    MANUAL("MANUAL"),    
    DICT("DICT"),    
    PAYMENT_INITIATOR("PAYMENT_INITIATOR"),    
    STATIC_QRCODE("STATIC_QRCODE"),    
    DYNAMIC_QRCODE("DYNAMIC_QRCODE");    
    
    private final String key;

    private EnumInitiationType(String key) {
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
        return (T) Utils.parseByKey(EnumInitiationType.class, key);
    }

}
