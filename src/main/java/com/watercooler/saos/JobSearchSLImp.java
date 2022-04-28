package com.watercooler.saos;

import com.watercooler.entities.Job;

import java.util.List;
import com.watercooler.daos.JobSearchDAOInt;
import com.watercooler.utilities.customExceptions.ApplicationUnsuccessful;
import com.watercooler.utilities.customExceptions.JobNotFound;
import com.watercooler.utilities.customExceptions.NoAppliedJobsFound;

public class JobSearchSLImp implements JobSearchSLInt {
    public JobSearchDAOInt jobSearchDAO;

    public JobSearchSLImp(JobSearchDAOInt jobSearchDAO) {
        this.jobSearchDAO = jobSearchDAO;
    }


    @Override
        public boolean insertAppliedJobs(int jobId, int applicantId) {
            int result = jobSearchDAO.insertAppliedJobs(jobId, applicantId);
            if (result == 1) {
                return true;
            } else {
                throw new ApplicationUnsuccessful("Record application unsuccessful. Please contact administration.");
            }
        }


    @Override
    public List<Job> viewAppliedJobs(int applicantId) {
        List<Job> viewAppliedJobsJobs = jobSearchDAO.viewAppliedJobs(applicantId);
        if (viewAppliedJobsJobs == null) {
            throw new NoAppliedJobsFound("There are no jobs for which you have applied.");
        } else {
            return viewAppliedJobsJobs;
        }
    }


    @Override
    public List<Job> selectJob(String jobType, String jobLocation) throws JobNotFound {
        List<Job>job = jobSearchDAO.selectJob(jobType, jobLocation);
        if (job == null) {
            throw new JobNotFound("Could not find job");
        } else {
            System.out.println(job);
            return job;

        }
    }
}


