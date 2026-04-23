package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By userField = By.cssSelector("[id='user-name']");
    private final By passwordField = By.xpath("//*[@placeholder='Password']");
    private final By submitButton = By.cssSelector(DATA_TEST_PATTERN.formatted("login-button"));
    private final By errorMsg = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(User user) {
        driver.findElement(userField).sendKeys(user.getLogin());
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(submitButton).click();
    }

    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    public String getErrorMsgValid() {
        return driver.findElement(errorMsg).getText();
    }
}
