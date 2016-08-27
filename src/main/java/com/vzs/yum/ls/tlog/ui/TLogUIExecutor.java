package com.vzs.yum.ls.tlog.ui;

import com.vzs.yum.ls.tlog.process.TLogMainProcess;
import com.vzs.yum.ls.tlog.process.TLogMainProcessContext;
import com.vzs.yum.ls.tlog.process.TLogProcess;
import com.vzs.yum.ls.tlog.report.TLogExcelReporterExecuter;
import com.vzs.yum.ls.tlog.report.TLogReportExecute;

import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/16/16.
 */
public class TLogUIExecutor {
    public static void execute(String folderPath, List<Date> selectionDate) {
//        String folderPath = "/Users/byao/Ben/doc/yum/T-LOG";

        TLogMainProcessContext tLogMainProcessContext = TLogMainProcessContext.builder().tLogFolderPath(folderPath).selectionDate(selectionDate).build();
        TLogProcess tLogMainProcess = new TLogMainProcess(tLogMainProcessContext);
        tLogMainProcess.execute();

        tLogMainProcessContext.setDebug(true);
        TLogReportExecute reportExecute = new TLogExcelReporterExecuter(tLogMainProcessContext);
        reportExecute.execute();
    }
}
