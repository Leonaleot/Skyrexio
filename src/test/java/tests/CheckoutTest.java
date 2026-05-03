package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class CheckoutTest extends BaseTest {
    final String goodsName = "Test.allTheThings() T-Shirt (Red)";

    @Test
    public void checkCheckout() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.openCart();
        cartPage.openCheckout();
        assertTrue(checkoutPage.pageTitleDisplayed());
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }

    @Test
    public void checkCorrectCheckout() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.openCart();
        cartPage.openCheckout();
        checkoutPage.fillForm("John", "Smith", "2435");
        checkoutPage.clickContinue();
        assertTrue(overviewPage.pageTitleDisplayed());
        assertEquals(overviewPage.getTitle(), "Checkout: Overview");
    }

    @Test(dataProvider = "invalidData")
    public void invalidCheckoutTest(String firstName, String lastName, String postalCode, String errorMessage) {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.openCart();
        cartPage.openCheckout();
        checkoutPage.fillForm(firstName, lastName, postalCode);
        checkoutPage.clickContinue();
        assertTrue(checkoutPage.isErrorMsgDisplayed(), "The error message fails to appear");
        assertEquals(checkoutPage.getErrorMessage(), errorMessage, "Error message doesn't correspond");
    }

    @DataProvider(name = "invalidData")
    public Object[][] getInvalidData() {
        return new Object[][]{
                {"", "Smirnov", "0872", "Error: First Name is required"},
                {"Ivan", "", "22", "Error: Last Name is required"},
                {"Imran", "Shirokov", "", "Error: Postal Code is required"},
                {"", "", "", "Error: First Name is required"}
        };
    }
}
