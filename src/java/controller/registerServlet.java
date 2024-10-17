/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.accountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.account;

/**
 *
 * @author Nguyen Cuong
 */
public class registerServlet extends HttpServlet {

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

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String roleIdStr = request.getParameter("role");

        int roleID = Integer.parseInt(roleIdStr);
        accountDAO DAO = new accountDAO();

        // Kiểm tra xem email đã tồn tại chưa
        if (DAO.checkEmailExists(email)) {
            request.setAttribute("message", "Email đã tồn tại! Vui lòng sử dụng email khác.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else if (!email.endsWith("@gmail.com")) {
            request.setAttribute("message", "Email phải thuộc miền @gmail.com.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } // Kiểm tra mật khẩu có chứa ký tự đặc biệt
        else if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            request.setAttribute("message", "Mật khẩu phải chứa ít nhất một ký tự đặc biệt.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } // Kiểm tra độ dài mật khẩu
        else if (password.length() < 8) {
            request.setAttribute("message", "Mật khẩu phải có ít nhất 8 ký tự.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // Tạo tài khoản mới
            account newAccount = new account(username, password, email, fullname, roleID);

            // Thực hiện đăng ký tài khoản
            account registeredAccount = DAO.registerAccount(newAccount);

            // Kiểm tra kết quả đăng ký
            if (registeredAccount != null) {
                request.setAttribute("message", "Đăng ký thành công!");
                request.setAttribute("success", true); // Thông báo thành công
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Đăng ký không thành công!");
                request.setAttribute("success", false); // Thông báo lỗi
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

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
