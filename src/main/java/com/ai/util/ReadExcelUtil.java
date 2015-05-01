package com.ai.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by zhoufan on 15/4/30.
 */
public class ReadExcelUtil {


    public static void readExcel() {

        try {
            Workbook wb = WorkbookFactory.create(new File("MyExcel.xls"));



        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }


    }

}
