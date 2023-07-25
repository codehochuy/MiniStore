<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Quản lý nghỉ phép</title>
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
                    <li class="breadcrumb-item active"><a href="#"><b>Quản lý nghỉ phép</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <table class="table table-hover table-bordered" id="sampleTable">
                <thead>
                    <tr>
                        <!--<th width="10"><input type="checkbox" id="all"></th>-->
                        <th>Employee Name</th>
                        <th>Employee ID</th>
                        <th>Shift ID</th>
                        <th>Status</th>
                        <th>Handle</th>
                    </tr>
                </thead>

                <tbody>                                        
                    <c:forEach items="${requestScope.LIST}" var="i">
                        <c:if test="${i.applicationStatus eq false}">
                            <tr>
                                <!--<td width="10"><input type="checkbox" name="check1" value="1"></td>-->
                                <td>${i.employeeName}</td>
                                <td>${i.employeeID}</td>
                                <td><c:forEach items="${requestScope.LIST3}" var="a">
                                        <c:if test="${a.shiftid eq i.leaveShift}">${a.shiftid}</c:if>
                                    </c:forEach></td>
                                <td>not yet</td>
                                <td style="display: flex; justify-content: space-left">
                                    <form style="width: 40px" action="ShowApplicationDetailServlet" method="post">
                                        <button class="btn btn-primary btn-sm trash" type="submit" title="Xem chi tiết">
                                            <i class="fas fa-eye"></i>
                                        </button>
                                        <input type="hidden" name="leaveShift" value="${i.leaveShift}"/>
                                        <input type="hidden" name="employeeID" value="${i.employeeID}"/>
                                    </form>
                                    <form action="DeleteApplicationServlet" method="Post" id="deleteForm">
                                        <input type="hidden" name="leaveShift" value="${i.leaveShift}">
                                        <button class="btn btn-primary btn-sm trash" type="submit" title="Xóa" onclick="confirmDelete(event)">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
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