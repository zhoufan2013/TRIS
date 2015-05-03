package com.ai.upc.service;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.BasePage;
import static org.openqa.selenium.By.id;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

/**
 * Created by zhoufan on 15/5/3.
 */
public class ServiceManm extends BasePage{

    public ServiceManm(ChromeDriver delegate) {
        super(delegate);
    }

    protected FluentWebElement createNewService() {
        return button(id(ModuleField.getFieldValue(ModuleConst.SERVICEMANM, "createNewServiceButton"))).click();
    }


}
