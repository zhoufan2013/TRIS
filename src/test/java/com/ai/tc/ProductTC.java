package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.TRIS;
import com.ai.upc.bean.ServiceVO;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by zhoufan on 15/5/11.
 */
public class ProductTC {

    private ChromeDriver driver;
    private ServiceVO service;
    private ExcelReader excel;

    @BeforeClass
    private void setup() {
        /*TRIS 系统初始化*/
        driver = TRIS.init();
        /*Excel 输入初始化*/
        service = ExcelReader.init(ExcelConst.XLSX_PATH).readService();
        excel = ExcelReader.init(ExcelConst.XLSX_PATH);
    }

    @AfterClass
    private void tearDown() {
        //driver.close();
        //driver.quit();
    }

    @Test
    public void testCreateProduct() {


    }

}
