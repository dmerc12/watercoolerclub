package com.watercooler.dataLayerTests;

import com.watercooler.daos.JobSearchDAOImp;
import com.watercooler.entities.Job;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AppliedJobsTest {
    JobSearchDAOImp jobSearchDAO = new JobSearchDAOImp();

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
}





