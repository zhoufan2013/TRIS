package com.ai.control.upc;

import com.ai.core.TRISBrowser;
import com.ai.upc.group.GroupManm;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zhoufan on 15/5/10.
 */
public class UPCGroupManmPage {

    private static transient Log _log = LogFactory.getLog(UPCGroupManmPage.class);

    private TRISBrowser browser;

    public UPCGroupManmPage() {}

    public UPCGroupManmPage(TRISBrowser browser) {
        this.browser = browser;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(browser, "group");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return GroupManm.Contants.TITLE.equals(currTitle);
    }
}
