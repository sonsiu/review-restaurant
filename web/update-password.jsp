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
    <div>bansonvtpt@gmail.com</div>-->
    
    <div class="" style="position: absolute;top: 8px;left: 16px;padding-top: 10px;"><a href="showblog?ID=${sessionScope.currentUser.id}" style="font-size: 18px;"/><button><svg xmlns="http://www.w3.org/2000/svg" style="" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-left" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5"/>
            </svg>       Trang cá nhân</button></div>
    
    <div class="container" id="container">
        <form method="post" action="update-password" style="padding-left: 250px; padding-right: 250px;">
            <h1 style="font-size: 175%;padding-bottom: 100px;padding-top: 30px;">Cập nhật mật khẩu của bạn</h1>
            <!--<div style="font-size: 10px;" ${mess!=null?"hidden":""}>Enter your email and we'll will send a OTP code to reset your password </div>-->
            <div class="alert alert-danger" style="${mess!=null?"":"font-size: 10px;"}" ${mess!=null?"":"hidden"}>${mess}</div>

            <input type="password" name="oldPassword" placeholder="Mật khẩu cũ" />
            <input type="password" name="newPassword" placeholder="Mật khẩu mới" />
            <input type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu" />
            <button type="submit">Cập nhật</button>
        </form>
    </div>    
</html>
