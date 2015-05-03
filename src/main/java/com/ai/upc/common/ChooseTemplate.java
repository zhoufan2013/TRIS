package com.ai.upc.common;

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

/**
 * VerisUPC TemplateChoose.html 公共页面选择数据模板
 *
 * Created by zhoufan on 15/5/3.
 */
public class ChooseTemplate extends BasePage{

    private static transient Log _log = LogFactory.getLog(ChooseTemplate.class);

    public ChooseTemplate(ChromeDriver delegate) {
        super(delegate);
    }

    protected void locateTemplateChooseFrame() {
        List<WebElement> elements = delegate.findElements(By.xpath(ElementXPath.TEMPLATE_CHOOSE_FRAME));
        delegate.switchTo().frame(elements.get(0));
    }

    protected FluentWebElement templateId() {
        return input(id(ModuleField.getFieldValue(ModuleConst.TEMPLATE_CHOOSE_FRAME, "templateId")));
    }

    protected FluentWebElement queryTemplate() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return button(id(ModuleField.getFieldValue(ModuleConst.TEMPLATE_CHOOSE_FRAME, "queryTemplateButton")));
    }

    protected FluentWebElement confirmChooseTemplate() {
        return button(id(ModuleField.getFieldValue(ModuleConst.TEMPLATE_CHOOSE_FRAME, "confirmChooseTemplate")));
    }

    protected FluentWebElement cancelChooseTemplate() {
        return button(id(ModuleField.getFieldValue(ModuleConst.TEMPLATE_CHOOSE_FRAME, "cancelChooseTemplate")));
    }

    protected List<WebElement> allRows() {
        List<WebElement> allRows = delegate.findElements(By.xpath(ElementXPath.TEMPLATE_CHOOSE_ALLROWS));
        return allRows;
    }

    /**
     *
     * @param allRows
     * @param specifiedTemplateId
     */
    protected void chooseSpecifiedTemplate(List<WebElement> allRows, String specifiedTemplateId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            _log.error(e);
        }
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if(cells.get(0).getText().equals(specifiedTemplateId)) {
                cells.get(0).click();
                break;
            }
        }
    }


}
