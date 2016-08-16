package com.vzs.yum.ls.tlog.util;

import java.util.List;

/**
 * Created by byao on 8/15/16.
 */
public class ListUtils {
    public static boolean isEmpty(List list) {
        if (list != null && list.size() != 0) {
            return false;
        }
        return true;
    }
}
