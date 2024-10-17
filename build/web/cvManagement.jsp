<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hồ sơ & CV</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
                    <a class="navbar-brand" href="JobseekerServlet?action=viewAllJobs">
                        <img class="logo" src="MyCV (1).png" alt="alt" width="150"/>
                    </a>
                    <div class="ml-auto">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                Xin chào,
                                <button id="btnFullName" class="btn btn-link navbar-text">
                                    <%= session.getAttribute("fullname")%>
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

        <section id="profile" class="py-4">
            <div class="container">
                <h2 class="text-center">Hồ sơ cá nhân</h2>
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Thông tin cá nhân</h5>
                                <p class="card-text">Họ và tên: ${account.fullname}</p>
                                <p class="card-text">Email: ${account.email}</p>
                                <!-- Form upload CV -->
                                <form action="CvManageServlet" method="post" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="cvPath">Tải lên ảnh CV:</label>
                                        <input type="file" class="form-control" id="cvPath" name="cvPath" required>
                                    </div>
                                    <input type="hidden" name="accountid" value="${account.accountID}">
                                    <input type="hidden" name="action" value="add">


                                    <c:if test="${not empty messageError}">
                                        <div id="successAlert" class="alert alert-danger alert-dismissible fade show" role="alert">
                                            ${messageError}
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                    </c:if>

                                    <c:if test="${not empty message}">
                                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                                            ${message}
                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                    </c:if>

                                    <button type="submit" class="btn btn-primary">Thêm CV</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Các CV đã tải lên</h5>

                                <!-- Hiển thị thông báo thành công hoặc lỗi khi xóa CV -->
                                <c:if test="${not empty CVmessage}">
                                    <div id="successAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                                        ${CVmessage}
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:if>

                                <c:if test="${not empty CVmessageError}">
                                    <div  class="alert alert-danger alert-dismissible fade show" role="alert">
                                        ${CVmessageError}
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:if>

                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Đường dẫn CV</th>
                                            <th>Ngày tải lên</th>
                                            <th>Xóa CV</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${cvlist}" var="cv" varStatus="status">
                                            <tr>
                                                <td>${status.index + 1}</td> <!-- Số thứ tự bắt đầu từ 1 -->
                                                <td><a href="CvManageServlet?action=displaycv&cvid=${cv.cvid}" target="_blank">${cv.cv}</a></td>
                                                <td>${cv.date}</td>
                                                <td>
                                                    <a href="CvManageServlet?action=delete&cvid=${cv.cvid}" 
                                                       class="btn btn-sm btn-danger" 
                                                       onclick="return confirm('Bạn có chắc chắn muốn xóa CV này không?');">
                                                        Xóa
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
                                                               const successAlert = document.getElementById("successAlert");
                                                               if (successAlert) {
                                                                   const autoDismiss = setTimeout(function () {
                                                                       successAlert.classList.remove('show'); // Hide alert
                                                                       successAlert.classList.add('fade'); // Add fade class for animation
                                                                   }, 5000); // 5000 milliseconds = 5 seconds

                                                                   const closeButton = successAlert.querySelector('.close');
                                                                   closeButton.addEventListener('click', function () {
                                                                       clearTimeout(autoDismiss);
                                                                       successAlert.classList.remove('show');
                                                                       successAlert.classList.add('fade');
                                                                   });
                                                               }
                                                           });
        </script>
    </body>
</html>
