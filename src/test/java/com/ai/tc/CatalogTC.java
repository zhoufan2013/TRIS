package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.upc.*;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.CatalogVO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author tianhj
 * @version 1.0.0
 * @date 2015/6/12
 * @description 该类的功能描述
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2015/6/12      tianhj         1.0.0             initial
 */
public class CatalogTC {
    private TRISBrowser browser;
    private CatalogVO catalog;

    @BeforeClass
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        //catalog = ExcelReader.init(ExcelConst.XLSX_PATH).readOfferGroup();
    }

    @AfterClass
    private void tearDown() {
        //browser.quit();
    }

    /**
     * 根目录查询
     */
    @Test
    public void UPC_CRM_CATALOG_0001() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开catalog菜单*/
        UPCCatalogManmPage catalogManm = menu.chooseCatalogMenu();
        /*查询catalog*/
        catalogManm.queryCatalog("20061");
    }


    /**
     * 根目录新增
     */
    @Test
    public void UPC_CRM_CATALOG_0002() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开catalog菜单*/
        UPCCatalogManmPage catalogManm = menu.chooseCatalogMenu();
        /*add button*/
        UPCCatalogEditUIPage catalogEditUIPage = catalogManm.createCatalog();
        /*add*/
        catalog = new CatalogVO();
        catalog.setCatalogName("autotest");
        catalog.setCatalogType("1");
        catalog.setDescription("autotest");
        catalogEditUIPage.createCatalog(catalog);
    }

    /**
     * 子目录新增
     */
    @Test
    public void UPC_CRM_CATALOG_0003() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开catalog菜单*/
        UPCCatalogManmPage catalogManm = menu.chooseCatalogMenu();
        /*查询catalog*/
        catalogManm.queryCatalog("30086");
        /*点击编辑子目录按钮*/
        UPCCatalogNodePage catalogNode = catalogManm.editCatalogNode("30086");


        catalogNode.newCatalogNode(catalog);

    }
}
