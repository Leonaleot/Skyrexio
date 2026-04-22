package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART_PATTERN =
            "//div[text()='%s']//ancestor::div[@class='inventory_item']" +
                    "//child::button[text()='Add to cart']";
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));

    private final By addToCartBtn = By.xpath("//*[text()='Add to cart']");
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
        driver.findElements(addToCartBtn).get(0).click();
    }

    public void addToCart(final String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));

        driver.findElement(addToCart).click();
    }

    public String checkRemoveBtnBorder() {
        return driver.findElement(removeCartBtn).getCssValue("border");
    }

    public String checkRemoveBtn() {
        return driver.findElement(removeCartBtn).getText();
    }
}
