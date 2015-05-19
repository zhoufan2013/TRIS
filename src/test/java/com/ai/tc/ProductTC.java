package com.ai.tc;

import com.ai.config.*;
import com.ai.control.upc.*;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.ProductVO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by zhoufan on 15/5/11.
 */
public class ProductTC {

    private TRISBrowser browser;
    private ProductVO product;

    @BeforeClass
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        product = ExcelReader.init(ExcelConst.XLSX_PATH).readProduct();
    }

    @AfterClass
    private void tearDown() {
        //driver.close();
        //driver.quit();
    }

    /**
     * 测试根据Excel输入创建产品功能
     * @author zhoufan
     */
    @Test
    public void testCreateProduct() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开产品菜单*/
        UPCProductManmPage productManm = menu.chooseProductMenu();
        /*新增产品*/
        UPCChooseTemplatePage templatePage = productManm.addProduct();
        /*选择模板*/
        UPCProductEditUIPage productEditPage = templatePage.chooseProductTemplate("26");
        /*创建产品*/
        String productId = productEditPage.createProduct(product);
        /*校验数据正确性*/
        productManm.queryProduct(productId);

    }

    /**
     * 测试查询产品功能
     * @author zhoufan
     */
    @Test
    public void testQueryProduct() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开产品菜单*/
        UPCProductManmPage productManm = menu.chooseProductMenu();
        /*查询产品*/
        productManm.queryProduct("30099");
    }


}
