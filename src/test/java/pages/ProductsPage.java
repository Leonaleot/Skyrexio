package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By addtoCartBtn = By.xpath("//*[text()='Add to cart']");
    private final By removeCartBtn = By.xpath("//*[text()='Remove']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean pageTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void addToCart() {
        driver.findElements(addtoCartBtn).get(0).click();
    }

    public String checkRemoveBtnBorder() {
        return driver.findElement(removeCartBtn).getCssValue("border");
    }

    public String checkRemoveBtn() {
        return driver.findElement(removeCartBtn).getText();
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
