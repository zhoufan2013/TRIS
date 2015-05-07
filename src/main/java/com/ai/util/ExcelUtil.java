package com.ai.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by zhoufan on 15/4/30.
 */
public final class ExcelUtil {

    private static transient Log _log = LogFactory.getLog(ExcelUtil.class);

    public static Workbook createWb(String filePath) {
        if(StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("参数错误") ;
        }
        try {
            Workbook wb = WorkbookFactory.create(new File(filePath));
            return wb;
        } catch (InvalidFormatException e) {
            _log.error("", e);//   throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式") ;
        } catch (IOException e) {
            _log.error("", e);
        }
        //return new XSSFWorkbook();
        return null;
    }

    public static Sheet getSheetViaSheetName(Workbook wb ,String sheetName) {
        return wb.getSheet(sheetName) ;
    }

    public static List<String[]> listFromSheet(Sheet sheet) {

        int rowTotal = sheet.getPhysicalNumberOfRows() ;
        //Debug.printf("{}共有{}行记录！" ,sheet.getSheetName() ,rowTotal) ;

        List<String[]> list = new ArrayList<String[]>() ;
        for(int r = sheet.getFirstRowNum() ; r <= sheet.getLastRowNum() ; r ++) {
            Row row = sheet.getRow(r) ;
            if(row == null)continue ;
            // 不能用row.getPhysicalNumberOfCells()，可能会有空cell导致索引溢出
            // 使用row.getLastCellNum()至少可以保证索引不溢出，但会有很多Null值，如果使用集合的话，就不说了
            String[] cells = new String[row.getLastCellNum()] ;
            for(int c = row.getFirstCellNum() ; c <= row.getLastCellNum() ; c++) {
                Cell cell = row.getCell(c) ;
                if(cell == null)continue ;
                cells[c] = getValueFromCell(cell);
            }
            list.add(cells) ;
        }

        return list ;
    }

    public static String getValueFromCell(Cell cell) {
        if(cell == null) {
            //printf("Cell is null !!!") ;
            return null ;
        }

        String value = null ;
        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC :   // 数字
                /*if(HSSFDateUtil.isCellDateFormatted(cell)) {        // 如果是日期类型
                    value = new SimpleDateFormat(DatePattern.LOCALE_ZH_DATE.getValue()).format(cell.getDateCellValue()) ;
                } else */
                value = String.valueOf(new Double(cell.getNumericCellValue()).longValue()) ;
                break ;
            case Cell.CELL_TYPE_STRING:     // 字符串
                value = cell.getStringCellValue() ;
                break ;
            case Cell.CELL_TYPE_BLANK:              // 空白
                value = StringUtils.EMPTY ;
                break ;
            case Cell.CELL_TYPE_BOOLEAN:            // Boolean
                value = String.valueOf(cell.getBooleanCellValue()) ;
                break ;
            case Cell.CELL_TYPE_ERROR:              // Error，返回错误码
                value = String.valueOf(cell.getErrorCellValue()) ;
                break ;
            default:value = StringUtils.EMPTY ;break ;
        }
        // 使用[]记录坐标
        return value + "["+cell.getRowIndex()+","+cell.getColumnIndex()+"]";
    }

}
