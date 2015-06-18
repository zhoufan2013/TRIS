package com.ai.control.upc;

import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import com.ai.upc.bean.CatalogVO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

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
    private static RemoteWebDriver driver;

    public UPCCatalogNodePage() {}

    public UPCCatalogNodePage(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browsers.getWebDriver();
    }

    public void newCatalogNode(CatalogVO catalog) {

        browser.leaveFrame();
        List<WebElement> elements = browser.getInternalWebDriver().findElementsByTagName("iframe");
        for(WebElement element : elements) {
            if (element.getAttribute("src").contains("page/upc.product.RelatedProductOfferingNode&catalogId=30086&m=81000013&p=upc.product.RelatedProductOfferingNode")) {

                element.click();
                browser.enterFrame(element);
                break;
            }
        }
        driver = browser.getInternalWebDriver();
        Actions action = new Actions(driver);
        action.contextClick(browser.getInternalWebDriver().findElementById("j1_1_anchor"));
    }
}
