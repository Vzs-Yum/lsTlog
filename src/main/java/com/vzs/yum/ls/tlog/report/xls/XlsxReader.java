package com.vzs.yum.ls.tlog.report.xls;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by byao on 9/15/16.
 */
public class XlsxReader {
    OPCPackage opcPackage;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    int rowNum = 0;
    int totalRowNum = 0;

    public XlsxReader (String fileAbsoulatePath) {
        try {
            opcPackage = OPCPackage.open(new File(fileAbsoulatePath));
            workbook = new XSSFWorkbook(opcPackage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public void readSheet(int sheetNo) {
        sheet = workbook.getSheetAt(sheetNo);
        rowNum = 0;
        totalRowNum = sheet.getPhysicalNumberOfRows();
    }

    public boolean readRow() {
        if (rowNum > totalRowNum) {
            sheet = null;
            return false;
        }
        row = sheet.getRow(rowNum);
        rowNum ++;
        return true;
    }

    public String getStringAtCol(int colNum) {
        if (row == null) {
            return "";
        }
        XSSFCell cell = row.getCell(colNum);
        if (cell != null) {
            return cell.getStringCellValue();
        }
        return "";
    }

    public void close() {
        try {
            opcPackage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
