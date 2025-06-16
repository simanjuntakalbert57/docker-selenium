package com.demo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.abstractcomponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        // Constructor for LoginPage
        super(driver); // Call the constructor of AbstractComponent
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(id = "login")
    private WebElement loginButton;

    By emailErrorMessage = By.xpath("//input[@id='userEmail']/following-sibling::div//div[@class='ng-star-inserted']");
    By passwordErrorMessage = By.xpath("//input[@id='userPassword']/following-sibling::div//div[@class='ng-star-inserted']");

    public void loginApplication(String email, String password){
        userEmail.sendKeys(null == email ? "" : email);
        userPassword.sendKeys(null == password ? "" : password);
        loginButton.click();
    }

    public Boolean isEmailErrorMessageVisible(){
       return isElementPresent(emailErrorMessage);
    }

    public Boolean isPasswordErrorMessageVisible(){
       return isElementPresent(passwordErrorMessage);
    }

    public String getEmailErrorMessage() {
        return driver.findElement(emailErrorMessage).getText();
    }
    public String getPasswordErrorMessage() {
        return driver.findElement(passwordErrorMessage).getText();
    }

}
