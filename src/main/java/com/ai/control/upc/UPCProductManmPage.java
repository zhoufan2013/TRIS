package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.config.Menu;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import com.ai.upc.catalog.CatalogManm;
import com.ai.upc.product.ProductBasicInfo;
import com.ai.upc.product.ProductManm;
import com.ai.util.TRISUtil;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoufan on 15/5/10.
 */
public class UPCProductManmPage {

    private static transient Log _log = LogFactory.getLog(UPCProductManmPage.class);

    private TRISBrowser browser;

    private TRISWebDriver driver;

    public UPCProductManmPage() {}

    public UPCProductManmPage(TRISBrowser browser) {
        this.browser = browser;
        this.driver = browser.getWebDriver();
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(browser, "product");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return ProductManm.Contants.TITLE.equals(currTitle);
    }

    /**
     * 定位到查询产品主页面frame
     */
    private void switchToProductManmFrame() {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("product")));
    }


    public UPCChooseTemplatePage addProduct() {
        new ProductManm(driver){{
            browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("product")));
            browser.click(createProductButton());
        }};
        return PageFactory.initPage(browser, UPCChooseTemplatePage.class);
    }

    public UPCProductManmPage queryProduct(final String prodIdorName) {
        new ProductManm(driver){{
            switchToProductManmFrame();
            browser.input(productIdorName(), prodIdorName);
            browser.click(queryProduct());

            //TODO
            //productIdorName().getText().shouldBe("333");
        }};
        return this;
    }

    public UPCProductEditUIPage editProduct(String productId) {
        chooseSpecifiedProduct(productId);
        return PageFactory.initPage(browser, UPCProductEditUIPage.class);
    }

    public UPCProductEditUIPage launchProduct(String productId) {
        List<WebElement> allRows = TRISUtil.allRows(browser, ElementXPath.PRODUCT_MANM_QUERY_ALLROWS);
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<WebElement> actions = cells.get(6).findElements(By.tagName("a"));
            for(WebElement action : actions) {
                if (action.getAttribute("onclick").equals("launch($(this))") && action.getAttribute("itemid").equals(productId)) {
                    action.click();
                    break;
                }
            }
        }

        new ProductBasicInfo(browser){{
            browser.getElement("//*[@id=\"wade_msg_ct\"]/div[4]/a[1]").click();//TODO
        }};

        new UPCSingleLaunchPage(browser){{
            chooseLaunchPath("CRM_SR0.3.2_dev");
            okLaunch();
        }};

        return PageFactory.initPage(browser, UPCProductEditUIPage.class);
    }

    public void deleteProduct() {

    }

    protected void chooseSpecifiedProduct(String specifiedTemplateId) {
        List<WebElement> allRows = TRISUtil.allRows(browser, ElementXPath.PRODUCT_MANM_QUERY_ALLROWS);
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<WebElement> actions = cells.get(6).findElements(By.tagName("a"));
            for(WebElement action : actions) {
                if (action.getAttribute("onclick").equals("updInfo($(this))") && action.getAttribute("itemid").equals(specifiedTemplateId)) {
                    String prodTitleName = new StringBuilder().append(action.getAttribute("itemname")).append("[").append(action.getAttribute("itemid")).append("]").toString();
                    action.click();
                    browser.leaveFrame();
                    browser.enterFrame(UPCUtil.findNavFrame(browser, prodTitleName));
                    break;
                }
            }
        }
    }
}
