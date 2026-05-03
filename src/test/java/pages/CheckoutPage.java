package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By firstName = By.cssSelector(DATA_TEST_PATTERN.formatted("firstName"));
    private final By lastName = By.cssSelector(DATA_TEST_PATTERN.formatted("lastName"));
    private final By postalCode = By.cssSelector(DATA_TEST_PATTERN.formatted("postalCode"));
    private final By continueBtn = By.cssSelector(DATA_TEST_PATTERN.formatted("continue"));
    private final By errorMsg = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public CheckoutPage(WebDriver driver) {
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

    @Step("Ввод в поле First Name")
    public void fillFirstName(String name) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(name);
    }

    @Step("Ввод в поле Last Name")
    public void fillLastName(String surname) {
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(surname);
    }

    @Step("Ввод в поле Postal Code")
    public void fillPostalCode(String zip) {
        driver.findElement(postalCode).clear();
        driver.findElement(postalCode).sendKeys(zip);
    }

    @Step("Нажатие кнопки Continue")
    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    public void fillForm(String first, String last, String zip) {
        fillFirstName(first);
        fillLastName(last);
        fillPostalCode(zip);
    }

    @Step("Проверка появления сообщения об ошибки")
    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    @Step("Получение сообщения об ошибки")
    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }
}
