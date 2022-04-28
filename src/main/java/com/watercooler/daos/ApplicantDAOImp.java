package com.watercooler.daos;
import java.sql.*;
import com.watercooler.entities.Applicant;
import com.watercooler.utilities.DatabaseConnection;


public class ApplicantDAOImp implements ApplicantDAO{


    @Override
    public int updateApplicant(Applicant applicant) {

        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "update applicant_table set applicant_first_name =? , applicant_last_name=?," +
                    " applicant_phone_number = ?, applicant_email = ?, applicant_work_history = ? ," +
                    " applicant_references = ?,  applicant_education = ? , applicant_desired_job = ? where applicant_id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);


            ps.setString(1,applicant.getFirstName());
            ps.setString(2, applicant.getLastName());
            ps.setString(3, applicant.getPhoneNumber());
            ps.setString(4,applicant.getEmail());
            ps.setString(5, applicant.getWorkHistory());
            ps.setString(6,applicant.getReferences());
            ps.setString(7,applicant.getEducation());
            ps.setString(8,applicant.getDesiredJob());
            ps.setInt(9,applicant.getId());
            ps.executeUpdate();


            return 1; //return 1
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }


    }












