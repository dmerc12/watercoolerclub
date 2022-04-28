package com.watercooler.saos;
import com.watercooler.daos.ApplicantDAO;
import com.watercooler.entities.Applicant;
import com.watercooler.daos.ApplicantDAOImp;
import com. watercooler.utilities.customExceptions.CustomUncheckedException;


public class ApplicantSAOImp implements ApplicantSAOSInterface{
    private ApplicantDAO daoImp;
    public ApplicantSAOImp(ApplicantDAO daoImp){
        this.daoImp = daoImp;
    }


    @Override
    public int catchErrorsApplicant(Applicant applicant) {

        if (applicant.getFirstName().length() > 25) {
            throw new CustomUncheckedException("First names cannot exceed 25 characters");
        } else if (applicant.getLastName().length() > 25) {
            throw new CustomUncheckedException("Last names cannot exceed 25 characters");
        } else if (applicant.getPhoneNumber().length() > 10){
            throw new CustomUncheckedException("Phone numbers cannot be more than 10 numbers");
        } else if (applicant.getEmail().length() > 30) {
            throw new CustomUncheckedException("Emails cannot exceed 30 characters");
        } else if (applicant.getWorkHistory().length() > 500) {
            throw new CustomUncheckedException("Work descriptions cannot exceed 500 characters");
        } else if (applicant.getReferences().length() > 800) {
            throw new CustomUncheckedException("Work reference descriptions exceed 800 characters");
        } else if (applicant.getEducation().length() > 200) {
            throw new CustomUncheckedException("Education descriptions cannot exceed 200 characters");
        }

        int result = daoImp.updateApplicant(applicant); // return 1

//        if (result == 0){
//                throw new
//            }
        return result;
//


    }

    public static void main(String[] args){
        ApplicantDAOImp daoImp = new ApplicantDAOImp();
        ApplicantSAOImp ASI = new ApplicantSAOImp(daoImp);

        String words = "haha";
        while (words.length() < 27) {
            words = words.concat("haha");
        }
        Applicant testMock = new Applicant(
                1,
                words,
                "it doesn't matter",
                "not number",
                "fake mail",
                "wer",
                "WHO CARES",
                "NO SCHOOL",
                "sdf");
        ASI.catchErrorsApplicant(testMock);

    }
}