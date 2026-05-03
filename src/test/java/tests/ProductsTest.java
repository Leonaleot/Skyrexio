package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("Интернет-магазин")
@Feature("Каталог продуктов")
@Owner("Tatiana Khokhlova tanjakhokhlova@yandex.com")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket",
                    "Sauce Labs Bolt T-Shirt");

    @Test
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")
    public void checkGoodsAdded() {
        SoftAssert soft = new SoftAssert();
        loginPage
                .open()
                .login(withAdminPermission());
        assertTrue(productsPage.pageTitleDisplayed());
        productsPage.addToCart();
        productsPage.addToCart("Sauce Labs Onesie");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");

        for (String goods : goodsList) {
            productsPage.addToCart(goods);
        }
        soft.assertEquals(productsPage.checkRemoveBtn(), "Remove");
        Allure.step("Соответствие названии кнопки Remove");
        soft.assertEquals(productsPage.checkRemoveBtnBorder(), "1px solid rgb(226, 35, 26)");
        Allure.step("Соответствие цвета Border кнопки Remove");
        soft.assertEquals(productsPage.navigationPanel.checkCounterValue(), "6");
        Allure.step("На бейдже Корзины поялвился ярлычок с соответствующим числом добавленных товаров");
        soft.assertEquals(productsPage.navigationPanel.checkCounterColor(), "rgba(226, 35, 26, 1)");
        Allure.step("Соответствие цвета бейджа на корзине");
        soft.assertAll();
    }
}
