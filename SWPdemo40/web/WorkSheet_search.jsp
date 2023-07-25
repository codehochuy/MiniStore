<%-- 
    Document   : WorkSheet
    Created on : May 31, 2023, 4:56:15 PM
    Author     : Vuong
--%>
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
        <link href='https://unpkg.com/css.gg@2.0.0/icons/css/search.css' rel='stylesheet'>


    </head>
    <body onload="time()" class="app sidebar-mini rtl">
        <jsp:include page="headerAdmin.jsp"/>
        <main class="app-content">
            <div class="app-title"> 
                <ul class="app-breadcrumb breadcrumb side">
          <li class="breadcrumb-item"><a href="WorkSheet_show">Danh sách ca làm việc </a></li>
             <li class="breadcrumb-item"><a href="#">Tìm kiếm</a></li>
                 
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">

                                
                          <div class="col-sm-2"></div>
                   <button id="addfirst" class="btn btn-sm btn-primary" type="submit" title="" name="" value=""><i class="fas fa-edit"></i>Thay đổi NV1 theo checkbox</button>
                         
                   <div class="col-sm-2"></div>
                    <button id="addsecond" class="btn btn-sm btn-primary" type="submit" title="" name="" value=""><i class="fas fa-edit"></i>Thay đổi NV2 theo checkbox</button>
                   
                                   
</div>


                            <table class="table table-hover table-bordered" id="sampleTable">
 <thead>
<tr>
                                                                            <th width="10"><input type="checkbox" id="all"></th>

                                        <th> ID</th>
                                          <th> Ngày</th>
                                              <th> Thứ</th>
                                        <th>Ca  </th>
                                        <th> Thời gian</th>
                                    
                                       
                                        <th> Chức vụ</th>
                                        
<th>Tên nhân viên </th>
                                        <th>Ảnh thẻ NV 1</th>
                                        <th>Ảnh thẻ NV 2</th>
                                        <th>HSL</th>
<th> Chức năng</th>
</tr>
                                </thead>

                                <tbody>                                        
                                    <c:forEach items="${requestScope.LIST}" var="i">
    <tr>
        <td width="10"><input type="checkbox" name="checkbox" value="${i.shiftid}"></td>
        
        
       <td>${i.shiftid}</td>
          <td>
                                                 ${i.startdate} 
                                               


                                            </td>
                                                  <td>
                                                <c:choose>
                                                    <c:when test="${i.dayofweekfirst == 1}">
                                                        <span >Chủ nhật</span>
                                                    </c:when>
                                                    <c:when test="${i.dayofweekfirst == 2}">
                                                        <span >Thứ 2</span>
                                                    </c:when>
                                                    <c:when test="${i.dayofweekfirst == 3}">
                                                        <span >Thứ 3</span>
                                                    </c:when>
                                                    <c:when test="${i.dayofweekfirst == 4}">
                                                        <span >Thứ 4</span>
                                                    </c:when>
                                                    <c:when test="${i.dayofweekfirst == 5}">
                                                        <span >Thứ 5</span>
                                                    </c:when>
                                                    <c:when test="${i.dayofweekfirst == 6}">
                                                        <span >Thứ 6</span>
                                                    </c:when>
                                                    <c:when test="${i.dayofweekfirst == 7}">
                                                        <span >Thứ 7</span>
                                                    </c:when>
                                                </c:choose>
                                                </td>
                                                
                                            
         <td>
                                                <c:choose>
                                                    <c:when test="${i.shiftname == 'Slot 1'}">
                                                        <span >Ca 1</span>
                                                    </c:when>
                                                        <c:when test="${i.shiftname == 'Slot 2'}">
                                                        <span >Ca 2</span>
                                                    </c:when>
                                                        <c:when test="${i.shiftname == 'Slot 3'}">
                                                        <span >Ca 3</span>
                                                    </c:when>
                                                        </c:choose>
                                            </td>
                                              <td>${i.starttime} - ${i.endtime}</td>
                                          
                                             
        <td>
            <c:if test="${i.employeerole == 2}">
                Thu ngân
            </c:if>
            <c:if test="${i.employeerole == 3}">
                Bảo vệ
            </c:if>
        </td>
      
                                          



                                            




                                            <td>
                                                <c:if test="${i.worksheetemployeefirstdto.id == 0}">
                                                    <span class="badge bg-danger">Chưa có nhân viên 1</span>
                                                </c:if>
                                                <a href="WorkSheet_showdetail?id=${i.worksheetemployeefirstdto.id}" name ="id" style="color: #3366ff;" >${i.worksheetemployeefirstdto.name} </a>

                                                




                                                <br>
                                                <c:if test="${i.worksheetemployeeseconddto.id == 0}">
                                                    <span class="badge bg-danger">Chưa có nhân viên 2</span>
                                                </c:if>
                                                <a href="WorkSheet_showdetail?id=${i.worksheetemployeeseconddto.id}" name ="id" style="color: #3366ff;" >${i.worksheetemployeeseconddto.name} </a>
                                            </td>





                                            <td><img src="${i.worksheetemployeefirstdto.avatar}" alt="" width="100px;"></td>
                                            <td><img src="${i.worksheetemployeeseconddto.avatar}" alt="" width="100px;"></td>
<td>${i.coefsalary}</td>

                                            <td style="display: flex; justify-content: space-between">


                                               
                                                    
                                                <form action="WorkSheet_delete_search" method="GET">
                                   
                                    <button class="btn btn-primary btn-sm trash" title="" type = "submit"><i class="fas fa-trash-alt"></i>
                                         </button>
                                        <input type="hidden" name="shiftid" value="${i.shiftid}" />
                                         <input type="hidden" name="employeerole" value="${i.employeerole}" />
                                         <input type="hidden" name="employeeidfirst" value="${i.worksheetemployeefirstdto.id}" />
                                         
                                         </form>
                                                


                                                <form action="WorkSheet_delete_search" method="POST">
                                   
                                    <button class="btn btn-primary btn-sm trash" title="" type = "submit"><i class="fas fa-trash-alt"></i>
                                         </button>
                                        <input type="hidden" name="shiftid" value="${i.shiftid}" />
                                         <input type="hidden" name="employeerole" value="${i.employeerole}" />
                                         <input type="hidden" name="employeeidsecond" value="${i.worksheetemployeeseconddto.id}" />
                                         </form>
                                         
                                         

 
   
                        <button class="btn btn-primary btn-sm edit" type="button" title="Chỉnh sửa hệ số lương" id="show-emp" data-toggle="modal"
    data-target="#ModalUP" onclick="getData('${i.shiftid}', '${i.employeerole}')">
    <i class="fas fa-edit"></i>
</button>

  
                                       

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
                                    <h5>Điều chỉnh hệ số lương </h5>
                                </span>
                            </div>
                        </div>
                   
                        <form action="WorkSheet_update" method="POST" id="update">
 
   
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
        <script type="text/javascript" src="./js/plugins/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="./js/plugins/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript">

                                                            $(document).ready(function() {
    $('#sampleTable').DataTable({
        "paging": true,             // Hiển thị phân trang
        "pageLength": 100,           // Số lượng danh mục trên mỗi trang
        // Các tùy chọn khác nếu cần thiết
    });
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
            function submitWorksheetForm() {
                document.getElementById('worksheetForm').submit();
            }
        </script>


        <script>
            function confirmDelete(button) {
                var shiftid = button.getAttribute("data-shiftid");
                var employeeid = button.getAttribute("data-employeeid");

                swal({
                    title: "Cảnh báo",
                    text: "Bạn có muốn xoá nhân viên ra khỏi lịch làm việc này?",
                    buttons: ["Hủy bỏ", "Đồng ý"],
                }).then((willDelete) => {
                    if (willDelete) {
                        var form = button.closest("form");
                        form.action = "WorkSheet_delete?shiftid=" + shiftid + "&employeeid=" + employeeid;
                        form.submit();
                    }

                });
            }
        </script>


        <script>
            function confirmDeleteall(button) {
                var shiftid = button.getAttribute("data-shiftid");
                swal({
                    title: "Cảnh báo",
                    text: "Bạn có muốn xoá ShiftID này?",
                    buttons: ["Hủy bỏ", "Đồng ý"],
                }).then((willDelete) => {
                    if (willDelete) {
                        var form = button.closest("form");
                        form.action = "WorkSheet_deleteall?shiftid=" + shiftid;
                        form.submit();
                    }
                });
            }
        </script>
        <script>
            <% if (request.getAttribute("message") != null) {%>
            swal("<%= request.getAttribute("message")%>", "", "success");
            <% request.removeAttribute("message"); %>
            <% } %>
        </script>

        <script>
            <% if (request.getAttribute("messageerror") != null) {%>
            swal("<%= request.getAttribute("messageerror")%>", "", "error");
            <% request.removeAttribute("messageerror"); %>
            <% } %>
        </script>

        <script>
            <% if (request.getAttribute("messagewarning") != null) {%>
            swal("<%= request.getAttribute("messagewarning")%>", "", "warning");
            <% request.removeAttribute("messagewarning"); %>
            <% } %>
        </script>

        <script>
            <% if (request.getAttribute("messageinfo") != null) {%>
            swal("<%= request.getAttribute("messageinfo")%>", "", "info");
            <% request.removeAttribute("messageinfo"); %>
            <% }%>
        </script>






      <script>
    function getData(shiftId, employeerole) {
        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/WorkSheet_load',
            data: {
                id: shiftId,
                employeerole: employeerole
            },
            success: function (data, textStatus, jqXHR) {
                $('#update').html(data);
            }
        })
    }
</script>

        <script>
            $('#all').click(function (e) {
                $('#sampleTable tbody :checkbox').prop('checked', $(this).is(':checked'));
                e.stopImmediatePropagation();
            });
        </script>
        
        <script>
    document.getElementById('addfirst').addEventListener('click', function() {
        var checkboxes = document.getElementsByName('checkbox');
        var selectedShiftIds = [];

        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                selectedShiftIds.push(checkbox.value);
            }
        });

        var addfirst = document.getElementById('addfirst');
        addfirst.value = selectedShiftIds.join('');

        // Tạo form và gửi đến WorkSheet_addmorefirstServlet bằng phương thức POST
        var form = document.createElement('form');
        form.method = 'GET';
        form.action = 'WorkSheet_change_sl'; // Đặt action tương ứng với URL của servlet

        // Tạo input hidden để chứa danh sách shiftid đã chọn
        var inputShiftIds = document.createElement('input');
        inputShiftIds.type = 'hidden';
        inputShiftIds.name = 'selectedShiftIds';
        inputShiftIds.value = selectedShiftIds.join(',');
        form.appendChild(inputShiftIds);

        // Lấy giá trị employeerole từ server-side
        var employeerole = '<%= request.getAttribute("employeerole") %>';

        // Tạo input hidden để chứa giá trị employeerole
        var inputEmployeeRole = document.createElement('input');
        inputEmployeeRole.type = 'hidden';
        inputEmployeeRole.name = 'employeerole';
        inputEmployeeRole.value = employeerole;
        form.appendChild(inputEmployeeRole);

        // Gắn form vào body và gửi form
        document.body.appendChild(form);
        form.submit();
    });
</script>


 <script>
    document.getElementById('addsecond').addEventListener('click', function() {
        var checkboxes = document.getElementsByName('checkbox');
        var selectedShiftIds = [];

        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                selectedShiftIds.push(checkbox.value);
            }
        });

        var addsecond = document.getElementById('addsecond');
        addsecond.value = selectedShiftIds.join('');

        // Tạo form và gửi đến WorkSheet_addmorefirstServlet bằng phương thức POST
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = 'WorkSheet_change_sl'; // Đặt action tương ứng với URL của servlet

        // Tạo input hidden để chứa danh sách shiftid đã chọn
        var inputShiftIds = document.createElement('input');
        inputShiftIds.type = 'hidden';
        inputShiftIds.name = 'selectedShiftIds';
        inputShiftIds.value = selectedShiftIds.join(',');
        form.appendChild(inputShiftIds);

        // Lấy giá trị employeerole từ server-side
        var employeerole = '<%= request.getAttribute("employeerole") %>';

        // Tạo input hidden để chứa giá trị employeerole
        var inputEmployeeRole = document.createElement('input');
        inputEmployeeRole.type = 'hidden';
        inputEmployeeRole.name = 'employeerole';
        inputEmployeeRole.value = employeerole;
        form.appendChild(inputEmployeeRole);

        // Gắn form vào body và gửi form
        document.body.appendChild(form);
        form.submit();
    });
</script>



        <script>
            <% if (request.getAttribute("messageerror") != null) {%>
            swal("<%= request.getAttribute("messageerror")%>", "", "error");
            <% request.removeAttribute("messageerror"); %>
            <% } %>
        </script>






    </body>
      
</html>

