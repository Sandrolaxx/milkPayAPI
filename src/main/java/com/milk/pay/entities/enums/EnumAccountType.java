package com.milk.pay.entities.enums;

import com.milk.pay.utils.Utils;

/**
 *
 * @author SRamos
 */
public enum EnumAccountType implements IEnum {

    SLRY("SLRY", "Conta Salário"),
    SVGS("SVGS", "Conta Poupança"),
    TRAN("TRAN", "Conta de Pagamentos"),
    CACC("CACC", "Conta Corrente");

    private String key;

    private String value;

    private EnumAccountType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    public static EnumAccountType parseByKey(String key) {
        return (EnumAccountType) Utils.parseByKey(EnumAccountType.class, key);
    }

}
