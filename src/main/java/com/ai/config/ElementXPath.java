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





}
