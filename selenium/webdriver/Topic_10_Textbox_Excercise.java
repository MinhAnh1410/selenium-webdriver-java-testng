package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_10_Textbox_Excercise {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String firstName, lastName, email, password, fullName;
    Random rand; //khai báo biến random

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        rand =new Random(); //khởi tạo biến rand = hàm random

        firstName = "Anh";
        lastName = "Anh 2";
        email ="anh" + rand.nextInt(99999) + "@gmail.com"; // số random là số nguyên dương dưới 6 chữ số
        password = "123456789";
        fullName =firstName + " " + lastName;
    }

    @Test
    public void TC_01_Textbox() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        // register
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
       // Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
       // Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),
                "Thank you for registering with Main Website Store.");

        String contactInformationText = driver.findElement(By.xpath(
                "//h3[text()='Contact Information']//parent::div/following-sibling::div/p")).getText();

        System.out.println(contactInformationText);
        Assert.assertTrue(contactInformationText.contains(email) && contactInformationText.contains(fullName));

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"),email);

        // add review
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//h2//a[@title='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field"))
                .sendKeys("Tập đoàn luôn nỗ lực để đem đến các sản phẩm chất lượng, đổi mới công nghệ và giữ vững vị thế hàng đầu.\n" +
                "\n" +
                "Điện thoại Samsung vẫn luôn chiếm lĩnh thị trường Việt Nam không chỉ bởi sự đa dạng mẫu mã, sản phẩm mà còn nhờ độ bền, độ chắc chắn, khả năng sử dụng lâu dài của nó. ");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Good phone");
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys("Manh");
        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(),
                "Your review has been accepted for moderation.");


        // log out
        driver.findElement(By.xpath("//a[@class='skip-link skip-account skip-active']")).click();
        driver.findElement(By.xpath("a[title='Log Out']")).click();

        Thread.sleep(6000);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

    }


    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}