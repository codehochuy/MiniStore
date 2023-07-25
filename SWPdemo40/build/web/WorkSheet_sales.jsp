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
        <jsp:include page="headerSales.jsp"/>

        <main class="app-content">
            <div class="app-title"> 
                <ul class="app-breadcrumb breadcrumb side">
                    <li class="breadcrumb-item active"><a href="WorkSheet_show_sales"><b>Danh sách ca làm việc </b></a></li>
                </ul>
                <div id="clock"></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="tile-body">
                            <div class="row element-button">
                                <div class="col-sm-2">
                            <div class="col-sm-2">
                                    <form action="WorkSheet_info_sales" method="POST">
                                        <button class="btn btn-add btn-sm" >
                                            <i class="fas fa-eye"></i> Thông tin cá nhân
                                        </button>
                                            <input type="hidden" name="ID"value="<%= session.getAttribute("ID") %>" />

                                    </form>
                                </div>
                                </div>

         
                                <div class="col-sm-2">
                                     <form action="WorkSheet_search_sales" method="POST">
                                   
                                        <button class="btn btn-add btn-sm" type ="submit" >

                                            <i class="fas fa-search"></i> Tìm kiếm lịch làm việc
                                        </button>
                                            <input type="hidden" name="id"value="<%= session.getAttribute("ID") %>" />

                                </div>
                                            <div class="col-sm-2"></div>
                                <div>
                                    <label for="Start Date">Từ ngày</label>
                                    <input type="date" name="startdate" style="width: 130px;">

                                </div>
  <div class="col-sm-2"></div>
                                <div>
                                    <label for="End Date">Đến ngày </label>
                                    <input type="date" name="enddate" style="width: 130px;">

                                </div>
                                </form>    

                            </div>
                            <div>
                             
                            </div>

                        </div>



                        <table class="table table-hover table-bordered" id="sampleTable">

                            <thead>

                                <tr>
<!--                                    <th width="10"><input type="checkbox" id="all"></th>-->

<!--                                    <th> ID</th>-->
    <th>Ngày </th>
      <th>Thứ </th>
                                    <th> Ca làm việc</th>
                                    <th> Thời gian</th>
                                  
                                
<!--                                     <th>Tên nhân viên </th>
                                      <th>Ảnh thẻ nhân viên 1 </th>
                                       <th>Ảnh thẻ nhân viên 2 </th>-->
                                    <th>Hệ số lương</th>
                     
                                </tr>
                            </thead>


                            <tbody>    
                              
                                <c:forEach items="${requestScope.LIST}" var="i">
                    
                                    <tr>


<td>${i.startdate}</td>
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
                                
                                                
                                                
                 <%--                                 <td>
                                                <c:if test="${i.worksheetemployeefirstdto.id == 0}">
                                                    <span class="badge bg-danger">Chưa có nhân viên 1 </span>
                                                </c:if>
                                             <a href="#" name ="" style="color: #3366ff;" >${i.worksheetemployeefirstdto.name} </a>


                                           




                                                <br>
                                                <c:if test="${i.worksheetemployeeseconddto.id == 0}">
                                                    <span class="badge bg-danger">Chưa có nhân viên 2</span>
                                                </c:if>
                                          <a href="#" name ="" style="color: #3366ff;" >${i.worksheetemployeeseconddto.name} </a>
                                            </td>
                                              <td><img src="${i.worksheetemployeefirstdto.avatar}" alt="" width="100px;"></td>
                                            <td><img src="${i.worksheetemployeeseconddto.avatar}" alt="" width="100px;"></td>
--%>
                                         <td>${i.coefsalary}</td>
                                    

                                        <!-- Các trường dữ liệu khác trong biểu mẫu -->
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



</body>

</html>

