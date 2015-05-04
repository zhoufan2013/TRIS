package com.ai.util;

import com.ai.config.LogConst;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhoufan on 15/4/29.
 */
public final class TRISUtil {

    private static Log _log = LogFactory.getLog(TRISUtil.class);

    /**
     * 屏幕截图，保存{file_name}.png到{save_path}
     */
    public static void snapshot(TakesScreenshot driver, String save_path, String file_name) {

        if(_log.isInfoEnabled()) {
            _log.info(LogConst.START_CAPUTARE);
            _log.info(MessageFormat.format(LogConst.SAVE_SCREENSHOT, file_name, save_path));
        }

        File file = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(save_path + file_name));
        }catch (IOException ioe){
            _log.error(ioe.getMessage(), ioe);
        }

    }

    /**
     * 按照yyyyMMddHHmmss的格式获取系统时间
     */
    public static String sysTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     *
     * @param allRows
     * @param specifiedEntityId
     */
    public static void triggerRowClick(List<WebElement> allRows, String specifiedEntityId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            _log.error(e);
        }
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if(cells.get(0).getText().equals(specifiedEntityId)) {
                cells.get(0).click();
                break;
            }
        }
    }
}
