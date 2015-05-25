package com.ai.tc;

import com.ai.config.AssertConst;
import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.control.upc.UPCHomePage;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.LoginVO;
import com.ai.config.TestCaseUtil;
import org.apache.commons.lang3.StringUtils;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.*;

import java.text.MessageFormat;


/**
 * @author zhoufan
 * @version 1.0.0
 * @date 15/5/7
 * @description Veris UPC 登录功能自动化case
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/5/7       zhoufan         1.0.0              initial
 *
 */
public class LoginTC {

    private TRISBrowser browser;
    private LoginVO loginInfo;

    @BeforeClass(alwaysRun = true)
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        loginInfo = ExcelReader.init(ExcelConst.XLSX_PATH).readLoginInfo();
    }

    @AfterClass(alwaysRun = true)
    private void tearDown() {
        browser.quit();
    }

    /**
     * 测试页面是否正常加载
     */
    @Test(groups = {"functest", "checkintest", "medium"})
    public void testUPCHomePageloading() {
        assertTrue(UPCHomePage.navigate(browser).isLoaded());
    }

    /**
     * 测试 username 为空是否正常登录
     */
    @Test(groups = {"functest", "medium"})
    public void testLoginWithNullUserName() {
        assertTrue(!UPCHomePage.navigate(browser).login(StringUtils.EMPTY, loginInfo.getPassword()));
    }

    /**
     * 测试 password 为空是否正常登录
     */
    @Test(groups = {"functest", "medium"})
    public void testLoginWithNullPwd() {
        assertTrue(!UPCHomePage.navigate(browser).login(loginInfo.getUsername(), StringUtils.EMPTY));
    }

    /**
     * login with password pattern #1 {123456}
     */
    @Test(groups = {"functest", "checkintest", "medium"})
    public void testLoginOne() {
        assertEquals(UPCHomePage.navigate(browser).login(loginInfo.getUsername(), loginInfo.getPassword()), true, MessageFormat.format(AssertConst.PASSWORD_PATTERN, "1"));
    }

    /**
     * login with password pattern #2 {123  456}
     */
    @Test(groups = {"functest", "high"})
    public void testLoginTwo() {
        assertEquals(UPCHomePage.navigate(browser).login(loginInfo.getUsername(), TestCaseUtil.pwdPattern2(loginInfo.getPassword())), true, MessageFormat.format(AssertConst.PASSWORD_PATTERN, "2"));
    }

    /**
     * login with password pattern #3 {  123456}
     */
    @Test(groups = {"functest", "high"})
    public void testLoginThree() {
        assertEquals(UPCHomePage.navigate(browser).login(loginInfo.getUsername(), TestCaseUtil.pwdPattern3(loginInfo.getPassword())), true, MessageFormat.format(AssertConst.PASSWORD_PATTERN, "3"));
    }

    /**
     * login with password pattern #4 {123456  }
     */
    @Test(groups = {"functest", "high"})
    public void testLoginFour() {
        assertEquals(UPCHomePage.navigate(browser).login(loginInfo.getUsername(), TestCaseUtil.pwdPattern4(loginInfo.getPassword())), true, MessageFormat.format(AssertConst.PASSWORD_PATTERN, "4"));
    }

}
