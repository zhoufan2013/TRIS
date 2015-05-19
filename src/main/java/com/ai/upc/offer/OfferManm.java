package com.ai.upc.offer;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.id;

/**
 * offer主查询页面
 *
 * Created by zhoufan on 15/5/1.
 */
public class OfferManm {

    public static class Contants {
        public static String TITLE = "Product Offering Management";
    }

    private TRISWebDriver fwd ;

    public OfferManm(TRISWebDriver delegate) {
        this.fwd = delegate;
    }

    protected FluentWebElement offerId() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFERMANM, "offerId")));
    }

    protected FluentWebElement createNewOffer() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.OFFERMANM, "createNewOfferButton")));
    }



}
