package com.watercooler.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestsLandingPageHome {
    private WebDriver driver;

    public By companyIdInput = By.id("comp-id");
    private By individualIdInput = By.id("ind-id");
    private By viewTestsButton = By.id("ind-view-tests");
    private By createTestButton = By.id("comp-create-test");

    public TestsLandingPageHome(WebDriver driver) {
        this.driver = driver;
    }

    public void inputCompanyId(String companyId) {
        driver.findElement(companyIdInput).sendKeys(companyId);
    }

    public void inputIndividualId(String individualId) {
        driver.findElement(individualIdInput).sendKeys(individualId);
    }

    public void clickViewTestsButton() {
        driver.findElement(viewTestsButton).click();
    }

    public void clickCreateTestButton() {
        driver.findElement(createTestButton).click();
    }
}
