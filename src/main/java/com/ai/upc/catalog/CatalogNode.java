package com.ai.upc.catalog;

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
public class CatalogNode {

    private TRISBrowser browser ;

    private TRISWebDriver fwd;

    public CatalogNode(TRISBrowser browser) {
        this.browser = browser;
        this.fwd = browser.getWebDriver();
    }
}
