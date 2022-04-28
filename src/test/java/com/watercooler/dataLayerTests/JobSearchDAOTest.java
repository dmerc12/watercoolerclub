package com.watercooler.dataLayerTests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.watercooler.daos.JobSearchDAOImp;
import com.watercooler.entities.Job;
import java.util.List;




public class JobSearchDAOTest {

    JobSearchDAOImp jobSearchDAO = new JobSearchDAOImp();

    @Test
    public void selectJobSuccess() {
        List<Job> jobList = jobSearchDAO.selectJob("Technology", "testLocation2");
        Assert.assertNotEquals(jobList.size(), 0);

    }

    @Test
    public void selectJobNoLocation() {
        List<Job> jobList = jobSearchDAO.selectJob("nothing", "management");
        Assert.assertNotEquals(jobList.size(), 0);

    }

        @Test
        public void selectJobNoType() {
            List<Job> jobList = jobSearchDAO.selectJob("testLocation2", "nothing");
            Assert.assertNotEquals(jobList.size(), 0);

    }

    @Test
    public void selectJobNoLocationNoType() {
        List<Job> jobList = jobSearchDAO.selectJob("nothing", "nothing");
        Assert.assertNotEquals(jobList.size(), 0);

    }


}
