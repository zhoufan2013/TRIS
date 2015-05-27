package com.ai.upc.group;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;


/**
 * Created by asiainfo on 2015/5/18.
 */
public class GroupBasicInfo {

    private static transient Log _log = LogFactory.getLog(GroupBasicInfo.class);

    private TRISWebDriver fwd ;
    private TRISBrowser browser;

    public GroupBasicInfo(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browsers.getWebDriver();
    }

    /**
     * offergroup名称输入框
     */
    protected FluentWebElement offerGroupName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "offerGroupName")));
    }

    /**
     * offergroup,mutuallyExclusiveYES
     */
    protected FluentWebElement mutuallyExclusiveYES() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "mutuallyExclusiveYES")));
    }

    /**
     * offergroup,mutuallyExclusiveNO
     */
    protected void mutuallyExclusiveNO() {
        //return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "mutuallyExclusiveNO")));
        browser.getInternalWebDriver().findElementById(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "mutuallyExclusiveNO")).click();
    }

    /**
     * offergroup,mutualConversionTypeDisable
     */
    protected FluentWebElement mutualConversionTypeDisable() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "mutualConversionTypeDisable")));
    }

    /**
     * offergroup,mutualConversionTypeEnabel
     */
    protected void mutualConversionTypeEnabel() {
        //return fwd.select(id(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "mutualConversionTypeEnabel")));
        browser.getInternalWebDriver().findElementById(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "mutualConversionTypeEnabel")).click();
    }

    /**
     * offergroup,mutualConversionTypeEnabelUpwaordConversion
     */
    protected FluentWebElement mutualConversionTypeEnabelUpwaordConversion() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "mutualConversionTypeEnabelUpwaordConversion")));
    }

    /**
     * offergroup,subscribeQuantityRestriction
     */
    protected FluentWebElement subscribeQuantityRestriction() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "subscribeQuantityRestriction")));
    }

    /**
     * offergroup,subscribeQuantityRestriction
     */
    protected FluentWebElement to() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "to")));
    }

    /**
     * offergroup,Description Customer
     */
    protected FluentWebElement descriptionCustomer() {
        return fwd.textarea(id(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "descriptionCustomer")));
    }


    protected void saveOfferGroupButton() {
        //return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "saveOfferGroupButton")));
        browser.getInternalWebDriver().findElementByXPath(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "saveOfferGroupButton")).click();
    }

    protected void addOffersButton() {
        browser.getInternalWebDriver().findElementById(ModuleField.getFieldValue(ModuleConst.OFFERGROUP_EDIT_UI, "addRelatedOffers")).click();
    }
}
