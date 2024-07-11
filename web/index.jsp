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
        <link rel="icon" href="favicon.ico" type="image/x-icon" />
        <link data-n-head="ssr" rel="icon" type="image/x-icon" href="/favicon.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <style>
            .fixed-header {
                position: fixed;
                top: 0;
                width: 100%;
                z-index: 1000; /* Ensure the header is on top of other elements */
                background-color: white; /* Adjust based on your header's background */
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Optional: add a shadow for better visibility */
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




            h2{
                font-weight: bold;
            }
        </style>
    </head>

    <body>
        <input type='text' name='from' value='${from}' hidden>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!--Turn Header, Footer to a seperate jsp file   -->
        <%@include file="components/header.jsp" %>
        <!-- Hero Section Begin -->
<!--        <div>test: ${sessionScope.currentUser.id}</div>-->
        <section class="hero content-below-header" style="background-color: #F5F4F4" >
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 " >
                        <div class="hero__search">


                        </div>
                        <div class="hero__item set-bg" data-setbg="img/food-banner-breadcrumb.png">
                            <div class="hero__text ">
                                <span >HOA LAC RIVIU</span>
                                <h2 class="text-white">Reviews Giàu Thông Tin <br />& Trung Thực</h2>
                                <p class="text-white">Góc nhìn mới về ẩm thực Việt Nam </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Hero Section End -->

        <!-- Categories Section Begin DO NOT DELETE THIS SECTION !!! -->
        <%@include file="components/homepage-category.jsp" %>
        <!-- Categories Section End -->

        <!-- Featured Section Begin -->
        <section class="featured spad" style="background-color: #F5F4F4">
            <style>
                .explore-tab {
                    border-radius: 20px;
                }
                .list-group-menu li {
                    background: lightgrey;
                    border-radius: 15px;
                }
            </style>
            <div class="container explore-tab">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="title-container">
                            <h2>Khám phá</h2>
                        </div>

                        <div class="show-choice" id="show-choice">
                            <div class="choice-item" id="explore-option">Khám phá</div>
                        </div>
                        <div class="featured__controls">
                            <div
                                data-v-2551ee83=""
                                style="height: 40px; width: 100%; margin-top: 15px"
                                >
                                <ul
                                    data-v-2551ee83=""
                                    class="list-group-menu d-flex"
                                    style="width: 100%; float: left"
                                    >
                                    <li data-v-2551ee83="" id="all">
                                        <a data-v-2551ee83="" href="homepage">Tất Cả</a>
                                    </li>
                                    <c:if test = "${sessionScope.currentUser != null}">
                                        <li data-v-2551ee83="" id="follow" >
                                            <a data-v-2551ee83="" href="homepage?action=follow">Theo dõi</a>
                                        </li>
                                        <li data-v-2551ee83="" id="near" >
                                            <a data-v-2551ee83="" href="homepage?action=near">Gần bạn</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row featured__filter">
                    <c:if test ="${empty listB}">
                        <div class="text-center">
                            <h4 class="empty-text">Không có gì ở đây cả .....</h4>
                        </div>
                        <style>
                            .empty-text{
                                right: 1000px;
                            }
                        </style>
                    </c:if>

                    <c:forEach items="${listB}" var="blog" > 
                        <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
                            <a href="blog-detail?id=${blog.blogID}">
                                <div class="featured__item">
                                    <div class="featured__item__pic">
                                        <c:forEach items="${listBlogImage}" var="image">
                                            <c:if test="${image.blogID == blog.blogID}">
                                                <img src="data:image/*;base64,${image.imageLink}" alt="">
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="blog__item__text">
                                        <ul>
                                            <li><i class="fa fa-calendar-o"></i> ${blog.date}</li>
                                            <li><i class="fa fa-heart-o"></i> ${blog.like}</li>
                                        </ul>
                                        <h5><a href="blog-detail?id=${blog.blogID}">${blog.title}</a></h5>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>

                </div>

                <ul class="listPage">

                </ul>

            </div>
        </div>

    </section>


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

    <c:forEach var="notification" items="${shownNotifications}">
        <c:if test="${not empty notification.title && not empty notification.message && not empty notification.type}">
            <script>
                $(document).ready(function () {
                    toastr.options = {
                        "timeOut": "10000",
                        "extendedTimeOut": "1000",
                        "closeButton": true,
                        "progressBar": true
                    };
                    toastr.${notification.type}('${notification.message}', '${notification.title}');
                });
            </script>
        </c:if>
    </c:forEach>

    <script>
        <c:if test="${not empty notify}">
        $(document).ready(function () {
            toastr.options = {
                "timeOut": "10000",
                "extendedTimeOut": "1000",
                "closeButton": true,
                "progressBar": true
            };
            toastr.${notify.type}('${notify.title}', '${notify.message}');
        });
        </c:if>
        // Function to get URL parameters
        function getParameterByName(name, url = window.location.href) {
            name = name.replace(/[\[\]]/g, '\\$&');
            const regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)');
            const results = regex.exec(url);
            if (!results)
                return null;
            if (!results[2])
                return '';
            return decodeURIComponent(results[2].replace(/\+/g, ' '));
        }

        // Function to set the active class
        function setActiveMenu() {
            const action = getParameterByName('action');

            if (!action) {
                document.getElementById('all').classList.add('active');
            } else if (action === 'follow') {
                document.getElementById('follow').classList.add('active');
            } else if (action === 'near') {
                document.getElementById('near').classList.add('active');
            }

        }

        // Call setActiveMenu on page load
        document.addEventListener('DOMContentLoaded', setActiveMenu);
    </script>
    <!--    <script>
            document.addEventListener('DOMContentLoaded', function () {
                const items = document.querySelectorAll('.featured__filter .col-lg-3');
                const loadMoreBtn = document.getElementById('load-more');
                let visibleItems = 8;
    
                function showItems() {
                    items.forEach((item, index) => {
                        if (index < visibleItems) {
                            item.style.display = 'inline-block';
                        } else {
                            item.style.display = 'none';
                        }
                    });
                    if (visibleItems >= items.length) {
                        loadMoreBtn.style.display = 'none';
                    }
                }
    
                loadMoreBtn.addEventListener('click', function () {
                    visibleItems += 8;
                    showItems();
                });
    
                showItems(); // Initial call to show items
            });
        </script>-->
    <!--    <script>
            document.addEventListener('DOMContentLoaded', function () {
                const exploreOption = document.getElementById('explore-option');
              const showChoice = document.getElementById('show-choice');
                const sectionTitle = document.querySelector('.title-container h2');
                const listGroupMenu = document.querySelector('.list-group-menu');
                const toggleIcon = document.getElementById('toggle-icon');
                toggleIcon.addEventListener('click', function () {
                    showChoice.style.display = showChoice.style.display === 'block' ? 'none' : 'block';
                });
                // Event listener for Explore option
                exploreOption.addEventListener('click', function () {
                    sectionTitle.textContent = 'Khám phá'; // Change heading back to "Khám phá"
                    listGroupMenu.style.display = 'block';
                    showChoice.style.display = 'none'; // Hide show-choice
                    window.location.assign('homepage');
                });
            });
        </script>-->
    <script>

        let shopPage = 1;
        let shoplimit = 12;
        let shoplist = document.querySelectorAll('.featured__filter .mix');
        function loadItem() {
            let beginGet = shoplimit * (shopPage - 1);
            let endGet = shoplimit * shopPage - 1;
            shoplist.forEach((item, key) => {
                if (key >= beginGet && key <= endGet) {
                    item.style.display = 'block';
                } else {
                    item.style.display = 'none';
                }
            });
            listPage();
        }
        loadItem();
        function listPage() {
            let count = Math.ceil(shoplist.length / shoplimit);
            document.querySelector('.listPage').innerHTML = '';
            if (shopPage !== 1) {
                let prev = document.createElement('li');
                prev.innerHTML = '<i class="fa fa-arrow-left"></i>';
                prev.setAttribute('onclick', "changPage(" + (shopPage - 1) + ")");
                document.querySelector('.listPage').appendChild(prev);

            }
            for (i = 1; i <= count; i++) {
                let newPage = document.createElement('li');
                newPage.innerText = i;
                if (i === shopPage) {
                    newPage.classList.add('active');
                }
                newPage.setAttribute('onclick', "changPage(" + i + ")");
                document.querySelector('.listPage').appendChild(newPage);
            }
            if (shopPage !== count) {
                let next = document.createElement('li');
                next.innerHTML = '<i class="fa fa-arrow-right"></i>';
                next.setAttribute('onclick', "changPage(" + (shopPage + 1) + ")");
                document.querySelector('.listPage').appendChild(next);

            }
        }
        function changPage(i) {
            shopPage = i;
            loadItem();
        }
    </script>
    <script>
        document.querySelectorAll('.category-link').forEach(function (link) {
            link.addEventListener('click', function (event) {
                var overlayText = this.querySelector('.text-overlay').innerText;
                localStorage.setItem('overlayText', overlayText);
            });
        });
    </script>
</body>

</html>