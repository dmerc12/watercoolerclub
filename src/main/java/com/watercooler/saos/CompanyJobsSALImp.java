package com.watercooler.saos;

import com.watercooler.daos.CompanyJobsDALImp;
import com.watercooler.entities.Applicant;
import com.watercooler.entities.Job;
import com.watercooler.utilities.customExceptions.EmptyInput;
import com.watercooler.utilities.customExceptions.InputTooLong;
import com.watercooler.utilities.customExceptions.NoApplicants;
import com.watercooler.utilities.customExceptions.NoJobFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class CompanyJobsSALImp implements CompanyJobsSAL{
    private CompanyJobsDALImp jobsDAO;

    public static Logger logger = LogManager.getLogger(CompanyJobsSALImp.class);

    public CompanyJobsSALImp(CompanyJobsDALImp jobsDAO) {
        this.jobsDAO = jobsDAO;
    }

    @Override
    public Job servicePostJob(Job job) {
        logger.info("Begining SAL function createJobPost with data: /n" + job);
        if(job.getTitle().length() == 0 || job.getType().length() == 0 || job.getDescription().length() == 0 || job.getLocation().length() == 0 || job.getCompanyName().length() == 0){
            logger.warn("Inputs for create job post function were left blank!");
            throw new EmptyInput("All input must be filled out!");
        }
        if(job.getTitle().length() > 25){
            logger.warn("Title input for create job post function was too long!");
            throw new InputTooLong("Job title is too long!");
        } else if(job.getType().length() > 30){
            logger.warn("Type input for create job post function was too long!");
            throw new InputTooLong("Job type is too long!");
        } else if(job.getDescription().length() > 500){
            logger.warn("Description input for create job post function was too long!");
            throw new InputTooLong("Job description is too long!");
        } else if(job.getLocation().length() > 100){
            logger.warn("Location input for create job post function was too long!");
            throw new InputTooLong("Job location is too long!");
        } else if(job.getCompanyName().length() > 50){
            logger.warn("Company name input for create job post function was too long!");
            throw new InputTooLong("Company name is too long!");
        } else{
            Job result = jobsDAO.postJob(job);
            logger.info("Finishing SAL function createJobPost with result: /n" + result);
            return result;
        }

    }

    @Override
    public List<Job> serviceViewJobs(int companyId) {
        logger.info("Begining SAL function viewJobs with data: " + companyId);
        List<Job> returningJobs = jobsDAO.viewJobs(companyId);
        if(returningJobs.size() < 1){
            logger.warn("List of returning jobs = null");
            throw new NoJobFound("There are no posted jobs with the company ID provided!");
        }else{
            logger.info("Finishing SAL function viewJobs with result: /n" + returningJobs);
            return returningJobs;
        }
    }

    @Override
    public List<Applicant> serviceViewApplicants(int jobId) {
        logger.info("Begining SAL function viewApplicants with data: " + jobId);
        List<Applicant> returningApplicants = jobsDAO.viewApplicants(jobId);
        if(returningApplicants.size() >= 1){
            logger.info("Finishing SAL function viewApplicants with result: /n" + returningApplicants);
            return returningApplicants;
        } else {
            logger.warn("List of returning applicants = null");
            throw new NoApplicants("There are no applicants for the job ID provided!");
        }
    }

    @Override
    public int serviceDeleteJobs(int jobId) {
        logger.info("Begining SAL function deleteJobs with data: " + jobId);
        int result = jobsDAO.deleteJobs(jobId);
        if (result == 0){
            logger.warn("Error with SAL function deleteJobs");
            throw new NoJobFound("There are no posted jobs with the company ID provided!");
        } else {
            logger.info("Successfully finishing SAL function deleteJobs");
            return 1;
        }
    }
}
