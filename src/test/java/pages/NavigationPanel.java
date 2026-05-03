package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pages.BasePage.DATA_TEST_PATTERN;

public class NavigationPanel {
    private final WebDriver driver;
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));
    private final By cartBadge = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));

    public NavigationPanel(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получение количества товаров на иконке Корзина")
    public String checkCounterValue() {
        return driver.findElement(cartBadge).getText();
    }

    @Step("Получение цвета фона бейджа на иконке Корзина")
    public String checkCounterColor() {
        return driver.findElement(cartBadge).getCssValue("background-color");
    }

    @Step("Открытие корзины")
    public void openCart() {
        driver.findElement(cartLink).click();
    }
}
