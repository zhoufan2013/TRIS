package com.ai.upc.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by zhoufan on 15/5/12.
 */
public class ProductVO {

    private String productName;

    private String productType;

    private String productCode;

    private String description;

    private List<CharSpecVO> prodChar;

    private List<ServiceVO> relService;

    /**
     * Map<relProdIds, relationship>
     */
    private Map<String, String> relProdSpecs;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CharSpecVO> getProdChar() {
        return prodChar;
    }

    public void setProdChar(List<CharSpecVO> prodChar) {
        this.prodChar = prodChar;
    }

    public Map<String, String> getRelProdSpecs() {
        return relProdSpecs;
    }

    public void setRelProdSpecs(Map<String, String> relProdSpecs) {
        this.relProdSpecs = relProdSpecs;
    }

    public List<ServiceVO> getRelService() {
        return relService;
    }

    public void setRelService(List<ServiceVO> relService) {
        this.relService = relService;
    }
}
