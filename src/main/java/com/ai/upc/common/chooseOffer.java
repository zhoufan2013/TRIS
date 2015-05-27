package com.ai.upc.common;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

/**
 * @author tianhj
 * @version 1.0.0
 * @date 2015/5/25
 * @description 该类的功能描述
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2015/5/25      tianhj         1.0.0             initial
 */
public class chooseOffer {

    private static transient Log _log = LogFactory.getLog(ChooseTemplate.class);

    private TRISWebDriver fwd ;
    private TRISBrowser browser;

    public chooseOffer(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browsers.getWebDriver();
    }


    protected FluentWebElement nameorID() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFER_CHOOSE_FRAME, "offerNameorID")));
    }

    protected FluentWebElement queryOffer() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFER_CHOOSE_FRAME, "query")));
    }

    protected void addOffer() {
        //return fwd.button(id(ModuleField.getFieldValue(ModuleConst.OFFER_CHOOSE_FRAME, "add")));
        browser.getInternalWebDriver().findElementById(ModuleField.getFieldValue(ModuleConst.OFFER_CHOOSE_FRAME, "add")).click();
    }

    protected FluentWebElement deleteOffer() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFER_CHOOSE_FRAME, "delete")));
    }

    protected FluentWebElement confirmOffer() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.OFFER_CHOOSE_FRAME, "OK")));
    }
}
