package com.ai.control.upc;

import com.ai.core.TRISBrowser;
import com.ai.upc.catalog.CatalogManm;
import com.ai.upc.product.ProductManm;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zhoufan on 15/5/10.
 */
public class UPCCatalogManmPage {

    private static transient Log _log = LogFactory.getLog(UPCCatalogManmPage.class);

    private TRISBrowser browser;

    public UPCCatalogManmPage() {}

    public UPCCatalogManmPage(TRISBrowser browser) {
        this.browser = browser;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(browser, "catalog");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return CatalogManm.Contants.TITLE.equals(currTitle);
    }
}
