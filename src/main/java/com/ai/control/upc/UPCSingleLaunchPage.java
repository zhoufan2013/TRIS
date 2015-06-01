package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.core.TRISBrowser;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.name;

import java.util.List;

/**
 * Created by zhoufan on 15/5/21.
 */
public class UPCSingleLaunchPage {

    private TRISBrowser browser;

    public UPCSingleLaunchPage() {}

    public UPCSingleLaunchPage(TRISBrowser browser) {
        this.browser = browser;
    }

    protected void chooseLaunchPath(String envName) {
        browser.enterFrame(browser.getElement(ElementXPath.TEMPLATE_CHOOSE_FRAME));
        List<WebElement> trs = browser.getElements(ElementXPath.SINGLE_LAUNCH_ALLROWS);
        for(WebElement tr : trs) {
            if (StringUtils.equals(pathName(tr), envName)) {
                tr.findElements(By.tagName("td")).get(0).findElement(By.id("areas")).click();
            }
        }
    }

    protected void okLaunch() {
        browser.getWebDriver().div(className("c_submit")).button(name("ok")).click();
    }

    protected void cancelLaunch() {
        browser.getWebDriver().div(className("c_submit")).button(name("cancel")).click();
    }

    private String pathName(WebElement tr) {
        return tr.findElements(By.tagName("td")).get(0).findElement(By.id("Table_Text_areas")).getText();
    }

    public void launchObject(String evename) {
        chooseLaunchPath(evename);
        okLaunch();
    }

}
