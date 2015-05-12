package com.ai.config;

import com.ai.upc.bean.CharSpecVO;
import com.ai.upc.bean.LoginVO;
import com.ai.upc.bean.ProductVO;
import com.ai.upc.bean.ServiceVO;
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
