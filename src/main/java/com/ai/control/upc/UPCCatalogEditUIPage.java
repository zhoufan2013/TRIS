package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.config.Menu;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import com.ai.upc.bean.CatalogVO;
import com.ai.upc.catalog.CatalogEdit;
import com.ai.upc.common.MessageBox;
import com.ai.util.UPCUtil;
import org.openqa.selenium.WebElement;

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
public class UPCCatalogEditUIPage {

    private TRISBrowser browser;
    private TRISWebDriver fwd;

    public UPCCatalogEditUIPage() {}

    public UPCCatalogEditUIPage(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browsers.getWebDriver();
    }

    private WebElement locateEditCatalogFrame() {
        return browser.getInternalWebDriver().findElementByXPath(ElementXPath.CATALOG_EDIT_FRAME);
    }

    public void createCatalog(final CatalogVO catalog) {
        browser.leaveFrame();
        browser.enterFrame(UPCUtil.findNavFrame(browser, "Product Catalog"));
        new CatalogEdit(browser) {{
            browser.enterFrame(locateEditCatalogFrame());
            browser.enterFrame(browser.getInternalWebDriver().findElementById("F-Frame"));
            browser.enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrame"));
            browser.enterFrame(browser.getInternalWebDriver().findElementById("F-Frame"));
            browser.input(name(), catalog.getCatalogName());
            browser.input(description(),catalog.getDescription());
            browser.leaveFrame();
            browser.enterFrame(UPCUtil.findNavFrame(browser, "Product Catalog"));
            browser.enterFrame(locateEditCatalogFrame());
            submit();
        }};

        new MessageBox(browser) {{
            okSuccessMsg();
        }};
    }
}
