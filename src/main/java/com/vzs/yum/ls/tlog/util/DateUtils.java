package com.vzs.yum.ls.tlog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by byao on 8/7/16.
 */
public class DateUtils {
    private static SimpleDateFormat transactionTimeFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
    public static Date formatTransactionTime(String transactionTime) {
        try {
            return transactionTimeFormater.parse(transactionTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String phraseToday() {
        return simpleDateFormat.format(new Date());
    }
}
