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
public class OfferVO {

    public OfferVO(){}

    public OfferVO(String offerId){
        this.offerId = offerId;
    }

    private String offerId;
    private String offerName;
    private String offerType;
    private String payType;
    private String brand;
    private String offerCode;
    private String descriptionCustomer;
    private String internalDescription;
    private String startDate;
    private String endDate;
    private String numLimit;
    private List<CharSpecVO> offerChar;
    private List<CatalogVO> offerCatalog;
    private List<BusinessVO> offerBusiness;
    private List<RelObjectVO> offerRelObject;
    private List<ChannelVO> offerChannel;
    private List<SegmentVO> offerSegment;
    private List<LocationVO> offerLocation;
    //Offering Associations From CurrentOffer
    private List<OfferFCVO> OfferFC;
    //Offering Associations To CurrentOffer
    private List<OfferTCVO> OfferTC;
    
    public List<ChannelVO> getOfferChannel() {
		return offerChannel;
	}

	public void setOfferChannel(List<ChannelVO> offerChannel) {
		this.offerChannel = offerChannel;
	}

	public List<SegmentVO> getOfferSegment() {
		return offerSegment;
	}

	public void setOfferSegment(List<SegmentVO> offerSegment) {
		this.offerSegment = offerSegment;
	}

	public List<LocationVO> getOfferLocation() {
		return offerLocation;
	}

	public void setOfferLocation(List<LocationVO> offerLocation) {
		this.offerLocation = offerLocation;
	}

	public List<OfferFCVO> getOfferFC() {
		return OfferFC;
	}

	public void setOfferFC(List<OfferFCVO> offerFC) {
		OfferFC = offerFC;
	}

	public List<OfferTCVO> getOfferTC() {
		return OfferTC;
	}

	public void setOfferTC(List<OfferTCVO> offerTC) {
		OfferTC = offerTC;
	}

	public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public List<CatalogVO> getOfferCatalog() {
        return offerCatalog;
    }

    public void setOfferCatalog(List<CatalogVO> offerCatalog) {
        this.offerCatalog = offerCatalog;
    }

    public List<BusinessVO> getOfferBusiness() {
        return offerBusiness;
    }

    public void setOfferBusiness(List<BusinessVO> offerBusiness) {
        this.offerBusiness = offerBusiness;
    }

    public List<CharSpecVO> getOfferChar() {
        return offerChar;
    }

    public void setOfferChar(List<CharSpecVO> offerChar) {
        this.offerChar = offerChar;
    }

    public String getNumLimit() {
        return numLimit;
    }

    public void setNumLimit(String numLimit) {
        this.numLimit = numLimit;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getInternalDescription() {
        return internalDescription;
    }

    public void setInternalDescription(String internalDescription) {
        this.internalDescription = internalDescription;
    }

    public String getDescriptionCustomer() {
        return descriptionCustomer;
    }

    public void setDescriptionCustomer(String descriptionCustomer) {
        this.descriptionCustomer = descriptionCustomer;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<RelObjectVO> getOfferRelObject() {
        return offerRelObject;
    }

    public void setOfferRelObject(List<RelObjectVO> offerRelObject) {
        this.offerRelObject = offerRelObject;
    }
}
