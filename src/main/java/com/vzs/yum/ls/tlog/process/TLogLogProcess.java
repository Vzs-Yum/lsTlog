package com.vzs.yum.ls.tlog.process;

import com.vzs.yum.ls.tlog.process.state.TLogFileStartState;
import com.vzs.yum.ls.tlog.process.state.TLogStateContext;
import lombok.AllArgsConstructor;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/7/16.
 */
@AllArgsConstructor
public class TLogLogProcess implements TLogProcess{
    private File tLog;
    private List<Date> selectionDate;
    public void execute() {
        if (tLog == null) {
            return;
        }
        BufferedReader tLogBufferedReader = null;
        try {
            tLogBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(tLog), "GB2312"));
            TLogStateContext tLogStateContext = new TLogStateContext();
            tLogStateContext.setFileName(tLog.getName());
            tLogStateContext.setTLogState(new TLogFileStartState());
            tLogStateContext.setSelectionDate(selectionDate);
            String tlogLine;
            while ((tlogLine = tLogBufferedReader.readLine()) != null) {
                tLogStateContext.request(tlogLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                tLogBufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
