package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static enums.TitleNaming.COMPLETE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("Оформление заказа")
@Owner("Tatiana Khokhlova tanjakhokhlova@yandex.com")
public class OverviewTest extends BaseTest {
    final List<String> goodsNames =
            List.of("Test.allTheThings() T-Shirt (Red)",
                    "Sauce Labs Backpack");

    @Test
    @Feature("Проверка соответствия товаров в заказе")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")
    public void checkGoodsInOverview() {
        SoftAssert soft = new SoftAssert();
        loginPage.open();
        loginPage.login(withAdminPermission());
        goodsNames.forEach(productsPage::addToCart);
        productsPage.navigationPanel.openCart();
        cartPage.openCheckout();
        checkoutPage.fillForm("John", "Smith", "2435");
        checkoutPage.clickContinue();
        soft.assertFalse(overviewPage.getProductNames().isEmpty());
        Allure.step("Список заказа не пуст");
        soft.assertEquals(overviewPage.getProductNames().size(), 2);
        Allure.step("Проверка количества товаров в списке заказа");
        soft.assertTrue(overviewPage.getProductNames().containsAll(goodsNames));
        Allure.step("Проверка соответствия содержимого списка заказов по имени товара");
        soft.assertAll();
    }

    @Test
    @Feature("Успешное оформление заказа")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemoo")
    @Issue("Saucedemoo")
    public void CompleteOrder() {
        loginPage
                .open()
                .login(withAdminPermission());
        goodsNames.forEach(productsPage::addToCart);
        productsPage.navigationPanel.openCart();
        cartPage.openCheckout();
        checkoutPage.fillForm("John", "Smith", "2435");
        checkoutPage.clickContinue();
        overviewPage.clickFinish();
        assertTrue(completePage.pageTitleDisplayed());
        assertEquals(completePage.getTitle(), COMPLETE.getDisplayName());
    }

}
