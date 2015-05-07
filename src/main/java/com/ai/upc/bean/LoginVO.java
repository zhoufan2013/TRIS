package com.ai.upc.bean;

/**
 * Created by zhoufan on 15/5/6.
 */
public class LoginVO {

    public LoginVO() {}

    public LoginVO(String link, String username, String password) {
        this.link = link; this.username = username; this.password = password;
    }

    private String link;

    private String username;

    private String password;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
