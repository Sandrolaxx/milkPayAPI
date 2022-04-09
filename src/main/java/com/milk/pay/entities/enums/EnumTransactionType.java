package com.milk.pay.entities.enums;

import com.milk.pay.utils.Utils;

/**
 *
 * @author SRamos
 */
public enum EnumTransactionType implements IEnum  {

    RECEIVEPIX("RECEIVEPIX"),    
    PIXREVERSAL("PIXREVERSAL"),//APROVAÇÃO DE RECEBIMENTO DE DEVOLUÇÃO    
    REVERTPIX("REVERTPIXL"),//DEVOLUÇÕES DE RECEBIMENTO    
    PAYMENT("PAYMENT");    
    
    private final String key;

    private EnumTransactionType(String key) {
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

    public static EnumTransactionType parseByKey(String key) {
        return (EnumTransactionType) Utils.parseByKey(EnumTransactionType.class, key);
    }

}
