<%-- 
    Document   : ManageRevunue
    Created on : May 27, 2023, 2:11:14 PM
    Author     : kienb
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Danh sách nhân viên | Quản trị Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <link href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
        <link href="https://cdn.datatables.net/datetime/1.1.2/css/dataTables.dateTime.min.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" type="text/css"
              href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <script type="text/javascript" src="scripts/jquery.formatCurrency.js"></script>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
            }
        </style>
    </head>

    <body onload="time()" class="app sidebar-mini rtl">
        <jsp:include page="headerAdmin.jsp"/>
        <main class="app-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="app-title">
                        <ul class="app-breadcrumb breadcrumb">
                            <li class="breadcrumb-item"><a href="#"><b>Dashboard</b></a></li>
                        </ul>
                        <div id="clock"></div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-lg-4">
                    <div class="widget-small primary coloured-icon"><i class='icon  bx bxs-user fa-3x'></i>
                        <div class="info">
                            <h4>Tổng Nhân viên</h4>
                            <p><b>${requestScope.data.empTotal} nhân viên</b></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4">
                    <div class="widget-small info coloured-icon"><i class='icon bx bxs-purchase-tag-alt fa-3x' ></i>
                        <div class="info">
                            <h4>Tổng sản phẩm</h4>
                            <p><b>${requestScope.data.proTotal} sản phẩm</b></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4">
                    <div class="widget-small warning coloured-icon"><i class='icon fa-3x bx bxs-shopping-bag-alt'></i>
                        <div class="info">
                            <h4>Tổng đơn hàng</h4>
                            <p><b>${requestScope.data.listOrder.size()} đơn hàng</b></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-lg-4">
                    <div class="widget-small primary coloured-icon"><i class='icon fa-3x bx bxs-chart' ></i>
                        <div class="info">
                            <h4>Tổng thu nhập</h4>
                            <p><b><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${requestScope.totalMoney}" /> đ</b></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4">
                    <div class="widget-small warning coloured-icon"><i class='icon fa-3x bx bxs-tag-x' ></i>
                        <div class="info">
                            <h4>Hết hàng</h4>
                            <p><b>${requestScope.data.proList.size()} sản phẩm</b></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4">
                    <div class="widget-small warning coloured-icon"><i class='icon fa-3x bx bxs-tag-x' ></i>
                        <div class="info">
                            <h4>Nhân viên đã nghỉ</h4>
                            <p><b>${requestScope.data.emplist.size()} sản phẩm</b></p>
                        </div>
                    </div>
                </div>
            </div>
            <!--            <div class="row">
                            <div class="col-md-12">
                                <div class="tile">
                                    <div>
                                        <h3 class="tile-title">TỔNG ĐƠN HÀNG</h3>
                                    </div>
                                    <div class="tile-body">
                                        <table border="0" cellspacing="5" cellpadding="5">
                                            <tbody><tr>
                                                    <td>Minimum date: <input type="date" id="min" name="min"></td>
                                                    <td>Maximum date: <input type="date" id="max" name="max"></td>
                                                </tr>
                                            </tbody></table>
                                        <table class="table table-hover table-bordered" id="data">
                                            <thead>
                                                <tr>
                                                    <th>ID đơn hàng</th>
                                                    <th>Khách hàng</th>
                                                    <th>Ngày tạo đơn</th>
                                                    <th>Số lượng</th>
                                                    <th>Tổng tiền</th>
                                                </tr>
                                            </thead>
                                            <tbody>
            <c:forEach items="${requestScope.data.listOrder}" var="i">
                <tr>
                    <td>${i.orderId}</td>
                    <td>${i.customerName}</td>
                    <td>${i.getFormattedDate()}</td>
                    <td>${i.totalProduct} sản phẩm</td>
                    <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.total}" /> đ</td>
                </tr>
            </c:forEach>
        <tfoot>
            <tr>
                <td colspan="4">Tổng cộng:</td>
                <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${requestScope.totalMoney}" /> đ</td>
            </tr>
        </tfoot>
        </tbody>
    </table>
</div>
</div>
</div>
</div>-->
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div>
                            <h3 class="tile-title">SẢN PHẨM ĐÃ HẾT</h3>
                        </div>
                        <div class="tile-body">
                            <table class="table table-hover table-bordered" id="sampleTable4">
                                <thead>
                                    <tr>
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Ảnh</th>
                                        <!--                                        <th>Số lượng</th>-->
                                        <th>Tình trạng</th>
                                        <th>Giá tiền</th>
                                        <th>Danh mục</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.data.proList}" var="i">
                                        <tr>
                                            <td>${i.productID}</td>
                                            <td>${i.productName}</td>
                                            <td><img src="${i.productLink}" alt="" width="100px;"></td>
<!--                                            <td>${i.quantity}</td>-->
                                            <c:if test="${i.quantity gt 0}">
                                                <td><span class="badge bg-success">Còn hàng</span></td>
                                            </c:if>
                                            <c:if test="${i.quantity le 0}">
                                                <td><span class="badge bg-danger">Hết hàng</span></td> 
                                            </c:if>
                                            <td><fmt:formatNumber value="${i.productPrice}" type="currency"/></td>
                                            <td>${i.category.name}</td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div>
                            <h3 class="tile-title">SẢN PHẨM ĐÃ BÁN</h3>
                        </div>
                        <div class="tile-body">
                            <table class="table table-hover table-bordered" id="sampleTable5">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Ảnh</th>
                                        <th>Tình trạng</th>
                                        <th>Giá tiền</th>
                                        <th>Danh mục</th>
                                        <th>Số lượng đã bán</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.data.list}" var="i" varStatus="status">
                                        <tr>
                                            <td>${status.index + 1}</td>
                                            <td>${i.productID}</td>
                                            <td>${i.productName}</td>
                                            <td><img src="${i.productLink}" alt="" width="100px;"></td>
                                                <c:if test="${i.quantity gt 0}">
                                                <td><span class="badge bg-success">Còn hàng</span></td>
                                            </c:if>
                                            <c:if test="${i.quantity le 0}">
                                                <td><span class="badge bg-danger">Hết hàng</span></td> 
                                            </c:if>
                                            <td><fmt:formatNumber value="${i.productPrice}" type="currency"/></td>
                                            <td>${i.category.name}</td>
                                            <td>${i.quatity2}</td>
                                        </tr>
                                    </c:forEach>
                                       
                                         
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div>
                            <h3 class="tile-title">NHÂN VIÊN ĐÃ NGHỈ LÀM</h3>
                        </div>
                        <div class="tile-body">
                            <div class="table-responsive">
                                <table class="table table-hover table-bordered" id="sampleTable3">
                                    <thead>
                                        <tr>
                                            <th>ID nhân viên</th>
                                            <th>Tên nhân viên</th>
                                            <th>Ảnh</th>
                                            <th>Địa chỉ</th>
                                            <th>Ngày Sinh</th>
                                            <th>Giới tính</th>
                                            <th>SĐT</th>
                                            <th>Chức vụ</th>
                                            <th>Tình Trạng</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.data.emplist}" var="i">
                                            <tr>
                                                <td>${i.employeeID}</td>
                                                <td>${i.employeename}</td>
                                                <td><img src="${i.employeeavatar}" alt="" width="100px;"></td>
                                                <td>${i.employeeaddress}</td>
                                                <td>${i.birthday}</td>
                                                <td>
                                                    <c:if test="${i.employeesex ==1}">
                                                        <span>Nam</span>
                                                    </c:if>
                                                    <c:if test="${i.employeesex ==2}">
                                                        <span>Nữ</span>
                                                    </c:if>
                                                </td>
                                                <td>${i.employeephone}</td>
                                                <td>${i.emprole.name}</td>
                                                <td>
                                                    <c:if test="${i.employeestatus == true}">
                                                        <span class="badge bg-success">Đang làm</span>
                                                    </c:if>
                                                    <c:if test="${i.employeestatus == false}">
                                                        <span class="badge bg-danger">Đã nghỉ</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="tile">
                        <h3 class="tile-title">DỮ LIỆU HÀNG THÁNG</h3>
                        <div class="embed-responsive embed-responsive-16by9">
                            <canvas class="embed-responsive-item" id="lineChartDemo"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="tile">
                        <h3 class="tile-title">THỐNG KÊ DOANH SỐ</h3>
                        <div class="embed-responsive embed-responsive-16by9">
                            <canvas class="embed-responsive-item" id="barChartDemo"></canvas>
                        </div>
                    </div>
                </div>
            </div>


        </main>
        <!-- Essential javascripts for application to work-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <!-- The javascript plugin to display page loading on top-->
        <script src="js/plugins/pace.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <!-- Data table plugin-->
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.2/moment.min.js"></script>
        <script src="https://cdn.datatables.net/datetime/1.1.2/js/dataTables.dateTime.min.js"></script>
        <script type="text/javascript" src="./js/plugins/dataTables.bootstrap.min.js"></script>

        <!-- Page specific javascripts-->
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
        </script>
        <script type="text/javascript" src="js/plugins/chart.js"></script>
        <script type="text/javascript">
        var data = {
            labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
            datasets: [{
                    label: "Dữ liệu đầu tiên",
                    fillColor: "rgba(255, 255, 255, 0.158)",
                    strokeColor: "black",
                    pointColor: "rgb(220, 64, 59)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "green",
                    data: [20, 59, 90, 51, 56, 100, 40, 60, 78, 53, 33, 81]
                },
                {
                    label: "Dữ liệu kế tiếp",
                    fillColor: "rgba(255, 255, 255, 0.158)",
                    strokeColor: "rgb(220, 64, 59)",
                    pointColor: "black",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "green",
                    data: [48, 48, 49, 39, 86, 10, 50, 70, 60, 70, 75, 90]
                }
            ]
        };


        var ctxl = $("#lineChartDemo").get(0).getContext("2d");
        var lineChart = new Chart(ctxl).Line(data);

        var ctxb = $("#barChartDemo").get(0).getContext("2d");
        var barChart = new Chart(ctxb).Bar(data);
        </script>
        <!-- Google analytics script-->
        <script type="text/javascript">
            if (document.location.hostname == 'pratikborsadiya.in') {
                (function (i, s, o, g, r, a, m) {
                    i['GoogleAnalyticsObject'] = r;
                    i[r] = i[r] || function () {
                        (i[r].q = i[r].q || []).push(arguments)
                    }, i[r].l = 1 * new Date();
                    a = s.createElement(o),
                            m = s.getElementsByTagName(o)[0];
                    a.async = 1;
                    a.src = g;
                    m.parentNode.insertBefore(a, m)
                })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
                ga('create', 'UA-72504830-1', 'auto');
                ga('send', 'pageview');
            }
        </script>
        <script>
            $('#data').dataTable();
            var minDate, maxDate;

            // Custom filtering function which will search data in column four between two values
            $.fn.dataTable.ext.search.push(
                    function (settings, data, dataIndex) {
                        var min = (minDate.val() ? new Date(minDate.val()) : null);
                        var max = (maxDate.val() ? new Date(maxDate.val()) : null);
                        var date = new Date(data[2]);

                        if (
                                (min === null && max === null) ||
                                (min === null && date <= max) ||
                                (min <= date && max === null) ||
                                (min <= date && date <= max)
                                ) {
                            return true;
                        }
                        return false;
                    }
            );

            $(document).ready(function () {
                // Create date inputs
                minDate = $('#min');
                maxDate = $('#max');

                // DataTables initialisation
                var table = $('#data').DataTable();

                // Refilter the table
                $('#min, #max').on('change', function () {
                    table.draw();
                });
            });
        </script>
        <script>
            $(document).ready(function () {
                $('#sampleTable3').DataTable();
            });
        </script>
        <script>
            $(document).ready(function () {
                $('#sampleTable4').DataTable();
            });
        </script>
        <script>
            $(document).ready(function () {
                $('#sampleTable5').DataTable();
            });
        </script>




    </body>

</html>
