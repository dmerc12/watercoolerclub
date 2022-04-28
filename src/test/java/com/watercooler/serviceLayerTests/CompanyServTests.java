package com.watercooler.serviceLayerTests;
import com.watercooler.daos.CompanyDAOImp;
import com.watercooler.entities.Company;
import com.watercooler.saos.CompanySAOImp;
import com.watercooler.utilities.customExceptions.CustomUncheckedException;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CompanyServTests {
    public static CompanyDAOImp companyDAO;
    public static CompanySAOImp companySAO;
    public static Company testMock;


    @BeforeClass
    public void setup() {
        companyDAO = Mockito.mock(CompanyDAOImp.class);
        companySAO = new CompanySAOImp(companyDAO);
    }

    @Test
    public void CompanyUpdateNameSuccess() {
        testMock = new Company(
                1,
                "5",
                "not number",
                "fakemail",
                "test",
                "fake", "s");
        Mockito.doReturn(1).when(companyDAO).updateCompany(testMock);
        int result = companySAO.catchErrorsCompany(testMock);
        Assert.assertEquals(result, 1);
    }

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Company name cannot exceed 45 characters")
    public void MoreThan45CharactersDescriptions() {
        String words = "meow";
        while (words.length() < 46) {
            words = words.concat("meow");
        }
        testMock = new Company(
                1,
                words,
                "not number",
                "fakemail",
                "test",
                "fake",
                "s");
        companySAO.catchErrorsCompany(testMock);

    }

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Phone numbers cannot be less than 10 numbers")
    public void MoreThan10Numbers() {
        String phoneNumber = "meow";
        while (phoneNumber.length() < 31) {
            phoneNumber = phoneNumber.concat("meow");
            testMock = new Company(
                    1,
                    "5",
                    phoneNumber,
                    "fakeemail",
                    "s",
                    "s",
                    "s");
            companySAO.catchErrorsCompany(testMock);
        }
    }

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Emails cannot exceed 30 characters")
    public void MoreThan30CharactersDescriptions() {
        String words = "meow";
        while (words.length() < 31) {
            words = words.concat("meow");
            testMock = new Company(
                    1,
                    "5",
                    "not number",
                    words,
                    "s",
                    "s",
                    "s");
            companySAO.catchErrorsCompany(testMock);
        }
    }

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Work descriptions cannot exceed 500 characters")
    public void MoreThan500CharactersDescriptions() {
        String words = "meow";
        while (words.length() < 501) {
            words = words.concat("meow");
            testMock = new Company(
                    1,
                    "5",
                    "not number",
                    "fake email",
                    words,
                    "s",
                    "s");

            companySAO.catchErrorsCompany(testMock);
        }
    }
    ;

    @Test(expectedExceptions = CustomUncheckedException.class,
            expectedExceptionsMessageRegExp = "Work reference descriptions exceed 800 characters")
    public void MoreThan10Numbers800() {
        String words = "meow";
        while (words.length() < 801) {
            words = words.concat("meow");
            testMock = new Company(
                    1,
                    "5",
                    "not number",
                    "fake email",
                    "fdkjdgk",
                    words,
                    "dbnfbdb");


            companySAO.catchErrorsCompany(testMock);
        }
    }
}

