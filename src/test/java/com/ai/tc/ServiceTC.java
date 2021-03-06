package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.TRIS;
import com.ai.control.upc.*;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.ServiceVO;
import com.ai.upc.common.MessageBox;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

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
    public void UPC_CRM_SERV_0001() {
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

    /**
     * SERVICE查询
     * tianhj
     */
    @Test
    public void UPC_CRM_SERV_0002() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开服务菜单*/
        UPCServiceManmPage serviceManm = menu.chooseServiceMenu();
        /*查询服务*/
        serviceManm.queryService("","");
    }

    /**
     * SERVICE编辑
     * tianhj
     */
    @Test
    public void UPC_CRM_SERV_0003() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开服务菜单*/
        UPCServiceManmPage serviceManm = menu.chooseServiceMenu();
        /*查询服务*/
        serviceManm.queryService("30192","");
        /*点击编辑按钮*/
        UPCServiceEditUIPage serviceEditPage = serviceManm.editService("30192");
        /*编辑保存*/
        service.setServiceId("30192");
        serviceEditPage.editService(service);
    }

    /**
     * SERVICE发布
     * tianhj
     */
    @Test
    public void UPC_CRM_SERV_0004() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开服务菜单*/
        UPCServiceManmPage serviceManm = menu.chooseServiceMenu();
        /*查询服务*/
        serviceManm.queryService("30192","");
       /*点击发布按钮*/
        UPCSingleLaunchPage launchPage = serviceManm.launchService("30192");

        new MessageBox(browser){{
            okSuccessMsg();
        }};

        /*发布*/
        launchPage.launchObject("CRM_SR0.3.2_dev");

        browser.pause(1, TimeUnit.MINUTES);

        /*校验发布是否成功*/
        UPCReleaseLogPage releaseLogPage = menu.chooseReleaseLogMenu();
        assertTrue(releaseLogPage.checkLaunchResult("30192"));
    }

}
