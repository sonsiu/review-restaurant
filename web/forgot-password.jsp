<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/login.css" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> 
    </head>

    <div>test: ${sessionScope.currentUser.email}</div>
    <div>test: ${sessionScope.currentUser.verifyCode}</div>
    <div>bansonvtpt@gmail.com</div>

    <div class="container" id="container">
        <c:choose>
            <c:when test="${isReset==null}">
                <form ${isReset?"hidden":""} method="post" action="forgot-password" style="padding-left: 250px; padding-right: 250px;">
                    <h1 ${isReset?"hidden":""} style="font-size: 175%;padding-bottom: 100px;">Forgot password</h1>
                    <div ${isReset?"hidden":""} style="font-size: 10px;" ${mess!=null?"hidden":""}>Enter your email and we'll will send a OTP code to reset your password </div>
                    <div ${isReset?"hidden":""} class="alert alert-danger" style="${mess!=null?"":"font-size: 10px;"}" ${mess!=null?"":"hidden"}>${mess}</div>
                    <input ${isReset?"hidden":""} type="text" name="email" placeholder="Email" />
                    <input hidden type="text" name="stepOne" value="true" />
                    <button ${isReset?"hidden":""} type="submit">Submit</button>
                </form>
            </c:when>
            <c:when test="${isReset!=null}">
                <form method="post" action="forgot-password" style="padding-left: 250px; padding-right: 250px;">
                    <h1 style="font-size: 175%;padding-bottom: 100px;">Reset your password</h1>
                    <!--<div style="font-size: 10px;" ${mess!=null?"hidden":""}>Enter your email and we'll will send a OTP code to reset your password </div>-->
                    <div class="alert alert-danger" style="${mess!=null?"":"font-size: 10px;"}" ${mess!=null?"":"hidden"}>${mess}</div>

                    <input type="password" name="password" placeholder="Password" />
                    <input type="password" name="confirmPassword" placeholder="Confirm password" />
                    <input hidden type="text" name="stepTwo" value="true" />
                    <button type="submit">Submit</button>
                </form>
            </c:when>
        </c:choose>
    </div>    
</html>
