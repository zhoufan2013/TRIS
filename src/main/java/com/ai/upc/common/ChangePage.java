package com.ai.upc.common;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.xpath;

/**
 * @author tianhj
 * @version 1.0.0
 * @date 2015/6/1
 * @description 该类的功能描述
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2015/6/1      tianhj         1.0.0             initial
 */
public class ChangePage {

    private static transient Log _log = LogFactory.getLog(ChooseTemplate.class);

    private TRISWebDriver fwd ;
    private TRISBrowser browser;

    public ChangePage(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browsers.getWebDriver();
    }

    protected void nextPageButton1() {
        //return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.CHANGE_PAGE, "nextpage1")));
        browser.getInternalWebDriver().findElementByXPath(ModuleField.getFieldValue(ModuleConst.CHANGE_PAGE, "nextpage1")).click();
    }

    protected FluentWebElement nextPageButtonN() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.CHANGE_PAGE, "nextpageN")));
    }

    protected FluentWebElement lastPageButton1() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.CHANGE_PAGE, "lastpage1")));
    }

    protected FluentWebElement lastPageButtonN() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.CHANGE_PAGE, "lastpageN")));
    }

    protected FluentWebElement firstPageButton1() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.CHANGE_PAGE, "firstpage1")));
    }

    protected FluentWebElement firstPageButtonN() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.CHANGE_PAGE, "firstpageN")));
    }
}
