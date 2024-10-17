package dal;

import model.Job;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class JobDAO extends DBContext {

    // Add Job
    public Job addJob(Job newJob) {
        String getMaxJidSql = "SELECT MAX(JobID) FROM Job";
        String insertSql = "INSERT INTO Job (JobID, JobTitle, Salary, Company, Description, Location, AccountID, quantity, Date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Tìm giá trị JobID lớn nhất hiện tại
            int newJid = 1;
            try (PreparedStatement ps = connection.prepareStatement(getMaxJidSql); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    newJid = rs.getInt(1) + 1;
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
                ps.setInt(1, newJid);
                ps.setString(2, newJob.getJobtitle());
                ps.setInt(3, newJob.getSalary());
                ps.setString(4, newJob.getCompany());
                ps.setString(5, newJob.getDescription());
                ps.setString(6, newJob.getLocation());
                ps.setInt(7, newJob.getAccountid());
                ps.setInt(8, newJob.getQuantity());
                ps.setDate(9, new Date(newJob.getDate().getTime())); // Chuyển đổi java.util.Date thành java.sql.Date

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    newJob.setJobid(newJid); // Đặt jobid mới vào đối tượng Job
                    return newJob; // Trả về đối tượng Job đã thêm
                } else {
                    System.out.println("Failed to add job.");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update Job
    public void updateJob(Job updatedJob) {
        String updateSql = "UPDATE Job SET JobTitle = ?, Salary = ?, Company = ?, Description = ?, Location = ?, "
                + "AccountID = ?, quantity = ?, Date = ? WHERE JobID = ?";

        try (PreparedStatement ps = connection.prepareStatement(updateSql)) {
            ps.setString(1, updatedJob.getJobtitle());
            ps.setInt(2, updatedJob.getSalary());
            ps.setString(3, updatedJob.getCompany());
            ps.setString(4, updatedJob.getDescription());
            ps.setString(5, updatedJob.getLocation());
            ps.setInt(6, updatedJob.getAccountid());
            ps.setInt(7, updatedJob.getQuantity());
            ps.setDate(8, new Date(updatedJob.getDate().getTime()));
            ps.setInt(9, updatedJob.getJobid());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Delete Job
    public void deleteJob(int jobid) {
        String deleteSql = "DELETE FROM Job WHERE JobID = ?";

        try (PreparedStatement ps = connection.prepareStatement(deleteSql)) {
            ps.setInt(1, jobid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Job> showAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String selectSql = "SELECT * FROM Job";

        try (PreparedStatement ps = connection.prepareStatement(selectSql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int jobid = rs.getInt("JobID");
                String jobtitle = rs.getString("JobTitle");
                int salary = rs.getInt("Salary");
                String company = rs.getString("Company");
                String descriptions = rs.getString("Description");
                String location = rs.getString("Location");
                int AccountId = rs.getInt("AccountID");
                int quantity = rs.getInt("quantity");
                
                Date date = rs.getDate("Date");
                Job j = new Job(jobid, jobtitle, salary, company, descriptions, location, AccountId, quantity, date);
                jobs.add(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobs;
    }

    // Show jobs by account ID
    public List<Job> showAllJobsByAccountID(int accountid) {
        List<Job> jobs = new ArrayList<>();
        String selectSql = "SELECT * FROM Job WHERE AccountID = ?";

        try (PreparedStatement ps = connection.prepareStatement(selectSql)) {
            ps.setInt(1, accountid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Job j = new Job();
                    j.setJobid(rs.getInt("JobID"));
                    j.setJobtitle(rs.getString("JobTitle"));
                    j.setSalary(rs.getInt("Salary"));
                    j.setCompany(rs.getString("Company"));
                    j.setDescription(rs.getString("Description"));
                    j.setLocation(rs.getString("Location"));
                    j.setAccountid(rs.getInt("AccountID"));
                    j.setQuantity(rs.getInt("quantity"));
                    j.setDate(rs.getDate("Date"));

                    jobs.add(j);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobs;
    }

    // Get job by JobID
    public Job getJobByJobID(int jobID) {
        String selectSql = "SELECT * FROM Job WHERE JobID = ?";
        Job job = null;

        try (PreparedStatement ps = connection.prepareStatement(selectSql)) {
            ps.setInt(1, jobID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    job = new Job();
                    job.setJobid(rs.getInt("JobID"));
                    job.setJobtitle(rs.getString("JobTitle"));
                    job.setSalary(rs.getInt("Salary"));
                    job.setCompany(rs.getString("Company"));
                    job.setDescription(rs.getString("Description"));
                    job.setLocation(rs.getString("Location"));
                    job.setAccountid(rs.getInt("AccountID"));
                    job.setQuantity(rs.getInt("quantity"));
                    job.setDate(rs.getDate("Date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return job;
    }
    
    public List<Job> searchJobsByTitle(String title) {
    List<Job> jobs = new ArrayList<>();
    String searchSql = "SELECT * FROM Job WHERE JobTitle LIKE ?";

    try (PreparedStatement ps = connection.prepareStatement(searchSql)) {
        ps.setString(1, "%" + title + "%"); 

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Job job = new Job();
                job.setJobid(rs.getInt("JobID"));
                job.setJobtitle(rs.getString("JobTitle"));
                job.setSalary(rs.getInt("Salary"));
                job.setCompany(rs.getString("Company"));
                job.setDescription(rs.getString("Description"));
                job.setLocation(rs.getString("Location"));
                job.setAccountid(rs.getInt("AccountID"));
                job.setQuantity(rs.getInt("Quantity"));
                job.setDate(rs.getDate("Date"));

                jobs.add(job);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return jobs; 
}

   


    public static void main(String[] args) {
        JobDAO dao = new JobDAO();

//        Job newj = new Job("Sửa điện thoại", 3000000, "Abc mobile", "Thợ phụ", "Hà nội", 1, 2, new java.util.Date());
//
//        dao.addJob(newj);
//        if (newj != null) {
//            System.out.println("Job added successfully with JobID = " + newj.getJobid());
//        } else {
//            System.out.println("Failed to add Job.");
//        }
//        List<Job> jobs = dao.showAllJobsByAccountID(1);
//
//        // Kiểm tra kết quả
//        if (jobs != null && !jobs.isEmpty()) {
//            System.out.println("Danh sách công việc cho accountId = 1:");
//            for (Job j : jobs) {
//                System.out.println("Job ID: " + j.getJobid());
//                System.out.println("Job Title: " + j.getJobtitle());
//                System.out.println("Salary: " + j.getSalary());
//                System.out.println("Company: " + j.getCompany());
//                System.out.println("Description: " + j.getDescription());
//                System.out.println("Location: " + j.getLocation());
//                System.out.println("Account ID: " + j.getAccountid());
//                System.out.println("Quantity: " + j.getQuantity());
//                System.out.println("Date: " + j.getDate());
//                System.out.println("-------------------------------------");
//            }
//        } else {
//            System.out.println("Không có công việc nào cho accountId = 1.");
//        }
//        Job updatedJob = new Job();
//        updatedJob.setJobid(1); // Set the ID of the job you want to update
//        updatedJob.setJobtitle("Updated Job Title");
//        updatedJob.setSalary(60000);
//        updatedJob.setCompany("Updated Company");
//        updatedJob.setDescription("Updated Description");
//        updatedJob.setLocation("Updated Location");
//        updatedJob.setAccountid(1); // Assuming account ID is 1
//        updatedJob.setQuantity(5);
//        updatedJob.setDate(new java.sql.Date(System.currentTimeMillis())); // Set to current date
//
//        // Call updateJob method
//        dao.updateJob(updatedJob);
//        System.out.println("Job updated successfully!");

List<Job> jobList = dao.showAllJobs();
        
        // Print job details
        for (Job job : jobList) {
            System.out.println("Job ID: " + job.getJobid());
            System.out.println("Job Title: " + job.getJobtitle());
            System.out.println("Company: " + job.getCompany());
            System.out.println("Salary: " + job.getSalary());
            System.out.println("Description: " + job.getDescription());
            System.out.println("Location: " + job.getLocation());
            System.out.println("Quantity: " + job.getQuantity());
            System.out.println("Account ID: " + job.getAccountid());
            System.out.println("Date: " + job.getDate());
            System.out.println("--------------------------------");
        }
    }
}
