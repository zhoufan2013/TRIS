package com.ai.upc.common;

import com.ai.core.TRISBrowser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * Veris UPC MessageBox 事件捕捉
 *
 * Created by zhoufan on 15/5/5.
 */
public class MessageBox {

    private static transient Log _log = LogFactory.getLog(MessageBox.class);

    private TRISBrowser browser;
    private RemoteWebDriver driver ;

    public MessageBox(TRISBrowser browser) {
        this.browser = browser;
        this.driver = browser.getInternalWebDriver();
    }

    protected void okSuccessMsg() {

        driver.findElement(By.xpath("//*[@id=\"wade_msg_ct\"]/div[4]/a[1]")).click();

        /*
        List<WebElement> elements = delegate.findElements(By.className("c_submit").tagName("a"));
        for(WebElement element : elements) {
            if(StringUtils.equals(element.getAttribute("tag"), "ok")) {
                element.click();
            }
        }*/
    }
}
