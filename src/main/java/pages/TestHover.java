package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestHover {
    WebDriver driver;

    public TestHover(WebDriver driver) {
        this.driver = driver;
    }

    public void hoverOverUserName() {
        WebElement userNameElement = driver.findElement(By.xpath("//button[@class='dropbtn']/span[@style='cursor: pointer;']"));
        Actions actions = new Actions(driver);

        // Hover over the user name
        actions.moveToElement(userNameElement).perform();

        // Wait for the dropdown to become visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdown-content']"))); // Wait for dropdown to appear
    }

    public WebElement getDropdownContentElement() {
        return driver.findElement(By.xpath("//div[@class='dropdown-content']"));
    }
}
