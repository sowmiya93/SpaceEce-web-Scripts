package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with the fields
    public void setName(String name) {
        driver.findElement(By.xpath("//input[@id='name' and @name='name' and @placeholder='Enter Name']")).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys(email);
    }

    public void setMobile(String mobile) {
        driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(mobile);
    }

    public void setPassword(String password) {
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword) {
        driver.findElement(By.xpath("//input[@placeholder='Confirm Password']")).sendKeys(confirmPassword);
    }

    public void uploadFile(String filePath) {
        WebElement uploadField = driver.findElement(By.xpath("//input[@type='file' and @id='image' and @name='image']"));
        uploadField.sendKeys(filePath);
    }

    public void selectUserType(String userType) {
        WebElement userTypeDropdown = driver.findElement(By.xpath("//select[@id='user_type']"));
        Select select = new Select(userTypeDropdown);
        select.selectByVisibleText(userType);
    }

    public void clickRegisterButton() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    public void clearField(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
    }
    // Method to clear all form fields
    public void clearForm() {
        // Clear fields using the helper method
        clearField(By.xpath("//input[@placeholder='Enter Name']"));
        clearField(By.xpath("//input[@placeholder='Enter Email']"));
        clearField(By.xpath("//input[@id='phone']"));
        clearField(By.xpath("//input[@placeholder='Enter Password']"));
        clearField(By.xpath("//input[@placeholder='Confirm Password']"));
        clearField(By.xpath("//input[@type='file']"));
        
        System.out.println("Form fields cleared.");
    }
    // Retrieve error message for a specific field
    public String getErrorMessage(String xpath) {
        WebElement errorElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath )));
        return errorElement.getText();
    }
    public void Emailexists() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement Emailexist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'toastify') and contains(text(), 'Email already exists!')]")));
            		

            if (Emailexist.isDisplayed()) {
                System.out.println("Error: Email is already registered.");
                clearForm();  // Clears the form when email is already registered
            }
        } catch (Exception e) {
            System.out.println("............");
        }
    }

    public void fillForm(String name, String email, String mobile, String password, String confirmPassword, String filePath, String userType) {
        setName(name);
        setEmail(email);
        setMobile(mobile);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        uploadFile(filePath);
        selectUserType(userType);
        clickRegisterButton();
    }
}

