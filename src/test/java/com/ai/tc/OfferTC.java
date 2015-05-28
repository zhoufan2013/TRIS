package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.upc.UPCChooseTemplatePage;
import com.ai.control.upc.UPCHomePage;
import com.ai.control.upc.UPCMenuPage;
import com.ai.control.upc.offer.UPCOfferEditUIPage;
import com.ai.control.upc.offer.UPCOfferManmPage;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.ProductVO;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

/**
 * @author zhoufan
 * @version 1.0.0
 * @date 15/5/23
 * @description 销售品模块自动化case
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/5/23      zhoufan         1.0.0              initial
 */
public class OfferTC {

    private TRISBrowser browser;
    private ProductVO product;

    @BeforeClass(alwaysRun = true)
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        product = ExcelReader.init(ExcelConst.XLSX_PATH).readProduct();
    }

    @AfterClass(alwaysRun = true)
    private void tearDown() {
        browser.quit();
    }

    /**
     * 测试根据GSM模板新增销售品
     * @author zhoufan
     */
    @Test(groups = {"functest", "high"})
    public void UPC_CRM_OFFER_0001() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("45");
        /*进入编辑页面并输入基本信息*/
        offerEditUIPage.intoBasicInfo().insertBasicInfo().insertOfferChar();

    }

    /**
     * 测试offer校验功能之必选特征
     * @author zhoufan
     */
    @Test(groups = {"functest", "medium"})
    public void UPC_CRM_OFFER_0002() {
    }


    /**
     * offer校验功能之关联操作
     * @author zhoufan
     */
    @Test(groups = {"functest", "medium"})
    public void UPC_CRM_OFFER_0003() {
    }

    /**
     * 测试拷贝DSL主套餐
     * @author zhoufan
     */
    @Test(groups = {"functest", "high"})
    public void UPC_CRM_OFFER_0004() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*查询并拷贝*/
        offerManmPage.queryOffer("2000539").copyOffer("2000539");
        /*校验*/
        //TODO
    }

    /**
     * 测试发布DSL主套餐功能
     * @author zhoufan
     */
    @Test(groups = {"functest", "high"})
    public void UPC_CRM_OFFER_0005() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*查询并发布*/
        offerManmPage.queryOffer("2000539").launchOffer("2000539");
        /*校验*/
        //TODO

    }

    /**
     * 测试offer编辑页面枚举特征是否显示正常
     * @author tianhj
     */
    @Test
    public void UPC_CRM_OFFER_0006() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*查询*/
        offerManmPage.queryOffer("95042611");
        /*点击编辑按钮*/
        UPCOfferEditUIPage offerEditUIPage = offerManmPage.editOffer("95042611");
        /*进入编辑页面*/
        offerEditUIPage.intoEditBaseicInfo("95042611");
        /*编辑并保存*/
        offerEditUIPage.checkEnumChar("95042611","200063");

        assertTrue(offerEditUIPage.checkEnumChar("95042611","200063"));
    }

}
