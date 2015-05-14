package com.ai.control.upc;

import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import com.ai.upc.common.ChooseMenu;
import com.ai.upc.login.Login;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

/**
 * Veris UPC 登录页面控制
 *
 * Created by zhoufan on 15/5/6.
 */
public class UPCHomePage {

    private static transient Log _log = LogFactory.getLog(UPCHomePage.class);

    private static TRISBrowser browser;

    public UPCHomePage() {}

    public UPCHomePage(TRISBrowser browser) {
        this.browser = browser;
    }

    public static UPCHomePage navigate(TRISBrowser browser) {
        browser.open("http://10.10.12.185:8212/UPC-TN-MYSQL-4304-JC/");
//        browser.open("http://10.10.12.151:8666/ALUPC-3307/");
        return PageFactory.initPage(browser, UPCHomePage.class);
    }

    public boolean login(final String username, final String password) {
        new Login(browser.getWebDriver()) {{
            browser.input(username(), username);
            browser.input(password(), password);
            browser.click(login());
        }};
        return StringUtils.equals(ChooseMenu.Contants.TITLE, browser.getCurrentTitle());
    }

    public UPCMenuPage login() {
        new Login(browser.getWebDriver()) {{
            browser.input(username(), "21upc");
            browser.input(password(), "123456");
            browser.click(login());
        }};
        return PageFactory.initPage(browser, UPCMenuPage.class);
    }

    public boolean isLoaded() {
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + browser.getCurrentTitle());
        }
        return Login.Contants.TITLE.equals(browser.getCurrentTitle());
    }

}
