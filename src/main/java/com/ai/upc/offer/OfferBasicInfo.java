package com.ai.upc.offer;

import com.ai.config.ElementXPath;
import com.ai.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 *
 *
 * Created by zhoufan on 15/5/1.
 */
public class OfferBasicInfo extends BasePage {

    public OfferBasicInfo(ChromeDriver delegate) {
        super(delegate);
    }

    protected void locateServiceBasicInfoFrame() {



        List<WebElement> elements = delegate.findElements(By.xpath(ElementXPath.TEMPLATE_CHOOSE_FRAME));
        delegate.switchTo().frame(elements.get(0));
    }


}
