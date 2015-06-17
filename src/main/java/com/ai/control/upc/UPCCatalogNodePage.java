package com.ai.control.upc;

import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;

/**
 * @author tianhj
 * @version 1.0.0
 * @date 2015/6/17
 * @description 该类的功能描述
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2015/6/17      tianhj         1.0.0             initial
 */
public class UPCCatalogNodePage {
    private TRISBrowser browser;
    private TRISWebDriver fwd;

    public UPCCatalogNodePage() {}

    public UPCCatalogNodePage(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browsers.getWebDriver();
    }
}
