package com.ai.upc.offer;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;

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

    /**
     * 查询Offer时的id和name框
     */
    protected FluentWebElement offerIdorName() {
        return fwd.input(name(ModuleField.getFieldValue(ModuleConst.OFFERMANM, "offerIdorName")));
    }

    /**
     * 新建Offer按钮
     */
    protected FluentWebElement createNewOfferButton() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.OFFERMANM, "createNewOfferButton")));
    }

    /**
     * 查询Offer按钮
     */
    protected FluentWebElement qryOfferButton() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.OFFERMANM, "qryOfferBtn")));
    }



}
