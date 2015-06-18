package com.ai.control.upc;

import com.ai.config.ElementXPath;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.ProductVO;
import com.ai.upc.common.ChooseCharSpec;
import com.ai.upc.common.MessageBox;
import com.ai.upc.common.RadioTree;
import com.ai.upc.product.ProductBasicInfo;
import com.ai.util.UPCUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoufan on 15/5/12.
 */
public class UPCProductEditUIPage {

    private TRISBrowser browser;

    public UPCProductEditUIPage() {}

    public UPCProductEditUIPage(TRISBrowser browser) {
        this.browser = browser;
    }

    String createdProductId = "-1";

    /**
     * 定位到新增产品的大frame框
     */
    private void switchToProductEditFrame() {
        browser.enterFrame(UPCUtil.findNavFrame(browser, "Add Product Specification"));
    }

    /**
     * 定位产品基本信息编辑frame
     */
    private void switchToProductBasicInfoFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElementById("detailFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-Frame"));
    }

    /**
     * 定位到产品类型树frame
     */
    private void switchToProductTypeTreeFrame() {
        browser.enterFrame(browser.getElement(ElementXPath.PRODUCT_TYPE_TREE_FRAME));
    }

    /**
     * 定位到产品关联关系frame框
     */
    private void switchToProductRelFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElementById("detailFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-FrameF-0"));
    }

    /**
     * 定位到产品特征编辑frame框
     */
    private void switchToProductCharFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElementById("detailFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-FrameF-1"));
    }

    /**
     * 定位到选择关联产品的frame框
     */
    private void switchToAddRelatedProductFrame() {
        browser.enterFrame(browser.getElement(ElementXPath.PRODUCT_RELATED_PRODUCT_FRAME)).enterFrame(browser.getInternalWebDriver().findElementById("selectProductFrame"));
    }

    /**
     * 定位到产品增加服务页面
     */
    private void switchToAddServiceFrame() {
        browser.enterFrame(browser.getElement(ElementXPath.PRODUCT_ADD_SERVICE_FRAME));
    }
    
    /**
     *
     */
    private String fetchNewCreatedProductId (String message) {
        Assert.assertTrue(StringUtils.contains(message, "Save successfully"));
        return StringUtils.substring(message, message.indexOf("=")+1, message.indexOf("】"));
    }

    /**
     * 图形树添加服务规格
     */
    public UPCProductEditUIPage addServiceSpecification(final String serviceId) {
        
        new ProductBasicInfo(browser){{
        	browser.upcHTreeHover(ElementXPath.PRODUCT_HTREE_HORIZONAL, ElementXPath.PRODUCT_HTREE_HORIZONAL_PLUS, ElementXPath.PRODUCT_HTREE_ADD_SERVICE);
            //browser.pause(1l, TimeUnit.SECONDS);
        	browser.getElement(ElementXPath.PRODUCT_HTREE_ADD_SERVICE).click();
        	browser.pause(1l, TimeUnit.SECONDS);
        	switchToAddServiceFrame();
            browser.pause(1l, TimeUnit.SECONDS);
            //System.out.println(serviceId);
            browser.input(serviceId(), serviceId);
            browser.click(queryServiceButton());
            chooseSpecifiedService(serviceId);
            browser.getElement(ModuleField.getFieldValue(ModuleConst.PRODUCT_ADD_SERVICE, "addServiceRightButton")).click();//TODO 优化
            browser.click(addServiceOKButton());
        }};
        return this;
    }

    private void chooseSpecifiedService(String serviceId) {
        browser.pause(1l, TimeUnit.SECONDS);
        List<WebElement> allRows = browser.getElements(ElementXPath.PRODUCT_ADD_SERVICE_SELECTABLE_TABLE_ALLROWS);
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for(WebElement cell : cells) {
                WebElement c = cell.findElement(By.tagName("input"));
                if(c.getAttribute("value").equals(serviceId) && !c.isSelected()) {
                    c.click();
                    break;
                }
            }
        }
    }

    /**
     * 添加产品关联关系
     */
    public UPCProductEditUIPage addproductRelationships() {
        new ProductBasicInfo(browser){{
            browser.click(productNodeCell());
            browser.pause(1l, TimeUnit.SECONDS);
            switchToProductRelFrame();
            browser.click(addRelProductButton());
            browser.leaveFrame();
            browser.enterFrame(UPCUtil.findNavFrame(browser, UPCUtil.findOnPageName(browser)));
            switchToAddRelatedProductFrame();
            browser.input(qryRelatedProductIdOrName(), "2100339");
            browser.click(qryRelatedProductButton());
            chooseSpecifiedProduct("2100339");
            browser.getElement(ModuleField.getFieldValue(ModuleConst.PRODUCT_RELATED_PRODUCT, "addProductRightButton")).click();
            browser.click(addProductOKButton());
        }};
        return this;
    }

    private void chooseSpecifiedProduct(String productId) {
        browser.pause(1l, TimeUnit.SECONDS);
        List<WebElement> allRows = browser.getElements(ElementXPath.PRODUCT_RELATED_PRODUCT_SELECTABLE_TABLE_ALLROWS);
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
     * 保存产品信息
     */
    public void saveProduct() {
        new ProductBasicInfo(browser){{
            browser.leaveFrame();
            browser.enterFrame(UPCUtil.findNavFrame(browser, UPCUtil.findOnPageName(browser)));
            browser.click(saveProductButton());
            browser.pause(1l, TimeUnit.SECONDS);
            new MessageBox(browser){{
                okSuccessMsg();
            }};
        }};
    }

    /**
     * 打开产品基本信息后直接提交保存
     */
    public UPCProductEditUIPage openThenSave() {
        new ProductBasicInfo(browser){{
            browser.click(productNodeCell());
            browser.click(saveProductButton());
        }};
        return this;
    }

    /**
     *
     *
     * @param product 依据Excel 输入
     * @return new product ID
     */
    public String createProduct(final ProductVO product) {

        new ProductBasicInfo(browser){{

            switchToProductEditFrame();
            String handler = browser.getWindowHandler();
            browser.click(productNodeCell());

            browser.pause(1l, TimeUnit.SECONDS);
            //browser.getElement("//*[@id=\"wade_ext_msg_div\"]/div[1]/div[2]/div[2]/div/div[2]/a").click();;

            //browser.getInternalWebDriver().findElement(By.xpath("//*[@id=\"wade_ext_msg_div\"]/div[1]/div[2]/div[2]/div/div[2]/a")).click();//TODO
            switchToProductBasicInfoFrame();

            browser.input(productName(), product.getProductName());
            browser.input(description(), product.getDescription());
            browser.input(productCode(), product.getProductCode());
            browser.click(productType());

            browser.selectWindow(handler);
            switchToProductEditFrame();
            switchToProductTypeTreeFrame();

            new RadioTree(browser) {{
                selectSpecifiedNode(product.getProductType());
            }};

            switchToProductEditFrame();
            switchToProductCharFrame();

            browser.pause(1l, TimeUnit.SECONDS);

            new ChooseCharSpec(browser) {{
                List<CharSpecVO> charSpecs = product.getProdChar();
                for(CharSpecVO charSpec : charSpecs) {
                    chooseSpecifiedServiceChar(charSpec.getCharSpecId(), charSpec.getCharValue());
                }
            }};

            browser.selectWindow(handler);
            switchToProductEditFrame();
            browser.click(saveProductButton());
            browser.pause(1l, TimeUnit.SECONDS);

            String message = browser.getText( browser.getElement("//*[@id=\"wade_msg_ct\"]/div[1]/div[1]") );
            createdProductId = fetchNewCreatedProductId(message);

            new MessageBox(browser){{
                okSuccessMsg();
            }};

        }};
        return createdProductId;
    }

}
