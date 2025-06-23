package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        //if (osName.contains(WebDriver.Window)) {
          //  System.setProperties("web.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        //} else {
            //System.setProperties("web.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        //}
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://dash.burgerprints.com/authentication/register");
    }


    @Test
    public void TC_01_ID() {
        // tim element co ID la Firstname
        driver.findElement(By.id("email"));
    }

    @Test
    public void TC_02_Class() {
        // giá trị class là duy nhất, chọn element phải có class
        driver.findElement(By.className("ng-star-inserted"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("viewport"));
    }

    @Test
    public void TC_04_Tagname() {
        driver.findElements(By.tagName("input"));
    }

    @Test
    public void TC_05_LinkText() {
        // tim voi do chinh xac cao, tuyet doi
        driver.findElement(By.linkText("Log in"));
    }

    @Test
    public void TC_06_Partial_LinkText() {
        // tim voi do chinh xac tuong doi (chi can trich 1 phan: dau/ cuoi/ giua)
        driver.findElement(By.partialLinkText("Sign in"));
        driver.findElement(By.partialLinkText("Google"));
        driver.findElement(By.partialLinkText("Sign in with Google"));
    }

    @Test
    public void TC_07_CSS() {
        // CSS vs ID
        driver.findElement(By.cssSelector("input[id='email']"));
        driver.findElement(By.cssSelector("input#email"));
        driver.findElement(By.cssSelector("#email"));

        // CSS vs Class
        driver.findElement(By.cssSelector("div[class='ng-star-inserted']"));
        driver.findElement(By.cssSelector("div.ng-star-inserted"));
        driver.findElement(By.cssSelector(".text-center"));

        // CSS vs Name
        //driver.findElement(By.cssSelector("input[name='viewport']"));

        // CSS vs Tagname
        driver.findElement(By.cssSelector("input"));

        // CSS vs Link
        driver.findElement(By.cssSelector("a[href='/authentication/login']"));

        // CSS vs Partial Link
        driver.findElement(By.cssSelector("a[href='/authentication/login']"));
    }

    //@Test
    public void TC_08_XPath() {
        // XPath vs ID
        //driver.findElement(By.xpath("//input[@id='email'"));

        // XPath vx Class
        //driver.findElements(By.xpath("//div[@class='text-center'"));

        // XPath vs Name
        //driver.findElement(By.xpath("//input[@email='Email'"));

        // XPath vs tagname
        driver.findElement(By.xpath("//input"));

        // XPath vs link
        driver.findElement(By.xpath("//a[@href=/authentication/login"));
        driver.findElement(By.xpath("//a[text()='Logg in']"));

        // XPath vs Partial link
        driver.findElement(By.xpath("//a[contains(@href,'authentication')]"));
        driver.findElement(By.xpath("//a[contains(text(),'log in)]"));
    }

    @AfterClass
    public void afterClass() {
        ///driver.quit();
    }
}


