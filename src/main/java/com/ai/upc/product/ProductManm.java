package com.ai.upc.product;

import com.ai.core.BasePage;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zhoufan on 15/5/10.
 */
public class ProductManm extends BasePage{

    public static class Contants {
        public static String TITLE = "Product Offering Management";
    }

    public ProductManm(ChromeDriver delegate) {
        super(delegate);
    }

}
