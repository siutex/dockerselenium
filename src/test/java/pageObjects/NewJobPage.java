package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class NewJobPage {
    public WebDriver driver;
    public WebDriverWait wait;

    By customerEditSpan = By.cssSelector("span[title='Edit']");
    By itemLine = By.cssSelector("div[data-testid='line-item-labor']");
    By buttonSpan = By.cssSelector("button[type='button']>span");
    By newJobHeader = By.tagName("h5");
    By addNewCustomerDialog = By.cssSelector("input[name='email");
    By firstNameInput = By.name("first_name");
    By lastNameInput = By.name("last_name");
    By buttonSubmit = By.cssSelector("button[type='submit']");
    By pClassWithText = By.cssSelector("p.MuiTypography-root");
    By itemNameInput = By.id("item-name");
    By unitPriceInput = By.id("unit-price");
    By tabButtonsSelector = By.cssSelector("button[tabindex='0']");
    By privateNotesTextArea = By.cssSelector("textarea[data-testid='private-notes-textfield'");
    By activityFeedHeaderSpan = By.cssSelector("h6>span");
    By jobFeedPClass = By.cssSelector("p[class^='MuiTypography-root']");
    By scheduleFromInput = By.cssSelector("div[data-testid='schedule-date-from']>div>input#input-date");
    By scheduleToInput = By.cssSelector("div[data-testid='schedule-date-to']>div>input#input-date");
    By newStartDateInput = By.id("newStartDate");
    By newEndDateInput = By.id("newEndDate");
    public String title = "Add job - HCP";

    public NewJobPage(WebDriver driver) {
        this.driver = driver;
        this.wait =  new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public WebElement getElementFromListByText(By selector, String eleText) {
        List<WebElement> newCustomerButtons = driver.findElements(selector);

        for (WebElement button : newCustomerButtons) {
            if (button.getText().contains(eleText))
                return button;
        }
        return null;
    }
    public void verifyOnPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(itemLine));
        Assert.assertEquals(driver.getTitle(), title);
    }

    public void NewCustomer(String first, String last) throws InterruptedException {
        getElementFromListByText(buttonSpan, "+ NEW CUSTOMER").click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(addNewCustomerDialog));
        WebElement firstName = driver.findElement(firstNameInput);
        firstName.clear();
        firstName.sendKeys(first);
        WebElement lastName = driver.findElement(lastNameInput);
        lastName.clear();
        lastName.sendKeys(last);
        WebElement createCustomer = getElementFromListByText(buttonSubmit, "CREATE CUSTOMER");
        createCustomer.click();
        wait.until((ExpectedConditions.presenceOfElementLocated(customerEditSpan)));
        WebElement customerTab = getElementFromListByText(pClassWithText, String.format("%s %s", first, last));
        wait.until(ExpectedConditions.textToBePresentInElement(customerTab, String.format("%s %s", first, last)));
        Assert.assertTrue(customerTab.isDisplayed());
    }

    public void AddSchedule(){
        WebElement scheduleFromBtn = driver.findElement(scheduleFromInput);
        scheduleFromBtn.clear();
        scheduleFromBtn.sendKeys("Wed, Mar 30 2023");
        Assert.assertEquals(scheduleFromBtn.getAttribute("value"), "Wed, Mar 30 2023");

        WebElement scheduleToBtn = driver.findElement(scheduleToInput);
        scheduleToBtn.clear();
        scheduleToBtn.sendKeys("Wed, Mar 30 2023");
        Assert.assertEquals(scheduleToBtn.getAttribute("value"), "Wed, Mar 30 2023");

        WebElement startTime = driver.findElement(newStartDateInput);
        startTime.clear();
        startTime.sendKeys("11:45am");
        Assert.assertEquals(startTime.getAttribute("value"), "11:45am");

        WebElement endTime = driver.findElement(newEndDateInput);
        endTime.clear();
        endTime.sendKeys("12:45am");
        Assert.assertEquals(endTime.getAttribute("value"), "12:45am");
    }

    public void AddItem(String item, String price) {
        WebElement itemName = driver.findElement(itemNameInput);
        itemName.clear();
        itemName.sendKeys(item);

        WebElement itemPrice = driver.findElement(unitPriceInput);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById(\"unit-price\").value='$12.00'");;

        Assert.assertEquals(itemName.getAttribute("value"), item);
        Assert.assertEquals(itemPrice.getAttribute("value"), price);
    }

    public void AddPrivateNote(String text) {
        WebElement privateNotesBtn = getElementFromListByText(tabButtonsSelector, "Private notes");
        privateNotesBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(privateNotesTextArea));
        WebElement textArea = driver.findElement(privateNotesTextArea);
        textArea.clear();
        textArea.sendKeys(text);
        Assert.assertEquals(textArea.getText(), text);
    }

    public void saveJobAndCheckFeed() {
        WebElement saveJobBtn = getElementFromListByText(buttonSpan, "SAVE JOB");
        saveJobBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(activityFeedHeaderSpan));
    }


    public Boolean verifyOnSchedule() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(newJobHeader));
        return driver.findElement(newJobHeader).isDisplayed();
    }
    public Boolean verifyNewJobIsCreated() {
        WebElement jobFeed = getElementFromListByText(jobFeedPClass, "Job created as Invoice");
        return jobFeed.isDisplayed();
    }
}
