package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Relative_Locator {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Relative() {
        driver.get("https://www.nopcommerce.com/en/login?returnUrl=%2Fen%2Fregister");
        //define button Login (2 kiểu: dùng BY hoặc WebElenment)
        By loginButtonby = By.cssSelector("btn blue-button");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("btn blue-button"));

        //define Remember me checkbox
        By rememberMeCheckBoxBy = By.id("RememberMe");

        //define textlink forgot pass
        //By linkForgotPass = By.linkText("/en/password-recovery");
                WebElement linkForgotPass = driver.findElement(By.linkText("/en/password-recovery"));

        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                        .above(loginButtonby)
                        .toRightOf(rememberMeCheckBoxBy)
                        .toLeftOf(linkForgotPass));
        System.out.println(rememberMeTextElement.getText());

        RelativeLocator.with(By.tagName("label"));

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}