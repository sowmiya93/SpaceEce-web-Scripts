package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	 public LoginPage(WebDriver driver) {
	        this.driver = driver;
	    }
	public void setEmail(String email) {
        driver.findElement(By.xpath("//input[@type='email' and @name='email' and @placeholder='Enter Email']")).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(By.xpath("//input[@type='password' and @name='password' and @placeholder='Enter Password']")).sendKeys(password);
    }
    public void selectUserType(String userType) {
        WebElement userTypeDropdown = driver.findElement(By.xpath("//select[@name='type' or @id='user_type']"));
        Select select = new Select(userTypeDropdown);
        select.selectByVisibleText(userType);
    }
    public void fillLoginForm(String email, String password,String userType) {
       
        setEmail(email);
        setPassword(password);
        selectUserType(userType);
        
    }
    public void clickLoginButton() {
        driver.findElement(By.xpath("//button[@name='login']")).click();
   
    }
   
    public void clearField(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
    }
    public void clearLoginForm() {
        // Clear fields using the helper method
     
        clearField(By.xpath("//input[@placeholder='Enter Email']"));
        
        clearField(By.xpath("//input[@placeholder='Enter Password']"));
        
        System.out.println("Form fields cleared.");
    }
    
   public void Logoutbtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait until the dropdown link is visible after hovering
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='./spacece_auth/logout.php']")));

        logoutButton.click(); // Click the logout button
    }
    public String getErrorMessage(String xpath) {
        try {
            WebElement errorElement = driver.findElement(By.xpath(xpath));
            return errorElement.getText();
        } catch (Exception e) {
            return "No error message found.";
        }
    }
    }

