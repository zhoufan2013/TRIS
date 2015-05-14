package com.ai.upc.common;
import com.ai.config.ModuleConst;
import com.ai.config.ModuleField;
import com.ai.core.TRISWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import static org.openqa.selenium.By.id;

/**
 * VerisUPC TemplateChoose.html 公共页面选择数据模板
 *
 * Created by zhoufan on 15/5/3.
 */
public class ChooseTemplate {

    private static transient Log _log = LogFactory.getLog(ChooseTemplate.class);

    private TRISWebDriver fwd ;

    public ChooseTemplate(TRISWebDriver delegate) {
        this.fwd = delegate;
    }

    protected FluentWebElement templateId() {
        return fwd.input(id(ModuleField.getFieldValue(ModuleConst.TEMPLATE_CHOOSE_FRAME, "templateId")));
    }

    protected FluentWebElement queryTemplate() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.TEMPLATE_CHOOSE_FRAME, "queryTemplateButton")));
    }

    protected FluentWebElement chooseTemplateButton() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.TEMPLATE_CHOOSE_FRAME, "confirmChooseTemplate")));
    }

    protected FluentWebElement cancelChooseButton() {
        return fwd.button(id(ModuleField.getFieldValue(ModuleConst.TEMPLATE_CHOOSE_FRAME, "cancelChooseTemplate")));
    }

}
