package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import com.ai.upc.common.chooseOffer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;


import java.util.List;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.By.id;

/**
 * @author tianhj
 * @version 1.0.0
 * @date 2015/5/25
 * @description 该类的功能描述
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2015/5/25      tianhj         1.0.0             initial
 */
public class UPCChooseOfferPage {

    private TRISBrowser browser;
    private TRISWebDriver fwd;

    public UPCChooseOfferPage() {}

    public UPCChooseOfferPage(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browsers.getWebDriver();
    }


    private WebElement locateOfferChooseFrame() {
        return browser.getInternalWebDriver().findElementByXPath(ElementXPath.OFFER_CHOOSE_FRAME);
    }

    public void chooseOffers(final String offerIDs) {
        new chooseOffer(browser){{
            browser.enterFrame(locateOfferChooseFrame());
            browser.input(nameorID(), offerIDs);
            browser.click(queryOffer());
            chooseSpecifiedOffers(allRows(), offerIDs);
            //browser.click(addOffer());
            addOffer();
            browser.click(confirmOffer());
        }};
    }

    protected void chooseSpecifiedOffers(List<WebElement> allRows, String offerIDs) {
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
                cells.get(0).click();
        }
    }

    protected List<WebElement> allRows() {
        browser.pause(1000l);//todo hehe
        List<WebElement> allRows = browser.getInternalWebDriver().findElements(By.xpath(ElementXPath.OFFER_CHOOSE_ALLROWS));
        return allRows;
    }
}
