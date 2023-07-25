<%-- 
    Document   : ShowPayroll
    Created on : Jun 15, 2023, 8:32:24 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bảng lương</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS -->
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <!-- Font-icon css -->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link href='https://unpkg.com/css.gg@2.0.0/icons/css/search.css' rel='stylesheet'>
    </head>
    <body onload="time()" class="app sidebar-mini rtl">
        <jsp:include page="headerAdmin.jsp"/>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Bảng lương</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>

            <form action="ShowPayrollServlet" method="post">
                <label for="start-date">Từ ngày:</label>
                <select id="start-date" name="shiftID1">
                    <c:forEach items="${requestScope.Lx}" var="i">
                        <c:if test="${i.shiftid % 5 == 1}">
                            <option value="${i.shiftid}">${i.startdate}</option>
                        </c:if>
                        <c:if test="${i.shiftid eq 55}">
                            <option value="${i.shiftid}">${i.enddate}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <label for="end-date">Đến ngày:</label>
                <select id="end-date" name="shiftID2">
                    <c:forEach items="${requestScope.Lx}" var="i">
                        <c:if test="${i.shiftid % 5 == 4 or i.shiftid eq 55}">
                            <option value="${i.shiftid}">${i.enddate}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <input class="btn btn-add btn-sm" type="submit" value="Search">
            </form>

            <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                    <tr>
                        <!--<th width="10"><input type="checkbox" id="all"></th>-->
                        <th>Employee ID</th>
                        <th>Employee Name</th>
                        <th>Social Insurance (VNĐ) (-)</th>
                        <th>Allowance (VNĐ) (+)</th>
                        <th>Fined Salary (VNĐ) (-)</th>
                        <th>Salary (VNĐ)</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="i" items="${requestScope.L}">
                        <tr>
                            <td>${i.employeeID}</td>
                            <td>${i.employeeName}</td>
                            <td>
                                <c:set var="multipliedSalary" value="${i.salary * 300000 * 0.105}" />
                                <fmt:formatNumber value="${multipliedSalary}" pattern="###,###,###" />
                            </td>
                            <td>
                                <c:set var="multipliedSalary" value="${i.salary * 300000 * 0.03}" />
                                <fmt:formatNumber value="${multipliedSalary}" pattern="###,###,###" />
                            </td>
                            <td>
                                <c:set var="multipliedSalary" value="${i.finedSalary * 300000}" />
                                <fmt:formatNumber value="${multipliedSalary}" pattern="###,###,###" />
                            </td>
                            <td>
                                <c:set var="multipliedSalary" value="${i.salary * 300000 * (1+0.03-0.105)}" />
                                <fmt:formatNumber value="${multipliedSalary}" pattern="###,###,###" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>

        <script src="./js/jquery-3.2.1.min.js"></script>
        <script src="./js/popper.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="src/jquery.table2excel.js"></script>
        <script src="./js/main.js"></script>
        <!-- The javascript plugin to display page loading on top -->
        <script src="js/plugins/pace.min.js"></script>
        <!-- Page specific javascripts -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <!-- Data table plugin -->
        <script type="text/javascript" src="./js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="./js/plugins/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript">

        $('#sampleTable').DataTable();
        function time() {
            var today = new Date();
            var weekday = new Array(7);
            weekday[0] = "Chủ Nhật";
            weekday[1] = "Thứ Hai";
            weekday[2] = "Thứ Ba";
            weekday[3] = "Thứ Tư";
            weekday[4] = "Thứ Năm";
            weekday[5] = "Thứ Sáu";
            weekday[6] = "Thứ Bảy";
            var day = weekday[today.getDay()];
            var dd = today.getDate();
            var mm = today.getMonth() + 1;
            var yyyy = today.getFullYear();
            var h = today.getHours();
            var m = today.getMinutes();
            var s = today.getSeconds();
            m = checkTime(m);
            s = checkTime(s);
            nowTime = h + " giờ " + m + " phút " + s + " giây";
            if (dd < 10) {
                dd = '0' + dd
            }
            if (mm < 10) {
                mm = '0' + mm
            }
            today = day + ', ' + dd + '/' + mm + '/' + yyyy;
            tmp = '<span class="date"> ' + today + ' - ' + nowTime +
                    '</span>';
            document.getElementById("clock").innerHTML = tmp;
            clocktime = setTimeout("time()", "1000", "Javascript");

            function checkTime(i) {
                if (i < 10) {
                    i = "0" + i;
                }
                return i;
            }
        }
        </script>
    </body>
</html>