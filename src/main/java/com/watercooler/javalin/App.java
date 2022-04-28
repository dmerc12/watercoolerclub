package com.watercooler.javalin;
import com.watercooler.daos.ApplicantDAOImp;
import com.watercooler.saos.ApplicantSAOImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.javalin.Javalin;

public class App {

    public static Logger logger = LogManager.getLogger(App.class);
    ApplicantSAOImp saoImp;

    public static void main(String[] args) {
        logger.info("update Javalin object now");
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        ApplicantDAOImp daoImp = new ApplicantDAOImp();
        ApplicantSAOImp saoImp = new ApplicantSAOImp(daoImp);
        UpdateApplicantController controller = new UpdateApplicantController(saoImp);

            app.post("/update/applicant", controller.updateApplicant);

            app.post("/update/Company", controller.updateCompany);


            logger.info("Starting web server");

            app.start();














}

}
