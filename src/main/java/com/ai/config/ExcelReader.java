package com.ai.config;

import com.ai.upc.bean.*;
import com.ai.util.ExcelUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import javax.xml.ws.Service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TODO 添加对 Excel 必填字段的校验 Assert
 *
 * Created by zhoufan on 15/5/5.
 */
public class ExcelReader {

    private static transient Log _log = LogFactory.getLog(ExcelReader.class);

    private static Workbook wookbook = null;

    private ExcelReader() {}

    private ExcelReader(String xlsxPath) {
        this.wookbook = ExcelUtil.createWb(xlsxPath);
    }

    public static ExcelReader init(String xlsxPath) {
        return new ExcelReader(xlsxPath);
    }

    public LoginVO readLoginInfo() {
        LoginVO loginVO = new LoginVO();
        loginVO.setUsername("21upc");
        loginVO.setPassword("123456");
        loginVO.setLink("");
        return loginVO;
    }

    public OfferVO readOffer() {
        OfferVO offer = new OfferVO();
        Sheet offerSheet = ExcelUtil.getSheetViaSheetName(wookbook, ExcelConst.UPC_MODULE_OFFER);
        List<String[]> rows = ExcelUtil.listFromSheet(offerSheet);

        int offerCharRowNum = -1;
        int offerChannelRowNum=-1;
        int offerLocationRowNum=-1;
        int offerSegmentRowNum=-1;
        for(String[] row : rows) {
            if (subTitleRow(row[0], ExcelConst.SUB_OFFER_CHAR)) {
            	offerCharRowNum = rowNum(row[0]);
            }else if (subTitleRow(row[0], ExcelConst.SUB_OFFER_CHANNEL)) {
            	offerChannelRowNum = rowNum(row[0]);
            }else if(subTitleRow(row[0], ExcelConst.SUB_OFFER_LOCATION)){
            	offerLocationRowNum = rowNum(row[0]);
			}else if(subTitleRow(row[0], ExcelConst.SUB_OFFER_SEGMENT)){
				offerSegmentRowNum = rowNum(row[0]);
			}
        }
        for(int i=0; i<rows.size(); i++) {
            if (i > 0 && i < offerCharRowNum) {
            	loadOfferBasicInfo(offer, rows.get(i));
            }else if (i > offerCharRowNum+1 && i < offerChannelRowNum) {
                loadOfferChar(offer, rows.get(i));
            }else if (i > offerChannelRowNum+1&&i < offerLocationRowNum) {
                loadOfferChannel(offer, rows.get(i));
            }else if (i > offerLocationRowNum+1&&i<offerSegmentRowNum){
            	loadOfferLocation(offer, rows.get(i));
			}else if (i > offerSegmentRowNum+1){
            	loadOfferSegment(offer, rows.get(i));
			}
        }
        return offer;
    }
    private void loadOfferBasicInfo(OfferVO offer, String[] row) {
        if (StringUtils.equals(splitCellValue(row[0]), "offerName")) {
        	offer.setOfferName(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]), "offerCode")) {
        	offer.setOfferCode(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]), "descriptionCustomer")) {
        	offer.setDescriptionCustomer(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]), "internalDescription")) {
        	offer.setInternalDescription(splitCellValue(row[1]));
        }
    }
    
    private void loadOfferChannel(OfferVO OfferVO, String[] row) {
    	  String channelId = splitCellValue(row[0]);
          assertTrue(StringUtils.isNotEmpty(channelId));
          if (null == OfferVO.getOfferChannel()) {
        	  OfferVO.setOfferChannel(new ArrayList<ChannelVO>());
          }
          ChannelVO channel = new ChannelVO();
          if (StringUtils.isNotEmpty(channelId)) {
        	  channel.setChannelId(channelId);
          }
          OfferVO.getOfferChannel().add(channel);
    }
    
    private void loadOfferSegment(OfferVO OfferVO, String[] row) {
  	  String segmentId = splitCellValue(row[0]);
      assertTrue(StringUtils.isNotEmpty(segmentId));
      if (null == OfferVO.getOfferSegment()) {
  	    OfferVO.setOfferSegment(new ArrayList<SegmentVO>());
      }
      SegmentVO segment = new SegmentVO();
      if (StringUtils.isNotEmpty(segmentId)) {
    	  segment.setSegmentId(segmentId);
      }
      OfferVO.getOfferSegment().add(segment);
  }
    
    private void loadOfferLocation(OfferVO OfferVO, String[] row) {
    	  String locationId = splitCellValue(row[0]);
          assertTrue(StringUtils.isNotEmpty(locationId));
          if (null == OfferVO.getOfferLocation()) {
        	  OfferVO.setOfferLocation(new ArrayList<LocationVO>());
          }
          LocationVO location = new LocationVO();
          if (StringUtils.isNotEmpty(locationId)) {
        	  location.setLocationId(locationId);
          }
          OfferVO.getOfferLocation().add(location);
    }
    
    
    private void loadOfferChar(OfferVO OfferVO, String[] row) {
  	  String charSpecId = splitCellValue(row[0]);
        String charSpecValue = splitCellValue(row[2]);
        assertTrue(StringUtils.isNotEmpty(charSpecId));
        if (null == OfferVO.getOfferChar()) {
      	  OfferVO.setOfferChar(new ArrayList<CharSpecVO>());
        }
        CharSpecVO charSpec = new CharSpecVO(charSpecId);
        if (StringUtils.isNotEmpty(charSpecValue)) {
            charSpec.setCharValue(charSpecValue);
        }
        OfferVO.getOfferChar().add(charSpec);
  }
    
    

    public ProductVO readProduct() {
        ProductVO product = new ProductVO();
        Sheet productSheet = ExcelUtil.getSheetViaSheetName(wookbook, ExcelConst.UPC_MODULE_PRODUCT);
        List<String[]> rows = ExcelUtil.listFromSheet(productSheet);

        int relatedProductRowNum = -1;
        int relatedCharSpecRowNum = -1;
        for(String[] row : rows) {
            if (subTitleRow(row[0], ExcelConst.SUB_TITLE_RELATED_PRODUCTS)) {
                relatedProductRowNum = rowNum(row[0]);
            }else if (subTitleRow(row[0], ExcelConst.SUB_TITLE_RELATED_CHAR_SPEC)) {
                relatedCharSpecRowNum = rowNum(row[0]);
            }
        }
        for(int i=0; i<rows.size(); i++) {
            if (i > 0 && i < relatedProductRowNum) {
                loadProductBasicInfo(product, rows.get(i));
            }else if (i > relatedProductRowNum+1 && i < relatedCharSpecRowNum) {
                loadRelatedProduct(product, rows.get(i));
            }else if (i > relatedCharSpecRowNum+1) {
                loadRelatedCharSpec(product, rows.get(i));
            }
        }
        return product;
    }

    public ServiceVO readService() {
        //TODO xlsx 校验
        ServiceVO service = new ServiceVO();
        //long startTime = System.currentTimeMillis();
        Sheet serviceSheet = ExcelUtil.getSheetViaSheetName(wookbook, ExcelConst.UPC_MODULE_SERVICE);
        List<String[]> rows = ExcelUtil.listFromSheet(serviceSheet);

        int relatedServiceRowNum = -1;
        int relatedCharSpecRowNum = -1;
        for(String[] row : rows) {
            if (subTitleRow(row[0], ExcelConst.SUB_TITLE_RELATED_SERVICES)) {
                relatedServiceRowNum = rowNum(row[0]);
            } else if (subTitleRow(row[0], ExcelConst.SUB_TITLE_RELATED_CHAR_SPEC)) {
                relatedCharSpecRowNum = rowNum(row[0]);
            }
        }

        for(int i=0; i<rows.size(); i++) {
            if (i > 0 && i < relatedServiceRowNum) {
                loadBasicInfo(service, rows.get(i));
            }else if (i > relatedServiceRowNum+1 && i < relatedCharSpecRowNum) {
                loadRelatedServices(service, rows.get(i));
            }else if (i > relatedCharSpecRowNum+1) {
                loadRelatedCharSpec(service, rows.get(i));
            }
        }

        return service;
    }

    public OfferGroupVO readOfferGroup() {
        //TODO xlsx 校验
        OfferGroupVO offerGroup = new OfferGroupVO();
        //long startTime = System.currentTimeMillis();
        Sheet offerGroupSheet = ExcelUtil.getSheetViaSheetName(wookbook, ExcelConst.UPC_MODULE_OFFERGROUP);
        List<String[]> rows = ExcelUtil.listFromSheet(offerGroupSheet);


        int relatedOfferRowNum = -1;
        for(String[] row : rows) {
            if (subTitleRow(row[0], ExcelConst.SUB_TITLE_RELATED_OFFERS)) {
                relatedOfferRowNum = rowNum(row[0]);
            }
        }


        for (int i=0; i<rows.size(); i++) {
            if (i>0 && i < relatedOfferRowNum) {
                loadOfferGroupBasicInfo(offerGroup,rows.get(i));
            }
        }

        return offerGroup;
    }

    private void loadOfferGroupBasicInfo(OfferGroupVO offergroup, String[] row) {
        if (StringUtils.equals(splitCellValue(row[0]),"Offering Group ID")) {
            offergroup.setOfferGroupID(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]),"Product Offering Group Name")) {
            offergroup.setOfferGroupName(splitCellValue(row[1]));
        } else  if (StringUtils.equals(splitCellValue(row[0]),"Subscribe Quantity Restriction")) {
            offergroup.setSubscribeQuantityRestriction(splitCellValue(row[1]));
        } else  if (StringUtils.equals(splitCellValue(row[0]),"To")) {
            offergroup.setTo(splitCellValue(row[1]));
        } else  if (StringUtils.equals(splitCellValue(row[0]),"Description Customer")) {
            offergroup.setDescriptionCustomer(splitCellValue(row[1]));
        } else  if (StringUtils.equals(splitCellValue(row[0]),"Mutually Exclusive")) {
            if (StringUtils.equals(splitCellValue(row[1]), "NO")) {
                offergroup.setMutuallyExclusive("NO");
            } else {
                offergroup.setMutuallyExclusive("YES");
            }
        } else  if (StringUtils.equals(splitCellValue(row[0]),"Mutual Conversion Type")) {
            if (StringUtils.equals(splitCellValue(row[1]), "Enable")) {
                offergroup.setMutualConversionType("Enable");
            } else  if (StringUtils.equals(splitCellValue(row[1]), "Enable upward conversion")) {
                offergroup.setMutualConversionType("Enable upward conversion");
            } else {
                offergroup.setMutualConversionType("Disable");
            }

        }
    }

    private void loadProductBasicInfo(ProductVO product, String[] row) {
        if (StringUtils.equals(splitCellValue(row[0]), "product name")) {
            product.setProductName(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]), "product type")) {
            product.setProductType(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]), "product code")) {
            product.setProductCode(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]), "description")) {
            product.setDescription(splitCellValue(row[1]));
        }
    }

    private void loadRelatedCharSpec(ProductVO product, String[] row) {
        String charSpecId = splitCellValue(row[0]);
        String charSpecValue = splitCellValue(row[2]);
        assertTrue(StringUtils.isNotEmpty(charSpecId));
        if (null == product.getProdChar()) {
            product.setProdChar(new ArrayList<CharSpecVO>());
        }
        CharSpecVO charSpec = new CharSpecVO(charSpecId);
        if (StringUtils.isNotEmpty(charSpecValue)) {
            charSpec.setCharValue(charSpecValue);
        }
        product.getProdChar().add(charSpec);
    }

    private void loadRelatedProduct(ProductVO product, String[] row) {
        String relProductId = splitCellValue(row[0]);
        String relationship = splitCellValue(row[2]);
        assertTrue(StringUtils.isNotEmpty(relProductId));
        assertTrue(StringUtils.isNotEmpty(relationship));
        if (null == product.getRelProdSpecs()) {
            product.setRelProdSpecs(new HashMap<String, String>());
        }
        product.getRelProdSpecs().put(relProductId, relationship);
    }

    private void loadBasicInfo(ServiceVO service, String[] row) {
        if (StringUtils.equals(splitCellValue(row[0]), "service name")) {
            service.setServiceName(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]), "service type")) {
            service.setServiceType(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]), "service category")) {
            service.setServiceCatagory(splitCellValue(row[1]));
        } else if (StringUtils.equals(splitCellValue(row[0]), "description")) {
            service.setDescription(splitCellValue(row[1]));
        }
    }

    private void loadRelatedServices(ServiceVO service, String[] row) {
        String relServiceId = splitCellValue(row[0]);
        String relationship = splitCellValue(row[2]);
        assertTrue(StringUtils.isNotEmpty(relServiceId));
        assertTrue(StringUtils.isNotEmpty(relationship));
        if (null == service.getRelServices()) {
            service.setRelServices(new HashMap<String, String>());
        }
        service.getRelServices().put(relServiceId, relationship);
    }

    private void loadRelatedCharSpec(ServiceVO service, String[] row) {
        String charSpecId = splitCellValue(row[0]);
        String charSpecValue = splitCellValue(row[2]);
        assertTrue(StringUtils.isNotEmpty(charSpecId));
        if (null == service.getServChar()) {
            service.setServChar(new ArrayList<CharSpecVO>());
        }
        CharSpecVO charSpec = new CharSpecVO(charSpecId);
        if (StringUtils.isNotEmpty(charSpecValue)) {
            charSpec.setCharValue(charSpecValue);
        }
        service.getServChar().add(charSpec);
    }

    private boolean subTitleRow(String subTitle, String specifiedTitle) {
        if (StringUtils.equals(subTitle.substring(0, subTitle.indexOf("[")), specifiedTitle)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private int rowNum(String subTitle) {
        String rowString = subTitle.substring(subTitle.indexOf("[")+1, subTitle.indexOf(","));
        if (StringUtils.isNotEmpty(rowString) && StringUtils.isNumeric(rowString)) {
            return Integer.parseInt(rowString);
        }
        return -1;
    }

    private String splitCellValue(String cellValue) {
        return cellValue.substring(0, cellValue.indexOf("["));
    }

}
