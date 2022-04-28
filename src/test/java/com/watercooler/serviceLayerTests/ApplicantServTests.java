package com.watercooler.serviceLayerTests;
import com.watercooler.daos.ApplicantDAOImp;
import com.watercooler.entities.Applicant;
import com.watercooler.saos.ApplicantSAOImp;
import com.watercooler.utilities.customExceptions.CustomUncheckedException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class ApplicantServTests {
    public static ApplicantDAOImp applicantDAO;
    public static ApplicantSAOImp applicantSAO;
    public static Applicant testMock;


    @BeforeClass
    public void setup() {
        applicantDAO = Mockito.mock(ApplicantDAOImp.class);
        applicantSAO = new ApplicantSAOImp(applicantDAO);
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
    public void MoreThan10Numbers() {
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
    public void MoreThan30CharactersDescriptions() {
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
    public void MoreThan500CharactersDescriptions() {
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



    }


