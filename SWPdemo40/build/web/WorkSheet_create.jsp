

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title></title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
        <!-- Font-icon css-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <!-- or -->
    </head>

    <body class="app sidebar-mini rtl">

        <jsp:include page="headerAdmin.jsp"/>
        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                 <li class="breadcrumb-item"><a href="WorkSheet_show">Danh sách ca làm việc </a></li>
                 <li class="breadcrumb-item"><a href="WorkSheet_create.jsp">Tạo lịch làm việc</a></li>
               
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <div class="row element-button">
                            
                              <form class="col-sm-2" action="WorkSheet_import.jsp">
                                    <button class="btn btn-add btn-sm">
                                        <i class="fas fa-plus"></i> Nhập nhân viên làm việc
                                    </button>
                                </form>

                        </div>
                        <h3 class="tile-title">Tạo lịch làm việc</h3>
                        <div class="tile-body">


                            <form action="WorkSheet_create" method="POST">
                                <div class="form-group col-md-4">
                                    <label class="control-label">Từ ngày</label>
                                    <input class="form-control" type="date" name="startdate" >
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label">Đến ngày</label>
                                    <input class="form-control" type="date" name="enddate" >
                                </div>
                                <div class="form-group col-md-4" ></div>
<!--                                <h6>Chú ý: Tạo ca làm việc trong 1 tháng </h6>-->
                                <button class="btn btn-save" title="" type="submit">Xác nhận</button>
                                <a class="btn btn-cancel" href="WorkSheet_show">Huỷ bỏ</a>
                            </form>

                            <div class="form-group col-md-10" ></div>
                            <c:if test="${not empty requestScope.Message}">
                                <span style="color: green;">${requestScope.Message}</span>
                            </c:if>



                        </div>
                    </div>
                </div>
            </div>

        </main>


        <!--                    MODAL DANH MỤC-->

    </body>
</html>
