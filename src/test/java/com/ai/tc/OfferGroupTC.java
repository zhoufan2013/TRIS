package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.config.SysInfo;
import com.ai.control.upc.*;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.OfferGroupVO;
import com.ai.util.UPCUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

/**
 * Created by asiainfo on 2015/5/19.
 */
public class OfferGroupTC {

    private TRISBrowser browser;
    private OfferGroupVO offerGroup;

    @BeforeClass
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        offerGroup = ExcelReader.init(ExcelConst.XLSX_PATH).readOfferGroup();
    }

    @AfterClass
    private void tearDown() {
        //browser.quit();
    }

    /**
     * 依据输入测试创建服offerGroup
     */
    @Test
    public void UPC_CRM_OFFERGROUP_0001() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开offerGroup菜单*/
        UPCOfferGroupManmPage offerGroupManm = menu.chooseofferGroupMenu();
        /*新增offerGroup*/
        UPCOfferGroupEditUIPage offerGroupEditUIPage = offerGroupManm.addOfferGroup();

        /*创建offerGroup*/
        offerGroupEditUIPage.createOfferGroup(offerGroup);
    }

    /**
     * 查询offerGroup
     */
    @Test
    public void UPC_CRM_OFFERGROUP_0002() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开offerGroup菜单*/
        UPCOfferGroupManmPage offerGroupManm = menu.chooseofferGroupMenu();
        /*查询offerGroup*/
        offerGroupManm.queryOfferGroup("31000001");
    }

    /**
     * 编辑offerGroup
     */
    @Test
    public void UPC_CRM_OFFERGROUP_0003() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开offerGroup菜单*/
        UPCOfferGroupManmPage offerGroupManm = menu.chooseofferGroupMenu();
        /*查询offerGroup*/
        offerGroupManm.queryOfferGroup("31000001");
        /*点编辑按钮*/
        UPCOfferGroupEditUIPage offerGroupEditUIPage = offerGroupManm.editOfferGroup("31000001");
        /*编辑并保存*/
        offerGroupEditUIPage.editOfferGroup(offerGroup);
    }

    /*发布offerGroup*/
    @Test
    public void UPC_CRM_OFFERGROUP_0004() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开offerGroup菜单*/
        UPCOfferGroupManmPage offerGroupManm = menu.chooseofferGroupMenu();
        /*查询offerGroup*/
        offerGroupManm.queryOfferGroup("31000001");
        /*点击发布按钮*/
        UPCSingleLaunchPage launchPage = offerGroupManm.launchOfferGroup("31000001");
        /*发布*/
        launchPage.launchObject("CRM_SR0.3.2_dev");
        /*校验发布是否成功*/
        UPCReleaseLogPage releaseLogPage = menu.chooseReleaseLogMenu();
        assertTrue(releaseLogPage.checkLaunchResult("31000001"));
    }
}
