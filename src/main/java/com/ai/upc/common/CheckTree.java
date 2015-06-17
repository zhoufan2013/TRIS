package com.ai.upc.common;

import com.ai.config.ElementXPath;
import com.ai.core.TRISBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.openqa.selenium.By.className;
import java.util.List;

/**
 *
 *
 * Created by zhoufan on 15/5/4.
 */
public class CheckTree {

    private TRISBrowser browser;
    private RemoteWebDriver driver ;

    public CheckTree(TRISBrowser browser) {
        this.browser = browser;
        this.driver = browser.getInternalWebDriver();
    }
    
    protected void selectSpecifiedNodes(String[] nodeIds) {
    	for (String nodeId:nodeIds){
    		selectSpecifiedNode(nodeId);
    	}
    }

    protected void selectSpecifiedNode(String nodeId) {
        recursive(driver.findElement(By.id(ElementXPath.CHECK_TREE_ROOT_NODE)), nodeId);
    }
    
    protected void sumbitCheckTree(){
        browser.click(browser.getWebDriver().button(className("c_submit").tagName("button")));
    }

    private void recursive(WebElement rootNode, String nodeId) {
        List<WebElement> childNodes = rootNode.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for(WebElement childNode : childNodes) {
            String id = childNode.getAttribute("id");
            String status = childNode.getAttribute("class");
            if (cutOffNodeName(id).equals(nodeId)) {
                childNode.findElement(By.tagName("input")).click();
            } else if (status.equals("fold")) {
                childNode.findElement(By.tagName("a")).click();
                recursive(childNode, nodeId);
            }
        }
    }

    private String cutOffNodeName(String nodeId) {
        return nodeId.substring(nodeId.lastIndexOf("●")+1, nodeId.length());
    }

}
