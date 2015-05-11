package com.ai.upc.catalog;

import com.ai.core.BasePage;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zhoufan on 15/5/11.
 */
public class CatalogManm extends BasePage{

    public static class Contants {
        public static String TITLE = "Product Catalog Management";
    }

    public CatalogManm(ChromeDriver delegate) {
        super(delegate);
    }
}
