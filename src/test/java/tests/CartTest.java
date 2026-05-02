package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
        SoftAssert soft = new SoftAssert();

        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.openCart();

        soft.assertEquals(cartPage.getProductNames().size(), 1);
        soft.assertFalse(cartPage.getProductNames().isEmpty());
        soft.assertTrue(cartPage.getProductNames().contains(goodsName));

        soft.assertAll();
    }
}
