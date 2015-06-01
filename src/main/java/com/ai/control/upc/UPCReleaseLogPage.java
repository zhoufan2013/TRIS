package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.config.Menu;
import com.ai.core.TRISBrowser;
import com.ai.upc.release.ReleaseLog;
import com.ai.util.UPCUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.openqa.selenium.By.tagName;
import java.util.List;

/**
 * Created by zhoufan on 15/5/21.
 */
public class UPCReleaseLogPage {

    private static transient Log _log = LogFactory.getLog(UPCReleaseLogPage.class);

    private TRISBrowser browser;

    private boolean checkresult;

    public UPCReleaseLogPage() {}

    public UPCReleaseLogPage(TRISBrowser browser) {
        this.browser = browser;
        checkresult = true;
    }

    /**
     * 定位到发布日志主页面
     */
    private void switchToReleaseLogFrame() {
        browser.enterFrame(UPCUtil.findNavFrame(browser, Menu.getMenuName("releaseLog")));
    }

    public boolean checkLaunchResult(final String objId) {

        switchToReleaseLogFrame();
        boolean result = true;
        new ReleaseLog(browser.getWebDriver()) {{
            browser.input(objectId(), objId);
            browser.click(queryButton());

            List<WebElement> trs = browser.getElements(ElementXPath.RELEASE_LOG_ALLROWS);
            trs.get(0).findElements(tagName("td")).get(0).findElements(tagName("a")).get(0).click();

//            browser.click(foldItem());TODO 待优化

            FluentWebElements fwes = newTr();
            for(int i=0; fwes.size()>i; i++) {
                if ("Failure".equals(fwes.get(i).tds().get(5).getText().toString())) {
                    checkresult = false;
                    break;
                }
            }
        }};

        if (checkresult == false) {
            return false;
        } else {
            return true;
        }
    }

    @Deprecated
    private boolean checkResult(String result) {
        if (StringUtils.equals(result, "Successful")) {
            return Boolean.TRUE;
        }else if (StringUtils.equals(result, "Failure")) {
            return Boolean.FALSE;
        }
        return Boolean.FALSE;
    }

}
