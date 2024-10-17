<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách công việc</title>
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
                    <a class="navbar-brand" href="employerDashboard.jsp">
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

        <section id="employer-dashboard" class="py-4">
            <div class="container">
                <h2 class="text-center">Quản Lý Công Việc</h2>

                <!-- Search Form -->
                <div class="row mb-3">
                    <div class="col-md-12">
                        <form action="JobManageServlet" method="get" class="form-inline">
                            <input type="hidden" name="action" value="searchByTitle">
                            <div class="form-group mr-2">
                                <label for="title" class="sr-only">Tên Công Việc</label>
                                <input type="text" id="title" name="title" class="form-control" placeholder="Search by Job Title">
                            </div>
                            <input type="hidden" name="action" value="searchByLocation">
                            <button type="submit" class="btn btn-primary">Search</button>
                        </form>
                    </div>
                </div>

                <!-- Hiển thị thông báo thành công -->
                <c:if test="${not empty successMessage}">
                    <div id="successAlert" class="alert alert-success alert-dismissible fade show" role="alert">
                        ${successMessage}
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
                                    <th>Job Title</th>
                                    <th>Company</th>
                                    <th>Salary</th>
                                    <th>Location</th>
                                    <th>Quantity</th>
                                    <th>Date</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${joblist}" var="job">
                                    <tr>
                                        <td>${job.jobtitle}</td>  
                                        <td>${job.company}</td>
                                        <td>${job.formatSalary()}</td> 
                                        <td>${job.location}</td>
                                        <td>${job.quantity}</td>
                                        <td>${job.date}</td>
                                        <td>
                                            <a href="JobManageServlet?action=edit&jobid=${job.jobid}" class="btn btn-sm btn-primary">Edit</a>
                                            <a href="JobManageServlet?action=delete&jobid=${job.jobid}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa công việc này không?');">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 text-right">
                        <a href="addJob.jsp" class="btn btn-primary">Add New Job</a>
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
