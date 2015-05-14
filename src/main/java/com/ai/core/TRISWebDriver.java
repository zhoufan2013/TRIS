package com.ai.core;

import com.ai.config.LogConst;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.monitors.CompositeMonitor;
import org.seleniumhq.selenium.fluent.monitors.HighlightOnError;
import org.seleniumhq.selenium.fluent.monitors.ScreenShotOnError;

/**
 * 基础控件层使用
 *
 * 1、引入Java统计度量工具 Metrics @link{https://dropwizard.github.io/metrics/3.1.0/}
 * 2、Error自动截图
 * 3、高亮页面Error
 * Created by zhoufan on 15/4/30.
 */
public class TRISWebDriver extends FluentWebDriver {

    private static transient Log _log = LogFactory.getLog(TRISWebDriver.class);

    public TRISWebDriver(RemoteWebDriver driver) {
        super(driver,
                new CompositeMonitor(WholeSuiteListener.codahaleMetricsMonitor,
                        new HighlightOnError(driver),
                        new ScreenShotOnError.WithUnitTestFrameWorkContext(driver, TRISWebDriver.class, LogConst.AUTO_SCREENSHOT_PATH_FROM, LogConst.AUTO_SCREENSHOT_PATH_TO
                        )));
    }



}
