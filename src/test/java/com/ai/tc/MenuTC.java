package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.TRIS;
import com.ai.control.upc.*;
import com.ai.control.upc.offer.UPCOfferManmPage;
import com.ai.upc.bean.LoginVO;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


/**
 * Veris UPC 菜单冒烟测试
 *
 * Created by zhoufan on 15/5/7.
 */
public class MenuTC {

    private ChromeDriver driver;
    private LoginVO loginInfo;
    private UPCMenuPage menu;

    @BeforeClass
    private void setup() {
        /*TRIS 系统初始化*/
        driver = TRIS.init();
        /*Excel 输入初始化*/
        loginInfo = ExcelReader.init(ExcelConst.XLSX_PATH).readLoginInfo();
        /*MenuPage 获取菜单页面对象*/
        menu = UPCHomePage.navigate(driver).login();
    }

    @AfterClass
    private void tearDown() {
        driver.close();
        driver.quit();
    }

    /**
     * 销售品菜单
     */
    @Test
    public void testOfferMenu() {
        UPCOfferManmPage offerManm = menu.chooseOfferMenu();
        offerManm.isLoaded();
    }

    /**
     * 产品规格菜单
     */
    @Test
    public void testProductMenu() {
        UPCProductManmPage productManm = menu.chooseProductMenu();
        productManm.isLoaded();
    }

    /**
     * 服务规格菜单
     */
    @Test
    public void testServiceMenu() {
        UPCServiceManmPage serviceManm = menu.chooseServiceMenu();
        serviceManm.isLoaded();
    }

    /**
     * 目录菜单
     */
    @Test
    public void testCatalogMenu() {
        UPCCatalogManmPage catalogManm = menu.chooseCatalogMenu();
        catalogManm.isLoaded();
    }

    /**
     * 策划组菜单
     */
    @Test
    public void testGroupMenu() {
        UPCGroupManmPage groupManm = menu.chooseGroupMenu();
        groupManm.isLoaded();
    }

    /**
     * 发布菜单
     */
    @Test
    public void testReleaseMenu() {

    }

    /**
     * 发布日志菜单
     */
    @Test
    public void testReleaseLogMenu() {

    }
}
