package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    final String goodsName = "Test.allTheThings() T-Shirt (Red)";

    @Test
    public void checkCart() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(cartPage.pageTitleDisplayed());
        productsPage.navigationPanel.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }

    @Test
    public void checkGoodsInCart() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.openCart();

        assertEquals(cartPage.getProductNames().size(), 1);
        assertFalse(cartPage.getProductNames().isEmpty());
        assertTrue(cartPage.getProductNames().contains(goodsName));
    }
}
