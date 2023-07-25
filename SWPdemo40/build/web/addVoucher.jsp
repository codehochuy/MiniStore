<%-- 
    Document   : addVoucher
    Created on : Jun 9, 2023, 8:26:55 PM
    Author     : PC
--%>
<%@page import="DTO.Employee7"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tạo voucher</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="css/view.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript" src="/ckeditor/ckeditor.js"></script>
        <script src="http://code.jquery.com/jquery.min.js" type="text/javascript"></script>

    </head>
    <body class="app sidebar-mini rtl">
        <!--Check login-->

        <!-- Navbar-->
        <jsp:include page="headerAdmin.jsp"/>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><a href="VoucherServlet">Danh sách Voucher</a></li>
                    <li class="breadcrumb-item"><a href="addVoucher.jsp">Tạo Voucher</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 class="tile-title">Tạo Voucher</h3>
                        <div class="tile-body">
                            <form action="AddVoucherServlet" class="row" method="post" onsubmit="return validateForm()">

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Tên voucher</label>
                                    <input required="" name="voucherName" class="form-control" type="text">

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Ngày bắt đầu</label>
                                    <input required="" name="dateStart" class="form-control" type="date">

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Ngày kết thúc</label>
                                    <input required="" name="dateEnd" class="form-control" type="date" >

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Trạng thái</label>
                                    <select name="voucherStatus" class="form-control" id="exampleSelect1">
                                        <option selected value="true">ON</option>
                                        <option value="false">OFF</option> 
                                    </select>
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Điều kiện</label>
                                    <!--                                    <input name="condition" class="form-control" type="number">-->
                                    <input required="" name="condition" class="form-control" type="number" />


                                </div>

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Số lượng</label>
                                    <input required="" name="quantity" class="form-control" type="number">
                                </div>

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Giá trị</label>
                                    <input required="" name="value" class="form-control" type="number">
                                </div>

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Nhân viên tạo</label>
                                    <select class="form-control" id="exampleSelect1" name="employeeID">
                                        <c:forEach  items="${listEmployees}" var="employee">
                                            <c:if test="${employee.getRoleId() == 1}">
                                                <option value="${employee.getEmployeeID()}">${employee.getEmployeeName()}</option>
                                            </c:if>

                                        </c:forEach>
                                    </select>
                                </div>


                        </div>
                        <button class="btn btn-save" type="submit">Lưu lại</button>
                        <a class="btn btn-cancel" href="VoucherServlet">Hủy bỏ</a>           

                    </div>
                </div>
            </div>


        </main>
        <script>
            function validateForm() {
                // Lấy giá trị các trường dữ liệu
                var startDate = document.getElementsByName("dateStart")[0].value;
                var endDate = document.getElementsByName("dateEnd")[0].value;
                var status = document.getElementsByName("voucherStatus")[0].value;
                var condition = document.getElementsByName("condition")[0].value;
                var quantity = document.getElementsByName("quantity")[0].value;
                var value = document.getElementsByName("value")[0].value;
                var currentDate = new Date().toISOString().split('T')[0];
                // Kiểm tra ngày kết thúc và ngày bắt đầu với trạng thái
                if (status === "true" && endDate < currentDate) {
                    alert('Ngày kết thúc không hợp lệ! Ngày kết thúc phải lớn hơn hoặc bằng ngày hôm nay khi trạng thái ON.');
                    return false;
                }
                
                if (status === 'true' && startDate > currentDate){
                    alert('Ngày bắt đầu không hợp lệ! Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày hôm nay khi trạng thái ON.');
                    return false;
                }
                
                if ( endDate < startDate) {
                    alert("Ngày bắt đầu và kết thúc không hợp lệ! Ngày kết thúc phải lớn hơn hoặc bắt ngày bắt đầu.");
                    return false;
                }
                
                // Kiểm tra điều kiện >= 0
                if (condition <= 0) {
                    alert('Điều kiện không hợp lệ! Điều kiện phải lớn hơn 0.');
                    return false;
                }

                // Kiểm tra số lượng >= 0
                if (quantity <= 0) {
                    alert('Số lượng không hợp lệ! Số lượng phải lớn hơn 0.');
                    return false;
                }

                

                // Kiểm tra giá trị >= 0
                if (value <= 0) {
                    alert('Giá trị không hợp lệ! Giá trị phải lớn hơn 0.');
                    return false;
                }

                // Mọi kiểm tra đã thành công, gửi biểu mẫu
                return true;
            }
        </script>

    </body>
</html>
