package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.config.Menu;
import com.ai.control.TRIS;
import com.ai.control.upc.UPCHomePage;
import com.ai.control.upc.UPCMenuPage;
import com.ai.control.upc.UPCProductManmPage;
import com.ai.control.upc.UPCServiceManmPage;
import com.ai.control.upc.offer.UPCOfferManmPage;
import com.ai.upc.bean.LoginVO;
import com.ai.util.UPCUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * 菜单冒烟
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
        //TODO
    }

    /**
     * 策划组菜单
     */
    @Test
    public void testGroupMenu() {

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
