package com.ai.control.upc;

import com.ai.control.upc.offer.UPCOfferManmPage;
import com.ai.core.PageFactory;
import com.ai.upc.common.ChooseMenu;
import org.openqa.selenium.chrome.ChromeDriver;

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
        new ChooseMenu(driver){{
            enterProductServiceCatalog();
            enterServiceModule();
        }};
        return PageFactory.initPage(driver, UPCServiceManmPage.class);
    }

    public UPCOfferManmPage chooseOfferMenu() {
        new ChooseMenu(driver){{
           enterProductServiceCatalog();
           enterOfferModule();
        }};
        return PageFactory.initPage(driver, UPCOfferManmPage.class);
    }

    public UPCProductManmPage chooseProductMenu() {
        new ChooseMenu(driver) {{
            enterProductServiceCatalog();
            enterProductModule();
        }};
        return PageFactory.initPage(driver, UPCProductManmPage.class);
    }

    public void chooseCatalogMenu() {
        new ChooseMenu(driver) {{
            enterProductServiceCatalog();
            enterCatalogModule();
        }};
    }

    public void chooseGroupMenu() {
        new ChooseMenu(driver) {{
            enterProductServiceCatalog();
            enterGroupModule();
        }};
    }



}
