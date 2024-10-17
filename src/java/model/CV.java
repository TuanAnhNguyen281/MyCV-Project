/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;


public class CV {
    private int cvid;
    private String cv;
    private int accountid;
    private Date date;

    public CV() {
    }

    public CV(String cv, int accountid, Date date) {
        this.cv = cv;
        this.accountid = accountid;
        this.date = date;
    }

    public CV(int cvid, String cv, int accountid, Date date) {
        this.cvid = cvid;
        this.cv = cv;
        this.accountid = accountid;
        this.date = date;
    }

    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
