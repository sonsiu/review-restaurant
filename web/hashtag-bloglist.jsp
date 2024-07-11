<%-- 
    Document   : index
    Created on : June 16, 10:10:34 PM
    Author     : Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>HomePage</title>

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


    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!--Turn Header, Footer to a seperate jsp file   -->
        <%@include file="components/header.jsp" %>

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg content-below-header" data-setbg="img/food-banner-breadcrumb.png">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>Hashtag</h2>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Featured Section Begin -->
        <section class="featured spad ">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <p>Kết quả tìm kiếm cho</p>
                            <h2>${hashtag}</h2>
                        </div>
                    </div>
                </div>
                <div id="search-results">
                    <!-- Search results will be loaded here -->
                    <div class="row featured__filter">
                        <c:choose>
                            <c:when test="${not empty blogs}">
                                <c:forEach items="${blogs}" var="blog">
                                    <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
                                        <div class="featured__item">
                                            <c:forEach items="${images}" var="image">
                                                <c:if test="${image.blogID == blog.blogID}">
                                                    <div class="featured__item__pic">
                                                        <img src="data:image/*;base64,${image.imageLink}" alt="">
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                            <div class="featured__item__text">
                                                <h6><a href="blog-detail?id=${blog.blogID}">${blog.title}</a></h6>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:when test="${empty blogs}">
                                <div class="col-lg-12 section-search">
                                    <p>It's quite empty here ...</p>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </section>

        <!-- Featured Section End -->

        <!--Turn Header, Footer to a seperate jsp file   -->
        <%@include file="components/footer.jsp" %>

        <style>
            .featured__item {
                background-color: white; /* Grey background */
                border-radius: 15px; /* Curved edges */
                padding: 0 0 15px 0; /* Padding inside the box */
                margin: 15px 0; /* Margin between boxes */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Optional: Adding a subtle shadow for better appearance */
                text-align: center; /* Center align content */
                
            }

            .featured__item__pic img {
                width: 300px; /* Fixed width */
                height: 300px; /* Fixed height */
                object-fit: cover; /* Ensures the image covers the dimensions without distortion */
                border-radius: 15px; /* Rounded corners on all edges */
            }

            .featured__item__text {
                margin-top: 10px; /* Space between image and text */
            }
            .featured__item__text h6 {
                font-weight: bold;
            }
        </style>

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>

    </script>


</html>