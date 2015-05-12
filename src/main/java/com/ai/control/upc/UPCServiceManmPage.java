package com.ai.control.upc;

import com.ai.config.Menu;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.PageFactory;
import com.ai.upc.login.Login;
import com.ai.upc.service.ServiceManm;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by zhoufan on 15/5/6.
 */
public class UPCServiceManmPage {

    private static transient Log _log = LogFactory.getLog(UPCServiceManmPage.class);

    private ChromeDriver driver;

    public UPCServiceManmPage() {}

    public UPCServiceManmPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        String currTitle = UPCUtil.findPageTitle(driver, "service");
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + currTitle);
        }
        return ServiceManm.Contants.TITLE.equals(currTitle);
    }

    /**
     * 新增
     */
    public UPCChooseTemplatePage addService() {
        driver.switchTo().frame(UPCUtil.findNavFrame(driver, Menu.getMenuName("service")));
        //List<WebElement> wes = driver.findElementsByXPath("//*[@id=\"ServSpecQueryResult\"]/div[1]/ul/li/a");//TODO
        //wes.get(0).click();
        //driver.findElementById("addBtn").click();
        driver.findElementById(ModuleField.getFieldValue(ModuleConst.SERVICEMANM, "createNewServiceButton"));
        return PageFactory.initPage(driver, UPCChooseTemplatePage.class);
    }

    /**
     * 查询
     */
    public void queryService(String serviceIdorName, String servType) {


    }

    /**
     * 删除
     */
    public void deleteService(String serviceId) {

    }

    /**
     * 发布
     */
    public void launchService(String serviceId) {

    }

    /**
     * 编辑
     */
    public void editService(String serviceId) {

    }

}
