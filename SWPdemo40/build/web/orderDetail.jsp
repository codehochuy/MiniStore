<%-- 
    Document   : orderDetail
    Created on : May 31, 2023, 4:31:41 AM
    Author     : PC
--%>
<%@page import="DTO.Employee7"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Xem chi tiết đơn hàng</title>
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

        <jsp:include page="headerAdmin.jsp"/>
        <main  class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item"><a href="OrderServlet">Danh sách đơn hàng</a></li>
                    <li class="breadcrumb-item">Xem chi tiết đơn hàng</li>
                </ul>
            </div>

            <div  class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 class="tile-title">Thông tin đơn hàng</h3>
                        <div class="tile-body">
                            <form action="UpdateOrderServlet" class="row" method="post">

                                <div class="form-group  col-md-4">
                                    <label class="control-label">ID đơn hàng</label>
                                    <input name="orderID" readonly="" class="form-control" type="text" value="${orderID}">
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Tên khách hàng</label>
                                    <input readonly name="customerName" class="form-control" type="text" value="${customerName}">

                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Số điện thoại khách hàng</label>
                                    <input readonly name="phone" class="form-control" type="number" value="${phone}" >

                                </div>

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Voucher</label>
                                    <input readonly name="voucherName" class="form-control" type="text" value="${voucherName}" >

                                </div>

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Ngày đặt hàng</label>
                                    <input readonly="" name="orderDate" class="form-control" type="date" value="${orderDate}">
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Tên người bán</label>
                                    <!--<input class="form-control" type="text" value="${employeeName}">-->
                                    <input readonly name="employeeName" class="form-control" type="text" value="${employeeName}" >
                                </div>
                            </form>


                        </div>
                        <h3 class="tile-title">Chi tiết đơn hàng</h3>
                        <!--                        <div>
                                                    <a class="btn btn-add btn-sm" href="AddProductOrderDetailServlet?orderID=${orderID}&voucherID=${voucher.voucherID}" title="Thêm sản phẩm"><i class="fas fa-plus"></i>
                                                        Thêm sản phẩm vào đơn hàng</a>
                                                </div>-->


                        <table id="sampleTable" class="table table-hover table-bordered" >
                            <thead>
                                <tr>
                                    <th>ID sản phẩm</th>
                                    <th>Hình ảnh</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                    <!--<th>Tính năng</th>-->

                                </tr>
                            </thead>
                            <tbody>


                                <c:forEach items="${listOrderDetails}" var="o">
                                    <c:set var="total" value="${total + o.getProductPrice() * o.getQuantity()}" />
                                    <tr>

                                        <td>${o.getProductID()}</td>
                                        <td><img src="img/${o.getProductLink()}" width="100px;"></td>

                                        <td>${o.getProductName()}</td>
                                        <td>${o.getProductPrice()}</td>
                                        <td>
                                            <input readonly="" type="number" id="quantityInput" name="quantity" value="${o.getQuantity()}" onchange="updateQuantity(this)" />
                                        </td>
                                        <td><c:out value="${o.getProductPrice() * o.getQuantity()}"/></td>
                                        <!--                                        <td style="display: flex">
                                                                                    <form style="width: 35px" action="UpdateQuantityOrderDetailServlet?orderID=${orderID}&productID=${o.getProductID()}&voucherID=${voucher.voucherID}" method="post">
                                                                                        <input type="hidden" name="quantity" value="${o.getQuantity()}" />  Thêm trường input ẩn 
                                                                                        <button class="btn btn-primary btn-sm edit" type="submit" title="Cập nhật số lượng">
                                                                                            <i class="fas fa-check"></i>
                                                                                        </button>
                                                                                    </form>
                                                                                    <form style="width: 35px" id="deleteForm" action="DeleteOrderDetailServlet?productID=${o.getProductID()}&orderID=${orderID}&voucherID=${voucher.voucherID}" method="post">
                                                                                        <button class="btn btn-primary btn-sm trash" type="submit" title="Xóa" onclick="confirmDelete();">
                                                                                            <i class="fas fa-trash-alt"></i>
                                                                                        </button>
                                                                                    </form> 
                                                                                    
                                                                                </td> -->
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                        <h4> Tiền hàng (Tạm tính): ${total}</h4>            
                        <h4> Voucher: <span  style="color: red">-${voucher.value}</span></h4> 
                        <h4>Tổng tiền: ${total - voucher.value}</h4>


                    </div>

                </div>

            </div>
            <a class="btn btn-delete btn-sm print-file" type="button" title="In" onclick="printData()"><i
                    class="fas fa-print"></i> In dữ liệu</a>

            <a class="btn btn-cancel" href="OrderServlet">Hủy bỏ</a>            

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
                var result = confirm("Bạn có chắc chắn muốn xóa sản phẩm này?");
                if (!result) {
                    event.preventDefault();
                }
            }


            var messUpdateQuantity = "${messUpdateQuantity}";
            if (messUpdateQuantity !== "") {
                alert(messUpdateQuantity);
            }


            var messAddProductOrderDetail = "${messAddProductOrderDetail}";
            if (messAddProductOrderDetail !== "") {
                alert(messAddProductOrderDetail);
            }

            var messUpdateNoVoucher = "${messUpdateNoVoucher}";
            if (messUpdateNoVoucher !== "") {
                alert(messUpdateNoVoucher);
            }

            var mess = "${mess}";
            if (mess !== "") {
                alert(mess);
            }

            <%-- cập nhật giá trị số lượng vào form "Cập nhật số lượng" --%>
            function updateQuantity(input) {
                var quantity = input.value;
                var form = input.parentNode.parentNode.getElementsByTagName('form')[0];
                var formQuantityInput = form.querySelector('input[name="quantity"]');
                formQuantityInput.value = quantity;
            }

//            var myApp = new function () {
//                this.printTable = function () {
//                    var tab = document.getElementById('sampleTable');
//                    var win = window.open('', '', 'height=700,width=700');
//                    win.document.write(tab.outerHTML);
//                    win.document.close();
//                    win.print();
//                }
//            }

            function printData() {
                var appTitleElements = document.getElementsByClassName('app-title');
                for (var i = 0; i < appTitleElements.length; i++) {
                    appTitleElements[i].style.display = 'none';
                }

                var btnElements = document.getElementsByClassName('btn');
                for (var i = 0; i < btnElements.length; i++) {
                    btnElements[i].style.display = 'none';
                }

                var sampleTable = $('#sampleTable').DataTable();
                sampleTable.destroy();

                // Lấy thông tin trong thẻ main
                var mainContent = document.querySelector('main.app-content').innerHTML;

                // Tạo một cửa sổ in mới
                var printWindow = window.open('', '_blank');

                // Ghi nội dung cần in vào cửa sổ in
                printWindow.document.open();
                printWindow.document.write('<html><head><title>HÓA ĐƠN MINISTORE</title></head><body>');
                printWindow.document.write(mainContent);
                printWindow.document.write('</body></html>');
                printWindow.document.close();

                // In nội dung
                printWindow.print();
            }

// Gắn sự kiện onclick vào thẻ a 'In dữ liệu'
            var printButton = document.querySelector('a.btn-print-file');
            printButton.addEventListener('click', printData);



        </script>
    </body>
</body>
</html>
