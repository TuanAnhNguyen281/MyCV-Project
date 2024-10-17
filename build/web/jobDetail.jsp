<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Chi tiết công việc</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .container {
                margin-top: 20px;
            }
            .card {
                margin-bottom: 20px;
            }
            .btn-primary {
                background-color: #007bff;
                border: none;
            }
            .btn-primary:hover {
                background-color: #0056b3;
            }
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
        <div class="container">
            <h2 class="mb-4">Chi tiết công việc</h2>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${job.jobtitle}</h5>
                    <p class="card-text"><strong>Công ty:</strong> ${job.company}</p>
                    <p class="card-text"><strong>Mô tả:</strong> ${job.description}</p>
                    <p class="card-text"><strong>Lương:</strong> ${job.formatSalary()}</p>
                    <p class="card-text"><strong>Địa điểm:</strong> ${job.location}</p>
                    <p class="card-text"><strong>Cần tuyển:</strong> ${job.quantity} người</p>
                    <p class="card-text"><strong>Ngày đăng:</strong> ${job.date}</p>
                </div>
            </div>
            <h3 class="mb-3">Nộp CV</h3>
            <form action="ApplicationServlet?action=apply" method="post">
                <input type="hidden" name="jobid" value="${job.jobid}">
                <div class="form-group">
                    <label for="cvId">Chọn CV:</label>
                    <select class="form-control" id="cvid" name="cvid" required>
                        <c:forEach var="cv" items="${cvList}">
                            <option value="${cv.cvid}">${cv.cv}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Nộp CV</button>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
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
