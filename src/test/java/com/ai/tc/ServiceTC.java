package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.TRIS;
import com.ai.control.upc.*;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.ServiceVO;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

/**
 * @author zhoufan
 * @version 1.0.0
 * @date 15/5/7
 * @description Veris UPC Service Test Case
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/5/7       zhoufan         1.0.0              initial
 *
 */
public class ServiceTC {

    private TRISBrowser browser;
    private ServiceVO service;

    @BeforeClass(alwaysRun = true)
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        service = ExcelReader.init(ExcelConst.XLSX_PATH).readService();
    }

    @AfterSuite(alwaysRun = true)
    private void tearDown() {
        browser.quit();
    }

    /**
     * 依据输入测试创建服务规格
     */
    @Test(groups = {"functest", "high"})
    public void testCreateService() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开服务菜单*/
        UPCServiceManmPage serviceManm = menu.chooseServiceMenu();
        /*新增服务*/
        UPCChooseTemplatePage templatePage = serviceManm.addService();
        /*选择模板*/
        UPCServiceEditUIPage serviceEditPage = templatePage.chooseServiceTemplate("47");
        /*创建服务*/
        serviceEditPage.createService(service);
    }


}
