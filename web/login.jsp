<%-- 
    Document   : login
    Created on : May 14, 2024, 3:37:03 PM
    Author     : legion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/login.css" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    </head>
<!--    <div>test: ${sessionScope.currentUser.email}</div>
    <div>test: ${sessionScope.currentUser.verifyCode}</div>
    <div>test: ${remember == null?"null":"not null"}</div>
    <div>test: ${testUsername}</div>-->



    <body style="overflow: hidden;">
        <%--<c:if test="">--%>
            <div class="" style="position: absolute;top: 8px;left: 16px;padding-top: 10px;"><a href="homepage" style="font-size: 18px;"><button><svg xmlns="http://www.w3.org/2000/svg" style="" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-left" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5"/>
            </svg>       Trang chủ</button></a></div>
        <%--</c:if>--%>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form method="post" action="sign-up">
                    <h1>Tạo tài khoản</h1>
                    <div class="social-container">
                        <a href="${GOOGLE_LOGIN_HREF}" class="social"><i class="fa fa-google" aria-hidden="true"></i></a>
                    </div>
                    <span ${signUpMess==null?"":"hidden"} ${mess==null?"":"hidden"}>hoặc đăng ký tài khoản mới</span>
                    <div class="${signUpMess!=null?"alert alert-danger":"alert alert-success"}" role="alert">${signUpMess}${successMess}</div>   

                    <c:choose>
                        <c:when test="${sessionScope.currentUser!=null}">
                            <input type="email" name="email" placeholder="Email" value="${sessionScope.currentUser.email}" ${sessionScope.currentUser!=null?"":"hidden"}/>
                            <input type="text" name="username" placeholder="Tên đăng nhập" value="${sessionScope.currentUser.username}" ${sessionScope.currentUser!=null?"":"hidden"}/>
                        </c:when>
                        <c:when test="${email!=null&&username==null}">
                            <input type="email" name="email" placeholder="Email" value="${email}" ${email!=null?"":"hidden"}/>
                            <input type="text" name="username" placeholder="Tên đăng nhập" value="" ${email!=null?"":"hidden"}/>
                        </c:when>
                        <c:when test="${username!=null&&email==null}">
                            <input type="email" name="email" placeholder="Email" value="" ${username!=null?"":"hidden"}/>
                            <input type="text" name="username" placeholder="Tên đăng nhập" value="${username}" ${username!=null?"":"hidden"}/>
                        </c:when>
                        <c:otherwise>
                            <input type="email" name="email" placeholder="Email" value="${email}"/>
                            <input type="text" name="username" placeholder="Tên đăng nhập" value="${username}"/>
                        </c:otherwise>
                    </c:choose>

                    <input type="password" name="password" placeholder="Mật khẩu" />
                    <input type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu" />
                    <button>Đăng Ký</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form method="post" action="login">
                    <h1>Đăng nhập</h1>
                    <div class="social-container">
                        <a href="${GOOGLE_LOGIN_HREF}" class="social"><i class="fa fa-google" aria-hidden="true"></i></a>
                    </div>
                    <span ${mess==null?"":"hidden"}${successMess==null?"":"hidden"}${loginMess==null?"":"hidden"}>hoặc dùng tài khoản của bạn</span>
                    <div class="${successMess==null?"alert alert-danger":"alert alert-success"}" role="alert">${mess}${loginMess}${successMess}</div>
                    <input type="text" name="username" placeholder="Tên đăng nhập" value="${cookieUsername}"/>
                    <input type="password" name="password" placeholder="Mật khẩu" value="${cookiePassword}"/>
                    <input name="remember" type="checkbox" ${remember == "on"?"checked":""}/>Ghi nhớ đăng nhập
                    <a href="forgot-password">Quên mật khẩu?</a>
                    <button type="submit">Đăng Nhập</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Chào mừng trở lại!</h1>
                        <p>Cập nhật những tin tức mới nhất bằng cách đăng nhập ngay!</p>
                        <button class="ghost" id="signIn">Đăng Nhập</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Xin chào, người mới!</h1>
                        <p>Hãy tạo tài khoản và chung tay gây dựng cộng đồng lớn mạnh!</p>
                        <button class="ghost" id="signUp">Đăng Ký</button>
                    </div>
                </div>
            </div>
        </div>


        <script>
            const signUpButton = document.getElementById('signUp');
            const signInButton = document.getElementById('signIn');
            const container = document.getElementById('container');

            signUpButton.addEventListener('click', () => {
                container.classList.add("right-panel-active");
            });

            signInButton.addEventListener('click', () => {
                container.classList.remove("right-panel-active");
            });
        </script>

    </body>
</html>
