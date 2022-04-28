package com.watercooler.daos;


import com.watercooler.entities.*;
import com.watercooler.utilities.*;

import java.sql.*;

public class UsernamePasswordApplicantDAOImp implements UsernamePasswordApplicantDAOInterface {

    @Override
    public UsernamePasswordApplicant createAccountApplicant(UsernamePasswordApplicant createdApplicant) {
        try(Connection connection = DatabaseConnection.createConnection()){

            String sql = "insert into usernames_passwords_applicant values(default, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, createdApplicant.getApplicantUsername());
            ps.setString(2, createdApplicant.getApplicantPassword());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            createdApplicant.setApplicantId(rs.getInt("applicant_id"));
            return createdApplicant;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UsernamePasswordApplicant selectApplicantById(int id) {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from usernames_passwords_applicant where applicant_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery(); // executeQuery returns a result set object with the results
            rs.next();
            UsernamePasswordApplicant usernamePasswordApplicant = new UsernamePasswordApplicant(
                    rs.getInt("applicant_id"),
                    rs.getString("applicant_username"),
                    rs.getString("applicant_password")
            );
            return usernamePasswordApplicant;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int verifyUsernamePasswordApplicant(UsernamePasswordApplicant UnPw) {
        try(Connection connection = DatabaseConnection.createConnection()){
            int counter = 0;
            String sql = "select applicant_id from usernames_passwords_applicant where applicant_username = ? and applicant_password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, UnPw.getApplicantUsername());
            ps.setString(2, UnPw.getApplicantPassword());
            ResultSet rs = ps.executeQuery(); // executeQuery returns a result set object with the results
            if (rs.next()) {
                return rs.getInt("applicant_id");
            }
            else {
                return 0;
            }
        } catch(SQLException e){
            e.printStackTrace();
            return 0;
        }

}

}

