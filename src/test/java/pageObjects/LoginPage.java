package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {
    public WebDriver driver;
    public String title = "Housecall Pro";
    By email = By.id("email");
    By password = By.id("password");
    By signInBtn = By.cssSelector("button.MuiButton-containedPrimary");
    By logo = By.id("Layer_1-2");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean VerifyLogo() {
        Assert.assertEquals(driver.getTitle(), title);
        return driver.findElement(logo).isDisplayed();
    }

    public void LoginIntoApp(String user, String pass) {
        WebElement userName = driver.findElement(email);
        userName.clear();
        userName.sendKeys(user);

        WebElement passWord = driver.findElement(password);
        passWord.clear();
        passWord.sendKeys(pass);

        driver.findElement(signInBtn).click();
    }
}
