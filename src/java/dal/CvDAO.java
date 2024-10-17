/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.CV;

public class CvDAO extends DBContext {

    public void addCv(CV newCv) throws SQLException {
        String getMaxCvidSql = "SELECT MAX(CVID) FROM CV";
        String insertSql = "INSERT INTO CV (CVID, CVPath, AccountID, Date) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            int newCvid = 1;
            try (PreparedStatement psMax = conn.prepareStatement(getMaxCvidSql); ResultSet rs = psMax.executeQuery()) {
                if (rs.next()) {
                    newCvid = rs.getInt(1) + 1;
                }
            }

            try (PreparedStatement psInsert = conn.prepareStatement(insertSql)) {
                psInsert.setInt(1, newCvid);
                psInsert.setString(2, newCv.getCv());
                psInsert.setInt(3, newCv.getAccountid());
                psInsert.setDate(4, new java.sql.Date(newCv.getDate().getTime()));

                psInsert.executeUpdate();
            }
        }
    }

    public List<CV> showAllCvByAccountId(int accountId) throws SQLException {
        List<CV> cvList = new ArrayList<>();
        String sql = "SELECT * FROM CV WHERE AccountID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CV cv = new CV(
                            rs.getInt("CVID"),
                            rs.getString("CVPath"),
                            rs.getInt("AccountID"),
                            rs.getDate("Date")
                    );
                    cvList.add(cv);
                }
            }
        }
        return cvList;
    }

    public CV getCvById(int cvid) throws SQLException {
        String sql = "SELECT * FROM CV WHERE CVID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cvid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CV(
                    rs.getInt("CVID"),
                    rs.getString("CVPath"),
                    rs.getInt("AccountID"),
                    rs.getDate("Date")
                    );
                }
            }
        }
        return null;
    }

    public List<CV> getCVsByAccountId(int accountId) throws SQLException {
    List<CV> cvList = new ArrayList<>();
    String sql = "SELECT * FROM CV WHERE AccountID = ?";

    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, accountId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CV cv = new CV(
                        rs.getInt("CVID"),
                        rs.getString("CVPath"),
                        rs.getInt("AccountID"),
                        rs.getDate("Date")
                );
                cvList.add(cv);
            }
        }
    }
    return cvList;
}

    
    public void deleteCv(int cvid) throws SQLException {
        String sql = "DELETE FROM CV WHERE CVID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cvid);
            ps.executeUpdate();
        }
    }
    public static void main(String[] args) {
        CvDAO cvDao = new CvDAO();

        // Giả sử bạn có một AccountID để test
        int accountIdTest = 4;

        try {
            // Gọi phương thức showAllCvByAccountId để lấy danh sách CV của accountIdTest
            List<CV> cvList = cvDao.showAllCvByAccountId(accountIdTest);

            // Kiểm tra xem danh sách CV có rỗng không
            if (cvList.isEmpty()) {
                System.out.println("Không có CV nào được tìm thấy cho AccountID: " + accountIdTest);
            } else {
                // Duyệt qua và in ra các thông tin của từng CV
                for (CV cv : cvList) {
                    System.out.println("CVID: " + cv.getCvid());
                    System.out.println("CVPath: " + cv.getCv());
                    System.out.println("AccountID: " + cv.getAccountid());
                    System.out.println("Date: " + cv.getDate());
                    System.out.println("------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi truy xuất CV từ database: " + e.getMessage());
        }
    }
}
