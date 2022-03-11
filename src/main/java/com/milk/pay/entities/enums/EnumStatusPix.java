package com.milk.pay.entities.enums;


/**
 *
 * @author SRamos
 */
public enum EnumStatusPix {

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
 
    public static Boolean containsProvider(String provider) {
        
        for (EnumStatusPix enumValue : EnumStatusPix.values()) {
            if (enumValue.getKey().equalsIgnoreCase(provider)) {
                return true;
            }
        }
        
        return false;
    }
}
