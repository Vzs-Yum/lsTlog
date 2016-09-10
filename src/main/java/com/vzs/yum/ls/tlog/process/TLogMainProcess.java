package com.vzs.yum.ls.tlog.process;

import com.vzs.yum.ls.tlog.exception.TLogIllegalArgsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * Created by byao on 8/7/16.
 */
@Slf4j
@AllArgsConstructor
public class TLogMainProcess implements TLogProcess{
    private TLogMainProcessContext tLogMainProcessContext;

    public void execute() {
        File tLogFileFolder = new File(tLogMainProcessContext.getTLogFolderPath());
        if (!tLogFileFolder.isDirectory()) {
            throw new TLogIllegalArgsException("please choose a folder");
        }

        File[] tlogs = tLogFileFolder.listFiles();
        for (File tlog : tlogs) {
            String fileName = tlog.getName();
            if (tlog.isDirectory() || !fileName.endsWith(".txt")) {
                continue;
            }
            log.info("reading file >>> " + fileName);
            TLogLogProcess tLogLogProcess = new TLogLogProcess(tlog, tLogMainProcessContext.getSelectionDate(),
                    tLogMainProcessContext.getStartTimeDate(), tLogMainProcessContext.getEndTimeDate());
            tLogLogProcess.execute();
        }
    }
}
