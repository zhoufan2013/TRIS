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
    public void testCreateOfferGroup() {
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开offerGroup菜单*/
        UPCOfferGroupManmPage offerGroupManm = menu.chooseofferGroupMenu();
        /*新增offerGroup*/
        UPCOfferGroupEditUIPage offerGroupEditUIPage = offerGroupManm.addOfferGroup();

        /*创建offerGroup*/
        offerGroupEditUIPage.createOfferGroup(offerGroup);
    }
}
