package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageObjects.NewJobPage;
import utils.TestContextSetup;

public class NewJobStepDefinition {

    public NewJobPage newJobPage;
    TestContextSetup testContextSetup;

    public NewJobStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.newJobPage = testContextSetup.pageObjectManager.getNewJobPage();
    }

    @Then("^Creates  customer with FirstName: (.+) , LastName: (.+)")
    public void user_add_new_cutomer(String firstName, String lastName) throws InterruptedException {
        newJobPage.verifyOnPage();
        newJobPage.NewCustomer(firstName, lastName);
    }

    @And("^Add Item with Item Name: (.+) and Price: (.+) and schedule$")
    public void add_item(String item, String price) throws InterruptedException {
        newJobPage.AddSchedule();
        newJobPage.AddItem(item, price);
    }

    @And("^Fill private note with text: (.+)$")
    public void add_private_note(String note) {
        newJobPage.AddPrivateNote(note);
    }

    @Then("^User saves job and checks Activity Feed")
    public void save_job_check_feed() {
        newJobPage.saveJobAndCheckFeed();
        newJobPage.verifyOnSchedule();
        newJobPage.verifyNewJobIsCreated();
    }
}
