package com.ai.tc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ai.config.ExcelReader;
import com.ai.control.upc.UPCChooseTemplatePage;
import com.ai.control.upc.UPCHomePage;
import com.ai.control.upc.UPCMenuPage;
import com.ai.control.upc.UPCProductEditUIPage;
import com.ai.control.upc.UPCProductManmPage;
import com.ai.control.upc.offer.UPCOfferEditUIPage;
import com.ai.control.upc.offer.UPCOfferManmPage;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.BusinessVO;
import com.ai.upc.bean.CatalogVO;
import com.ai.upc.bean.ChannelVO;
import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.LocationVO;
import com.ai.upc.bean.OfferVO;
import com.ai.upc.bean.ProductVO;
import com.ai.upc.bean.SegmentVO;
import com.ai.util.UPCUtil;

/**
 * 测试用例  DSLSplitter
 * @author wuchao
 * 
 */
public class DSLSplitterTC {
	
    private TRISBrowser browser;
    private ProductVO product;

    @BeforeClass(alwaysRun = true)
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        product = ExcelReader.init("src/test/resources/tc/DSLSPLITTER/product_DSLSplitter.xlsx").readProduct();
    }

    @AfterClass(alwaysRun = true)
    private void tearDown() {
        //browser.quit(); 
    }
    
    /**
     * 新增dslsplitteroffer的offer的特征
     * 线下操作
     */
    public void UPC_CRM_DSLSPLITTER_0001(){
    }

    /*
     * 添加dslsplitter 的产品
     */
    @Test
    public void UPC_CRM_DSLSPLITTER_0002(){
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        browser.pause(8000l);
        /*打开产品菜单*/
        UPCProductManmPage productManmPage = menu.chooseProductMenu();
        /*新加产品*/
        UPCChooseTemplatePage templatePage = productManmPage.addProduct();
        /*选择模版*/
        UPCProductEditUIPage productEditUIPage = templatePage.chooseProductTemplate("26");
        /*创建产品*/
        productEditUIPage.createProduct(product);
    }
	
    /**
     * 添加dslsplitter offer
     */
    @Test
    public void UPC_CRM_DSLSPLITTER_0003(){
    	OfferVO offer = generateOfferVO_DSLSPLITTER_0003();
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("245");
        
        browser.pause(1,TimeUnit.SECONDS);
        //进入编辑页面并输入基本信息
        offerEditUIPage.intoBasicInfo().insertBasicInfo(offer);
        //编辑特征信息
        offerEditUIPage.insertOfferChar(offer);
        //添加渠道
        offerEditUIPage.intoEligibilityCriteria().insertSaleChannel(offer);
        //添加销售区域
        offerEditUIPage.insertSaleLocation(offer);
        //添加客户细分
        offerEditUIPage.insertSaleSegment(offer);
        //添加操作
        offerEditUIPage.intoBusiInterItems().insertBusiInterItems(offer);
        // 添加目录        	
        offerEditUIPage.intoCatalogNode().insertCatalogNode(offer);
        //添加产品 
        offerEditUIPage.addProductSpecification("95042745");
        
        offerEditUIPage.saveOffer();
    }
    
    /**
     * dslsplitteroffer关联offer配置
     */
    @Test
    public void UPC_CRM_DSLSPLITTER_0004(){
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        // 查询offer
        offerManmPage.queryOffer("2000631");
        // 编辑offer
        UPCOfferEditUIPage offerEditUIPage = offerManmPage.editOffer("2000631");
        // 进入offer编辑页面
        offerEditUIPage.intoEditBaseicInfo("2000631");
        // 添加offer关系
        offerEditUIPage.intoOfferAssociation("2000631").insertOfferAssociation("2000631", "Compatible", "95042544");
        // 保存offer
        offerEditUIPage.saveOfferInfo();
    }
    
    private OfferVO generateOfferVO_DSLSPLITTER_0003(){
    	OfferVO  offer = new OfferVO();
    	offer.setOfferName("test");
    	offer.setOfferType("DSL_VAS");
    	offer.setPayType("2");
    	offer.setBrand("1");
    	offer.setOfferCode("-1");
    	offer.setDescriptionCustomer("wuchao");
    	offer.setInternalDescription("wuchao");
    	offer.setStartDate("2014-01-01");
    	offer.setEndDate("2037-01-01");
    	
    	CatalogVO catalog = new CatalogVO("971110000006"); 
    	List<CatalogVO> offerCatalog = new ArrayList<CatalogVO>();
    	offerCatalog.add(catalog);
    	offer.setOfferCatalog(offerCatalog);
    	
    	BusinessVO business1 = new BusinessVO("191000000004");
    	business1.setBusinessName("Subscribe VAS Offer");
    	BusinessVO business2 = new BusinessVO("191000000005");
    	business2.setBusinessName("Unsubscribe VAS Offer");
    	List<BusinessVO> offerBusiness = new ArrayList<BusinessVO>();
    	offerBusiness.add(business1);
    	offerBusiness.add(business2);
    	offer.setOfferBusiness(offerBusiness);
    	
    	List<CharSpecVO> offerChar = new ArrayList<CharSpecVO>();
    	CharSpecVO char1 = new CharSpecVO("10000001"); 	char1.setCharValue("15990085");
    	CharSpecVO char2 = new CharSpecVO("10000002");	char2.setCharValue("1");
    	CharSpecVO char3 = new CharSpecVO("10000003");	char3.setCharValue("33606");
    	CharSpecVO char4 = new CharSpecVO("10000004");	char4.setCharValue("1");
    	CharSpecVO char5 = new CharSpecVO("10000005");	char5.setCharValue("15990104");
    	CharSpecVO char6 = new CharSpecVO("10000006"); 	char6.setCharValue("1");
    	CharSpecVO char7 = new CharSpecVO("200063");  	char7.setCharValue("31660");
    	offerChar.add(char1);    	offerChar.add(char2);    	offerChar.add(char3);
    	offerChar.add(char4);    	offerChar.add(char5);    	offerChar.add(char6); 
    	offerChar.add(char7);
    	offer.setOfferChar(offerChar);
    	
    	List<ChannelVO> offerChannel = new ArrayList<ChannelVO>();
    	ChannelVO channel = new ChannelVO(); channel.setChannelId("0");
    	offerChannel.add(channel);
    	offer.setOfferChannel(offerChannel);
    	
    	List<LocationVO> offerLocation = new ArrayList<LocationVO>();
    	LocationVO location = new LocationVO(); location.setLocationId("1000000");
    	offerLocation.add(location);
    	offer.setOfferLocation(offerLocation);
    	
    	List<SegmentVO> offerSegment = new ArrayList<SegmentVO>();
    	SegmentVO segment = new SegmentVO(); segment.setSegmentId("0");
    	offerSegment.add(segment);
    	offer.setOfferSegment(offerSegment);
    	
    	return offer;
    }
    
}
