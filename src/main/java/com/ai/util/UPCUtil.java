package com.ai.util;

import com.ai.config.Menu;
import com.ai.core.TRISBrowser;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

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

    public static String findPageTitle(TRISBrowser browser, String menuName) {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName(menuName)));
        return browser.getText(browser.getElement("/html/body/div[1]/div[1]/div/div[1]/div[1]"));
    }

}
