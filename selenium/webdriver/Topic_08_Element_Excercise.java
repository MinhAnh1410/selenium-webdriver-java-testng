package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_08_Element_Excercise {

    WebDriver driver;
    //WebElement element;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //element = driver.findElement(By.xpath(""));

    }

    @Test
    public void TC_01_Displaed()
            //isDisplayed: kiểm tra bất kì 1 element nào có hiển thị ko
            // nếu hiển thị tức là có thể nhìn thấy, có kích thước cụ thể
    {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement TextboxMail = driver.findElement(By.cssSelector("input[id='mail']"));

            if (TextboxMail.isDisplayed()) //nếu bến textboxMail tồn tại
            {
             System.out.println("Element email is displayed");  // in ra text E is displayed
             TextboxMail.sendKeys("Automation testing");
            }
            else {
            System.out.println("Element email is not displayed"); // nếu ko thì in ra text ko tồn tại
            }


        WebElement TickBoxUnder_18 = driver.findElement(By.cssSelector("input[id='under_18']"));
            if (TickBoxUnder_18.isDisplayed())
            { System.out.println("Element U18 is displayed");
                TickBoxUnder_18.click();
            }
            else { System.out.println("Element U18 is not displayed"); }

        WebElement TextboxEduction = driver.findElement(By.cssSelector("textarea[id='edu']"));

        if (TextboxEduction.isDisplayed())
        {
            System.out.println("Element edu is displayed");
            TextboxEduction.sendKeys("Automation testing");
        }
        else {
            System.out.println("Element edu is not displayed");
        }


        WebElement hoverUser = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

            if (hoverUser.isDisplayed()) {
                System.out.println("Element hoverUser is displayed");
            } else {
                System.out.println("Element hoverUser is not displayed");
            }
    }

    @Test
    public void TC_02_Enable()
    //isEnable: kiểm tra bất kì 1 element nào có th tương tác được ( ngược lại với read-only v disable)
    {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement TextboxMail = driver.findElement(By.cssSelector("input[id='mail']"));
            if (TextboxMail.isEnabled()) {
               System.out.println("Element mail is enable ");
            }
            else {
               System.out.println("Element mail is disable");
            }


        WebElement TickBoxUnder_18 = driver.findElement(By.cssSelector("input[id='under_18']"));
           if (TickBoxUnder_18.isEnabled()) {
            System.out.println("Element age is enable ");
           }
           else {
            System.out.println("Element age is disable");
           }

        WebElement password = driver.findElement(By.cssSelector("input[id='disable_password']"));
        if (password.isEnabled()) {
            System.out.println("Element password is enable ");
        }
        else {
            System.out.println("Element password is disable");
        }

        WebElement bio = driver.findElement(By.cssSelector("textarea[id='bio']']"));
        if (bio.isEnabled()) {
            System.out.println("Element bio is enable ");
        }
        else {
            System.out.println("Element bio is disable");
        }


    }

    @Test
    public void TC_03_Selected()
    //isSelected: kiểm tra bất kì 1 element nào có được chọn hay ko ( áp dụng với checkbox, tickbox,..)
    {
        WebElement TickBoxUnder_18 = driver.findElement(By.cssSelector("input[id='under_18']"));
        if (TickBoxUnder_18.isSelected()) {
            System.out.println("Element age is selected ");
        }
        else {
            System.out.println("Element age is de-selected");
        }

        WebElement TickBoxJava = driver.findElement(By.cssSelector("input[id='java']"));
        if (TickBoxJava.isSelected()) {
            System.out.println("Element java is enable ");
        }
        else {
            System.out.println("Element java is disable");
        }

    }

    @Test
    public void TC_04_MailChimp_Validate() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).sendKeys("anh@gmail.com");
        driver.findElement(By.id("email")).sendKeys(Keys.TAB);
        //Thread.sleep(2000);

        // nhập số vào password
        driver.findElement(By.id("new_password")).sendKeys("12345");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());

        //only lower case
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("auto");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        //Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='username-check completed']")).isDisplayed());





    }





    @AfterClass
        public void afterClass() {
        //driver.quit();
        }
}