
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Danh sách nhân viên | Quản trị Admin</title>
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
        <script type="text/javascript" src="scripts/jquery.formatCurrency.js"></script>

    </head>

    <body onload="time()" class="app sidebar-mini rtl">
        <jsp:include page="headerAdmin.jsp"/>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="#"><b>Danh sách sản phẩm</b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">
                                <div class="col-sm-2">

                                    <a class="btn btn-add btn-sm" href="createProduct" title="Thêm"><i class="fas fa-plus"></i>
                                        Tạo mới sản phẩm</a>
<!--                                </div>
                                <div class="col-sm-2">
                                    <a class="btn btn-delete btn-sm nhap-tu-file" type="button" title="Nhập" onclick="myFunction(this)"><i
                                            class="fas fa-file-upload"></i> Tải từ file</a>
                                </div>

                                <div class="col-sm-2">
                                    <a class="btn btn-delete btn-sm print-file" type="button" title="In" onclick="myApp.printTable()"><i
                                            class="fas fa-print"></i> In dữ liệu</a>
                                </div>
                                <div class="col-sm-2">
                                    <a class="btn btn-delete btn-sm print-file js-textareacopybtn" type="button" title="Sao chép"><i
                                            class="fas fa-copy"></i> Sao chép</a>
                                </div>

                                <div class="col-sm-2">
                                    <a class="btn btn-excel btn-sm" href="" title="In"><i class="fas fa-file-excel"></i> Xuất Excel</a>
                                </div>
                                <div class="col-sm-2">
                                    <a class="btn btn-delete btn-sm pdf-file" type="button" title="In" onclick="myFunction(this)"><i
                                            class="fas fa-file-pdf"></i> Xuất PDF</a>
                                </div>
                                <div class="col-sm-2">
                                    <a class="btn btn-delete btn-sm" type="button" title="Xóa" onclick="myFunction(this)"><i
                                            class="fas fa-trash-alt"></i> Xóa tất cả </a>
                                </div>-->
                            </div>
                            <table class="table table-hover table-bordered" id="sampleTable">
                                <thead>
                                    <tr>
                                        <th width="10"><input type="checkbox" id="all"></th>
                                        <th>Mã sản phẩm</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Ảnh</th>
                                        <th>Số lượng</th>
                                        <th>Tình trạng</th>
                                        <th>Giá tiền</th>
                                        <th>Danh mục</th>
                                        <th>Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="i">
                                        <tr>
                                            <td width="10"><input type="checkbox" name="check1" value="1"></td>
                                            <td>${i.productID}</td>
                                            <td>${i.productName}</td>
                                            <td><img src="${i.productLink}" alt="" width="100px;"></td>
                                            <td>${i.quantity}</td>
                                            <c:if test="${i.quantity gt 0}">
                                                <td><span class="badge bg-success">Còn hàng</span></td>
                                            </c:if>
                                            <c:if test="${i.quantity le 0}">
                                                <td><span class="badge bg-danger">Hết hàng</span></td> 
                                            </c:if>
                                            <td><fmt:formatNumber value="${i.productPrice}" type="currency"/></td>
                                            <td>${i.category.name}</td>
                                            <td><button class="btn btn-primary btn-sm trash" type="button" onclick="isDelete('${i.productID}')" title="Xóa"
                                                        ><i class="fas fa-trash-alt"></i> 
                                                </button>
                                                <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp" data-toggle="modal"
                                                        data-target="#ModalUP" onclick="getData('${i.productID}')"><i class="fas fa-edit"></i></button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tr>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <!--
          MODAL
        -->
        <div class="modal fade" id="ModalUP" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
             data-keyboard="false">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">

                    <div class="modal-body">
                        <div class="row">
                            <div class="form-group  col-md-12">
                                <span class="thong-tin-thanh-toan">
                                    <h5>Chỉnh sửa thông tin sản phẩm cơ bản</h5>
                                </span>
                            </div>
                        </div>
                        <form action="update" method="POST" id="updatesp">
                            

                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>
        <!--
        MODAL
        -->

        <!-- Essential javascripts for application to work-->
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
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="./js/plugins/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript">
                                                            var table;
                                                            var list = ['checbox', 'productID',
                                                                'productName', 'image',
                                                                'productQuantity',
                                                                'status',
                                                                'productPrice',
                                                                'category'];
                                                            $(document).ready(function () {
                                                                $('#sampleTable thead tr').clone(true)
                                                                        .addClass('filters')
                                                                        .appendTo('#sampleTable thead');
                                                                table = $('#sampleTable').DataTable(
                                                                        {
                                                                            "bDestroy": true,
                                                                            orderCellsTop: true,
                                                                            fixedHeader: true,
                                                                            initComplete: function () {
                                                                                var api = this.api();
                                                                                api
                                                                                        .columns()
                                                                                        .eq(0)
                                                                                        .each(function (colIdx) {
                                                                                            // Set the header cell to contain the input element
                                                                                            if (colIdx === 0 || colIdx === 3) {
                                                                                                var cell = $('.filters th').eq(
                                                                                                        $(api.column(colIdx).header()).index()
                                                                                                        );
                                                                                                $(cell).html('');
                                                                                            }
                                                                                            if (colIdx > 0 && colIdx !== 3)
                                                                                            {
                                                                                                var cell = $('.filters th').eq(
                                                                                                        $(api.column(colIdx).header()).index()
                                                                                                        );
                                                                                                var title = $(cell).text();
                                                                                                $(cell).html('<input type="text" placeholder="' + title + '" \n\
                                                                                                id="' + list[colIdx] + '" style="width: 100px" onchange="searchProduct()"/>');
                                                                                                if (colIdx === 2) {
                                                                                                    $(cell).html('<input type="text" placeholder="' + title + '" \n\
                                                                                                id="' + list[colIdx] + '" style="width: 200px" onchange="searchProduct()"/>');
                                                                                                }
                                                                                                if (colIdx === 5) {
                                                                                                    $(cell).html('<select style="width: 100px; height: 28px" onchange="searchProduct()" id="status"> \n' +
                                                                                                            '<option selected></option>' +
                                                                                                            '<option value="1">Còn hàng</option>' +
                                                                                                            '<option value="2">Hết hàng</option>' +
                                                                                                            '</select>'
                                                                                                            );
                                                                                                }
                                                                                                if (colIdx === 7) {
                                                                                                    var listJson = ${requestScope.listCate};
                                                                                                    var txt = '<select style="width: 100px;height: 28px" onchange="searchProduct()" id="category"> \n';
                                                                                                    txt += '<option selected></option> \n';
                                                                                                    for (let j = 0; j < listJson.length; j++) {
                                                                                                        txt += '<option value="' + listJson[j].id + '">' + listJson[j].name + '</option> \n';
                                                                                                    }
                                                                                                    txt += '</select>';
                                                                                                    $(cell).html(txt);
                                                                                                }
                                                                                                if (colIdx === 8) {
                                                                                                    $(cell).html('<a href="product" style="text-align: center">Đặt lại</a>');
                                                                                                }
                                                                                            }
                                                                                        });
                                                                            }
                                                                        }
                                                                )
                                                            });
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
        <script>
            function deleteRow(r) {
                var i = r.parentNode.parentNode.rowIndex;
                document.getElementById("myTable").deleteRow(i);
            }
            function isDelete(i) {
                var check = confirm('Do you want to delete product with id = ' + i);
                if (check) {
                    window.window.location.href = '${pageContext.request.contextPath}/deleteProduct?id=' + i;
                }
            }
            oTable = $('#sampleTable').dataTable();
            $('#all').click(function (e) {
                $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
                e.stopImmediatePropagation();
            });
            function getData(i) {
                $.ajax({
                    type: 'GET',
                    url: '${pageContext.request.contextPath}/loadProductByID',
                    data: {
                        id: i
                    },
                    success: function (data, textStatus, jqXHR) {
                        $('#updatesp').html(data);
                    }
                })
            }

            var format = function (num) {
                var str = num.toString().replace("$", ""), parts = false, output = [], i = 1, formatted = null;
                if (str.indexOf(".") > 0) {
                    parts = str.split(".");
                    str = parts[0];
                }
                str = str.split("").reverse();
                for (var j = 0, len = str.length; j < len; j++) {
                    if (str[j] != ",") {
                        output.push(str[j]);
                        if (i % 3 == 0 && j < (len - 1)) {
                            output.push(",");
                        }
                        i++;
                    }
                }
                formatted = output.reverse().join("");
                return("$" + formatted + ((parts) ? "." + parts[1].substr(0, 2) : ""));
            };

            function searchProduct() {
                var id = $('#productID').val();
                var name = $('#productName').val();
                var quantity = $('#productQuantity').val();
                var status = $('#status').val();
                var price = $('#productPrice').val();
                var categoryID = $('#category').val();
                $.ajax({
                    type: 'POST',
                    url: '${pageContext.request.contextPath}/product',
                    data: {
                        id: id,
                        name: name,
                        quantity: quantity,
                        status: status,
                        price: price,
                        categoryID: categoryID
                    },
                    dataType: "json",
                    success: function (data, textStatus, jqXHR) {
                        table.clear();
                        for (let idx = 0; idx < data.length; idx++) {
                            table.row.add({
                                0: '<input type="checkbox" name="check1" >',
                                1: data[idx].productID,
                                2: data[idx].productName,
                                3: '<img src="img/' + data[idx].productLink + '" alt="" width="100px;">',
                                
                                4: data[idx].quantity,
                                5: '<span class=\"badge bg-success\">' + (data[idx].quantity > 0 ? 'Còn hàng' : 'Hết hàng') + '</span>',
                                6: format(data[idx].productPrice),
                                7: data[idx].category.name,
                                8: '<button class="btn btn-primary btn-sm trash" type="button" onclick="isDelete(' + data[idx].productID + ')" title="Xóa"><i class="fas fa-trash-alt"></i> ' +
                                        '</button>' +
                                        '<button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp" data-toggle="modal" data-target="#ModalUP" onclick="getData(' + data[idx].productID + ')"><i class="fas fa-edit"></i></button>'
                            })
                        }
                        table.draw(false)
                    }
                })
            }
        </script>
    </body>

</html>
