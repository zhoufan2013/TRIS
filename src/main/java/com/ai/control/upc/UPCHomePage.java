package com.ai.control.upc;

import com.ai.core.PageFactory;
import com.ai.upc.common.ChooseMenu;
import com.ai.upc.login.Login;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zhoufan on 15/5/6.
 */
public class UPCHomePage {

    private static transient Log _log = LogFactory.getLog(UPCHomePage.class);

    private ChromeDriver driver;

    public UPCHomePage() {}

    public UPCHomePage(ChromeDriver driver) {
        this.driver = driver;
    }

    public static UPCHomePage navigate(ChromeDriver driver) {
        //driver.get("http://10.10.12.151:8666/ALUPC-3307/");
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


    public boolean login(final String username, final String password) {
        new Login(driver) {{
            username().clearField().sendKeys(username);
            password().clearField().sendKeys(password);
            login().click();
        }};
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ChooseMenu.Contants.TITLE.equals(driver.getTitle());
    }

    public boolean isLoaded() {
        if (_log.isDebugEnabled()) {
            _log.info("Current Page Title is " + driver.getTitle());
        }
        return Login.Contants.TITLE.equals(driver.getTitle());
    }

}
