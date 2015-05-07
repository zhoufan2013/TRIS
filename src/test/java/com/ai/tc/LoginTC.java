package com.ai.tc;

import com.ai.control.TRIS;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Veris UPC Login Test Case
 *
 * Created by zhoufan on 15/5/7.
 */
public class LoginTC {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = TRIS.init();
    }

    @After
    public void tearDown() {
        //driver.close();
        driver.quit();
    }

    @Test
    public void testUPCHomePageloading() {

    }

    @Test
    public void testLoginWithNullUserName() {

    }

    @Test
    public void testLoginWithNullPwd() {

    }


    @Test
    public void testLoginOne() {

    }

    @Test
    public void testLoginTwo() {

    }

    @Test
    public void testLoginThree() {

    }

    @Test
    public void testLoginFour() {
        Assert.assertTrue(1>2);
    }

    @Test
    public void testLoginFive() {

    }






}
