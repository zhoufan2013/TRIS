package com.ai.upc.offer;

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
 * @author zhoufan
 * @version 1.0.0
 * @date 15/5/24
 * @description Offer基本信息页的控件收集
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/5/24      zhoufan         1.0.0              initial
 */
public class OfferBasicInfo {

    private static transient Log _log = LogFactory.getLog(OfferBasicInfo.class);

    private TRISWebDriver fwd ;

    public OfferBasicInfo(TRISBrowser browser) {
        this.fwd = browser.getWebDriver();
    }

    /**
     * 图形树的策划名称框
     */
    protected FluentWebElement offerNodeCell() {
        return fwd.td(xpath(ModuleField.getFieldValue(ModuleConst.OFFER_EDIT_UI, "OfferNodeCell")));
    }

    /**
     * 采集Offer名称输入框
     */
    protected FluentWebElement offerName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFER_EDIT_UI, "offerName")));
    }

    /**
     * 采集OfferCode输入框
     */
    protected FluentWebElement offerCode() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFER_EDIT_UI, "offerCode")));
    }

    /**
     * 采集客户描述输入框
     */
    protected FluentWebElement description() {
        return fwd.textarea(id(ModuleField.getFieldValue(ModuleConst.OFFER_EDIT_UI, "desc")));
    }

    /**
     * 采集内部描述输入框
     */
    protected FluentWebElement internalDescription() {
        return fwd.textarea(id(ModuleField.getFieldValue(ModuleConst.OFFER_EDIT_UI, "interalDesc")));
    }



}
