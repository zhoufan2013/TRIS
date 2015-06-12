package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.config.Menu;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.upc.common.RadioTree;
import com.ai.upc.enumeration.OfferAction;
import com.ai.upc.login.Login;
import com.ai.upc.service.ServiceManm;
import com.ai.util.TRISUtil;
import com.ai.util.UPCUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by zhoufan on 15/5/6.
 */
public class UPCServiceManmPage {

    private static transient Log _log = LogFactory.getLog(UPCServiceManmPage.class);

    private TRISBrowser browser;

    public UPCServiceManmPage() {}

    public UPCServiceManmPage(TRISBrowser browser) {
        this.browser = browser;
    }


    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(browser, "service");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return ServiceManm.Contants.TITLE.equals(currTitle);
    }

    /**
     * 新增
     */
    public UPCChooseTemplatePage addService() {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("service")));
        new ServiceManm(browser) {{
            browser.click(createServiceButton());
        }};
        return PageFactory.initPage(browser, UPCChooseTemplatePage.class);
    }

    /**
     * 查询
     */
    public void queryService(final  String serviceIdorName, final String servType) {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("service")));
        new ServiceManm(browser) {{

            if (!StringUtils.isEmpty(serviceIdorName)) {
                browser.input(serviceIdorName(),serviceIdorName);
            }
            if (!StringUtils.isEmpty(servType)) {
                new RadioTree(browser){{
                    selectSpecifiedNode(servType);
                }};
            }
            queryServiceButton();
        }};
    }

    /**
     * 删除
     */
    public void deleteService(String serviceId) {

    }

    /**
     * 发布
     */
    public void launchService(String serviceId) {

    }

    /**
     * 编辑
     */
    public UPCServiceEditUIPage editService(String serviceId) {
        doAction(serviceId, "edit");
        return PageFactory.initPage(browser, UPCServiceEditUIPage.class);
    }


    /**
     * service 操作接口
     */
    private void doAction(String serviceId, String action) {
        List<WebElement> allRows = TRISUtil.allRows(browser, ElementXPath.SERVICE_MANM_QUERY_ALLROWS);
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (StringUtils.equals(cells.get(2).getText(), serviceId)) {
                List<WebElement> eles = cells.get(7).findElements(By.tagName("a"));
                for (WebElement ele : eles) {
                    if (ele.getAttribute("onclick").equals(OfferAction.convert(action).getFunction())) {
                        ele.click();
                    }
                }
            }
        }
    }

}
