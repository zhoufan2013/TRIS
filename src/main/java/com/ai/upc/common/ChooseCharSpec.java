package com.ai.upc.common;

import com.ai.config.ElementXPath;
import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static org.openqa.selenium.By.tagName;
import static org.openqa.selenium.By.xpath;

/**
 *
 *
 * Created by zhoufan on 15/5/4.
 */
public class ChooseCharSpec {

    private static transient Log _log = LogFactory.getLog(ChooseCharSpec.class);

    private static final String CHECK_STATE = "on";

    private TRISBrowser browser;
    private RemoteWebDriver driver ;

    public ChooseCharSpec(TRISBrowser browser) {
        this.browser = browser;
        this.driver = browser.getInternalWebDriver();
    }

    /**
     *
     * @param specifiedCharSpec     测试输入特征编码 TODO 是否要改成特征值?
     * @param specifiedCharValue    测试输入特征值
     */
    protected void chooseSpecifiedServiceChar(String specifiedCharSpec, String specifiedCharValue) {
        List<WebElement> allRows = selectCharSpecAllRows();
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            WebElement cellElement = cells.get(2).findElement(tagName("img"));
            if (isMatched(cellElement, specifiedCharSpec)) {

                if (isOnStatus(cellElement)) {

                    //TODO

                } else if(!isOnStatus(cellElement)) {
                    cellElement.click();//先选中这个特征

                    String charType = cellElement.getAttribute("chartype");
                    switch(charType) {
                        case "1" :
                        case "2" : fillinCharValue(cells.get(3), specifiedCharValue);break;
                        case "3" :
                        case "4" : ; break;
                        case "5" : 
                        	List<WebElement> cellElements = cells.get(3).findElements(tagName("input"));
                        	for (int i = 0; i < cellElements.size(); i++) {
                        		if(cellElements.get(i).getAttribute("value").equals(specifiedCharValue)){
                        			cellElements.get(i).click();//先选中这个特征
                        			break;
                        		}
							}
                        	; break;
                        default: break;
                    }
                }
            }
        }
    }

    /**
     * 校验offer模板带出的特征是否正确
     * @param offerCharSpecs
     * @return
     */
    public boolean isExistOfferCharSpec(List<String> offerCharSpecs){
    	List<WebElement> allRows = selectCharSpecAllRows();
    	for(String charSpec : offerCharSpecs){
    		for(WebElement row : allRows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                WebElement cellElement = cells.get(2).findElement(tagName("img"));
                if (isMatched(cellElement, charSpec)) {
                	return true;
                }
            }
    	}
    	return false;
    }
    
    public boolean isCharHaveEnumValue(String specifiedCharSpec) {
        List<WebElement> allRows = selectCharSpecAllRows();
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            WebElement cellElement = cells.get(2).findElement(tagName("img"));
            if (isMatched(cellElement, specifiedCharSpec)) {

                if (isOnStatus(cellElement)) {
                    return true;
                } else if(!isOnStatus(cellElement)) {
                    cellElement.click();//先选中这个特征

                    String charType = cellElement.getAttribute("chartype");
                    switch(charType) {
                        case "1" : return true;
                        case "2" : return true;
                        case "3" : return true;
                        case "4" : return true;
                        case "5" :
                            try {
                                cells.get(3).findElement(tagName("li"));
                                return true;
                            } catch (Exception e) {
                                return false;
                            }
                        default: break;
                    }
                }
            }
        }
        return false;
    }

    private boolean isMatched(WebElement cellElement, String specifiedCharSpec) {
        if (StringUtils.equals(cellElement.getAttribute("id"), specifiedCharSpec)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /*判断该特征是否已被选中*/
    private boolean isOnStatus(WebElement cellElement) {
        if (StringUtils.equals(cellElement.getAttribute("status"), CHECK_STATE)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private void fillinCharValue(WebElement cellElement, String specifiedCharValue) {
        cellElement.findElement(tagName("input")).sendKeys(specifiedCharValue);
    }

    private List<WebElement> selectCharSpecAllRows() {
        List<WebElement> allRows = driver.findElements(xpath(ElementXPath.CHAR_SPEC_CHOOSE_ALLROWS));
        //TODO assert info
        return allRows;
    }

}
