package com.ai.control.upc;

import com.ai.config.Menu;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.PageFactory;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.OfferGroupVO;
import com.ai.upc.common.ChooseCharSpec;
import com.ai.upc.common.MessageBox;
import com.ai.upc.common.RadioTree;
import com.ai.upc.group.GroupBasicInfo;
import com.ai.upc.service.ServiceManm;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import java.util.List;

import static org.openqa.selenium.By.id;

/**
 * Created by asiainfo on 2015/5/19.
 */
public class UPCOfferGroupEditUIPage {

    private static transient Log _log = LogFactory.getLog(UPCOfferGroupEditUIPage.class);

    private TRISBrowser browser;

    public UPCOfferGroupEditUIPage() {}

    public UPCOfferGroupEditUIPage(TRISBrowser browser) {
        this.browser = browser;
    }




    public void switchToOfferGroupEditFrame() {
        browser.leaveFrame();
        browser.enterFrame(UPCUtil.findNavFrame(browser, "Product Offering Group Management"));
    }


    protected void switchToOfferGroupBasicInfoFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElementById("F-Frame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-Frame"));
        //browser.getInternalWebDriver().switchTo().frame(browser.getInternalWebDriver().findElement(id("F-Frame"))).switchTo().frame(browser.getInternalWebDriver().findElement(id("F-TabsetFrame"))).switchTo().frame(browser.getInternalWebDriver().findElement(id("F-Frame")));
    }

    public void switchToRelatedOfferEditFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElement(id("F-Frame"))).enterFrame(browser.getInternalWebDriver().findElement(id("F-TabsetFrame"))).enterFrame(browser.getInternalWebDriver().findElement(id("F-FrameF-0")));
    }

    protected UPCChooseOfferPage addOffers() {
        switchToRelatedOfferEditFrame();
        new GroupBasicInfo(browser) {{
            addOffersButton();
        }};
        return PageFactory.initPage(browser, UPCChooseOfferPage.class);
    }


    public void createOfferGroup(final OfferGroupVO offerGroup) {

        switchToOfferGroupEditFrame();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获取当前页面句柄
        final String handler = browser.getInternalWebDriver().getWindowHandle();
        new GroupBasicInfo(browser){{
            switchToOfferGroupBasicInfoFrame();

            browser.input(offerGroupName(), offerGroup.getOfferGroupName());
            browser.input(subscribeQuantityRestriction(), offerGroup.getSubscribeQuantityRestriction());
            browser.input(to(),offerGroup.getTo());
            mutualConversionTypeEnabel();
            mutuallyExclusiveNO();
            browser.input(descriptionCustomer(),offerGroup.getDescriptionCustomer());

        }};

        new GroupBasicInfo(browser) {{
            browser.selectWindow(handler);
            switchToOfferGroupEditFrame();

            /*选择关联offer*/
            UPCChooseOfferPage chooseOfferPage = addOffers();
            switchToOfferGroupEditFrame();
            chooseOfferPage.chooseOffers("95042552");

            switchToOfferGroupEditFrame();
            saveOfferGroupButton();
        }};


        new MessageBox(browser) {{
            okSuccessMsg();
        }};
    }
}
