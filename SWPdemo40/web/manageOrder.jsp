<%-- 
    Document   : manageOrder
    Created on : May 24, 2023, 10:21:39 PM
    Author     : PC
--%>

<%@page import="DTO.Employee7"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách đơn hàng</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="css/view.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

    </head>
    <body onload="time()" class="app sidebar-mini rtl">
        <!--Check login-->
        
        <!-- Navbar-->

        <header class="app-header">
            <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar"
                                            aria-label="Hide Sidebar"></a>
            <!-- Navbar Right Menu-->
            <ul class="app-nav">


                <!-- User Menu-->
                <li><a class="app-nav__item" href="/index.html"><i class='bx bx-log-out bx-rotate-180'></i> </a>

                </li>
            </ul>
        </header>
        <!-- Sidebar menu-->
        <div class="app-sidebar__overlay" data-toggle="sidebar"></div>
        <jsp:include page="headerAdmin.jsp"/>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item"><a href="OrderServlet">Danh sách đơn hàng</a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">
<!--                                <div class="col-sm-2">

                                    <a class="btn btn-add btn-sm" href="productList" title="Thêm"><i class="fas fa-plus"></i>
                                        Tạo mới đơn hàng</a>
                                </div>-->




                            </div>

                            <table class="table table-hover table-bordered" id="sampleTable">
                                <thead>
                                    <tr>

                                        <th>ID đơn hàng</th>
                                        <th>Tên khách hàng</th>
                                        <th>Số điện thoại</th>
                                        <th>Voucher</th>
                                        <th>Ngày đặt hàng</th>
                                        <th>Nhân viên bán hàng</th>                                        
                                        <th>Tính năng</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listOrders}" var="o">
                                        <tr> 

                                            <td><a href="#">${o.getOrderID()}</a></td> 
                                            <td>${o.getCustomerName()}</td>  
                                            <td>${o.getPhone()}</td> 
                                            <td>${o.getVoucherName()}</td>   
                                            <td>${o.getOrderDate()}</td> 
                                            <td>${o.getEmployeeName()}</td> 
                                            <td style="display: flex">

                                                <form style="width: 35px" id="deleteForm" action="DeleteOrderServlet?orderID=${o.getOrderID()}" method="post">
                                                    <button class="btn btn-primary btn-sm trash" type="submit" title="Xóa" onclick="confirmDelete();">
                                                        <i class="fas fa-trash-alt"></i>
                                                    </button>
                                                </form>

                                                <form style="width: 35px" action="UpdateOrderServlet" method="get">
                                                    <input type="hidden" name="orderID" value="${o.getOrderID()}">
                                                    <input type="hidden" name="customerName" value="${o.getCustomerName()}">
                                                    <input type="hidden" name="phone" value="${o.getPhone()}">
                                                    <input type="hidden" name="voucherID" value="${o.getVoucherID()}">
                                                    <input type="hidden" name="orderDate" value="${o.getOrderDate()}">
                                                    <input type="hidden" name="employeeName" value="${o.getEmployeeName()}">
                                                    <button class="btn btn-primary btn-sm edit" type="submit" title="Sửa">
                                                        <i class="fas fa-edit"></i>
                                                    </button>
                                                </form>

                                                <form style="width: 35px; padding-left: 3px" action="OrderDetailServlet" method="post">
                                                    <input type="hidden" name="orderID" value="${o.getOrderID()}">
                                                    <input type="hidden" name="customerName" value="${o.getCustomerName()}">
                                                    <input type="hidden" name="phone" value="${o.getPhone()}">
                                                    <input type="hidden" name="voucherID" value="${o.getVoucherID()}">
                                                    <input type="hidden" name="voucherName" value="${o.getVoucherName()}">
                                                    <input type="hidden" name="orderDate" value="${o.getOrderDate()}">
                                                    <input type="hidden" name="employeeName" value="${o.getEmployeeName()}">
                                                    <button class="btn btn-primary btn-sm trash" type="submit" title="Xem chi tiết">
                                                        <i  class="fas fa-eye"></i>
                                                    </button>
                                                </form>    
                                            </td>    

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <!-- Essential javascripts for application to work-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="src/jquery.table2excel.js"></script>
        <script src="js/main.js"></script>
        <!-- The javascript plugin to display page loading on top-->
        <script src="js/plugins/pace.min.js"></script>
        <!-- Page specific javascripts-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <!-- Data table plugin-->
        <script type="text/javascript" src="js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="js/plugins/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript">$('#sampleTable').DataTable();</script>
        <script>
            function confirmDelete() {
                var result = confirm("Bạn có chắc chắn muốn xóa đơn hàng này?");
                if (!result) {
                    event.preventDefault();
                }
            }
            var myApp = new function () {
                this.printTable = function () {
                    var tab = document.getElementById('sampleTable');
                    var win = window.open('', '', 'height=700,width=700');
                    win.document.write(tab.outerHTML);
                    win.document.close();
                    win.print();
                }
            }

            <%-- Lấy giá trị của thuộc tính "mess" --%>
            var mess = "${mess}";

            <%-- Kiểm tra nếu có thông báo, hiển thị cửa sổ thông báo --%>
            if (mess !== "") {
                alert(mess);
            }

        </script>
    </body>
</html>
