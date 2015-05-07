package com.ai.control.upc;

import com.ai.config.Menu;
import com.ai.core.PageFactory;
import com.ai.util.UPCUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by zhoufan on 15/5/6.
 */
public class UPCServiceManmPage {

    private ChromeDriver driver;

    public UPCServiceManmPage() {}

    public UPCServiceManmPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public UPCChooseTemplatePage addService() {
        driver.switchTo().frame(UPCUtil.findNavFrame(driver, Menu.getMenuName("service")));
        List<WebElement> wes = driver.findElementsByXPath("//*[@id=\"ServSpecQueryResult\"]/div[1]/ul/li/a");//TODO
        wes.get(0).click();
        return PageFactory.initPage(driver, UPCChooseTemplatePage.class);
    }

    public void queryService(String serviceIdorName, String servType) {


    }

    public void deleteService(String serviceId) {

    }

    public void launchService(String serviceId) {

    }

    public void editService(String serviceId) {

    }

}
