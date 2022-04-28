package com.watercooler.daos;

import com.watercooler.entities.Job;
import com.watercooler.utilities.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobSearchDAOImp implements JobSearchDAOInt {

    @Override
    public List<Job> selectJob(String jobType, String jobLocation) {

        try (Connection connection = DatabaseConnection.createConnection()) {
            String append = "";
            if (jobLocation.equals("nothing") && !jobType.equals("nothing")) {
                append = " where job_type = " + "'" + jobType + "'";
            } else if (!jobLocation.equals("nothing") && jobType.equals("nothing")) {
                append = " where job_location = " + "'" + jobLocation + "'";
            } else if (!jobLocation.equals("nothing") && !jobType.equals("nothing")) {
                append = " where job_location = " + "'" + jobLocation + "'" + " and job_type = " + "'" + jobType + "'";
            } else if (jobLocation.equals("nothing") && jobType.equals("nothing")) {
                append = ""; // should be better way to do this
            }

//            System.out.println(append);
//
            String sql = "select * from job_table" + append;
//            System.out.println(sql);
            Statement s = connection.createStatement();
//            select * from job_table where job_location = 'testLocation4' and job_type = 'Technology'
            s.execute(sql);
            ResultSet rs = s.getResultSet();
            List<Job> joblist = new ArrayList<>();
            while (rs.next()) {
                Job job = new Job(
                        rs.getInt("job_id"),
                        rs.getString("job_title"),
                        rs.getString("job_type"),
                        rs.getString("job_description"),
                        rs.getInt("company_id"),
                        rs.getString("job_location"),
                        rs.getString("company_name")
                );
                joblist.add(job);
            }
            //rs.next();
            System.out.println(joblist);
            return joblist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public int insertAppliedJobs(int jobId, int applicantId) {
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "insert into applied_jobs values(?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, jobId);
            ps.setInt(2, applicantId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Job> viewAppliedJobs(int applicantId) {

        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from applied_jobs join job_table on applied_jobs.job_id = job_table.job_id where applicant_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, applicantId);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            List<Job> joblist = new ArrayList<>();

            while (rs.next()) {
                Job job = new Job(
                        rs.getInt("job_id"),
                        rs.getString("job_title"),
                        rs.getString("job_type"),
                        rs.getString("job_description"),
                        rs.getInt("company_id"),
                        rs.getString("job_location"),
                        rs.getString("company_name")
                );
                joblist.add(job);
            }
            //rs.next();
            return joblist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        JobSearchDAOImp JSDI = new JobSearchDAOImp();
        List<Job> result = JSDI.viewAppliedJobs(-1);
        System.out.println(result.toArray().length);

    }


    }















//    try(Connection connection = DatabaseConnection.createConnection()){
//        Int append = "";
//        Int sql = "select jobId from job_table" + append applied_jobs_table;
//        Int sql = "select applicantId from applicant_table" + append applied_jobs_table
//        System.out.println(sql);
//        Statement s = connection.createStatement();
//        s.execute(sql);
//        ResultSet rs = s.getResultSet();
//        List<Job> appliedjobs = new ArrayList<>();
//        return appliedjobs;
//    } catch(SQLException e){
//        e.printStackTrace();
//        return null;
//    }
//
//}
//
