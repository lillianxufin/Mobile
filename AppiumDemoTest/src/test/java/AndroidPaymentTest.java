package test.java;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import main.java.Gateway.MainGateway;

/**
 * Test class for Android login functionality using Appium
 * Tests both successful and failed login scenarios
 */
public class AndroidPaymentTest extends BaseTest {
    // Appium driver instance for Android automation
	protected AppiumDriver<? extends MobileElement> driver = null;

    private MainGateway mainGateway;
    private String username = "company";
    private String password = "company";
    private String name="John Doe";
    private String amount="23.2";
    private String country="Switzerland";
    private String phone="1111111";
    
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
    

    @Test
    public void testUserMakesAPayment() {
    	mainGateway.userLogIn(username, password);
    	double beforeBalance = Double.valueOf(mainGateway.getBalance());
        mainGateway.makeAPayment(phone, name, amount, country);
        Double afterBalance = Double.valueOf(mainGateway.getBalance());
        Double paidAmount = Double.valueOf(amount);
        Assertions.assertEquals(beforeBalance-paidAmount, afterBalance);
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