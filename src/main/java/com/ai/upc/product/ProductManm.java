package com.ai.upc.product;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

/**
 * 产品规格主查询页面
 *
 * Created by zhoufan on 15/5/10.
 */
public class ProductManm extends TRISWebDriver {

    public static class Contants {
        public static String TITLE = "Product Specification Management";
    }

    public ProductManm(ChromeDriver delegate) {
        super(delegate);
    }

    /**
     * 新增产品按钮
     */
    protected FluentWebElement addNewProduct() {
        return button(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_MANM, "createNewProductButton")));
    }

    /**
     * 查询产品按钮
     */
    protected FluentWebElement queryProduct() {
        return button(xpath(ModuleField.getFieldValue(ModuleConst.PRODUCT_MANM, "queryProduct")));
    }

    /**
     * 产品ID和名称输入框
     */
    protected FluentWebElement productIdorName() {
        return input(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_MANM, "prodIdorName")));
    }

    /**
     * 定位到查询产品主页面frame
     */
    protected void switchToProductManmFrame() {
//        delegate.switchTo().frame(UPCUtil.findNavFrame(delegate, Menu.getMenuName("product")));
    }



}
