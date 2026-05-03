package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class OverviewPage extends BasePage {
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By product = By.cssSelector(".inventory_item_name");
    private final By cancelBtn = By.id("cancel");
    private final By finishBtn = By.id("finish");

    public OverviewPage(WebDriver driver) {
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

    @Step("Получение списка продуктов")
    public ArrayList<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn));
        List<WebElement> allProducts = driver.findElements(product);
        ArrayList<String> names = new ArrayList<>();

        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }

    @Step("Нажатие кнопки Finish")
    public void clickFinish() {
        driver.findElement(finishBtn).click();
    }
}
