package main.java.Gateway;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import main.java.Page.MakePaymentPage;

public class PaymentGateway {
    private AppiumDriver<? extends MobileElement> driver;
    private MakePaymentPage paymentPage;
    
    // Constructor for AppiumDriver (handles both Android and iOS)
    public PaymentGateway(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
        paymentPage = new MakePaymentPage(driver);
    }
    
    public void makeAPayment(String phone, String name, String amount, String country) {
    	paymentPage.fillInPhone(phone);
    	paymentPage.fillInAmount(amount);
    	paymentPage.fillInCountry(country);
    	paymentPage.fillInName(name);
    	paymentPage.clickSendPayment();
    	paymentPage.clickYes();
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