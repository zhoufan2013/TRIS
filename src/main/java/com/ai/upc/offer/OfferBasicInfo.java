package com.ai.upc.offer;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;

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
     * 采集Offer类型输入框
     */
    protected FluentWebElement offerType() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFER_EDIT_UI, "offerTypeName")));
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
    
    /**
     * 产品关联服务查询条件的输入框
     */
    protected FluentWebElement productId() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFER_ADD_PRODUCT, "prodId")));
    }
    
    protected FluentWebElement queryProductButton() {
        return fwd.button(name(ModuleField.getFieldValue(ModuleConst.OFFER_ADD_PRODUCT, "queryPruductButton")));
    }
    
    /**
     * 确认已选择的服务关联到产品下
     */
    public FluentWebElement addProductOKButton() {
        return fwd.div(className("c_submit")).button(name("submitBtn"));
    }
    
    /**
     * 采集offerRelType描述输入框
     */
    protected FluentWebElement offerRelType() {
        return fwd.select(id(ModuleField.getFieldValue(ModuleConst.OFFER_ASSOCIATIONS, "offerRelType")));
    }
    
    /**
     * 采集offerId描述输入框
     */
    protected FluentWebElement offerNameOrId() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.OFFER_ASSOCIATIONS, "nameOrId")));
    }
    
    /**
     * 采集offerId描述输入框
     */
    protected FluentWebElement queryOfferAssociation() {
        return fwd.button(name(ModuleField.getFieldValue(ModuleConst.OFFER_ASSOCIATIONS, "queryOfferAssociation")));
    }
    
    /**
     * 校验offer类型
     */
    public boolean isVerifyOfferType(TRISBrowser browser,String offerType) {
    	String offerTypeName = browser.getValue(offerType().getWebElement());
    	if(offerType.equals(offerTypeName)){
    		return true;
    	}
        return false;
    }
    
    /**
     * 选择offer到右框
     */
    public FluentWebElement addOfferRightButton() {
        return fwd.link(id(ModuleField.getFieldValue(ModuleConst.OFFER_ASSOCIATIONS, "addOfferRightButton")));
    }

    /**
     *选择offer到左框
     */
    public FluentWebElement addOfferLeftButton() {
        return fwd.link(id(ModuleField.getFieldValue(ModuleConst.OFFER_ASSOCIATIONS, "addOfferLeftButton")));
    }
    
    /**
    *提交添加关联offer按钮
    */
   public FluentWebElement addOfferSubButton() {
       return fwd.button(id(ModuleField.getFieldValue(ModuleConst.OFFER_ASSOCIATIONS, "submitOffer")));
   }
   
   /**
    * 提交保存offer信息按钮
    */
   protected FluentWebElement saveOfferButton() {
       return fwd.button(id(ModuleField.getFieldValue(ModuleConst.OFFER_EDIT_UI, "saveBtn")));
   }
}
