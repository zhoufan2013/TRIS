package com.ai.upc.product;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.*;

/**
 * 产品规格主编辑页面
 *
 * Created by zhoufan on 15/5/12.
 */
public class ProductBasicInfo {

    private static transient Log _log = LogFactory.getLog(ProductBasicInfo.class);

    private TRISWebDriver fwd ;

    public ProductBasicInfo(TRISBrowser browser) {
        this.fwd = browser.getWebDriver();
    }

    /**
     * 图形树的产品框
     */
    protected FluentWebElement productNodeCell() {
        return fwd.td(xpath(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "productNodeCell")));
    }

    /**
     * 产品名称输入框
     */
    protected FluentWebElement productName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "productName")));
    }

    /**
     * 产品 Code 输入框，Telenor 没有使用
     */
    protected FluentWebElement productCode() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "productCode")));
    }

    /**
     * 产品类型按钮
     */
    protected FluentWebElement productType() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "productType")));
    }

    /**
     * 产品描述输入框
     */
    protected FluentWebElement description() {
        return fwd.textarea(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "description")));
    }

    /**
     * 提交保存产品按钮
     */
    protected FluentWebElement saveProductButton() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "saveProductButton")));
    }

    /**
     * 产品规格提交保存成功提示框
     */
    protected FluentWebElement okMsg() {
        return fwd.div(xpath("//*[@id=\"wade_msg_ct\"]/div[1]/div[1]"));
    }

    /**
     * 产品关联服务查询条件的输入框
     */
    protected FluentWebElement serviceId() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_ADD_SERVICE, "serviceId")));
    }

    /**
     * 产品关联服务查询条件的输入框
     */
    protected FluentWebElement serviceName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_ADD_SERVICE, "serviceName")));
    }

    /**
     * 产品关联服务搜索按钮
     */
    protected FluentWebElement queryServiceButton() {
        return fwd.button(name(ModuleField.getFieldValue(ModuleConst.PRODUCT_ADD_SERVICE, "queryServiceButton")));
    }

    /**
     * 选择服务到右框
     */
    public FluentWebElement addServiceRightButton() {
        return fwd.link(xpath(ModuleField.getFieldValue(ModuleConst.PRODUCT_ADD_SERVICE, "addServiceRightButton")));
    }

    /**
     *
     */
    public FluentWebElement addServiceLeftButton() {
        return fwd.link(xpath(ModuleField.getFieldValue(ModuleConst.PRODUCT_ADD_SERVICE, "addServiceLeftButton")));
    }

    /**
     * 确认已选择的服务关联到产品下
     */
    public FluentWebElement addServiceOKButton() {
        return fwd.div(className("c_submit")).button(name("submitBtn"));
    }

    /**
     *
     */
    public FluentWebElement addRelProductButton() {
        return fwd.link(id("baddrow"));
    }

    /**
     * 确认发布按钮
     */
    public FluentWebElement confirmLauchButton() {
//        return fwd.link(xpath(ModuleField.getFieldValue(ModuleConst.SINGLE_LAUNCH, "confirmLauchButton")));
        return fwd.link(xpath("//*[@id=\"wade_msg_ct\"]/div[4]/a[1]"));
    }

    /**
     * 查询关联产品的IdorName框
     */
    protected FluentWebElement qryRelatedProductIdOrName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_RELATED_PRODUCT, "IdorName")));
    }

    /**
     * 查询关联产品的query按钮
     */
    protected FluentWebElement qryRelatedProductButton() {
        return fwd.button(xpath(ModuleField.getFieldValue(ModuleConst.PRODUCT_RELATED_PRODUCT, "qryBtn")));
    }

    /**
     * 确认已选择的产品关联到产品下
     */
    public FluentWebElement addProductOKButton() {
        return fwd.div(className("c_submit")).button(name("submitBtn"));
    }

}
