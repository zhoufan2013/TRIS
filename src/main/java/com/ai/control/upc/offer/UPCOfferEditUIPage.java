package com.ai.control.upc.offer;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.OfferVO;
import com.ai.upc.bean.SaleChannelVO;
import com.ai.upc.common.ChooseCharSpec;
import com.ai.upc.common.MessageBox;
import com.ai.upc.offer.OfferBasicInfo;
import com.ai.util.UPCUtil;
//import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import static org.openqa.selenium.By.tagName;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public UPCOfferEditUIPage insertSaleChannel(OfferVO offer) {
        switchToSubFrameViaTitle(OfferUIConst.CHANNEL);
        List<WebElement> trs = browser.getElements("//*[@id=\"relTable\"]/tbody/tr");
        for(WebElement tr : trs) {
            List<WebElement> tds = tr.findElements(tagName("td"));
            List<WebElement> as = tds.get(5).findElements(tagName("a"));
            if (as.get(1).getAttribute("onclick").equals("delImgClick($(this))")) {
                as.get(1).click();
                new MessageBox(browser){{
                    okSuccessMsg();
                }};
            }
        }

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

}
