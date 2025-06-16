package selenium.selenium_scenario.pom_impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.demo.base.BaseTest;
import com.demo.pageobjects.DashboardPage;
import com.demo.pageobjects.LoginPage;

public class LoginImplPOM extends BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    
    @BeforeMethod
    public void setup() throws InterruptedException{
         // Setup WebDriver
        super.setUp();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test(priority = 1)
    public void validCredentials() throws InterruptedException {
        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Valid credentials test is running.");

        loginPage.loginApplication("simanjuntakalbert57@gmail.com", "XBf@rWNvByn!#K8");
        Assert.assertEquals(dashboardPage.getHomePageText(), "Automation Practice","Home page text does not match!");
    }

    @Test(priority = 1, dataProvider = "invalidCredentialsData")
    public void invalidCredentials(String email, String password, String emailError, String passwordError) throws InterruptedException {
        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Invalid credentials test is running.");

        /*
         * 1. Valid Email , Invalid Password
         * 2. Invalid Email , Valid Password
         * 3. Invalid Email , Invalid Password
         * 4. Empty Email , Invalid Password
         */

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginApplication(email, password);

        // Validate error messages
        // Validate email error
        if(loginPage.isEmailErrorMessageVisible()) {
            String emailErrorMessage = loginPage.getEmailErrorMessage();
            Assert.assertEquals(emailErrorMessage, emailError, "Email error message does not match!");
        }

        // Validate password error
        if (loginPage.isPasswordErrorMessageVisible()) {
            String passwordErrorMessage = loginPage.getPasswordErrorMessage();        
            Assert.assertEquals(passwordErrorMessage, passwordError, "Password error message does not match!");
        }
    }


    @DataProvider(name = "invalidCredentialsData")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
            {"simanjuntakalbert57@gmail.com","","","*Password is required"},
            {"simanjuntakalbert57","XBf@rWNvByn!#K8","*Enter Valid Email",""},
            {"simanjuntakalbert57","","*Enter Valid Email","*Password is required"},
            {"","vByn!#K8","*Email is required",""},
            {"","", "*Email is required", "*Password is required"}
        };
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after the test
        super.tearDown();
    }

}
