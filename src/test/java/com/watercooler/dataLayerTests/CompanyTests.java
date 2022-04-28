package com.watercooler.dataLayerTests;
import com.watercooler.entities.Company;
import com.watercooler.daos.CompanyDAOImp;
import org.testng.annotations.Test;
import org. testng.Assert;

public class CompanyTests {

    CompanyDAOImp companyDAO = new CompanyDAOImp();


    @Test
    public void updateCompanySuccess(){

        Company company = new Company(-1, "companyName: 1", "phone: 2", "email: 3", "aboutUs: 4", "relatedJobs: 5","Atlanta");
         int result = companyDAO.updateCompany(company);
         Assert.assertTrue(result == 1);
    }




}
