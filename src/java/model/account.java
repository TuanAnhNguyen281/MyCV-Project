/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nguyen Cuong
 */
public class account {
    private int accountID;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private int roleid;

    public account() {
    }

    
    public account(String username, String password, String email, String fullname, int roleid) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.roleid = roleid;
    }

    
    
    public account(int accountID, String username, String password, String email, String fullname, int roleid) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.roleid = roleid;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }
    
    
}
