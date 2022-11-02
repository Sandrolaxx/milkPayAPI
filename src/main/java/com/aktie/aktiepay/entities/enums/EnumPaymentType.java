package com.aktie.aktiepay.entities.enums;

import com.aktie.aktiepay.utils.EnumUtil;

/**
 *
 * @author SRamos
 */
public enum EnumPaymentType implements IEnum {

    PIX("PIX - Pagamentos Instantâneos"),
    BOLETO("Boleto Bancário");

    private EnumPaymentType(String value) {
        this.value = value;
    }

    private String value;

    @Override
    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean containsInEnum(String key) {
        return false;
    }

    public static EnumPaymentType parseByKey(String key) {
        return (EnumPaymentType) EnumUtil.parseByKey(EnumPaymentType.class, key);
    }

}
