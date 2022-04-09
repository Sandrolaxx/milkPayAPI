package com.milk.pay.entities.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author SRamos
 */
public enum EnumDateFormat implements IEnum {

    DDMM(new SimpleDateFormat("dd/MM")),
    DDMMYY(new SimpleDateFormat("dd/MM/yy")),
    DDMMYYYY(new SimpleDateFormat("dd/MM/yyyy")),
    DDMMYYHHMM(new SimpleDateFormat("dd/MM/yy HH:mm")),
    DDMMYYYYHHMM(new SimpleDateFormat("dd/MM/yyyy HH:mm")),
    DDMMYYYYHHMMSS(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")),
    YYYYMMDD(new SimpleDateFormat("yyyy-MM-dd")),
    YYYYMMDDHHMMSS(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),
    YYYYMMDDTHHMMSS(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")),
    MMYY(new SimpleDateFormat("MM/yy")),
    MMYYYY(new SimpleDateFormat("MM/yyyy")),
    EXTENSO(new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy")),
    FULLEXTENSO(new SimpleDateFormat("EEEE',' dd 'de' MMMMM 'de' yyyy 'as' HH:mm:ss")),
    EXTENSODIAMES(new SimpleDateFormat("dd 'de' MMMMM")),
    MMMDYYYYHHMMSSAAAEN_US(new SimpleDateFormat("MMM d',' yyyy hh:mm:ss a", Locale.ENGLISH)),
    MMMDDYYYYHHMMSSAAAEN_US(new SimpleDateFormat("MMM dd',' yyyy hh:mm:ss a", Locale.ENGLISH)),
    HHMM(new SimpleDateFormat("HH:mm"));

    private final SimpleDateFormat dateFormat;

    private EnumDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public SimpleDateFormat getFormat() {
        return dateFormat;
    }

    public final String format(Date date) {
        return dateFormat.format(date);
    }

    public Date parse(String source) throws ParseException {
        return dateFormat.parse(source);
    }

    public String toPattern() {
        return dateFormat.toPattern();
    }

    @Override
    public boolean containsInEnum(String key) {
        return false;
    }

    @Override
    public String getKey() {
        return null;
    }

}
