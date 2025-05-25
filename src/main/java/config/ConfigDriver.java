package config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ConfigDriver {

    public WebDriver driver;

    // Default URL to be tested
    private String url = "http://13.126.83.12/index.php";

    // Method to initialize the driver, go to URL, and click the Register icon
    public void initializeAndGoToUrl() {
        // Automatically set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Initialize ChromeDriver (opens Chrome browser)
        driver = new ChromeDriver();

        // Navigate to the URL
        driver.get(url);

        // Wait for the page to load completely
        
    }
      
    public void goBackToRegisterPage() {
        try {
            // Wait for 3 seconds
            Thread.sleep(3000);  // Simple wait (not a best practice but works as per request)

            // Find and click the Register link
            WebElement registerLink = driver.findElement(By.xpath("//a[span[text()='Register']]"));
            registerLink.click();
            System.out.println("Clicked Register link successfully.");
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Optionally, close the driver after the test
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
