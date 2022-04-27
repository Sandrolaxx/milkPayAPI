package com.milk.pay.entities.enums;

import com.milk.pay.utils.Utils;

/**
 *
 * @author SRamos
 */
public enum EnumPaymentType implements IEnum {

    PIX("PIX", "PIX - Pagamentos Instantaneoes"),
    BANKSLIP("BOLETO", "Boleto Bancário");

    private EnumPaymentType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;

    private String value;

    @Override
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean containsInEnum(String key) {
        return false;
    }

    public static EnumPaymentType parseByKey(String key) {
        return (EnumPaymentType) Utils.parseByKey(EnumPaymentType.class, key);
    }

}
