

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
                 <li class="breadcrumb-item"><a href="WorkSheet_import.jsp">Nhập nhân viên làm việc</a></li>
               
                </ul>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                 
                        <h3 class="tile-title">Nhập nhân viên làm việc</h3>
                        
                           
                        <div class="tile-body">


                            <form action="WorkSheet_import" method="POST">
              
                                <div class="form-group col-md-4">
                                   <label class="control-label">Nhập nhân viên làm việc trong khoảng thời gian</label>
                              
                                    <label class="control-label"></label>
                                    <input class="form-control" type="date" name="startdate" >
                                     <label class="control-label"></label>
                                    <input class="form-control" type="date" name="enddate" >
                                    
                                    <div class="form-group col-md-4"></div>
                              
                                </div>
                                  <div class="form-group col-md-4">
                                         <label class="control-label">Vào khoảng thời gian </label>
                                    <label class="control-label"></label>
                                    <input class="form-control" type="date" name="startdate_s" >
                                     <label class="control-label"></label>
                                    <input class="form-control" type="date" name="enddate_s" >
   </div>


                  

                                <button class="btn btn-save" title="" type="submit">Xác nhận</button>
                                <a class="btn btn-cancel" href="WorkSheet_create.jsp">Huỷ bỏ</a>
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
