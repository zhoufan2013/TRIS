package com.ai.upc.service;

import com.ai.config.ElementXPath;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.BasePage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import java.util.List;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

/**
 * 服务基本信息控件
 *
 * Created by zhoufan on 15/5/3.
 */
public class ServiceBasicInfo extends BasePage{

    private static transient Log _log = LogFactory.getLog(ServiceBasicInfo.class);

    public ServiceBasicInfo(ChromeDriver delegate) {
        super(delegate);
    }

    protected FluentWebElement serviceName() {
        return input(id(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "serviceName")));
    }

    protected FluentWebElement serviceType() {
        return button(xpath(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "serviceType")));
    }

    protected FluentWebElement serviceCatagory() {
        return button(xpath(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "serviceCatagory")));
    }

    protected FluentWebElement serviceDescription() {
        return textarea(id(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "description")));
    }

    protected FluentWebElement saveServiceButton() {
        return button(id(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "saveservicebutton")));
    }

    protected void locateServiceBasicInfoFrame() {
        delegate.switchTo().frame(delegate.findElement(id("F-Frame"))).switchTo().frame(delegate.findElement(id("F-TabsetFrame"))).switchTo().frame(delegate.findElement(id("F-Frame")));
    }

    protected void locateServiceTypeTreeFrame() {
        delegate.switchTo().frame(delegate.findElement(By.xpath(ElementXPath.SERVICE_TYPE_TREE_FRAME)));
    }

    protected void locateSelectServiceCatagoryFrame() {
        delegate.switchTo().frame(delegate.findElement(By.xpath(ElementXPath.SERVICE_CATAGORY_FRAME))).switchTo().frame(delegate.findElement(By.id("selectServiceCatalogFrame")));
    }

    protected FluentWebElement selectConditionIDorName() {
        return input(id(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "selectConditionIDorName")));
    }

    protected FluentWebElement queryServiceCatagoryButton() {
        return button(xpath(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "queryServiceCatagoryButton")));
    }

    protected void chooseSpecifiedServiceCatagory(String specifiedServiceCatagoryId) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            _log.error(e);
        }
        List<WebElement> allRows = selectServiceCatagoryAllRows();
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if(cells.get(1).getText().equals(specifiedServiceCatagoryId)) {
                cells.get(1).click();
                break;
            }
        }
    }

    private List<WebElement> selectServiceCatagoryAllRows() {
        List<WebElement> allRows = delegate.findElements(By.xpath(ElementXPath.SERVICE_CATAGORY_CHOOSE_ALLROWS));
        return allRows;
    }

    protected void confirmSelectServiceCatagory() {
        button(id("btsave")).click();
    }

    protected void cancelSelectServiceCatagory() {
        button(id("btcancel")).click();
    }

    protected FluentWebElement saveServiceSpecification() {
        return button(id("btsave"));
    }

    protected void locateServiceCharFrame() {
        delegate.switchTo().frame(delegate.findElement(id("F-Frame"))).switchTo().frame(delegate.findElement(id("F-TabsetFrame"))).switchTo().frame(delegate.findElement(id("F-FrameF-1")));
    }


}
