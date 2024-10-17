<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Job" %>
<%@ page import="dal.JobDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chỉnh sửa công việc</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="StyleIndex.css">
        <style>
            .navbar-text {
                display: inline-block;
                padding-bottom: .7rem;
            }
            .dropdown-menu-right {
                right: 0;
                left: auto;
            }
        </style>
    </head>
    <body>
        <header>
            <div id="signin" class="container-fluid bg-light">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="navbar-brand" href="Recruiter.jsp">
                        <img class="logo" src="MyCV (1).png" alt="alt" width="150"/>
                    </a>
                    <div class="ml-auto">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                Xin chào,
                                <button id="btnFullName" class="btn btn-link navbar-text">
                                    <%= session.getAttribute("fullname") %>
                                </button>
                                <div id="logoutDropdown" class="dropdown-menu dropdown-menu-right" aria-labelledby="btnFullName">
                                    <a class="dropdown-item" href="logoutServlet">Đăng xuất</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>

        <%
            // Initialize JobDAO and get the jobId parameter from the request
            JobDAO jobDao = new JobDAO();

            // Retrieve account ID from session
            HttpSession userSession = request.getSession();
            Integer accountid = (Integer) userSession.getAttribute("AccountID");
            
            String jobIdStr = request.getParameter("jobid");
            int jobId = Integer.parseInt(jobIdStr);
            
            if (accountid == null) {
                // Redirect to login page if AccountID is not found in session
                response.sendRedirect("login.jsp");
                return;
            }

            Job job = null;
  
                job = jobDao.getJobByJobID(jobId); // Fetch job details by ID
                if (job == null) {
            request.setAttribute("errorMessage", "Công việc không tồn tại hoặc đã bị xóa.");
            request.getRequestDispatcher("updateJob.jsp").forward(request, response);
            return;
                }
        %>

        <section id="addjob" class="py-4">
            <div class="container">
                <h2 class="text-center">Chuyển sửa thông tin Tuyển Dụng</h2>
                <form action="JobManageServlet" method="POST">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="jobid" value="<%= job.getJobid() %>">
                    <div class="form-group">
                        <label for="companyName">Tên công ty</label>
                        <input type="text" class="form-control" id="company" name="company" value="<%= job.getCompany() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="jobTitle">Tên công việc</label>
                        <input type="text" class="form-control" id="jobtitle" name="jobtitle" value="<%= job.getJobtitle() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="jobDescription">Mô tả công việc</label>
                        <textarea class="form-control" id="description" name="description" rows="4" required><%= job.getDescription() %></textarea>
                    </div>
                    <div class="form-group">
                        <label for="jobLocation">Chọn lại Địa điểm</label>
                        <select class="form-control" id="location" name="location" required>
                            <option value="">Chọn địa điểm</option>
                            <option value="Hà Nội">Hà Nội</option>
                            <option value="TP.HCM">TP.HCM</option>
                            <option value="Đà Nẵng">Đà Nẵng</option>
                            <option value="Hải Phòng">Hải Phòng</option>
                            <option value="Cần Thơ">Cần Thơ</option>
                            <option value="An Giang">An Giang</option>
                            <option value="Bà Rịa - Vũng Tàu">Bà Rịa - Vũng Tàu</option>
                            <option value="Bắc Giang">Bắc Giang</option>
                            <option value="Bắc Kạn">Bắc Kạn</option>
                            <option value="Bạc Liêu">Bạc Liêu</option>
                            <option value="Bến Tre">Bến Tre</option>
                            <option value="Bình Định">Bình Định</option>
                            <option value="Bình Dương">Bình Dương</option>
                            <option value="Bình Phước">Bình Phước</option>
                            <option value="Bình Thuận">Bình Thuận</option>
                            <option value="Cà Mau">Cà Mau</option>
                            <option value="Đắk Lắk">Đắk Lắk</option>
                            <option value="Đắk Nông">Đắk Nông</option>
                            <option value="Điện Biên">Điện Biên</option>
                            <option value="Đồng Nai">Đồng Nai</option>
                            <option value="Đồng Tháp">Đồng Tháp</option>
                            <option value="Gia Lai">Gia Lai</option>
                            <option value="Hà Giang">Hà Giang</option>
                            <option value="Hà Nam">Hà Nam</option>
                            <option value="Hà Tĩnh">Hà Tĩnh</option>
                            <option value="Hải Dương">Hải Dương</option>
                            <option value="Hòa Bình">Hòa Bình</option>
                            <option value="Hưng Yên">Hưng Yên</option>
                            <option value="Khánh Hòa">Khánh Hòa</option>
                            <option value="Kiên Giang">Kiên Giang</option>
                            <option value="Kon Tum">Kon Tum</option>
                            <option value="Lai Châu">Lai Châu</option>
                            <option value="Lạng Sơn">Lạng Sơn</option>
                            <option value="Lâm Đồng">Lâm Đồng</option>
                            <option value="Long An">Long An</option>
                            <option value="Nam Định">Nam Định</option>
                            <option value="Ninh Bình">Ninh Bình</option>
                            <option value="Ninh Thuận">Ninh Thuận</option>
                            <option value="Phú Thọ">Phú Thọ</option>
                            <option value="Phú Yên">Phú Yên</option>
                            <option value="Quảng Bình">Quảng Bình</option>
                            <option value="Quảng Nam">Quảng Nam</option>
                            <option value="Quảng Ngãi">Quảng Ngãi</option>
                            <option value="Quảng Ninh">Quảng Ninh</option>
                            <option value="Quảng Trị">Quảng Trị</option>
                            <option value="Sóc Trăng">Sóc Trăng</option>
                            <option value="Tây Ninh">Tây Ninh</option>
                            <option value="Thái Bình">Thái Bình</option>
                            <option value="Thái Nguyên">Thái Nguyên</option>
                            <option value="Thanh Hóa">Thanh Hóa</option>
                            <option value="Thừa Thiên Huế">Thừa Thiên Huế</option>
                            <option value="Tiền Giang">Tiền Giang</option>
                            <option value="Trà Vinh">Trà Vinh</option>
                            <option value="Vĩnh Long">Vĩnh Long</option>
                            <option value="Vĩnh Phúc">Vĩnh Phúc</option>
                            <option value="Yên Bái">Yên Bái</option>                 
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="quantity">Số người cần tìm:</label>
                        <input type="number" class="form-control" id="quantity" name="quantity" value="<%= job.getQuantity() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="salary">Lương</label>
                        <input type="number" class="form-control" id="salary" name="salary" value="<%= job.getSalary() %>" required>
                    </div>
                    <input type="submit" value="Cập nhật" name="Submit" class="btn btn-primary" />
                </form>
            </div>
        </section>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var btnFullName = document.getElementById("btnFullName");
                var logoutDropdown = document.getElementById("logoutDropdown");

                btnFullName.addEventListener("click", function () {
                    logoutDropdown.classList.toggle("show");
                });

                document.addEventListener("click", function (event) {
                    if (!logoutDropdown.contains(event.target) && !btnFullName.contains(event.target)) {
                        logoutDropdown.classList.remove("show");
                    }
                });
            });
        </script>
    </body>
</html>
