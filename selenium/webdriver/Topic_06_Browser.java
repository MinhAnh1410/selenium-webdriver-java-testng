package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Browser {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @Test
    public void TC_01_Firefox() {
        driver = new FirefoxDriver();
        driver.get("https://www.lazada.vn/");
        driver.quit();
    }

    @Test
    public void TC_02_Chrome() {
        driver = new ChromeDriver();
        driver.get("https://www.lazada.vn/");
        driver.quit();
    }

    @Test
    public void TC_04_Edge() {
        driver = new EdgeDriver();
        driver.get("https://www.lazada.vn/");
        driver.quit();
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}