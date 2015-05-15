package com.ai.upc.product;

import com.ai.config.ElementXPath;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

/**
 * 产品规格主编辑页面
 *
 * Created by zhoufan on 15/5/12.
 */
public class ProductBasicInfo {

    private static transient Log _log = LogFactory.getLog(ProductBasicInfo.class);

    private TRISWebDriver fwd ;

    public ProductBasicInfo(TRISWebDriver delegate) {
        this.fwd = delegate;
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

}
