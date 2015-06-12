package com.ai.upc.service;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import static org.openqa.selenium.By.id;

import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

/**
 * 服务规格主查询页面控件收集
 *
 * Created by zhoufan on 15/5/3.
 */
public class ServiceManm {

    public static class Contants {
        public static String TITLE = "Service Specification Management";
    }

    private TRISWebDriver fwd ;
    private TRISBrowser browser;

    public ServiceManm(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browser.getWebDriver();
    }

    protected FluentWebElement createServiceButton() {
        return fwd.link(id(ModuleField.getFieldValue(ModuleConst.SERVICEMANM, "createNewServiceButton")));
    }

    protected void queryServiceButton() {
        //return fwd.link(id(ModuleField.getFieldValue(ModuleConst.SERVICEMANM,"queryServiceButton")));
        browser.getInternalWebDriver().findElementById(ModuleField.getFieldValue(ModuleConst.SERVICEMANM,"queryServiceButton")).click();
    }

    /**
     * ID和名称输入框
     */
    protected FluentWebElement serviceIdorName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.SERVICEMANM, "serviceIdorName")));
    }
}
