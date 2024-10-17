<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trang chủ nhà tuyển dụng</title>
        
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
                    <a class="navbar-brand" href="#">
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

        <section id="employer-dashboard" class="py-4">
            <div class="container">
                <h2 class="text-center">Bảng điều khiển Nhà tuyển dụng</h2>
                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="card text-center">
                            <div class="card-body">
                                <h5 class="card-title">Tìm Nhân Viên</h5>
                                <p class="card-text">Tìm kiếm và xem thông tin ứng viên phù hợp với nhu cầu công việc của bạn.</p>
                                <a href="ApplicationServlet?action=employerview" class="btn btn-primary">Tìm Nhân Viên</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card text-center">
                            <div class="card-body">
                                <h5 class="card-title">Quản Lý Thông Tin Tuyển Dụng</h5>
                                <p class="card-text">Quản lý thông tin tuyển dụng để thu hút ứng viên phù hợp với vị trí công việc của bạn.</p>
                                <a href="JobManageServlet?action=list" class="btn btn-primary">Quản Lý Thông Tin Tuyển Dụng</a>
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
            });
        </script>
    </body>
</html>
