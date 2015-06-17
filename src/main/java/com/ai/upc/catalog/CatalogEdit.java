package com.ai.upc.catalog;

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
 * @date 2015/6/15
 * @description 该类的功能描述
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2015/6/15      tianhj         1.0.0             initial
 */
public class CatalogEdit {
    private static transient Log _log = LogFactory.getLog(CatalogEdit.class);

    private TRISWebDriver fwd ;
    private TRISBrowser browser;

    public CatalogEdit(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browsers.getWebDriver();
    }

    protected FluentWebElement name() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.CATALOG_EDIT, "name")));
    }

    protected FluentWebElement type() {
        return fwd.select(id(ModuleField.getFieldValue(ModuleConst.CATALOG_EDIT, "type")));
    }

    protected FluentWebElement description() {
        return fwd.textarea(id(ModuleField.getFieldValue(ModuleConst.CATALOG_EDIT, "des")));
    }

    protected void submit() {
        //return fwd.button(id(ModuleField.getFieldValue(ModuleConst.CATALOG_EDIT,"submit")));
        browser.getInternalWebDriver().findElementById(ModuleField.getFieldValue(ModuleConst.CATALOG_EDIT,"submit")).click();
    }

    protected FluentWebElement cancel() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.CATALOG_EDIT,"cancel")));
    }
}
