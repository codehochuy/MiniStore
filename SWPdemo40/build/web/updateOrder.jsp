<%-- 
    Document   : UpdateOrder
    Created on : Jun 7, 2023, 7:55:40 PM
    Author     : PC
--%>

<%@page import="DTO.Employee7"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sửa đơn hàng</title>
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
                    <li class="breadcrumb-item"><a href="#">Sửa đơn hàng</a></li>
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 class="tile-title">Chi tiết đơn hàng</h3>
                        <div>
                            <form style="width: 35px" id="deleteForm" action="AddProductOrderDetailServlet" method="get">

                                <input type="hidden" name="orderID" value="${orderID}">
                                <input type="hidden" name="customerName" value="${customerName}">
                                <input type="hidden" name="phone" value="${phone}">
                                <input type="hidden" name="voucherID" value="${voucherID}">
                                <input type="hidden" name="orderDate" value="${orderDate}">
                                <input type="hidden" name="employeeName" value="${employeeName}">
                                <button class="btn btn-save" type="submit" title="Thêm sản phẩm">
                                    <i class="fas fa-plus"></i>Thêm sản phẩm vào đơn hàng
                                </button>
                            </form>     
                        </div>


                        <table class="table table-hover table-bordered" id="sampleTable">
                            <thead>
                                <tr>
                                    <th>ID sản phẩm</th>
                                    <th>Hình ảnh</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                    <th>Tính năng</th>

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
                                            <input type="number" id="quantityInput" name="quantity" value="${o.getQuantity()}" onchange="updateQuantity(this)" />
                                        </td>
                                        <td><c:out value="${o.getProductPrice() * o.getQuantity()}"/></td>
                                        <td style="display: flex">
                                            <form style="width: 35px" action="UpdateQuantityOrderDetailServlet" method="post">
                                                <input type="hidden" name="quantity" value="${o.getQuantity()}" />
                                                <input type="hidden" name="productID" value="${o.getProductID()}" />
                                                <input type="hidden" name="orderID" value="${orderID}">
                                                <input type="hidden" name="customerName" value="${customerName}">
                                                <input type="hidden" name="phone" value="${phone}">
                                                <input type="hidden" name="voucherID" value="${voucherID}">
                                                <input type="hidden" name="orderDate" value="${orderDate}">
                                                <input type="hidden" name="employeeName" value="${employeeName}">
                                                <button class="btn btn-primary btn-sm edit" type="submit" title="Cập nhật số lượng">
                                                    <i class="fas fa-check"></i>
                                                </button>
                                            </form>
                                            <form style="width: 35px" id="deleteForm" action="DeleteOrderDetailServlet" method="post">
                                                <input type="hidden" name="productID" value="${o.getProductID()}" />
                                                <input type="hidden" name="orderID" value="${orderID}">
                                                <input type="hidden" name="customerName" value="${customerName}">
                                                <input type="hidden" name="phone" value="${phone}">
                                                <input type="hidden" name="voucherID" value="${voucherID}">
                                                <input type="hidden" name="orderDate" value="${orderDate}">
                                                <input type="hidden" name="employeeName" value="${employeeName}">
                                                <button class="btn btn-primary btn-sm trash" type="submit" title="Xóa" onclick="confirmDelete();">
                                                    <i class="fas fa-trash-alt"></i>
                                                </button>
                                            </form> 

                                        </td> 
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                        <h4> Tiền hàng (Tạm tính): ${total}</h4>            
                        <h4> Voucher: <span id="voucherValue" style="color: red">-${voucher.value}</span></h4> 
                        <h4>Tổng tiền: ${total - voucher.value}</h4>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 class="tile-title">Sửa đơn hàng</h3>
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
                                <div class="form-group col-md-4">
                                    <label class="control-label">Số điện thoại khách hàng</label>
                                    <input required id="phoneInput" name="phone" class="form-control" type="number" value="${phone}" onchange="updatePhoneNumber()">
                                </div>


                                <div class="form-group  col-md-4">
                                    <label class="control-label">Voucher</label>

                                    <select class="form-control" id="exampleSelect1" name="voucherID">

                                        <c:forEach  items="${listVouchers}" var="voucher">

                                            <c:if test="${voucher.isVoucherStatus() && orderDate.after(voucher.dateStart) && orderDate.before(voucher.dateEnd)}">
                                                <option value="${voucher.getVoucherID()}" ${voucher.getVoucherID() == voucherID ? 'selected' : ''} data-condition="${voucher.getCondition()}">${voucher.getVoucherName()}</option>
                                            </c:if>


                                        </c:forEach>

                                    </select>
                                </div>

                                <div class="form-group  col-md-4">
                                    <label class="control-label">Ngày đặt hàng</label>
                                    <input required="" name="orderDate" class="form-control" type="date" value="${orderDate}">
                                </div>
                                <div class="form-group  col-md-4">
                                    <label class="control-label">Tên người bán</label>
                                    <!--<input class="form-control" type="text" value="${employeeName}">-->
                                    <select class="form-control" id="exampleSelect1" name="employeeID">
                                        <c:forEach  items="${listEmployees}" var="employee">
                                            <c:if test="${employee.getRoleId() == 2}">
                                                <option value="${employee.getEmployeeID()}" ${employee.getEmployeeName() == employeeName ? 'selected' : ''}>${employee.getEmployeeName()}</option>
                                            </c:if>

                                        </c:forEach>
                                    </select>
                                </div>


                        </div>
                        <button class="btn btn-save" type="submit">Lưu lại</button>
                        <a class="btn btn-cancel" href="OrderServlet">Hủy bỏ</a>           

                    </div>
                </div>
            </div>


        </main>
        <script>

            window.onload = function () {
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
            };

            function confirmDelete() {
                var result = confirm("Bạn có chắc chắn muốn xóa sản phẩm này?");
                if (!result) {
                    event.preventDefault();
                }
            }




            <%-- cập nhật giá trị số lượng vào form "Cập nhật số lượng" --%>


            function updateQuantity(input) {
                var quantity = input.value;
                var form = input.parentNode.parentNode.getElementsByTagName('form')[0];
                var formQuantityInput = form.querySelector('input[name="quantity"]');
                formQuantityInput.value = quantity;
                if (quantity <= 0) {

                    alert('Vui lòng nhập số lượng lớn hơn 0');
                    return false;
                }
                return true;
            }

            //check phone





            var listCustomers = [
            <c:forEach var="customer" items="${listCustomers}">
                {
                    customerID: "${customer.customerID}",
                    customerName: "${customer.customerName}",
                    phone: "${customer.phone}"
                },
            </c:forEach>
            ];

            function updatePhoneNumber() {
                var phoneInput = document.getElementById('phoneInput');
                var newPhoneNumber = phoneInput.value;
                var isPhoneNumberExists = listCustomers.some(function (customer) {
                    return customer.phone === newPhoneNumber;
                });
                if (isPhoneNumberExists) {
                    return true;
                } else {
                    alert('Số điện thoại không nằm trong danh sách khách hàng thân thiết.');
                    return false;
                }
            }

            // check select voucher

            var voucherSelect = document.getElementById("exampleSelect1");
            var totalValue = ${total};

            voucherSelect.addEventListener("change", function () {
                var selectedVoucher = voucherSelect.options[voucherSelect.selectedIndex];
                var condition = parseInt(selectedVoucher.dataset.condition);
                var value = parseInt(selectedVoucher.dataset.value);
                if (totalValue < condition) {
                    alert("Tổng tiền chưa đủ điều kiện sử dụng voucher!");
                    voucherSelect.removeChild(selectedVoucher);
                } else {
                    
                }
            });


        </script>                                
    </body>
</html>
