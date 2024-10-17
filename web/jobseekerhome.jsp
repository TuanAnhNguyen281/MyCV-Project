<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trang chủ người tìm việc</title>
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
            .nav-item a {
                display: inline-block;
                margin-right: 20px;
            }
        </style>
    </head>
    <body>
        <header>
            <div id="signin" class="container-fluid bg-light">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="navbar-brand" href="#">
                        <img class="logo" src="MyCV (1).png" alt="alt" width="150"/>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="CvManageServlet?action=list">Hồ sơ & CV</a>
                                <a class="nav-link" href="ApplicationServlet?action=jobseekerview">Danh sách ứng tuyển</a>
                            </li>
                        </ul>
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

        <div class="container mb-3">
            <form action="JobseekerServlet" method="post" class="form-inline justify-content-center">
                <input type="hidden" name="action" value="searchByTitle">
                <div class="input-group w-50">
                    <input type="text" id="title" name="title" class="form-control" placeholder="Nhập tên công việc" aria-label="Tên công việc">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-primary ">Tìm Kiếm</button>
                    </div>
                </div>
            </form>
        </div>


        <c:if test="${not empty message}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${message}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

        <section id="job-listings" class="py-4">
            <div class="container">
                <h2 class="text-center mb-4">Danh sách công việc</h2>
                <div class="row">
                    <c:forEach items="${joblist}" var="job">
                        <div class="col-md-6 col-lg-4 mb-4">
                            <div class="card h-100 shadow-sm">
                                <div class="card-body">
                                    <h5 class="card-title font-weight-bold">${job.jobtitle}</h5>
                                    <p class="card-text">
                                        <i class="fas fa-building"></i> 
                                        <small class="text-muted">Công ty: <span class="font-weight-bold text-dark">${job.company}</span></small>
                                    </p>
                                    <p class="card-text">
                                        <i class="fas fa-dollar-sign"></i> 
                                        <small class="text-muted">Lương: <span class="font-weight-bold text-success">${job.formatSalary()}</span></small>
                                    </p>
                                    <p class="card-text">
                                        <i class="fas fa-map-marker-alt"></i> 
                                        <small class="text-muted">Địa điểm: <span class="font-weight-bold text-dark">${job.location}</span></small>
                                    </p>
                                    <a href="JobseekerServlet?action=viewJobDetails&jobId=${job.jobid}" class="btn btn-primary btn-block mt-3">Xem chi tiết</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
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
            });
        </script>
    </body>
</html>
