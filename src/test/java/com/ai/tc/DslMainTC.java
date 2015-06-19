package com.ai.tc;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import com.ai.control.upc.UPCServiceEditUIPage;
import com.ai.control.upc.UPCServiceManmPage;
import com.ai.control.upc.offer.UPCOfferEditUIPage;
import com.ai.control.upc.offer.UPCOfferManmPage;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.OfferVO;
import com.ai.upc.bean.ProductVO;
import com.ai.upc.bean.ServiceVO;

/**
 * @author wangchao
 * @version 1.0.0
 * @date 15/6/10
 * @description 销售品模块自动化case
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/6/10      wangchao         1.0.0              initial
 */
public class DslMainTC {

    private TRISBrowser browser;
    private OfferVO offer;

    @BeforeClass(alwaysRun = true)
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        offer = ExcelReader.init(ExcelConst.XLSX_PATH).readOffer();
    }

    @AfterClass(alwaysRun = true)
    private void tearDown() {
        //browser.quit();
    }
    
    /**
     * 新增dslmainoffer的服务,2200000下的特征,线下操作
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0001(){
    	
    }
    
    /**
     * 新增dslmainoffer的offer和产品下的特征,线下操作
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0002(){
    	
    }

    /**
     * 新增dslmainoffer的服务,2200000：DSL Access
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0003(){
    	
    }
    
    /**
     * 新增dslmainoffer的产品bre YYM/xxM
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0004(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        browser.pause(1l, TimeUnit.SECONDS);
        /*打开产品菜单*/
        UPCProductManmPage productManm = menu.chooseProductMenu();
        /*新增产品*/
        UPCChooseTemplatePage templatePage = productManm.addProduct();
        /*选择模板*/
        UPCProductEditUIPage productEditPage = templatePage.chooseProductTemplate("26");
        /*创建产品*/
        ProductVO product = new  ProductVO();
        product.setProductName("bre YYM/xxM");
        product.setProductCode("bre YYM/xxM");
        product.setProductType("DSLCOMM");
        product.setDescription("wangchao");
        /*创建产品上的特征*/
        List<CharSpecVO> prodChars = new ArrayList<CharSpecVO>();
        CharSpecVO charSpec = new CharSpecVO();
        charSpec.setCharSpecId("25042018");
        charSpec.setCharValue("wangchao");
        prodChars.add(charSpec);
        product.setProdChar(prodChars);
        
        String productId = productEditPage.createProduct(product);
        /*校验数据正确性*/
        productManm.queryProduct(productId).editProduct(productId);
        /*产品关联服务后并保存产品*/
        productEditPage.addServiceSpecification("2200000").saveProduct();
    }
    
    /**
     * 模板带出的offer类型是否正确
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0005(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("246");
        /*进入编辑页面校验模板的offer类型是否正确*/
        assertTrue(offerEditUIPage.intoBasicInfo().verifyOfferType("DSL_MAIN"));
    }
    
    /**
     * 模板带出的offer特征是否正确
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0006(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("246");
        /*校验offer上的特征是否正确*/
        List<String> charSpecs = new ArrayList<String>();
        charSpecs.add("2700004");
        charSpecs.add("2701347");
        charSpecs.add("50001");
        charSpecs.add("27000029");
        charSpecs.add("10000001");
        charSpecs.add("10000002");
        charSpecs.add("10000003");
        charSpecs.add("10000004");
        charSpecs.add("10000005");
        charSpecs.add("10000006");
        charSpecs.add("200063");
        assertTrue(offerEditUIPage.intoBasicInfo().checkOfferChar(charSpecs));
    }
    
    /**
     * 模板带出产品是否正确
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0007(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("246");
        /*校验offer上的产品是否正确*/
        List<String> relPrds = new ArrayList<String>();
        relPrds.add("bre YYM/xxM");
        assertTrue(offerEditUIPage.selectOfferRelProdSpecs(browser, relPrds));
    }
    
    /**
     * 模板带出的offer目录是否正确
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0009(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("246");
        /*校验offer模板上的目录是否正确*/
        offerEditUIPage.intoBasicInfo().intoCatalogNode().verifyCatalogNode("971110000001");
    }
    
    /**
     * 模板带出的操作是否正确
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0010(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("246");
        /*校验offer模板上的操作是否正确*/
        offerEditUIPage.intoBasicInfo().intoBusiInterItems().verifyBusiInterItems("191000001004").
        verifyBusiInterItems("305378").verifyBusiInterItems("305379").verifyBusiInterItems("191000001006").verifyBusiInterItems("191000001018").
        verifyBusiInterItems("305382").verifyBusiInterItems("191000002204");
    }
    
    /**
     * 新增dslmainoffer
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0011(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*新增策划*/
        UPCChooseTemplatePage templatePage = offerManmPage.addOffer();
        /*选择模板*/
        UPCOfferEditUIPage offerEditUIPage = templatePage.chooseOfferTemplate("246");
        
        /*进入编辑页面并输入基本信息*/
        offerEditUIPage.intoBasicInfo().insertBasicInfo(offer).insertOfferChar(offer);
        /*offer上添加目录*/
        offerEditUIPage.intoCatalogNode().insertCatalogNode(offer);
        /*offer上添加操作*/
        offerEditUIPage.intoBusiInterItems().insertBusiInterItems(offer);
        /*offer上添加渠道，Location和客户细分*/
        offerEditUIPage.intoEligibilityCriteria().insertSaleChannel(offer).insertSaleLocation(offer).insertSaleSegment(offer);
        /*offer上添加产品*/
        offerEditUIPage.insertOfferTree().addProductSpecification("95042745");
        /*保存offer信息*/
        offerEditUIPage.saveOffer();
    }

    /**
     * dslmainoffer关联cpeoffer配置
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0012(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*查询*/
        offerManmPage.queryOffer("95042611");
        /*点击编辑按钮*/
        UPCOfferEditUIPage offerEditUIPage = offerManmPage.editOffer("95042611");
        /*进入编辑页面*/
        offerEditUIPage.intoEditBaseicInfo("95042611");
        /*校验offer模板上的目录是否正确*/
        offerEditUIPage.intoOfferAssociation("95042611").insertOfferAssociation("95042611","Subscribe Together", "95042652").saveOfferInfo();
    }
    
    /**
     * dslmainoffer关联Splitter Accesstory配置
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0013(){

    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*查询*/
        offerManmPage.queryOffer("95042611");
        /*点击编辑按钮*/
        UPCOfferEditUIPage offerEditUIPage = offerManmPage.editOffer("95042611");
        /*进入编辑页面*/
        offerEditUIPage.intoEditBaseicInfo("95042611");
        /*校验offer模板上的目录是否正确*/
        offerEditUIPage.intoOfferAssociation("95042611").insertOfferAssociation("95042611","Subscribe Together", "2000631").saveOfferInfo();
    
    }
    
    /**
     * dslmainoffer关联offer的中相容关系的配置
     * @author wangchao
     */
    
    @Test
    public void UPC_CRM_DSLMAIN_0014(){

    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*查询*/
        offerManmPage.queryOffer("95042611");
        /*点击编辑按钮*/
        UPCOfferEditUIPage offerEditUIPage = offerManmPage.editOffer("95042611");
        /*进入编辑页面*/
        offerEditUIPage.intoEditBaseicInfo("95042611");
        /*校验offer模板上的目录是否正确*/
        offerEditUIPage.intoOfferAssociation("95042611").insertOfferAssociation("95042611","Compatible", "2000617").saveOfferInfo();
    
        /*点击编辑按钮*/
        offerEditUIPage = offerManmPage.editOffer("95042611");
        /*进入编辑页面*/
        offerEditUIPage.intoEditBaseicInfo("95042611");
        /*校验offer模板上的目录是否正确*/
        offerEditUIPage.intoOfferAssociation("95042611").insertOfferAssociation("95042611","Compatible", "2000619").saveOfferInfo();
    
        /*点击编辑按钮*/
        offerEditUIPage = offerManmPage.editOffer("95042611");
        /*进入编辑页面*/
        offerEditUIPage.intoEditBaseicInfo("95042611");
        /*校验offer模板上的目录是否正确*/
        offerEditUIPage.intoOfferAssociation("95042611").insertOfferAssociation("95042611","Compatible", "2000644").saveOfferInfo();
    
        /*点击编辑按钮*/
        offerEditUIPage = offerManmPage.editOffer("95042611");
        /*进入编辑页面*/
        offerEditUIPage.intoEditBaseicInfo("95042611");
        /*校验offer模板上的目录是否正确*/
        offerEditUIPage.intoOfferAssociation("95042611").insertOfferAssociation("95042611","Compatible", "95042221").saveOfferInfo();
    
    }
    /**
     * dslmainoffer关联offer的中和dslmainoffer的可转关系的配置
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0015(){
    	/*登录*/
        UPCMenuPage menu = UPCHomePage.navigate(browser).login();
        /*打开策划菜单*/
        UPCOfferManmPage offerManmPage = menu.chooseOfferMenu();
        /*查询*/
        offerManmPage.queryOffer("95042611");
        /*点击编辑按钮*/
        UPCOfferEditUIPage offerEditUIPage = offerManmPage.editOffer("95042611");
        /*进入编辑页面*/
        offerEditUIPage.intoEditBaseicInfo("95042611");
        /*校验offer模板上的目录是否正确*/
        offerEditUIPage.intoOfferAssociation("95042611").insertOfferAssociation("95042611","Conversion Relation", "95042223").saveOfferInfo();
    
    }
}
