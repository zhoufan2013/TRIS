package com.ai.core;

import com.ai.config.LogConst;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.testng.Assert;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * 1、引入Java统计度量工具 Metrics @link{https://dropwizard.github.io/metrics/3.1.0/}<br>
 * 2、Error自动截图<br>
 * 3、高亮页面Error<br>
 * 4、支持多浏览器 (1)Chrome (2)IE (3)FireFox<br>
 * 5、重新封装 Web UI interaction API<br>
 * 6、支持配置操作间隔时间，满足不同人员要求<br>
 *
 * @author zhoufan
 * @version 1.0
 * @date 15/5/13
 */
public class TRISBrowser {

    private static transient Log _log = LogFactory.getLog(TRISBrowser.class);

    private static TRISWebDriver driver;

    private static RemoteWebDriver internalWebDriver;

    private TRISBrowser() {
        setupBrowser(1);
        driver = new TRISWebDriver(internalWebDriver);
    }

    public static TRISBrowser init() {
        return new TRISBrowser();
    }

    public TRISWebDriver getWebDriver() {
        return driver;
    }

    public RemoteWebDriver getInternalWebDriver(){
        return internalWebDriver;
    }

    /**
     *
     * @param time The amount of time to wait.
     * @param unit The unit of measure for {@code time}.
     */
    public void pause(long time, TimeUnit unit) {
        assertTrue(time > 0, LogConst.PAUSE_TIME_LESS_THAN_ZERO);
        try {
            Thread.sleep(unit.toMillis(time));
        } catch (InterruptedException e) {
            _log.error("", e);
        }
        if (_log.isDebugEnabled()) {
            _log.info("TRIS pauses " + unit.toMillis(time) + " " + unit);
        }
    }

    /**
     * 每个操作间隔根据TRIS统一配置
     *
     * @param millis The amount of time to wait.
     */
    public void pause(long millis) {
        pause(millis, TimeUnit.MILLISECONDS);
    }

    /**
     * 打开网页链接
     * //TODO 对 url 作校验
     *
     * @param url
     */
    public void open(String url) {
        pause(1000l);
        internalWebDriver.get(url);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS open " + url);
        }
    }

    /**
     * 退出（关闭当前页面，关闭浏览器）
     */
    public void quit() {
        pause(1000l);
        internalWebDriver.close();
        internalWebDriver.quit();
        if (_log.isDebugEnabled()) {
            _log.info("TRIS quit ... See you soon ...");
        }
    }

    /**
     * 获取当前页面标题
     */
    public String getCurrentTitle() {
       return internalWebDriver.getTitle();
    }

    /**
     * 获取当前窗口句柄
     */
    public String getWindowHandler() {
        return internalWebDriver.getWindowHandle();
    }

    /**
     * 选择窗口
     *
     * @param nameOrHandler
     */
    public void selectWindow(String nameOrHandler) {
        pause(1000l);
        internalWebDriver.switchTo().window(nameOrHandler);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS select window [" + nameOrHandler + "]");
        }
    }

    /**
     * 进入指定iFrame
     *
     * @param element
     */
    public TRISBrowser enterFrame(WebElement element) {
        pause(1000l);
        internalWebDriver.switchTo().frame(element);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS enter frame [" + element.getText() + "]");
        }
        return this;
    }

    public void enterFrame(String nameOrId) {
        pause(1000l);
        internalWebDriver.switchTo().frame(nameOrId);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS enter frame [" + nameOrId + "]");
        }
    }

    /**
     * Selects either the first frame on the page, or the main document when a page contains
     * iframes.
     */
    public void leaveFrame() {
        pause(1000l);
        internalWebDriver.switchTo().defaultContent();
        if (_log.isDebugEnabled()) {
            _log.info("TRIS leaves current Frame ...");
        }
    }

    public String getText(WebElement element) {
        pause(1000l);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS get text [" + element.getText() + "]");
        }
        return element.getText();

    }

    /**
     *
     * @param xpath
     * @return
     */
    public WebElement getElement(String xpath) {
        pause(1000l);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS finds element on [" + xpath + "]");
        }
        return internalWebDriver.findElementByXPath(xpath);
    }

    /**
     *
     * @param xpath
     * @return
     */
    public List<WebElement> getElements(String xpath) {
        pause(1l, TimeUnit.SECONDS);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS try to find elements on [" + xpath + "]");
        }
        return internalWebDriver.findElementsByXPath(xpath);
    }

    /**
     * //TODO
     */
    public void select(FluentWebElement element, String option) {
        Select select = new Select(element.getWebElement());
        select.selectByVisibleText(option);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS select " + option);
        }
    }

    //TODO
    public void refresh() {

    }

    /**
     * Click When Ready
     *
     * @param element
     */
    public void click(FluentWebElement element) {
        //pause(1000l);
        WebDriverWait wait = new WebDriverWait(internalWebDriver, TimeUnit.MILLISECONDS.toSeconds(10000l));//TODO
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(element.getWebElement()));
        pause(1l, TimeUnit.SECONDS);
        webElement.click();
        if (_log.isDebugEnabled()) {
            _log.info("TRIS click " + element.getText());
        }
    }

    /**
     * 先清空，再输入
     *
     * @param element
     * @param text
     */
    public void input(FluentWebElement element, String text) {
        pause(1l, TimeUnit.SECONDS);
        element.clearField().sendKeys(text);
        //element.sendKeys(text);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS input " + text);
        }
    }

    /**
     * 数据验证
     * Fluent Selenium API #shouldBe 自动处理Assert
     *
     * @param element
     * @param expected
     */
    public void verify(FluentWebElement element, String expected) {
        element.getText().shouldBe(expected);
        if (_log.isDebugEnabled()) {
            _log.info("TRIS verify " + element.getText());
        }
    }


    /**
     * [VerisUPC] 图形树Hover交互
     * @param xpath1
     * @param xpath2
     * @param xpath3
     */
    public void upcHTreeHover(String xpath1, String xpath2, String xpath3) {

        pause(1l, TimeUnit.SECONDS);

        Actions builder = new Actions(internalWebDriver);
        WebElement w1 = this.getElement(xpath1);
        builder.moveToElement(w1);

        WebElement w2 = this.getElement(xpath2);
        builder.moveToElement(w2);

        WebElement w3 = this.getElement(xpath3);
        builder.moveToElement(w3);
        builder.click();
        builder.perform();
    }

    /**
     * 获取VerisUPC弹窗
     * @param xpath
     * @return
     */
    public WebElement switchToUPCAlert(String xpath) {
        pause(1l, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(internalWebDriver, TimeUnit.MILLISECONDS.toSeconds(1000l));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath(xpath)));
        _log.info(element.getText());
        return element;
    }

    public void mouseOver(String xpath) {
        pause(1l, TimeUnit.SECONDS);

        Actions builder = new Actions(internalWebDriver);
        WebElement we = this.getElement(xpath);
        builder.moveToElement(we);

        WebElement w2 = this.getElement("//*[@id=\"product\"]/span/table/tbody/tr/td[3]");
        builder.moveToElement(w2);

        WebElement w = this.getElement("//*[@id=\"product\"]/span/table/tbody/tr/td[3]/div/div[3]/div/div/div[2]/ul/li[2]/a");
        builder.moveToElement(w);
        builder.click();
        builder.perform();

        /*
        Firefox and IE require multiple cycles, more than twice, to cause a
        hovering effect
        if (GlobalSettings.browserCoreType == 1
                || GlobalSettings.browserCoreType == 3) {
            for (int i = 0; i < 5; i++) {
                Actions builder = new Actions(browserCore);
                builder.moveToElement(we).build().perform();
            }
            logger.info("Mouseover " + xpath);
            return;
        }*/
    }

    private void setupBrowser(int browserType) {
        switch (browserType) {
            case 1 : chromeDriver();break;
            case 2 : ieDriver();break;
            case 3 : firefoxDriver();break;
            default: incorrectBrowserType();
        }
    }

    private void chromeDriver() {
        /*ChromeDriverService chromeService = new ChromeDriverService.Builder().usingDriverExecutable(new File("/Users/zhoufan/chromedriver")).build();
        try {
            chromeService.start();
        } catch (IOException e) {
            _log.error(MessageFormat.format(LogConst.START_ERROR, "ChromeDriver"), e);
        }*/
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        //capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName("chrome");
        URL url = null;
        try {
            url = new URL("http://10.11.17.238:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        internalWebDriver = new RemoteWebDriver(url, capabilities);
        internalWebDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        internalWebDriver.manage().window().maximize();
        if (_log.isDebugEnabled()) {
            _log.info(MessageFormat.format(LogConst.BROWSER_USE_LOG, "Chrome"));
        }
    }

    private void firefoxDriver() {
        internalWebDriver = new FirefoxDriver();
        if (_log.isDebugEnabled()) {
            _log.info(MessageFormat.format(LogConst.BROWSER_USE_LOG, "Firefox"));
        }
    }

    private void ieDriver() {
        System.setProperty("webdriver.ie.driver", "123123123123");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        //capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, Boolean.TRUE);
        internalWebDriver = new InternetExplorerDriver(capabilities);
        internalWebDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        internalWebDriver.manage().window().maximize();
        if (_log.isDebugEnabled()) {
            _log.info(MessageFormat.format(LogConst.BROWSER_USE_LOG, "Internet Explorer"));
        }
    }

    private void incorrectBrowserType() {
        Assert.fail(LogConst.INCORRECT_BROWSER);
    }

}
