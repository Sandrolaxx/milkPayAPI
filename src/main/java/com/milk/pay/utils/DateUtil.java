package com.milk.pay.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.milk.pay.entities.enums.EnumDateFormat;

import java.text.DateFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

/**
 *
 * @author ocean
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

    public static int getDiaDoMes() {
        return hoje().get(Calendar.DAY_OF_MONTH);
    }

    public static int getDiaDoMes(Date data) {
        CALENDAR.setTime(data);
        return CALENDAR.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMes() {
        return hoje().get(Calendar.MONTH) + 1;
    }

    public static int getMes(Date data) {
        CALENDAR.setTime(data);
        return CALENDAR.get(Calendar.MONTH) + 1;
    }

    public static int getAno() {
        return hoje().get(Calendar.YEAR);
    }

    public static int getAno(Date data) {
        CALENDAR.setTime(data);
        return CALENDAR.get(Calendar.YEAR);
    }

    public static boolean mesmoDia(Date d1, Date d2) {
        Calendar c1 = zerarHora(d1);
        Calendar c2 = zerarHora(d2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
                && (c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR));
    }

    public static boolean mesmoDiaDoAno(Date d1, Date d2) {
        final Calendar c1 = zerarHora(d1);
        c1.set(Calendar.YEAR, getAno());
        final Calendar c2 = zerarHora(d2);
        c2.set(Calendar.YEAR, getAno());

        return c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    public static Calendar agora() {
        return Calendar.getInstance(LOCALE_BRAZIL);
    }

    public static Calendar hoje() {
        return zerarHora(Calendar.getInstance(LOCALE_BRAZIL));
    }

    public static Calendar amanha() {
        Calendar h = hoje();
        h.add(GregorianCalendar.DAY_OF_MONTH, 1);
        return h;
    }

    public static String getDateToday() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);

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

    public static Calendar zerarHora(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar zerarHora(Date data) {
        Calendar c1 = Calendar.getInstance(LOCALE_BRAZIL);
        c1.setTime(data);
        return zerarHora(c1);
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

    public static boolean isVencido(Date dataVencimento) {
        return dataVencimento != null ? (new Date()).after(dataVencimento) : false;
    }

    public static boolean equals(Date data1, Date data2, EnumDateFormat sdf) {
        return compareTo(data1, data2, sdf) == 0;
    }

    public static int compareTo(Date date1, Date date2, EnumDateFormat sdf) {
        try {
            if (date1 == null) {
                date1 = hoje().getTime();
            }

            if (date2 == null) {
                date2 = hoje().getTime();
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

    public static Date subtrairDias(Date data, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, -dias);

        return calendar.getTime();
    }

    public static Date somarDias(Date data, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, dias);

        return calendar.getTime();
    }

    public static Integer quantidadeMesEntreDatas(Calendar dataInicial, Calendar dataFinal) {
        int difMes = dataFinal.get(Calendar.MONTH) - dataInicial.get(Calendar.MONTH);
        int difAno = ((dataFinal.get(Calendar.YEAR) - dataInicial.get(Calendar.YEAR)) * 12);
        int total = difAno + difMes;
        return total;
    }

    public static Integer quantidadeDiasEntreDatas(Date dataInicial, Date dataFinal) {
        Calendar _dataInicio = dateToCalendar(dataInicial);
        Calendar _dataFinal = dateToCalendar(dataFinal);

        return quantidadeDiasEntreDatas(_dataInicio, _dataFinal);
    }

    public static Integer quantidadeDiasEntreDatas(Calendar dataInicial, Calendar dataFinal) {
        LocalDate dInicial = LocalDate.of(dataInicial.get(Calendar.YEAR), dataInicial.get(Calendar.MONTH) + 1,
                dataInicial.get(Calendar.DAY_OF_MONTH));
        LocalDate dFinal = LocalDate.of(dataFinal.get(Calendar.YEAR), dataFinal.get(Calendar.MONTH) + 1,
                dataFinal.get(Calendar.DAY_OF_MONTH));

        return (int) ChronoUnit.DAYS.between(dInicial, dFinal);
    }

    public static boolean compararDiaMesAnoData1MenorDiaMesAnoData2(Calendar data1, Calendar data2) {
        return data1.get(Calendar.YEAR) < data2.get(Calendar.YEAR)
                || (data1.get(Calendar.MONTH) < data2.get(Calendar.MONTH)
                && data1.get(Calendar.YEAR) <= data2.get(Calendar.YEAR))
                || (data1.get(Calendar.DAY_OF_MONTH) < data2.get(Calendar.DAY_OF_MONTH)
                && data1.get(Calendar.MONTH) <= data2.get(Calendar.MONTH)
                && data1.get(Calendar.YEAR) <= data2.get(Calendar.YEAR));
    }

    public static boolean compararDiaMesAnoData1MaiorDiaMesAnoData2(Calendar data1, Calendar data2) {
        return data1.get(Calendar.YEAR) > data2.get(Calendar.YEAR)
                || (data1.get(Calendar.MONTH) > data2.get(Calendar.MONTH)
                && data1.get(Calendar.YEAR) >= data2.get(Calendar.YEAR))
                || (data1.get(Calendar.DAY_OF_MONTH) > data2.get(Calendar.DAY_OF_MONTH)
                && data1.get(Calendar.MONTH) >= data2.get(Calendar.MONTH)
                && data1.get(Calendar.YEAR) >= data2.get(Calendar.YEAR));
    }

    public static boolean compararMesAnoData1MaiorMesAnoData2(Calendar data1, Calendar data2) {
        return data1.get(Calendar.YEAR) > data2.get(Calendar.YEAR)
                || (data1.get(Calendar.MONTH) > data2.get(Calendar.MONTH)
                && data1.get(Calendar.YEAR) >= data2.get(Calendar.YEAR));
    }

    public static boolean compararMesAnoData1MenorMesAnoData2(Calendar data1, Calendar data2) {
        return data1.get(Calendar.YEAR) < data2.get(Calendar.YEAR)
                || (data1.get(Calendar.MONTH) < data2.get(Calendar.MONTH)
                && data1.get(Calendar.YEAR) <= data2.get(Calendar.YEAR));
    }

    public static boolean compararMesAnoData1IgualMesAnoData2(Calendar data1, Calendar data2) {
        return data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
                && data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
    }

    /**
     *
     * @param tempo ex: '1.50 => 90minutes'
     * @return
     */
    public static Date converterDoubleToHora(Double d) {
        Date hora = null;
        if (d == null) {
            return hora;
        }
        Double minutes = (d * 60);
        Calendar calendario = hoje();
        calendario.add(Calendar.MINUTE, minutes.intValue());
        hora = calendario.getTime();
        return hora;
    }

    public static Date DDMMYYYYToDate(String value) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formatter.parse(value);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        return data;
    }

    public static Calendar DDMMYYYYToCalendar(String value) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formatter.parse(value);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        return dateToCalendar(data);
    }

    public static Calendar DDMMYYYYHHMMSSToCalendar(String value) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = null;
        try {
            data = formatter.parse(value);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        return dateToCalendar(data);
    }

    public static int convertHourToSeconds(String horaCompleta) {
        int resultSegundos = 0;
        int posicao = horaCompleta.indexOf(":") - 2;

        String h = horaCompleta.substring(posicao, posicao + 2);
        String m = horaCompleta.substring(posicao + 3, posicao + 5);
        String s = horaCompleta.substring(posicao + 6, posicao + 8);

        int hora = Integer.parseInt(h) * 3600;
        int minutos = Integer.parseInt(m) * 60;
        int segundos = Integer.parseInt(s);

        resultSegundos = hora + minutos + segundos;

        return resultSegundos;
    }

    public static Date adicionarOuSubtrairDias(Date data, Integer qntDias, boolean adicionar) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        if (adicionar) {
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + qntDias);
        } else {
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - qntDias);
        }
        return c.getTime();
    }

    public static String converterSegundosEmHora(Integer seg) {
        int horas = seg / 3600;
        int minutos = (seg - (horas * 3600)) / 60;
        int segundos = seg - (horas * 3600) - (minutos * 60);

        String hora = String.valueOf(horas);
        String minuto = String.valueOf(minutos);
        String segundo = String.valueOf(segundos);

        if (horas < 10) {
            if (horas != 0) {
                hora = "0" + String.valueOf(horas);
            } else {
                hora = "00";
            }
        }
        if (minutos < 10) {
            if (minutos != 0) {
                minuto = "0" + String.valueOf(minutos);
            } else {
                minuto = "00";
            }
        }
        if (segundos < 10) {
            if (segundos != 0) {
                segundo = "0" + String.valueOf(segundos);
            } else {
                segundo = "00";
            }
        }

        return (hora + ":" + minuto + ":" + segundo);
    }

    public static String converterMinutosEmHora(Integer minutes) {
        int horas = minutes / 60;
        int minutos = (minutes - (horas * 60));

        String hora = String.valueOf(horas);
        String minuto = String.valueOf(minutos);

        if (horas < 10) {
            if (horas != 0) {
                hora = "0" + String.valueOf(horas);
            } else {
                hora = "00";
            }
        }
        if (minutos < 10) {
            if (minutos != 0) {
                minuto = "0" + String.valueOf(minutos);
            } else {
                minuto = "00";
            }
        }

        return (hora + ":" + minuto + ":00");
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

    public static String getDiaPorExtenso(Date date) {
        String[] semanaExtenso = {"domingo", "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira",
            "sexta-feira", "sabado"};
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dia = c.get(7);
        return semanaExtenso[dia - 1];
    }

    public static Date primeiroDiaDoMes(Date date) {
        Calendar c = zerarHora(date);
        c.set(Calendar.DAY_OF_MONTH, 1);

        return c.getTime();
    }

    public static Date ultimoDiaDoMes(Date date) {
        Calendar c = zerarHora(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return c.getTime();
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

}
