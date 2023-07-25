<%-- 
    Document   : login
    Created on : May 23, 2023, 9:34:31 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Đăng nhập quản trị</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/mainn.css">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sweetalert/1.0.1/sweetalert.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <style>
            .signin-google {
                display: inline-block;
                background-color: #fff;
                color: #000;
                border: 1px solid #ccc;
                padding: 10px 20px;
                text-decoration: none;
                font-family: Arial, sans-serif;
                font-size: 14px;
                border-radius: 4px;
                position: relative;
                overflow: hidden;
            }

            .google-icon {
                position: absolute;
                left: 8px;
                top: 50%;
                transform: translateY(-50%);
                width: 20px;
                height: 20px;
                background: url('images/logo-google.png') no-repeat center center;
                background-size: contain;
            }

            .signin-google:hover {
                background-color: #f1f1f1;
            }

            .signin-google:focus {
                outline: none;
                box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);
            }

            .signin-google:active {
                background-color: #e1e1e1;
            }
            .signin-google{
                cursor: var(--cursor_pointer);
                width: var(--onehundred_percent);
                padding: 10px 0;
                outline: none;
            }

        </style>

    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <div class="login100-pic js-tilt" data-tilt>
                        <img src="images/team.jpg" alt="IMG">
                    </div>
                    <!--=====TIÊU ĐỀ======-->
                    <div class="login100-form validate-form">
                        <span class="login100-form-title">
                            <b>ĐĂNG NHẬP HỆ THỐNG MINISTORE</b>
                        </span>
                        <!--=====FORM INPUT TÀI KHOẢN VÀ PASSWORD======-->
                        <form action="LoginServlet" method="post">
                            <div class="wrap-input100">
                                <input class="input100" type="text" placeholder="Tài khoản" name="username">
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
                                    <i class='bx bx-user'></i>
                                </span>
                            </div>
                            <div class="wrap-input100">
                                <input autocomplete="off" class="input100" type="password" placeholder="Mật khẩu"
                                       name="password" >
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
                                    <i class='bx bx-key'></i>
                                </span>
                            </div>

                            <!--=====ĐĂNG NHẬP======-->
                            <div class="container-login100-form-btn">
                                <input type="submit" value="Đăng nhập" />
                            </div>
                            <!--=====LINK TÌM MẬT KHẨU======-->
                            <!--                            <div class="text-right p-t-12">
                                                            <a class="txt2" href="forgot.jsp">
                                                                Bạn quên mật khẩu?
                                                            </a>
                                                        </div>-->
                        </form>

<!--                        <div style="text-align: center; padding: 20px;">
                            <h2>OR</h2>
                        </div>


                        <a style="text-align: center;" class="signin-google" href="https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=http://localhost:8080/SWPdemoN/LoginGoogleHandler&response_type=code
                           &client_id=1088509298613-7bdl8nei44if7io8i1k1juit0d1ahbkp.apps.googleusercontent.com&approval_prompt=force">
                            <span  class="google-icon"></span>
                            Đăng nhập với Google
                        </a>-->

                        <p style="color: red">${mess}</p>
                        <!--=====FOOTER======-->
                        <div class="text-center p-t-70 txt2">
                            Phần mềm quản lý bán hàng <i class="far fa-copyright" aria-hidden="true"></i>
                            <script type="text/javascript">document.write(new Date().getFullYear());</script> <a


                    </div>
                </div>
            </div>
        </div>
        <script>
            <%-- Get attribute "messLoginByGoogle" --%>
                            var messLoginByGoogle = "${messLoginByGoogle}";

            <%-- Check and display alert --%>
                            if (messLoginByGoogle !== "") {
                                alert(messLoginByGoogle);
                            }
        </script>                    
</body>
</html>
