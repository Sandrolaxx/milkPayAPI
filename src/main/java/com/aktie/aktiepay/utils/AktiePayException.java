package com.aktie.aktiepay.utils;

import com.aktie.aktiepay.entities.enums.EnumErrorCode;

/**
 *
 * @author SRamos
 */
public class AktiePayException extends RuntimeException {

    private String errorCode = "-1";

    public AktiePayException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AktiePayException(EnumErrorCode error) {
        super(error.getErro());
        this.errorCode = error.getKey();
    }

    public AktiePayException(EnumErrorCode error, Object... args) {
        super(StringUtil.stringPatternFormat(error.getErro(), args));
        this.errorCode = error.getKey();
    }

    public String getErrorCode() {
        return errorCode;
    }

}
