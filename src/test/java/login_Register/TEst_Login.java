package login_Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import config.ConfigDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.TestHover;
import utils.DataValidation;
import utils.ExcelReader;

import java.io.IOException;
import java.time.Duration;

public class TEst_Login {
    WebDriver driver;
    LoginPage loginPage;
    ConfigDriver configDriver;
    HomePage homePage;
    TestHover testHover;

    @BeforeClass
    public void setup() {
        configDriver = new ConfigDriver();
        configDriver.initializeAndGoToUrl();
        driver = configDriver.driver;
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        testHover = new TestHover(driver); // Instantiate the TestHover class
    }

    @Test
    public void testRegister() throws IOException {
        homePage.LoginElement();
        String filePath = "D:\\1sowmi\\SPAC Internship\\Logindata.xlsx"; // Update file path here
        Object[][] excelData = ExcelReader.readExcelData(filePath);

        for (Object[] row : excelData) {
            String email = (String) row[0];
            String password = (String) row[1];
            String userType = (String) row[2];

            // Fill the form, but skip empty fields where required
            if (email != null && !email.isEmpty()) loginPage.setEmail(email);
            if (password != null && !password.isEmpty()) loginPage.setPassword(password);
            if (userType != null && !userType.isEmpty()) loginPage.selectUserType(userType);

            loginPage.clickLoginButton();

            if (isLoginSuccessful()) {
                System.out.println("Login successful, hovering over user name.");
                waitFor(2);

                // Call the hover method from TestHover class
                testHover.hoverOverUserName();

                waitFor(2);  // Wait for the dropdown to be visible

                System.out.println("Hover action performed, attempting logout.");
                loginPage.Logoutbtn();  // Logout after successful login
            } else {
                captureLoginErrors(email, password, userType);
                waitFor(2);
                loginPage.clearLoginForm();
            }
        }
    }

    private boolean isDropdownVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(testHover.getDropdownContentElement())).isDisplayed();
        } catch (Exception e) {
            return false; // If the dropdown is not visible, hover failed
        }
    }

    private boolean isLoginSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement profileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@style='cursor: pointer;']")));
            System.out.println("Login is successful.");
            return profileElement.isDisplayed();
        } catch (Exception e) {
            return false; // If the element is not found, login failed
        }
    }

    private void captureLoginErrors(String email, String password, String userType) {
        if (email == null || email.isEmpty()) {
            String emailError = loginPage.getErrorMessage("//div[text()='Invalid email or password']");
            System.out.println("Email Error: " + emailError);
        } else if (!DataValidation.isValidEmail(email)) {
            String emailError = loginPage.getErrorMessage("//label[@id='email-error']");
            System.out.println("Email Error: " + emailError);
        }

        if (password == null || password.isEmpty()) {
            String passwordError = loginPage.getErrorMessage("//div[text()='Invalid email or password']");
            System.out.println("Password Error: " + passwordError);
        }
        waitFor(2);
    }

    private void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
