<%@page import="DTO.Employee7"%>
<%-- 
    Document   : manageVoucher
    Created on : Jun 9, 2023, 12:36:47 PM
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Voucher</title>
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
                    <li class="breadcrumb-item"><a href="OrderServlet">Danh sách Voucher</a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">
                                <div class="col-sm-2">

                                    <a class="btn btn-add btn-sm" href="AddVoucherServlet" title="Thêm"><i class="fas fa-plus"></i>
                                        Tạo mới Voucher</a>
                                </div>



                            </div>

                            <table class="table table-hover table-bordered" id="sampleTable">
                                <thead>
                                    <tr>

                                        <th>ID voucher</th>
                                        <th>Tên voucher</th>
                                        <th>Ngày bắt đầu</th>
                                        <th>Ngày kết thúc</th>
                                        <th>Trạng thái</th>
                                        <th>Điều kiện</th>
                                        <th>Số lượng</th>                                        
                                        <th>Giá trị</th>                                        
                                        <th>Nhân viên tạo</th>
                                        <th>Tính năng</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listVoucher}" var="v">
                                        <c:if test="${v.getVoucherID() != 1}">
                                            <tr> 

                                                <td>${v.getVoucherID()}</td> 
                                                <td>${v.getVoucherName()}</td>  
                                                <td>${v.getDateStart()}</td>   
                                                <td>${v.getDateEnd()}</td>   
                                                <td>
                                                    <c:if test="${v.isVoucherStatus()}">
                                                        ON
                                                    </c:if>
                                                    <c:if test="${!v.isVoucherStatus()}">
                                                        OFF
                                                    </c:if>
                                                </td>
                                                <td>Giá trị đơn hàng trên ${v.getCondition()} VND</td> 
                                                <td>${v.getQuantity()}</td> 
                                                <td>Giảm ${v.getValue()} VND</td> 
                                                <td>${v.getEmployeeName()}</td> 
                                                <td style="display: flex">

                                                    <form style="width: 35px" id="deleteForm" action="DeleteVoucherServlet?voucherID=${v.getVoucherID()}" method="post">
                                                        <button style="height: 29.5px;" class="btn btn-primary btn-sm trash" type="submit" title="Xóa" onclick="confirmDelete();">
                                                            <i class="fas fa-trash-alt"></i>
                                                        </button>
                                                    </form>

                                                    <form style="width: 35px" action="UpdateVoucherServlet" method="post">
                                                        <input type="hidden" name="voucherID" value="${v.getVoucherID()}">
                                                        <input type="hidden" name="voucherName" value="${v.getVoucherName()}">
                                                        <input type="hidden" name="dateStart" value="${v.getDateStart()}">
                                                        <input type="hidden" name="dateEnd" value="${v.getDateEnd()}">
                                                        <input type="hidden" name="voucherStatus" value="${v.isVoucherStatus()}">
                                                        <input type="hidden" name="condition" value="${v.getCondition()}">
                                                        <input type="hidden" name="quantity" value="${v.getQuantity()}">
                                                        <input type="hidden" name="value" value="${v.getValue()}">
                                                        <input type="hidden" name="employeeID" value="${v.getEmployeeID()}">
                                                        <input type="hidden" name="employeeName" value="${v.getEmployeeName()}">
                                                        <button style="height: 29.5px;" class="btn btn-primary btn-sm edit" type="submit" title="Sửa">
                                                            <i class="fas fa-edit"></i>
                                                        </button>
                                                    </form>


                                                </td>    

                                            </tr>
                                        </c:if>



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
                var result = confirm("Bạn có chắc chắn muốn xóa Voucher này?");
                if (!result) {
                    event.preventDefault();
                }
            }

            var messUpdateVoucher = "${messUpdateVoucher}";


            if (messUpdateVoucher !== "") {
                alert(messUpdateVoucher);
            }


            var messAddVoucher = "${messAddVoucher}";


            if (messAddVoucher !== "") {
                alert(messAddVoucher);
            }

            var messDeleteVoucher = "${messDeleteVoucher}";


            if (messDeleteVoucher !== "") {
                alert(messDeleteVoucher);
            }
        </script>
    </body>
</html>
