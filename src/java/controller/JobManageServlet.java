/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.JobDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import model.Job;

/**
 *
 * @author ntanh
 */
public class JobManageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Lấy accountid từ session
        HttpSession session = request.getSession();
        Integer accountid = (Integer) session.getAttribute("AccountID");

        if (accountid == null) {
            // Nếu accountid chưa tồn tại trong session, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        // Tạo đối tượng JobDAO để thao tác với CSDL
        JobDAO dao = new JobDAO();
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addJob(request, dao, accountid);
                    request.getSession().setAttribute("successMessage", "Công việc đã được đăng ký thành công.");
                    break;
                case "edit":
                    editJob(request, dao, accountid);
                    request.getSession().setAttribute("successMessage", "Công việc đã được chỉnh sửa thành công.");
                    break;
                case "delete":
                    deleteJob(request, dao, accountid);
                    request.getSession().setAttribute("successMessage", "Công việc đã được xóa thành công.");
                    break;
                case "list":
                    List<Job> list = dao.showAllJobsByAccountID(accountid);
                    request.getSession().setAttribute("joblist", list);
                    request.getRequestDispatcher("jobManagement.jsp").forward(request, response);
                    return;
                case "searchByTitle":
                    String title = request.getParameter("title");
                    List<Job> jobsByTitle = dao.searchJobsByTitle(title); 
                    request.getSession().setAttribute("joblist", jobsByTitle); 
                    request.getRequestDispatcher("jobManagement.jsp").forward(request, response); 
                    return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Đã xảy ra lỗi: " + e.getMessage());
        }

        // Sau khi thực hiện các thao tác, chuyển hướng về trang danh sách công việc
        response.sendRedirect("JobManageServlet?action=list");
    }

    // Thêm Job mới
    private void addJob(HttpServletRequest request, JobDAO dao, int accountid) {
        String jobtitle = request.getParameter("jobtitle");
        int salary = Integer.parseInt(request.getParameter("salary"));
        String company = request.getParameter("company");
        String description = request.getParameter("description");
        String location = request.getParameter("location");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date date = new Date(); // Ngày hiện tại

        // Tạo đối tượng Job và thiết lập các giá trị
        Job newJob = new Job();
        newJob.setJobtitle(jobtitle);
        newJob.setSalary(salary);
        newJob.setCompany(company);
        newJob.setDescription(description);
        newJob.setLocation(location);
        newJob.setAccountid(accountid); // Lấy accountid từ session
        newJob.setQuantity(quantity);
        newJob.setDate(new java.sql.Date(date.getTime()));

        // Thêm Job vào CSDL
        dao.addJob(newJob);
    }

    // Chỉnh sửa Job
    private void editJob(HttpServletRequest request, JobDAO dao, int accountid) {
        // Lấy jobid từ request URI

        String jobidStr = request.getParameter("jobid");
        int jobid = Integer.parseInt(jobidStr);

        // Kiểm tra nếu jobid không tồn tại
        // Lấy thông tin từ form
        String jobtitle = request.getParameter("jobtitle");
        int salary = Integer.parseInt(request.getParameter("salary"));
        String company = request.getParameter("company");
        String description = request.getParameter("description");
        String location = request.getParameter("location");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date date = new Date(); // Ngày hiện tại

        // Tạo đối tượng Job đã chỉnh sửa và thiết lập các giá trị mới
        Job updatedJob = new Job();
        updatedJob.setJobid(jobid); // Thiết lập jobid
        updatedJob.setJobtitle(jobtitle);
        updatedJob.setSalary(salary);
        updatedJob.setCompany(company);
        updatedJob.setDescription(description);
        updatedJob.setLocation(location);
        updatedJob.setAccountid(accountid); // Lấy accountid từ session
        updatedJob.setQuantity(quantity);
        updatedJob.setDate(new java.sql.Date(date.getTime())); // Thiết lập ngày hiện tại

        // Cập nhật Job trong CSDL
        dao.updateJob(updatedJob);
    }

    // Xóa Job
    private void deleteJob(HttpServletRequest request, JobDAO dao, int accountid) {
        int jobid = Integer.parseInt(request.getParameter("jobid"));
        dao.deleteJob(jobid);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
