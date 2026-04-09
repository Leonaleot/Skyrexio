package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LoginTest extends BaseTest {
    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkIncorrectLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message fails to appear");
        assertEquals(loginPage.getErrorMsgValid(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void checkWithoutLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message fails to appear");
        assertEquals(loginPage.getErrorMsgValid(), "Epic sadface: Username is required");
    }

    @Test
    public void checkWithoutPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message fails to appear");
        assertEquals(loginPage.getErrorMsgValid(), "Epic sadface: Password is required");
    }

    @Test
    public void checkWithoutLoginPassword() {
        loginPage.open();
        loginPage.login("", "");
        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message fails to appear");
        assertEquals(loginPage.getErrorMsgValid(), "Epic sadface: Username is required");
    }

    @Test
    public void checkIncorrectPassword() {
        loginPage.open();
        loginPage.login("locked_out_user", "secretsauce");
        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message fails to appear");
        assertEquals(loginPage.getErrorMsgValid(), "Epic sadface: Username and password do not match any user in this service");
    }
}
