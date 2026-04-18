package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pages.BasePage.DATA_TEST_PATTERN;

public class NavigationPanel {
    private WebDriver driver;
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));
    private final By cartBadge = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));

    public NavigationPanel(WebDriver driver) {
        this.driver = driver;
    }

    public String checkCounterValue() {
        return driver.findElement(cartBadge).getText();
    }

    public String checkCounterColor() {
        return driver.findElement(cartBadge).getCssValue("background-color");
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }
}
