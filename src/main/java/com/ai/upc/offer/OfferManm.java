package com.ai.upc.offer;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.BasePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.id;

/**
 * offer主查询页面
 *
 * Created by zhoufan on 15/5/1.
 */
public class OfferManm extends BasePage {

    public OfferManm(ChromeDriver delegate) {
        super(delegate);
    }

    protected FluentWebElement offerId() {
        return input(id(ModuleField.getFieldValue(ModuleConst.OFFERMANM, "offerId")));
    }

    protected FluentWebElement createNewOffer() {
        return button(id(ModuleField.getFieldValue(ModuleConst.OFFERMANM, "createNewOfferButton")));
    }



}
