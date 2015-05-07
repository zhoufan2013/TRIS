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
     * 测试人员指定模板创建
     */
    public UPCBasePage chooseTemplate(String tempId) {
        templateChoose(tempId);
        return PageFactory.initPage(driver, UPCBasePage.class);
    }

    /**
     * 依赖输入 upc_data_template.xlsx
     */
    public void chooseTemplate() {
        //TODO 解析 xlsx
        String tempId = "47";
        templateChoose(tempId);
    }

    private void templateChoose(final String tempId) {
        new ChooseTemplate(driver){{
            locateTemplateChooseFrame();
            templateId().sendKeys(tempId);
            queryTemplate().click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chooseSpecifiedTemplate(allRows(), tempId);
            confirmChooseTemplate().click();
        }};
    }
}
