package com.ai.control.upc;

import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.ProductVO;
import com.ai.upc.common.ChooseCharSpec;
import com.ai.upc.common.MessageBox;
import com.ai.upc.common.RadioTree;
import com.ai.upc.product.ProductBasicInfo;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

/**
 * Created by zhoufan on 15/5/12.
 */
public class UPCProductEditUIPage {

    private ChromeDriver driver;

    public UPCProductEditUIPage() {}

    public UPCProductEditUIPage(ChromeDriver driver) {
        this.driver = driver;
    }

    /**
     *
     *
     * @param product 依据Excel 输入
     * @return new product ID
     */
    public String createProduct(final ProductVO product) {

    /*
        new ProductBasicInfo(driver){{

            switchToProductEditFrame();
            String handler = driver.getWindowHandle();
            productNodeCell().click();
            driver.findElement(By.xpath("//*[@id=\"wade_ext_msg_div\"]/div[1]/div[2]/div[2]/div/div[2]/a")).click();//TODO

            locateProductBasicInfoFrame();
            productName().clearField().sendKeys(product.getProductName());
            description().clearField().sendKeys(product.getDescription());
            productCode().clearField().sendKeys(product.getProductCode());
            productType().click();

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
            }};

        }};
            */
        return StringUtils.EMPTY;
    }
}
