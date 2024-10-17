
package model;

import java.util.Date;


public class Application {
    private int applicationid;
    private String status;
    private int cvid;
    private int jobid;
    private Date date;
    private String jobtitle; 
    private String cv;    

    public Application() {
    }

    public Application(String status, int cvid, int jobid, Date date) {
        this.status = status;
        this.cvid = cvid;
        this.jobid = jobid;
        this.date = date;
    }

    public Application(int applicationid, String status, int cvid, int jobid, Date date) {
        this.applicationid = applicationid;
        this.status = status;
        this.cvid = cvid;
        this.jobid = jobid;
        this.date = date;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public int getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(int applicationid) {
        this.applicationid = applicationid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCvid() {
        return cvid;
    }

    public void setCvid(int cvid) {
        this.cvid = cvid;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
