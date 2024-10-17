/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Nguyen Cuong
 */
public class loginDAO extends DBContext {
    public account getByUsernamePassword(String username, String password) {
        String query = "SELECT * FROM Account WHERE UserName = ? AND Password = ?";
        try (Connection conn = connection; PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account account = new account();
                    account.setAccountID(rs.getInt("AccountID"));
                    account.setEmail(rs.getString("Email"));
                    account.setUsername(rs.getString("UserName"));
                    account.setPassword(rs.getString("Password")); 
                    account.setFullname(rs.getString("FullName"));
                    account.setRoleid(rs.getInt("RoleID"));
                    return account;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
