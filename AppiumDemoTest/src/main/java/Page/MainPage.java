package main.java.Page;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class MainPage {
    private AppiumDriver<? extends MobileElement> driver;
    private By makePaymentBtnBy;
    private By balanceBy;
    
    public MainPage(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
        setLocators();
    }
    
    private void setLocators() {
    	String platform = getPlatformName();
        if (platform.equals("Android")) {
            // Android locators
        	makePaymentBtnBy = By.id("makePaymentButton");
        	balanceBy = By.xpath("//*[contains(@text, 'Your balance is:')]");
        } else if (platform.equals("iOS")) {
            // iOS locators - update these based on your iOS app
        	makePaymentBtnBy = By.xpath("//*[@name='makePaymentButton']");
        }
    }
    
    public void clickMakePayment() {
    	driver.findElement(makePaymentBtnBy).click();
    }
    
    public String getBalance() {
    	MobileElement balanceText = driver.findElement(balanceBy);
    	String fullText = balanceText.getText(); 
    	String balanceAmount = fullText.substring(fullText.indexOf(":") + 1, fullText.length()-1);
    	return balanceAmount;
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