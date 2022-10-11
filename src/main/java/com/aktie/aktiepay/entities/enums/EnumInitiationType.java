package com.aktie.aktiepay.entities.enums;

import com.aktie.aktiepay.utils.EnumUtil;

/**
 *
 * @author SRamos
 */
public enum EnumInitiationType implements IEnum {

    MANUAL("Manual"),    
    DICT("Diretório de Identificadores de Contas Transacionais"),    
    PAYMENT_INITIATOR("Iniciação de Pagamento"),    
    STATIC_QRCODE("QR Code Estático"),    
    DYNAMIC_QRCODE("QR Code Dinâmico");    
    
    private final String value;

    private EnumInitiationType(String value) {
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

    public static EnumInitiationType parseByKey(String key) {
        return (EnumInitiationType) EnumUtil.parseByKey(EnumInitiationType.class, key);
    }

}
