package com.ai.upc.service;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import static org.openqa.selenium.By.id;

import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

/**
 * Created by zhoufan on 15/5/3.
 */
public class ServiceManm {

    public static class Contants {
        public static String TITLE = "Service Specification Management";
    }

    private TRISWebDriver fwd ;

    public ServiceManm(TRISWebDriver delegate) {
        this.fwd = delegate;
    }

    protected FluentWebElement createServiceButton() {
        return fwd.link(id(ModuleField.getFieldValue(ModuleConst.SERVICEMANM, "createNewServiceButton")));
    }


}
