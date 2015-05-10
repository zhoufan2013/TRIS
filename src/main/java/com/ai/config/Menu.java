package com.ai.config;

import com.ai.util.ResourceUtil;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Properties;

/**
 * 解析{/menu.properties}
 *
 * Created by zhoufan on 15/5/1.
 */
public class Menu {

    public static transient Log _log = LogFactory.getLog(Menu.class);

    private static final String MENU_PROPERTIES = "/menu.properties";

    private static Properties menu_info;

    static {
        loadMenuResource();
    }

    public static String getMenuName(String key){
        if(StringUtils.isEmpty(key))
            return StringUtils.EMPTY;
        return menu_info.getProperty(key);
    }

    public static void loadMenuResource(){
        try {
            if(_log.isDebugEnabled()){
                _log.info("Start to load menu resource...");
            }

            menu_info = ResourceUtil.loadPropertiesFromFilepath(MENU_PROPERTIES, CharEncoding.UTF_8);

            if(_log.isDebugEnabled()){
                _log.info(menu_info.size() + " counts of menus loaded...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
