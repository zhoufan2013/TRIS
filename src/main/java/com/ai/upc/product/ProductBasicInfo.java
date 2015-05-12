package com.ai.upc.product;

import com.ai.config.ElementXPath;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.BasePage;
import com.ai.util.UPCUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

/**
 * Created by zhoufan on 15/5/12.
 */
public class ProductBasicInfo extends BasePage{


    public static class Contants {
        public static String TITLE = "Service Specification Management";
    }

    private static transient Log _log = LogFactory.getLog(ProductBasicInfo.class);

    public ProductBasicInfo(ChromeDriver delegate) {
        super(delegate);
    }

    /**
     * 定位产品基本信息编辑frame
     */
    protected void locateProductBasicInfoFrame() {
        delegate.switchTo().frame(delegate.findElement(id("detailFrame"))).switchTo().frame(delegate.findElement(id("F-TabsetFrame"))).switchTo().frame(delegate.findElement(id("F-Frame")));
    }

    /**
     * 定位到产品类型树frame
     */
    protected void switchToProductTypeTreeFrame() {
        delegate.switchTo().frame(delegate.findElement(By.xpath(ElementXPath.PRODUCT_TYPE_TREE_FRAME)));
    }

    /**
     * 定位到编辑产品的大frame框
     */
    public void switchToProductEditFrame() {
        delegate.switchTo().frame(UPCUtil.findNavFrame(delegate, "Add Product Specification"));
    }

    /**
     * 定位到产品特征编辑frame框
     */
    protected void switchToProductCharFrame() {
        delegate.switchTo().frame(delegate.findElement(id("detailFrame"))).switchTo().frame(delegate.findElement(id("F-TabsetFrame"))).switchTo().frame(delegate.findElement(id("F-FrameF-1")));
    }

    /**
     * 图形树的产品框
     */
    protected FluentWebElement productNodeCell() {
        return td(xpath(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "productNodeCell")));
    }

    /**
     * 产品名称输入框
     */
    protected FluentWebElement productName() {
        return input(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "productName")));
    }

    /**
     * 产品 Code 输入框，Telenor 没有使用
     */
    protected FluentWebElement productCode() {
        return input(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "productCode")));
    }

    /**
     * 产品类型按钮
     */
    protected FluentWebElement productType() {
        return button(xpath(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "productType")));
    }

    /**
     * 产品描述输入框
     */
    protected FluentWebElement description() {
        return textarea(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "description")));
    }

    /**
     * 提交保存产品按钮
     */
    protected FluentWebElement saveProductButton() {
        return button(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_EDIT_UI, "saveProductButton")));
    }

}
