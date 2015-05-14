package com.ai.control.upc;

import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
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

    public UPCProductManmPage() {}

    public UPCProductManmPage(TRISBrowser browser) {
        this.browser = browser;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(browser, "product");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return ProductManm.Contants.TITLE.equals(currTitle);
    }

    /*
    public UPCChooseTemplatePage createNewProduct() {
        new ProductManm(browser){{
            switchToProductManmFrame();
            createNewProduct();
        }};
        //return PageFactory.initPage(driver, UPCChooseTemplatePage.class);
        return null;
    }

    public void queryProduct(final String prodIdorName) {
        new ProductManm(driver){{
            switchToProductManmFrame();
            productIdorName().clearField().sendKeys(prodIdorName);
            driver.findElement(By.xpath("//*[@id=\"ProdSpecQueryForm\"]/div[2]/button")).click();
            //queryProduct().click();

            //TODO
            productIdorName().getText().shouldBe("333");
        }};
    }*/
}
