package main.java.Page;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class MakePaymentPage {
    private AppiumDriver<? extends MobileElement> driver;
    private By phoneFieldBy;
    private By nameFieldBy;
    private By amountFieldBy;
    private By countryBy;
    private By sendPaymentBtnBy;
    private By yesBtnBy;
    
    public MakePaymentPage(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
        setLocators();
    }
    
    private void setLocators() {
    	String platform = getPlatformName();
        if (platform.equals("Android")) {
            // Android locators
        	 phoneFieldBy = By.id("com.experitest.ExperiBank:id/phoneTextField");
             nameFieldBy = By.id("com.experitest.ExperiBank:id/nameTextField");
             amountFieldBy = By.id("com.experitest.ExperiBank:id/amountTextField");
             countryBy = By.id("com.experitest.ExperiBank:id/countryTextField");
             sendPaymentBtnBy = By.id("com.experitest.ExperiBank:id/sendPaymentButton");
             yesBtnBy = By.id("android:id/button1");
        } else if (platform.equals("iOS")) {
            // iOS locators - update these based on your iOS app
        	phoneFieldBy = By.xpath("//*[@name='phoneTextField']");
        	nameFieldBy = By.xpath("//*[@name='nameTextField']");
        	amountFieldBy = By.xpath("//*[@name='amountTextField']");
        	countryBy = By.xpath("//*[@name='countryButton']");
        	sendPaymentBtnBy = By.xpath("//*[@name='sendPaymentButton']");
        	yesBtnBy = By.xpath("//*[@name='Yes']");
        }
    }
    
    public void fillInPhone(String phone) {
    	driver.findElement(phoneFieldBy).sendKeys(phone);
    }
    
    public void fillInName(String name) {
    	driver.findElement(nameFieldBy).sendKeys(name);
    }
    
    public void fillInAmount(String amount) {
    	driver.findElement(amountFieldBy).sendKeys(amount);
    }
    
    public void fillInCountry(String country) {
    	driver.findElement(countryBy).sendKeys(country);
    }
    
    public void clickSendPayment() {
    	driver.findElement(sendPaymentBtnBy).click();
    }
    
    public void clickYes() {
    	driver.findElement(yesBtnBy).click();
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