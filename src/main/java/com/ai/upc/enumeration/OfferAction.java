package com.ai.upc.enumeration;

import java.io.Serializable;

/**
 * @author zhoufan
 * @version 1.0.0
 * @date 15/5/23
 * @description Offer 操作枚举
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/5/23      zhoufan         1.0.0              initial
 */
public enum OfferAction implements Serializable{

    COPY("copy", "copyOffering($(this))"),

    EDIT("edit", "updInfo($(this))"),

    VALIDATE("validate", "doValidate($(this))"),

    TERMINATE("terminate", "offerTerminate($(this))"),

    EXPORT("export", "exportOffering($(this))"),

    LAUNCH("launch", "launch($(this))"),

    GROUPLAUNCH("groupLaunch","p_launch(this)"),

    UPDATE4APPROVE("update", "doUpdate4Approve($(this))");


    private OfferAction(String action, String function) {
        this.action = action;
        this.function = function;
    }

    private String action;

    private String function;

    public String getAction() {
        return action;
    }

    public String getFunction() {
        return function;
    }

    public static OfferAction convert(String action) {
        for (OfferAction offerAction : OfferAction.values()) {
            if (offerAction.getAction().equals(action)) {
                return offerAction;
            }
        }
        return null;
    }
}
