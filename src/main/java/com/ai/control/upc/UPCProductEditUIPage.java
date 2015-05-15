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
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

/**
 * Created by zhoufan on 15/5/12.
 */
public class UPCProductEditUIPage {

    private TRISBrowser browser;

    public UPCProductEditUIPage() {}

    public UPCProductEditUIPage(TRISBrowser browser) {
        this.browser = browser;
    }

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
     *
     * @param product 依据Excel 输入
     * @return new product ID
     */
    public String createProduct(final ProductVO product) {

        new ProductBasicInfo(browser.getWebDriver()){{

            switchToProductEditFrame();
            String handler = browser.getInternalWebDriver().getWindowHandle();
            browser.click(productNodeCell());

            browser.getInternalWebDriver().findElement(By.xpath("//*[@id=\"wade_ext_msg_div\"]/div[1]/div[2]/div[2]/div/div[2]/a")).click();//TODO
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

            browser.pause(1000l);

            new ChooseCharSpec(browser) {{
                List<CharSpecVO> charSpecs = product.getProdChar();
                for(CharSpecVO charSpec : charSpecs) {
                    chooseSpecifiedServiceChar(charSpec.getCharSpecId(), charSpec.getCharValue());
                }
            }};

            browser.selectWindow(handler);
            switchToProductEditFrame();
            browser.click(saveProductButton());

            new MessageBox(browser){{
                okSuccessMsg();
            }};

            /*
            driver.switchTo().window(handler);
            switchToProductEditFrame();
            switchToProductTypeTreeFrame();
            new RadioTree(driver){{
                selectSpecifiedNode(product.getProductType());
            }};
            switchToProductEditFrame();
            switchToProductCharFrame();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new ChooseCharSpec(driver){{
                List<CharSpecVO> charSpecs = product.getProdChar();
                for(CharSpecVO charSpec : charSpecs) {
                    chooseSpecifiedServiceChar(charSpec.getCharSpecId(), charSpec.getCharValue());
                }
            }};

            driver.switchTo().window(handler);
            switchToProductEditFrame();
            saveProductButton().click();

            new MessageBox(driver) {{
                okSuccessMsg();
            }};*/

        }};
        return StringUtils.EMPTY;
    }
}
