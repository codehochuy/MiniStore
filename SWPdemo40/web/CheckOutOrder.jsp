<%-- 
    Document   : CheckOutOrder
    Created on : Jun 24, 2023, 3:20:52 PM
    Author     : kienb
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link href='https://unpkg.com/css.gg@2.0.0/icons/css/search.css' rel='stylesheet'>
    </head>
    <body onload="time()" class="app sidebar-mini rtl">
        <jsp:include page="headerSales.jsp"/>
        <main class="app-content">
            <div class="app-title"> 
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Ca làm việc </b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Name</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.cart}" var="i" varStatus="idx">
                                <tr>
                                    <th scope="row">${idx.index + 1}</th>
                                    <td>${i.key.productName}</td>
                                    <td>
                                        <form action="updateCart">
                                            <input type="hidden" name="id" value="${i.key.productID}">
                                            <input min="1" type="number" max="${i.key.quantity}" name="quantity" value="${i.value}" onchange="this.form.submit()">
                                        </form>
                                    </td>
                                    <td>${i.key.productPrice * i.value}</td>
                                    <td><a href="Delete?id=${i.key.productID}">Delete</a></td>
                                </tr> 
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                <form class="col-md-12" action="checkout" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="total">Tổng tiền</label>
                            <input type="number" class="form-control" value="${requestScope.total}" id="total" name="total" readonly="">
                        </div>
                        <c:if test="${requestScope.voucher != null}">
                            <div class="form-group col-md-6">
                                <label for="voucher">Giảm giá</label>
                                <input type="hidden" value="${requestScope.voucher.voucherID}" name="voucherId">
                                <input type="text" class="form-control" value="${requestScope.voucher.value}" id="voucher" name="voucher" readonly="">
                            </div>
                        </c:if>
                        <div class="form-group col-md-6">
                            <label for="pay">Tổng tiền Thanh Toán</label>
                            <input type="number" class="form-control" value="${requestScope.total - (requestScope.voucher != null ? requestScope.voucher.value : 0)}" id="pay" name="pay" readonly="">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="receive">Khách trả</label>
                            <input type="number" class="form-control" id="receive" name="receive" onchange="shipping()">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="ship">Tiền thừa</label>
                            <input type="number" class="form-control" id="ship" name="ship" readonly="">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="customername">Tên khách hàng</label>
                            <input type="text" class="form-control" id="customername" name="name" >
                        </div>
                        <div class="form-group col-md-6">
                            <label for="phone">Số điện thoại</label>
                            <input type="number" class="form-control" id="phone" name="phone">
                        </div>
                        <div id="message" style="color: red"></div>
                    </div>
                    <button type="submit" class="btn btn-primary">Thanh toán</button>
                </form>
            </div>
        </main>
        <script src="./js/jquery-3.2.1.min.js"></script>
        <script src="./js/popper.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="src/jquery.table2excel.js"></script>
        <script src="./js/main.js"></script>
        <!-- The javascript plugin to display page loading on top-->
        <script src="js/plugins/pace.min.js"></script>
        <!-- Page specific javascripts-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <!-- Data table plugin-->
        <script type="text/javascript" src="./js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="./js/plugins/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript">

                                $('#sampleTable').DataTable();
                                //Thời Gian
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

                                function shipping() {
                                    var total = Number($('#pay').val());
                                    var receive = Number($('#receive').val());
                                    if (total > receive) {
                                        $('#message').html('Chưa thanh toán đủ số tiền cần thiết')
                                        $('form :submit').attr("disabled", "disabled");
                                    } else {
                                        $('#message').html('')
                                        $('form :submit').removeAttr('disabled');
                                        $('#ship').val(receive - total)
                                    }
                                }
        </script>
    </body>
</html>
