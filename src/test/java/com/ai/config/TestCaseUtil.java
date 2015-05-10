package com.ai.config;

/**
 * Created by zhoufan on 15/5/4.
 */
public class TestCaseUtil {

    public static String pwdPattern2(String originPwd) {
        return new StringBuilder().append(originPwd.substring(0, originPwd.length()/2)).append("  ").append(originPwd.substring(originPwd.length()/2, originPwd.length())).toString();
    }

    public static String pwdPattern3(String originPwd) {
        return new StringBuilder().append("  ").append(originPwd).toString();
    }

    public static String pwdPattern4(String originPwd) {
        return new StringBuilder().append(originPwd).append("  ").toString();
    }

}
