package com.watercooler.javalin;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.watercooler.daos.ApplicantDAO;
import com.watercooler.daos.CompanyDAO;
import com.watercooler.daos.CompanyDAOImp;
import com.watercooler.entities.Applicant;
import com.watercooler.entities.Company;
import com.watercooler.saos.ApplicantSAOImp;
import com.watercooler.saos.ApplicantSAOSInterface;
import com.watercooler.saos.CompanySAOImp;
import com.watercooler.saos.CompanySAOSInterface;
import com.watercooler.utilities.customExceptions.CustomUncheckedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.javalin.http.Handler;
import com.watercooler.daos.ApplicantDAOImp;



public class UpdateApplicantController{

    public static Logger logger = LogManager.getLogger(UpdateApplicantController.class);
    public ApplicantSAOSInterface SAOS;
    public UpdateApplicantController(ApplicantSAOSInterface SAOS) {
        this.SAOS = SAOS;

    }
    ApplicantDAO DAO = new ApplicantDAOImp();



    public Handler updateApplicant = ctx -> {

        try {
            logger.info("update an applicant");
            String body = ctx.body();
            Gson gson = new Gson();
            logger.info("1");
            Applicant updateApplicant = gson.fromJson(body, Applicant.class);
            logger.info("2");
            int info = SAOS.catchErrorsApplicant(updateApplicant);
            logger.info(info);
            String infoUpdate = gson.toJson(info);
            ctx.result(infoUpdate);

            ctx.status(201);
        } catch (CustomUncheckedException e) {
            ctx.result(e.getMessage());
            ctx.status(405);
        }
    };

    CompanyDAO SI = new CompanyDAOImp();
    CompanySAOSInterface LI  = new CompanySAOImp(SI);

    public Handler updateCompany = ctx -> {

        try {
            logger.info("update company");
            String body = ctx.body();
            Gson gson = new Gson();
            logger.info("1");
            Company updateCompany = gson.fromJson(body, Company.class);
            logger.info("2");
            int info = LI.catchErrorsCompany(updateCompany);
            logger.info(info);
            String infoupdate = gson.toJson(info);
            ctx.result(infoupdate);


            ctx.status(201);

        } catch (CustomUncheckedException e) {
            ctx.result(e.getMessage());
            ctx.status(405);
        }


     };



}
