

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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

    </head>

    <body onload="time()" class="app sidebar-mini rtl">
        <jsp:include page="headerAdmin.jsp"/>
        <main class="app-content">
            <div class="app-title"> 
                <ul class="app-breadcrumb breadcrumb side">
         <li class="breadcrumb-item"><a href="WorkSheet_show">Danh sách ca làm việc </a></li>
                    <li class="breadcrumb-item"><a href="#"> Tìm kiếm</a></li>
                    <li class="breadcrumb-item"><a href="#"> Thay đổi NV2 theo checkbox</a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">

                    <div class="tile">
                        <div class="tile-body">
<!--                            <div class="row element-button">-->
                                <div class="col-sm-2">
                                    
                         
                                </div>
                                <table class="table table-hover table-bordered" id="sampleTable">
                                    <thead>
                                        <tr>
<!--                                            <th width="10"><input type="checkbox" id="all"></th>-->
                                            <th>ID nhân viên</th>
                                            <th>Tên nhân viên</th>
                                            <th>Ảnh</th>
                                            <th>Địa chỉ</th>
                                            <th>Ngày Sinh</th>
                                            <th>Giới tính</th>
                                            <th>SĐT</th>
                                            <th>Chức vụ</th>
                                            <th>Tình Trạng</th>
                                            <th>Chọn nhân viên</th>
                                        </tr>
                                    </thead>

                                    <tbody>                                        
                                        <c:forEach items="${LIST}" var="i">
                                            <tr>
<!--                                                <td width="10"><input type="checkbox" name="check1" value="1"></td>-->
                                              
                <td> ${i.employeeID}</td>


                                                <td>${i.employeename}</td>
                                                <td><img src="${i.employeeavatar}" alt="" width="100px;"></td>
                                                <td>${i.employeeaddress}</td>
                                                <td>${i.birthday}</td>
                                                <td>
                                                    <c:if test="${i.employeesex ==1}">
                                                        <span >Nam</span>
                                                    </c:if>
                                                    <c:if test="${i.employeesex ==2}">
                                                        <span >Nữ</span>
                                                    </c:if>
                                                </td>
                                                <td>${i.employeephone}</td>
                                                <td>${i.emprole.name}</td>
                                                <td>
                                                    <c:if test="${i.employeestatus == true}">
                                                        <span class="badge bg-success">Đang làm</span>
                                                    </c:if>
                                               

                                                </td>
                                                <td>
                                                    
                                             
               <c:set var="shiftIdList" value="${shiftIdList}" />
<c:forEach items="${shiftIdList}" var="shiftId">
    <%-- Sử dụng shiftId tại đây --%>
    <input type="hidden" name="" value="${shiftId}" />
    
</c:forEach>
    
                <form style="width: 40px;" action="WorkSheet_change_sl_add" method="POST">
    <button class="btn btn-primary btn-sm edit" type="submit" title="Thêm nhân viên này" name="employeeid" value="${i.employeeID}">
        <i class="fas fa-check"></i>
    </button>
    <input type="hidden" name="shiftIdList" value="${shiftIdList}" />
    <input type="hidden" name="employeerole" value="${i.emprole.name}" />
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
                                    <h5>Chỉnh sửa thông tin nhân viên cơ bản</h5>
                                </span>
                            </div>
                        </div>

                        <form action="updateuser" method="POST" id="updateusers">
                            
                        </form>

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
                        </script>
                       <script>
    function confirmDelete(event) {
        event.preventDefault();
        var form = event.target.closest("form");
        var employeeID = form.querySelector("input[name='employeeID']").value;

        swal({
            title: "Cảnh báo",
            text: "Bạn có muốn xoá nhân viên ra khỏi lịch làm việc này?",
            buttons: ["Hủy bỏ", "Đồng ý"],
        }).then((willDelete) => {
            if (willDelete) {
                var redirectURL = "user";
                var deleteURL = "deleteuser?employeeID=" + employeeID + "&redirect=" + encodeURIComponent(redirectURL);
                fetch(deleteURL, { method: "POST" })
                    .then(function (response) {
                        if (response.ok) {
                            // Xóa thành công, hiển thị thông báo và tải lại trang
                            swal("Thành công", "Xóa nhân viên thành công", "success")
                                .then(() => {
                                    window.location.href = redirectURL;
                                });
                        } else {
                            // Xử lý lỗi hoặc hiển thị thông báo không thành công nếu cần thiết
                            swal("Lỗi", "Xóa không thành công.", "error");
                        }
                    })
                    .catch(function (error) {
                        // Xử lý lỗi hoặc hiển thị thông báo không thành công nếu cần thiết
                        swal("Lỗi", "Xóa không thành công.", "error");
                    });
            }
        });
    }
</script>


                        <script>
                            function deleteRow(r) {
                                var i = r.parentNode.parentNode.rowIndex;
                                document.getElementById("myTable").deleteRow(i);
                            }

                            function isDelete(i) {
                                var check = confirm('Do you want to delete user with id = ' + i);
                                if (check) {
                                    window.window.location.href = '${pageContext.request.contextPath}/deleteuser?id=' + i;
                                }
                            }
//                            jQuery(function () {
//                                jQuery(".trash").click(function () {
//                                    swal({
//                                        title: "Cảnh báo",
//                                        text: "Bạn có chắc chắn là muốn xóa nhân viên này?",
//                                        buttons: ["Hủy bỏ", "Đồng ý"],
//                                    })
//                                            .then((willDelete) => {
//                                                if (willDelete) {
//                                                    swal("Đã xóa thành công.!", {
//
//                                                    });
//                                                }
//                                            });
//                                });
//                            }
//                            );
                            oTable = $('#sampleTable').dataTable();
                            $('#all').click(function (e) {
                                $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
                                e.stopImmediatePropagation();
                            });

                            function getData(i) {
                                $.ajax({
                                    type: 'GET',
                                    url: '${pageContext.request.contextPath}/loadUser',
                                    data: {
                                        id: i
                                    },
                                    success: function (data, textStatus, jqXHR) {
                                        $('#updateusers').html(data);
                                    }
                                })
                            }
                        </script>
                        </body>

                        </html>
