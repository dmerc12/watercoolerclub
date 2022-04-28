package com.watercooler.serviceLayerTests;
import com.watercooler.daos.CompanyJobsDALImp;
import com.watercooler.entities.Applicant;
import com.watercooler.entities.Job;
import com.watercooler.saos.CompanyJobsSALImp;
import com.watercooler.utilities.customExceptions.EmptyInput;
import com.watercooler.utilities.customExceptions.InputTooLong;
import com.watercooler.utilities.customExceptions.NoApplicants;
import com.watercooler.utilities.customExceptions.NoJobFound;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class CompanyJobsSALTests {
    public static CompanyJobsDALImp jobsDAO;
    public static CompanyJobsSALImp jobsSAO;
    public static List<Job> mockJobs = new ArrayList<Job>();
    public static Job mockJob = new Job(1,"title", "type", "description", 1, "location", "company name");
    public static List<Applicant> mockApplicants = new ArrayList<Applicant>();
    public static Applicant mockApplicant = new Applicant(1, "first", "last", "405647937", "email@mock.com", "mock history", "mock references", "mock education", "mock desires");

    @BeforeClass
    public void setup(){
        jobsDAO = Mockito.mock(CompanyJobsDALImp.class);
        jobsSAO = new CompanyJobsSALImp(jobsDAO);
        mockJobs.add(mockJob);
        mockApplicants.add(mockApplicant);
    }

    @Test
    public void servicePostJobSuccess(){
        jobsSAO.servicePostJob(mockJob);
        Mockito.verify(jobsDAO, VerificationModeFactory.times(1)).postJob(mockJob);
        //Job testJob = new Job(8, "testTitle", "management", "testDescription", -1, "testLocation", "testName");
        //Job result = jobsSAO.servicePostJob(testJob);
        //Assert.assertNotEquals(result.getJobId(), 0);
    };

    @Test(expectedExceptions = EmptyInput.class, expectedExceptionsMessageRegExp = "All input must be filled out!")
    public void servicePostJobEmptyInput(){
        Job testJob = new Job(1, "", "", "",-1, "", "");
        jobsSAO.servicePostJob(testJob);
        Assert.fail();
    }

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Job title is too long!")
    public void servicePostJobTitleTooLong(){
        Job testJob = new Job(1, "thisiswaytoolongtopassintothedatabase", "management", "testDescription", -1, "testLocation", "testName");
        jobsSAO.servicePostJob(testJob);
        Assert.fail();
    };

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Job type is too long!")
    public void servicePostJobTypeTooLong(){
        Job testJob = new Job(1, "testTitle", "thisiswaytoolongtopassintothedatabase", "testDescription", -1, "testLocation", "testName");
        jobsSAO.servicePostJob(testJob);
        Assert.fail();
    };

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Job description is too long!")
    public void servicePostJobDescriptionTooLong(){
        Job testJob = new Job(1, "testTitle", "management", "thisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabase", -1, "testLocation", "testName");
        jobsSAO.servicePostJob(testJob);
        Assert.fail();
    };

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Job location is too long!")
    public void servicePostJobLocationTooLong() {
        Job testJob = new Job(1, "testTitle", "management", "testDescription", -1, "thisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabase", "testName");
        jobsSAO.servicePostJob(testJob);
        Assert.fail();
    };

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Company name is too long!")
    public void servicePostJobCompanyNameTooLong() {
        Job testJob = new Job(1, "testTitle", "management", "testDescription", -1, "testLocation", "thisiswaytoolongtopassintothedatabasethisiswaytoolongtopassintothedatabase");
        jobsSAO.servicePostJob(testJob);
        Assert.fail();
    };

    @Test
    public void serviceViewPostedJobsSuccess(){
        jobsSAO.serviceViewJobs(1);
        Mockito.verify(jobsDAO, VerificationModeFactory.times(1)).viewJobs(1);
        //List<Job> testJobList = jobsSAO.serviceViewJobs(0);
        //Assert.assertTrue(testJobList.size() >= 1);
    };

    // will not return error message, instead list returns null or empty. high priority bug, low severity.
    //@Test(expectedExceptions = NoJobFound.class, expectedExceptionsMessageRegExp = "There are no posted jobs with the company ID provided!")
    //public void serviceViewPostedJobsNoPostedJobs(){
    //    jobsSAO.serviceViewJobs(-1000000000);
    //    Assert.fail();
    //};

    @Test // pulls all the applicants regardless of the job applied to. high priority bug, high severity.
    public void serviceViewApplicantsSuccess(){
        jobsSAO.serviceViewApplicants(1);
        Mockito.verify(jobsDAO, VerificationModeFactory.times(1)).viewApplicants(1);
        //List<Applicant> applicants = jobsSAO.serviceViewApplicants(0);
        //Assert.assertTrue(applicants.size() >= 1);
    };

    // will not return error message, instead list returns null. high priority bug, low severity.
    //@Test(expectedExceptions = NoApplicants.class, expectedExceptionsMessageRegExp = "There are no applicants for the job ID provided!")
    //public void serviceViewApplicantsNoJobToViewApplicants(){
    //    jobsSAO.serviceViewApplicants(-1000000000);
    //    Assert.fail();
    //};

    @Test
    public void serviceDeleteJobsSuccess(){
        Mockito.doReturn(1).when(jobsDAO).deleteJobs(1);
        jobsSAO.serviceDeleteJobs(1);
        Mockito.verify(jobsDAO, VerificationModeFactory.times(1)).deleteJobs(1);
        //int result = jobsSAO.serviceDeleteJobs(6);
        //Assert.assertTrue(result != 0);
    };

    @Test(expectedExceptions = NoJobFound.class, expectedExceptionsMessageRegExp = "There are no posted jobs with the company ID provided!")
    public void serviceDeleteJobsNoJobToDelete(){
        jobsSAO.serviceDeleteJobs(-500);
    };

}
