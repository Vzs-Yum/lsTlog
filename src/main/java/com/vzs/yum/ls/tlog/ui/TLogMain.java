package com.vzs.yum.ls.tlog.ui;

import com.vzs.yum.ls.tlog.process.TLogMainProcess;
import com.vzs.yum.ls.tlog.process.TLogMainProcessContext;
import com.vzs.yum.ls.tlog.process.TLogProcess;
import com.vzs.yum.ls.tlog.report.TLogExcelReporterExecuter;
import com.vzs.yum.ls.tlog.report.TLogReportExecute;

/**
 * Created by byao on 8/7/16.
 */
public class TLogMain {
    public static void main (String ... args) {
        String folderPath = "/Users/byao/Ben/doc/yum/T-LOG";
        TLogMainProcessContext tLogMainProcessContext = TLogMainProcessContext.builder().tLogFolderPath(folderPath).build();
        TLogProcess tLogMainProcess = new TLogMainProcess(tLogMainProcessContext);
        tLogMainProcess.execute();

        TLogReportExecute reportExecute = new TLogExcelReporterExecuter(tLogMainProcessContext);
        reportExecute.execute();
    }
}
