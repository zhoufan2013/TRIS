package com.ai.config;

import com.ai.upc.bean.CharSpec;
import com.ai.upc.bean.Service;
import com.ai.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

    private static Service service = new Service();

    static {
        wookbook = ExcelUtil.createWb("src/main/resources/upc_data_template.xlsx"); //TODO
    }

    public static Service readService() {
        long startTime = System.currentTimeMillis();
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
                loadBasicInfo(rows.get(i));
            }else if (i > relatedServiceRowNum+1 && i < relatedCharSpecRowNum) {
                loadRelatedServices(rows.get(i));
            }else if (i > relatedCharSpecRowNum+1) {
                loadRelatedCharSpec(rows.get(i));
            }
        }

        if (_log.isDebugEnabled()) {
            _log.info(System.currentTimeMillis() - startTime);
        }

        return service;
    }

    private static void loadBasicInfo(String[] row) {
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


    private static void loadRelatedServices(String[] row) {
        String relServiceId = splitCellValue(row[0]);
        String relationship = splitCellValue(row[2]);
        assertTrue(StringUtils.isNotEmpty(relServiceId));
        assertTrue(StringUtils.isNotEmpty(relationship));
        if (null == service.getRelServices()) {
            service.setRelServices(new HashMap<String, String>());
        }
        service.getRelServices().put(relServiceId, relationship);
    }

    private static void loadRelatedCharSpec(String[] row) {
        String charSpecId = splitCellValue(row[0]);
        String charSpecValue = splitCellValue(row[2]);
        assertTrue(StringUtils.isNotEmpty(charSpecId));
        if (null == service.getServChar()) {
            service.setServChar(new ArrayList<CharSpec>());
        }
        CharSpec charSpec = new CharSpec(charSpecId);
        if (StringUtils.isNotEmpty(charSpecValue)) {
            charSpec.setCharValue(charSpecValue);
        }
        service.getServChar().add(charSpec);
    }

    private static boolean subTitleRow(String subTitle, String specifiedTitle) {
        if (StringUtils.equals(subTitle.substring(0, subTitle.indexOf("[")), specifiedTitle)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private static int rowNum(String subTitle) {
        String rowString = subTitle.substring(subTitle.indexOf("[")+1, subTitle.indexOf(","));
        if (StringUtils.isNotEmpty(rowString) && StringUtils.isNumeric(rowString)) {
            return Integer.parseInt(rowString);
        }
        return -1;
    }

    private static String splitCellValue(String cellValue) {
        return cellValue.substring(0, cellValue.indexOf("["));
    }
}
