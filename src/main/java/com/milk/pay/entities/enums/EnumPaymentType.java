package com.milk.pay.entities.enums;

/**
 *
 * @author SRamos
 */
public enum EnumPaymentType implements IEnum {

    PIX("PIX", "PIX - Pagamentos Instantaneoes"),
    BANKSLIP("BANKSLIP", "Boleto Bancário");

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

}
