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
    //private ProductVO product;

    @BeforeClass(alwaysRun = true)
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        //product = ExcelReader.init(ExcelConst.XLSX_PATH).readProduct();
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
        offerEditUIPage.intoBasicInfo().intoCatalogNode().verifyCatalogNode(browser, "971110000001");
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
    }
    
    /**
     * 新增dslmainoffer
     * @author wangchao
     */
    @Test
    public void UPC_CRM_DSLMAIN_0011(){
    	
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
        offerEditUIPage.intoOfferAssociation("95042611").insertOfferAssociation("95042611","Subscribe Together", "95042652").saveOffer();
    }
}
