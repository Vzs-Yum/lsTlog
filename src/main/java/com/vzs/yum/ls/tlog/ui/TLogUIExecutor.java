package com.vzs.yum.ls.tlog.ui;

import com.vzs.yum.ls.tlog.process.TLogMainProcess;
import com.vzs.yum.ls.tlog.process.TLogMainProcessContext;
import com.vzs.yum.ls.tlog.process.TLogProcess;
import com.vzs.yum.ls.tlog.report.TLogExcelReporterExecuter;
import com.vzs.yum.ls.tlog.report.TLogExcelSummaryReporterExecuter;
import com.vzs.yum.ls.tlog.report.TLogMenuReporterReader;
import com.vzs.yum.ls.tlog.report.TLogReportExecute;
import com.vzs.yum.ls.tlog.report.vo.menu.TLogMenuReporterVO;
import com.vzs.yum.ls.tlog.util.DateUtils;
import com.vzs.yum.ls.tlog.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/16/16.
 */
public class TLogUIExecutor {
    public static void execute(String folderPath, List<Date> selectionDate, Date startTimeDate, Date endTimeDate) {
//        String folderPath = "/Users/byao/Ben/doc/yum/T-LOG";

        TLogMainProcessContext tLogMainProcessContext = TLogMainProcessContext.builder().
                tLogFolderPath(folderPath).selectionDate(selectionDate).startTimeDate(startTimeDate).endTimeDate(endTimeDate).build();
        tLogMainProcessContext.createReporterFolderPath();

        TLogProcess tLogMainProcess = new TLogMainProcess(tLogMainProcessContext);
        tLogMainProcess.execute();

        TLogMenuReporterReader tLogMenuReporterReader = new TLogMenuReporterReader(tLogMainProcessContext.getTLogFolderPath());
        tLogMenuReporterReader.execute();
        TLogMenuReporterVO tLogMenuReporterVO = tLogMenuReporterReader.getTLogMenuReporterVO();

        tLogMainProcessContext.setDebug(true);
        tLogMainProcessContext.setTLogMenuReporterVO(tLogMenuReporterVO);
        TLogExcelReporterExecuter reportExecute = new TLogExcelReporterExecuter(tLogMainProcessContext);
        reportExecute.execute();

        TLogExcelSummaryReporterExecuter summaryReporterExecuter = new TLogExcelSummaryReporterExecuter();
        summaryReporterExecuter.setTLogMainProcessContext(tLogMainProcessContext);
        summaryReporterExecuter.setTLogSummaryVO(reportExecute.getTLogSummaryVO());
        summaryReporterExecuter.execute();
    }
}
