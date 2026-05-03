package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class OverviewTest extends BaseTest {
    final List<String> goodsNames = List.of("Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack");

    @Test
    public void checkGoodsInOverview() {
        SoftAssert soft = new SoftAssert();
        loginPage.open();
        loginPage.login(withAdminPermission());
        goodsNames.forEach(productsPage::addToCart);
        productsPage.navigationPanel.openCart();
        cartPage.openCheckout();
        checkoutPage.fillForm("John", "Smith", "2435");
        checkoutPage.clickContinue();
        soft.assertEquals(overviewPage.getProductNames().size(), 2);
        soft.assertFalse(overviewPage.getProductNames().isEmpty());
        soft.assertTrue(overviewPage.getProductNames().containsAll(goodsNames));
        soft.assertAll();
    }

    @Test
    public void CompleteOrder() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        goodsNames.forEach(productsPage::addToCart);
        productsPage.navigationPanel.openCart();
        cartPage.openCheckout();
        checkoutPage.fillForm("John", "Smith", "2435");
        checkoutPage.clickContinue();
        overviewPage.clickFinish();
        assertTrue(completePage.pageTitleDisplayed());
        assertEquals(completePage.getTitle(), "Checkout: Complete!");
    }

}
