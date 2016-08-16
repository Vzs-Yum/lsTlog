package com.vzs.yum.ls.tlog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by byao on 8/7/16.
 */
public class DateUtils {
    private static SimpleDateFormat transactionTimeFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
    private static SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyyMMdd");
    public static Date formatTransactionTime(String transactionTime) {
        try {
            return transactionTimeFormater.parse(transactionTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatToDateOnly(Date date) {
        return dateOnlyFormat.format(date);
    }

    public static String phraseToday() {
        return simpleDateFormat.format(new Date());
    }

    public static boolean isSameDay(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
}
