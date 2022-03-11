package com.milk.pay.utils;

import com.milk.pay.entities.enums.EnumErrorCode;

/**
 *
 * @author SRamos
 */
public class MilkPayException extends RuntimeException {

    private String errorCode = "-1";

    public MilkPayException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MilkPayException(EnumErrorCode error) {
        super(error.getErro());
        this.errorCode = error.getKey();
    }

    public MilkPayException(EnumErrorCode error, Object... args) {
        super(StringUtil.stringPatternFormat(error.getErro(), args));
        this.errorCode = error.getKey();
    }

    public String getErrorCode() {
        return errorCode;
    }

}
