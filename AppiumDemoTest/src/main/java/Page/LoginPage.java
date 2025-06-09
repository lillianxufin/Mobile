package main.java.Page;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class LoginPage {
    private AppiumDriver<? extends MobileElement> driver;
    private By usernameField;
    private By passwordField;
    private By loginButton;
    
    public LoginPage(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
        setLocators();
    }
    
    private void setLocators() {
    	String platform = getPlatformName();
        if (platform.equals("Android")) {
            // Android locators
            usernameField = By.id("usernameTextField");
            passwordField = By.id("passwordTextField");
            loginButton = By.id("loginButton");
        } else if (platform.equals("iOS")) {
            // iOS locators - update these based on your iOS app
            usernameField = By.id("usernameTextField");
            passwordField = By.id("passwordTextField");
            loginButton = By.id("loginButton");
        }
    }
    
    public void sendUserName(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }
    
    public void sendPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }
    
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    
    // Method to get platform type for platform-specific operations if needed
    public String getPlatformName() {
        if (driver instanceof AndroidDriver) {
            return "Android";
        } else if (driver instanceof IOSDriver) {
            return "iOS";
        } else {
            return "Unknown";
        }
    }
}