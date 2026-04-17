package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket",
                    "Sauce Labs Bolt T-Shirt");

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.pageTitleDisplayed());
        productsPage.addToCart();
        productsPage.addToCart("Sauce Labs Onesie");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");

        for (String goods : goodsList) {
            productsPage.addToCart(goods);
        }

        assertEquals(productsPage.checkRemoveBtn(), "Remove");
        assertEquals(productsPage.checkRemoveBtnBorder(), "1px solid rgb(226, 35, 26)");
        assertEquals(productsPage.checkCounterValue(), "6");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
