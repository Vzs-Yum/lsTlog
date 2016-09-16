package com.vzs.yum.ls.tlog.report;

import com.vzs.yum.ls.tlog.report.vo.menu.TLogMenuReporterVO;
import com.vzs.yum.ls.tlog.report.xls.XlsxReader;
import com.vzs.yum.ls.tlog.util.StringUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * Created by byao on 9/15/16.
 */
@Slf4j
public class TLogMenuReporterReader implements TLogReportExecute{
    public static final String fileName = "产品分类对照表.xlsx";
    private String filePath;
    @Getter
    TLogMenuReporterVO tLogMenuReporterVO = new TLogMenuReporterVO();
    XlsxReader xlsxReader;

    public TLogMenuReporterReader (String filePath) {
        this.filePath = filePath + File.separator + fileName;
    }

    public void execute() {
        log.info("reading file >>> " + fileName);
        xlsxReader = new XlsxReader(filePath);

        readMenuSheet();

        xlsxReader.close();
    }

    private void readMenuSheet() {
        xlsxReader.readSheet(0);
        while (xlsxReader.readRow()) {
            String key = xlsxReader.getStringAtCol(0);
            String menu = xlsxReader.getStringAtCol(1);
            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(menu)) {
                tLogMenuReporterVO.addEntry(key.trim(), menu.trim());
            }
        }
    }
}
