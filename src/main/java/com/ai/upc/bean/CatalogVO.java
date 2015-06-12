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
public class CatalogVO {
    public CatalogVO(){}

    public CatalogVO(String catalogId){
        this.catalogId = catalogId;
    }

    private String catalogId;
    private String catalogName;

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }




}
