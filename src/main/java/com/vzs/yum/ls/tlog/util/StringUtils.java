package com.vzs.yum.ls.tlog.util;

/**
 * Created by byao on 8/13/16.
 */
public class StringUtils {
    public static String[] splictStringBySpace(String string) {
        return string.split("\\s+");
    }

    public static boolean isEmpty (String string) {
        return string == null || string.trim().length() == 0;
    }

    public static boolean isNotEmpty (String string) {
        return !isEmpty(string);
    }
}
