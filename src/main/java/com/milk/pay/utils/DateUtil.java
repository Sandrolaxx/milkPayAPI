package com.milk.pay.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.milk.pay.entities.enums.EnumDateFormat;

/**
 *
 * @author SRamos
 */
public class DateUtil {

    public static final String TIMEZONE = "America/Sao_Paulo";

    public static final ZoneId ZONE_ID = ZoneId.of(TIMEZONE);

    public static final Locale LOCALE_BRAZIL = new Locale("pt", "BR");

    private static final Calendar CALENDAR = Calendar.getInstance(LOCALE_BRAZIL);

    public static final SimpleDateFormat DDMMYYYYHHMMSS = EnumDateFormat.DDMMYYYYHHMMSS.getFormat();

    public static final SimpleDateFormat DDMMYYYYHHMM = EnumDateFormat.DDMMYYYYHHMM.getFormat();

    public static final SimpleDateFormat DDMMYYYY = EnumDateFormat.DDMMYYYY.getFormat();

    public static final SimpleDateFormat YYYYMMDDHHMMSS = EnumDateFormat.YYYYMMDDHHMMSS.getFormat();

    public static final SimpleDateFormat YYYYMMDDTHHMMSS = EnumDateFormat.YYYYMMDDTHHMMSS.getFormat();

    public static final SimpleDateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static Date parseISO8601(String data) throws ParseException {
        return ISO8601.parse(data);
    }

    public static String formatISO8601(Date data) {
        return ISO8601.format(data);
    }

    public static String formatYYYYMMDD(Date date) {
        return date != null ? EnumDateFormat.YYYYMMDD.format(date) : "";
    }

    public static String formatYYYYMMDDHHMMSS(Date date) {
        return date != null ? YYYYMMDDHHMMSS.format(date) : "";
    }

    public static String formatYYYYMMDDTHHMMSS(Date date) {
        return date != null ? YYYYMMDDTHHMMSS.format(date) : "";
    }

    public static String formatDDMMYYYYHHMMSS(Date date) {
        return date != null ? DDMMYYYYHHMMSS.format(date) : "";
    }

    public static String formatDDMMYYYYHHMM(Date date) {
        return date != null ? DDMMYYYYHHMM.format(date) : "";
    }

    public static String formatDDMMYYYY(Date date) {
        return date != null ? DDMMYYYY.format(date) : "";
    }

    public static String format(EnumDateFormat dataFormat, Date date) {
        return date != null ? dataFormat.getFormat().format(date) : "";
    }

    public static int getDayOfMonth() {
        return today().get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfMonth(Date data) {
        CALENDAR.setTime(data);
        return CALENDAR.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth() {
        return today().get(Calendar.MONTH) + 1;
    }

    public static int getMonth(Date data) {
        CALENDAR.setTime(data);
        return CALENDAR.get(Calendar.MONTH) + 1;
    }

    public static int getYear() {
        return today().get(Calendar.YEAR);
    }

    public static int getYear(Date data) {
        CALENDAR.setTime(data);
        return CALENDAR.get(Calendar.YEAR);
    }

    public static boolean sameDay(Date d1, Date d2) {
        Calendar c1 = resetTime(d1);
        Calendar c2 = resetTime(d2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
                && (c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR));
    }

    public static boolean sameDayOfYear(Date d1, Date d2) {
        final Calendar c1 = resetTime(d1);
        c1.set(Calendar.YEAR, getYear());
        final Calendar c2 = resetTime(d2);
        c2.set(Calendar.YEAR, getYear());

        return c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    public static Calendar now() {
        return Calendar.getInstance(LOCALE_BRAZIL);
    }

    public static Calendar today() {
        return resetTime(Calendar.getInstance(LOCALE_BRAZIL));
    }

    public static Calendar tomorrow() {
        Calendar h = today();
        h.add(GregorianCalendar.DAY_OF_MONTH, 1);
        return h;
    }

    public static String getDateToday() {
        return DDMMYYYY.format(new Date(System.currentTimeMillis()));
    }

    public static String getDateTodayInit() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 00:00:00");
        Date date = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }

    public static String getDateTodayEnd() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 23:59:59");
        Date date = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }

    public static Calendar resetTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar resetTime(Date data) {
        Calendar c1 = Calendar.getInstance(LOCALE_BRAZIL);
        c1.setTime(data);
        return resetTime(c1);
    }

    public static Calendar dateToCalendar(Date data) {
        Calendar c1 = Calendar.getInstance(LOCALE_BRAZIL);
        c1.setTime(data);
        return c1;
    }

    public static Boolean isWeekend(Calendar c1) {
        return c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static Boolean isWeekend(Date date) {
        Calendar c1 = Calendar.getInstance(LOCALE_BRAZIL);
        c1.setTime(date);
        return isWeekend(c1);
    }

    public static boolean isDueDate(Date dueDate) {
        return dueDate != null ? (new Date()).after(dueDate) : false;
    }

    public static boolean equals(Date data1, Date data2, EnumDateFormat sdf) {
        return compareTo(data1, data2, sdf) == 0;
    }

    public static int compareTo(Date date1, Date date2, EnumDateFormat sdf) {
        try {
            if (date1 == null) {
                date1 = today().getTime();
            }

            if (date2 == null) {
                date2 = today().getTime();
            }

            String dataFormat1 = sdf.format(date1);
            String dataFormat2 = sdf.format(date2);

            date1 = sdf.parse(dataFormat1);
            date2 = sdf.parse(dataFormat2);

            return date1.compareTo(date2);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseIsoOffSetDate(String date) {
        TemporalAccessor parse = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZONE_ID).parse(date);
        LocalDateTime localDateTime = OffsetDateTime.from(parse).toLocalDateTime();
        return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
    }

    public static String formatIsoOffsetDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(date.toInstant(), ZONE_ID).truncatedTo(ChronoUnit.SECONDS)
                .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public static Date subtractDays(Date data, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, -dias);

        return calendar.getTime();
    }

    public static Date sumDays(Date data, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, dias);

        return calendar.getTime();
    }

    public static Date DDMMYYYYToDate(String value) {
        Date data = null;
        try {
            data = DDMMYYYY.parse(value);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        return data;
    }

    public static Date DDMMYYYYHHMMSSToDate(String value) {
        Date data = null;
        try {
            data = DDMMYYYYHHMMSS.parse(value);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        return data;
    }

    public static Calendar DDMMYYYYToCalendar(String value) {
        Date data = null;
        try {
            data = DDMMYYYY.parse(value);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        return dateToCalendar(data);
    }

    public static Calendar DDMMYYYYHHMMSSToCalendar(String value) {
        Date data = null;
        try {
            data = DDMMYYYYHHMMSS.parse(value);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        return dateToCalendar(data);
    }

    public static String convertLongHourToHour(Long tempo) {
        int seconds = (int) (tempo / 1000) % 60;
        int minutes = (int) ((tempo / (1000 * 60)) % 60);
        int hours = (int) ((tempo / (1000 * 60 * 60)) % 24);

        String hora = String.valueOf(hours);
        String minuto = String.valueOf(minutes);
        String segundo = String.valueOf(seconds);

        if (hours < 10) {
            if (hours != 0) {
                hora = "0" + String.valueOf(hours);
            } else {
                hora = "00";
            }
        }
        if (minutes < 10) {
            if (minutes != 0) {
                minuto = "0" + String.valueOf(minutes);
            } else {
                minuto = "00";
            }
        }
        if (seconds < 10) {
            if (seconds != 0) {
                segundo = "0" + String.valueOf(seconds);
            } else {
                segundo = "00";
            }
        }
        return (hora + ":" + minuto + ":" + segundo);
    }

    public static String formatStrToDateDDMMYYYY(String tipoDate, String value) {
        try {
            Date data = new SimpleDateFormat(tipoDate).parse(value.trim());

            return DateUtil.formatDDMMYYYY(data);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Date toDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZONE_ID).toInstant());
    }

    public static Date ConvertStringMillisToFormatedDate(String millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(millis));

        return calendar.getTime();
    }

    public static Date parse(EnumDateFormat dateFormat, String dateStr) throws ParseException {
        if (StringUtil.isNullOrEmpty(dateStr)) {
            return null;
        }

        DateFormat df = dateFormat.getFormat();
        df.setLenient(false);
        return df.parse(dateStr);
    }

    public static Integer numberOfDaysBetweenDates(Date initialDate, Date finalDate) {
        Calendar iDate = dateToCalendar(initialDate);
        Calendar fDate = dateToCalendar(finalDate);

        return numberOfDaysBetweenDates(iDate, fDate);
    }

    public static Integer numberOfDaysBetweenDates(Calendar initialDate, Calendar finalDate) {
        LocalDate iDate = LocalDate.of(initialDate.get(Calendar.YEAR), initialDate.get(Calendar.MONTH) + 1,
            initialDate.get(Calendar.DAY_OF_MONTH));
        LocalDate fDate = LocalDate.of(finalDate.get(Calendar.YEAR), finalDate.get(Calendar.MONTH) + 1,
            finalDate.get(Calendar.DAY_OF_MONTH));

        return (int) ChronoUnit.DAYS.between(iDate, fDate);
    }

}
