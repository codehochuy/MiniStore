

<%@page import="DTO.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .rounded {
                border-radius: 50%;
            }

        </style>
    </head>
    <body>
        <%
            Employee employee = ((Employee) session.getAttribute("USER"));
            if (!(employee.getEmprole().getName().equals("Manager"))) {
                request.getRequestDispatcher("Unauthorized.jsp").forward(request, response);
            }
        %>
        <!-- Navbar-->
        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">


                <!-- User Menu-->
                <li><a class="app-nav__item" href="Logout"><i class='bx bx-log-out bx-rotate-180'></i> </a>

                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <aside class="app-sidebar">
            <div class="app-sidebar__user">
                <img style=" border-radius: 50%; " class="app-sidebar__user-avatar rounded" src="img/${sessionScope.USER.employeeavatar}" width="100px"  alt="User Image">
                <div>
                    <p class="app-sidebar__user-name"><b></b></p>
                    <p class="app-sidebar__user-designation">
                        <font>${sessionScope.USER.employeename}</font>
                    </p>
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
                <li><a class="app-menu__item" href="revunue"><i
                            class='app-menu__icon bx bx-pie-chart-alt-2'></i><span class="app-menu__label">Bảng điều khiển  </span></a>
                </li>
                <li><a class="app-menu__item" href="product"><i
                            class='app-menu__icon bx bx-purchase-tag-alt'></i><span class="app-menu__label">Quản lý sản phẩm</span></a>
                </li>
                <li><a class="app-menu__item " href="user"><i 
                            class='app-menu__icon bx bx-id-card'></i><span class="app-menu__label">Quản lý nhân viên</span></a>
                </li>
                  <li><a class="app-menu__item <%= session.getAttribute("menuHighlight") != null && session.getAttribute("menuHighlight").equals("worksheet") ? "active-menu" : "" %>" href="WorkSheet_show">
    <i class='app-menu__icon bx bx-id-card'></i>
    <span class="app-menu__label">Quản lý lịch làm việc</span>
</a>
                </li>
            

                <li>
                    <a class="app-menu__item" href="OrderServlet">
                        <i class='app-menu__icon bx bx-task'></i>
                        <span class="app-menu__label">Quản lý đơn hàng</span>
                    </a>
                </li>
                <li>
                    <a class="app-menu__item" href="VoucherServlet">
                        <i class='app-menu__icon bx bx-dollar'></i>
                        <span class="app-menu__label">Quản lý voucher</span>
                    </a>
                </li> 
                <!--                <li><a class="app-menu__item" href="table-data-banned.html"><i class='app-menu__icon bx bx-run'></i><span
                                           class="app-menu__label">Quản lý nội bộ
                                       </span></a></li>
                               <li><a class="app-menu__item" href="table-data-money.html"><i class='app-menu__icon bx bx-dollar'></i><span
                                           class="app-menu__label">Bảng kê lương</span></a></li>
                -->                            

                <li><a class="app-menu__item" href="ShowLeaveApplicationServlet"><i class='app-menu__icon bx bx-calendar-check'></i><span
                            class="app-menu__label">Quản lý nghỉ phép</span></a>
                </li>
                <li><a class="app-menu__item" href="ShowPayrollServlet?shiftID1=1&shiftID2=100"><i class='app-menu__icon bx bx-calendar-check'></i><span
                            class="app-menu__label">Bảng lương</span></a>
                </li>
                
                <li><a class="app-menu__item" href="ShowAttendanceReportServlet"><i class='app-menu__icon bx bx-calendar-check'></i><span
                            class="app-menu__label">Báo cáo điểm danh</span></a>
                </li>











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
    </body>
</html>
