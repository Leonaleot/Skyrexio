package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static enums.TitleNaming.CART;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

@Epic("Интернет-магазин")
@Feature("Корзина")
@Owner("Tatiana Khokhlova tanjakhokhlova@yandex.com")
public class CartTest extends BaseTest {
    final String goodsName = "Test.allTheThings() T-Shirt (Red)";

    @Test
    @Story("Проверка открытии корзины")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")
    public void checkCart() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(cartPage.pageTitleDisplayed());
        productsPage.navigationPanel.openCart();
        assertEquals(cartPage.getTitle(), CART.getDisplayName());
    }

    @Test
    @Story("Проверка соотвествия добавленных товаров в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")
    public void checkGoodsInCart() {
        SoftAssert soft = new SoftAssert();
        loginPage
                .open()
                .login(withAdminPermission());
        productsPage.addToCart(goodsName);
        productsPage.navigationPanel.openCart();

        soft.assertFalse(cartPage.getProductNames().isEmpty());
        Allure.step("Корзина не пуста");
        soft.assertEquals(cartPage.getProductNames().size(), 1);
        Allure.step("Проверка количества товаров в корзине");
        soft.assertTrue(cartPage.getProductNames().contains(goodsName));
        Allure.step("Проверка соответствия содержимого корзины по имени товара");
        soft.assertAll();
    }
}
