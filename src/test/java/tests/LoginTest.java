package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
public class LoginTest extends BaseTest {
    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");

        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkIncorrectLogin() {
        loginPage.open();
        loginPage.login("locked_out_user","secret_sauce");

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message fails to appear");

        String errorMessage = driver.findElement(By.xpath("//*[@data-test='error']")).getText();
        assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.", "Error message does not match");
    }

    @Test
    public void checkWithoutLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
        boolean isErrorMsgDisplayed = driver.findElement(By.xpath("//*[@data-test='error']")).isDisplayed();
        assertTrue(isErrorMsgDisplayed, "The error message fails to appear");
        String errorMessage = driver.findElement(By.xpath("//*[@data-test='error']")).getText();
        assertEquals(errorMessage, "Epic sadface: Username is required", "Error message does not match");
    }
}
