

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Thêm sản phẩm | Quản trị Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Main CSS-->
        <link rel="stylesheet" type="text/css" href="./css/admin.css">
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
        <script>
            function readURL(input, thumbimage) {
                if (input.files && input.files[0]) { // Sử dụng cho Firefox - Chrome
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("#thumbimage").attr('src', e.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                } else { // Sử dụng cho IE
                    $("#thumbimage").attr('src', input.value);
                }
                $("#thumbimage").show();
                $('.filename').text($("#uploadfile").val());
                $('.Choicefile').css('background', '#14142B');
                $('.Choicefile').css('cursor', 'default');
                $(".removeimg").show();
                $(".Choicefile").unbind('click');
            }

            $(document).ready(function () {
                $(".Choicefile").bind('click', function () {
                    $("#uploadfile").click();
                });
                $(".removeimg").click(function () {
                    $("#thumbimage").attr('src', '').hide();
                    $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
                    $(".removeimg").hide();
                    $(".Choicefile").bind('click', function () {
                        $("#uploadfile").click();
                    });
                    $('.Choicefile').css('background', '#14142B');
                    $('.Choicefile').css('cursor', 'pointer');
                    $(".filename").text("");
                });
            })
        </script>
    </head>

    <body class="app sidebar-mini rtl">
        <style>
            .Choicefile {
                display: block;
                background: #14142B;
                border: 1px solid #fff;
                color: #fff;
                width: 150px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
                padding: 5px 0px;
                border-radius: 5px;
                font-weight: 500;
                align-items: center;
                justify-content: center;
            }

            .Choicefile:hover {
                text-decoration: none;
                color: white;
            }

            #uploadfile,
            .removeimg {
                display: none;
            }

            #thumbbox {
                position: relative;
                width: 100%;
                margin-bottom: 20px;
            }

            .removeimg {
                height: 25px;
                position: absolute;
                background-repeat: no-repeat;
                top: 5px;
                left: 5px;
                background-size: 25px;
                width: 25px;
                /* border: 3px solid red; */
                border-radius: 50%;

            }

            .removeimg::before {
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                content: '';
                border: 1px solid red;
                background: red;
                text-align: center;
                display: block;
                margin-top: 11px;
                transform: rotate(45deg);
            }

            .removeimg::after {
                /* color: #FFF; */
                /* background-color: #DC403B; */
                content: '';
                background: red;
                border: 1px solid red;
                text-align: center;
                display: block;
                transform: rotate(-45deg);
                margin-top: -2px;
            }
        </style>
        <jsp:include page="headerAdmin.jsp"/>

        <main class="app-content">
            <div class="app-title">
                <ul class="app-breadcrumb breadcrumb">
                    <li class="breadcrumb-item">Danh sách nhân viên</li>
                    <li class="breadcrumb-item"><a href="#">Thêm nhân viên</a></li>
                </ul>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="tile">
                        <h3 class="tile-title">Tạo mới nhân viên</h3>
                        <div class="tile-body">
                            <form class="row" action="createuser" method="post" enctype="multipart/form-data" id="createPro">
                                <div class="form-group col-md-4">
                                    <label class="control-label">Họ và tên</label>
                                    <input class="form-control" type="text" required name="name" id="name">
                                    <span id="nameError" class="error"></span>
                                </div>

                                <div class="form-group col-md-4">
                                    <label class="control-label">Địa chỉ thường trú</label>
                                    <input class="form-control" type="text" required name="address" id="address">
                                    <span id="addressError" class="error"></span>
                                </div>

                                <div class="form-group col-md-4">
                                    <label class="control-label">Số điện thoại</label>
                                    <input class="form-control" type="number" required name="phone" id="phone" >
                                    <span id="phoneError" class="error"></span>
                                </div>

                                <div class="form-group col-md-4">
                                    <label class="control-label">Ngày sinh</label>
                                    <input class="form-control" type="date" name="birthday" id="birthday" required>
                                    <span id="birthdayError" class="error"></span>
                                </div>

                                <div class="form-group col-md-3">
                                    <label for="exampleSelect1" class="control-label">Giới tính</label>
                                    <select class="form-control" id="exampleSelect1" name="sex" required>
                                        <option disabled >-- Chọn giới tính --</option>
                                        <option value="1">Nam</option>
                                        <option value="2">Nữ</option>
                                    </select>
                                </div>

                                <div class="form-group col-md-3">
                                    <label for="exampleSelect1" class="control-label">Chức vụ</label>
                                    <select class="form-control" id="exampleSelect1" name="role" required>
                                        <option disabled >-- Chọn chức vụ --</option>
                                        <c:forEach items="${requestScope.list}" var="i">
                                            <option value="${i.id}">${i.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group col-md-4">
                                    <label class="control-label">Tên đăng nhập</label>
                                    <input class="form-control" type="text" name="username" id="username" required>
                                    <span id="usernameError" class="error"></span>
                                </div>

                                <div class="form-group col-md-4">
                                    <label class="control-label">Mật Khẩu</label>
                                    <input class="form-control" type="text" name="pass" id="pass" required>
                                    <span id="passError" class="error"></span>
                                </div>

                                <div class="form-group col-md-12">
                                    <label class="control-label">Ảnh nhân viên</label>
                                    <div id="myfileupload">
                                        <input required type="file" id="uploadfile" name="ImageUpload" onchange="readURL(this);">
                                    </div>
                                    <div id="thumbbox">
                                        <img height="450" width="400" alt="Thumb image" id="thumbimage" style="display: none" />
                                        <a class="removeimg" href="javascript:"></a>
                                    </div>
                                    <div id="boxchoice">
                                        <a href="javascript:" class="Choicefile"><i class="fas fa-cloud-upload-alt"></i> Chọn ảnh</a>
                                        <p style="clear:both"></p>
                                    </div>
                                    <span id="imageError" class="error"></span>
                                </div>
                            </form>
                        </div>
                        <button class="btn btn-save" type="button" onclick="validateForm()">Lưu lại</button>
                        <a class="btn btn-cancel" href="user">Hủy bỏ</a>
                    </div>
                </div>
            </div>



        </main>

        <script src="./js/jquery-3.2.1.min.js"></script>
        <script src="./js/popper.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="./js/main.js"></script>
        <script src="./js/plugins/pace.min.js"></script>
        <script>
                            const inpFile = document.getElementById("inpFile");
                            const loadFile = document.getElementById("loadFile");
                            const previewContainer = document.getElementById("imagePreview");
                            const previewImage = previewContainer.querySelector(".image-preview__image");
                            const previewDefaultText = previewContainer.querySelector(".image-preview__default-text");
                            inpFile.addEventListener("change", function () {
                                const file = this.files[0];
                                if (file) {
                                    const reader = new FileReader();
                                    previewDefaultText.style.display = "none";
                                    previewImage.style.display = "block";
                                    reader.addEventListener("load", function () {
                                        previewImage.setAttribute("src", this.result);
                                    });
                                    reader.readAsDataURL(file);
                                }
                            });
        </script>



        <script>

            function validateForm() {
                var name = document.getElementById("name").value.trim();
                var address = document.getElementById("address").value.trim();
                var phone = document.getElementById("phone").value.trim();
                var username = document.getElementById("username").value.trim();
                var pass = document.getElementById("pass").value.trim();
                var birthday = document.getElementById("birthday").value;
                var imageError = document.getElementById("imageError");

                var nameError = document.getElementById("nameError");
                var addressError = document.getElementById("addressError");
                var phoneError = document.getElementById("phoneError");
                var usernameError = document.getElementById("usernameError");
                var passError = document.getElementById("passError");
                var birthdayError = document.getElementById("birthdayError");

                var fileInput = document.getElementById("uploadfile");
                var file = fileInput.files[0];

                var isValid = true;

                if (name === "") {
                    nameError.textContent = "Vui lòng nhập họ và tên.";
                    nameError.style.color = "red";
                    document.getElementById("name").style.borderColor = "red";
                    isValid = false;
                } else if (name.length > 25) {
                    nameError.textContent = "Họ và tên không quá 25 ký tự.";
                    nameError.style.color = "red";
                    document.getElementById("name").style.borderColor = "red";
                    isValid = false;
                }
//                else if (/[^a-zA-Z0-9\s]/.test(name)) {
//                    nameError.textContent = "Họ và tên không được chứa ký tự đặt biệt .";
//                    nameError.style.color = "red";
//                    document.getElementById("name").style.borderColor = "red";
//                    isValid = false;
//                }
                else {
                    nameError.textContent = "";
                    nameError.style.color = "";
                    document.getElementById("name").style.borderColor = "";
                }

                if (address === "") {
                    addressError.textContent = "Vui lòng nhập địa chỉ.";
                    addressError.style.color = "red";
                    document.getElementById("address").style.borderColor = "red";
                    isValid = false;
                } else if (address.length > 15) {
                    addressError.textContent = "Địa chỉ không quá 15 ký tự.";
                    addressError.style.color = "red";
                    document.getElementById("address").style.borderColor = "red";
                    isValid = false;
                }
//                else if (/[^a-zA-Z0-9\s]/.test(address)) {
//                    addressError.textContent = "Địa chỉ không được chứa ký tự đặt biệt.";
//                    addressError.style.color = "red";
//                    document.getElementById("address").style.borderColor = "red";
//                    isValid = false;
//                }
                else {
                    addressError.textContent = "";
                    addressError.style.color = "";
                    document.getElementById("address").style.borderColor = "";
                }

                if (phone === "") {
                    phoneError.textContent = "Vui lòng nhập số điện thoại.";
                    phoneError.style.color = "red";
                    document.getElementById("phone").style.borderColor = "red";
                    isValid = false;
                } else if (phone.length < 10 || phone.length > 11) {
                    phoneError.textContent = "Số điện thoại phải có từ 10 đến 11 số.";
                    phoneError.style.color = "red";
                    document.getElementById("phone").style.borderColor = "red";
                    isValid = false;
                } else {
                    phoneError.textContent = "";
                    phoneError.style.color = "";
                    document.getElementById("phone").style.borderColor = "";
                }


                if (username === "") {
                    usernameError.textContent = "Vui lòng nhập tên đăng nhập.";
                    usernameError.style.color = "red";
                    document.getElementById("username").style.borderColor = "red";
                    isValid = false;
                } else if (/[^a-zA-Z0-9\s]/.test(username)) {
                    usernameError.textContent = "Tên đăng nhập không được chứa ký tự đặt biệt.";
                    usernameError.style.color = "red";
                    document.getElementById("username").style.borderColor = "red";
                    isValid = false;
                } else if (username.length < 3 || username.length > 10) {
                    usernameError.textContent = "Tên đăng nhập phải có từ 3-10 ký tự.";
                    usernameError.style.color = "red";
                    document.getElementById("username").style.borderColor = "red";
                    isValid = false;
                } else {
                    var xhr = new XMLHttpRequest();
                    xhr.open('GET', 'loadusername', true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var usernames = JSON.parse(xhr.responseText); // Parse the JSON response
                            if (usernames.includes(username)) {
                                usernameError.textContent = "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.";
                                usernameError.style.color = "red";
                                document.getElementById("username").style.borderColor = "red";
                                isValid = false;
                            } else {
                                usernameError.textContent = "";
                                usernameError.style.color = "";
                                document.getElementById("username").style.borderColor = "";

                                // Only submit the form if all other parameters are valid
                                if (isValid && name && address && phone && pass && birthday && file) {
                                    document.getElementById("createPro").submit();
                                }
                            }
                        }
                    };

                    xhr.send();
                }

                if (pass === "") {
                    passError.textContent = "Vui lòng nhập mật khẩu.";
                    passError.style.color = "red";
                    document.getElementById("pass").style.borderColor = "red";
                    isValid = false;
                } else if (pass.length < 3 || pass.length > 7) {
                    passError.textContent = "Mật khẩu phải có từ 3-7 ký tự.";
                    passError.style.color = "red";
                    document.getElementById("pass").style.borderColor = "red";
                    isValid = false;
                } else if (/[^a-zA-Z0-9]/.test(pass)) {
                    passError.textContent = "Mật khẩu không được chứa ký tự đặt biệt.";
                    passError.style.color = "red";
                    document.getElementById("pass").style.borderColor = "red";
                    isValid = false;
                } else {
                    passError.textContent = "";
                    passError.style.color = "";
                    document.getElementById("pass").style.borderColor = "";
                }
                if (birthday === "") {
                    birthdayError.textContent = "Vui lòng chọn ngày sinh";
                    birthdayError.style.color = "red";
                    document.getElementById("birthday").style.borderColor = "red";
                    isValid = false;
                } else {
                    birthdayError.textContent = "";
                    birthdayError.style.color = "";
                    document.getElementById("birthday").style.borderColor = "";

                }

                if (!file) {
                    imageError.textContent = "Vui lòng chọn ảnh.";
                    imageError.style.color = "red";
                    isValid = false;
                } else {
                    imageError.textContent = "";
                    imageError.style.color = "";
                }
            }

        </script>


        <script>
            <% if (request.getAttribute("message") != null) {%>
            swal("<%= request.getAttribute("message")%>", "", "success");
            <% request.removeAttribute("message"); %>
            <% }%>
        </script>

    </body>

</html>
