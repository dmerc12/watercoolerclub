package com.watercooler.apis;

import io.javalin.Javalin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.watercooler.daos.UsernamePasswordApplicantDAOImp;
import com.watercooler.daos.UsernamePasswordApplicantDAOInterface;
import com.watercooler.daos.UsernamePasswordCompanyDAOImp;
import com.watercooler.daos.UsernamePasswordCompanyDAOInterface;
import com.watercooler.saos.UsernamePasswordApplicantSAOImp;
import com.watercooler.saos.UsernamePasswordApplicantSAOInterface;
import com.watercooler.saos.UsernamePasswordCompanySAOImp;
import com.watercooler.saos.UsernamePasswordCompanySAOInterface;

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

        app.start();
        logger.info("Application started successfully.");

    } //main end

}
