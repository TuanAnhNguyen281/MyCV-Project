/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class applicationDAO extends DBContext {

    public void addApplication(Application application) {

        String getMaxAppidSql = "SELECT MAX(ApplicationID) FROM Application";
        String sql = "INSERT INTO Application (ApplicationID, Status, CVID, JobID, date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement psGetMaxId = connection.prepareStatement(getMaxAppidSql); ResultSet rs = psGetMaxId.executeQuery()) {
            int newAppId = 1;
            if (rs.next()) {
                newAppId = rs.getInt(1) + 1;
            }

            try (PreparedStatement psInsert = connection.prepareStatement(sql)) {

                psInsert.setInt(1, newAppId);
                psInsert.setString(2, application.getStatus());
                psInsert.setInt(3, application.getCvid());
                psInsert.setInt(4, application.getJobid());
                psInsert.setDate(5, new Date(application.getDate().getTime()));

                psInsert.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateApplicationStatus(int applicationId, String newStatus) {
        String sql = "UPDATE Application SET status = ? WHERE applicationid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, applicationId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Application> getApplicationsByAccountId(int accountId) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT a.ApplicationID, a.Status, a.CVID, a.JobID, a.date, acc.fullname, acc.email, "
                + "j.JobTitle, cv.CVPath "
                + "FROM Application a "
                + "JOIN CV cv ON a.CVID = cv.CVID "
                + "JOIN Account acc ON cv.AccountID = acc.AccountID "
                + "JOIN Job j ON a.JobID = j.JobID "
                + "WHERE acc.AccountID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Application app = new Application();
                    app.setApplicationid(rs.getInt("ApplicationID"));
                    app.setStatus(rs.getString("Status"));
                    app.setCvid(rs.getInt("CVID"));
                    app.setJobid(rs.getInt("JobID"));
                    app.setDate(rs.getDate("date"));

                    // Lấy thêm thông tin JobTitle và CVPath
                    String jobTitle = rs.getString("JobTitle");
                    String cvPath = rs.getString("CVPath");

                    // In ra thông tin bao gồm cả JobTitle và CVPath
                    System.out.println("ApplicationID: " + app.getApplicationid() + ", Status: " + app.getStatus()
                            + ", CVID: " + app.getCvid() + ", JobID: " + app.getJobid()
                            + ", JobTitle: " + jobTitle + ", CVPath: " + cvPath
                            + ", Date: " + app.getDate());

                    // Lưu trữ thông tin JobTitle và CVPath vào đối tượng Application
                    app.setJobtitle(jobTitle); // Đảm bảo setter tên là setJobTitle
                    app.setCv(cvPath);     // Đảm bảo setter tên là setCvPath

                    applications.add(app);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

   public List<Application> getApplicationsByEAccountId(int accountId) {
    List<Application> applications = new ArrayList<>();
    String sql = "SELECT a.ApplicationID, a.Status, a.CVID, a.JobID, a.date, acc.fullname, acc.email, "
               + "j.JobTitle, cv.CVPath "
               + "FROM Application a "
               + "JOIN CV cv ON a.CVID = cv.CVID "
               + "JOIN Job j ON a.JobID = j.JobID "
               + "JOIN Account acc ON j.AccountID = acc.AccountID "
               + "WHERE j.AccountID = ?";  // Sử dụng j.AccountID để tìm kiếm theo accountId của nhà tuyển dụng

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, accountId);  // Thay thế với accountId của nhà tuyển dụng
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Application app = new Application();
                app.setApplicationid(rs.getInt("ApplicationID"));
                app.setStatus(rs.getString("Status"));
                app.setCvid(rs.getInt("CVID"));
                app.setJobid(rs.getInt("JobID"));
                app.setDate(rs.getDate("date"));

                // Lấy thêm thông tin JobTitle và CVPath
                String jobTitle = rs.getString("JobTitle");
                String cvPath = rs.getString("CVPath");

                // In ra thông tin bao gồm cả JobTitle và CVPath
                System.out.println("ApplicationID: " + app.getApplicationid() + ", Status: " + app.getStatus()
                        + ", CVID: " + app.getCvid() + ", JobID: " + app.getJobid()
                        + ", JobTitle: " + jobTitle + ", CVPath: " + cvPath
                        + ", Date: " + app.getDate());

                // Lưu trữ thông tin JobTitle và CVPath vào đối tượng Application
                app.setJobtitle(jobTitle); // Đảm bảo setter tên là setJobTitle
                app.setCv(cvPath);     // Đảm bảo setter tên là setCvPath

                applications.add(app);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return applications;
}


    public void deleteApplication(int applicationId) {
        String sql = "DELETE FROM Application WHERE ApplicationID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, applicationId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted application with ID: " + applicationId);
            } else {
                System.out.println("No application found with ID: " + applicationId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Application newApplication = new Application();
//        newApplication.setJobid(1); // jobid = 1
//        newApplication.setCvid(3);    // cvid = 1
//        newApplication.setStatus("Đang xử lý..."); // status = "Đang xử lý..."
//        newApplication.setDate(new java.util.Date());
//
//        // Tạo DAO và thêm Application mới vào DB
//        applicationDAO appDao = new applicationDAO();
//        appDao.addApplication(newApplication);
//
//        System.out.println("Thêm đơn ứng tuyển thành công!");

        // Tạo DAO và thêm Application mới vào DB
        applicationDAO upDao = new applicationDAO();
//            upDao.updateApplicationStatus(1, "Đồng ý");
//
//            System.out.println("update đơn ứng tuyển thành công!");
//upDao.deleteApplication(1);
int accountId = 1;

        List<Application> applications = upDao.getApplicationsByEAccountId(accountId);

        // Kiểm tra và in ra kết quả
        if (applications.isEmpty()) {
            System.out.println("Không có đơn ứng tuyển nào cho accountId: " + accountId);
        } else {
            for (Application app : applications) {
                System.out.println("ApplicationID: " + app.getApplicationid());
                System.out.println("Status: " + app.getStatus());
                System.out.println("CVID: " + app.getCvid());
                System.out.println("JobID: " + app.getJobid());
                System.out.println("Date: " + app.getDate());
                // Bạn có thể bổ sung thêm cách hiển thị JobTitle và CVPath khi lấy được
            }
        }
    }
}
