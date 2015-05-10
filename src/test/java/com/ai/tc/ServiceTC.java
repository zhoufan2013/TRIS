package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.TRIS;
import com.ai.upc.bean.LoginVO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Veris UPC Service Test Case
 *
 * Created by zhoufan on 15/5/7.
 */
public class ServiceTC {

    private static ChromeDriver driver;
    private static LoginVO loginInfo;

    @BeforeClass
    public static void setup() {
        /*TRIS 系统初始化*/
        driver = TRIS.init();
        /*Excel 输入初始化*/
        loginInfo = ExcelReader.init(ExcelConst.XLSX_PATH).readLoginInfo();
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }



}
