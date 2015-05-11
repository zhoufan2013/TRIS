package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.TRIS;
import com.ai.control.upc.*;
import com.ai.upc.bean.ServiceVO;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Veris UPC Service Test Case
 *
 * Created by zhoufan on 15/5/7.
 */
public class ServiceTC {

    private ChromeDriver driver;
    private ServiceVO service;

    @BeforeClass
    private void setup() {
        /*TRIS 系统初始化*/
        driver = TRIS.init();
        /*Excel 输入初始化*/
        service = ExcelReader.init(ExcelConst.XLSX_PATH).readService();
    }

    @AfterClass
    private void tearDown() {
        //driver.close();
        //driver.quit();
    }

    /**
     * 依据输入测试创建服务规格
     */
    @Test
    public void testCreateService() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(driver).login();
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
