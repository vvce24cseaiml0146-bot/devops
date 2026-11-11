
package selenium;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class hello {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        String page = new File("webpage/login.html")
                .getAbsoluteFile()
                .toURI()
                .toString();

        driver.get(page);
    }

    @Test
    public void testValidLogin() {

        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.id("loginButton")).click();

        String actualMessage =
                driver.findElement(By.id("message")).getText();

        Assert.assertEquals(actualMessage, "Login Successful");
    }

    @Test
    public void testInvalidLogin() {

        driver.findElement(By.id("username")).sendKeys("wrong");
        driver.findElement(By.id("password")).sendKeys("wrong123");
        driver.findElement(By.id("loginButton")).click();

        String actualMessage =
                driver.findElement(By.id("message")).getText();

        Assert.assertEquals(
                actualMessage,
                "Invalid Username or Password"
        );
    }

    @Test
    public void testBackgroundColor() {

        WebElement body = driver.findElement(By.tagName("body"));

        String actualColor =
                body.getCssValue("background-color");

        Assert.assertEquals(
                actualColor,
                "rgba(173, 216, 230, 1)"
        );
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);

        if (driver != null) {
            driver.quit();
        }
    }
}
