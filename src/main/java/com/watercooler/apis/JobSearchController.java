package com.watercooler.apis;
import com.watercooler.saos.JobSearchSLInt;
import com.watercooler.saos.JobSearchSLImp;
import com.watercooler.daos.JobSearchDAOInt;
import com.watercooler.daos.JobSearchDAOImp;
import com.watercooler.utilities.customExceptions.ApplicationUnsuccessful;
import com.watercooler.utilities.customExceptions.NoAppliedJobsFound;
import io.javalin.http.Handler;
import com.google.gson.Gson;
import com.watercooler.entities.Job;
import java.util.*;



public class JobSearchController {
       public JobSearchDAOInt jobSearchDAOInt = new JobSearchDAOImp();
       public JobSearchSLInt jobSearchSLInt = new JobSearchSLImp(jobSearchDAOInt);
//       This is the service layer object

       //    [access modifier] Handler [name] = ctx -> {
//        code goes here
//    }
       public Handler jobSearch = ctx -> {
              String jobLocation = ctx.pathParam("jobLocation");
              String jobType = ctx.pathParam("jobType");
              System.out.println(jobLocation);
              System.out.println(jobType);
              List<Job> resultJobList = jobSearchSLInt.selectJob(jobType, jobLocation);
              System.out.println(resultJobList);
              Gson gson = new Gson();
              String jobSearchJson = gson.toJson(resultJobList);
              System.out.println(jobSearchJson);
              ctx.result(jobSearchJson);
              ctx.status(200);

       };

       public Handler insertAppliedJobs = ctx -> {
              try {
                     Gson gson = new Gson();
                     int jobId = Integer.parseInt(ctx.pathParam("jobId"));
                     int applicantId = Integer.parseInt(ctx.pathParam("applicantId"));
                     boolean result = jobSearchSLInt.insertAppliedJobs(jobId, applicantId);
                     if (result) {
                            ctx.result("{\"message\": \"Application created successfully\"}");
                            ctx.status(201);
                     }
              } catch (ApplicationUnsuccessful e) {
                     ctx.result(e.getMessage());
                     ctx.status(405);
              }
       };


       public Handler viewAppliedJobs = ctx -> {
              try {
                     Gson gson = new Gson();
                     int applicantId = Integer.parseInt(ctx.pathParam("applicantId"));
                     List<Job> resultJobList = jobSearchSLInt.viewAppliedJobs(applicantId);
                     System.out.println(resultJobList);
                     String jobSearchJson = gson.toJson(resultJobList);
                     System.out.println(jobSearchJson);
                     ctx.result(jobSearchJson);
                     ctx.status(200);
              } catch (NoAppliedJobsFound e) {
                     ctx.result(e.getMessage());
                     ctx.status(404);
              }

       };
}







