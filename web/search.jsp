<%-- 
    Document   : index
    Created on : May 15, 2024, 10:10:34 PM
    Author     : Vinh
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
        <style>
            td {
                margin: 10px 10px; /* Add padding to each cell for spacing (adjust as needed) */
                width : 300px;
            }

            .featured__item {
                background-color: white; /* Grey background */
                border : 1px solid lightgrey;
                border-radius: 15px; /* Curved edges */
                padding-bottom: 15px; /* Padding inside the box */
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

            .search-link{
                color:black;
                cursor: pointer;
            }

            .profile-card {
                border: 1px solid #eaeaea;
                padding: 30px;
                border-radius: 10px;
                background: #fff;
            }

            h5{
                font-weight: bold;
            }

            #advanced-blog-list-content{
                height: 1000px;
                overflow-y: scroll;
                background:white;
                border-radius: 15px;
                margin-left: 30px;
                padding: 10px 0;
            }

        </style>
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
                            <h2>Tìm kiếm</h2>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Featured Section Begin -->
        <section class="featured spad " style="background-color: #F5F4F4">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <p>Kết quả tìm kiếm cho</p>
                            <h2>${param.txt}</h2>
                            <input type="hidden" id="search-name" value="${param.txt}">
                        </div>
                        <div class="featured__controls">
                            <ul>
                                <li>
                                    <button id="all" onclick ="toTab('all')" class="search-option-btn search-option" data-action="all" style="color:black; background: lightgrey;">
                                        Tất cả
                                    </button>
                                </li>
                                <li>
                                    <button id="blog" onclick ="toTab('blog')" class="search-option-btn search-option" data-action="blog">Bài viết</button>
                                </li>
                                <li>
                                    <button id="user" onclick ="toTab('user')" class="search-option-btn search-option" data-action="user">Người dùng</button>
                                </li>
                            </ul>
                        </div>
                        <script>
                            function toogleAdvancedSearch(isOn) {
                                const blogSection = document.getElementById("blog-content");
                                const advancedSection = document.getElementById("blog-advanced-content");

                                if (blogSection.style.display === "none") {
                                    blogSection.style.display = "";
                                    advancedSection.style.display = "none";
                                } else {
                                    blogSection.style.display = "none";
                                    advancedSection.style.display = "";
                                }

                            }

                            function toTab(tab) {

                                const allTab = document.getElementById("all");
                                const blogTab = document.getElementById("blog");
                                const userTab = document.getElementById("user");
                                const blogSection = document.getElementById("blog-content");
                                const advancedSection = document.getElementById("blog-advanced-content");
                                const userSection = document.getElementById("user-content");

                                switch (tab) {
                                    case "all":
                                        allTab.style.color = 'black';
                                        allTab.style.background = 'lightgrey';

                                        blogTab.style.color = 'grey';
                                        blogTab.style.background = '#F5F4F4';

                                        userTab.style.color = 'grey';
                                        userTab.style.background = '#F5F4F4';

                                        blogSection.style.display = '';
                                        advancedSection.style.display = "none";
                                        userSection.style.display = '';
                                        break;
                                    case "blog":
                                        blogTab.style.color = 'black';
                                        blogTab.style.background = 'lightgrey';

                                        allTab.style.color = 'grey';
                                        allTab.style.background = '#F5F4F4';

                                        userTab.style.color = 'grey';
                                        userTab.style.background = '#F5F4F4';

                                        blogSection.style.display = '';
                                        advancedSection.style.display = "none";
                                        userSection.style.display = 'none';
                                        break;
                                    case "user":
                                        userTab.style.color = 'black';
                                        userTab.style.background = 'lightgrey';

                                        blogTab.style.color = 'grey';
                                        blogTab.style.background = '#F5F4F4';

                                        allTab.style.color = 'grey';
                                        allTab.style.background = '#F5F4F4';

                                        blogSection.style.display = 'none';
                                        advancedSection.style.display = "none";
                                        userSection.style.display = '';
                                        break;
                                    default:
                                    // code block
                                }
                            }
                        </script>
                    </div>
                </div>
                <div id="search-results">
                    <!-- Search results will be loaded here -->
                    <div id="blog-content" class="row featured__filter">
                        <c:choose>
                            <c:when test="${not empty listBlogsearch}">
                                <div class="col-lg-12 section-search">
                                    <p style="font-size: 30px; font-weight: bold;">Posts (${blogCount}) <small style='font-size:15px'>(Không tìm được bài mình muốn ? Thử <span class="search-link" onclick="toogleAdvancedSearch()"> Tìm kiếm nâng cao</span>)</small></p>
                                </div>
                                <c:forEach items="${listBlogsearch}" var="blog">
                                    <div class="col-lg-3 col-md-4 col-sm-6">
                                        <div class="featured__item">
                                            <c:forEach items="${listBlogImage}" var="image">
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
                        </c:choose>
                    </div>

                    <div id="blog-advanced-content" class="row " style="display: none;">
                        <c:choose>
                            <c:when test="${not empty listBlogsearch}">
                                <div class="col-lg-12 section-search">
                                    <p style="font-size: 30px; font-weight: bold;">Tìm kiếm nâng cao <small style='font-size:15px'>(Trở lại <span class="search-link" onclick="toogleAdvancedSearch()">Tìm kiếm</span>)</small></p>
                                </div>
                                <div class="row col-lg-4"> 

                                    <form id="advanced-search-form">
                                        <div class="col-lg-12 profile-card mt-4">
                                            <h5>Ẩm thực</h5><hr><br>
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Vietnamese" onclick="getBlogListByAJAX()"> Việt<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Chinese" onclick="getBlogListByAJAX()"> Trung<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Korean" onclick="getBlogListByAJAX()"> Hàn<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Thai" onclick="getBlogListByAJAX()"> Thái<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Europe" onclick="getBlogListByAJAX()"> Âu<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="American" onclick="getBlogListByAJAX()"> Mỹ<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Japanese" onclick="getBlogListByAJAX()"> Nhật<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Other" onclick="getBlogListByAJAX()"> Khác<br>
                                                        </td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-lg-12 profile-card mt-4">
                                            <h5>Bữa ăn</h5><hr><br>
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" onclick="getBlogListByAJAX()" value="Breakfast"> Sáng<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" onclick="getBlogListByAJAX()" value="Lunch"> Trưa<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" onclick="getBlogListByAJAX()" value="Dinner"> Tối<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" onclick="getBlogListByAJAX()" value="Late Night"> Đêm khuya<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" onclick="getBlogListByAJAX()" value="Brunch"> Giữa sáng-trưa<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" onclick="getBlogListByAJAX()" value="Drink"> Đồ uống<br>
                                                        </td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-lg-12 profile-card mt-4">
                                            <h5>Giá thành</h5><hr><br>
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="price-range" onclick="getBlogListByAJAX()" value="$ (>10000 vnd)"> $ (>10000 vnd) <br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="price-range" onclick="getBlogListByAJAX()" value="$+ (10000 - 50000 vnd)"> $+ (10000 - 50000 vnd)<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="price-range" onclick="getBlogListByAJAX()" value="$$ (50001 - 200000 vnd)"> $$ (50001 - 200000 vnd)<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="price-range" onclick="getBlogListByAJAX()" value="$$+ (200001 - 500000 vnd)"> $$+ (200001 - 500000 vnd)<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="price-range" onclick="getBlogListByAJAX()" value="$$$ (<500000 vnd)"> $$$ (<500000 vnd)<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-lg-12 profile-card mt-4">
                                            <h5>Chế độ ăn đặc biệt</h5><hr><br>
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" onclick="getBlogListByAJAX()" value="Vegan"> Vegan <br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" onclick="getBlogListByAJAX()" value="Glueten-free"> Gluten-free<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type"onclick="getBlogListByAJAX()" value="Low-fat"> Low-fat<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" onclick="getBlogListByAJAX()" value="Diabetic Diet"> Diabetic Diet<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" onclick="getBlogListByAJAX()" value="High-protein"> High-protein<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" onclick="getBlogListByAJAX()" value="Low-protein"> Low-protein<br>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </form>

                                </div>
                                <div class="row col-lg-8" id="advanced-blog-list-content"> 
                                    <c:forEach items="${listBlogsearch}" var="blog">
                                        <div class="col-lg-4 col-md-4 col-sm-6 mix oranges fresh-meat">
                                            <div class="featured__item">
                                                <c:forEach items="${listBlogImage}" var="image">
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
                                </div>
                            </c:when>
                        </c:choose>
                    </div><br>
                    <div id="user-content" class="row featured__filter">
                        <c:choose>
                            <c:when test="${not empty listUsersearch}">
                                <div class="col-lg-12 section-search">
                                    <p style="font-size: 30px; font-weight: bold;">Người dùng</p>
                                </div>
                                <c:forEach items="${listUsersearch}" var="user">
                                    <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
                                        <div class="featured__item">
                                            <div class="featured__item__pic">
                                                <img src="data:image/*;base64,${user.profilePicture}" alt="">
                                            </div>
                                            <div class="featured__item__text">
                                                <h6>${user.displayName}</h6>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:when test ="${empty listUsersearch}">
                                <div class="col-lg-12 section-search text-center">
                                    <p style="font-size: 30px; font-weight: bold; margin-top: 30px">Không tìm thấy người dùng</p>
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
//                                                                $(document).ready(function () {
//                                                                    $('.search-option').click(function (event) {
//                                                                        event.preventDefault(); // Prevent the default button click behavior
//
//                                                                        var action = $(this).data('action'); // Get the action (all, blog, user)
//                                                                        var txt = '<%= request.getParameter("txt") %>'; // Get the search text from server-side
//
//                                                                        $.ajax({
//                                                                            url: 'search',
//                                                                            type: 'POST',
//                                                                            data: {action: action, txt: txt},
//                                                                            success: function (response) {
//                                                                                $('#search-results').html(response);
//                                                                            },
//                                                                            error: function (xhr, status, error) {
//                                                                                console.error('Error:', status, error);
//                                                                                alert('Error occurred while searching.');
//                                                                            }
//                                                                        });
//
//                                                                    });
//                                                                }
//                                                                );

                                                                function getBlogListByAJAX() {
                                                                    var txt = document.getElementById('search-name').value;
                                                                    // Create empty arrays for each checkbox group
                                                                    var meals = [];
                                                                    var foods = [];
                                                                    var prices = [];
                                                                    var specials = [];

                                                                    var selectedFoods = document.querySelectorAll('input[name="food-type"]:checked');
                                                                    for (var x = 0; x < selectedFoods.length; x++)
                                                                    {
                                                                        foods.push(selectedFoods[x].value);
                                                                    }

                                                                    var selectedMeals = document.querySelectorAll('input[name="meal-type"]:checked');
                                                                    if (selectedMeals.length > 0) {
                                                                        for (var x = 0; x < selectedMeals.length; x++)
                                                                        {
                                                                            meals.push(selectedMeals[x].value);
                                                                        }
                                                                    }

                                                                    var selectedPrices = document.querySelectorAll('input[name="price-range"]:checked');
                                                                    for (var x = 0; x < selectedPrices.length; x++)
                                                                    {
                                                                        prices.push(selectedPrices[x].value);
                                                                    }

                                                                    var selectedSpecials = document.querySelectorAll('input[name="special-diet-type"]:checked');
                                                                    for (var x = 0; x < selectedSpecials.length; x++)
                                                                    {
                                                                        specials.push(selectedSpecials[x].value);
                                                                    }

                                                                    var data = {
                                                                        txt: txt,
                                                                        meals: meals.length > 0 ? meals.join(',') : undefined, // Send meals only if not empty
                                                                        foods: foods.length > 0 ? foods.join(',') : undefined, // Send foods only if not empty
                                                                        prices: prices.length > 0 ? prices.join(',') : undefined, // Send prices only if not empty
                                                                        specials: specials.length > 0 ? specials.join(',') : undefined // Send specials only if not empty
                                                                    };

                                                                    $.ajax({
                                                                        url: 'advanced-blog-search',
                                                                        type: 'GET',
                                                                        data: data
//                                                                          {  txt: txt,
//                                                                            meals: meals.join(','),
//                                                                            foods: foods.join(','),
//                                                                            prices: prices.join(','),
//                                                                            specials: specials.join(',')}
                                                                        ,
                                                                        success: function (response) {
                                                                            $('#advanced-blog-list-content').html(response);
                                                                        },
                                                                        error: function (xhr, status, error) {
                                                                            console.error('Error:', status, error);
                                                                            alert('Error occurred while searching.');
                                                                        }
                                                                    });

                                                                    var data = {
                                                                        foodTypes: foods,
                                                                        mealTypes: meals,
                                                                        priceRanges: prices,
                                                                        specialDiets: specials
                                                                    };

                                                                    // Log data object before sending the request
                                                                    console.log("Sending data:", data);
                                                                }
    </script>


</html>