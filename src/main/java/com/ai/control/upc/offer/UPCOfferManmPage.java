package com.ai.control.upc.offer;

import com.ai.core.TRISBrowser;
import com.ai.upc.offer.OfferManm;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhoufan on 15/5/10.
 */
public class UPCOfferManmPage {

    private static transient Log _log = LogFactory.getLog(UPCOfferManmPage.class);

    private TRISBrowser browser;

    public UPCOfferManmPage() {}

    public UPCOfferManmPage(TRISBrowser browser) {
        this.browser = browser;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(browser, "offer");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return OfferManm.Contants.TITLE.equals(currTitle);
    }
}
