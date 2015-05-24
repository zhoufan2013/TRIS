package com.ai.control.upc.offer;

import com.ai.core.TRISBrowser;
import com.ai.upc.common.ChooseCharSpec;
import com.ai.upc.offer.OfferBasicInfo;
import com.ai.util.UPCUtil;
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

    public UPCOfferEditUIPage() {}

    public UPCOfferEditUIPage(TRISBrowser browser) {
        this.browser = browser;
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
     * 进入 Offer Basic Info 页签
     */
    public UPCOfferEditUIPage intoBasicInfo() {
        new OfferBasicInfo(browser){{
            switchToOfferEditFrame();
            browser.click(offerNodeCell());
        }};
        return this;
    }

    /**
     * 输入Offer基本信息
     */
    public UPCOfferEditUIPage insertBasicInfo() {
        new OfferBasicInfo(browser){{
            browser.pause(1l, TimeUnit.SECONDS);
            switchToOfferBasicInfoFrame();
            browser.input(offerName(), "zhoufan");
            browser.input(offerCode(), "999");
            browser.input(description(), "111");
            browser.input(internalDescription(), "222");
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
    public UPCOfferEditUIPage insertOfferChar() {
        new OfferBasicInfo(browser){{
            browser.leaveFrame();
            switchToOfferEditFrame();
            switchToOfferCharFrame();
            new ChooseCharSpec(browser) {{
                chooseSpecifiedServiceChar("2700035", "111");
                chooseSpecifiedServiceChar("27000029", "222");
                /*
                List<CharSpecVO> charSpecs = product.getProdChar();
                for(CharSpecVO charSpec : charSpecs) {
                    chooseSpecifiedServiceChar(charSpec.getCharSpecId(), charSpec.getCharValue());
                }*/
            }};
        }};

        return this;
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
     * Offer关联公共条件
     */
    public UPCOfferEditUIPage insertEligibilityCriteria() {
        return this;
    }

    /**
     * 校验
     */
    public UPCOfferEditUIPage verifyEligibilityCriteria() {
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
