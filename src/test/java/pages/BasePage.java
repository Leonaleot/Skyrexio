package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public class BasePage {
    public static final String BASE_URL = PropertyReader.getProperty("saucedemoo.url");
    public static final String DATA_TEST_PATTERN = "[data-test='%s']";
    public NavigationPanel navigationPanel;
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.navigationPanel = new NavigationPanel(driver);
    }
}
