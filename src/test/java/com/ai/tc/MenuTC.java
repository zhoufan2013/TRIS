package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.upc.*;
import com.ai.control.upc.offer.UPCOfferManmPage;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.LoginVO;
import org.testng.annotations.*;
import static org.testng.Assert.assertTrue;


/**
 * @author zhoufan
 * @version 1.0.0
 * @date 15/5/7
 * @description Veris UPC 菜单冒烟
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/5/7       zhoufan         1.0.0              initial
 *
 */
public class MenuTC {

    private TRISBrowser browser;
    private LoginVO loginInfo;
    private UPCMenuPage menu;

    @BeforeClass(alwaysRun = true)
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        loginInfo = ExcelReader.init(ExcelConst.XLSX_PATH).readLoginInfo();
        /*MenuPage 获取菜单页面对象*/
        menu = UPCHomePage.navigate(browser).login();
    }

    @AfterClass(alwaysRun = true)
    private void tearDown() {
        browser.quit();
    }

    /**
     * 销售品菜单
     */
    @Test(groups = {"functest", "high"})
    public void UPC_CRM_MENU_0001() {
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        offerManmPage.isLoaded();
    }

    /**
     * 产品规格菜单
     */
    @Test(groups = {"functest", "high"})
    public void UPC_CRM_MENU_0002() {
        UPCProductManmPage productManm = menu.chooseProductMenu();
        assertTrue(productManm.isLoaded());
    }

    /**
     * 服务规格菜单
     */
    @Test(groups = {"functest", "high"})
    public void UPC_CRM_MENU_0003() {
        UPCServiceManmPage serviceManm = menu.chooseServiceMenu();
        assertTrue(serviceManm.isLoaded());
    }

    /**
     * 目录菜单
     */
    @Test(groups = {"functest", "high"})
    public void UPC_CRM_MENU_0005() {
        UPCCatalogManmPage catalogManmPage = menu.chooseCatalogMenu();
        assertTrue(catalogManmPage.isLoaded());
    }

    /**
     * 策划组菜单
     */
    @Test(groups = {"functest", "high"})
    public void UPC_CRM_MENU_0006() {
        UPCGroupManmPage groupManmPage = menu.chooseGroupMenu();
        assertTrue(groupManmPage.isLoaded());
    }
}
