package selenium.selenium_scenario.pom_impl;

import org.testng.annotations.Test;

import com.demo.base.BaseTest;
import com.demo.pageobjects.CartPage;
import com.demo.pageobjects.ConfirmationPage;
import com.demo.pageobjects.DashboardPage;
import com.demo.pageobjects.LoginPage;
import com.demo.pageobjects.OrderPage;

import groovyjarjarantlr4.v4.parse.ANTLRParser.notSet_return;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckoutTestImplPOM extends BaseTest{
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CartPage cartPage;
    OrderPage orderPage;
    ConfirmationPage confirmationPage;

    @BeforeClass
    public void setup() throws InterruptedException{
        super.setUp();
        // init class
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }

    @Test
    public void Login(){
        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Valid credentials test is running.");
        loginPage.loginApplication("simanjuntakalbert57@gmail.com", "XBf@rWNvByn!#K8");
        Assert.assertEquals(dashboardPage.getHomePageText(), "Automation Practice","Home page text does not match!");
    }


    @Test(dependsOnMethods = {"Login"})
    public void CheckoutScenarioTest() throws InterruptedException{
        String productName = "ZARA COAT 3";

        // DashboardPage
        dashboardPage.addToCart(productName);
        dashboardPage.clickOnCart();

        // Scenario Cart Page
        Assert.assertTrue(cartPage.verifyCheckoutProduct(productName), "Product not found in cart!");
        cartPage.goToCheckoutPage();

        // Scenario Order Page
        orderPage.selectCountry("Indonesia");
        orderPage.submitOrder();

        // Scenario Order Confirmation Page
        Assert.assertTrue(confirmationPage.getConfirmationMessage().contains("THANKYOU FOR THE ORDER."), "Order confirmation message not found!");
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after the test
        super.tearDown();
    }
}
