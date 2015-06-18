package com.ai.config;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

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

    public static String SERVICE_MANM_QUERY_ALLROWS = "//*[@id=\"ServSpecQueryResultTable\"]/tbody/tr";

    /*单选树父节点id*/
    public static String RADIO_TREE_ROOT_NODE = "radioTree○root";

    /*特征控件表格内所有行定位*/
    public static String CHAR_SPEC_CHOOSE_ALLROWS = "//*[@id=\"relTable\"]/tbody/tr";

    /*product type tree iframe 定位*/
    public static String PRODUCT_TYPE_TREE_FRAME = "//iframe[@eventid='productSpecificationType']";

    /*offer choose iframe 定位*/
    public static String OFFER_CHOOSE_FRAME = "//iframe[@eventid='addAfter']";
    public static String OFFER_CHOOSE_ALLROWS = "//*[@id=\"offerTableTable\"]/tbody/tr";
 
    /*定位offer下关联的产品*/
    public static String OFFER_HTREE_PROD = "//*[@id=\"offer_prods\"]/div/ul";

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
    
    /**/
    public static String OFFER_ADD_OFFER_FRAME = "//iframe[@eventid='addAfter']";

    /*产品关联服务可选表格内所有行定位*/
    public static String PRODUCT_ADD_SERVICE_SELECTABLE_TABLE_ALLROWS = "//*[@id=\"SelectableServiceTable\"]/tbody/tr";

    /*产品关联服务已选表格内所有行定位*/
    public static String PRODUCT_ADD_SERVICE_SELECTED_TABLE_ALLROWS = "//*[@id=\"SelectedServiceTable\"]/tbody/tr";

    /**/
    public static String SINGLE_LAUNCH_ALLROWS = "//*[@id=\"AreaTreeTable\"]/tbody/tr";

    /*发布日志表格内所有行*/
    public static String RELEASE_LOG_ALLROWS = "//*[@id=\"launchLogListTable\"]/tbody/tr";

    /*产品关联产品 iframe 定位*/
    public static String PRODUCT_RELATED_PRODUCT_FRAME = "//iframe[@eventid='addAfter']";

    /*产品关联产品可选表格内所有行定位*/
    public static String PRODUCT_RELATED_PRODUCT_SELECTABLE_TABLE_ALLROWS = "//*[@id=\"SelectableProductTable\"]/tbody/tr";

    /*产品关联产品已选表格内所有行定位*/
    public static String PRODUCT_RELATED_PRODUCT_SELECTED_TABLE_ALLROWS = "//*[@id=\"SelectedProductTable\"]/tbody/tr";

    /*查询Offer主页面表格所有行定位*/
    public static String OFFER_MANM_QUERY_ALLROWS = "//*[@id=\"offerTableTable\"]/tbody/tr";

    /*查询OfferGroup主页面表格所有行定位*/
    public static String OFFERGROUP_MANM_QUERY_ALLROWS = "//*[@id=\"ProdOfferingGroupTableTable\"]/tbody/tr";

}
