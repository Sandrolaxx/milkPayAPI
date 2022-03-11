package com.milk.pay.entities.enums;


/**
 *
 * @author SRamos
 */
public enum EnumInitiationType {

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

    public static EnumInitiationType strToEnum(String key) {
        for (EnumInitiationType enumValue : EnumInitiationType.values()) {
            if (enumValue.getKey().equalsIgnoreCase(key)) {
                return enumValue;
            }
        }
        return null;
    }

}
