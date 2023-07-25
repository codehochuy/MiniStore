<%-- 
    Document   : Unauthorized
    Created on : Jul 4, 2023, 4:24:57 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Unauthorized Access</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            
            background-size: cover;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            text-align: center;
            color: #000;
        }

        h1 {
            font-size: 36px;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            margin-bottom: 30px;
        }

        a {
            text-decoration: none;
            color: #000;
            font-weight: bold;
            font-size: 16px;
            background-color: #f44336;
            padding: 10px 20px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>
    <h1>Unauthorized Access</h1>
    <p>You are not authorized to access this page.</p>
    <a href="Logout">Back to Login</a>

</body>


</html>
