package com.milk.pay.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

    public static final DateTimeFormatter DDMMYYYYHHMMSS = EnumDateFormat.DDMMYYYYHHMMSS.getFormat();

    public static final DateTimeFormatter DDMMYYYYHHMM = EnumDateFormat.DDMMYYYYHHMM.getFormat();

    public static final DateTimeFormatter DDMMYYYY = EnumDateFormat.DDMMYYYY.getFormat();

    public static final DateTimeFormatter YYYYMMDDHHMMSS = EnumDateFormat.YYYYMMDDHHMMSS.getFormat();

    public static final DateTimeFormatter YYYYMMDDTHHMMSS = EnumDateFormat.YYYYMMDDTHHMMSS.getFormat();

    public static final DateTimeFormatter ISO8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static LocalDateTime parseISO8601(String data) throws ParseException {
        return LocalDateTime.from(ISO8601.parse(data));
    }

    public static String formatISO8601(LocalDateTime data) {
        return ISO8601.format(data);
    }

    public static String formatYYYYMMDD(LocalDate date) {
        return date != null ? EnumDateFormat.YYYYMMDD.format(date) : "";
    }

    public static String formatYYYYMMDDHHMMSS(LocalDate date) {
        return date != null ? YYYYMMDDHHMMSS.format(date) : "";
    }

    public static String formatYYYYMMDDTHHMMSS(LocalDate date) {
        return date != null ? YYYYMMDDTHHMMSS.format(date) : "";
    }

    public static String formatDDMMYYYYHHMMSS(LocalDate date) {
        return date != null ? DDMMYYYYHHMMSS.format(date) : "";
    }

    public static String formatDDMMYYYYHHMM(LocalDate date) {
        return date != null ? DDMMYYYYHHMM.format(date) : "";
    }

    public static String formatDDMMYYYY(LocalDate date) {
        return date != null ? DDMMYYYY.format(date) : "";
    }

    public static String format(EnumDateFormat dataFormat, LocalDateTime date) {
        return date != null ? dataFormat.getFormat().format(date) : "";
    }

    public static int getDayOfMonth() {
        return today().get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth() {
        return today().get(Calendar.MONTH) + 1;
    }

    public static int getYear() {
        return today().get(Calendar.YEAR);
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
        return DDMMYYYY.format(LocalDate.now());
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

    public static Calendar localDateToCalendar(LocalDate data) {
        Calendar c1 = Calendar.getInstance(LOCALE_BRAZIL);
        c1.setTimeInMillis(data.toEpochDay());
        return c1;
    }

    public static Calendar localDateTimeToCalendar(LocalDateTime data) {
        Calendar c1 = Calendar.getInstance(LOCALE_BRAZIL);
        c1.setTime(Date.from(data.atZone(ZoneId.systemDefault()).toInstant()));
        return c1;
    }

    public static boolean isWeekend(Calendar c1) {
        return c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static boolean isWeekend(LocalDate date) {
        Calendar c1 = Calendar.getInstance(LOCALE_BRAZIL);
        c1.setTimeInMillis(date.toEpochDay());
        return isWeekend(c1);
    }

    public static boolean isDueDate(Date dueDate) {
        return dueDate != null ? (new Date()).after(dueDate) : false;
    }

    public static LocalDate DDMMYYYYToLocalDate(String value) {
        return  LocalDate.from(DDMMYYYY.parse(value));
    }

    public static LocalDateTime DDMMYYYYHHMMSSToLocalDateTime(String value) {
        return LocalDateTime.from(DDMMYYYYHHMMSS.parse(value));
    }

    public static Calendar DDMMYYYYToCalendar(String value) {
        return localDateToCalendar(DDMMYYYYToLocalDate(value));
    }

    public static Calendar DDMMYYYYHHMMSSToCalendar(String value) {
        return localDateTimeToCalendar(DDMMYYYYHHMMSSToLocalDateTime(value));
    }

    public static Date toDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZONE_ID).toInstant());
    }

    public static Date ConvertStringMillisToFormatedDate(String millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(millis));

        return calendar.getTime();
    }

    public static LocalDate parseLocalDate(EnumDateFormat dateFormat, String dateStr) throws ParseException {
        if (StringUtil.isNullOrEmpty(dateStr)) {
            return null;
        }

        return dateFormat.parse(dateStr);
    }

    public static LocalDateTime parseLocalDateTime(EnumDateFormat dateFormat, String dateStr) throws ParseException {
        if (StringUtil.isNullOrEmpty(dateStr)) {
            return null;
        }

        return LocalDateTime.from(dateFormat.parse(dateStr));
    }

    public static Integer numberOfDaysBetweenDates(LocalDateTime initialDate, LocalDateTime finalDate) {
        return (int) ChronoUnit.DAYS.between(initialDate, finalDate);
    }

}
