package com.ai.upc.common;

import com.ai.config.Menu;
import com.ai.core.BasePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.Period;

import static org.openqa.selenium.By.linkText;

/**
 * Veris UPC 系统所有菜单项控制
 *
 * //TODO maybe move this class into ./control
 *
 * Created by zhoufan on 15/5/1.
 */
public class ChooseMenu extends BasePage{

    public ChooseMenu(ChromeDriver delegate) {
        super(delegate);
    }

    protected void enterProductServiceCatalog() {
        link(linkText(Menu.getMenuName("productservicecatalog"))).click();
    }

    protected void enterOfferModule() {
        link(linkText(Menu.getMenuName("offer"))).click();
    }

    protected void enterProductModule() {
        link(linkText(Menu.getMenuName("product"))).click();
    }

    protected void enterServiceModule() {
        link(linkText(Menu.getMenuName("service"))).click().within(Period.secs(5));
    }
}
