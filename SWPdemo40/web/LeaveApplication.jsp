<%-- 
    Document   : LeaveApplication
    Created on : May 28, 2023, 11:36:10 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leave Application</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS -->
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <!-- Font-icon CSS -->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

        <style>
            body {
                font-family: 'Roboto', sans-serif;
            }

            h1 {
                text-align: center;
            }

            h6 {
                text-align: center;
            }

            .form-container {
                max-width: 400px;
                margin: 0 auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .form-container label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            .form-container input[type="text"] {
                width: 100%;
                padding: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            .form-container .form-group {
                margin-bottom: 5px;
            }

            .form-container .btn-container {
                text-align: center;
                margin-top: 20px;
            }

            .form-container .btn-container input[type="submit"] {
                padding: 5px 10px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
        </style>
        <style>
            .btn-check-status {
                background-color: #4CAF50; /* Màu nền */
                color: white; /* Màu chữ */
                padding: 5px 10px; /* Kích thước nút */
                border: none; /* Loại bỏ viền */
                border-radius: 5px; /* Bo tròn góc */
                cursor: pointer; /* Hiệu ứng con trỏ */
                font-size: 14px; /* Cỡ chữ */
            }

            .btn-check-status:hover {
                background-color: #45a049; /* Màu nền khi hover */
            }
        </style>
        <jsp:include page="headerSales.jsp"/>
    </head>
    <body>
        <br><br><br><h1>ĐƠN XIN NGHỈ PHÉP</h1>
        <div class="form-container">
            <form action="SendLeaveApplicationServlet" method="post">
                <div class="form-group">
                    <label for="fullName">Name: ${sessionScope.USER.employeename}</label>
                    <input type="hidden" id="fullName" name="fullName" value="${sessionScope.USER.employeename}" required readonly>
                </div>
                <div class="form-group">
                    <label>ID: ${sessionScope.USER.employeeID}</label>
                    <input type="hidden" id="ID" name="ID" value="${sessionScope.USER.employeeID}" required readonly>
                </div>
                <div class="form-group">
                    <label for="leaveDate">Leave Shift:</label>
                    <!--<input type="date" id="leaveDate" name="leaveDate" required>-->
                    <select id="leaveShift" name="leaveShift">
                        <c:forEach items="${requestScope.LIST}" var="i">
                            <c:if test="${i.worksheetemployeefirstdto.name eq sessionScope.USER.employeename or i.worksheetemployeeseconddto.name eq sessionScope.USER.employeename}">
                                <option value="${i.shiftid}">(${i.shiftid}) ${i.starttime} ${i.startdate} to ${i.endtime} ${i.enddate}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="reason">Leave Reason:</label>
                    <!--<input type="text" id="reason" name="leaveReason" required>-->
                    <textarea id="reason" name="leaveReason" rows='5' cols='40' required></textarea>
                </div>
                <div class="btn-container">
                    <input type="submit" value="SEND">
                </div>
                <div class="form-group" style="text-align: center;">
                    <p style="color: green;">${SC}</p>
                </div>
                <div class="form-group" style="text-align: center;">
                    <p style="color: red;">${SF}</p>
                </div>
            </form>
            <form class="form-group" action="CheckApplicationStatusServlet" method="post" id="myFrom">
                <input type="hidden" name="employeeID" value="${sessionScope.USER.employeeID}" required readonly>
                <h6><button type="submit" class="btn-check-status">CHECK APPLICATION STATUS</button></h6>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>