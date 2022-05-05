package com.milk.pay.entities.enums;

import com.milk.pay.utils.EnumUtil;

/**
 *
 * @author SRamos
 */
public enum EnumTransactionType implements IEnum  {

    RECEIVEPIX("Pix Recebido"),    
    PIXREVERSAL("Pix Estornado"),   
    PAYMENT("Pix Pago");    
    
    private final String value;

    private EnumTransactionType(String value) {
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

    public static EnumTransactionType parseByKey(String key) {
        return (EnumTransactionType) EnumUtil.parseByKey(EnumTransactionType.class, key);
    }

}
