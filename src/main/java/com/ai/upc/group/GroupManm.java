package com.ai.upc.group;

import com.ai.core.BasePage;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zhoufan on 15/5/11.
 */
public class GroupManm extends BasePage{

    public static class Contants {
        public static String TITLE = "Product Offering Group Management";
    }

    public GroupManm(ChromeDriver delegate) {
        super(delegate);
    }

}
