package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket",
                    "Sauce Labs Bolt T-Shirt");

    @Test
    public void checkGoodsAdded() {
        SoftAssert soft = new SoftAssert();
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.pageTitleDisplayed());
        productsPage.addToCart();
        productsPage.addToCart("Sauce Labs Onesie");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");

        for (String goods : goodsList) {
            productsPage.addToCart(goods);
        }

        soft.assertEquals(productsPage.checkRemoveBtn(), "Remove");
        soft.assertEquals(productsPage.checkRemoveBtnBorder(), "1px solid rgb(226, 35, 26)");
        soft.assertEquals(productsPage.navigationPanel.checkCounterValue(), "6");
        soft.assertEquals(productsPage.navigationPanel.checkCounterColor(), "rgba(226, 35, 26, 1)");
        soft.assertAll();
    }
}
