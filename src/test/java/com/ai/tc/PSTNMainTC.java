package com.ai.tc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.experimental.theories.internal.Assignments;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ai.config.ExcelConst;
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
import com.ai.upc.bean.ServiceVO;
import com.ai.upc.common.MessageBox;
import com.ai.util.UPCUtil;

public class PSTNMainTC {
    private TRISBrowser browser;

    @BeforeClass(alwaysRun = true)
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
    }

    @AfterClass(alwaysRun = true)
    private void tearDown() {
        //browser.quit(); 
    }
    
    public List<String> getFileNames(String path,String endsWith) { 
    	List<String> list = new ArrayList<String>();
        File file = new File(path);  
        if (file.isDirectory()) {  
            File[] dirFile = file.listFiles();  
            for (File f : dirFile) {  
                if (f.isDirectory())  
                	getFileNames(f.getAbsolutePath(),endsWith);  
                else {  
                    if (f.getName().endsWith(endsWith))  
                    	list.add( f.getAbsolutePath() );
                }
            }  
        }  
        return list;
    }
    
    private String Test_0004_ProductService(ProductVO product){
    	String serviceId = "";
    	switch ( product.getProductName() ) {
			case "PSTN Access": 
				serviceId = "2200094";break;
			case "Totalspærring":
				 serviceId = "2200095"; break;
			case "Mobilspærring, Danmark":
				serviceId = "2200096"; break;
			case "Udlandsspærring":
				serviceId = "2200097"; break;
			case "Voksenspærring, Udland":
				serviceId = "2200098"; break;
			case "Spærring for 901-numre":
				serviceId = "2200099"; break;
			case "Spærring for 902-904-numre":
				serviceId = "2200100"; break;
			case "Spærring for 9050-numre":
				serviceId = "2200101"; break;
			case "Spærring for 90565-numre":
				serviceId = "2200102"; break;
			case "Spærring for 909-numre":
				serviceId = "2200103"; break;
			default:	break;
		}
    	return serviceId;
    }
    private List<ProductVO> generate_Test0004_ProductVO(){
    	List<ProductVO> list = new ArrayList<ProductVO>();

    	List<String> fileNames = getFileNames("src/test/resources/tc/PSTNMAIN", ".xlsx");
    	for (int i=0;i<fileNames.size();i++){
    		 ProductVO product = ExcelReader.init(fileNames.get(i)).readProduct();
    		 
    		 List<ServiceVO> relService = new ArrayList<ServiceVO>();
    		 ServiceVO service = new ServiceVO();
    		 service.setServiceId( Test_0004_ProductService(product) );
    		 product.setRelService(relService);
    		 
    		 list.add(product);
    	}
    	
    	return list;
    }
    
    /**
     * 新增pstnmainoffer的服务 2200094下的特征
     * 线下操作
     */
    public void UPC_CRM_PSTNMAIN_0001(){
    	
    }
    
    /**
     * 新增pstnmainoffer的offer和产品下的特征 
     * 线下操作
     */
    public void UPC_CRM_PSTNMAIN_0002(){
    	
    }
    
    /**
     * 新增pstnmainoffer的服务
     * 服务id固定 暂时不支持自动化测试新增
     */
    public void UPC_CRM_PSTNMAIN_0003(){
    	
    }
    /**
     * 批量创建产品
     */
    @Test
    public void UPC_CRM_PSTNMAIN_0004(){
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        browser.pause(8000l);
        /*打开产品菜单*/
        UPCProductManmPage productManmPage = menu.chooseProductMenu();
        
        //新增产品
    	List<ProductVO> productList = generate_Test0004_ProductVO();
    	for (int i=0;i<productList.size();i++){
    		ProductVO product = productList.get(i);
            /*新加产品*/
            UPCChooseTemplatePage templatePage = productManmPage.addProduct();
            /*选择模版*/
            UPCProductEditUIPage productEditUIPage = templatePage.chooseProductTemplate("26");
            /*创建产品*/
            String productId = productEditUIPage.createProduct(product);
            //添加服务
            if ( product.getRelService()!=null ){
            	//输入添加的产品查询
            	productEditUIPage = productManmPage.queryProduct(productId).editProduct(productId);
            	for (ServiceVO service:product.getRelService()){
            		productEditUIPage.addServiceSpecification(service.getServiceId());
            	}
            	productEditUIPage.saveProduct();
            }
    	}
    }

    /**
     * 模板带出的offer类型是否正确
     */
    @Test
    public void UPC_CRM_PSTNMAIN_0005(){
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("247");

        browser.pause(1,TimeUnit.SECONDS);
        /*进入编辑页面并输入基本信息*/
        offerEditUIPage = offerEditUIPage.intoBasicInfo();
        /*校验offerType*/
        boolean flag = offerEditUIPage.verifyOfferType("FIX_MAIN");
        Assert.assertTrue("Offer Template Data Verify Fail(OfferType is not FIXMAIN)", flag);
        
        offerEditUIPage.cancleOffer();
    }
    
    /**
     * 模板带出的offer产品是否正确
     */
    @Test
    public void UPC_CRM_PSTNMAIN_0006(){
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("247");

        browser.pause(1,TimeUnit.SECONDS);
        /*进入编辑页面并输入基本信息*/
        offerEditUIPage = offerEditUIPage.intoBasicInfo();

        List<String> prodSpecNameList = new ArrayList<String>();
        prodSpecNameList.add("PSTN Access");				prodSpecNameList.add("Totalspærring");	
        prodSpecNameList.add("Mobilspærring, Danmark");     prodSpecNameList.add("Udlandsspærring");	
        prodSpecNameList.add("Voksenspærring, Udland");		prodSpecNameList.add("Spærring for 901-numre");
        prodSpecNameList.add("Spærring for 902-904-numre");	prodSpecNameList.add("Spærring for 9050-numre");	prodSpecNameList.add("Spærring for 90565-numre");
        prodSpecNameList.add("Spærring for 909-numre");
        
        boolean flag = offerEditUIPage.selectOfferRelProdSpecs(browser, prodSpecNameList);
        Assert.assertTrue("Offer Template Data Verify Fail(missing Product Specification)", flag);
        
        offerEditUIPage.cancleOffer();
    }
    /**
     * 模板带出的offer目录是否正确
     */
    @Test
    public void UPC_CRM_PSTNMAIN_0007(){
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("247");
        
        browser.pause(1,TimeUnit.SECONDS);
        /*进入编辑页面并输入基本信息*/
        offerEditUIPage = offerEditUIPage.intoBasicInfo();
        /*进入目录*/
        offerEditUIPage = offerEditUIPage.intoCatalogNode().verifyCatalogNode("971110000005");

        offerEditUIPage.cancleOffer();
    }
    
    /**
     * 模板带出的offer操作是否正确
     */
    @Test
    public void UPC_CRM_PSTNMAIN_0008(){
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("247");
        
        browser.pause(1,TimeUnit.SECONDS);
        /*进入编辑页面并输入基本信息*/
        offerEditUIPage = offerEditUIPage.intoBasicInfo();
        /*校验操作*/
        offerEditUIPage = offerEditUIPage.intoBusiInterItems();
        offerEditUIPage.verifyBusiInterItems("191000001001");
        offerEditUIPage.verifyBusiInterItems("192200000006");
        offerEditUIPage.verifyBusiInterItems("191000001003");
        offerEditUIPage.verifyBusiInterItems("191000001063");
        offerEditUIPage.verifyBusiInterItems("192200000007");
        offerEditUIPage.verifyBusiInterItems("192200000010");
        offerEditUIPage.verifyBusiInterItems("191001007001");
        
        offerEditUIPage.cancleOffer();
    }
    
    /**
     * 新增pstnmainoffer，包括所有offer信息（offer关联关系除外）
     */
    @Test
    public void UPC_CRM_PSTNMAIN_0009(){
    	OfferVO offer = generateOfferVO_PSTNMAIN_0009();
        /*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("247");
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
        offerEditUIPage.addProductSpecification("2100708");
        offerEditUIPage.addProductSpecification("2101304");
        offerEditUIPage.addProductSpecification("2101305");
        offerEditUIPage.addProductSpecification("2101306");
        offerEditUIPage.addProductSpecification("2101307");
        offerEditUIPage.addProductSpecification("2101308");
        offerEditUIPage.addProductSpecification("2101309");
        offerEditUIPage.addProductSpecification("2101310");
        offerEditUIPage.addProductSpecification("2101311");
        offerEditUIPage.addProductSpecification("2100724");
        
        offerEditUIPage.saveOffer();
    	
    }
    
    /**
     * pstnmainoffe与pstnmainoffer可换，pstnmain与pstnaddon连带
     */
    @Test
    public void UPC_CRM_PSTNMAIN_0010(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*查询*/
        offerManmPage.queryOffer("25042038");
        /*点击编辑按钮*/
        UPCOfferEditUIPage offerEditUIPage = offerManmPage.editOffer("25042038");
        /*进入编辑页面*/
        offerEditUIPage.intoEditBaseicInfo("25042038");
        
        offerEditUIPage.intoOfferAssociation("25042038").insertOfferAssociation("25042038", "Conversion relation", "25042099");
        offerEditUIPage.intoOfferAssociation("25042038").insertOfferAssociation("25042038", "Subscribe Together", "25042048");
    	
        offerEditUIPage.saveOffer();
    }
    
    private OfferVO generateOfferVO_PSTNMAIN_0009(){
    	OfferVO  offer = new OfferVO();
    	offer.setOfferName("test");
    	offer.setOfferType("FIXMAIN");
    	offer.setPayType("2");
    	offer.setBrand("1");
    	offer.setOfferCode("-1");
    	offer.setDescriptionCustomer("wuchao");
    	offer.setInternalDescription("wuchao");
    	offer.setStartDate("2014-01-01");
    	offer.setEndDate("2037-01-01");
    	
    	CatalogVO catalog = new CatalogVO("971110000005"); 
    	List<CatalogVO> offerCatalog = new ArrayList<CatalogVO>();
    	offerCatalog.add(catalog);
    	offer.setOfferCatalog(offerCatalog);
    	
    	BusinessVO business1 = new BusinessVO("191000001001");
    	business1.setBusinessName("PSTN  New Connection");
    	BusinessVO business2 = new BusinessVO("192200000006");
    	business2.setBusinessName("B2B Change Offer");
    	BusinessVO business3 = new BusinessVO("191000001003");
    	business3.setBusinessName("PSTN Termination");
    	BusinessVO business4 = new BusinessVO("191000001063");
    	business4.setBusinessName("Modify Installation Address");
    	BusinessVO business5 = new BusinessVO("192200000007");
    	business5.setBusinessName("B2B change Number");
    	BusinessVO business6 = new BusinessVO("192200000010");
    	business6.setBusinessName("B2B Product Change");
    	BusinessVO business7 = new BusinessVO("191001007001");
    	business7.setBusinessName("Ownership Transfer");
    	
    	List<BusinessVO> offerBusiness = new ArrayList<BusinessVO>();
    	offerBusiness.add(business1);
    	offerBusiness.add(business2);
    	offerBusiness.add(business3);
    	offerBusiness.add(business4);
    	offerBusiness.add(business5);
    	offerBusiness.add(business6);
    	offerBusiness.add(business7);
    	offer.setOfferBusiness(offerBusiness);
    	
    	List<CharSpecVO> offerChar = new ArrayList<CharSpecVO>();
    	CharSpecVO char1 = new CharSpecVO("50001"); 	char1.setCharValue("20641");
    	CharSpecVO char2 = new CharSpecVO("2700004");	char2.setCharValue("20565");
    	CharSpecVO char3 = new CharSpecVO("2700013");	char3.setCharValue("20262");
    	CharSpecVO char4 = new CharSpecVO("2700035");	char4.setCharValue("1");
    	CharSpecVO char5 = new CharSpecVO("2701347");	char5.setCharValue("31382");
    	CharSpecVO char6 = new CharSpecVO("10000001"); 	char6.setCharValue("15990085");
    	CharSpecVO char7 = new CharSpecVO("10000002");  char7.setCharValue("1");
    	CharSpecVO char8 = new CharSpecVO("10000003");  char8.setCharValue("31624");
    	CharSpecVO char9 = new CharSpecVO("10000004");  char9.setCharValue("1");
    	CharSpecVO char10 = new CharSpecVO("10000005");  char10.setCharValue("15990104");
    	CharSpecVO char11 = new CharSpecVO("10000006");  char11.setCharValue("1");
    	CharSpecVO char12 = new CharSpecVO("10000007");  char12.setCharValue("31601");
    	offerChar.add(char1);    	offerChar.add(char2);    	offerChar.add(char3);
    	offerChar.add(char4);    	offerChar.add(char5);    	offerChar.add(char6); 
    	offerChar.add(char7);    	offerChar.add(char8);    	offerChar.add(char9); 
    	offerChar.add(char10);		offerChar.add(char11);    	offerChar.add(char12);
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
