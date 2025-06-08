package main.java.gateway;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import main.java.Page.LoginPage;

public class LoginGateway {
	private AndroidDriver<AndroidElement> driver;
	private LoginPage loginPage;
	public LoginGateway(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		loginPage = new LoginPage(driver);
	}
	
	public void userLogIn(String username, String password) {
		loginPage.sendUserName(username);
		loginPage.sendPassword(password);
		loginPage.clickLoginButton();
	}
}
