package com.ai.upc.product;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

/**
 * 产品规格主查询页面
 *
 * Created by zhoufan on 15/5/10.
 */
public class ProductManm {

    public static class Contants {
        public static String TITLE = "Product Specification Management";
    }

    private TRISWebDriver fwd ;

    public ProductManm(TRISWebDriver delegate) {
        this.fwd = delegate;
    }

    /**
     * 新增产品按钮
     */
    protected FluentWebElement createProductButton() {
        return fwd.link(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_MANM, "createNewProductButton")));
    }

    /**
     * 查询产品按钮
     */
    protected FluentWebElement queryProduct() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_MANM, "queryProduct")));
    }

    /**
     * 产品ID和名称输入框
     */
    protected FluentWebElement productIdorName() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.PRODUCT_MANM, "prodIdorName")));
    }

}
