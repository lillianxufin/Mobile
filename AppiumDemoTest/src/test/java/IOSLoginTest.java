package test.java;

import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.ScreenOrientation;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import main.java.Gateway.LoginGateway;

public class IOSLoginTest extends BaseTest {
	protected IOSDriver<IOSElement> driver = null;
    private LoginGateway loginGateway;
   
	@BeforeEach
	public void setUp() throws Exception {
		init("@os='ios'");
		// Init application / device capabilities
		
		dc.setCapability("testName", "IOSLoginTest");
		
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
		//dc.setCapability("appVersion", "1777");
		dc.setCapability("platformName", "ios");
		
		driver = new IOSDriver<>(new URL(getProperty("url",cloudProperties) + "/wd/hub"), dc);
		 // Initialize login gateway with driver instance
        loginGateway = new LoginGateway(driver);
	}

	@Test
    public void testUserLoginSuccess() {
    	driver.rotate(ScreenOrientation.PORTRAIT);
        loginGateway.userLogIn("company", "company");
    }

    @Test
    public void testUserLoginFail() {
    	driver.rotate(ScreenOrientation.PORTRAIT);
        loginGateway.userLogIn("company", "company1");
        loginGateway.errorAlertIsDisplayed();
    }

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

}
