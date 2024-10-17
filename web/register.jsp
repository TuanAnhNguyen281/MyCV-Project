<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng ký</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f8f9fa;
                overflow-y: hidden;
            }
            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100%;
            }
            .register-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: white;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 50%;
            }
            .register-form {
                flex: 1;
                padding: 40px;
                margin: 10px;
            }
            .error {
                color: white;
                background-color: red;
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
                border: 1px solid darkred;
                font-weight: bold;
                text-align: center;
            }
            .success {
                color: white;               /* Màu chữ trắng */
                background-color: green;    /* Nền xanh */
                padding: 10px;              /* Khoảng cách bên trong hộp */
                margin: 10px 0;             /* Khoảng cách bên ngoài hộp */
                border-radius: 5px;         /* Bo tròn các góc hộp */
                border: 1px solid darkgreen;/* Đường viền màu xanh đậm */
                font-weight: bold;          /* Chữ đậm */
                text-align: center;         /* Căn giữa nội dung */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="register-container">
                <div class="register-form">
                    <h2 class="text-center">Đăng ký</h2>
                    <form action="registerservlet" method="post">

                        <div class="form-group">
                            <label for="fullName">Họ và tên:</label>
                            <input type="text" class="form-control" id="fullName" name="fullname" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="username">Tên đăng nhập:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Mật khẩu:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="form-group">
                            <label for="role">Bạn là:</label><br>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="employer" value="1" required>
                                <label class="form-check-label" for="employer">Nhà tuyển dụng</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="jobseeker" value="2" required>
                                <label class="form-check-label" for="jobseeker">Người tìm việc</label> 
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Đăng ký</button>
                        <c:if test="${not empty message}">
                            <div class="${success ? 'success' : 'error'}" role="alert">
                                ${message}
                            </div>
                        </c:if>

                    </form>
                    <p class="mt-3 text-center">Nếu đã có tài khoản, vui lòng <a href="login.jsp">đăng nhập</a>.</p>
                </div>
            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
