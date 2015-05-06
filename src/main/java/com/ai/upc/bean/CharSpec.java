package com.ai.upc.bean;

/**
 * Created by zhoufan on 15/5/5.
 */
public class CharSpec {

    public CharSpec(){}

    public CharSpec(String charSpecId){
        this.charSpecId = charSpecId;
    }

    private String charSpecId;

    private String charSpecName;

    private String charValue;

    public String getCharSpecId() {
        return charSpecId;
    }

    public void setCharSpecId(String charSpecId) {
        this.charSpecId = charSpecId;
    }

    public String getCharSpecName() {
        return charSpecName;
    }

    public void setCharSpecName(String charSpecName) {
        this.charSpecName = charSpecName;
    }

    public String getCharValue() {
        return charValue;
    }

    public void setCharValue(String charValue) {
        this.charValue = charValue;
    }
}
