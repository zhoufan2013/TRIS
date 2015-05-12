package com.ai.tc;

import com.ai.config.*;
import com.ai.control.TRIS;
import com.ai.control.upc.*;
import com.ai.upc.bean.ProductVO;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by zhoufan on 15/5/11.
 */
public class ProductTC {

    private ChromeDriver driver;
    private ProductVO product;
    private ExcelReader excel;

    @BeforeClass
    private void setup() {
        /*TRIS 系统初始化*/
        driver = TRIS.init();
        /*Excel 输入初始化*/
        product = ExcelReader.init(ExcelConst.XLSX_PATH).readProduct();
    }

    @AfterClass
    private void tearDown() {
        //driver.close();
        //driver.quit();
    }

    @Test
    public void testCreateProduct() {

        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(driver).login();
        /*打开产品菜单*/
        UPCProductManmPage productManm = menu.chooseProductMenu();
        /*新增产品*/
        UPCChooseTemplatePage templatePage = productManm.createNewProduct();
        /*选择模板*/
        UPCProductEditUIPage productEditPage = templatePage.chooseProductTemplate("26");
        /*创建产品*/
        productEditPage.createProduct(product);

    }

}
