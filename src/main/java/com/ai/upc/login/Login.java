package com.ai.upc.login;

import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.id;

/**
 * 获取登录界面元素（控制层另写）
 *
 * Created by zhoufan on 15/4/30.
 */
public class Login {

    //当前页面标题，用于判断页面在规定时间内是否正常加载
    public static class Contants {
        public static String TITLE = "产品管理";
    }

    private TRISWebDriver fwd ;

    public Login(TRISWebDriver delegate) {
        this.fwd = delegate;
    }

    /**
     * 用户名输入框
     */
    protected FluentWebElement username() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "username")));
    }

    /**
     * 密码输入框
     */
    protected FluentWebElement password() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "password")));
    }

    /**
     * 验证码输入框
     */
    protected FluentWebElement verifycode() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "verifycode")));
    }

    /**
     * 是否记住用户CheckBox
     */
    protected FluentWebElement rememberusername() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "rememberusername")));
    }

    /**
     * 登录按钮
     */
    protected FluentWebElement login() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.LOGIN, "login")));
    }
}