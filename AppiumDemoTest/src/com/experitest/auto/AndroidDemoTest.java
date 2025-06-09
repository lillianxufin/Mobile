package com.experitest.auto;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.gateway.LoginGateway;

public class AndroidDemoTest extends BaseTest {
	protected AppiumDriver<? extends MobileElement> driver = null;
    private LoginGateway loginGateway;
    private WebDriverWait wait;
    @BeforeEach
    public void setUp() throws Exception {
        init("@os='android'");
        
        dc.setCapability("app", "cloud:com.experitest.ExperiBank/.LoginActivity");
        dc.setCapability("appPackage", "com.experitest.ExperiBank");
        dc.setCapability("appActivity", ".LoginActivity");
        dc.setCapability("testName", "AndroidDemoTest");
        dc.setCapability("platformName", "Android");
        
        driver = new AndroidDriver<AndroidElement>(new URL(getProperty("url", cloudProperties) + "/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        loginGateway = new LoginGateway(driver);
        wait = new WebDriverWait(driver, 10L);
    }

    @Test
    public void testUserLoginSuccess() {
        loginGateway.userLogIn("company", "company");
    }

    @Test
    public void testUserLoginFail() {
        loginGateway.userLogIn("company", "company1");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("android:id/alertTitle"))));
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}