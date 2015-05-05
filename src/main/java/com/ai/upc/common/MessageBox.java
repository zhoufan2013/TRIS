package com.ai.upc.common;

import com.ai.core.BasePage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import static org.openqa.selenium.By.className;


/**
 * Veris UPC MessageBox 事件捕捉
 *
 * Created by zhoufan on 15/5/5.
 */
public class MessageBox extends BasePage{

    public MessageBox(ChromeDriver delegate) {
        super(delegate);
    }

    protected void okSuccessMsg() {
        FluentWebElements elements = links(className("c_submit").tagName("a"));
        for(FluentWebElement element : elements) {
            if (StringUtils.equals(element.getAttribute("tag").toString(), "ok")) {
                element.click();
            }
        }
    }
}
