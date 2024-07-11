<%-- 
    Document   : userdetail.jsp
    Created on : May 21, 2024, 3:38:34 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<header>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/newcss.css">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</header>
<body>
    <%@include file="components/header.jsp" %>
    <%@include file="components/modal/modal-edit-user.jsp" %>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div id="content" class="content content-full-width">
                                <!-- begin profile -->
                                <div class="profile">
                                    <div class="profile-header">
                                        <!-- BEGIN profile-header-cover -->
                                        <div class="profile-header-cover"></div>
                                        <!-- END profile-header-cover -->
                                        <!-- BEGIN profile-header-content -->
                                        <div class="profile-header-content">
                                            <!-- BEGIN profile-header-img -->
                                            <div class="profile-header-img">
                                                <img src="${u.profilePicture}" alt="">
                                            </div>
                                            <!-- END profile-header-img -->
                                            <!-- BEGIN profile-header-info -->
                                            <div class="profile-header-info">
                                                <h4 class="m-t-10 m-b-5">${u.displayName}</h4>
                                                <p class="m-b-10">@ ${u.id}</p>
                                                <button type="button" class="btn btn-sm btn-info mb-2" data-toggle="modal" data-target="#modal-edit-user">Edit Profile</button>
                                            </div>
                                            <!-- END profile-header-info -->
                                        </div>
                                        <!-- END profile-header-content -->
                                        <!-- BEGIN profile-header-tab -->
                                        <%@include file="components/userinfotags.jsp" %>

                                        <!-- END profile-header-tab -->
                                    </div>
                                </div>
                                <!-- end profile -->
                                <!-- begin profile-content -->
                                <div class="profile-content">
                                    <!-- begin tab-content -->
                                    <div class="tab-content p-0">

                                        <!-- begin #profile-about tab -->
                                        <div class="tab-pane fade in active show" id="profile-about">
                                            <!-- begin table -->
                                            <div class="table-responsive">
                                                <table class="table table-profile">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>
                                                                <h4>Nguyen Hai Dang</h4>
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr class="highlight">

                                                        </tr>
                                                        <tr class="divider">
                                                            <td colspan="2"></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="field">Display Name</td>
                                                            <td>${u.displayName}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="field">Email</td>
                                                            <td>${u.email}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="field">Phone</td>
                                                            <td>${u.phone}</td>
                                                        </tr>

                                                        <tr class="divider">
                                                            <td colspan="2"></td>
                                                        </tr>
                                                        <tr class="highlight">

                                                        </tr>
                                                        <tr class="divider">
                                                            <td colspan="2"></td>
                                                        </tr>                                                                                       
                                                        <tr class="divider">
                                                            <td colspan="2"></td>
                                                        </tr>
                                                        <tr class="highlight">
                                                            <td class="field">&nbsp;</td>

                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- end table -->
                                        </div>
                                        <!-- end #profile-about tab -->
                                    </div>
                                    <!-- end tab-content -->
                                </div>
                                <!-- end profile-content -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
