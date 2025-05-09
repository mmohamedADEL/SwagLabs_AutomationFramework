package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Utility;

public class LoginPage {
    private WebDriver driver;
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public LoginPage enterUsername(String username) {
        Utility.sendData(driver, usernameField, username);
        return this;
    }
    public LoginPage enterPassword(String password) {
        Utility.sendData(driver, passwordField, password);
        return this;
    }
    public HomePage  clickOnLoginButton() {
        Utility.clickOnElement(driver, loginButton);
        return new HomePage(driver);
    }
    public boolean assertHomeUrl(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals(expectedUrl);
    }
}
