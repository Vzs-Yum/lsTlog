package com.vzs.yum.ls.tlog.report.xls;

import com.vzs.yum.ls.tlog.util.DateUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by byao on 8/15/16.
 */
public class XlsxReporter {
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    int rowNum = 0;
    int rowCol = 0;
    CellStyle dateStyle;
    CellStyle timeStyle;
    public void createXls() {
        try {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("TLog");
            createFormater();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFormater() {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        dateStyle = cellStyle;


        cellStyle = workbook.createCellStyle();
        createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("HH:mm:ss"));
        timeStyle = cellStyle;
    }

    public void createRow() {
        row = sheet.createRow(rowNum);
        rowNum ++;

        rowCol = 0;
    }

    public void addCell(String string) {
        XSSFCell cell = row.createCell(rowCol);
        cell.setCellValue(string);
        rowCol ++;
    }

    public void addCell(Number number) {
        if (number == null) {
            addCell("");
            return;
        }
        XSSFCell cell = row.createCell(rowCol);
        cell.setCellValue(number.doubleValue());
        rowCol ++;
    }

    public void addCellDate(Date operateTime) {
        addCell(operateTime, dateStyle);
    }

    public void addCellTime(Date operateTime) {
        addCell(operateTime, timeStyle);
    }

    private void addCell(Date date, CellStyle style) {
        if (date == null) {
            addCell("");
            return;
        }
        XSSFCell cell = row.createCell(rowCol);
        cell.setCellValue(date);
        cell.setCellStyle(style);
        rowCol ++;
    }


    public void closeAndSave(String filePath) {
        try {
            FileOutputStream out = new FileOutputStream(new File(filePath));
            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
