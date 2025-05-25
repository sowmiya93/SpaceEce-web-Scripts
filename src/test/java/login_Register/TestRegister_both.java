package login_Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.ConfigDriver;
import pages.HomePage;
import pages.RegisterPage;
import utils.DataValidation;
import utils.ExcelReader;
import java.io.IOException;

public class TestRegister_both {
    WebDriver driver;
    RegisterPage registerPage;
    ConfigDriver configDriver;
    HomePage homePage;

    @BeforeClass
    public void setup() {
        configDriver = new ConfigDriver();
        configDriver.initializeAndGoToUrl();
        driver = configDriver.driver;
        registerPage = new RegisterPage(driver);
        homePage =new HomePage(driver);
    }

    @Test
   
    public void testRegister() throws IOException {
    	homePage.RegisterElement();
        String filePath = "D:\\1sowmi\\SPAC Internship\\testData.xlsx"; // Update file path here
        Object[][] excelData = ExcelReader.readExcelData(filePath);

        for (Object[] row : excelData) {
            String name = (String) row[0];
            String email = (String) row[1];
            String mobile = (String) row[2];
            String password = (String) row[3];
            String confirmPassword = (String) row[4];
            String imagePath = (String) row[5];
            String userType = (String) row[6];

            // Fill the form, but skip empty fields where required
            if (name != null && !name.isEmpty()) registerPage.setName(name);
            if (email != null && !email.isEmpty()) registerPage.setEmail(email);
            if (mobile != null && !mobile.isEmpty()) registerPage.setMobile(mobile);
            if (password != null && !password.isEmpty()) registerPage.setPassword(password);
            if (confirmPassword != null && !confirmPassword.isEmpty()) registerPage.setConfirmPassword(confirmPassword);
            if (imagePath != null && !imagePath.isEmpty()) registerPage.uploadFile(imagePath);
            if (userType != null && !userType.isEmpty()) registerPage.selectUserType(userType);

            registerPage.clickRegisterButton();
            registerPage.Emailexists();
            // Validate the fields and capture errors
            boolean isValid = true;

         // Validate Name (if empty, show error message)
         if (name == null || name.isEmpty()) {
             System.out.println(registerPage.getErrorMessage("//input[@placeholder='Enter Name']"));
             isValid = false;
         } else if (!DataValidation.isValidName(name)) {
             System.out.println(registerPage.getErrorMessage("//label[@id='name-error' and @for='name' and contains(@class, 'error')]\r\n"
             		+ ""));
             isValid = false;
         }

         // Validate Email (if empty, show error message)
         if (email == null || email.isEmpty()) {
             System.out.println(registerPage.getErrorMessage("//label[@id='email-error']"));
             isValid = false;
         } else if (!DataValidation.isValidEmail(email)) {
             System.out.println(registerPage.getErrorMessage("//label[@id='email-error']"));
             isValid = false;
         }

         // Validate Mobile (if empty, show error message)
         if (mobile == null || mobile.isEmpty()) {
             System.out.println(registerPage.getErrorMessage("//label[@id='phone-error' and @for='phone']"));
             isValid = false;
         } else if (!DataValidation.isValidMobile(mobile)) {
             System.out.println(registerPage.getErrorMessage("//label[@id='phone-error' and @for='phone']"));
             isValid = false;
         }

         // Validate Password (if empty, show error message)
         if (password == null || password.isEmpty()) {
             System.out.println(registerPage.getErrorMessage("//label[@id='password-error']"));
             isValid = false;
         } else if (!DataValidation.isValidPassword(password)) {
             System.out.println(registerPage.getErrorMessage("//label[@id='password-error']"));
             isValid = false;
         }

         // Validate Confirm Password (if empty, show error message)
         if (confirmPassword == null || confirmPassword.isEmpty()) {
             System.out.println(registerPage.getErrorMessage("//label[@id='cpassword-error']"));
             isValid = false;
         } else if (!DataValidation.isPasswordConfirmed(password, confirmPassword)) {
             System.out.println(registerPage.getErrorMessage("//label[@id='cpassword-error']"));
             isValid = false;
         }

         // Validate File Upload (if empty, show error message)
         if (imagePath == null || imagePath.isEmpty()) {
             System.out.println(registerPage.getErrorMessage("//label[@id='image-error' and @for='image' and contains(@class, 'error')]"));
             isValid = false;
         } else if (!DataValidation.isFileValid(imagePath)) {
             System.out.println(registerPage.getErrorMessage("//label[@id='image-error' and @for='image' and contains(@class, 'error')]"));
             isValid = false;
         }


            // If valid, proceed to the login page (this will happen only when all fields are valid)
            if (isValid) {
                if (isLoginPageVisible()) {
                    configDriver.goBackToRegisterPage();
                }
            } else {
                // Clear form and continue to the next set of data
                waitFor(4);
                registerPage.clearForm();
            }

            // Wait before the next iteration
            waitFor(5);
        }
    }

    private boolean isLoginPageVisible() {
        try {
            WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }}

