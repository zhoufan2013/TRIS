package com.ai.control.upc;

import com.ai.control.upc.offer.UPCOfferManmPage;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.upc.common.ChooseMenu;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhoufan on 15/5/6.
 */
public class UPCMenuPage {

    private static TRISBrowser browser;

    public UPCMenuPage() {}

    public UPCMenuPage(TRISBrowser browser) {
        this.browser = browser;
    }

    public UPCServiceManmPage chooseServiceMenu() {
        browser.leaveFrame();
        new ChooseMenu(browser.getWebDriver()){{
            browser.click(productServiceCatalog());
            browser.click(serviceModule());
        }};
        return PageFactory.initPage(browser, UPCServiceManmPage.class);
    }

    public UPCOfferManmPage chooseOfferMenu() {
        browser.leaveFrame();
        new ChooseMenu(browser.getWebDriver()){{
            browser.pause(2l, TimeUnit.SECONDS);
            browser.click(productServiceCatalog());
            browser.click(offerModule());
        }};
        return PageFactory.initPage(browser, UPCOfferManmPage.class);
    }

    public UPCProductManmPage chooseProductMenu() {
        browser.leaveFrame();
        new ChooseMenu(browser.getWebDriver()) {{
            browser.click(productServiceCatalog());
            browser.click(productModule());
        }};
        return PageFactory.initPage(browser, UPCProductManmPage.class);
    }

    public UPCCatalogManmPage chooseCatalogMenu() {
        browser.leaveFrame();
        new ChooseMenu(browser.getWebDriver()) {{
            browser.click(productServiceCatalog());
            browser.click(catalogModule());
        }};
        return PageFactory.initPage(browser, UPCCatalogManmPage.class);
    }

    public UPCGroupManmPage chooseGroupMenu() {
        browser.leaveFrame();
        new ChooseMenu(browser.getWebDriver()) {{
            browser.click(productServiceCatalog());
            browser.click(groupModule());
        }};
        return PageFactory.initPage(browser, UPCGroupManmPage.class);
    }

    public void chooseReleaseMenu() {
        browser.leaveFrame();
        new ChooseMenu(browser.getWebDriver()){{
            browser.click(productLifecycle());
            browser.click(releaseModule());
        }};
    }

    public UPCReleaseLogPage chooseReleaseLogMenu() {
        browser.leaveFrame();
        new ChooseMenu(browser.getWebDriver()) {{
            browser.click(productLifecycle());
            browser.click(releaseLogModule());
        }};
        return PageFactory.initPage(browser, UPCReleaseLogPage.class);
    }

}