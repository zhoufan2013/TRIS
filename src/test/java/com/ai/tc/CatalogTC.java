package com.ai.tc;

import com.ai.config.ExcelConst;
import com.ai.config.ExcelReader;
import com.ai.core.TRISBrowser;
import com.ai.upc.bean.CatalogVO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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
public class CatalogTC {
    private TRISBrowser browser;
    private CatalogVO catalog;

    @BeforeClass
    private void setup() {
        /*TRIS 浏览器模拟初始化*/
        browser = TRISBrowser.init();
        /*Excel 输入初始化*/
        //catalog = ExcelReader.init(ExcelConst.XLSX_PATH).readOfferGroup();
    }

    @AfterClass
    private void tearDown() {
        //browser.quit();
    }



}
