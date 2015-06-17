package com.ai.upc.offer;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentWebElement;

import com.ai.core.TRISBrowser;
import com.ai.core.TRISWebDriver;
import com.ai.upc.common.ChooseCharSpec;

public class OfferBusiness {

    private TRISWebDriver fwd ;
    private TRISBrowser browser;

    public OfferBusiness(TRISBrowser browsers) {
        this.browser = browsers;
        this.fwd = browsers.getWebDriver();
    }
    
    protected FluentWebElement inputBusinessName(){
    	return fwd.input( By.id("busiInterName") );
    }
	
    protected FluentWebElement queryBusiness(){
		return fwd.button( By.xpath("//*[@id=\"condition\"]/div[2]/button") );
	}

    protected FluentWebElement confirmBusiness(){
		return fwd.button( By.cssSelector("body > div.m_wrapper > div.m_wrapper2 > div > div.c_submit > button.e_button-page-ok") );
	}

    protected FluentWebElement cancleBusiness(){
		return fwd.button( By.xpath("/html/body/div[1]/div[1]/div/div[3]/button[2]") );
	}
	
    protected FluentWebElement selectBusiness(){
		return fwd.link( By.xpath("/html/body/div[1]/div[1]/div/div[2]/div/div[3]/div/div/div[2]/ul/li[1]/a") );
	}
	
    protected FluentWebElement unselectBusiness(){
		return fwd.link(By.xpath("/html/body/div[1]/div[1]/div/div[2]/div/div[3]/div/div/div[2]/ul/li[2]/a"));
	}
    
    protected WebElement checkInputBusiness(String businessId){
    	WebElement checkInput = null;
    	List<WebElement> trs = browser.getElements("//*[@id=\"BusiInterTableTable\"]/tbody//tr");
    	for (WebElement tr:trs){
    		String Id = tr.findElement(By.xpath("td[3]")).getText();
    		if (businessId!=null && businessId.equals(Id) ){
    			checkInput = tr.findElement(By.xpath("td[2]"));
    			break; 
    		}
    	}
    	return checkInput;
    }	
}
