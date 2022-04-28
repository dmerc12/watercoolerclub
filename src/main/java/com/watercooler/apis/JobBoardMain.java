package com.watercooler.apis;

import com.watercooler.daos.*;
import com.watercooler.saos.*;
import io.javalin.Javalin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobBoardMain {

    public static Logger logger = LogManager.getLogger(SkillTestsController.class);

    public static void main(String[] args) {
        logger.info("Application starting...");
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
            }
        );

        UsernamePasswordApplicantDAOInterface appDAO = new UsernamePasswordApplicantDAOImp();
        UsernamePasswordApplicantSAOInterface appSAO = new UsernamePasswordApplicantSAOImp(appDAO);
        UsernamePasswordCompanyDAOInterface compDAO = new UsernamePasswordCompanyDAOImp();
        UsernamePasswordCompanySAOInterface compSAO = new UsernamePasswordCompanySAOImp(compDAO);
        JavalinController sessionController = new JavalinController(appSAO, compSAO);

        ManagingJobsController jobsController = new ManagingJobsController();

        SkillTestsController testsController = new SkillTestsController();

        JobSearchController jobSearchController = new JobSearchController();

        ApplicantDAOImp daoImp = new ApplicantDAOImp();
        ApplicantSAOImp saoImp = new ApplicantSAOImp(daoImp);
        UpdateApplicantController updateController = new UpdateApplicantController(saoImp);

        app.post("/login/applicant", sessionController.UnPwApplicantVerify);
        app.post("/login/company", sessionController.UnPwCompanyVerify);
        app.post("/create/applicant", sessionController.UnPwApplicantCreate);
        app.post("/create/company", sessionController.UnPwCompanyCreate);

        app.post("/createJobPost", jobsController.createJobPost);
        app.patch("/viewPostedJobs", jobsController.viewJobs);
        app.patch("/viewJobApplicants", jobsController.viewApplicants);
        app.delete("/deleteJobPost", jobsController.deleteJobPost);

        app.get("/skilltest", testsController.getSkillTests);
        app.get("/skilltest/{skillTestId}", testsController.getSingleSkillTest);
        app.post("/skilltest/{skillTestId}/result/{applicantId}", testsController.postTestResult);
        app.post("/skilltest/new", testsController.postNewSkillTest);

        app.get("/jobSearch/{jobType}/{jobLocation}", jobSearchController.jobSearch);
        app.post("/jobInsertJob/{jobId}/{applicantId}", jobSearchController.insertAppliedJobs);
        app.get("/viewAppliedJobs/{applicantId}", jobSearchController.viewAppliedJobs);

        app.post("/update/applicant", updateController.updateApplicant);
        app.post("/update/Company", updateController.updateCompany);

        app.start();
        logger.info("Application started successfully.");

    } //main end

}
