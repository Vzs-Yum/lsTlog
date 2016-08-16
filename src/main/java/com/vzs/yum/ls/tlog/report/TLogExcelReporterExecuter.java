package com.vzs.yum.ls.tlog.report;

import com.google.common.collect.Lists;
import com.vzs.yum.ls.tlog.process.TLogMainProcessContext;
import com.vzs.yum.ls.tlog.report.xls.XlsxReporter;
import com.vzs.yum.ls.tlog.util.DateUtils;
import com.vzs.yum.ls.tlog.util.FileUtils;
import com.vzs.yum.ls.tlog.util.ListUtils;
import com.vzs.yum.ls.tlog.vo.TLogTransaction;
import com.vzs.yum.ls.tlog.vo.TLogTransactionFooter;
import com.vzs.yum.ls.tlog.vo.TLogTransactionNoun;
import com.vzs.yum.ls.tlog.vo.TLogTransactionSingleton;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by byao on 8/14/16.
 */
public class TLogExcelReporterExecuter implements TLogReportExecute {
    private TLogTransactionSingleton tLogTransactionSingleton = TLogTransactionSingleton.getInstance();
    private TLogMainProcessContext tLogMainProcessContext;

    private XlsxReporter xlsxReporter;

    public TLogExcelReporterExecuter(TLogMainProcessContext tLogMainProcessContext) {
        this.tLogMainProcessContext = tLogMainProcessContext;
    }

    public void execute() {

        System.out.println("exporting report");
        String reportFoder = tLogMainProcessContext.getTLogFolderPath() + File.separatorChar + "report";
        FileUtils.makeDirs(reportFoder);
        String reportAbsFilePath = reportFoder + File.separatorChar + "TLog(" + DateUtils.phraseToday() + ").xlsx";

        createXlsx();

        writeHeader(10);
        List<TLogTransaction> tLogTransactionList = tLogTransactionSingleton.findAllTLogTransaction();
        for (TLogTransaction tLogTransaction : tLogTransactionList) {
            addOneTransaction(tLogTransaction);
        }

        xlsxReporter.closeAndSave(reportAbsFilePath);
        System.out.println("report created and close");
    }

    private void createXlsx() {
        xlsxReporter = new XlsxReporter();
        xlsxReporter.createXls();
    }

    private void addOneTransaction(TLogTransaction tLogTransaction) {
        List<TLogTransactionFooter> transactionFooters = tLogTransaction.getTransactionFooters();
        Collections.sort(transactionFooters, new Comparator<TLogTransactionFooter>() {
            public int compare(TLogTransactionFooter o1, TLogTransactionFooter o2) {
                if (o1.getTransactionFooterDate() != null && o2.getTransactionFooterDate() != null) {
                    return o1.getTransactionFooterDate().compareTo(o2.getTransactionFooterDate());
                }
                return 0;
            }
        });

        boolean isFirst = true;
        for (TLogTransactionFooter tLogTransactionFooter : transactionFooters) {
            if (isFirst) {
                firstLineWrite(tLogTransaction, tLogTransactionFooter);
                isFirst = false;
            } else {
                String operateJudge;
                if (tLogTransactionFooter.getTender() != null) {
                    operateJudge = "结账";
                } else {
                    operateJudge = judgeOperate(tLogTransactionFooter.getNouns());
                }
                writeNounsWithHead(tLogTransaction, tLogTransactionFooter, "Transaction Footer", operateJudge);
            }
        }
    }

    private void writeHeader(int dishLoop) {
        xlsxReporter.createRow();
        xlsxReporter.addCell("订单号");
        xlsxReporter.addCell("操作日期");
        xlsxReporter.addCell("操作时间");
        xlsxReporter.addCell("操作内容");
        xlsxReporter.addCell("logged op");
        xlsxReporter.addCell("own op");
        xlsxReporter.addCell("操作判断");
        xlsxReporter.addCell("桌号");
        xlsxReporter.addCell("顾客数");
        xlsxReporter.addCell("金额合计");
        for (int i = 0 ; i < dishLoop ; i ++) {
            int appendStr = i + 1;
            xlsxReporter.addCell("菜品数量" + appendStr);
            xlsxReporter.addCell("菜品名称" + appendStr);
            xlsxReporter.addCell("Mod" + appendStr);
        }
    }

    private void firstLineWrite(TLogTransaction tLogTransaction, TLogTransactionFooter tLogTransactionFooter) {
        writeLineHeader(tLogTransaction, tLogTransactionFooter, "Transaction Header", "开台", true);
        writeNounsWithHead(tLogTransaction, tLogTransactionFooter, "Transaction Footer", "第一次存单");
    }

    private void writeNounsWithHead(TLogTransaction tLogTransaction, TLogTransactionFooter tLogTransactionFooter , String operateContext, String operateJudge) {
        writeLineHeader(tLogTransaction, tLogTransactionFooter, operateContext, operateJudge, false);
        for (TLogTransactionNoun tLogTransactionNoun : tLogTransactionFooter.getNouns()) {
            writeNoun(tLogTransactionNoun);
            if (tLogTransactionNoun.getIsSet()) {
                for (TLogTransactionNoun setNoun : tLogTransactionNoun.getSetDetailNouns()) {
                    writeNoun(setNoun);
                }
            }
        }

    }

    private void writeNoun(TLogTransactionNoun tLogTransactionNoun) {
        Long quantity = tLogTransactionNoun.getQuantity();
        String name = tLogTransactionNoun.getName();
        String mod = tLogTransactionNoun.getMod();
        xlsxReporter.addCell(quantity);
        String perFixStr = tLogTransactionNoun.isPostDelete() ? "**" : tLogTransactionNoun.isPreDelete() ? "*" : tLogTransactionNoun.isExisting() ? "" : "%";
        xlsxReporter.addCell(perFixStr + name);
        xlsxReporter.addCell(mod);
    }

    private String judgeOperate(List<TLogTransactionNoun> nouns) {
        if (ListUtils.isEmpty(nouns)) {
            return "查看";
        }
        boolean hasAdd = false;
        boolean hasDelete = false;
        for (TLogTransactionNoun tLogTransactionNoun : nouns) {
            if (tLogTransactionNoun.isPostDelete() || tLogTransactionNoun.isPreDelete()) {
                hasDelete = true;
            } else {
                hasAdd = true;
            }

            if (hasAdd && hasDelete) {
                break;
            }

            if (tLogTransactionNoun.getIsSet()) {
                for (TLogTransactionNoun setNoun : tLogTransactionNoun.getSetDetailNouns()) {
                    if (setNoun.isPostDelete() || setNoun.isPreDelete()) {
                        hasDelete = true;
                    } else {
                        hasAdd = true;
                    }

                    if (hasAdd && hasDelete) {
                        break;
                    }
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        if (hasAdd) {
            sb.append("新增");
        }
        if (hasDelete) {
            if (hasAdd) {
                sb.append(",删除");
            } else {
                sb.append("删除");
            }
        }
        return sb.toString();
    }

    private void writeLineHeader(TLogTransaction tLogTransaction, TLogTransactionFooter tLogTransactionFooter, String operateContext, String operateJudge, boolean isHeader) {
        xlsxReporter.createRow();
        String transactionid = tLogTransaction.getTransactionHeaderId();
        Date operateTime = tLogTransaction.getTransactionHeaderTime();
        if (!isHeader) {
            operateTime = tLogTransactionFooter.getTransactionFooterDate();
        }


        String loggedOp = tLogTransactionFooter.getLoggedOp();
        String ownedOp = tLogTransactionFooter.getOwnerOp();
        Long tableNo = tLogTransactionFooter.getTableNo();
        Long customCount = tLogTransactionFooter.getGuestsNum();
        Double total = tLogTransactionFooter.getTotal();

        xlsxReporter.addCell(transactionid);
        xlsxReporter.addCellDate(operateTime);
        xlsxReporter.addCellTime(operateTime);
        xlsxReporter.addCell(operateContext);
        xlsxReporter.addCell(loggedOp);
        xlsxReporter.addCell(ownedOp);
        xlsxReporter.addCell(operateJudge);
        xlsxReporter.addCell(tableNo);
        xlsxReporter.addCell(customCount);
        xlsxReporter.addCell(total);
    }


}
