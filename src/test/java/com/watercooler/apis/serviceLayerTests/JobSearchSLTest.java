package com.watercooler.apis.serviceLayerTests;

import com.watercooler.daos.JobSearchDAOImp;
import com.watercooler.entities.Job;
import com.watercooler.saos.JobSearchSLImp;
import com.watercooler.utilities.customExceptions.JobNotFound;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.*;

public class JobSearchSLTest {

    Job job = new Job();
    List<Job> jobList = new ArrayList<Job>();
    List<Job> emptyList = new ArrayList<Job>();
    JobSearchDAOImp jobSearchDAO;
    JobSearchSLImp jobSearchSAO;


    @BeforeClass
    public void setup() {

        jobList.add(job);
        jobSearchDAO = Mockito.mock(JobSearchDAOImp.class);
        jobSearchSAO = new JobSearchSLImp(jobSearchDAO);

    };

    @Test
    public void selectJobSuccess() { //tests if the tunnel is open
        Mockito.doReturn(jobList).when(jobSearchDAO).selectJob("", "");
        //Mockito.doReturn(1).when(JobSearchDAO).selectJob("", "");
        List<Job> result = jobSearchSAO.selectJob("", "");
        Assert.assertEquals(result, jobList);
    }

    @Test
    public void selectJobEmptyList() {
        Mockito.doReturn(emptyList).when(jobSearchDAO).selectJob("hi", "hi");
        List<Job> result = jobSearchSAO.selectJob("hi", "hi");
        Assert.assertEquals(result, emptyList);
    }

    @Test
    public void viewAppliedJobsSuccess() {
        Mockito.doReturn(jobList).when(jobSearchDAO).viewAppliedJobs(-2);
            List<Job> result = jobSearchSAO.viewAppliedJobs(-2);
            Assert.assertEquals(result, jobList);
    }

    @Test
    public void viewAppliedJobEmptyList() {
        Mockito.doReturn(emptyList).when(jobSearchDAO).viewAppliedJobs(-2);
        List<Job> result = jobSearchSAO.viewAppliedJobs(-2);
        Assert.assertEquals(result, emptyList);

        }

    @Test
    public void insertAppliedJobSuccess() {
        Mockito.doReturn(1).when(jobSearchDAO).insertAppliedJobs(-2, -2);
        boolean result = jobSearchSAO.insertAppliedJobs(-2, -2);
        Assert.assertTrue(result);
    }
}
