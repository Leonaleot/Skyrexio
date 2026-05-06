package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.OverviewPage;

import static enums.TitleNaming.CHECKOUT;
import static enums.TitleNaming.OVERVIEW;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("Оформление заказа")
@Owner("Tatiana Khokhlova tanjakhokhlova@yandex.com")
public class CheckoutTest extends BaseTest {
    final String goodsName = "Test.allTheThings() T-Shirt (Red)";

    @Test
    @Story("Проверка открытии страницы Checkout: Your Information")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")
    public void checkCheckout() {
        CheckoutPage checkoutPage = loginPage
                .open()
                .login(withAdminPermission())
                .addToCart(goodsName)
                .navigationPanel
                .openCart()
                .openCheckout();
        assertTrue(checkoutPage.pageTitleDisplayed());
        assertEquals(checkoutPage.getTitle(), CHECKOUT.getDisplayName());
    }

    @Test
    @Feature("Оформление заказа с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")
    public void checkCorrectCheckout() {
        OverviewPage overviewPage = loginPage
                .open()
                .login(withAdminPermission())
                .addToCart(goodsName)
                .navigationPanel
                .openCart()
                .openCheckout()
                .fillForm("John", "Smith", "2435")
                .clickContinueSuccess();
        assertTrue(overviewPage.pageTitleDisplayed());
        assertEquals(overviewPage.getTitle(), OVERVIEW.getDisplayName());
    }

    @Test(dataProvider = "invalidData")
    @Feature("Оформление заказа с невалидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")

    public void invalidCheckoutTest(String firstName, String lastName, String postalCode, String errorMessage, String story) {
        Allure.label("story", story);
        CheckoutPage checkoutPage = loginPage
                .open()
                .login(withAdminPermission())
                .addToCart(goodsName)
                .navigationPanel
                .openCart()
                .openCheckout()
                .fillForm(firstName, lastName, postalCode)
                .clickContinue();
        assertTrue(checkoutPage.isErrorMsgDisplayed(), "The error message fails to appear");
        assertEquals(checkoutPage.getErrorMessage(), errorMessage, "Error message doesn't correspond");
    }

    @DataProvider(name = "invalidData")
    public Object[][] getInvalidData() {
        return new Object[][]{
                {"", "Smirnov", "0872", "Error: First Name is required", "Пустое поле First Name"},
                {"Ivan", "", "22", "Error: Last Name is required", "Пустое поле Last Name"},
                {"Imran", "Shirokov", "", "Error: Postal Code is required", "Пустое поле PostalCode"},
                {"", "", "", "Error: First Name is required", "Все поля пустые"}
        };
    }
}
