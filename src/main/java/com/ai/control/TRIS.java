package com.ai.control;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * TRIS 系统设定
 *
 * Created by zhoufan on 15/5/6.
 */
public class TRIS {

    public static ChromeDriver init() {
        System.setProperty("webdriver.chrome.driver", "/Users/zhoufan/chromedriver");
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

}
