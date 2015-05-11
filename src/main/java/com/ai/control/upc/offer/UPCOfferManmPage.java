package com.ai.control.upc.offer;

import com.ai.upc.offer.OfferManm;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zhoufan on 15/5/10.
 */
public class UPCOfferManmPage {

    private static transient Log _log = LogFactory.getLog(UPCOfferManmPage.class);

    private ChromeDriver driver;

    public UPCOfferManmPage() {}

    public UPCOfferManmPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(driver, "offer");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return OfferManm.Contants.TITLE.equals(currTitle);
    }
}
