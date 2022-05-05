package com.milk.pay.entities.enums;

import com.milk.pay.utils.EnumUtil;

/**
 *
 * @author SRamos
 */
public enum EnumAccountType implements IEnum {

    SLRY("Conta Salário"),
    SVGS("Conta Poupança"),
    TRAN("Conta de Pagamentos"),
    CACC("Conta Corrente");

    private String value;

    private EnumAccountType(String value) {
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

    public static EnumAccountType parseByKey(String key) {
        return (EnumAccountType) EnumUtil.parseByKey(EnumAccountType.class, key);
    }

}
