package com.ai.control.upc;

import com.ai.control.upc.offer.UPCOfferManmPage;
import com.ai.core.PageFactory;
import com.ai.upc.common.ChooseMenu;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.jvm.hotspot.debugger.Page;

/**
 * Created by zhoufan on 15/5/6.
 */
public class UPCMenuPage {

    private ChromeDriver driver;

    public UPCMenuPage() {}

    public UPCMenuPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public UPCServiceManmPage chooseServiceMenu() {
        driver.switchTo().defaultContent();
        new ChooseMenu(driver){{
            enterProductServiceCatalog();
            enterServiceModule();
        }};
        return PageFactory.initPage(driver, UPCServiceManmPage.class);
    }

    public UPCOfferManmPage chooseOfferMenu() {
        driver.switchTo().defaultContent();
        new ChooseMenu(driver){{
           enterProductServiceCatalog();
           enterOfferModule();
        }};
        return PageFactory.initPage(driver, UPCOfferManmPage.class);
    }

    public UPCProductManmPage chooseProductMenu() {
        driver.switchTo().defaultContent();
        new ChooseMenu(driver) {{
            enterProductServiceCatalog();
            enterProductModule();
        }};
        return PageFactory.initPage(driver, UPCProductManmPage.class);
    }

    public UPCCatalogManmPage chooseCatalogMenu() {
        driver.switchTo().defaultContent();
        new ChooseMenu(driver) {{
            enterProductServiceCatalog();
            enterCatalogModule();
        }};
        return PageFactory.initPage(driver, UPCCatalogManmPage.class);
    }

    public UPCGroupManmPage chooseGroupMenu() {
        driver.switchTo().defaultContent();
        new ChooseMenu(driver) {{
            enterProductServiceCatalog();
            enterGroupModule();
        }};
        return PageFactory.initPage(driver, UPCGroupManmPage.class);
    }



}
