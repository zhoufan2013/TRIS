package com.ai.upc.catalog;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;

/**
 * Created by zhoufan on 15/5/11.
 */
public class CatalogManm {

    private TRISBrowser browser ;

    private TRISWebDriver fwd;

    public static class Contants {
        public static String TITLE = "Product Catalog Management";
    }


    protected void createCatalogButton() {
        browser.getInternalWebDriver().findElementByXPath(ModuleField.getFieldValue(ModuleConst.CATALOG_MANM, "addBtn")).click();
        //return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_MANM, "createNewOfferGroupButton")));
    }


    protected void queryCatalogButton() {
        //return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_MANM, "queryOfferGroup")));
        browser.getInternalWebDriver().findElementByXPath(ModuleField.getFieldValue(ModuleConst.CATALOG_MANM, "queryBtn")).click();
    }


    protected FluentWebElement catalogIdorName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.CATALOG_MANM, "nameorId")));
    }

}
