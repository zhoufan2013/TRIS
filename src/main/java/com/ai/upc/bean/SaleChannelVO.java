package com.ai.upc.bean;

/**
 * @author zhoufan
 * @version 1.0.0
 * @date 15/5/26
 * @description 渠道信息
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 15/5/26      zhoufan         1.0.0              initial
 */
public class SaleChannelVO {

    private String channelId;

    private String channelName;

    private String validFrom;

    private String expireTo;

    public SaleChannelVO(){}

    public SaleChannelVO(String channelId, String channelName, String validFrom, String expireTo){
        this.channelId = channelId;
        this.channelName = channelName;
        this.validFrom = validFrom;
        this.expireTo = expireTo;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getExpireTo() {
        return expireTo;
    }

    public void setExpireTo(String expireTo) {
        this.expireTo = expireTo;
    }
}
