package com.watercooler.apis;
import io.javalin.Javalin;
public class JobSearchMain {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        JobSearchController jobSearchController = new JobSearchController();

        app.get("/jobSearch/{jobType}/{jobLocation}", jobSearchController.jobSearch);

        app.post("/jobInsertJob/{jobId}/{applicantId}", jobSearchController.insertAppliedJobs);
        app.get("/viewAppliedJobs/{applicantId}", jobSearchController.viewAppliedJobs);

        app.start();



    }


}

