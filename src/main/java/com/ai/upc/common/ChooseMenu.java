package com.ai.upc.common;

import com.ai.config.Menu;
import com.ai.core.TRISWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.linkText;

/**
 * Veris UPC 系统所有菜单项控制
 *
 * //TODO maybe move this class into ./control
 *
 * Created by zhoufan on 15/5/1.
 */
public class ChooseMenu {

    public static class Contants {
        public static String TITLE = "Base";
    }

    private TRISWebDriver fwd ;

    public ChooseMenu(TRISWebDriver delegate) {
        this.fwd = delegate;
    }

    protected FluentWebElement productServiceCatalog() {
        return fwd.link(linkText(Menu.getMenuName("productservicecatalog")));
    }

    protected FluentWebElement offerModule() {
        return fwd.link(linkText(Menu.getMenuName("offer")));
    }

    protected FluentWebElement productModule() {
        return fwd.link(linkText(Menu.getMenuName("product")));
    }

    protected FluentWebElement serviceModule() {
        return fwd.link(linkText(Menu.getMenuName("service")));
    }

    protected FluentWebElement catalogModule() {
        return fwd.link(linkText(Menu.getMenuName("catalog")));
    }

    protected FluentWebElement groupModule() {
        return fwd.link(linkText(Menu.getMenuName("group")));
    }

    protected FluentWebElement productLifecycle() {
        return fwd.link(linkText(Menu.getMenuName("productlifecycle")));
    }

    protected FluentWebElement releaseModule() {
        return fwd.link(linkText(Menu.getMenuName("release")));
    }

    protected FluentWebElement releaseLogModule() {
        return fwd.link(linkText(Menu.getMenuName("releaseLog")));
    }

}