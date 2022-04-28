package com.milk.pay.utils;

import javax.json.bind.adapter.JsonbAdapter;

import com.milk.pay.entities.enums.EnumPaymentType;

/**
 *
 * @author SRamos
 */
public class EnumPaymentTypeAdapter implements JsonbAdapter<EnumPaymentType, String> {

    @Override
    public String adaptToJson(EnumPaymentType enumPaymentType) {
        return enumPaymentType.getKey();
    }

    @Override
    public EnumPaymentType adaptFromJson(String srt) {
        return EnumPaymentType.parseByKey(srt);
    }
}