package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String DATA_TEST_PATTERN = "[data-test='%s']";
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
