package com.ai.upc.common;

import static org.openqa.selenium.By.className;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ai.config.ElementXPath;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.ChannelVO;
import com.ai.upc.bean.LocationVO;
import com.ai.upc.bean.SegmentVO;

/**
 *
 *
 * Created by zhoufan on 15/5/4.
 */
public class RadioTree {

    private TRISBrowser browser;
    private RemoteWebDriver driver ;

    public RadioTree(TRISBrowser browser) {
        this.browser = browser;
        this.driver = browser.getInternalWebDriver();
    }

    protected void selectSpecifiedNode(String nodeName) {
        recursive(driver.findElement(By.id(ElementXPath.RADIO_TREE_ROOT_NODE)), nodeName);
        browser.click(browser.getWebDriver().button(className("c_submit").tagName("button")));

        //button(className("c_submit").tagName("button")).click();
        //delegate.findElement(By.className("c_submit")).findElement(By.tagName("button")).click();
    }

    private void recursive(WebElement rootNode, String nodeName) {
        List<WebElement> childNodes = rootNode.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for(WebElement childNode : childNodes) {
            String id = childNode.getAttribute("id");
            String status = childNode.getAttribute("class");
            if (cutOffNodeName(id).equals(nodeName)) {
                childNode.findElement(By.tagName("input")).click();
            } else if (status.equals("fold")) {
                childNode.findElement(By.tagName("a")).click();
                recursive(childNode, nodeName);
            }
        }
    }

    private String cutOffNodeName(String nodeName) {
        return nodeName.substring(nodeName.lastIndexOf("●")+1, nodeName.length());
    }

    public  void selectOfferChannel(List<ChannelVO> channelVOs) {
    	browser.getElement("//*[@id=\"checkTree○0\"]/a[2]/span").click();
    	for (int i = 0; i < channelVOs.size(); i++) {
    		 recursive(driver.findElement(By.id("checkTree○0")), channelVOs.get(i).getChannelId());
		}
        browser.click(browser.getWebDriver().button(className("c_submit").tagName("button")));
    }
    
    public  void selectOfferLocation(List<LocationVO> locationVO) {
    	browser.getElement("//*[@id=\"checkTree○1000000\"]/a[2]/span").click();
    	for (int i = 0; i < locationVO.size(); i++) {
    		 recursive(driver.findElement(By.id("checkTree○1000000")), locationVO.get(i).getLocationId());
		}
        browser.click(browser.getWebDriver().button(className("c_submit").tagName("button")));
    }
    
    public  void selectOfferSegment(List<SegmentVO> segmentVO) {
    	browser.getElement("//*[@id=\"checkTree○0\"]/a[2]/span").click();
    	for (int i = 0; i < segmentVO.size(); i++) {
    		 recursive(driver.findElement(By.id("checkTree○0")), segmentVO.get(i).getSegmentId());
		}
        browser.click(browser.getWebDriver().button(className("c_submit").tagName("button")));
    }
     
}
