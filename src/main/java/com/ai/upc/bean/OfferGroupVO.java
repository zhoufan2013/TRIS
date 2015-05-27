package com.ai.upc.bean;

import java.util.List;

/**
 * Created by asiainfo on 2015/5/19.
 */
public class OfferGroupVO {
    private String offerGroupName;
    private String mutuallyExclusive;
    private String mutualConversionType;
    private String subscribeQuantityRestriction;
    private String to;
    private String descriptionCustomer;

    //private List<OfferVO> offerVOs;


    public String getOfferGroupName() {
        return offerGroupName;
    }

    public void setOfferGroupName(String offerGroupName) {
        this.offerGroupName = offerGroupName;
    }

    public String getMutuallyExclusive() {
        return mutuallyExclusive;
    }

    public void setMutuallyExclusive(String mutuallyExclusive) {
        this.mutuallyExclusive = mutuallyExclusive;
    }


    public String getMutualConversionType() {
        return mutualConversionType;
    }

    public void setMutualConversionType(String mutualConversionType) {
        this.mutualConversionType = mutualConversionType;
    }



    public String getSubscribeQuantityRestriction() {
        return subscribeQuantityRestriction;
    }

    public void setSubscribeQuantityRestriction(String subscribeQuantityRestriction) {
        this.subscribeQuantityRestriction = subscribeQuantityRestriction;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDescriptionCustomer() {
        return descriptionCustomer;
    }

    public void setDescriptionCustomer(String descriptionCustomer) {
        this.descriptionCustomer = descriptionCustomer;
    }
}
