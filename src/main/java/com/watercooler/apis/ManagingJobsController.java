package com.watercooler.apis;
import com.google.gson.Gson;
import com.watercooler.daos.CompanyJobsDALImp;
import com.watercooler.entities.Applicant;
import com.watercooler.entities.Job;
import com.watercooler.saos.CompanyJobsSALImp;
import com.watercooler.utilities.customExceptions.InputTooLong;
import com.watercooler.utilities.customExceptions.NoApplicants;
import com.watercooler.utilities.customExceptions.NoJobFound;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class ManagingJobsController {
    CompanyJobsDALImp DAO = new CompanyJobsDALImp();
    CompanyJobsSALImp SAO = new CompanyJobsSALImp(DAO);

    public static Logger logger = LogManager.getLogger(ManagingJobsController.class);

    public Handler createJobPost = ctx -> {
        try{
            logger.info("Beginning API handler createJobPost with information: /n" + ctx);
            String requestBody = ctx.body();
            Gson gson = new Gson();
            Job job = gson.fromJson(requestBody, Job.class);
            SAO.servicePostJob(job);
            ctx.result("Job post was successfully created!");
            ctx.status(201);
            logger.info("Finished API handler createJobPost with result: /n" + job + "/nStatus Code: " + 201);
        } catch (InputTooLong exception){
            ctx.result(exception.getMessage());
            ctx.status(400);
            logger.error("Error with API handler createJobPost with error: " + exception.getMessage());
        }
    };

    public Handler viewJobs = ctx -> {
        try {
            logger.info("Beginning API handler viewJobs with information: /n" + ctx);
            String requestBody = ctx.body();
            Gson gson = new Gson();
            Job companyId = gson.fromJson(requestBody, Job.class);
            List<Job> jobs = SAO.serviceViewJobs(companyId.getCompanyId());
            for (Job job : jobs) {
                if (job.getCompanyId() == companyId.getCompanyId()) {
                    String jobJSON = gson.toJson(job);
                    ctx.result(jobJSON);
                    ctx.status(200);
                    logger.info("Finished API handler view jobs with result: /n" + jobJSON + "/nStatus Code: " + 201);
                }
            }
        } catch (NoJobFound exception){
            ctx.result("There are no posted jobs with the company ID provided!");
            ctx.status(400);
            logger.error("Error with API handler viewJobs with error: " + exception.getMessage());
        }
    };

    public Handler viewApplicants = ctx -> {
        try {
            logger.info("Beginning API handler viewApplicants with information: /n" + ctx);
            String requestBody = ctx.body();
            Gson gson = new Gson();
            Job jobId = gson.fromJson(requestBody, Job.class);
            List<Applicant> applicants = SAO.serviceViewApplicants(jobId.getJobId());
            for (Applicant applicant : applicants) {
                String applicantJSON = gson.toJson(applicant);
                ctx.result(applicantJSON);
                ctx.status(200);
                logger.info("Finished API handler viewApplicants with result: /n" + applicantJSON + "/nStatus Code: " + 201);
            }
        } catch (NoApplicants exception) {
            ctx.result("There are no applicants for the job ID provided!");
            ctx.status(400);
            logger.error("Error with API handler viewApplicants with error: " + exception.getMessage());
        }
    };

    public Handler deleteJobPost = ctx -> {
        try {
            logger.info("Beginning API handler deleteJobPost with information: /n" + ctx);
            String requestBody = ctx.body();
            Gson gson = new Gson();
            Job jobId = gson.fromJson(requestBody, Job.class);
            SAO.serviceDeleteJobs(jobId.getJobId());
            ctx.result("Job post successfully removed.");
            ctx.status(200);
            logger.info("Finished API handler deleteJobPost with result: /n" + jobId + "/nStatus Code: " + 201);
        } catch (NoJobFound exception){
            ctx.result("There are no posted jobs with the company ID provided!");
            ctx.status(400);
            logger.error("Error with API handler deleteJobPost with error: " + exception.getMessage());
        }
    };
}
