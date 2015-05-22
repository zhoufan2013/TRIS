package com.ai.util;

import com.ai.config.Menu;
import com.ai.core.TRISBrowser;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoufan on 15/5/7.
 */
public final class UPCUtil {

    /**
     *
     * @param browser
     * @param navtabTitle
     * @return
     */
    public static String findNavFrame(TRISBrowser browser, String navtabTitle) {
        List<WebElement> elements = browser.getInternalWebDriver().findElement(By.id("tab_ct_tr")).findElements(By.tagName("td"));
        for (WebElement element : elements) {
            if (element.getAttribute("title").equals(navtabTitle)) {
                String idx = element.getAttribute("idx");
                //Assert.assertThat();
                return new StringBuilder().append("navframe_").append(idx).toString();
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     *
     * @param browser
     * @return
     */
    public static String findOnPageName(TRISBrowser browser) {
        List<WebElement> elements =browser.getElements("//*[@id=\"tab_ct_tr\"]/td");
        for(WebElement element : elements) {
            if (element.getAttribute("class").equals("on")) {
                return element.getAttribute("title");
            }
        }
        return StringUtils.EMPTY;
    }

    public static String findPageTitle(TRISBrowser browser, String menuName) {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName(menuName)));
        return browser.getText(browser.getElement("/html/body/div[1]/div[1]/div/div[1]/div[1]"));
    }

}
