package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Register_Demo {

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
    public void Register_01_Emty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Bước 1:action
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//button[text()=\"ĐĂNG KÝ\" and @type=\"submit\"]")).click();

        // Bước 2:verify
        // cách 1:
        // khai báo biến: String emailAddressErrorMessage = driver.findElement(By.id("txtFirstname-error")).getText();
        //gán biến: Assert.assertEquals(emailAddressErrorMessage,"Vui lòng nhập họ tên");
        // cách 2 ngắn gọn hơn:
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại. ");
    }

    @Test
    public void Register_02_Invalid_Email_Address() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        // Bước 1:action
        driver.findElement(By.id("txtFirstname")).sendKeys("manh");
        driver.findElement(By.id("txtEmail")).sendKeys("a@12");
        driver.findElement(By.id("txtCEmail")).sendKeys("a@12");
        driver.findElement(By.id("txtPassword")).sendKeys("manh1234");
        driver.findElement(By.id("txtCPassword")).sendKeys("manh1234");
        driver.findElement(By.id("txtPhone")).sendKeys("09876543211");
        driver.findElement(By.xpath("//button[text()=\"ĐĂNG KÝ\" and @type=\"submit\"]")).click();

        // Bước 2:verify
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");


    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Bước 1:action
        driver.findElement(By.id("txtFirstname")).sendKeys("manh");
        driver.findElement(By.id("txtEmail")).sendKeys("anh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("anh1@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("manh1234");
        driver.findElement(By.id("txtCPassword")).sendKeys("manh1234");
        driver.findElement(By.id("txtPhone")).sendKeys("09876543211");
        driver.findElement(By.xpath("//button[text()=\"ĐĂNG KÝ\" and @type=\"submit\"]")).click();

        // Bước 2:verify
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void Register_04_Invalid_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Bước 1:action
        driver.findElement(By.id("txtFirstname")).sendKeys("manh");
        driver.findElement(By.id("txtEmail")).sendKeys("anh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("anh@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("manh");
        driver.findElement(By.id("txtCPassword")).sendKeys("manh");
        driver.findElement(By.id("txtPhone")).sendKeys("09876543211");
        driver.findElement(By.xpath("//button[text()=\"ĐĂNG KÝ\" and @type=\"submit\"]")).click();

        // Bước 2:verify
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 kí tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 kí tự");
    }

    @Test
    public void Register_05_Incorrect_Confirm_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Bước 1:action
        driver.findElement(By.id("txtFirstname")).sendKeys("manh");
        driver.findElement(By.id("txtEmail")).sendKeys("anh@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("anh@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("manh123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("manh");
        driver.findElement(By.id("txtPhone")).sendKeys("09876543211");
        driver.findElement(By.xpath("//button[text()=\"ĐĂNG KÝ\" and @type=\"submit\"]")).click();

        // Bước 2:verify
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu nhập lại không đúng");
    }

    @Test
    public void Register_06_Invalid_Phone_Number() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        // Bước 1:action
        driver.findElement(By.id("txtFirstname")).sendKeys("manh");
        driver.findElement(By.id("txtEmail")).sendKeys("a@12");
        driver.findElement(By.id("txtCEmail")).sendKeys("a@12");
        driver.findElement(By.id("txtPassword")).sendKeys("manh");
        driver.findElement(By.id("txtCPassword")).sendKeys("manh");
        // phone less than 10 chareters
        driver.findElement(By.id("txtPhone")).sendKeys("098765432");
        driver.findElement(By.xpath("//button[text()=\"ĐĂNG KÝ\" and @type=\"submit\"]")).click();

        // Bước 2:verify
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Số điện thoại phải từ 10-11 số");
        // phone more than 12 charaters
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("09876543211111");
        driver.findElement(By.xpath("//button[text()=\"ĐĂNG KÝ\" and @type=\"submit\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Số điện thoại phải từ 10-11 số");
        // phone's ISP wrong (nhà mạng ko hợp lệ)
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("01234567899");
        driver.findElement(By.xpath("//button[text()=\"ĐĂNG KÝ\" and @type=\"submit\"]")).click();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Số điện thoại bắt đầu bằng: 09-03-012-016-018-019-088-03-05-07-08");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}