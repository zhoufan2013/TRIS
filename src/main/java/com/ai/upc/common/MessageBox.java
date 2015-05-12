package com.ai.upc.common;

import com.ai.core.BasePage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import java.util.List;

import static org.openqa.selenium.By.className;


/**
 * Veris UPC MessageBox 事件捕捉
 *
 * Created by zhoufan on 15/5/5.
 */
public class MessageBox extends BasePage{

    private static transient Log _log = LogFactory.getLog(MessageBox.class);

    public MessageBox(ChromeDriver delegate) {
        super(delegate);
    }

    protected void okSuccessMsg() {

        delegate.findElement(By.xpath("//*[@id=\"wade_msg_ct\"]/div[4]/a[1]")).click();

        /*
        List<WebElement> elements = delegate.findElements(By.className("c_submit").tagName("a"));
        for(WebElement element : elements) {
            if(StringUtils.equals(element.getAttribute("tag"), "ok")) {
                element.click();
            }
        }*/
    }
}
