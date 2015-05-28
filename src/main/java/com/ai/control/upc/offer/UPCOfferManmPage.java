package com.ai.control.upc.offer;

import com.ai.config.ElementXPath;
import com.ai.config.Menu;
import com.ai.control.upc.UPCChooseTemplatePage;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.upc.enumeration.OfferAction;
import com.ai.upc.offer.OfferManm;
import com.ai.upc.product.ProductManm;
import com.ai.util.TRISUtil;
import com.ai.util.UPCUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    /**
     * 拷贝Offer
     */
    public void copyOffer(String offerId) {
        doAction(offerId, "copy");
    }

    /**
     * 终止Offer
     */
    public void terminateOffer(String offerId) {
        doAction(offerId, "terminate");
    }

    /**
     * 验证Offer
     */
    public void validateOffer(String offerId) {
        doAction(offerId, "validate");
    }

    /**
     * 发布Offer
     */
    public void launchOffer(String offerId) {
        doAction(offerId, "launch");
    }

    public UPCOfferEditUIPage editOffer(String offerId) {
        doAction(offerId, "edit");
        return PageFactory.initPage(browser, UPCOfferEditUIPage.class);
    }
    /**
     * 导出Offer
     */
    public void exportOffer(String offerId) {
        doAction(offerId, "export");
    }

    /**
     * 升级批准Offer
     */
    public void updateOffer(String offerId) {
        doAction(offerId, "update");
    }

    /**
     * Offer操作接口
     */
    private void doAction(String offerId, String action) {
        List<WebElement> allRows = TRISUtil.allRows(browser, ElementXPath.OFFER_MANM_QUERY_ALLROWS);
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (StringUtils.equals(cells.get(0).getText(), offerId)) {
                List<WebElement> eles = cells.get(7).findElements(By.tagName("a"));
                for (WebElement ele : eles) {
                    if (ele.getAttribute("onclick").equals(OfferAction.convert(action).getFunction())) {
                        ele.click();
                    }
                }
            }
        }
    }

    /**
     * 查询Offer
     */
    public UPCOfferManmPage queryOffer(final String offerId) {
        new OfferManm(browser.getWebDriver()){{
            browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("offer")));
            browser.input(offerIdorName(), offerId);
            browser.click(qryOfferButton());
        }};
        return this;
    }

    /**
     * 新增Offer
     */
    public UPCChooseTemplatePage addOffer() {
        new OfferManm(browser.getWebDriver()){{
            browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("offer")));
            browser.click(createNewOfferButton());
        }};
        return PageFactory.initPage(browser, UPCChooseTemplatePage.class);
    }

}
