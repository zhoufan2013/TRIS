package com.ai.control.upc;

import com.ai.config.Menu;
import com.ai.core.PageFactory;
import com.ai.upc.catalog.CatalogManm;
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

    private ChromeDriver driver;

    public UPCProductManmPage() {}

    public UPCProductManmPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(driver, "product");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return CatalogManm.Contants.TITLE.equals(currTitle);
    }

    /**
     * 新增
     */
    public UPCChooseTemplatePage createNewProduct() {
        driver.switchTo().frame(UPCUtil.findNavFrame(driver, Menu.getMenuName("product")));
        //driver.findElementById(ModuleField.getFieldValue(ModuleConst.PRODUCT_MANM, "createNewProductButton"));
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[3]/ul/li/a")).click();
        return PageFactory.initPage(driver, UPCChooseTemplatePage.class);
    }
}
