package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private final By pageTitle = By.cssSelector("[data-test='title']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean pageTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }
}
