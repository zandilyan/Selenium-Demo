package selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FirstGitFile {
    public WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})

    public void test1(@Optional("browser") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "usr/bin/chromedriver");
//            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--headless");
            option.addArguments("window-size=1920,1080");
            option.addArguments("--disable-gpu");
            driver = new ChromeDriver(option);
            driver.get("https://www.saucedemo.com/");
        }
    }

    @Test
    public void TEST093050() throws InterruptedException, IOException {
        System.out.println(driver.getTitle());
        String ActualTitle = driver.getTitle();
        String ExpectedTitle = "Swag Labs";
        Assert.assertEquals(ExpectedTitle, ActualTitle);
        File LoginPageSS = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(LoginPageSS, new File("target/SS/Loginpage.png"));
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(1000);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("target/SS/screenshot.png"));
        System.out.println("Logged in");
        Select SortOption = new Select(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")));
        SortOption.selectByValue("za");
        System.out.println("Sorting done");
        WebElement saucelabsBackpackA2C = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        saucelabsBackpackA2C.click();
        System.out.println("Added to cart");
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Sandi");
        driver.findElement(By.id("last-name")).sendKeys("Srini");
        driver.findElement(By.id("postal-code")).sendKeys("621802");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        System.out.println("Checked out Successfully");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,5000)");
        System.out.println("Tests done successfully");
        Thread.sleep(2000);
        driver.close();
    }
}

