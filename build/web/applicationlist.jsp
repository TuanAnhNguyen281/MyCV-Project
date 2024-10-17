<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách ứng tuyển</title>
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
                    <a class="navbar-brand" href="JobseekerServlet?action=viewAllJobs">
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

        <section id="application-dashboard" class="py-4">
            <div class="container">
                <h2 class="text-center">Danh Sách Ứng Tuyển</h2>

                <!-- Hiển thị thông báo thành công -->
                <c:if test="${not empty message}">
                    <div id="successAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                        ${message}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Đường dẫn CV</th>
                                    <th>Tên công việc</th>
                                    <th>Trạng thái</th>
                                    <th>Ngày ứng tuyển</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${applicationList}" var="application">
                                    <tr>
                                        <td><a href="CvManageServlet?action=displaycv&cvid=${application.cvid}">${application.cv}</a></td>
                                        <td>${application.jobtitle}</td>  
                                        <td> 
                                            <c:choose>   
                                                <c:when test="${application.status == 'Đang xử lý...'}">
                                                    <span style="color: orange;">${application.status}</span>
                                                </c:when>
                                                <c:when test="${application.status == 'Đồng ý'}">
                                                    <span style="color: green;">${application.status}</span>
                                                </c:when>
                                                <c:when test="${application.status == 'Từ chối'}">
                                                    <span style="color: red;">${application.status}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span>${application.status}</span> <!-- Nếu không khớp với bất kỳ điều kiện nào -->
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${application.date}</td> 
                                        <td>
                                            <a href="ApplicationServlet?action=delete&applicationId=${application.applicationid}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa ứng tuyển này không?');">Xóa</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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

                                                    // Xử lý thông báo thành công
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
