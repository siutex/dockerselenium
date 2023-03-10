package stepDefinitions;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.DashboardPage;
import utils.TestContextSetup;

public class DashboardStepDefinition {
    public WebDriver driver;

    public DashboardPage dashboardPage;
    TestContextSetup testContextSetup;

    public DashboardStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.dashboardPage = testContextSetup.pageObjectManager.getDashboardPage();
    }

    @Given("^User is on dashboard page and wants to add a job")
    public void user_dashboard_new_job() {
        Assert.assertTrue(dashboardPage.VerifyDash());
        dashboardPage.NewJob();
    }
}
