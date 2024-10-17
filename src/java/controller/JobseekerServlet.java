/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CvDAO;
import dal.JobDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.CV;
import model.Job;

/**
 *
 * @author ntanh
 */
public class JobseekerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Integer accountid = (Integer) session.getAttribute("AccountID");
        String action = request.getParameter("action");
        // Default action to view all jobs
        if (accountid == null) {
            // Nếu accountid chưa tồn tại trong session, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }
        JobDAO DAO = new JobDAO();
        if ("viewJobDetails".equals(action)) {
            int jobId = Integer.parseInt(request.getParameter("jobId"));

            Job job = DAO.getJobByJobID(jobId);
            request.setAttribute("job", job);

            // Lấy danh sách CV của người dùng hiện tại và đặt vào request
            CvDAO cv = new CvDAO();
            List<CV> cvList = null; // Khởi tạo danh sách CV để tránh lỗi null
            try {
                cvList = cv.showAllCvByAccountId(accountid);
            } catch (Exception ex) {
                response.sendRedirect("error.jsp");
                return;
            }
            request.setAttribute("cvList", cvList);

            request.getRequestDispatcher("jobDetail.jsp").forward(request, response);
        } else if ("searchByTitle".equals(action)) {

            String title = request.getParameter("title");
            List<Job> searchResults = DAO.searchJobsByTitle(title); // Method to search jobs by title
            request.setAttribute("joblist", searchResults);
            request.getRequestDispatcher("jobseekerhome.jsp").forward(request, response);
        } else {
            List<Job> list = DAO.showAllJobs();
            request.getSession().setAttribute("joblist", list);
            request.getRequestDispatcher("jobseekerhome.jsp").forward(request, response);
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
