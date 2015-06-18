package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.config.Menu;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.CatalogVO;
import com.ai.upc.catalog.CatalogManm;
import com.ai.upc.enumeration.OfferAction;
import com.ai.upc.product.ProductManm;
import com.ai.util.TRISUtil;
import com.ai.util.UPCUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

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

    /**
     * 查询
     */
    public void queryCatalog(final String nameorID) {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("catalog")));
        new CatalogManm(browser) {{
            browser.input(catalogIdorName(),nameorID);
            queryCatalogButton();
        }};
    }

    /**
     * add
     */
    public UPCCatalogEditUIPage createCatalog() {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("catalog")));
        new CatalogManm(browser) {{
            createCatalogButton();
        }};
        return PageFactory.initPage(browser, UPCCatalogEditUIPage.class);
    }


    public UPCCatalogNodePage editCatalogNode(String catalogId) {
        doAction(catalogId, "editCatalogNode");
        return PageFactory.initPage(browser, UPCCatalogNodePage.class);
    }

    private void doAction(String catalogId, String action) {
        List<WebElement> allRows = TRISUtil.allRows(browser, ElementXPath.CATALOG_MANM_QUERY_ALLROWS);
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (StringUtils.equals(cells.get(0).getText(), catalogId)) {
                List<WebElement> eles = cells.get(3).findElements(By.tagName("a"));
                for (WebElement ele : eles) {
                    if (ele.getAttribute("onclick").equals(OfferAction.convert(action).getFunction())) {
                        ele.click();
                    }
                }
            }
        }
    }
}
