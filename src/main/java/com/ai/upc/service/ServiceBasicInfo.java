package com.ai.upc.service;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

/**
 * 服务基本信息控件
 *
 * Created by zhoufan on 15/5/3.
 */
public class ServiceBasicInfo {

    public static class Contants {
        public static String TITLE = "Service Specification Management";
    }

    private static transient Log _log = LogFactory.getLog(ServiceBasicInfo.class);

    private TRISWebDriver fwd ;

    public ServiceBasicInfo(TRISWebDriver delegate) {
        this.fwd = delegate;
    }

    protected FluentWebElement confirmTypeButton() {
        return fwd.link(xpath("//*[@id=\"wade_ext_msg_div\"]/div[1]/div[2]/div[2]/div/div[2]/a"));
    }

    protected FluentWebElement serviceName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "serviceName")));
    }

    protected FluentWebElement serviceType() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "serviceType")));
    }

    protected FluentWebElement serviceCatagory() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "serviceCatagory")));
    }

    protected FluentWebElement serviceDescription() {
        return fwd.textarea(id(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "description")));
    }

    protected FluentWebElement saveServiceButton() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "saveservicebutton")));
    }

    protected FluentWebElement selectConditionIDorName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "selectConditionIDorName")));
    }

    protected FluentWebElement queryServiceCatagoryButton() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.SERVICE_EDIT_UI, "queryServiceCatagoryButton")));
    }

    /*
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
    }*/

    protected FluentWebElement confirmSelectServiceCatagory() {
        return fwd.button(id("btsave"));
    }

    protected void cancelSelectServiceCatagory() {
        fwd.button(id("btcancel"));
    }

    protected FluentWebElement saveServiceSpecification() {
        return fwd.button(id("btsave"));
    }

    /*
    protected void locateServiceCharFrame() {
        delegate.switchTo().frame(delegate.findElement(id("F-Frame"))).switchTo().frame(delegate.findElement(id("F-TabsetFrame"))).switchTo().frame(delegate.findElement(id("F-FrameF-1")));
    }*/

}
