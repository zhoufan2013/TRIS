package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.ServiceVO;
import com.ai.upc.common.ChooseCharSpec;
import com.ai.upc.common.MessageBox;
import com.ai.upc.common.RadioTree;
import com.ai.upc.service.ServiceBasicInfo;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.id;

/**
 * Created by zhoufan on 15/5/7.
 */
public class UPCServiceEditUIPage {

    private static transient Log _log = LogFactory.getLog(UPCServiceEditUIPage.class);

    private TRISBrowser browser;

    public UPCServiceEditUIPage() {}

    public UPCServiceEditUIPage(TRISBrowser browser) {
        this.browser = browser;
    }

    public void switchToServiceEditFrame() {
        browser.enterFrame(UPCUtil.findNavFrame(browser, "Add Service Specification"));
        //browser.getInternalWebDriver().switchTo().frame(UPCUtil.findNavFrame(browser, "Add Service Specification"));
    }

    protected void switchToServiceBasicInfoFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElementById("F-Frame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-Frame"));
        //browser.getInternalWebDriver().switchTo().frame(browser.getInternalWebDriver().findElement(id("F-Frame"))).switchTo().frame(browser.getInternalWebDriver().findElement(id("F-TabsetFrame"))).switchTo().frame(browser.getInternalWebDriver().findElement(id("F-Frame")));
    }

    protected void switchToServiceTypeTreeFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElement(By.xpath(ElementXPath.SERVICE_TYPE_TREE_FRAME)));
//        browser.getInternalWebDriver().switchTo().frame(browser.getInternalWebDriver().findElement(By.xpath(ElementXPath.SERVICE_TYPE_TREE_FRAME)));
    }

    protected void switchToSelectServiceCatagoryFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElement(By.xpath(ElementXPath.SERVICE_CATAGORY_FRAME))).enterFrame(browser.getInternalWebDriver().findElement(By.id("selectServiceCatalogFrame")));
//        browser.getInternalWebDriver().switchTo().frame(browser.getInternalWebDriver().findElement(By.xpath(ElementXPath.SERVICE_CATAGORY_FRAME))).switchTo().frame(browser.getInternalWebDriver().findElement(By.id("selectServiceCatalogFrame")));
    }

    protected void switchToServiceCharFrame() {
//        browser.getInternalWebDriver().switchTo().frame(browser.getInternalWebDriver().findElement(id("F-Frame"))).switchTo().frame(browser.getInternalWebDriver().findElement(id("F-TabsetFrame"))).switchTo().frame(browser.getInternalWebDriver().findElement(id("F-FrameF-1")));
        browser.enterFrame(browser.getInternalWebDriver().findElement(id("F-Frame"))).enterFrame(browser.getInternalWebDriver().findElement(id("F-TabsetFrame"))).enterFrame(browser.getInternalWebDriver().findElement(id("F-FrameF-1")));
    }

    protected void chooseSpecifiedServiceCatagory(String specifiedServiceCatagoryId) {
        browser.pause(500);
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
        List<WebElement> allRows = browser.getElements(ElementXPath.SERVICE_CATAGORY_CHOOSE_ALLROWS);
//        List<WebElement> allRows = browser.getInternalWebDriver().findElements(By.xpath(ElementXPath.SERVICE_CATAGORY_CHOOSE_ALLROWS));
        return allRows;
    }

    /**
     * 测试创建
     * 正常流
     *
     * @param service
     */
    public void createService(final ServiceVO service) {

        switchToServiceEditFrame();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        browser.getInternalWebDriver().findElement(By.xpath("//*[@id=\"wade_ext_msg_div\"]/div[1]/div[2]/div[2]/div/div[2]/a")).click();//TODO

        //获取当前页面句柄
        final String handler = browser.getInternalWebDriver().getWindowHandle();
        new ServiceBasicInfo(browser.getWebDriver()){{
            //browser.click(confirmTypeButton());
            switchToServiceBasicInfoFrame();

            browser.input(serviceName(), service.getServiceName());
            browser.input(serviceDescription(), service.getDescription());
            browser.click(serviceType());

            //返回父页面
            browser.selectWindow(handler);

            switchToServiceEditFrame();
            switchToServiceTypeTreeFrame();
        }};

        new RadioTree(browser){{
            selectSpecifiedNode(service.getServiceType());
        }};

        browser.enterFrame(browser.getInternalWebDriver().findElementById("navframe_56"));
        new ServiceBasicInfo(browser.getWebDriver()) {{
            switchToServiceBasicInfoFrame();
            browser.getInternalWebDriver().findElement(By.xpath("//*[@id=\"baseinfo\"]/div/ul/li[4]/span[2]/button")).click();
            browser.selectWindow(handler);
            switchToServiceEditFrame();
            switchToSelectServiceCatagoryFrame();
            browser.input(selectConditionIDorName(), service.getServiceCatagory());
            browser.click(queryServiceCatagoryButton());
            chooseSpecifiedServiceCatagory(service.getServiceCatagory());
            browser.click(confirmSelectServiceCatagory());

            switchToServiceEditFrame();
            switchToServiceCharFrame();

        }};

        new ChooseCharSpec(browser){{
            List<CharSpecVO> charSpecs = service.getServChar();
            for(CharSpecVO charSpec : charSpecs) {
                chooseSpecifiedServiceChar(charSpec.getCharSpecId(), charSpec.getCharValue());
            }
        }};

        new ServiceBasicInfo(browser.getWebDriver()) {{
            browser.selectWindow(handler);
            switchToServiceEditFrame();
            browser.click(saveServiceButton());
        }};

        new MessageBox(browser) {{
            okSuccessMsg();
        }};
    }

    public void editService(final ServiceVO service) {
        new ServiceBasicInfo(browser.getWebDriver()) {{
            browser.leaveFrame();
            browser.enterFrame(UPCUtil.findNavFrameIncludeID(browser,service.getServiceId()));
            browser.click(saveServiceButton());
        }};

        new MessageBox(browser) {{
            okSuccessMsg();
        }};
    }

    public void verifyCreatedService() {
        System.out.println("verifying ... ");
    }

}
