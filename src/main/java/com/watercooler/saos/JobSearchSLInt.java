package com.watercooler.saos;

import com.watercooler.entities.Job;
import com.watercooler.utilities.customExceptions.JobNotFound;
import kotlin.collections.EmptyList;

import java.util.List;

public interface JobSearchSLInt{

    List<Job> selectJob(String jobLocation, String jobType) throws JobNotFound;

    boolean insertAppliedJobs(int jobId, int applicantId);

    List<Job> viewAppliedJobs (int applicantId);



}
