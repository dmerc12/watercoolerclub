package com.watercooler.apis;

import com.google.gson.Gson;
import com.watercooler.entities.UsernamePasswordApplicant;
import com.watercooler.entities.UsernamePasswordCompany;
import com.watercooler.saos.UsernamePasswordApplicantSAOInterface;
import com.watercooler.saos.UsernamePasswordCompanySAOInterface;
import com.watercooler.utilities.CustomUncheckedException;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JavalinController {

    public static Logger logger = LogManager.getLogger(JavalinController.class);

    public UsernamePasswordApplicantSAOInterface usernamePasswordApplicantSAOImp;
    public UsernamePasswordCompanySAOInterface usernamePasswordCompanySAOImp;

    public JavalinController(UsernamePasswordApplicantSAOInterface usernamePasswordApplicantSAOImp, UsernamePasswordCompanySAOInterface usernamePasswordCompanySAOImp) {
        this.usernamePasswordApplicantSAOImp = usernamePasswordApplicantSAOImp;
        this.usernamePasswordCompanySAOImp = usernamePasswordCompanySAOImp;
    }

    public Handler UnPwApplicantVerify = ctx -> {
        logger.info("getting an applicant");
        String body = ctx.body();

        Gson gson = new Gson();
        UsernamePasswordApplicant usernamePasswordApplicantGson = gson.fromJson(body, UsernamePasswordApplicant.class);

        int info = usernamePasswordApplicantSAOImp.catchErrorsUnPwApplicant(usernamePasswordApplicantGson);
//

        ctx.result(String.valueOf(info));
        logger.info("applicant got");
    };

    public Handler UnPwCompanyVerify = ctx -> {
        logger.info("getting an company");
        String body = ctx.body();

        Gson gson = new Gson();
        UsernamePasswordCompany usernamePasswordCompanyGson = gson.fromJson(body, UsernamePasswordCompany.class);

        int info = usernamePasswordCompanySAOImp.catchErrorsUnPwCompany(usernamePasswordCompanyGson);
//
//        String infoConvert = gson.toJson(info);

        ctx.result(String.valueOf(info));
        logger.info("company got");
    };

//    public Handler UnPwApplicantGet = ctx -> {
//        logger.info("getting an applicant");
//        String body = ctx.body();
//
//        Gson gson = new Gson();
//        UsernamePasswordApplicant usernamePasswordApplicantGson = gson.fromJson(body, UsernamePasswordApplicant.class);
//
//        UsernamePasswordApplicant info = usernamePasswordApplicantSAOImp.selectApplicantById(usernamePasswordApplicantGson.getApplicantId());
////
//        String infoConvert = gson.toJson(info);
//
//        ctx.result(infoConvert);
//        logger.info("applicant got");
////        int id2 = id.getApplicantId();
////
////        String id3 = gson.toJson(id2);
//////        id3 = gson.toJson(id2);
////
////        ctx.result(id3);
//
//    };
//
//    public Handler UnPwCompanyGet = ctx -> {
//
//        String body = ctx.body();
//
//        Gson gson = new Gson();
//        UsernamePasswordCompany usernamePasswordCompanyGson = gson.fromJson(body, UsernamePasswordCompany.class);
//
//        UsernamePasswordCompany info = usernamePasswordCompanySAOImp.selectCompanyById(usernamePasswordCompanyGson.getCompanyId());
////
//        String infoConvert = gson.toJson(info);
//
//        ctx.result(infoConvert);
//        int id2 = id.getApplicantId();
//
//        String id3 = gson.toJson(id2);
////        id3 = gson.toJson(id2);
//
//        ctx.result(id3);

//    };


    public Handler UnPwApplicantCreate = ctx -> {
        try {
            String body = ctx.body();

            Gson gson = new Gson();

            UsernamePasswordApplicant usernamePasswordApplicantGson = gson.fromJson(body, UsernamePasswordApplicant.class);

            UsernamePasswordApplicant creation = usernamePasswordApplicantSAOImp.catchErrorsAccountApplicant(usernamePasswordApplicantGson);

            String creating = gson.toJson(creation);

            ctx.result(creating);
            ctx.status(201);

        } catch (CustomUncheckedException exception) {
            ctx.status(400);

        }
        // body


        //get return value of the service layer


        // return to the requester

    };

    public Handler UnPwCompanyCreate = ctx -> {

        try {
            String body = ctx.body();

            Gson gson = new Gson();

            UsernamePasswordCompany usernamePasswordCompanyGson = gson.fromJson(body, UsernamePasswordCompany.class);

            UsernamePasswordCompany creation = usernamePasswordCompanySAOImp.catchErrorsUsernamePasswordCompany(usernamePasswordCompanyGson);

            String creating = gson.toJson(creation);

            ctx.result(creating);

        } catch (CustomUncheckedException exception) {
            ctx.status(400);

        }



    };
}

        // use the context object Javalin provides to read your path parameters

//        try(Connection connection = DatabaseConnection.createConnection()){
//
//            String sql = "select * from usernames_passwords_applicant where applicant_id = ?";
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, UsernamePasswordApplicant.getApplicantId());
//            ps.setString(2, UsernamePasswordApplicant.getApplicantUsername());
//            ps.setString(3, UsernamePasswordApplicant.getApplicantId());
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            rs.next();
//            createdApplicant.setApplicantId(rs.getInt("applicant_id"));
//            return createdApplicant;
//        } catch(SQLException e){
//            e.printStackTrace();
//            return null;

//        int id = Integer.parseInt(ctx.pathParam("id"));
//        for(UsernamePasswordApplicant unpw : ){
//            if(p.getPersonId() == id){
//                Gson gson = new Gson();
//                String personJSON = gson.toJson(p);
//                ctx.result(personJSON);
//                ctx.status(200);
//            }
//        }
//    };
//
//
//
//    public Handler createApplicant = ctx -> {
//        // get the body of the request
//        String body = ctx.body();
//        // use gson to convert the json into an object that we need
//        Gson gson = new Gson();
//        UsernamePasswordApplicant usernamePasswordApplicant = gson.fromJson(body, UsernamePasswordApplicant.class);
//        // this code here simulates sending the new object into your service/da layer
//        usernamePasswordApplicant.setPersonId(personIdGenerator);
//        personIdGenerator++;
//        people.add(person);
//
//        // return a result message
//        ctx.result("Applicant created");
//        ctx.status(201);
//    };



