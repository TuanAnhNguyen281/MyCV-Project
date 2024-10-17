<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Xem CV</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .cv-container {
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                padding: 20px;
                margin-top: 20px;
            }
            .cv-image {
                max-width: 100%; /* Responsive */
                height: auto; /* Maintain aspect ratio */
            }
        </style>
    </head>
    <body>

        <header>
            <div id="signin" class="container-fluid bg-light">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="navbar-brand" href="">
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

        <div class="container cv-container">
            <h2 class="text-center">Xem CV</h2>
            <!-- Hiển thị CV -->
            <div class="text-center">
                <c:if test="${not empty cv}">
                    <img src="save/${cv.cv}" alt="CV Image" class="cv-image" />
                </c:if>
                <c:if test="${empty cv}">
                    <p>Không có CV để hiển thị.</p>
                </c:if>
            </div>
        </div>
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
