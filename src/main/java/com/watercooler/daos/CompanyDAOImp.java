package com.watercooler.daos;
import com.watercooler.entities.Company;
import java.sql.*;
import com.watercooler.utilities.DatabaseConnection;



public class CompanyDAOImp implements CompanyDAO {


    @Override
    public int updateCompany(Company company) {
        try (Connection connection = DatabaseConnection.createConnection()) {
        String sql = "update company_table set company_name =?, company_phone_number =?, company_email =?, company_about_us =?, company_related_jobs =?, company_location =? where company_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, company.getName());
        ps.setString(2, company.getPhoneNumber());
        ps.setString(3, company.getEmail());
        ps.setString(4, company.getAboutUs());
        ps.setString(5, company.getRelatedJobs());
        ps.setString(6, company.getLocation());
        ps.setInt(7, company.getId());
        return ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        return 0;

    }

    }
}