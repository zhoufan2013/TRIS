package com.ai.upc.common;

import com.ai.config.ElementXPath;
import com.ai.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.By.className;
import java.util.List;

/**
 *
 *
 * Created by zhoufan on 15/5/4.
 */
public class RadioTree extends BasePage {

    public RadioTree(ChromeDriver delegate) {
        super(delegate);
    }

    protected void selectSpecifiedNode(String nodeName) {
        recursive(delegate.findElement(By.id(ElementXPath.RADIO_TREE_ROOT_NODE)), nodeName);

        button(className("c_submit").tagName("button")).click();
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

}
