package selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
            option.addArguments("--no-sandbox");
            driver = new ChromeDriver();
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
        WebDriverWait wait = new WebDriverWait(driver, 70);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name")));
        WebElement Username = driver.findElement(By.id("user-name"));
        Username.sendKeys("standard_user");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        WebElement Password = driver.findElement(By.id("password"));
        Password.sendKeys("secret_sauce");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
        WebElement Submit = driver.findElement(By.id("login-button"));
        Submit.click();
        Thread.sleep(1000);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("target/SS/screenshot.png"));
        System.out.println("Logged in");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")));
        Select SortOption = new Select(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")));
        SortOption.selectByValue("za");
        System.out.println("Sorting done");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
        WebElement saucelabsBackpackA2C = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        saucelabsBackpackA2C.click();
        System.out.println("Added to cart");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"shopping_cart_container\"]/a")));
        WebElement Cart = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        Cart.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        WebElement Checkout = driver.findElement(By.id("checkout"));
        Checkout.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("first-name")));
        WebElement Firstname = driver.findElement(By.id("first-name"));
        Firstname.sendKeys("Sandi");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("last-name")));
        WebElement Lastname = driver.findElement(By.id("last-name"));
        Lastname.sendKeys("Srini");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("postal-code")));
        WebElement ZIP = driver.findElement(By.id("postal-code"));
        ZIP.sendKeys("621802");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        WebElement Continue = driver.findElement(By.id("continue"));
        Continue.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        WebElement Finish = driver.findElement(By.id("finish"));
        Finish.click();
        System.out.println("Checked out Successfully");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0,5000)");
        System.out.println("Tests done successfully");
        Thread.sleep(2000);
        driver.close();
    }
}

