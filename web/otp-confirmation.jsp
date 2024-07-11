<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Site Metas -->
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="shortcut icon" href="images/161-1616544_popcorn-icon-cinema-flat-icon-png.png" type="">

        <title> OTP - Confirmation </title>

        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="view/css/bootstrap.css" />

        <!--owl slider stylesheet -->
        <link rel="stylesheet" type="text/css"
              href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
        <!-- nice select  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css"
              integrity="sha512-CruCP+TD3yXzlvvijET8wV5WxxEh5H8P4cmz0RFbKK6FlZ2sYl3AEsKlLPHbniXKSrDdFewhbmBK5skbdsASbQ=="
              crossorigin="anonymous" />
        <!-- font awesome style -->
        <link href="view/css/font-awesome.min.css" rel="stylesheet" />

        <!-- Custom styles for this template -->
        <link href="view/css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="view/css/responsive.css" rel="stylesheet" />

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <style>
            @import url('https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css');
            .form-control {
                margin-right: 12px;
                width: 64px;
                height: 40px;
            }

            .form-control:focus {
                color: #495057;
                background-color: #fff;
                border-color: #ff8880;
                outline: 0;
                box-shadow: none;
            }

            .form-control.form-control-solid {
                background-color: #F3F6F9;
                border-color: #F3F6F9;
                color: #3F4254;
                transition: color 0.15s ease, background-color 0.15s ease, border-color 0.15s ease, box-shadow 0.15s ease;
            }

            .form-control.form-control-solid:active,
            .form-control.form-control-solid.active,
            .form-control.form-control-solid:focus,
            .form-control.form-control-solid.focus {
                background-color: #EBEDF3;
                border-color: #EBEDF3;
                color: #3F4254;
                transition: color 0.15s ease, background-color 0.15s ease, border-color 0.15s ease, box-shadow 0.15s ease;
            }

            .cursor {
                cursor: pointer;
            }

            input[type="text"] {
                display: block;
                margin : 0 auto;
            }
        </style>
    </head>
    <body class="d-flex flex-column min-vh-100">

<!--<h1>username: ${username}, email: ${email}, password: ${password}</h1>-->
        <div style="padding-top: 30px;">
            <form action="otp-confirmation" method="post">
                <input type='text' name='username' value='${username}' hidden>
                <input type='text' name='password' value='${password}' hidden>
                <input type='text' name='email' value='${email}' hidden>

                <h1 class="h2 text-center">Xác thực email của bạn</h1>
                <!-------------------------------------Test------------------------------------------------------------------------>
                <div>test: ${sessionScope.currentUser.email}</div>
                <div>test: ${sessionScope.currentUser.verifyCode}</div>
                <div>test: ${sessionScope.currentUser.expDate}</div>
                <div>test: ${sessionScope.action}</div>
                <div>test: ${sessionScope.action.equals("forgot")}</div>
                <!----------------------------------------------------------------------------------------------------------------->                
                <div class="text-center alert alert-danger" role="alert" ${mess==null?"hidden":""}>${mess}</div>
                <div class="mb-6 text-center mt-5">
                    <div id="otp" class="flex justify-center">
                        <input class="m-2 text-center form-control form-control-solid rounded focus:border-blue-400 focus:shadow-outline" type="text" name="otp1" id="first" maxlength="1" />
                        <input class="m-2 text-center form-control form-control-solid rounded focus:border-blue-400 focus:shadow-outline" type="text" name="otp2" id="second" maxlength="1" />
                        <input class="m-2 text-center form-control form-control-solid rounded focus:border-blue-400 focus:shadow-outline" type="text" name="otp3" id="third" maxlength="1" />
                        <input class="m-2 text-center form-control form-control-solid rounded focus:border-blue-400 focus:shadow-outline" type="text" name="otp4" id="fourth" maxlength="1" />
                        <input class="m-2 text-center form-control form-control-solid rounded focus:border-blue-400 focus:shadow-outline" type="text" name="otp5" id="fifth" maxlength="1" />
                        <input class="m-2 text-center form-control form-control-solid rounded focus:border-blue-400 focus:shadow-outline" type="text" name="otp6" id="sixth" maxlength="1" />
                    </div>
                </div>
                <h1 class="h4 text-center" ${sessionScope.isSignUp?"":"hidden"}>Đễ bắt đầu sử dùng tài khoản, hãy xác thực email</h1>
                <h1 class="h4 text-center" ${sessionScope.isForgot?"":"hidden"}>Đễ bắt đầu sử thay đổi mật khẩu, hãy chứng minh quyền sở hữu email</h1>
                <div class="d-flex justify-content-center mr-5">
                    <p>Không nhận được mã? <a style="color: blue;text-decoration: none;border-bottom: 1px solid blue;" href="resendOtp"> Gửi lại.</a></p>
                </div>
                <div class="d-flex justify-content-center mr-5">
                    <button type="submit" value="verify" class="btn btn-warning btn-lg ms-3 text-center mt-3" style="border-radius: 10px">Xác thực</button>
                </div>
            </form>
        </div>



        <script>
            function OTPInput() {
                const inputs = document.querySelectorAll('#otp > *[id]');
                for (let i = 0; i < inputs.length; i++) {
                    inputs[i].addEventListener('keydown', function (event) {
                        if (event.key === "Backspace") {
                            inputs[i].value = '';
                            if (i !== 0)
                                inputs[i - 1].focus();
                        } else {
                            if (i === inputs.length - 1 && inputs[i].value !== '') {
                                return true;
                            } else if (event.keyCode > 47 && event.keyCode < 58) {
                                inputs[i].value = event.key;
                                if (i !== inputs.length - 1)
                                    inputs[i + 1].focus();
                                event.preventDefault();
                            } else if (event.keyCode > 64 && event.keyCode < 91) {
                                inputs[i].value = String.fromCharCode(event.keyCode);
                                if (i !== inputs.length - 1)
                                    inputs[i + 1].focus();
                                event.preventDefault();
                            }
                        }
                    });
                }
            }
            OTPInput();
        </script>
    </body>
</html>
