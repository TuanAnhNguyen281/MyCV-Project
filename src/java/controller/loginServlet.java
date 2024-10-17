/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.loginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.account;

/**
 *
 * @author Nguyen Cuong
 */
public class loginServlet extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Lấy thông tin người dùng thông qua username và password
        loginDAO dao = new loginDAO();
        account loggedInAccount = dao.getByUsernamePassword(username, password);

        if (loggedInAccount != null) {
            // Tạo session để lưu thông tin người dùng cho các hoạt động
            HttpSession session = request.getSession();
            session.setAttribute("account", loggedInAccount);
            session.setAttribute("fullname", loggedInAccount.getFullname());

            // Thêm userID và userName vào session
            session.setAttribute("AccountID", loggedInAccount.getAccountID());
            session.setAttribute("UserName", loggedInAccount.getUsername());

            // Phân quyền account
            int role = loggedInAccount.getRoleid();
            switch (role) {
                case 1:
                    response.sendRedirect("employerDashboard.jsp");
                    break;
                case 2:
                    response.sendRedirect("JobseekerServlet");
                    break;
                case 3:
                    response.sendRedirect("admin.jsp");
                    break;
                default:
                    request.setAttribute("message", "Sai tài khoản hoặc mật khẩu");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    break;
            }
        } else {
            //Thông báo lỗi nếu không đăng nhập đướcj
            request.setAttribute("message", "Sai tài khoản hoặc mật khẩu");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
