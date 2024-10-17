/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class accountDAO extends DBContext {

    public account registerAccount(account account) {
        String getMaxAidSql = "SELECT MAX(AccountID) FROM Account";
        String insertSql = "INSERT INTO Account (AccountID, Email, UserName, Password, FullName, RoleID) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // Tìm giá trị aid lớn nhất hiện tại
            int newAid = 1;
            try (PreparedStatement ps = connection.prepareStatement(getMaxAidSql); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    newAid = rs.getInt(1) + 1;
                }
            }

            // Chèn bản ghi mới với aid mới
            try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
                ps.setInt(1, newAid);
                ps.setString(2, account.getEmail());
                ps.setString(3, account.getUsername());
                ps.setString(4, account.getPassword());
                ps.setString(5, account.getFullname());
                ps.setInt(6, account.getRoleid());
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    account.setAccountID(newAid);
                    return account;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Account WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public account getAccountById(int accountID) {
        String sql = "SELECT AccountID, Email, UserName, Password, FullName, RoleID FROM Account WHERE AccountID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("AccountID");
                    String email = rs.getString("Email");
                    String username = rs.getString("UserName");
                    String password = rs.getString("Password");
                    String fullname = rs.getString("FullName");
                    int roleid = rs.getInt("RoleID");

                    account acc = new account(username, password, email, fullname, roleid);
                    acc.setAccountID(id);
                    return acc;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
//        account newAccount = new account("cuongdepzai", "Cuong123", "Cuong@gmail.com", "Cường", 1);
//
//        // Gọi phương thức registerAccount để thêm tài khoản vào CSDL
//        accountDAO accountDao = new accountDAO();
//        account registeredAccount = accountDao.registerAccount(newAccount);
//
//        // Kiểm tra kết quả
//        if (registeredAccount != null) {
//            System.out.println("Đăng ký thành công. Account ID: " + registeredAccount.getAccountID());
//        } else {
//            System.out.println("Đăng ký thất bại.");
//        }
        accountDAO accountDao = new accountDAO();
        int searchId = 1; // replace with the desired AccountID to search
        account acc = accountDao.getAccountById(searchId);

        if (acc != null) {
            System.out.println("Account found: " + acc.getFullname());
        } else {
            System.out.println("Account not found.");
        }
    }

}
