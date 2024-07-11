<%-- 
    Document   : header
    Created on : May 16, 2024, 12:54:07 PM
    Author     : legion
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Header Section Begin -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i> holaeatery@gmail.com</li>
                            <li>Review Quán ăn khắp Việt Nam</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <!-- The form -->
                    <c:if test="${not empty sessionScope.currentUser}">
                        <div class="form-popup" id="myForm">
                            <div class="form-container">
                                <h4 style="text-align: center">Thông báo</h4>
                                <div class="notify-container">
                                    <c:if test="${empty listNotif}">
                                        <strong>Không có gì ở đây cả ...</strong>
                                    </c:if>
                                    <c:forEach items="${listNotif}" var="o">    
                                        <div class ="notify-content">
                                            <c:set var="diff" value="${o.getDateDiff(o.timestamp)}" />
                                            <c:set var="diffMinute" value="${o.getDateDiffMinute(o.timestamp)}" />

                                            <small>${diff <= 24?(diff < 1? diffMinute:diff ):Double.valueOf(diff/24).intValue()} ${diff<=24? (diff<1? (diffMinute<1?"minute":"minutes") :diff<2?"hour":"hours") : (diff/24<2?"day":"days")} ago</small><br>
                                            <!--<small>${o.getDateDiff(o.timestamp)} hours ago</small><br>-->
                                            <strong>${o.title}</strong>
                                            <p>${o.message}</p>

                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="header__top__right">
                        <c:if test="${sessionScope.currentUser != null}">
                            <div class="header__top__right__auth">
                                <a style="margin-right:5px">Hello, ${sessionScope.currentUser.displayName}  </a>
                            </div>
                            <div class="header__top__right__social" style="top : 10px;">
                                <div class="header-img">
                                    <div class="round-image">
                                        <img src="data:image/*;base64,${sessionScope.currentUser.profilePicture}" alt="Not Available">
                                    </div>
                                </div>
                                <style>
                                    .header-img{
                                        width: 30px;
                                        height: 30px;
                                    }
                                    .round-image {
                                        width: 100%; /* Adjust as desired */
                                        height: 100%; /* Adjust as desired */
                                        border-radius: 50%; /* Creates a circle */
                                        overflow: hidden; /* Hides overflowing parts of the image */
                                    }

                                    .round-image img {
                                        width: 100%; /* Ensures image fills the container */
                                        height: 100%; /* Ensures image fills the container */
                                        object-fit: cover; /* Scales image to cover the container */
                                    }
                                </style>
                            </div>
                            <div class="header__top__right__social">
                                <a href="" onmouseover="openNotification()" onmouseleave="closeNotification()"><i class="fa fa-bell" style="margin-right: 5px;"></i>Thông báo</a>
                            </div>
                            <div class="header__top__right__social">
                                <a href="showblog?ID=${sessionScope.currentUser.id}"><i class="fa fa-user" style="margin-right: 5px;"></i> Cá nhân </a>
                            </div>
                            <c:if test="${sessionScope.currentUser.role == 1}">
                                <div class="header__top__right__social">
                                    <a href="moder/moderator.jsp"><i class="fa fa-group" style="margin-right: 5px;"></i>Moderation</a>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.currentUser.role == 0}">
                                <div class="header__top__right__social">
                                    <a href="admin/adminDashboard.jsp"><i class="fa fa-group" style="margin-right: 5px;"></i>Admin</a>
                                </div>
                            </c:if>

                        </c:if>

                        <div class="header__top__right__social">
                            <c:if test="${sessionScope.currentUser != null}">
                                <a href="log-out"><i class="fa fa-sign-out"></i> Đăng Xuất</a>
                            </c:if>
                            <c:if test="${sessionScope.currentUser == null}">   
                                <a href="login"><i class="fa fa-user"></i> Đăng Nhập</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-2">
                <div class="header__logo">
                    <a href="homepage"><img src="img/logo-new.png" alt="" style="width: 150px; height:auto"></a>
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul>
                        <li><a href="homepage"><i class="fa fa-home" style="margin-right: 5px;"></i>Trang Chủ</a></li>
                        </li>
                        <c:if test="${sessionScope.currentUser != null}">
                            <li>
                                <c:if test="${sessionScope.currentUser.role == 2}">
                                <li><a href="createblog"><i class="fa fa-pencil" style="margin-right: 5px;"></i>Tạo Bài</a></li>
                                </c:if>
                                <c:if test="${sessionScope.currentUser.role == 1}">
                                <li><a href="moder/moderator.jsp">Moderation</a></li>
                                </c:if>
                            </c:if>

                    </ul>
                </nav>
            </div>
            <div class="col-lg-4">
                <div class="hero__search__form " >
                    <form id="search-form" action="search?action=all" method="post">
                        <div class="hero__search__categories">
                            <i class="fa fa-search"></i>
                        </div> 
                        <input type="text" name="txt" placeholder="Bạn muốn tìm gì ?">
                    </form>
                </div>
            </div>
        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
    <style>

        /* The popup form - hidden by default */
        .form-popup {
            display: none;
            position: fixed;
            top:50px ;
            right: 250px;
            border: 3px solid #f1f1f1;
            z-index: 9;
        }

        /* Add styles to the form container */
        .form-container {
            max-width: 400px;
            padding: 10px;
            background-color: white;
        }
        .notify-container{
            width: 100%;
            padding: 15px;
            margin: 10px 0 22px 0;
            background: #f1f1f1;
        }
        .notify-content{
            border-bottom: 1px solid lightgray; /* Adjust border style and color as needed */
        }

        .form-popup:hover {
            display: block;
        }



        /* Set a style for the submit/login button */
        .form-container .btn {
            background-color: #04AA6D;
            color: white;
            padding: 10px 10px;
            border: none;
            cursor: pointer;
            width: 100%;
            margin-bottom:10px;
            opacity: 0.8;
        }

        /* Add a red background color to the cancel button */
        .form-container .cancel {
            background-color: red;
        }

        /* Add some hover effects to buttons */
        .form-container .btn:hover, .open-button:hover {
            opacity: 1;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
                                    $(document).ready(function () {
                                        $('#search-form').submit(function (event) {
                                            var searchText = $('input[name="txt"]').val().trim();
                                            if (searchText === "") {
                                                event.preventDefault(); // Prevent form submission if input is empty
                                            }
                                        });

                                        $('.search-option').click(function (event) {
                                            event.preventDefault(); // Prevent the default button click behavior

                                            var action = $(this).data('action'); // Get the action (all, blog, user)
                                            var txt = $('input[name="txt"]').val().trim(); // Get the search text

                                            if (txt === "") {
                                                return; // Do nothing if the search text is empty
                                            }

                                            $.ajax({
                                                url: 'search',
                                                type: 'POST',
                                                data: {action: action, txt: txt},
                                                success: function (response) {
                                                    $('#search-results').html(response);
                                                },
                                                error: function (xhr, status, error) {
                                                    console.error('Error:', status, error);
                                                    alert('Error occurred while searching.');
                                                }
                                            });
                                        });
                                    });
//                                    function toggleForm() {
//                                        event.preventDefault();
//                                        var form = document.getElementById("myForm");
//                                        if (form.style.display === "none" || form.style.display === "") {
//                                            form.style.display = "block";
//                                        } else {
//                                            form.style.display = "none";
//                                        }
//                                    }
                                    function openNotification() {
                                        var form = document.getElementById("myForm");
                                        form.style.display = "block";
                                    }
                                    function closeNotification() {
                                        var form = document.getElementById("myForm");
                                        form.style.display = "none";
                                    }

    </script>

</header>
<!-- Header Section End -->