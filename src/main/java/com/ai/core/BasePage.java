package com.ai.core;

import com.ai.config.LogConst;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.monitors.CompositeMonitor;
import org.seleniumhq.selenium.fluent.monitors.HighlightOnError;
import org.seleniumhq.selenium.fluent.monitors.ScreenShotOnError;

/**
 * 1、引入Java统计度量工具 Metrics @link{https://dropwizard.github.io/metrics/3.1.0/}
 * 2、Error自动截图
 * 3、高亮Error日志
 * Created by zhoufan on 15/4/30.
 */
public class BasePage extends FluentWebDriver{

    public BasePage(FirefoxDriver delegate) {
        super(delegate,
                new CompositeMonitor(WholeSuiteListener.codahaleMetricsMonitor,
                        new HighlightOnError(delegate),
                        new ScreenShotOnError.WithUnitTestFrameWorkContext(delegate, BasePage.class, LogConst.AUTO_SCREENSHOT_PATH_FROM, LogConst.AUTO_SCREENSHOT_PATH_TO
                        )));
    }
}
