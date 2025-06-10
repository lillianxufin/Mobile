package main.java.Gateway;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.java.Page.LoginPage;

public class LoginGateway {
    private AppiumDriver<? extends MobileElement> driver;
    private LoginPage loginPage;
    
    // Constructor for AppiumDriver (handles both Android and iOS)
    public LoginGateway(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }
    
    public void userLogIn(String username, String password) {
        loginPage.sendUserName(username);
        loginPage.sendPassword(password);
        loginPage.clickLoginButton();
    }
    
    public void errorAlertIsDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, 10L);
    	wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorAlertElement()));
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