package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.*;

@Epic("Авторизация")
@Owner("Tatiana Khokhlova tanjakhokhlova@yandex.com")
public class LoginTest extends BaseTest {

    @Test
    @Feature("С валиднымии данными")
    @Story("Успешный вход в систему")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")
    public void checkLogin() {
        loginPage
                .open()
                .login(withAdminPermission());
        assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName());
    }

    @Test(dataProvider = "incorrectData")
    @Feature("С невалидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")
    public void checkIncorrectLogin(User user, String errorMessage, String story) {
        Allure.label("story", story);
        loginPage
                .open()
                .login(user);
        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message fails to appear");
        assertEquals(loginPage.getErrorMsgValid(), errorMessage, "Error message doesn't correspond");
    }

    @DataProvider(name = "incorrectData")
    public Object[][] loginData() {
        return new Object[][]{
                {withIncorrectLoginPermission(),
                        "Epic sadface: Username and password do not match any user in this service", "Неверный логин"},
                {withIncorrectPassPermission(),
                        "Epic sadface: Username and password do not match any user in this service", "Неверный пароль"},
                {withLockedPermission(),
                        "Epic sadface: Sorry, this user has been locked out.", "Заблокированный пользователь"},
                {withEmptyLoginPermission(),
                        "Epic sadface: Username is required", "Пустое поле Логин"},
                {withEmptyPassPermission(),
                        "Epic sadface: Password is required", "Пустое поле Пароль"},
                {withEmptyLoginPassPermission(),
                        "Epic sadface: Username is required", "Пустые поля Логин и Пароль"},
        };
    }
}
