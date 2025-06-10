package main.java.Gateway;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.java.Page.MainPage;

public class MainGateway {
    private AppiumDriver<? extends MobileElement> driver;
    private LoginGateway loginGateway;
    private PaymentGateway paymentGateway;
    private MainPage mainPage;
    
    // Constructor for AppiumDriver (handles both Android and iOS)
    public MainGateway(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
        loginGateway = new LoginGateway(driver);
        paymentGateway = new PaymentGateway(driver);
        mainPage = new MainPage(driver);
    }
    
    public void userLogIn(String username, String password) {
        loginGateway.userLogIn(username, password);
    }
    
    public void errorAlertIsDisplayed() {
    	loginGateway.errorAlertIsDisplayed();
    }
    
    public void makeAPayment(String phone, String name, String amount, String country) {
    	mainPage.clickMakePayment();
    	paymentGateway.makeAPayment(phone, name, amount, country);
    }
    
    public String getBalance() {
    	return mainPage.getBalance();
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