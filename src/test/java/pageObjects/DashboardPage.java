package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DashboardPage {
    public WebDriver driver;
    By newMenu = By.id("email");
    By password = By.id("password");
    By newBtn = By.cssSelector("button[data-testid='tracked-button']");
    By newJobBtn = By.xpath("//li[text()='Job']");

    By userDashboard = By.cssSelector("button.jss658");
    By dashboardTheme = By.cssSelector("div[data-testid='dashboard-bootstrap']");

    public String title = "Secure Login | Housecall Pro";
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean VerifyDash() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(dashboardTheme));
        return driver.findElement(dashboardTheme).isDisplayed();
    }
    public void NewJob(){
        WebElement newJob = driver.findElement(newBtn);
        newJob.click();
        WebElement newJobOption = driver.findElement(newJobBtn);
        newJobOption.click();
    }
}
