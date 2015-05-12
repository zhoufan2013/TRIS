package com.ai.control.upc;

import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.ServiceVO;
import com.ai.upc.common.ChooseCharSpec;
import com.ai.upc.common.MessageBox;
import com.ai.upc.common.RadioTree;
import com.ai.upc.service.ServiceBasicInfo;
import com.ai.util.UPCUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by zhoufan on 15/5/7.
 */
public class UPCServiceEditUIPage extends UPCBasePage{

    private ChromeDriver driver;

    public UPCServiceEditUIPage() {}

    public UPCServiceEditUIPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public void switchToServiceEditFrame() {
        driver.switchTo().frame(UPCUtil.findNavFrame(driver, "Add Service Specification"));
    }

    public void createService(final ServiceVO service) {

        switchToServiceEditFrame();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//*[@id=\"wade_ext_msg_div\"]/div[1]/div[2]/div[2]/div/div[2]/a")).click();//TODO
        final String handler = driver.getWindowHandle();
        new ServiceBasicInfo(driver){{
            //获取当前页面句柄

            locateServiceBasicInfoFrame();
            serviceName().sendKeys(service.getServiceName());
            serviceDescription().sendKeys(service.getDescription());
            serviceType().click();
            //返回父页面
            driver.switchTo().window(handler);

            switchToServiceEditFrame();
            locateServiceTypeTreeFrame();
        }};

        new RadioTree(driver){{
            selectSpecifiedNode(service.getServiceType());
        }};

        driver.switchTo().frame(driver.findElement(By.id("navframe_56")));

        new ServiceBasicInfo(driver) {{
            locateServiceBasicInfoFrame();
            driver.findElement(By.xpath("//*[@id=\"baseinfo\"]/div/ul/li[4]/span[2]/button")).click();
            driver.switchTo().window(handler);
            switchToServiceEditFrame();
            locateSelectServiceCatagoryFrame();
            selectConditionIDorName().sendKeys(service.getServiceCatagory());
            queryServiceCatagoryButton().click();
            chooseSpecifiedServiceCatagory(service.getServiceCatagory());
            confirmSelectServiceCatagory();

            switchToServiceEditFrame();
            locateServiceCharFrame();
        }};

        new ChooseCharSpec(driver){{

            List<CharSpecVO> charSpecs = service.getServChar();
            for(CharSpecVO charSpec : charSpecs) {
                chooseSpecifiedServiceChar(charSpec.getCharSpecId(), charSpec.getCharValue());
            }
        }};

        new ServiceBasicInfo(driver) {{
            driver.switchTo().window(handler);
            switchToServiceEditFrame();
            saveServiceButton().click();

        }};

        new MessageBox(driver) {{
            okSuccessMsg();
        }};
    }

    public void verify() {
        System.out.println("verifying ... ");
    }

}
