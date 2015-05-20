package com.ai.config;

/**
 * VerisUPC 界面元素定位XPATH集合
 *
 * Created by zhoufan on 15/5/3.
 */
public final class ElementXPath {

    /*查询模板 iframe 定位*/
    public static String TEMPLATE_CHOOSE_FRAME = "//iframe[@eventid='addbyframework']";

    /*查询模板结果表格内所有行定位*/
    public static String TEMPLATE_CHOOSE_ALLROWS = "//*[@id=\"listFormTable\"]/tbody/tr";

    /*service type tree iframe 定位*/
    public static String SERVICE_TYPE_TREE_FRAME = "//iframe[@eventid='serviceSpecificationType']";

    /*service catagory iframe 定位*/
    public static String SERVICE_CATAGORY_FRAME = "//iframe[@eventid='relateServiceCategory_serviceRelateCategoryId']";

    /*service catagory查询表格内所有行定位*/
    public static String SERVICE_CATAGORY_CHOOSE_ALLROWS = "//*[@id=\"ServiceCatagoryTableTable\"]/tbody/tr";

    /*单选树父节点id*/
    public static String RADIO_TREE_ROOT_NODE = "radioTree○root";

    /*特征控件表格内所有行定位*/
    public static String CHAR_SPEC_CHOOSE_ALLROWS = "//*[@id=\"relTable\"]/tbody/tr";

    /*product type tree iframe 定位*/
    public static String PRODUCT_TYPE_TREE_FRAME = "//iframe[@eventid='productSpecificationType']";

    /*产品规格主查询页面所有行定位*/
    public static String PRODUCT_MANM_QUERY_ALLROWS = "//*[@id=\"ProdSpecTableTable\"]/tbody/tr";

    /**/
    public static String PRODUCT_HTREE_HORIZONAL = "//*[@id=\"product\"]/span/table/tbody/tr/td[2]";

    /**/
    public static String PRODUCT_HTREE_HORIZONAL_PLUS = "//*[@id=\"product\"]/span/table/tbody/tr/td[3]";

    /**/
    public static String PRODUCT_HTREE_ADD_SERVICE = "//*[@id=\"product\"]/span/table/tbody/tr/td[3]/div/div[3]/div/div/div[2]/ul/li[2]/a";

    /**/
    public static String PRODUCT_ADD_SERVICE_FRAME = "//iframe[@eventid='addServiceAfter']";
}
