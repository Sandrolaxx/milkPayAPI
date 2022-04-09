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

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    public static EnumInitiationType parseByKey(String key) {
        return (EnumInitiationType) Utils.parseByKey(EnumInitiationType.class, key);
    }

}
