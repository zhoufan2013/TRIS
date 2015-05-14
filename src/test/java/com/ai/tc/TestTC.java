package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.upc.UPCHomePage;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.LoginVO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by zhoufan on 15/5/14.
 */
public class TestTC {

    private static TRISBrowser browser;
    private static LoginVO loginInfo;

    @BeforeClass
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        loginInfo = ExcelReader.init(ExcelConst.XLSX_PATH).readLoginInfo();
    }

    @AfterClass
    private void tearDown() {
        //browser.quit();
    }

    @Test
    public void test() {
        boolean flag = UPCHomePage.navigate(browser).login("21upc", "123456");
    }


}
