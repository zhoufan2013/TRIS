package com.ai.upc.release;

import com.ai.config.ElementXPath;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.openqa.selenium.By.*;

/**
 * Created by zhoufan on 15/5/21.
 */
public class ReleaseLog {

    public static class Contants {
        public static String TITLE = "Service Specification Management";
    }

    private static transient Log _log = LogFactory.getLog(ReleaseLog.class);

    private TRISWebDriver fwd ;

    public ReleaseLog(TRISWebDriver delegate) {
        this.fwd = delegate;
    }

    protected FluentWebElement objectId() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.RELEASE_LOG, "objectId")));
    }

    protected FluentWebElement queryButton() {
        return fwd.div(className("submit")).button(name("queryButton"));
    }

    protected FluentWebElements newTr() {
        return fwd.trs(className("new"));
    }

    /**
     * 查询出的发布结果中第一个加号
     * TODO 还要优化
     */
    protected WebElement foldItem() {

//        return fwd.trs(xpath(ElementXPath.RELEASE_LOG_ALLROWS)).get(0).tds().get(0).links().get(0);
        return null;
    }

}
