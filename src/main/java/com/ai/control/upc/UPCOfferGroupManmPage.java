package com.ai.control.upc;

import com.ai.config.Menu;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.upc.group.GroupManm;
import com.ai.upc.service.ServiceManm;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by asiainfo on 2015/5/19.
 */
public class UPCOfferGroupManmPage {
    private static transient Log _log = LogFactory.getLog(UPCOfferGroupManmPage.class);

    private TRISBrowser browser;

    public UPCOfferGroupManmPage() {}

    public UPCOfferGroupManmPage(TRISBrowser browser) {
        this.browser = browser;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(browser, "offerGroup");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return GroupManm.Contants.TITLE.equals(currTitle);
    }


    /**
     * 新增
     */
    public UPCOfferGroupEditUIPage addOfferGroup() {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("offerGroup")));
        new GroupManm(browser) {{
            createOfferGroupButton();
        }};
        return PageFactory.initPage(browser, UPCOfferGroupEditUIPage.class);
    }



}
