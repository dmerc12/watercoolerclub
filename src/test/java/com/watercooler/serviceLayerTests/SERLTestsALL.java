package com.watercooler.serviceLayerTests;

import com.watercooler.daos.*;
import com.watercooler.saos.*;
import com.watercooler.entities.*;
import com.watercooler.utilities.customExceptions.*;

import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SERLTestsALL {
    public static ApplicantDAOImp applicantDAO;
    public static ApplicantSAOImp applicantSAO;
    public static CompanyJobsDALImp jobsDAO;
    public static CompanyJobsSALImp jobsSAO;
    public static CompanyDAOImp companyDAO;
    public static CompanySAOImp companySAO;
    public static CreateTestDAOImp createTestDAO;
    public static CreateTestSAOImp createTestSAO;
    public static SkillTestDAOImp skillTestDAO;
    public static SkillTestSAOImp skillTestSAO;
    public static UsernamePasswordApplicantSAOImp usernamePasswordApplicantSAOImp;
    public static UsernamePasswordApplicantDAOImp usernamePasswordApplicantDAOImp;
    public static UsernamePasswordCompanySAOImp usernamePasswordCompanySAOImp;
    public static UsernamePasswordCompanyDAOImp usernamePasswordCompanyDAOImp;

    OversizedText text = new OversizedText();

    public static Applicant testMock;
    public static List<Job> mockJobs = new ArrayList<Job>();
    public static Job mockJob = new Job(1,"title", "type", "description", 1, "location", "company name");
    public static List<Applicant> mockApplicants = new ArrayList<Applicant>();
    public static Applicant mockApplicant = new Applicant(1, "first", "last", "405647937", "email@mock.com", "mock history", "mock references", "mock education", "mock desires");
    public static Company testCompMock;
    public static SkillTest testSkillMock = new SkillTest(-1, "test", "test", "test", "test", "test", "test", -1);
    public static List<SkillTest> mockList = new ArrayList<SkillTest>();
    public static SkillTest mockTest = new SkillTest(-1, "test", "test", "test", "test", "test", "test", -1);

    @BeforeClass
    public void setup() {
        applicantDAO = Mockito.mock(ApplicantDAOImp.class);
        applicantSAO = new ApplicantSAOImp(applicantDAO);
        jobsDAO = Mockito.mock(CompanyJobsDALImp.class);
        jobsSAO = new CompanyJobsSALImp(jobsDAO);
        mockJobs.add(mockJob);
        mockApplicants.add(mockApplicant);
        companyDAO = Mockito.mock(CompanyDAOImp.class);
        companySAO = new CompanySAOImp(companyDAO);
        createTestDAO = Mockito.mock(CreateTestDAOImp.class);
        createTestSAO = new CreateTestSAOImp(createTestDAO);
        skillTestDAO = Mockito.mock(SkillTestDAOImp.class);
        skillTestSAO = new SkillTestSAOImp(skillTestDAO);
        mockList.add(mockTest);
        usernamePasswordApplicantDAOImp = Mockito.mock(UsernamePasswordApplicantDAOImp.class);
        usernamePasswordApplicantSAOImp = new UsernamePasswordApplicantSAOImp(usernamePasswordApplicantDAOImp);
        usernamePasswordCompanyDAOImp = Mockito.mock(UsernamePasswordCompanyDAOImp.class);
        usernamePasswordCompanySAOImp = new UsernamePasswordCompanySAOImp(usernamePasswordCompanyDAOImp);
    }

    @Test
    public void ApplicantUpdateNameSuccess() {
        testMock = new Applicant(
                1,
                "2",
                "it doesn't matter",
                "not number",
                "fake mail",
                "NO EXP",
                "WHO CARES",
                "NO SCHOOL",
                "sdf");
        Mockito.doReturn(1).when(applicantDAO).updateApplicant(testMock);
        int result = applicantSAO.catchErrorsApplicant(testMock);
        Assert.assertEquals(result, 1);
    }

    //First names cannot exceed 25 characters
    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "First names cannot exceed 25 characters")
    public void MoreThan25CharactersDescriptions() {
        String words = "haha";
        while (words.length() < 27) {
            words = words.concat("haha");
        }
        testMock = new Applicant(
                1,
                words,
                "it doesn't matter",
                "not number",
                "fake mail",
                "wer",
                "WHO CARES",
                "NO SCHOOL",
                "sdf");
        applicantSAO.catchErrorsApplicant(testMock);
    }

    //Last names cannot exceed 25 characters
    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Last names cannot exceed 25 characters")
    public void MoreThan35CharactersDescriptions() {
        String words = "haha";
        while (words.length() < 26) {
            words = words.concat("haha");
        }
        testMock = new Applicant(
                1,
                "2",
                words,
                "not number",
                "fake mail",
                "sdfjdj",
                "WHO CARES",
                "NO SCHOOL",
                "sdf");
        applicantSAO.catchErrorsApplicant(testMock);
    }

    //Phone number cannot be less than 10 numbers
    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Phone numbers cannot be more than 10 numbers")
    public void MoreThan10Numbers2() {
        String phoneNumber = "haha";
        while (phoneNumber.length() < 11) {
            phoneNumber = phoneNumber.concat("haha");
        }
        testMock = new Applicant(
                1,
                "2",
                "it doesn't matter",
                phoneNumber,
                "fake mail",
                "s",
                "WHO CARES",
                "NO SCHOOL",
                "sdf");
        applicantSAO.catchErrorsApplicant(testMock);
    }

    //"Emails cannot exceed 30 characters"
    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Emails cannot exceed 30 characters")
    public void MoreThan30CharactersDescriptions3() {
        String words = "haha";
        while (words.length() < 32) {
            words = words.concat("haha");
        }
        testMock = new Applicant(
                1,
                "2",
                "it doesn't matter",
                "not number",
                words,
                "weret",
                "WHO CARES",
                "NO SCHOOL",
                "sdf");
        applicantSAO.catchErrorsApplicant(testMock);
    }

    //"work descriptions cannot exceed 1500 characters"
    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Work descriptions cannot exceed 500 characters")
    public void MoreThan500CharactersDescriptions4() {
        String words = "haha";
        while (words.length() < 501) {
            words = words.concat("haha");
        }
        testMock = new Applicant(
                1,
                "2",
                "it doesn't matter",
                "not number",
                "fake mail",
                words,
                "WHO CARES",
                "NO SCHOOL",
                "sdf");
        applicantSAO.catchErrorsApplicant(testMock);


    }
    //"Work reference descriptions cannot exceed 800 characters"
    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Work reference descriptions exceed 800 characters")
    public void MoreThan800CharactersDescriptions() {
        String words = "haha";
        while (words.length() < 801) {
            words = words.concat("haha");
        }
        testMock = new Applicant(
                1,
                "2",
                "it doesn't matter",
                "not number",
                "fake mail",
                "rierieh",
                words,
                "hjhkjd",
                "fkjfkj");


        applicantSAO.catchErrorsApplicant(testMock);
    }

    //"Education reference descriptions cannot exceed 200 characters"
    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Education descriptions cannot exceed 200 characters")
    public void MoreThan200CharactersDescriptions() {
        String words = "haha";
        while (words.length() < 203) {
            words = words.concat("haha");
        }
        testMock = new Applicant(
                1,
                "2",
                "it doesn't matter",
                "not number",
                "fake mail",
                "2",
                "who cares",
                words,
                "sdf");
        applicantSAO.catchErrorsApplicant(testMock);
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

    @Test
    public void CompanyUpdateNameSuccess() {
        testCompMock = new Company(
                1,
                "5",
                "not number",
                "fakemail",
                "test",
                "fake", "s");
        Mockito.doReturn(1).when(companyDAO).updateCompany(testCompMock);
        int result = companySAO.catchErrorsCompany(testCompMock);
        Assert.assertEquals(result, 1);
    }

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Company name cannot exceed 45 characters")
    public void MoreThan45CharactersDescriptions() {
        String words = "meow";
        while (words.length() < 46) {
            words = words.concat("meow");
        }
        testCompMock = new Company(
                1,
                words,
                "not number",
                "fakemail",
                "test",
                "fake",
                "s");
        companySAO.catchErrorsCompany(testCompMock);

    }

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Phone numbers cannot be less than 10 numbers")
    public void MoreThan10Numbers() {
        String phoneNumber = "meow";
        while (phoneNumber.length() < 31) {
            phoneNumber = phoneNumber.concat("meow");
            testCompMock = new Company(
                    1,
                    "5",
                    phoneNumber,
                    "fakeemail",
                    "s",
                    "s",
                    "s");
            companySAO.catchErrorsCompany(testCompMock);
        }
    }

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Emails cannot exceed 30 characters")
    public void MoreThan30CharactersDescriptions() {
        String words = "meow";
        while (words.length() < 31) {
            words = words.concat("meow");
            testCompMock = new Company(
                    1,
                    "5",
                    "not number",
                    words,
                    "s",
                    "s",
                    "s");
            companySAO.catchErrorsCompany(testCompMock);
        }
    }

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Work descriptions cannot exceed 500 characters")
    public void MoreThan500CharactersDescriptions() {
        String words = "meow";
        while (words.length() < 501) {
            words = words.concat("meow");
            testCompMock = new Company(
                    1,
                    "5",
                    "not number",
                    "fake email",
                    words,
                    "s",
                    "s");

            companySAO.catchErrorsCompany(testCompMock);
        }
    }
    ;

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Work reference descriptions exceed 800 characters")
    public void MoreThan10Numbers800() {
        String words = "meow";
        while (words.length() < 801) {
            words = words.concat("meow");
            testCompMock = new Company(
                    1,
                    "5",
                    "not number",
                    "fake email",
                    "fdkjdgk",
                    words,
                    "dbnfbdb");


            companySAO.catchErrorsCompany(testCompMock);
        }
    }

    @Test
    public void validateTestSuccess() {
        Mockito.doReturn(1).when(createTestDAO).createSkillTestData(testSkillMock);
        boolean result = createTestSAO.createSkillTestService(testSkillMock);
        Assert.assertTrue(result);
    }

    @Test
    public void checkCreateCalledOnce() {
        Mockito.doReturn(1).when(createTestDAO).createSkillTestData(testSkillMock);
        createTestSAO.createSkillTestService(testSkillMock);
        Mockito.verify(createTestDAO, VerificationModeFactory.times(1)).createSkillTestData(testSkillMock);
    }

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Inputted first question is too long.")
    public void validateFirstQuestionFailure() {
        SkillTest skillTest = new SkillTest(-2, text.oversizedText, "test", "test", "test", "test", "test", -2);
        createTestSAO.createSkillTestService(skillTest);
    }

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Inputted first answer is too long.")
    public void validateFirstAnswerFailure() {
        SkillTest skillTest = new SkillTest(-2, "test", text.oversizedText, "test", "test", "test", "test", -2);
        createTestSAO.createSkillTestService(skillTest);
    }

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Inputted second question is too long.")
    public void validateSecondQuestionFailure() {
        SkillTest skillTest = new SkillTest(-2, "test", "test", text.oversizedText, "test", "test", "test", -2);
        createTestSAO.createSkillTestService(skillTest);
    }

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Inputted second answer is too long.")
    public void validateSecondAnswerFailure() {
        SkillTest skillTest = new SkillTest(-2, "test", "test", "test", text.oversizedText, "test", "test", -2);
        createTestSAO.createSkillTestService(skillTest);
    }

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Inputted third question is too long.")
    public void validateThirdQuestionFailure() {
        SkillTest skillTest = new SkillTest(-2, "test", "test", "test", "test", text.oversizedText, "test", -2);
        createTestSAO.createSkillTestService(skillTest);
    }

    @Test(expectedExceptions = InputTooLong.class, expectedExceptionsMessageRegExp = "Inputted third answer is too long.")
    public void validateThirdAnswerFailure() {
        SkillTest skillTest = new SkillTest(-2, "test", "test", "test", "test", "test", text.oversizedText, -2);
        createTestSAO.createSkillTestService(skillTest);
    }

    @Test(expectedExceptions = EmptyField.class, expectedExceptionsMessageRegExp = "All fields must be filled.")
    public void validateNoDataInputted() {
        SkillTest skillTest = new SkillTest(-2, "test", "test", "test", "test", "test", "", -2);
        createTestSAO.createSkillTestService(skillTest);
    }

    @Test
    public void getAllTestsServiceSuccess() {
        Mockito.doReturn(mockList).when(skillTestDAO).getAllSkillTests();
        HashMap<Integer, String> resultMap = skillTestSAO.getAllSkillTests();
        Assert.assertEquals(resultMap.get(-1), "test");
    }

    @Test
    public void getSpecificTestServiceSuccess() {
        Mockito.doReturn(mockTest).when(skillTestDAO).getSpecificSkillTest(-1);
        SkillTest test = skillTestSAO.getSpecificSkillTest(-1);
        Assert.assertEquals(test, mockTest);
    }

    @Test
    public void placeApplicantServiceSuccess() {
        Mockito.doReturn(1).when(skillTestDAO).placePassingApplicant(-1, -1);
        boolean result = skillTestSAO.placePassingApplicant(-1, -1);
        Assert.assertTrue(result);

    }

    @Test
    public void placeApplicantOnce() {
        Mockito.doReturn(1).when(skillTestDAO).placePassingApplicant(-1, -1);
        skillTestSAO.placePassingApplicant(-1, -1);
        Mockito.verify(skillTestDAO, VerificationModeFactory.times(1)).placePassingApplicant(-1, -1);
    }

    @Test(expectedExceptions = com.watercooler.utilities.CustomUncheckedException.class, expectedExceptionsMessageRegExp = "Username needs to be less than 25 characters")
    public void createApplicantUsernameTooLong() {
        UsernamePasswordApplicant newApplicant = new UsernamePasswordApplicant(1, "This is a long string I'm making, hopefully it will be over 25 ", "Popeyes");
        usernamePasswordApplicantSAOImp.catchErrorsAccountApplicant(newApplicant);}

    @Test(expectedExceptions = com.watercooler.utilities.CustomUncheckedException.class, expectedExceptionsMessageRegExp = "Username needs to be at least 5 characters")
    public void createApplicantUsernameTooShort() {
        UsernamePasswordApplicant newApplicant = new UsernamePasswordApplicant(1, "wut", "Popeyes");
        usernamePasswordApplicantSAOImp.catchErrorsAccountApplicant(newApplicant);}

    @Test(expectedExceptions = com.watercooler.utilities.CustomUncheckedException.class, expectedExceptionsMessageRegExp = "Password needs to be less than 25 characters")
    public void createApplicantPasswordTooLong() {
        UsernamePasswordApplicant newApplicant = new UsernamePasswordApplicant(1, "Reasonable string", "Popeyes, pretty good when it's fresh but when it's been under the heat lamp for hours not so much");
        usernamePasswordApplicantSAOImp.catchErrorsAccountApplicant(newApplicant);
    }
    @Test(expectedExceptions = com.watercooler.utilities.CustomUncheckedException.class, expectedExceptionsMessageRegExp = "Password needs to be at least 5 characters")
    public void createApplicantPasswordTooShort() {
        UsernamePasswordApplicant newApplicant = new UsernamePasswordApplicant(1, "Reasonable string", "wut?");
        usernamePasswordApplicantSAOImp.catchErrorsAccountApplicant(newApplicant);
    }
    @Test(expectedExceptions = com.watercooler.utilities.CustomUncheckedException.class, expectedExceptionsMessageRegExp = "Username needs to be less than 25 characters")
    public void createCompanyUsernameTooLong() {
        UsernamePasswordCompany newCompany = new UsernamePasswordCompany(1, "This is a long string I'm making, hopefully it will be over 25 ", "Popeyes");
        usernamePasswordCompanySAOImp.catchErrorsUsernamePasswordCompany(newCompany);}

    @Test(expectedExceptions = com.watercooler.utilities.CustomUncheckedException.class, expectedExceptionsMessageRegExp = "Username needs to be at least 5 characters")
    public void createCompanyUsernameTooShort() {
        UsernamePasswordCompany newCompany = new UsernamePasswordCompany(1, "wut", "Popeyes");
        usernamePasswordCompanySAOImp.catchErrorsUsernamePasswordCompany(newCompany);}

    @Test(expectedExceptions = com.watercooler.utilities.CustomUncheckedException.class, expectedExceptionsMessageRegExp = "Password needs to be less than 25 characters")
    public void createCompanyPasswordTooLong() {
        UsernamePasswordCompany newCompany = new UsernamePasswordCompany(1, "a fine string", "This is a long string I'm making, hopefully it will be over 25. Definitely over 25 now");
        usernamePasswordCompanySAOImp.catchErrorsUsernamePasswordCompany(newCompany);}

    @Test(expectedExceptions = com.watercooler.utilities.CustomUncheckedException.class, expectedExceptionsMessageRegExp = "Password needs to be at least 5 characters")
    public void createCompanyPasswordTooShort() {
        UsernamePasswordCompany newCompany = new UsernamePasswordCompany(1, "a fine string", "wut?");
        usernamePasswordCompanySAOImp.catchErrorsUsernamePasswordCompany(newCompany);}

    @Test
    public void createApplicantUsernameTooLongNonMock() {
        try{ UsernamePasswordApplicant newApplicant = new UsernamePasswordApplicant(1, "This is a long string I'm making, hopefully it will be over 25 ", "Popeyes");
            String result  = String.valueOf(usernamePasswordApplicantSAOImp.catchErrorsAccountApplicant(newApplicant));
            Assert.fail();
        }
        catch (com.watercooler.utilities.CustomUncheckedException e){
            Assert.assertEquals(e.getMessage(), "Username needs to be less than 25 characters");
        }

    }

    @Test
    public void createApplicantUsernameTooShortNonMock() {
        try{ UsernamePasswordApplicant newApplicant = new UsernamePasswordApplicant(1, "wut", "Popeyes");
            String result  = String.valueOf(usernamePasswordApplicantSAOImp.catchErrorsAccountApplicant(newApplicant));
            Assert.fail();
        }
        catch (com.watercooler.utilities.CustomUncheckedException e){
            Assert.assertEquals(e.getMessage(), "Username needs to be at least 5 characters");
        }

    }

    @Test
    public void createApplicantPasswordTooLongNonMock() {
        try{ UsernamePasswordApplicant newApplicant = new UsernamePasswordApplicant(1, "this is fine ", "This is a long string I'm making, hopefully it will be over 25");
            String result  = String.valueOf(usernamePasswordApplicantSAOImp.catchErrorsAccountApplicant(newApplicant));
            Assert.fail();
        }
        catch (com.watercooler.utilities.CustomUncheckedException e){
            Assert.assertEquals(e.getMessage(), "Password needs to be less than 25 characters");
        }

    }

    @Test
    public void createApplicantPasswordTooShortNonMock() {
        try{ UsernamePasswordApplicant newApplicant = new UsernamePasswordApplicant(1, "This is fine", "wut");
            String result  = String.valueOf(usernamePasswordApplicantSAOImp.catchErrorsAccountApplicant(newApplicant));
            Assert.fail();
        }
        catch (com.watercooler.utilities.CustomUncheckedException e){
            Assert.assertEquals(e.getMessage(), "Password needs to be at least 5 characters");
        }

    }

}
