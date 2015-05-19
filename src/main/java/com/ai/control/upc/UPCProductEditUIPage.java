package com.ai.control.upc;

import com.ai.config.ElementXPath;
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
     * 定位到编辑产品的大frame框
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
     * 定位到产品特征编辑frame框
     */
    private void switchToProductCharFrame() {
        browser.enterFrame(browser.getInternalWebDriver().findElementById("detailFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-TabsetFrame")).enterFrame(browser.getInternalWebDriver().findElementById("F-FrameF-1"));
    }

    /**
     *
     */
    private String fetchNewCreatedProductId (String message) {
        Assert.assertTrue(StringUtils.contains(message, "Save successfully"));
        return StringUtils.substring(message, message.indexOf("=")+1, message.indexOf("】"));
    }

    /**
     *
     *
     * @param product 依据Excel 输入
     * @return new product ID
     */
    public String createProduct(final ProductVO product) {

        new ProductBasicInfo(browser.getWebDriver()){{

            switchToProductEditFrame();
            String handler = browser.getWindowHandler();
            browser.click(productNodeCell());

            browser.getElement("//*[@id=\"wade_ext_msg_div\"]/div[1]/div[2]/div[2]/div/div[2]/a").click();;

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

            String message = browser.getText(okMsg().getWebElement());
            createdProductId = fetchNewCreatedProductId(message);

            new MessageBox(browser){{
                okSuccessMsg();
            }};

        }};
        return createdProductId;
    }

}
