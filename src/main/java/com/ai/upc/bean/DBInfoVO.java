package com.ai.upc.bean;

/**
 * Created by zhoufan on 15/5/6.
 */
public class DBInfoVO {

    public DBInfoVO() {}

    public DBInfoVO(String host, String port, String username, String password) {
        this.host = host; this.port = port; this.username = username; this.password = password;
    }

    private String host;

    private String port;

    private String username;

    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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
