package pages;

import io.qameta.allure.Step;
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
    @Step("Отображение заголовка страницы")
    public boolean pageTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }
    @Step("Получение заголовка страницы")
    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }
    @Step("Добавление товара в корзину с помощью локатора с индексом")
    public void addToCart() {
        driver.findElements(addToCartBtn).get(0).click();
    }
    @Step("Добавление товара в корзину с помощью уникального локатора")
    public void addToCart(final String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));

        driver.findElement(addToCart).click();
    }
    @Step("Получение цвета обводки кнопки Remove")
    public String checkRemoveBtnBorder() {
        return driver.findElement(removeCartBtn).getCssValue("border");
    }
    @Step("Получение текста кнопки Remove")
    public String checkRemoveBtn() {
        return driver.findElement(removeCartBtn).getText();
    }
}
