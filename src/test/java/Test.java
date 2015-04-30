import com.ai.config.ModuleField;
import com.ai.util.TestCaseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Created by zhoufan on 15/4/29.
 */
public class Test {

    Log _log = LogFactory.getLog(Test.class);

    @org.junit.Test
    public void testZhoufan() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://10.10.12.151:8666/ALUPC-3307/");
        driver.manage().window().maximize();
        WebElement txtbox = driver.findElement(By.name("STAFF_ID"));
        txtbox.clear();
        txtbox.sendKeys("21upc");
        WebElement pwd = driver.findElement(By.name("PASSWORD"));
        pwd.clear();
        pwd.sendKeys("123456");
        WebElement btn = driver.findElement(By.id("login_btn"));
        btn.click();
        WebElement func = driver.findElement(By.linkText("Product/Service Catalog"));
        func.click();
        WebElement prodOffering = driver.findElement(By.linkText("Product Offering"));
        prodOffering.click();

        TestCaseUtil.snapshot((TakesScreenshot) driver, "/Users/zhoufan/", "upc.png");

        //driver.close();
    }

    @Before
    public void setup(){

    }

    @org.junit.Test
    public void test123(){
        String username = ModuleField.getFieldValue("logout", "exit");
        System.out.println(username);
    }

    @org.junit.Test
    public void zhoufan(){
        _log.error("asdasdqwdqwdqw");
    }
}
