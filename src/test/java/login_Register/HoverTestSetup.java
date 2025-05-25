package login_Register;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.ConfigDriver;

import java.time.Duration;

public class HoverTestSetup {

    WebDriver driver;
    ConfigDriver configDriver;

    @BeforeClass
    public void setup() {
        configDriver = new ConfigDriver();
        configDriver.initializeAndGoToUrl(); // Initialize WebDriver and navigate to the URL
        driver = configDriver.driver;
    }

    @Test
    public void testHover() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the user avatar to be visible
        WebElement userNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class='dropbtn']/img[@class='user_avatar']")));

        // Perform hover action
        Actions actions = new Actions(driver);
        actions.moveToElement(userNameElement).perform();

        System.out.println("Hover action performed successfully.");

        // Validate if the dropdown appears
        WebElement dropdownContent = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='dropdown-content']")));
        if (dropdownContent.isDisplayed()) {
            System.out.println("Dropdown is visible after hover.");
        } else {
            System.out.println("Dropdown did not appear.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
