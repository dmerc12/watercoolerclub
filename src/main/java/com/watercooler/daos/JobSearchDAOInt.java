package com.watercooler.daos;
import java.util.List;
import com.watercooler.entities.Job;

public interface JobSearchDAOInt{

    List<Job> selectJob(String jobLocation, String jobType);

    int insertAppliedJobs(int jobId, int applicantId);

    List<Job> viewAppliedJobs (int applicantId);


}

