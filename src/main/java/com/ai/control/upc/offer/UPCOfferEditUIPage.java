package com.ai.control.upc.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


//import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ai.config.ElementXPath;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.BusinessVO;
import com.ai.upc.bean.CatalogVO;
import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.OfferVO;
import com.ai.upc.common.CheckTree;
import com.ai.upc.common.ChooseCharSpec;
import com.ai.upc.common.MessageBox;
import com.ai.upc.common.RadioTree;
import com.ai.upc.offer.OfferBasicInfo;
import com.ai.upc.offer.OfferBusiness;
import com.ai.util.UPCUtil;

/**
 * @author zhoufan
 * @version 1.0.0
 * @date 15/5/23
 * @description Offer界面交互编排类
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/5/23      zhoufan         1.0.0              initial
 */
public class UPCOfferEditUIPage {

    private TRISBrowser browser;

    private RemoteWebDriver driver;

    public UPCOfferEditUIPage() {}

    public UPCOfferEditUIPage(TRISBrowser browser) {
        this.browser = browser;
        this.driver = browser.getInternalWebDriver();
    }

    /**
     * 定位到新增策划的大frame框
     */
    private void switchToOfferEditFrame() {
        browser.enterFrame(UPCUtil.findNavFrame(browser, "Add Product Offering"));
    }

    /**
     * 定位策划基本信息编辑frame
     */
    private void switchToOfferBasicInfoFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElementById("detailFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-Frame"));
    }

    /**
     * 定位到销售品特征编辑frame框
     */
    private void switchToOfferCharFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElementById("detailFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-FrameF-0"));
    }
    /**
     * 定位到产品增加服务页面
     */
    private void switchToAddProductFrame() {
        browser.enterFrame(browser.getElement(ElementXPath.OFFER_ADD_PRODUCT_FRAME)).enterFrame(browser.getElement("//iframe[@id='selectProductFrame']"));
    }
    
    /**
     * 定位到Offer编辑界面的页签
     * @param tabTitle 页签名称
     */
    private void switchToFrameViaTabTitle(String tabTitle) {
        browser.leaveFrame();
        switchToOfferEditFrame();
        browser.enterFrame("detailFrame");
        //点击事件
        WebElement tab = browser.getElement("//*[@id=\"mytab_tab\"]");
        List<WebElement> links = tab.findElements(By.tagName("a"));
        for (WebElement link : links) {
            if (StringUtils.equals(link.getText(), tabTitle)) {
                link.click();
                break;
            }
        }
        //进入真正的frame
        List<WebElement> elements = driver.findElementsByTagName("iframe");
        for(WebElement element : elements) {
            if (element.getAttribute("tab_title").equals(tabTitle)) {
                element.click();
                browser.enterFrame(element);
                break;
            }
        }
    }

    /**
     * 定位到页签内的子frame
     * @param title 页签内子frame的标题
     */
    private void switchToSubFrameViaTitle(String title) {
        List<WebElement> elements = driver.findElementsByTagName("iframe");
        for (WebElement element : elements) {
            if (element.getAttribute("title").equals(title)) {
                browser.enterFrame(element);
                break;
            }
        }
    }

    /**
     * 进入 Offer Basic Info 页签
     */
    public UPCOfferEditUIPage intoBasicInfo() {
        new OfferBasicInfo(browser){{
            switchToOfferEditFrame();
            browser.click(offerNodeCell());
        }};
        return this;
    }

    public UPCOfferEditUIPage intoEditBaseicInfo(final String offerId) {
        new OfferBasicInfo(browser){{
            browser.leaveFrame();
            browser.enterFrame(UPCUtil.findNavFrameIncludeID(browser, offerId));
            browser.click(offerNodeCell());
        }};
        return this;
    }

    /**
     * 进入 Offer Eligibility Criteria 页签
     */
    public UPCOfferEditUIPage intoEligibilityCriteria() {
        switchToFrameViaTabTitle(ModuleField.getFieldValue(ModuleConst.OFFER_TAB_TITLE, "eligibility"));
        return this;
    }

    
    public UPCOfferEditUIPage insertOfferTree(){
    	 List<WebElement> elements = driver.findElementsByTagName("iframe");
         for (WebElement element : elements) {
        	 if (element.getAttribute("src").indexOf("upc.product.OfferEditUI")>0) {
        		 browser.enterFrame(element);
        		 break;
			}
         }
         browser.getElement("//*[@id=\"hierarchy\"]").click();
         return this;
    }
    /**
     * 图形树添加产品规格
     */
    public UPCOfferEditUIPage addProductSpecification(final String productId) {
    	new OfferBasicInfo(browser){{
    		browser.leaveFrame();
    		List<WebElement> elements = driver.findElementsByTagName("iframe");
	        for (WebElement element : elements) {
	        	if (element.getAttribute("src").indexOf("upc.product.OfferEditUI")>0) {
	        		 browser.enterFrame(element);
	        		 break;
				}
	        }
	        browser.getElement("//*[@id=\"hierarchy\"]").click();
    		browser.pause(1l, TimeUnit.SECONDS);
    		browser.upcHTreeHover(ElementXPath.OFFER_HTREE_HORIZONAL, ElementXPath.OFFER_HTREE_HORIZONAL_PLUS, ElementXPath.OFFER_HTREE_ADD_PRODUCT);
    		browser.pause(1l, TimeUnit.SECONDS);
	        switchToAddProductFrame();
	    	browser.pause(1l, TimeUnit.SECONDS);
	        browser.input(productId(), productId);
	        browser.click(queryProductButton());
        	chooseSpecifiedProduct(productId);
        	browser.getElement(ModuleField.getFieldValue(ModuleConst.PRODUCT_ADD_SERVICE, "addServiceRightButton")).click();//TODO 优化
        	browser.click(addProductOKButton());
    	 }};
        return this;
    }
    private void chooseSpecifiedProduct(String productId) {
        browser.pause(1l, TimeUnit.SECONDS);
        List<WebElement> allRows = browser.getElements(ElementXPath.OFFER_ADD_PRODUCT_SELECTABLE_TABLE_ALLROWS);
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for(WebElement cell : cells) {
                WebElement c = cell.findElement(By.tagName("input"));
                if(c.getAttribute("value").equals(productId) && !c.isSelected()) {
                    c.click();
                    break;
                }
            }
        }
    }
    
    /**
     * 输入Offer基本信息
     */
    public UPCOfferEditUIPage insertBasicInfo(final OfferVO offer) {
        new OfferBasicInfo(browser){{
            browser.pause(1l, TimeUnit.SECONDS);
            switchToOfferBasicInfoFrame();
            browser.input(offerName(), offer.getOfferName());
            browser.input(offerCode(), offer.getOfferCode());
            browser.input(description(), offer.getDescriptionCustomer());
            browser.input(internalDescription(), offer.getInternalDescription());
        }};
        return this;
    }

    /**
     * 校验Offer基本信息
     */
    public UPCOfferEditUIPage verifyBasicInfo() {
       return this;
    }

    /**
     * Offer关联特征
     */
    public UPCOfferEditUIPage insertOfferChar(final OfferVO offer) {
        new OfferBasicInfo(browser){{
            browser.leaveFrame();
            switchToOfferEditFrame();
            switchToOfferCharFrame();
            new ChooseCharSpec(browser) {{
                List<CharSpecVO> charSpecs = offer.getOfferChar();
                for (CharSpecVO charSpec : charSpecs ) {
                    chooseSpecifiedServiceChar(charSpec.getCharSpecId(),charSpec.getCharValue());
                }
                /*
                chooseSpecifiedServiceChar("2700035", "111");
                chooseSpecifiedServiceChar("27000029", "222");
                List<CharSpecVO> charSpecs = product.getProdChar();
                for(CharSpecVO charSpec : charSpecs) {
                    chooseSpecifiedServiceChar(charSpec.getCharSpecId(), charSpec.getCharValue());
                }*/
            }};
        }};

        return this;
    }


    public boolean checkEnumChar(final String offerId,final String charSepc) {
        new OfferBasicInfo(browser){{
            browser.leaveFrame();
            browser.enterFrame(UPCUtil.findNavFrameIncludeID(browser, offerId));
            switchToOfferCharFrame();
        }};
        return (new ChooseCharSpec(browser).isCharHaveEnumValue(charSepc));
    }


    /**
     * 校验Offer关联特征
     */
    public UPCOfferEditUIPage verifyOfferChar() {
        return this;
    }

    /**
     * Offer关联定价计划
     */
    public UPCOfferEditUIPage insertPricePlan() {
        return this;
    }

    /**
     * 校验Offer关联定价计划
     */
    public UPCOfferEditUIPage verifyPricePlan() {
        return this;
    }
    /**
     * Offer关联销售渠道
     */
    public UPCOfferEditUIPage insertSaleChannel(final OfferVO offer) {
        switchToSubFrameViaTitle(OfferUIConst.CHANNEL);
        new OfferBasicInfo(browser){{
        	browser.getElement("//*[@id=\"btnDiv\"]/a[2]").click();
	        browser.pause(3l, TimeUnit.SECONDS);
	        browser.leaveFrame();
	        browser.leaveFrame();
	        List<WebElement> elements = driver.findElementsByTagName("iframe");
	        for (WebElement element : elements) {
	        	if (element.getAttribute("src").indexOf("upc.product.OfferEditUI")>0) {
	        		 browser.enterFrame(element);
	        		 List<WebElement> elements1 = driver.findElementsByTagName("iframe");
	        		 for (WebElement element1 : elements1) {
	        			 if (element1.getAttribute("src").indexOf("upc.common.ChannelTree")>0) {
	        				 browser.enterFrame(element1);
	        				 break;
						}
					}
				}
	        }
	        new RadioTree(browser) {{
                selectOfferChannel(offer.getOfferChannel());
            }};
        }};
        return this;
    }
    
    /**
     * Offer关联Location
     */
    public UPCOfferEditUIPage insertSaleLocation(final OfferVO offer) {
        List<WebElement> elementsL = driver.findElementsByTagName("iframe");
        for (WebElement elementL : elementsL) {
        	if (elementL.getAttribute("src").indexOf("upc.product.OfferEditUI")>0) {
        		 browser.enterFrame(elementL);
        	}
        }
        browser.enterFrame(browser.getInternalWebDriver().findElementById("detailFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrameF-2"));
        switchToSubFrameViaTitle(OfferUIConst.LOCATION);
        new OfferBasicInfo(browser){{
        	browser.getElement("//*[@id=\"btnDiv\"]/a[2]").click();
	        browser.pause(3l, TimeUnit.SECONDS);
	        browser.leaveFrame();
	        browser.leaveFrame();
	        List<WebElement> elements = driver.findElementsByTagName("iframe");
	        for (WebElement element : elements) {
	        	if (element.getAttribute("src").indexOf("upc.product.OfferEditUI")>0) {
	        		 browser.enterFrame(element);
	        		 List<WebElement> elements1 = driver.findElementsByTagName("iframe");
	        		 for (WebElement element1 : elements1) {
	        			 if (element1.getAttribute("src").indexOf("upc.common.CBPlaceTree")>0) {
	        				 browser.enterFrame(element1);
	        				 break;
						}
					}
				}
	        }
	        new RadioTree(browser) {{
                selectOfferLocation(offer.getOfferLocation());
            }};
        }};
        return this;
    }
    
    
    /**
     * Offer关联segment
     */
    public UPCOfferEditUIPage insertSaleSegment(final OfferVO offer) {
        List<WebElement> elementsL = driver.findElementsByTagName("iframe");
        for (WebElement elementL : elementsL) {
        	if (elementL.getAttribute("src").indexOf("upc.product.OfferEditUI")>0) {
        		 browser.enterFrame(elementL);
        	}
        }
        browser.enterFrame(browser.getInternalWebDriver().findElementById("detailFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrameF-2"));
        switchToSubFrameViaTitle(OfferUIConst.SEGMENTS);
        new OfferBasicInfo(browser){{
        	browser.getElement("//*[@id=\"btnDiv\"]/a[1]").click();
	        browser.pause(3l, TimeUnit.SECONDS);
	        browser.leaveFrame();
	        browser.leaveFrame();
	        List<WebElement> elements = driver.findElementsByTagName("iframe");
	        for (WebElement element : elements) {
	        	if (element.getAttribute("src").indexOf("upc.product.OfferEditUI")>0) {
	        		 browser.enterFrame(element);
	        		 List<WebElement> elements1 = driver.findElementsByTagName("iframe");
	        		 for (WebElement element1 : elements1) {
	        			 if (element1.getAttribute("src").indexOf("upc.common.SegmentTree")>0) {
	        				 browser.enterFrame(element1);
	        				 break;
						}
					}
				}
	        }
	        new RadioTree(browser) {{
                selectOfferSegment(offer.getOfferSegment());
            }};
        }};
        return this;
    }
    
    
    

    /**
     * 校验Offer关联销售渠道
     * TODO javabean 反射比对
     */
    public UPCOfferEditUIPage verifySaleChannel() {
        return this;
    }

    /**
     * Offer关联销售区域
     */
    public UPCOfferEditUIPage insertSaleLocation() {
        return this;
    }

    /**
     * 校验Offer关联销售区域
     */
    public UPCOfferEditUIPage verifySaleLocation() {
        return this;
    }

    /**
     * Offer关联市场细分
     */
    public UPCOfferEditUIPage insertCustomerSegments() {
        return this;
    }

    /**
     * 校验Offer关联市场细分
     */
    public UPCOfferEditUIPage verifyCustomerSegments() {
        return this;
    }

    public UPCOfferEditUIPage insertOfferRelations() {
        return this;
    }

    public UPCOfferEditUIPage verifyOfferRelations() {
        return this;
    }

    /**
     * Offer关联操作
     */
    public UPCOfferEditUIPage insertBusiInterItems() {
        return this;
    }

    public UPCOfferEditUIPage verifyBusiInterItems() {
        return this;
    }
    
    /**
     * 保存offer 
     */
    public String saveOffer(){
    	//点击提交
    	browser.leaveFrame();
    	switchToOfferEditFrame();
        browser.click(browser.getWebDriver().button(By.id("btsave")));
        //记录消息内容
        String message = browser.getText( browser.getElement("//*[@id=\"wade_msg_ct\"]/div[1]/div[1]") );
        //点击确定消息框
        new MessageBox(browser){{
    		okSuccessMsg();
    	}};
    	return message;
    }
    
    /**
     * 取消offer
     */
    public String cancleOffer(){
    	//点击提交
    	browser.leaveFrame();
    	switchToOfferEditFrame();
        browser.click(browser.getWebDriver().button(By.id("btcancel")));
        //记录消息内容
        String message = browser.getText( browser.getElement("//*[@id=\"wade_msg_ct\"]/div[4]/a[1]") );
        //点击确定消息框
        new MessageBox(browser){{
    		okSuccessMsg();
    	}};
    	return message;
    }
    
    /**
     * 定位到弹出框   //*[@eventid="addAfter"]
     */
    private void switchToOfferPopupFrame(){
    	browser.enterFrame(browser.getElement(ElementXPath.OFFER_CHOOSE_FRAME));
    }

	/**
     * 进入offer catalog 页签
     */
    public UPCOfferEditUIPage intoCatalogNode(){
        switchToFrameViaTabTitle(ModuleField.getFieldValue(ModuleConst.OFFER_TAB_TITLE, "nodes"));
    	return this;
    }
    /**
     * 进入offer Relate Business Interaction页签 
     * */
    public UPCOfferEditUIPage intoBusiInterItems() {
        switchToFrameViaTabTitle(ModuleField.getFieldValue(ModuleConst.OFFER_TAB_TITLE, "busiitems"));
        return this;
    }

    /**
     * Offer关联操作
     */
    public UPCOfferEditUIPage insertBusiInterItems(final OfferVO offer) {
    	
    	WebElement table = browser.getElement("//*[@id=\"relTable\"]");
    	List<WebElement> tds = table.findElements(By.xpath("//*[@id=\"relTable\"]/tbody//tr/td[2]"));
    	List<BusinessVO> offerBusiness = offer.getOfferBusiness();
    	for (WebElement td:tds){
        	for (int i=offerBusiness.size()-1;i>=0;i--){
    			if (td.getText().equals(offerBusiness.get(i).getBusinessId())) {
    				offerBusiness.remove(i);
				}
    		}
    	}
    	if (offerBusiness.size()==0) {
			return this;
		}
    	
    	//点击add按钮
    	browser.click( browser.getWebDriver().link( By.xpath("//*[@id=\"baddrow\"]") ) );
    	browser.pause(1, TimeUnit.SECONDS);
    	//转到弹出Frame
    	browser.leaveFrame();
    	switchToOfferEditFrame();
    	switchToOfferPopupFrame();
    	//添加操作
    	new OfferBusiness(browser){{
    		List<BusinessVO> offerBusiness = offer.getOfferBusiness();
    		for (BusinessVO business:offerBusiness){
    			browser.input(inputBusinessName(), business.getBusinessName());
    			browser.click(queryBusiness());
    			checkInputBusiness( business.getBusinessId() ).click();
    			browser.click(selectBusiness());
    		}
    		browser.click(confirmBusiness());
    		//回到操作frame
    		intoBusiInterItems();
    	}};
        return this;
    }
    public UPCOfferEditUIPage verifyBusiInterItems(String businessId) {
    	boolean flag = false;
    	WebElement table = browser.getElement("//*[@id=\"relTable\"]");
    	List<WebElement> tds = table.findElements(By.xpath("//*[@id=\"relTable\"]/tbody//tr/td[2]"));
    	for (WebElement td:tds){
    		if (businessId!=null && businessId.equals(td.getText()) ){
    			flag = true;
    		}
    	}
		Assert.assertTrue("Offer Template Data Verify Fail(missing BusiInterItems[id:"+businessId+"])", flag);
    	return this;
    }

    /**
     *  offer关联目录
     */
    public UPCOfferEditUIPage insertCatalogNode(final OfferVO offer){
    	//点击add按钮
    	browser.click( browser.getWebDriver().link( By.xpath("//*[@id=\"baddrow\"]") ) );
    	browser.pause(1, TimeUnit.SECONDS);
    	//转到弹出Frame
    	browser.leaveFrame();
    	switchToOfferEditFrame();
    	switchToOfferPopupFrame();
    	//选择树节点
    	new CheckTree(browser) {{
    		 List<CatalogVO> offerCatalog = offer.getOfferCatalog();
    		 for (CatalogVO catalog:offerCatalog){
    			 selectSpecifiedNode(catalog.getCatalogId());
    		 }
    		 sumbitCheckTree();
    		 //回到目录的frame
    		 intoCatalogNode();
         }};
    	return this;
    }
    public UPCOfferEditUIPage verifyCatalogNode(String catalogNodeId){
    	boolean flag = false;
    	WebElement table = browser.getElement("//*[@id=\"relTable\"]");
    	List<WebElement> tds = table.findElements(By.xpath("//*[@id=\"relTable\"]/tbody//tr/td[2]"));
    	for (WebElement td:tds){
    		if (catalogNodeId!=null && catalogNodeId.equals(td.getText()) ){
    			flag = true;
    		}
    	}
		Assert.assertTrue("Offer Template Data Verify Fail(missing catalogNode[id:"+catalogNodeId+"])", flag);
    	return this;
    }


    /**
     * 进入offer 的Product Offering Associations页签
     */
    public UPCOfferEditUIPage intoOfferAssociation(String offerId){
    	switchToExistOfferTabTitle(ModuleField.getFieldValue(ModuleConst.OFFER_TAB_TITLE, "associations"),offerId);
    	return this;
    }
    
    /**
     * 编辑现有offer时定位到Offer编辑界面的页签
     * @param tabTitle 页签名称
     */
    private void switchToExistOfferTabTitle(String tabTitle,String offerId) {
        browser.leaveFrame();
        browser.enterFrame(UPCUtil.findNavFrameIncludeID(browser, offerId));
        browser.enterFrame("detailFrame");

        //点击事件
        WebElement tab = browser.getElement("//*[@id=\"mytab_tab\"]");
        List<WebElement> links = tab.findElements(By.tagName("a"));
        for (WebElement link : links) {
            if (StringUtils.equals(link.getText(), tabTitle)) {
                link.click();
                break;
            }
        }
        //进入真正的frame
        List<WebElement> elements = driver.findElementsByTagName("iframe");
        for(WebElement element : elements) {
            if (element.getAttribute("tab_title").equals(tabTitle)) {
                element.click();
                browser.enterFrame(element);
                break;
            }
        }
    }
    
    
    /**
     * 添加offer关联offer
     */
    public UPCOfferEditUIPage insertOfferAssociation(final String offerId,final String offerRelType,final String relOfferId){
    	new OfferBasicInfo(browser){{
    		switchToAddOfferRelFrame();
    		browser.getElement("//*[@id=\"baddrow\"]").click();
        	browser.pause(1l, TimeUnit.SECONDS);
        	switchToAddOfferFrame(offerId);
        	browser.pause(1l, TimeUnit.SECONDS);
        	browser.select(offerRelType(), offerRelType);
        	browser.input(offerNameOrId(), relOfferId);
        	browser.click(queryOfferAssociation());
        	chooseSpecifiedOffer(relOfferId);
        	browser.click(addOfferRightButton());
        	browser.click(addOfferSubButton());
    	}};
    	
    	return this;
    }
    
    public boolean checkOfferChar(final List<String> charSepcs){
    	new OfferBasicInfo(browser){{
            browser.leaveFrame();
            switchToOfferEditFrame();
            switchToOfferCharFrame();
        }};
        return (new ChooseCharSpec(browser).isExistOfferCharSpec(charSepcs));
    }
    
    /**
     * 校验offer类型
     */
    public boolean verifyOfferType(final String offerType){
    
    	 new OfferBasicInfo(browser){{
            browser.pause(1l, TimeUnit.SECONDS);
            switchToOfferBasicInfoFrame();
        }};
        return new OfferBasicInfo(browser).isVerifyOfferType(browser,offerType);
    }
    
    /**
     * 定位offer上关联产品
     */
    public boolean selectOfferRelProdSpecs(TRISBrowser browser,final List<String> prodSpecNameList){
    	String relPordSpecName = "";
    	List<String> relPordSpecNameList = new ArrayList<String>();
    	WebElement relProdsUl = browser.getElement(ElementXPath.OFFER_HTREE_PROD);
    	List<WebElement> relProdsLis = relProdsUl.findElements(By.tagName("li"));
    	for(WebElement prodSpecEle :relProdsLis){
    		List<WebElement> relProdsAs =  prodSpecEle.findElements(By.tagName("a"));
    		relPordSpecName = relProdsAs.get(0).getAttribute("value");
    		relPordSpecNameList.add(relPordSpecName);
    	}
    	
    	if(relPordSpecNameList.containsAll(prodSpecNameList)){
    		return true;
    	}
    	return false;
    }
    
    /**
     * offer页面定位的添加关联offer的frame
     */
    private void switchToAddOfferFrame(String offerId) {
    	browser.leaveFrame();
    	System.out.println(browser.getCurrentTitle());
    	browser.enterFrame(UPCUtil.findNavFrameIncludeID(browser, offerId));
        System.out.println(browser.getCurrentTitle());
        browser.enterFrame(browser.getElement(ElementXPath.OFFER_ADD_OFFER_FRAME));
    }
    
    /**
     * offer页面定位的添加关联offer的frame
     */
    private void switchToAddOfferRelFrame() {
        browser.enterFrame(browser.getElement("//iframe[@id='F-Frame']"));
    }
    
    private void chooseSpecifiedOffer(String offerId) {
        browser.pause(1l, TimeUnit.SECONDS);
        List<WebElement> allRows = browser.getElements("//*[@id=\"offerTableTable\"]/tbody/tr");
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for(WebElement cell : cells) {
                WebElement c = cell.findElement(By.tagName("input"));
                if(c.getAttribute("value").equals(offerId) && !c.isSelected()) {
                    c.click();
                    break;
                }
            }
        }
    }
    
    /**
     * 保存产品信息
     */
    public void saveOfferInfo() {
        new OfferBasicInfo(browser){{
            browser.leaveFrame();
            browser.enterFrame(UPCUtil.findNavFrame(browser, UPCUtil.findOnPageName(browser)));
            browser.click(saveOfferButton());
            browser.pause(1l, TimeUnit.SECONDS);
            new MessageBox(browser){{
                okSuccessMsg();
            }};
        }};
    }
    
    
}
