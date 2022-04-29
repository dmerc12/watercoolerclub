package com.watercooler.steps;

import com.watercooler.runner.TestRunner;
import com.watercooler.entities.OversizedText;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CreateSkillTestSteps {
    OversizedText tooMuchText = new OversizedText();

    @Given("I am on the landing page")
    public void i_am_on_the_landing_page() {
        TestRunner.driver.get("File:C:\\Users\\zacha\\Documents\\GitHub\\watercoolerclubP2\\frontEnd\\zachWilmer\\zach-test-landing-page.html");
        //login as a buisness user then go through steps
    }

    @When("I input the {string}")
    public void i_input_my(String string) {
        TestRunner.wait.until(ExpectedConditions.titleIs("Skill Test Test Landing Page"));
        TestRunner.landingPage.inputCompanyId(string);
    }

    @When("I click on the Create Skill Tests button")
    public void i_click_on_the_create_skill_tests_button() {
        TestRunner.landingPage.clickCreateTestButton();
    }

    @When("I am on the create skill test page")
    public void i_am_on_the_create_skill_test_page() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Create Skill Test"));
        Assert.assertEquals("Create Skill Test", TestRunner.driver.getTitle());
    }

    @When("I input the {string} 1Q")
    public void i_input_the_1Q(String string) {
        TestRunner.createSkillTest.inputQuestionOne(string);
    }

    @When("I input the {string} 1A")
    public void i_input_the_1A(String string) {
        TestRunner.createSkillTest.inputAnswerOne(string);
    }

    @When("I input the {string} 2Q")
    public void i_input_the_2Q(String string) {
        TestRunner.createSkillTest.inputQuestionTwo(string);
    }

    @When("I input the {string} 2A")
    public void i_input_the_2A(String string) {
        TestRunner.createSkillTest.inputAnswerTwo(string);
    }

    @When("I input the {string} 3Q")
    public void i_input_the_3Q(String string) {
        TestRunner.createSkillTest.inputQuestionThree(string);
    }

    @When("I input the {string} 3A")
    public void i_input_the_3A(String string) {
        TestRunner.createSkillTest.inputAnswerThree(string);
    }

    @When("I click the finalize button")
    public void i_click_the_finalize_button() {
        TestRunner.createSkillTest.submitTest();
    }

    @When("I get an alert telling me that the test was created successfully")
    public void i_get_an_alert_telling_me_that_the_test_was_created_successfully() {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(TestRunner.driver.switchTo().alert().getText(), "Test created successfully!");
        TestRunner.driver.switchTo().alert().accept();
    }

    @Then("I am then on the landing page")
    public void i_am_then_on_the_landing_page() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Skill Test Test Landing Page"));
    }

    @When("I input a first question that is too long")
    public void i_input_a_first_question_that_is_too_long() {
        TestRunner.createSkillTest.inputQuestionOne("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
    }

    @Then("I get an alert telling me that the first question is too long")
    public void i_get_an_alert_telling_me_that_the_first_question_is_too_long() {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(TestRunner.driver.switchTo().alert().getText(), "Inputted first question is too long.");
        TestRunner.driver.switchTo().alert().accept();
    }

}
