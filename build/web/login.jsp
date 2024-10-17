<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link rel="stylesheet" href="styles.css"> 
    <style>
        /* CSS cho form login */
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .login-container label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }

        .login-container input[type="text"], 
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .login-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        .login-container input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error {
            color: red;
            font-weight: bold;
            text-align: center;
            margin-bottom: 15px;
        }

        .success {
            color: green;
            font-weight: bold;
            text-align: center;
            margin-bottom: 15px;
        }

        .login-container a {
            text-decoration: none;
            color: #4CAF50;
            display: block;
            text-align: center;
            margin-top: 15px;
        }

        .login-container a:hover {
            color: #45a049;
        }
    </style>
</head>
<body>

    <div class="login-container">
        <h2>Đăng nhập</h2>
        
        <!-- Hiển thị thông báo lỗi hoặc thành công -->
        <c:if test="${not empty message}">
            <div class="${success ? 'success' : 'error'}" role="alert">
                ${message}
            </div>
        </c:if>
        
        <form action="loginservlet" method="post">
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" placeholder="Nhập tên đăng nhập" required>
            
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" placeholder="Nhập mật khẩu" required>
            
            <input type="submit" value="Đăng nhập">
        </form>

        <a href="register.jsp">Nếu chưa có tài khoản ? hãy đăng ký ngay</a>
    </div>

</body>
</html>

