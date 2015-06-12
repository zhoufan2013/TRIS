package com.ai.upc.bean;

/**
 * @author tianhj
 * @version 1.0.0
 * @date 2015/6/12
 * @description 该类的功能描述
 * <p/>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2015/6/12      tianhj         1.0.0             initial
 */
public class RelObjectVO {
    private String relType;
    private String relObjectId;

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public String getRelObjectId() {
        return relObjectId;
    }

    public void setRelObjectId(String relObjectId) {
        this.relObjectId = relObjectId;
    }
}
