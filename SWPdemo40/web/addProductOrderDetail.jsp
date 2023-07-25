<%-- 
    Document   : addProductOrderDetail
    Created on : Jun 8, 2023, 1:28:26 AM
    Author     : PC
--%>

<%@page import="DTO.Employee7"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Thêm sản phẩm vào đơn hàng</title>
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
                    <li class="breadcrumb-item"><a href="OrderServlet">Danh sách đơn hàng</a></li>
                    <li class="breadcrumb-item"><a href="OrderDetailServlet?orderID=${orderID}&voucherID=${voucherID}">Xem chi tiết đơn hàng</a></li>
                    <li class="breadcrumb-item"><a href="#">Thêm sản phẩm vào đơn hàng</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 class="tile-title">Thêm sản phẩm vào đơn hàng</h3>
                        <div class="tile-body">
                            <form action="AddProductOrderDetailServlet" class="row" method="post" >
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Mã đơn hàng</label>
                                    <input class="form-control" type="number" readonly="" name="orderID" value="${orderID}">
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Mã sản phẩm</label>
                                    <input required="" class="form-control" type="number" name="productID">
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Số lượng</label>
                                    <input required="" class="form-control" type="number" name="quantity">
                                </div>  
                                <input type="hidden" name="orderID" value="${orderID}">
                                <input type="hidden" name="customerName" value="${customerName}">
                                <input type="hidden" name="phone" value="${phone}">
                                <input type="hidden" name="voucherID" value="${voucherID}">
                                <input type="hidden" name="orderDate" value="${orderDate}">
                                <input type="hidden" name="employeeName" value="${employeeName}">
                        </div>
                                
                        <button class="btn btn-save" type="submit">Lưu lại</button>
                        <a class="btn btn-cancel" href="UpdateOrderServlet?orderID=${orderID}&voucherID=${voucherID}&customerName=${customerName}&phone=${phone}&orderDate=${orderDate}&employeeName=${employeeName}">Hủy bỏ</a>



                    </div>
                    </main>
                    </body>
                    </html>
