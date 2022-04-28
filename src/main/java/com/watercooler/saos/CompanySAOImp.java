package com.watercooler.saos;
import com.watercooler.daos.CompanyDAO;
import com.watercooler.entities.Company;
import com.watercooler.daos.CompanyDAOImp;
import com.watercooler.utilities.customExceptions.CustomUncheckedException;




public class CompanySAOImp implements CompanySAOSInterface{
    private CompanyDAO daoImp;
    public CompanySAOImp(CompanyDAO daoImp) { this.daoImp = daoImp;}



    @Override
    public int catchErrorsCompany(Company company) {


        if (company.getName().length() > 45) {
            throw new CustomUncheckedException("Company name cannot exceed 45 characters");
        } else if (company.getPhoneNumber().length() > 10) {
            throw new CustomUncheckedException("Phone numbers cannot be less than 10 numbers");
        } else if (company.getEmail().length() > 30) {
            throw new CustomUncheckedException("Emails cannot exceed 30 characters");
        } else if (company.getAboutUs().length() > 500) {
            throw new CustomUncheckedException("Work descriptions cannot exceed 500 characters");
        } else if (company.getRelatedJobs().length() > 800) {
            throw new CustomUncheckedException("Work reference descriptions exceed 800 characters");
        }

        return daoImp.updateCompany(company);
    }
}
