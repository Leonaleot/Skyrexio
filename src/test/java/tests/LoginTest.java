package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.*;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Khokhlova Tatiana @tataxox")
    @TmsLink("Skyrexio")
    @Issue("Skyrexio")
    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(dataProvider = "incorrectData")
    public void checkIncorrectLogin(User user, String errorMessage) {
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message fails to appear");
        assertEquals(loginPage.getErrorMsgValid(), errorMessage, "Error message doesn't correspond");
    }

    @DataProvider(name = "incorrectData")
    public Object[][] loginData() {
        return new Object[][]{
                {withIncorrectLoginPermission(), "Epic sadface: Username and password do not match any user in this service"},
                {withIncorrectPassPermission(), "Epic sadface: Username and password do not match any user in this service"},
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {withEmptyLoginPermission(), "Epic sadface: Username is required"},
                {withEmptyPassPermission(), "Epic sadface: Password is required"},
                {withEmptyLoginPassPermission(), "Epic sadface: Username is required"},
        };
    }
}
