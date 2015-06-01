package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.config.Menu;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.upc.enumeration.OfferAction;
import com.ai.upc.group.GroupManm;
import com.ai.upc.service.ServiceManm;
import com.ai.util.TRISUtil;
import com.ai.util.UPCUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by asiainfo on 2015/5/19.
 */
public class UPCOfferGroupManmPage {
    private static transient Log _log = LogFactory.getLog(UPCOfferGroupManmPage.class);

    private TRISBrowser browser;

    public UPCOfferGroupManmPage() {}

    public UPCOfferGroupManmPage(TRISBrowser browser) {
        this.browser = browser;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(browser, "offerGroup");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return GroupManm.Contants.TITLE.equals(currTitle);
    }


    /**
     * 新增
     */
    public UPCOfferGroupEditUIPage addOfferGroup() {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("offerGroup")));
        new GroupManm(browser) {{
            createOfferGroupButton();
        }};
        return PageFactory.initPage(browser, UPCOfferGroupEditUIPage.class);
    }

    /**
     * 查询
     */
    public void queryOfferGroup(final String nameorID) {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("offerGroup")));
        new GroupManm(browser) {{
            browser.input(offerGroupIdorName(),nameorID);
            queryOfferGroupButton();
        }};
    }


    /**
     * 编辑
     */
    public UPCOfferGroupEditUIPage editOfferGroup(String offerGroupId) {
        doAction(offerGroupId, "edit");
        return PageFactory.initPage(browser, UPCOfferGroupEditUIPage.class);
    }

    /**
     * 发布
     */
    public UPCSingleLaunchPage launchOfferGroup(String offerGroupId) {
        doAction(offerGroupId, "groupLaunch");
        return PageFactory.initPage(browser, UPCSingleLaunchPage.class);
    }

    /**
     * offergroup 操作接口
     */
    private void doAction(String offerGroupId, String action) {
        List<WebElement> allRows = TRISUtil.allRows(browser, ElementXPath.OFFERGROUP_MANM_QUERY_ALLROWS);
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (StringUtils.equals(cells.get(1).getText(), offerGroupId)) {
                List<WebElement> eles = cells.get(3).findElements(By.tagName("a"));
                for (WebElement ele : eles) {
                    if (ele.getAttribute("onclick").equals(OfferAction.convert(action).getFunction())) {
                        ele.click();
                    }
                }

                List<WebElement> eles2 = cells.get(3).findElements(By.tagName("img"));
                if (eles2.get(1).getAttribute("onclick").equals(OfferAction.convert(action).getFunction())) {
                    eles2.get(1).click();
                }

            }
        }
    }

}
