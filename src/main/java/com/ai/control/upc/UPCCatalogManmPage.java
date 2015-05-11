package com.ai.control.upc;

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

    private ChromeDriver driver;

    public UPCCatalogManmPage() {}

    public UPCCatalogManmPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(driver, "catalog");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return ProductManm.Contants.TITLE.equals(currTitle);
    }
}
