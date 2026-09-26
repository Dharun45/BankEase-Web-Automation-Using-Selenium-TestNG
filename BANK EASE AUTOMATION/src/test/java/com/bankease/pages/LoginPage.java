package com.bankease.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    // Step 1: Store the locators (where are the elements on the page?)
    By usernameField = By.name("uid");
    By passwordField = By.name("password");
    By loginButton   = By.name("btnLogin");
    By errorMessage  = By.className("barone");

    // Step 2: Constructor - receive driver from test class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Step 3: Actions (what can user do on login page?)
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
    
    public String getErrorMessage() {
        try {
            // ✅ Wait for alert to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            org.openqa.selenium.Alert alert = 
                wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (Exception e) {
            return driver.findElement(errorMessage).getText();
        }
    }

    // Step 4: Combined method - do full login in one line
    public void doLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}