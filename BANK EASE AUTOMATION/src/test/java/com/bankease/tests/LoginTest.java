package com.bankease.tests;

import com.bankease.base.BaseTest;
import com.bankease.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // Test 1: Valid Login
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("mngr663868", "subEnEn"); // ← updated credentials

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("Managerhomepage"),
                "Login failed - Manager homepage not loaded!");

        System.out.println("Valid Login Test PASSED!");
    }

    // Test 2: Invalid Login
    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("wronguser", "wrongpass");

        String errorMsg = loginPage.getErrorMessage();
        System.out.println("Error message shown: " + errorMsg);

        Assert.assertTrue(errorMsg.contains("not valid"),
                "Error message mismatch!");

        System.out.println("Invalid Login Test PASSED!");
    }

    // Test 3: Empty Login
    @Test
    public void testEmptyLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("", "");              // ← Empty credentials
        
        String errorMsg = loginPage.getErrorMessage();  // ← Capture alert
        Assert.assertNotNull(errorMsg, "No error shown for empty login!");  // ← Verify error exists
    }
}