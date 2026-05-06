package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By product = By.cssSelector(".inventory_item_name");
    private final By continueShopBtn = By.id("continue-shopping");
    private final By checkoutBtn = By.cssSelector(DATA_TEST_PATTERN.formatted("checkout"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Отображение заголовка страницы YourCart")
    public boolean pageTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    @Step("Получение заголовка страницы YourCart")
    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    @Step("Получение списка продуктов в корзине")
    public ArrayList<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShopBtn));
        List<WebElement> allProducts = driver.findElements(product);
        ArrayList<String> names = new ArrayList<>();

        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }

    @Step("Нажатие кнопки Checkout")
    public CheckoutPage openCheckout() {
        driver.findElement(checkoutBtn).click();
        return new CheckoutPage(driver);
    }
}
