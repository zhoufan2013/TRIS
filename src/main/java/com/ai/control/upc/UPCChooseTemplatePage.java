package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.control.upc.offer.UPCOfferEditUIPage;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.upc.common.ChooseTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoufan on 15/5/6.
 */
public class UPCChooseTemplatePage {

    private TRISBrowser browser;

    public UPCChooseTemplatePage() {}

    public UPCChooseTemplatePage(TRISBrowser browser) {
        this.browser = browser;
    }

    /**
     * 创建指定服务模板
     * @param tempId 模板ID
     * @return 服务模块交互编排
     */
    public UPCServiceEditUIPage chooseServiceTemplate(String tempId) {
        templateChoose(tempId);
        return PageFactory.initPage(browser, UPCServiceEditUIPage.class);
    }

    /**
     * 创建指定产品模板
     * @param tempId 模板ID
     * @return 产品模块交互编排
     */
    public UPCProductEditUIPage chooseProductTemplate(String tempId) {
        templateChoose(tempId);
        return PageFactory.initPage(browser, UPCProductEditUIPage.class);
    }

    /**
     * 创建指定策划模板
     * @param tempId 模板ID
     * @return 策划模块交互编排
     */
    public UPCOfferEditUIPage chooseOfferTemplate(String tempId) {
        templateChoose(tempId);
        return PageFactory.initPage(browser, UPCOfferEditUIPage.class);
    }

    private WebElement locateTemplateChooseFrame() {
        return browser.getInternalWebDriver().findElementByXPath(ElementXPath.TEMPLATE_CHOOSE_FRAME);
    }


    private void templateChoose(final String tempId) {
        new ChooseTemplate(browser.getWebDriver()){{
            browser.enterFrame(locateTemplateChooseFrame());
            browser.input(templateId(), tempId);
            browser.click(queryTemplate());
            chooseSpecifiedTemplate(allRows(), tempId);
            browser.click(chooseTemplateButton());
        }};
    }

    protected void chooseSpecifiedTemplate(List<WebElement> allRows, String specifiedTemplateId) {
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if(cells.get(0).getText().equals(specifiedTemplateId)) {
                cells.get(0).click();
                break;
            }
        }
    }

    protected List<WebElement> allRows() {
        browser.pause(1000);//todo hehe
        List<WebElement> allRows = browser.getInternalWebDriver().findElements(By.xpath(ElementXPath.TEMPLATE_CHOOSE_ALLROWS));
        return allRows;
    }

}
