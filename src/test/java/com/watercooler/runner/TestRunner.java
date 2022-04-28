package com.watercooler.runner;

import com.watercooler.poms.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

//import poms

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "com/watercooler/steps",
        plugin = {"pretty", "html:documentation/full-e2e-report.html"}
)
public class TestRunner {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static TestsLandingPageHome landingPage;
    public static CreateJobPostPOMs createJob;
    public static CompanyLandingPagePOMs compLandingPage;
    public static ViewSkillTestsHome viewSkillTests;
    public static TakeSkillTestHome takeSkillTest;
    public static CreateSkillTestHome createSkillTest;
    public static JobsHome jobsHome;

    @BeforeClass
    public static void setup() {
        File file = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();

        landingPage = new TestsLandingPageHome(driver);
        viewSkillTests = new ViewSkillTestsHome(driver);
        takeSkillTest = new TakeSkillTestHome(driver);
        createSkillTest = new CreateSkillTestHome(driver);
        jobsHome = new JobsHome(driver);
        compLandingPage = new CompanyLandingPagePOMs(driver);
        createJob = new CreateJobPostPOMs(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }

}
