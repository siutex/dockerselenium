package stepDefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utils.TestContextSetup;

public class LoginStepDefinition {
    public WebDriver driver;
    public LoginPage loginPage;

    public DashboardPage dashboardPage;
    TestContextSetup testContextSetup;

    public LoginStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
        this.dashboardPage = testContextSetup.pageObjectManager.getDashboardPage();
    }


    @Given("^User is signing into App with email: (.+) and password: (.+)$")
    public void user_is_login(String email, String password) {
        Assert.assertTrue(loginPage.VerifyLogo());
        loginPage.LoginIntoApp(email, password);
        Assert.assertTrue(dashboardPage.VerifyDash());
    }
}
