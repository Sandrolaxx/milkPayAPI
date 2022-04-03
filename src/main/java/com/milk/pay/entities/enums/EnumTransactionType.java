package com.milk.pay.entities.enums;

/**
 *
 * @author SRamos
 */
public enum EnumTransactionType {

    RECEIVEPIX("RECEIVEPIX"),    
    PIXREVERSAL("PIXREVERSAL"),//APROVAÇÃO DE RECEBIMENTO DE DEVOLUÇÃO    
    REVERTPIX("REVERTPIXL"),//DEVOLUÇÕES DE RECEBIMENTO    
    PAYMENT("PAYMENT");    
    
    private final String key;

    private EnumTransactionType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
 
    public static Boolean containsProvider(String provider) {
        
        for (EnumTransactionType enumValue : EnumTransactionType.values()) {
            if (enumValue.getKey().equalsIgnoreCase(provider)) {
                return true;
            }
        }
        
        return false;
    }
}
