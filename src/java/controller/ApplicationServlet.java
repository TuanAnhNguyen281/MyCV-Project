/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.applicationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import model.Application;

/**
 *
 * @author ntanh
 */
public class ApplicationServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Integer accountid = (Integer) session.getAttribute("AccountID");

        if (accountid == null) {
            // Nếu accountid chưa tồn tại trong session, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }
        String action = request.getParameter("action");

        if ("apply".equals(action)) {
            String status = "Đang xử lý...";
            String cvidStr = request.getParameter("cvid");
            String jobidStr = request.getParameter("jobid");

            if (cvidStr != null && jobidStr != null) {
                int cvid = Integer.parseInt(cvidStr);
                int jobid = Integer.parseInt(jobidStr);
                Date date = new Date();
                Application application = new Application(status, cvid, jobid, date);

                applicationDAO applicationDAO = new applicationDAO();
                applicationDAO.addApplication(application);
                session.setAttribute("message", "Ứng tuyển thành công!");
                response.sendRedirect("JobseekerServlet");
            } else {
                session.setAttribute("message", "Có lỗi xảy ra! Vui lòng kiểm tra lại.");
                response.sendRedirect("JobseekerServlet");
            }
        } else if ("updateStatus".equals(action)) {
            int applicationId = Integer.parseInt(request.getParameter("applicationId"));
            String newStatus = request.getParameter("newStatus");
            applicationDAO applicationDAO = new applicationDAO();
            applicationDAO.updateApplicationStatus(applicationId, newStatus);
            response.sendRedirect("ApplicationServlet?action=employerview");
        } else if ("delete".equals(action)) {
            int applicationId = Integer.parseInt(request.getParameter("applicationId"));
            applicationDAO applicationDAO = new applicationDAO();
            try {
                applicationDAO.deleteApplication(applicationId);
                session.setAttribute("message", "Xóa ứng tuyển thành công");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("message", "Có lỗi xảy ra trong quá trình xóa ứng tuyển!");
            }
            response.sendRedirect("ApplicationServlet?action=jobseekerview");
        } else if ("jobseekerview".equals(action)) {
            applicationDAO applicationDAO = new applicationDAO();
            List<Application> applications = applicationDAO.getApplicationsByAccountId(accountid);
            session.setAttribute("applicationList", applications);
            request.getRequestDispatcher("applicationlist.jsp").forward(request, response);
        } else if ("employerview".equals(action)) {
            applicationDAO applicationDAO = new applicationDAO();
            List<Application> applications = applicationDAO.getApplicationsByEAccountId(accountid);
            session.setAttribute("applicationList", applications);
            request.getRequestDispatcher("replyapplication.jsp").forward(request, response);
        }
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
