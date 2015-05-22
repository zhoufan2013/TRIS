package com.ai.tc;

import com.ai.config.*;
import com.ai.control.upc.*;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.ProductVO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoufan
 * @version 1.0.0
 * @date 15/5/11
 * @description 产品模块自动化case
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/5/11      zhoufan         1.0.0              initial
 *
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
//        browser.quit();
    }

    /**
     * 测试根据Excel输入创建产品功能
     * @author zhoufan
     */
    @Test
    public void UPC_CRM_0001() {
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
    public void UPC_CRM_0002() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开产品菜单*/
        UPCProductManmPage productManm = menu.chooseProductMenu();
        /*查询产品,编辑产品*/
        UPCProductEditUIPage productEditUIPage = productManm.queryProduct("1010065").editProduct("1010065");
        /*产品关联服务后并保存产品*/
        productEditUIPage.addServiceSpecification("2200000").saveProduct();

    }

    /**
     * 测试产品发布功能
     * @author zhoufan
     */
    @Test
    public void UPC_CRM_0003() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开产品菜单*/
        UPCProductManmPage productManm = menu.chooseProductMenu();
        /*查询产品,编辑产品*/
        productManm.queryProduct("1010065").launchProduct("1010065");
        /*发布等待时常5秒*/
        browser.pause(5l, TimeUnit.SECONDS);
        /*打开发布菜单*/
        UPCReleaseLogPage releaseLogPage = menu.chooseReleaseLogMenu();
        /*确认同步结果2000546*/
        releaseLogPage.checkLaunchResult("1010065");
    }

    /**
     * 测试非必选特征是否弹出校验框
     * @author zhoufan
     */
    @Test
    public void UPC_CRM_0004() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开产品菜单*/
        UPCProductManmPage productManm = menu.chooseProductMenu();
        /*查询产品,编辑产品*/
        UPCProductEditUIPage productEditUIPage = productManm.queryProduct("1010065").editProduct("1010065").openThenSave();
        /*校验是否弹出校验不通过的提示框*/
        browser.switchToUPCAlert("//*[@id=\"wade_ext_msg_title\"]");
    }

    /**
     * 测试新增产品关系
     * @author zhoufan
     */
    @Test
    public void UPC_CRM_0005() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开产品菜单*/
        UPCProductManmPage productManm = menu.chooseProductMenu();
        /*查询产品,编辑产品*/
        UPCProductEditUIPage productEditUIPage = productManm.queryProduct("1010065").editProduct("1010065");
        /*新增产品关联关系并保存*/
        productEditUIPage.addproductRelationships();
        /*数据校验*/
        // TODO
    }
}
