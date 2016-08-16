package com.vzs.yum.ls.tlog.util;

/**
 * Created by byao on 8/13/16.
 */
public class NumberUtils {
    public static Double pharseDouble(String string) {
        string = string.replace(",", "");
        return Double.parseDouble(string);
    }

    public static Long phraseLong(String string) {
        try {
            string = string.replace(",", "");
            return Long.parseLong(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
