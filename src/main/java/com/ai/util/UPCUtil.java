package com.ai.util;

import com.ai.config.Menu;
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
     * @param driver
     * @param navtabTitle
     * @return
     */
    public static String findNavFrame(WebDriver driver, String navtabTitle) {
        List<WebElement> elements = driver.findElement(By.id("tab_ct_tr")).findElements(By.tagName("td"));
        for (WebElement element : elements) {
            if (element.getAttribute("title").equals(navtabTitle)) {
                String idx = element.getAttribute("idx");
                //Assert.assertThat();
                return new StringBuilder().append("navframe_").append(idx).toString();
            }
        }
        return StringUtils.EMPTY;
    }

    public static String findPageTitle(WebDriver driver, String menuName) {
        driver.switchTo().frame(UPCUtil.findNavFrame(driver, Menu.getMenuName(menuName)));
        return driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div[1]")).getText();
    }

}
