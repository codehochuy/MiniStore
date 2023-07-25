

<%@page import="DTO.Employee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>
    <body>
        <%
            Employee employee = ((Employee) session.getAttribute("USER"));
            if (employee.getEmprole().getName().equals("Manager")) {
                request.getRequestDispatcher("Unauthorized.jsp").forward(request, response);
            }
        %>
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">
                <c:if test="${sessionScope.isWork != null}">
                    <li>
                        <a id="showToast" class="app-nav__item" href="attendance" style="padding: 25px">Chấm công</a>
                    </li>
                </c:if>
                <!-- User Menu-->
                <li style="margin-left: 12%;"><a class="app-nav__item" href="Logout" ><i class='bx bx-log-out bx-rotate-180'></i> </a>

                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user"><img class="app-sidebar__user-avatar" src="img/${sessionScope.USER.employeeavatar}" width="50px"
                                                alt="User Image">
                <div>
                    <p class="app-sidebar__user-name"><b></b></p>
                    <p class="app-sidebar__user-designation"><font >
                        Welcome, ${sessionScope.USER.employeename}
                        <font/></p>
                </div>
            </div>
            <hr>
            <ul class="app-menu">
                <!--                <li><a class="app-menu__item haha" href="phan-mem-ban-hang.html"><i class='app-menu__icon bx bx-cart-alt'></i>
                                        <span class="app-menu__label">POS Bán Hàng</span></a></li>
                                <li><a class="app-menu__item " href="index.html"><i class='app-menu__icon bx bx-tachometer'></i><span
                                            class="app-menu__label">Bảng điều khiển</span></a></li>
                                <li><a class="app-menu__item " href="table-data-table.html"><i class='app-menu__icon bx bx-id-card'></i>
                                        <span class="app-menu__label">Quản lý nhân viên</span></a></li>
                                <li><a class="app-menu__item " href="#"><i class='app-menu__icon bx bx-user-voice'></i><span
                                            class="app-menu__label">Quản lý khách hàng</span></a></li>-->



                <li><a class="app-menu__item" href="#" onclick="submitForm()"><i class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Xem lịch làm việc </span></a>


                </li>



                <!--                <li><a class="app-menu__item" href="table-data-banned.html"><i class='app-menu__icon bx bx-run'></i><span
                                           class="app-menu__label">Quản lý nội bộ
                                       </span></a></li>
                               <li><a class="app-menu__item" href="table-data-money.html"><i class='app-menu__icon bx bx-dollar'></i><span
                                           class="app-menu__label">Bảng kê lương</span></a></li>
                -->                            

                <li><a class="app-menu__item" href="LeaveApplicationServlet"><i class='app-menu__icon bx bx-calendar-check'></i><span
                            class="app-menu__label">Đơn xin nghỉ phép</span></a>
                </li>
                <li><a class="app-menu__item" href="ShowPayrollDetailServlet?shiftID1=1&shiftID2=100"><i class='app-menu__icon bx bx-calendar-check'></i><span
                            class="app-menu__label">Bảng lương cá nhân</span></a>
                </li>
                <li><a class="app-menu__item" href="ShowAttendanceReportDetailServlet"><i class='app-menu__icon bx bx-calendar-check'></i><span
                            class="app-menu__label">Thông tin điểm danh</span></a>
                </li>
                <!--                <li><a class="app-menu__item" href="ShowAttendanceFormServlet"><i
                                            class='app-menu__icon bx bx-pie-chart-alt-2'></i><span class="app-menu__label">Điểm danh</span></a>
                                </li>-->
                <c:if test="${sessionScope.USER.emprole.name eq 'Sales'}">
                    <li><a class="app-menu__item" href="productList"><i
                                class='app-menu__icon bx bx-pie-chart-alt-2'></i><span class="app-menu__label">Sản phẩm</span></a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.isWork != null && sessionScope.isPay eq true}">
                    <li><a class="app-menu__item" href="cart"><i
                                class='app-menu__icon bx bx-pie-chart-alt-2'></i><span class="app-menu__label">Thanh toán</span></a>
                    </li>
                </c:if>











                <!--<li><a class="app-menu__item" href="page-calendar.html"><i class='app-menu__icon bx bx-calendar-check'></i><span
                class="app-menu__label">Lịch công tác </span></a></li>
    <li><a class="app-menu__item" href="#"><i class='app-menu__icon bx bx-cog'></i><span class="app-menu__label">Cài
                đặt hệ thống</span></a></li>-->
            </ul>
        </aside>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var currentUrl = window.location.href;

                // Tìm phần tử li có href tương ứng với URL hiện tại và thêm lớp active
                var navItems = document.querySelectorAll(".app-menu__item");
                navItems.forEach(function (item) {
                    if (item.href === currentUrl) {
                        item.classList.add("active");
                    }
                });
            });
        </script>
        <script>
            function submitForm() {
                // Lấy ID từ session
                var id = '<%= session.getAttribute("ID")%>';

                // Tạo một form ẩn
                var form = document.createElement("form");
                form.method = "POST";
                form.action = "WorkSheet_show_sales";

                // Thêm trường ẩn chứa giá trị ID vào form
                var idField = document.createElement("input");
                idField.type = "hidden";
                idField.name = "ID";
                idField.value = id;
                form.appendChild(idField);

                document.body.appendChild(form);
                form.submit();
            }
        </script>

    </body>
</html>
