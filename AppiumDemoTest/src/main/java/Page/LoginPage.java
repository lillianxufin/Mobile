package main.java.Page;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginPage {
	public By usernameBy = By.id("usernameTextField");
	public By pwdBy = By.id("passwordTextField");
	public By loginBtnBy = By.id("loginButton");
	
	private AndroidDriver<AndroidElement> driver;
	public LoginPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	public void sendUserName(String username) {
		driver.findElement(usernameBy).sendKeys(username);
	}
	public void sendPassword(String password) {
		driver.findElement(pwdBy).sendKeys(password);
	}
	public void clickLoginButton() {
		driver.findElement(loginBtnBy).click();
	}
}
