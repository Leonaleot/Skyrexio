package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    public CheckoutPage fillFirstName(String name) {
        WebElement firstNameInput = driver.findElement(firstName);
        firstNameInput.clear();
        firstNameInput.sendKeys(name);
        return this;
    }

    @Step("Ввод в поле Last Name")
    public CheckoutPage fillLastName(String surname) {
        WebElement lastNameInput = driver.findElement(lastName);
        lastNameInput.clear();
        lastNameInput.sendKeys(surname);
        return this;
    }

    @Step("Ввод в поле Postal Code")
    public CheckoutPage fillPostalCode(String zip) {
        WebElement postalCodeInput = driver.findElement(postalCode);
        postalCodeInput.clear();
        postalCodeInput.sendKeys(zip);
        return this;
    }

    @Step("Нажатие кнопки Continue при корректном вводе полей")
    public OverviewPage clickContinueSuccess() {
        driver.findElement(continueBtn).click();
        return new OverviewPage(driver);
    }

    @Step("Нажатие кнопки Continue при некорректном вводе полей")
    public CheckoutPage clickContinue() {
        driver.findElement(continueBtn).click();
        return this;
    }

    public CheckoutPage fillForm(String first, String last, String zip) {
        return fillFirstName(first)
                .fillLastName(last)
                .fillPostalCode(zip);
    }

    @Step("Проверка появления сообщения об ошибке")
    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }
}
