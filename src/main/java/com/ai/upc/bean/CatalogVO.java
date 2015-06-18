package com.ai.upc.bean;

import java.util.List;

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
    private String catalogType;
    private String description;
    private List<CatalogVO> catalogNodes;

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

    public String getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(String catalogType) {
        this.catalogType = catalogType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CatalogVO> getCatalogNodes() {
        return catalogNodes;
    }

    public void setCatalogNodes(List<CatalogVO> catalogNodes) {
        this.catalogNodes = catalogNodes;
    }
}
