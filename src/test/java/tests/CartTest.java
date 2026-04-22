package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {
    final String goodsName = "Test.allTheThings() T-Shirt (Red)";

    @Test
    public void checkCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(cartPage.pageTitleDisplayed());
        productsPage.navigationPanel.openCart();
        assertEquals(cartPage.getTitle(), "Your Cart");
    }

    @Test
    public void checkGoodsInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.openCart();

        assertEquals(cartPage.getProductNames().size(), 1);
        assertFalse(cartPage.getProductNames().isEmpty());
        assertTrue(cartPage.getProductNames().contains(goodsName));
    }
}
