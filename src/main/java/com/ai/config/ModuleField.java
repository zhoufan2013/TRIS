package com.ai.config;

import com.ai.util.ResourceUtil;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析{/module-field.xml}
 *
 * Created by zhoufan on 15/4/30.
 */
public final class ModuleField {

    private static transient Log _log = LogFactory.getLog(ModuleField.class);

    private static final String MODULE_FILED_PATH = "/module-field.xml";

    public static HashMap<String, Module> _cached = new HashMap<String, Module>();

    private ModuleField(){}

    /**
     * 获取模块对象，内包含此模块所有控件
     */
    public static Module getModule(String moduleName){

        return _cached.get(moduleName);
    }

    /**
     * 获取 Veris UPC 界面指定控件的ID
     */
    public static String getFieldValue(String moduleName, String id) {
        return  _cached.get(moduleName).getFieldMap().get(id);
    }

    static {
        synchronized (_cached) {

            try {
                String moduleFileds = ResourceUtil.readCfgFile(MODULE_FILED_PATH, CharEncoding.UTF_8);
                Document doc = DocumentHelper.parseText(moduleFileds);

                if (null == doc) {
                    _log.error(LogConst.ERROR_PARSE_FILE + MODULE_FILED_PATH);
                }

                //load entity info
                List<Element> elements = doc.selectNodes("/modules/module");
                if(null == elements || elements.size() < 1){
                    _log.error(LogConst.EMPTY_MODULE_FIELD);
                }else{

                    Module module = null;
                    for(Element element : elements) {

                        module = new Module();
                        module.name = element.attributeValue("name");
                        List<Element> elementList = element.elements();
                        if (null != elementList && elementList.size() > 0) {
                            Map<String, String> fieldMap = new HashMap<String, String>();
                            for(Element ele : elementList) {
                                fieldMap.put(ele.attributeValue("id") == null ? "":ele.attributeValue("id"), ele.getText() == null ? "":ele.getText());
                            }
                            module.setFieldMap(fieldMap);
                        }
                        _cached.put(module.name, module);
                    }
                }

            } catch (DocumentException de) {
                _log.error(de.getMessage(), de);
            } catch (IOException ioe) {
                _log.error(ioe.getMessage(), ioe);
            }

        }
    }

    public static class Module{

        public String name;
        public Map<String, String> fieldMap;

        public void setFieldMap(Map<String, String> fieldMap) {
            this.fieldMap = fieldMap;
        }

        public Map<String, String> getFieldMap(){
            if(null == fieldMap)
                this.fieldMap = new HashMap<String, String>();
            return this.fieldMap;
        }
    }

}
