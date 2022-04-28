package com.watercooler.dataLayerTests;

import com.watercooler.daos.*;

import com.watercooler.entities.*;

import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.List;


public class DALTestsALL {
    CompanyJobsDALImp jobsDAO = new CompanyJobsDALImp();
    ApplicantDAOImp applicantDAO = new ApplicantDAOImp();
    JobSearchDAOImp jobSearchDAO = new JobSearchDAOImp();
    CompanyDAOImp companyDAO = new CompanyDAOImp();
    CreateTestDAOImp createTestDAO = new CreateTestDAOImp();
    SkillTestDAOImp skillTestDataImp = new SkillTestDAOImp();
    UsernamePasswordApplicantDAOInterface usernamePasswordApplicationDAO = new UsernamePasswordApplicantDAOImp();

    @Test
    public void postJobSuccess() {
        Job testJob = new Job(1, "testTitle", "management", "testDescription", -1, "testLocation", "testName");
        Job resultingJob = jobsDAO.postJob(testJob);
        Assert.assertNotEquals(resultingJob.getJobId(), 0);
    }

    ;

    @Test
    public void viewJobsSuccess() {
        List<Job> testJobs = jobsDAO.viewJobs(-1);
        Assert.assertTrue(testJobs.size() >= 1);
    }

    ;

    @Test
    public void viewApplicantsSuccess() {
        List<Applicant> applicants = jobsDAO.viewApplicants(4);
        Assert.assertTrue(applicants.size() >= 1);
    }

    ;

    @Test
    public void deleteJobsSuccess() {
        int result = jobsDAO.deleteJobs(1);
        Assert.assertTrue(result != 0);
    }

    ;

    @Test
    public void updateApplicantSuccess() {
        Applicant applicant = new Applicant(2, "firstname", "lastname", "4055674532", "email", "work history", "references", "education", "desired job");
        int result = applicantDAO.updateApplicant(applicant);
        Assert.assertTrue(result == 1);

    }

    @Test
    public void insertAppliedJobs() {
        int appliedJobs = jobSearchDAO.insertAppliedJobs(2, -1);
        Assert.assertNotEquals(appliedJobs, 0);
    }


    @Test
    public void viewAppliedJobs() {
        List<Job> appliedJobs = jobSearchDAO.viewAppliedJobs(-1);
        Assert.assertNotEquals(appliedJobs.toArray().length, 0);

    }

    @Test
    public void updateCompanySuccess(){
        Company company = new Company(-1, "companyName: 1", "phone: 2", "email: 3", "aboutUs: 4", "relatedJobs: 5","Atlanta");
        int result = companyDAO.updateCompany(company);
        Assert.assertTrue(result == 1);
    }

    @Test
    public void createTestSuccess() {
        SkillTest newTest = new SkillTest(-1, "test question 1", "test answer 1", "test question 2", "test answer 2", "test question 3", "test answer three 3", -1);
        int result = createTestDAO.createSkillTestData(newTest);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void selectJobSuccess() {
        List<Job> jobList = jobSearchDAO.selectJob("Technology", "testLocation2");
        Assert.assertNotEquals(jobList.size(), 0);

    }

    @Test
    public void selectJobNoLocation() {
        List<Job> jobList = jobSearchDAO.selectJob("management", "nothing");
        Assert.assertNotEquals(jobList.size(), 0);

    }

    @Test
    public void selectJobNoType() {
        List<Job> jobList = jobSearchDAO.selectJob("nothing", "testLocation2");
        Assert.assertNotEquals(jobList.size(), 0);

    }

    @Test
    public void selectJobNoLocationNoType() {
        List<Job> jobList = jobSearchDAO.selectJob("nothing", "nothing");
        Assert.assertNotEquals(jobList.size(), 0);

    }

    @Test
    public void getAllTestsSuccess() {
        List<SkillTest> resultList = skillTestDataImp.getAllSkillTests();
        Assert.assertNotEquals(resultList.size(), 0);
    }

    @Test
    public void getSpecificTestSuccess() {
        SkillTest resultTest = skillTestDataImp.getSpecificSkillTest(-1);
        String resultTestQuestionOne = resultTest.getQuestionOne();
        Assert.assertEquals(resultTestQuestionOne, "test");
    }

    @Test
    public void placePassingSuccess() {
        int resultCount = skillTestDataImp.placePassingApplicant(-1, -1);
        Assert.assertEquals(resultCount, 1);
    }

    @Test
    public void createApplicantSuccess() {
        UsernamePasswordApplicant newApplicant = new UsernamePasswordApplicant(1, "Coloniel", "Popeyes");
        UsernamePasswordApplicant resultingApplicant = usernamePasswordApplicationDAO.createAccountApplicant(newApplicant);
        Assert.assertNotEquals(resultingApplicant.getApplicantId(), 0);

    }

    UsernamePasswordCompanyDAOInterface usernamePasswordCompanyDAO = new UsernamePasswordCompanyDAOImp();


    @Test
    public void createCompanyPersonSuccess() {
        UsernamePasswordCompany newCompanyPerson = new UsernamePasswordCompany(5, "Soysauce", "Ambrosia");
        UsernamePasswordCompany resultingCompanyPerson = usernamePasswordCompanyDAO.createAccountCompany(newCompanyPerson);
        Assert.assertNotEquals(resultingCompanyPerson.getCompanyId(), 5);
    }

    @Test
    public void selectApplicantByIdSuccess() {
        UsernamePasswordApplicant usernamePasswordApplicant = usernamePasswordApplicationDAO.selectApplicantById(1);
        Assert.assertEquals(usernamePasswordApplicant.getApplicantId(), 1);
    }

    @Test
    public void selectCompanyByIdSuccess() {
        UsernamePasswordCompany usernamePasswordCompany = usernamePasswordCompanyDAO.selectCompanyAccountById(1);
        Assert.assertEquals(usernamePasswordCompany.getCompanyId(), 1);
    }


}
