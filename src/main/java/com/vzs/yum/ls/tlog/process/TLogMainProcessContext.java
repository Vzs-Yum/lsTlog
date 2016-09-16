package com.vzs.yum.ls.tlog.process;

import com.vzs.yum.ls.tlog.report.vo.menu.TLogMenuReporterVO;
import com.vzs.yum.ls.tlog.util.DateUtils;
import com.vzs.yum.ls.tlog.util.FileUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/7/16.
 */
@Builder
@Getter
public class TLogMainProcessContext {
    private String tLogFolderPath;
    private List<Date> selectionDate;
    private String reportFolderPath;
    private Date startTimeDate;
    private Date endTimeDate;
    @Setter
    private boolean isDebug;
    @Setter
    private TLogMenuReporterVO tLogMenuReporterVO;

    public String getReporterFolderPath() {
        return reportFolderPath;
    }

    public void createReporterFolderPath() {
        reportFolderPath = getTLogFolderPath() + File.separatorChar + "report" + File.separatorChar + DateUtils.phraseToday();
        FileUtils.makeDirs(reportFolderPath);
    }
}
