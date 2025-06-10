package test.java;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import main.java.Gateway.MainGateway;

import org.openqa.selenium.ScreenOrientation;

/**
 * Test class for Android login functionality using Appium
 * Tests both successful and failed login scenarios
 */
public class AndroidLoginTest extends BaseTest {
    // Appium driver instance for Android automation
	protected AppiumDriver<? extends MobileElement> driver = null;
    
    // Gateway class for login-related operations
    private MainGateway mainGateway;
    
    /**
     * Set up method executed before each test
     * Initializes driver, capabilities, and test dependencies
     */
    @BeforeEach
    public void setUp() throws Exception {
        // Initialize base test configuration for Android OS
        init("@os='android'");
        
        // Configure Android app capabilities
        // Specify the cloud-based app location and entry point activity
        dc.setCapability("app", "cloud:com.experitest.ExperiBank/.LoginActivity");
        
        // Set the Android app package identifier
        dc.setCapability("appPackage", "com.experitest.ExperiBank");
        
        // Define the main activity to launch when app starts
        dc.setCapability("appActivity", ".LoginActivity");
        
        // Set custom test name for reporting and identification
        dc.setCapability("testName", "AndroidLTest");
        
        // Specify target platform as Android
        dc.setCapability("platformName", "Android");
        
        // Create AndroidDriver instance with cloud URL and capabilities
        driver = new AndroidDriver<AndroidElement>(new URL(getProperty("url", cloudProperties) + "/wd/hub"), dc);
        
        // Set implicit wait timeout to 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        // Initialize login gateway with driver instance
        mainGateway = new MainGateway(driver);
    }
    
    /**
     * Test case for successful user login
     * Verifies login with valid credentials
     */
    @Test
    public void testUserLoginSuccess() {
        // Set device orientation to portrait mode
    	driver.rotate(ScreenOrientation.PORTRAIT);
        
        // Perform login with valid credentials (username: "company", password: "company")
        mainGateway.userLogIn("company", "company");
    }
    
    /**
     * Test case for failed user login
     * Verifies error handling with invalid credentials
     */
    @Test
    public void testUserLoginFail() {
        // Set device orientation to portrait mode
    	driver.rotate(ScreenOrientation.PORTRAIT);
        
        // Attempt login with invalid credentials (username: "company", password: "company1")
        mainGateway.userLogIn("company", "company1");
        
        // Verify that error alert is displayed after failed login attempt
        mainGateway.errorAlertIsDisplayed();
    }
    
    /**
     * Cleanup method executed after each test
     * Ensures proper driver cleanup to prevent resource leaks
     */
    @AfterEach
    public void tearDown() {
        // Quit driver if it was initialized
        if (driver != null) {
            driver.quit();
        }
    }
}