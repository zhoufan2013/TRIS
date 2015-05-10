package com.ai.upc.login;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.BasePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.id;

/**
 * 获取登录界面元素（控制层另写）
 *
 * Created by zhoufan on 15/4/30.
 */
public class Login extends BasePage {

    public static class Contants {
        public static String TITLE = "产品管理";
    }

    public Login(ChromeDriver delegate) {
        super(delegate);
    }

    protected FluentWebElement username() {
        return input(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "username")));
    }

    protected FluentWebElement password() {
        return input(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "password")));
    }

    protected FluentWebElement verifycode() {
        return input(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "verifycode")));
    }

    protected FluentWebElement rememberusername() {
        return input(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "rememberusername")));
    }

    protected FluentWebElement login() {
        return button(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "login")));
    }

}
