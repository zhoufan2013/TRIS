package com.ai.control.upc;

import com.ai.core.PageFactory;
import com.ai.upc.login.Login;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zhoufan on 15/5/6.
 */
public class UPCHomePage {

    private ChromeDriver driver;

    public UPCHomePage() {}

    public UPCHomePage(ChromeDriver driver) {
        this.driver = driver;
    }

    public static UPCHomePage navigate(ChromeDriver driver) {
        driver.get("http://10.10.12.185:8212/UPC-TN-MYSQL-4304-JC/");
        return PageFactory.initPage(driver, UPCHomePage.class);
    }

    public UPCMenuPage login() {
        new Login(driver) {{
            username().clearField().sendKeys("21upc");
            password().clearField().sendKeys("123456");
            login().click();
        }};
        return PageFactory.initPage(driver, UPCMenuPage.class);
    }

}
