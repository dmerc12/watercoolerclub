package com.watercooler.dataLayerTests;
import com.watercooler.entities.Applicant;

import com.watercooler.daos.ApplicantDAOImp;
import org.testng.annotations.Test;
import org.testng.Assert;

public class ApplicantTests {

    ApplicantDAOImp applicantDAO = new ApplicantDAOImp();

            @Test
            public void updateApplicantSuccess(){
               Applicant applicant = new Applicant(2, "firstname", "lastname", "4055674532", "email", "work history", "references", "education", "desired job");
               int result  = applicantDAO.updateApplicant(applicant);
               Assert.assertTrue(result ==1);

            }
}
