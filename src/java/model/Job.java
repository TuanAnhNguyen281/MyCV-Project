package model;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class Job {

    private int jobid;
    private String jobtitle;
    private int salary;
    private String company;
    private String Description;
    private String Location;
    private int accountid;
    private int quantity;
    private Date date;

    public Job() {
    }

    public Job(String jobtitle, int salary, String company, String Description, String Location, int accountid, int quantity, Date date) {
        this.jobtitle = jobtitle;
        this.salary = salary;
        this.company = company;
        this.Description = Description;
        this.Location = Location;
        this.accountid = accountid;
        this.quantity = quantity;
        this.date = date;
    }

    public Job(int jobid, String jobtitle, int salary, String company, String Description, String Location, int accountid, int quantity, Date date) {
        this.jobid = jobid;
        this.jobtitle = jobtitle;
        this.salary = salary;
        this.company = company;
        this.Description = Description;
        this.Location = Location;
        this.accountid = accountid;
        this.quantity = quantity;
        this.date = date;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
        public String formatSalary() {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN")); // Định dạng tiếng Việt
        return formatter.format(salary) + " VND"; // Trả về lương đã định dạng
    }

}
