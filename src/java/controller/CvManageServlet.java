/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CvDAO;
import dal.JobDAO;
import dal.accountDAO;
import jakarta.servlet.RequestDispatcher;
import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
public class CvManageServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Integer accountid = (Integer) session.getAttribute("AccountID");
        String action = request.getParameter("action");

        if (accountid == null) {
            // Nếu accountid chưa tồn tại trong session, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        CvDAO cvDao = new CvDAO();

        if ("add".equals(action)) {
            Part file = request.getPart("cvPath");
            String imagefile = file.getSubmittedFileName();

            // Kiểm tra định dạng file hợp lệ (img, png, jpg)
            if (!imagefile.endsWith(".img") && !imagefile.endsWith(".png") && !imagefile.endsWith(".jpg")) {
                // Định dạng file không hợp lệ, gửi thông báo lỗi
                session.setAttribute("message", "Định dạng file không hợp lệ! Vui lòng chọn file .img, .png hoặc .jpg");
                response.sendRedirect("cvManagement.jsp");
                return;
            }

            System.out.println("Selected image file name: " + imagefile);
            String uploadPath = "D:/prj project/MyCV/web/save/" + imagefile;
            System.out.println("UploadPath: " + uploadPath);

            try (FileOutputStream fos = new FileOutputStream(uploadPath); InputStream is = file.getInputStream()) {

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);

                // Thêm CV mới vào cơ sở dữ liệu
                CV newCv = new CV();
                newCv.setCv(imagefile);
                newCv.setAccountid(accountid);
                newCv.setDate(new java.util.Date());

                cvDao.addCv(newCv);
                session.setAttribute("message", "Thêm CV thành công!");
                response.sendRedirect("CvManageServlet?action=list");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("message", "Có lỗi xảy ra trong quá trình thêm CV!");
                response.sendRedirect("cvManagement.jsp");
            }
        } else if ("delete".equals(action)) {
            // Xử lý xóa CV
            int cvid = Integer.parseInt(request.getParameter("cvid"));
            try {
                cvDao.deleteCv(cvid);
                session.setAttribute("CVmessage", "Xóa CV thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("CVmessage", "Có lỗi xảy ra trong quá trình xóa CV!");
            }
            response.sendRedirect("CvManageServlet?action=list");
        } else if ("list".equals(action)) {
            // Hiển thị tất cả CV của người dùng
            try {
                List<CV> cvList = cvDao.showAllCvByAccountId(accountid);
                session.setAttribute("cvlist", cvList);
                request.getRequestDispatcher("cvManagement.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("message", "Có lỗi xảy ra trong quá trình tải danh sách CV!");
                response.sendRedirect("cvManagement.jsp");
            }
        } else if ("displaycv".equals(action)) {
            int cvid = Integer.parseInt(request.getParameter("cvid"));
            CV cv = null;

            try {
                cv = cvDao.getCvById(cvid);
                if (cv == null) {
                    session.setAttribute("message", "CV không tồn tại!");
                    response.sendRedirect("error.jsp"); // Redirect to an error page or handle accordingly
                    return;
                }
                // Set the CV object as a request attribute
                request.setAttribute("cv", cv);
                // Forward to viewcv.jsp instead of redirecting
                RequestDispatcher dispatcher = request.getRequestDispatcher("viewcv.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("message", "Có lỗi xảy ra trong quá trình hiển thị CV!");
                response.sendRedirect("error.jsp"); // Redirect to an error page or handle accordingly
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
