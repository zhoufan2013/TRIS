package com.ai.control.upc;

import com.ai.config.Menu;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import com.ai.upc.catalog.CatalogManm;
import com.ai.upc.product.ProductManm;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

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

    public void queryProduct(final String prodIdorName) {
        new ProductManm(driver){{
            switchToProductManmFrame();
            browser.input(productIdorName(), prodIdorName);
            //productIdorName().clearField().sendKeys(prodIdorName);
            browser.click(queryProduct());
            //driver.findElement(By.xpath("//*[@id=\"ProdSpecQueryForm\"]/div[2]/button")).click();
            //queryProduct().click();
            //TODO
            //productIdorName().getText().shouldBe("333");
        }};
    }
}
