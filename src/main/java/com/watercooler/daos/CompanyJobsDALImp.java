package com.watercooler.daos;
import com.watercooler.entities.Applicant;
import com.watercooler.entities.Job;
import com.watercooler.utilities.DatabaseConnection;
import com.watercooler.utilities.customExceptions.NoJobFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyJobsDALImp  implements CompanyJobsDAL {

    public static Logger logger = LogManager.getLogger(CompanyJobsDALImp.class);

    @Override
    public Job postJob(Job job){
        logger.info("Begining DAL function createJobPost with data: /n" + job);
        try (Connection connection = DatabaseConnection.createConnection()){
            String sql = "insert into job_table values (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, job.getJobId());
                ps.setString(2, job.getTitle());
                ps.setString(3, job.getType());
                ps.setString(4, job.getDescription());
                ps.setInt(5, job.getCompanyId());
                ps.setString(6, job.getLocation());
                ps.setString(7, job.getCompanyName());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                job.setJobId(rs.getInt("job_id"));
            }
            logger.info("Finishing DAL function createJobPost with result: /n" + job);
            return job;
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Error with DAL function createJobPost with error: " + exception.getMessage());
            return null;
        }
    };

    @Override
    public List<Job> viewJobs(int companyId){
        logger.info("Begining DAL function viewJobs with data: /n" + companyId);
        try (Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from job_table where company_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, -1);
            ResultSet rs = ps.executeQuery();
            List<Job> testJobs = new ArrayList<>();
            while(rs.next()){
                Job job = new Job(
                        rs.getInt("job_id"),
                        rs.getString("job_title"),
                        rs.getString("job_type"),
                        rs.getString("job_description"),
                        rs.getInt("company_id"),
                        rs.getString("job_location"),
                        rs.getString("company_name")
                );
                testJobs.add(job);
            }
            logger.info("Finishing DAL function viewJobs with result: /n" + testJobs);
            return testJobs;
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Error with DAL function viewJobs with error: " + exception.getMessage());
            return null;
        }
    };

    @Override
    public List<Applicant> viewApplicants(int jobId){
        logger.info("Starting DAL function viewApplicants with data: " + jobId);
        try (Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from applicant_table Left Join applied_jobs on job_id=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();
            List<Applicant> applicants = new ArrayList<>();
            while (rs.next()){
                Applicant applicant = new Applicant(
                        rs.getInt("applicant_id"),
                        rs.getString("applicant_first_name"),
                        rs.getString("applicant_last_name"),
                        rs.getString("applicant_phone_number"),
                        rs.getString("applicant_email"),
                        rs.getString("applicant_work_history"),
                        rs.getString("applicant_references"),
                        rs.getString("applicant_education"),
                        rs.getString("applicant_desired_job")
                );
                applicants.add(applicant);
            }
            if (applicants.size() == 0){
                logger.warn("Error with DAL function viewApplicants with error: Applicants list size = 0");
                throw new NoJobFound("There are no applicants for the job ID provided!");
            } else {
                logger.info("Finishing DAL function viewApplicants with result: " + applicants);
                return applicants;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Error with DAL function viewApplicants with error: " + exception.getMessage());
            return null;
        }
    };

    @Override
    public int deleteJobs(int jobId){
        logger.info("Begining DAL function deleteJobs with data: " + jobId);
        try (Connection connection = DatabaseConnection.createConnection()){
            String sql = "delete from job_table where job_id=?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, jobId);
            int result = ps.executeUpdate();
            logger.info("Finishing DAL function deleteJobs with result: " + result);
            return result;
        } catch (SQLException exception) {
            exception.printStackTrace();
            logger.error("Error with DAL function deleteJobs with error: " + exception.getMessage());
            return 0;
        }
    };
}
