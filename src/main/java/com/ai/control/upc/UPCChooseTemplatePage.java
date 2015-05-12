package com.ai.control.upc;

import com.ai.core.PageFactory;
import com.ai.upc.common.ChooseTemplate;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by zhoufan on 15/5/6.
 */
public class UPCChooseTemplatePage extends UPCBasePage{

    private ChromeDriver driver;

    public UPCChooseTemplatePage() {}

    public UPCChooseTemplatePage(ChromeDriver driver) {
        this.driver = driver;
    }

    /**
     * 测试人员指定创建服务模板
     */
    public UPCServiceEditUIPage chooseServiceTemplate(String tempId) {
        templateChoose(tempId);
        return PageFactory.initPage(driver, UPCServiceEditUIPage.class);
    }

    public UPCProductEditUIPage chooseProductTemplate(String tempId) {
        templateChoose(tempId);
        return PageFactory.initPage(driver, UPCProductEditUIPage.class);
    }


    private void templateChoose(final String tempId) {
        new ChooseTemplate(driver){{
            locateTemplateChooseFrame();
            templateId().sendKeys(tempId);
            queryTemplate().click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chooseSpecifiedTemplate(allRows(), tempId);
            confirmChooseTemplate().click();
        }};
    }
}
