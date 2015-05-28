package com.ai.upc.group;


import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

/**
 * Created by zhoufan on 15/5/11.
 */
public class GroupManm {

    public static class Contants {
        public static String TITLE = "Product Offering Group Management";
    }

    private TRISBrowser browser ;

    private TRISWebDriver fwd;

    public GroupManm(TRISBrowser browser) {
        this.browser = browser;
        this.fwd = browser.getWebDriver();
    }

    /**
     * 新增offergroup按钮
     */
    protected void createOfferGroupButton() {
        browser.getInternalWebDriver().findElementByXPath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_MANM, "createNewOfferGroupButton")).click();
        //return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_MANM, "createNewOfferGroupButton")));
    }

    /**
     * 查询offergroup按钮
     */
    protected void queryOfferGroupButton() {
        //return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_MANM, "queryOfferGroup")));
        browser.getInternalWebDriver().findElementByXPath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_MANM, "queryOfferGroup")).click();
    }

    /**
     * offergroup的ID和名称输入框
     */
    protected FluentWebElement offerGroupIdorName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_MANM, "offerGroupIdorName")));
    }

}
