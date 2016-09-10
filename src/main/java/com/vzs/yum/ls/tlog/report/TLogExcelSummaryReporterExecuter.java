package com.vzs.yum.ls.tlog.report;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vzs.yum.ls.tlog.process.TLogMainProcessContext;
import com.vzs.yum.ls.tlog.report.vo.TLogSheepOrderVO;
import com.vzs.yum.ls.tlog.report.vo.TLogSummaryVO;
import com.vzs.yum.ls.tlog.report.xls.XlsxReporter;
import com.vzs.yum.ls.tlog.util.DateUtils;
import lombok.Setter;
import lombok.experimental.Builder;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by byao on 9/4/16.
 */
@Slf4j
@Setter
public class TLogExcelSummaryReporterExecuter implements TLogReportExecute {
    private TLogSummaryVO tLogSummaryVO;
    private XlsxReporter xlsxReporter;
    private TLogMainProcessContext tLogMainProcessContext;
    public void execute() {
        log.info("exporting summary report");
        String reportFoder = tLogMainProcessContext.getReporterFolderPath();
        String reportAbsFilePath = reportFoder + File.separatorChar + "TLogSummary(" + DateUtils.phraseToday() + ").xlsx";

        createXlsx();
        createSummarySheet();
        createAveragePeople();

        xlsxReporter.closeAndSave(reportAbsFilePath);
        log.info("summary report created and close");
    }

    private void createXlsx() {
        xlsxReporter = new XlsxReporter();
    }

    private void createAveragePeople() {
        xlsxReporter.createSheet("TLogSummary_Sheep_Avg");
        TLogSheepOrderVO tLogSheepOrderVO = tLogSummaryVO.getTLogSheepOrderVO();
        Map<Long, Long> sheepCountMap = tLogSheepOrderVO.getSheepCountMap();
        Map<Long, Long> excludeSheepCountMap = tLogSheepOrderVO.getExcludeSheepCountMap();

        List<Long> guests = combineGuest(sheepCountMap.keySet(), excludeSheepCountMap.keySet());
        createHeader(guests);

        xlsxReporter.createRow();
        xlsxReporter.addCell("羊肉");
        createCount(guests, sheepCountMap);
        xlsxReporter.createRow();
        xlsxReporter.addCell("非羊肉");
        createCount(guests, excludeSheepCountMap);
    }

    private void createCount(List<Long> guests, Map<Long, Long> countMap) {

        for (Long guestCount : guests) {
            Long sheepCount = countMap.get(guestCount);
            if (sheepCount == null) {
                xlsxReporter.addCell("");
            } else {
                xlsxReporter.addCell(sheepCount);
            }
        }
    }

    private void createHeader(List<Long> guests) {
        xlsxReporter.createRow();
        xlsxReporter.addCell("种类/人数");
        for (Long guesetCount : guests) {
            xlsxReporter.addCell(guesetCount + "人");
        }
    }

    private List<Long> combineGuest(Set<Long> firstSet, Set<Long> secondSet) {
        Set<Long> guestNumberLine = Sets.newTreeSet();
        guestNumberLine.addAll(firstSet);
        guestNumberLine.addAll(secondSet);
        return Lists.newArrayList(guestNumberLine);
    }

    private void createSummarySheet() {
        xlsxReporter.createSheet("TLogSummary");
        int orderCount = tLogSummaryVO.getTotalOrderCount();
        double totalOrderTimeCountSeconds = tLogSummaryVO.getTLogSummaryUsageTimeVO().getTotalSeconds();
        long totalOrderGuestCoutn = tLogSummaryVO.getTLogSummaryGuestTotalVO().getGuestTotalCount();
        long totalOrderCoutn = tLogSummaryVO.getTLogSummaryAddOrderVO().getTotalAddCount();
        long totalORderCountWithSetDetail = tLogSummaryVO.getTLogSummaryAddOrderVO().getTotalAddCountWithSetDetail();

        xlsxReporter.createRow();
        xlsxReporter.addCell("平均订单时间");
        xlsxReporter.addCell(totalOrderTimeCountSeconds / orderCount + "秒");

        xlsxReporter.createRow();
        xlsxReporter.addCell("平均订单人数");
        xlsxReporter.addCell(totalOrderGuestCoutn / orderCount + "人");

        xlsxReporter.createRow();
        xlsxReporter.addCell("平均增加产品(不展开套餐)");
        xlsxReporter.addCell(totalOrderCoutn / orderCount + "个");

        xlsxReporter.createRow();
        xlsxReporter.addCell("平均增加产品(展开套餐)");
        xlsxReporter.addCell(totalORderCountWithSetDetail / orderCount + "个");
    }


}
