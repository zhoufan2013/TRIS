package com.ai.util;

import com.ai.config.LogConst;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhoufan on 15/4/29.
 */
public final class TestCaseUtil {

    private static Log _log = LogFactory.getLog(TestCaseUtil.class);

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
}
