package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_11_Textbox_Exercise_2 {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String firstName, lastName, email, password, fullName, EmployeeID, passportNumber, passportComment;
    Random rand;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        rand =new Random(); //khởi tạo biến rand = hàm random

        firstName = "Anh";
        lastName = "Anh 2";
        email ="anh" + rand.nextInt(99999) + "@gmail.com"; // số random là số nguyên dương dưới 6 chữ số
        password = "Anh123456789";
        fullName =firstName + " " + lastName;
        passportNumber = "431276122";
        passportComment = "Check\npass";

    }

    @Test
    public void TC_01_Oranage_HRM() throws InterruptedException {

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // đăng nhập
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        //wait cho tất cả các icon loading biến mất
        Assert.assertTrue(isLoadingIconDisappear());


        //Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"); Không verify bằng URL

        //Kiểm tra trang PIM đã được direct thành công (kểm tra bằng cách header PIM đã hiển thị hay chưa)
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        //mở trang PIM
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        //Kiểm tra trang PIM đã được direct thành công (kểm tra bằng cách header PIM đã hiển thị hay chưa)
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='PIM']")).isDisplayed());

        //mở trang Add Employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        //create new Employee
        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);
        EmployeeID =  driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div/following-sibling::div/input")).getDomProperty("value");
        System.out.println(EmployeeID);


        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[text()='Username']//parent::div/following-sibling::div/input")).sendKeys(email);
        driver.findElement(By.xpath("//label[text()='Password']//parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']//parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();

        //verify success msg displayed
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed());

        // loading icon at Add Employee page
        Assert.assertTrue(isLoadingIconDisappear());

        // loading icon at detail page
        Assert.assertTrue(isLoadingIconDisappear());

        // personal detail page
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div/following-sibling::div/input")).getDomProperty("value"), EmployeeID);

        // immigration page
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(),'Add')]")).click();

        driver.findElement(By.xpath("//label[text()='Number']//parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']//parent::div/following-sibling::div/textarea")).sendKeys(passportComment);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();

        Thread.sleep(2000);

        //verify success msg displayed
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Successfully Saved']")).isDisplayed());
        Assert.assertTrue(isLoadingIconDisappear());


        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Passport']/parent::div/following-sibling::div/div[text()='" + passportNumber + "']")).isDisplayed());

        //logout
        driver.findElement(By.cssSelector("li.oxd-userdropdown")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        //login
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        //wait cho tất cả các icon loading biến mất
        Assert.assertTrue(isLoadingIconDisappear());

        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());

        // click My info page
        driver.findElement(By.xpath("//span[text()='My Info']/parent::a")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        // personal detail page
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName")).getDomProperty("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName")).getDomProperty("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div/following-sibling::div/input")).getDomProperty("value"), EmployeeID);

        Assert.assertTrue(driver.findElement(By.cssSelector("input[name='lastName")).isEnabled());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[name='firstName")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div/following-sibling::div/input")).isEnabled());

        // immigration page
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Assert.assertTrue(isLoadingIconDisappear());

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Passport']/parent::div/following-sibling::div/div[text()='" + passportNumber + "']")).isDisplayed());
    }

    private Boolean isLoadingIconDisappear() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.
                invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}