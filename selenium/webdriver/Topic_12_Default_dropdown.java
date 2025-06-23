package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Default_dropdown {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Facebook_SignUp() {
        driver.get("https://www.facebook.com/reg/");

        // vào URL thành công, dropdown xuất hiện
        select = new Select(driver.findElement(By.cssSelector("select#day")));

        // chọn 1 option
        select.selectByVisibleText("25");

        // chọn xong verify lại xem đã chọn đúng giá trị mong muốn chưa
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"25");

        //verify xem dropdown có phải multiple ko
        // nếu là multiple --> trả về true
        // nếu là single --> trả về false
        Assert.assertFalse(select.isMultiple());

        //verify xem tổng số option trong list dropdown là bao nhiêu
        Assert.assertEquals(select.getOptions().size(),31);

    }

    @Test
    public void TC_02_NopCommerce() {
        driver.get("https://demo.nopcommerce.com/");

        driver.findElement(new By.ByCssSelector("a.ico-register")).click();

        driver.findElement(By.xpath("//input[@id='gender-female']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Minh");
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Minh2");
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("Minh@gmail.com");



    }

    @Test
    public void TC_03_Rode() {
        driver.get("https://www.rode.com/wheretobuy");
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.cssSelector("button.btn-default")).click();

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));
        Assert.assertEquals(dealers.size(),16);

        for(WebElement element: dealers) {
            System.out.println(element.getText());
        }


    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}