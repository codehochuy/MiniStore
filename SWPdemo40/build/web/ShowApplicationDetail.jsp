<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đơn xin nghỉ phép</title>
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

            .container {
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .form-container {
                width: 320px;
                background-color: #f2f2f2;
                padding: 10px;
                margin-bottom: 10px;
                border-radius: 5px;
                text-align: left;
            }

            p {
                margin: 5px 0;
            }

            button[name="choice"] {
                padding: 5px 10px;
                border: none;
                color: #fff;
                border-radius: 3px;
                cursor: pointer;
            }

            button[name="choice"][value="yes"] {
                background-color: #009933;
            }

            button[name="choice"][value="no"] {
                background-color: #ff4d4d;
            }
            .button-container {
                text-align: center;
                margin-top: 20px;
            }

            .approve-button,
            .reject-button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                font-weight: bold;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
                border: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .approve-button {
                background-color: #54a0ff;
                color: #ffffff;
            }

            .reject-button {
                background-color: #ff5454;
                color: #ffffff;
            }

            .approve-button:hover,
            .reject-button:hover {
                background-color: #2c7be5;
            }
        </style>
        <jsp:include page="headerAdmin.jsp"/>
    </head>
    <body>
        <br><br><br><div class="container">
            <h2>ĐƠN XIN NGHỈ PHÉP</h2>
            <c:if test="${requestScope.LIST !=null }">
                <c:if test="${not empty requestScope.LIST}">
                    <c:forEach var="a" varStatus="counter" items="${requestScope.LIST}">
                        <c:if test="${requestScope.leaveShift eq a.leaveShift and requestScope.employeeID eq a.employeeID and a.applicationStatus eq false}">
                            <div class="form-container">
                                <p>Name: ${a.employeeName}</p>
                                <p>ID: ${a.employeeID}</p>
                                <p>Leave Shift: <c:forEach items="${requestScope.LIST3}" var="i">
                                        <c:if test="${i.shiftid eq a.leaveShift}">${i.shiftid} / ${i.starttime} ${i.startdate} to ${i.endtime} ${i.enddate}</c:if>
                                    </c:forEach></p>
                                <p>Leave Reason: ${a.leaveReason}</p>
                                <p>Status: not yet</p>
                                <form action="ProcessLeaveApplicationServlet" method="post">
                                    <input type="hidden" name="employeeID" value="${a.employeeID}" required readonly>
                                    <input type="hidden" name="leaveReason" value="${a.leaveReason}" required readonly>
                                    <input type="hidden" name="leaveShift" value="${a.leaveShift}" required readonly>
                                    <div class="button-container">
                                        <button class="approve-button" type="submit" name="choice" value="yes">APPROVE</button>
                                        <button class="reject-button" type="submit" name="choice" value="no">REJECT</button>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:if>
        </div>
    </body>
</html>