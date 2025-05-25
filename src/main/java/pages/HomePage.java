package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	 WebDriver driver;
	 public HomePage(WebDriver driver) {
	        this.driver = driver;
	    }
	public void RegisterElement() {
      	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      // Wait for the Register link to be visible and clickable
      WebElement registerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='./spacece_auth/register.php']//span[text()='Register']")));

      // Click the Register link to go to the register page
      registerLink.click();
  }
	public void LoginElement() {
      	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      // Wait for the Register link to be visible and clickable
      WebElement LoginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='./spacece_auth/login.php']")));
      // Click the Register link to go to the register page
      LoginLink.click();
      }
	
}
