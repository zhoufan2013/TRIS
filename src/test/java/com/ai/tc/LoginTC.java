package com.ai.tc;

import com.ai.config.AssertConst;
import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.TRIS;
import com.ai.control.upc.UPCHomePage;
import com.ai.upc.bean.LoginVO;
import com.ai.config.TestCaseUtil;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.MessageFormat;


/**
 * Veris UPC Login Test Case
 *
 * Created by zhoufan on 15/5/7.
 */
public class LoginTC {

    private static ChromeDriver driver;
    private static LoginVO loginInfo;

    @BeforeClass
    private void setup() {
        /*TRIS 系统初始化*/
        driver = TRIS.init();
        /*Excel 输入初始化*/
        loginInfo = ExcelReader.init(ExcelConst.XLSX_PATH).readLoginInfo();
    }

    @AfterClass
    private void tearDown() {
        driver.close();
        driver.quit();
    }

    /**
     * 测试页面是否正常加载
     */
    @Test
    public void testUPCHomePageloading() {
        assertTrue(UPCHomePage.navigate(driver).isLoaded());
    }

    /**
     * 测试 username 为空是否正常登录
     */
    @Test
    public void testLoginWithNullUserName() {
        assertTrue(!UPCHomePage.navigate(driver).login(StringUtils.EMPTY, loginInfo.getPassword()));
    }

    /**
     * 测试 password 为空是否正常登录
     */
    @Test
    public void testLoginWithNullPwd() {
        assertTrue(!UPCHomePage.navigate(driver).login(loginInfo.getUsername(), StringUtils.EMPTY));
    }

    /**
     * login with password pattern #1 {123456}
     */
    @Test
    public void testLoginOne() {
        assertEquals(UPCHomePage.navigate(driver).login(loginInfo.getUsername(), loginInfo.getPassword()), true, MessageFormat.format(AssertConst.PASSWORD_PATTERN, "1"));
    }

    /**
     * login with password pattern #2 {123  456}
     */
    @Test
    public void testLoginTwo() {
        assertEquals(UPCHomePage.navigate(driver).login(loginInfo.getUsername(), TestCaseUtil.pwdPattern2(loginInfo.getPassword())), true, MessageFormat.format(AssertConst.PASSWORD_PATTERN, "2"));
    }

    /**
     * login with password pattern #3 {  123456}
     */
    @Test
    public void testLoginThree() {
        assertEquals(UPCHomePage.navigate(driver).login(loginInfo.getUsername(), TestCaseUtil.pwdPattern3(loginInfo.getPassword())), true, MessageFormat.format(AssertConst.PASSWORD_PATTERN, "3"));
    }

    /**
     * login with password pattern #4 {123456  }
     */
    @Test
    public void testLoginFour() {
        assertEquals(UPCHomePage.navigate(driver).login(loginInfo.getUsername(), TestCaseUtil.pwdPattern4(loginInfo.getPassword())), true, MessageFormat.format(AssertConst.PASSWORD_PATTERN, "4"));
    }

}
