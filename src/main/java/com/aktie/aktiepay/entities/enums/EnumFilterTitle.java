package com.aktie.aktiepay.entities.enums;

import com.aktie.aktiepay.utils.EnumUtil;

/**
 *
 * @author SRamos
 */
public enum EnumFilterTitle implements IEnum {

    ID("id"),
    AMOUNT("amount"),
    DUE_DATE("dueDate"),
    INCLUSION_DATE("inclusionDate"),
    NF_NUMBER("nfNumber"),
    BARCODE("barcode"),
    DIGITABLE("digitable"),
    PAYMENT_TYPE("paymentType");

    private EnumFilterTitle(String value) {
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

    public static EnumFilterTitle parseByKey(String key) {
        return (EnumFilterTitle) EnumUtil.parseByKey(EnumFilterTitle.class, key);
    }

}
