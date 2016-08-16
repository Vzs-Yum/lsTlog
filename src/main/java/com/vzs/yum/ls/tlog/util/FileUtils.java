package com.vzs.yum.ls.tlog.util;

import java.io.File;

/**
 * Created by byao on 8/15/16.
 */
public class FileUtils {
    public static void makeDirs(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
    }
}
