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
        <title>Bài viết</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link rel="stylesheet" href="css/blog-detail.css" type="text/css">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    </head>

    <style>
        #comment-icon-${blog.blogID} {
            color: black; /* Default color */
        }

        #comment-icon-${blog.blogID}:hover {
            color: black; /* Color on hover */
        }

        #comment-icon-${blog.blogID}:active {
            color: black; /* Color on click */
        }

        #flag-icon-${blog.blogID} {
            color: black; /* Default color */
        }

        #flag-icon-${blog.blogID}:hover {
            color: black; /* Color on hover */
        }

        #flag-icon-${blog.blogID}:active {
            color: black; /* Color on click */
        }
        .rating-section .individual-ratings div .rating-bar-food::after {
            content: '';
            height: 100%;
            background: #ffc107;
            border-radius: 5px;
            position: absolute;
            top: 0;
            left: 0;
            width : ${blog.foodQualityRate /5 * 100}%;
        }

        .rating-section .individual-ratings div .rating-bar-environment::after {
            content: '';
            height: 100%;
            background: #ffc107;
            border-radius: 5px;
            position: absolute;
            top: 0;
            left: 0;
            width : ${blog.environmentRate /5 * 100}%;
        }

        .rating-section .individual-ratings div .rating-bar-service::after {
            content: '';
            height: 100%;
            background: #ffc107;
            border-radius: 5px;
            position: absolute;
            top: 0;
            left: 0;
            width : ${blog.serviceRate /5 * 100}%;
        }

        .rating-section .individual-ratings div .rating-bar-pricing::after {
            content: '';
            height: 100%;
            background: #ffc107;
            border-radius: 5px;
            position: absolute;
            top: 0;
            left: 0;
            width : ${blog.pricingRate /5 * 100}%;
        }

        .rating-section .individual-ratings div .rating-bar-hygiene::after {
            content: '';
            height: 100%;
            background: #ffc107;
            border-radius: 5px;
            position: absolute;
            top: 0;
            left: 0;
            width : ${blog.hygienRate /5 * 100}%;
        }
    </style>
    <body>

        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!--Turn Header, Footer to a separate jsp file   -->
        <%@include file="components/header.jsp" %>
        <%@include file="components/modal/modal-report-blog.jsp" %>
        <!-- Blog Details Hero Begin -->
        <section class="blog-details-hero set-bg" data-setbg="img/food-banner-breadcrumb.png">
            <div class="container">
            </div>
        </section>
        <!-- Blog Details Hero End -->

        <!-- Blog Details Section Begin -->
        <section class="blog-details spad" style="background-color: #F5F4F4">
            <div class="container">
                <div class="row">
                    <div class="col-lg-1">

                        <div class="profile-card mt-4" style="display: flex; flex-direction: column; align-items: center;">
                            <!-- Like icon with tooltip -->
                            <a href="#" onclick="likeBlog(event, ${blog.blogID})" title="${sessionScope.currentUser != null && hasLiked ? 'Không thích bài này' : 'Thích bài này'}" data-toggle="tooltip">
                                <i class="fa ${sessionScope.currentUser != null && hasLiked ? 'fa-heart' : 'fa-heart-o'}" id="heart-icon-${blog.blogID}"
                                   style="font-size: 24px; margin-bottom: 30px; ${sessionScope.currentUser != null && hasLiked ? 'color: red;' : ''}"></i>
                            </a>
                            <span id="like-count-${blog.blogID}" style="font-size: 24px; margin-bottom: 30px;" title="${blog.like} người thích bài này">${blog.like}</span>

                            <!-- Comment icon with tooltip -->
                            <a href="#comment-section-${blog.blogID}" title="Đi đến mục bình luận">
                                <i class="fa fa-comment-o" id="comment-icon-${blog.blogID}" style="font-size: 24px; margin-bottom: 30px;"></i>
                            </a>

                            <span id="comment-count-${blog.blogID}" style="font-size: 24px; margin-bottom: 30px;" title="${blog.commentCount} bình luận trong bài này">${blog.commentCount}</span>


                            <!-- Report icon with tooltip -->
                            <c:if test="${!blogReported && !hasReported && sessionScope.currentUser != null && sessionScope.currentUser.id != blog.userID}">
                                <a href="#" data-toggle="modal" data-target="#modal-report-blog" id="reportButton" title="Tố cáo bài này">
                                    <i class="fa fa-flag-o" id="flag-icon-${blog.blogID}" style="font-size: 24px; margin-bottom: 30px;"></i>
                                </a>
                            </c:if>

                            <!-- Bookmark icon with tooltip -->
                            <c:if test="${sessionScope.currentUser != null && sessionScope.currentUser.id != blog.userID && sessionScope.currentUser.role == 2}">
                                <c:if test="${isBookmarked}">
                                    <i class="fa fa-bookmark bookmark-icon" id="bookmark-icon" title="Xóa dấu trang bài này" style="color: #f5c842; font-size: 24px; margin-bottom: 30px; cursor: pointer;"></i>                           
                                </c:if>
                                <c:if test="${!isBookmarked}">
                                    <i class="fa fa-bookmark-o bookmark-icon" id="bookmark-icon" title="Đánh dấu trang bài này" style="color: grey; font-size: 24px; margin-bottom: 30px; cursor: pointer;"></i>                           
                                </c:if>
                            </c:if>
                        </div> 

                    </div>
                    <script>

                        const toggleButton = document.getElementById('bookmark-icon');
                        toggleButton.addEventListener('click', function () {
                            const isFollowing = toggleButton.classList.contains('btn-danger');

                            if (toggleButton.style.color == "grey") {
                                toggleButton.style.color = "#f5c842";
                                toggleButton.classList.remove('fa-bookmark-o');
                                toggleButton.classList.add('fa-bookmark');
                                toggleButton.setAttribute('title', 'Xóa dấu trang bài này');

                                changeStatus("${blog.blogID}", "add");

                            } else {
                                toggleButton.style.color = "grey";
                                toggleButton.classList.remove('fa-bookmark');
                                toggleButton.classList.add('fa-bookmark-o');
                                toggleButton.setAttribute('title', 'Đánh dấu trang bài này');

                                changeStatus("${blog.blogID}", "remove");
                            }
                        });

// Function to change the bookmark status in the backend
                        function changeStatus(blogID, action) {
                            $.ajax({
                                url: 'bookmark',
                                type: 'POST',
                                data: {
                                    blogID: blogID,
                                    action: action
                                },
                                success: function (response) {
                                    // Handle success response if needed
                                }
                            });
                        }
                    </script>
                   <div class="col-lg-7">
                        <div class="container">
                            <div id="myCarousel" class="carousel slide" style="width: 100%;">


                                <div class="carousel-inner">
                                    <c:forEach var="image" items="${images}" varStatus="status">
                                        <div class="item ${status.first ? 'active' : ''}">
                                            <img src="data:image/*;base64,${image.imageLink}" style="width:100%;">
                                        </div>
                                    </c:forEach>
                                </div>

                                <!-- Left and right controls -->
                                <a class="left carousel-control" href="#myCarousel" role="button" onclick="plusSlides(-1)">
                                    <span class="glyphicon glyphicon-chevron-left"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control" href="#myCarousel" role="button" onclick="plusSlides(1)">
                                    <span class="glyphicon glyphicon-chevron-right"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>

                            <!-- Thumbnails -->
                            <div id="thumbnails" class="thumbnails" style="display: flex; justify-content: center; margin-top: 10px;">
                                <c:forEach var="image" items="${images}" varStatus="status">
                                    <div class="thumbnail-item ${status.first ? 'active' : ''}" data-slide-to="${status.index}" style="margin: 0 5px; cursor: pointer;">
                                        <img src="data:image/*;base64,${image.imageLink}" style="width: 100px; height: 100px; object-fit: cover;">
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <c:if test ="${sessionScope.currentUser.role == 1}">
                            <div class="blog__details__description">
                                <p>Tạo vào : ${blog.date}   </p>
                                <p>Địa điểm : ${blog.eateryLocation}</p>
                                <p>Địa chỉ cụ thể : ${blog.eateryAddress}</p>
                            </div>
                        </c:if>
                        <div id="description" class="blog__details__description collapsed">
                            <p>${blog.date}</p>
                            <h3 style="font-weight : bold;">${blog.title}</h3>
                            <p>Viết bởi <strong>${user.displayName}</strong></p><hr>
                            <p>${blog.description}</p>
                        </div>
                        <span id="readMoreBtn" class="read-more">Đọc thêm</span>
                        <!--                        <form action="bookmark" method="post">
                                                   <input type="hidden" name="blogID" value="$blog.blogID">
                                                    <button type="submit">Bookmark</button>
                                                </form>-->
                    </div>

                    <div class="col-lg-4 col-md-5 order-md-1 order-2">
                        <div class="profile-card mt-4">
                            <a href="showblog?ID=${user.id}">
                                <img src="data:image/*;base64,${user.profilePicture}" alt="User Profile Picture">
                                <h4>${user.displayName}</h4>
                                <p>@${user.id}</p>
                                <div class="stats">
                                    <div>

                                        <p>Bài viết</p>
                                        <p style="font-weight:bold;">
                                            ${totalBlogs}
                                        </p>
                                    </div>
                                    <div>

                                        <p>Đang theo dõi</p>
                                        <p style="font-weight:bold;">${following}</p>
                                    </div>
                                    <div>

                                        <p>Người theo dõi</p>
                                        <p style="font-weight:bold;">${follower}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div id="rating" class="rating-section">
                            <h3>Điểm Rate</h3>
                            <div class="overall-rating">
                                <span>${blog.rate}/5.0</span>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="individual-ratings">
                                <div>
                                    <span>Chất lượng</span>
                                    <div class="rating-bar rating-bar-food" style="width :100%" >
                                        <div></div>
                                    </div>
                                    <span>${blog.foodQualityRate}</span>
                                </div>
                                <div>
                                    <span>Không gian</span>
                                    <div class="rating-bar rating-bar-environment" style="width :100%" >
                                        <div></div>
                                    </div>
                                    <span>${blog.environmentRate}</span>
                                </div>
                                <div>
                                    <span>Phục vụ</span>
                                    <div class="rating-bar rating-bar-service" style="width :100%" >
                                        <div ></div>
                                    </div>
                                    <span>${blog.serviceRate}</span>
                                </div>
                                <div>
                                    <span>Giá cả</span>
                                    <div class="rating-bar rating-bar-pricing" style="width :100%">
                                        <div ></div>
                                    </div>
                                    <span>${blog.pricingRate}</span>
                                </div>
                                <div>
                                    <span>Vệ sinh</span>
                                    <div class="rating-bar rating-bar-hygiene" style="width :100%" >
                                        <div style="width: 100%;"></div>
                                    </div>
                                    <span>${blog.hygienRate}</span>
                                </div>
                            </div>
                        </div>

                        <div class="details">
                            <h3>Địa điểm</h3>
                            <iframe src="https://www.google.com/maps/embed/v1/place?key=AIzaSyCJnsbQBxAy1goHWDsu5bzOqO4epRMtJqw&q='${blog.eateryAddress},${blog.eateryLocation}'" width="320" height="300" allowfullscreen></iframe>
                        </div>

                        <div class="tags">
                            <h3>Tags</h3>
                            <div class="tags-list">
                                <c:if test = "${empty hashTag}">
                                    <p>(None)</p>
                                </c:if>
                                <c:forEach var="tag" items="${hashTag}">
                                    <a class="tag" href="show-by-hashtag?hashtag=%23${tag.infoValue}">
                                        <span>#${tag.infoValue}</span>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="details">
                            <h3>Danh mục</h3>
                            <p><strong>Ẩm thực</strong><br>|
                                <c:forEach var="food" items="${foodType}">
                                    ${food.infoValue} |
                                </c:forEach>
                            </p>
                            <p><strong>Chế độ ăn đặc biệt </strong><br>|
                                <c:if test = "${empty specialDiet}">
                                    None |
                                </c:if>
                                <c:forEach var="diet" items="${specialDiet}">
                                    ${diet.infoValue} |
                                </c:forEach>
                            </p>
                            <p><strong>Phục vụ bữa</strong><br>|
                                <c:forEach var="meal" items="${mealType}">
                                    ${meal.infoValue} |
                                </c:forEach>
                            </p>
                        </div>


                    </div>

                </div>
            </div>
        </section>
        <!-- Blog Details Section End -->
        <div id="comment-section-${blog.blogID}" style="background-color: #F5F4F4">
            <%@include file="components/commentsection.jsp" %>
        </div>

        <!--Turn Header, Footer to a separate jsp file   -->
        <div style="background-color: #F5F4F4">
            <%@include file="components/footer.jsp" %>

        </div>

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
        <script>
                        $(document).ready(function () {
                            $('#readMoreBtn').click(function () {
                                $('#description').toggleClass('collapsed');
                                if ($('#description').hasClass('collapsed')) {
                                    $('#readMoreBtn').text('Read More');
                                } else {
                                    $('#readMoreBtn').text('Read Less');
                                }
                            });
                        });
        </script>
        <!-- Add jQuery library -->


        <script>
            $(document).ready(function () {
                // Hover effect to change heart icon on hover
                $('[id^=heart-icon]').hover(
                        function () {
                            $(this).addClass('fa-heart').removeClass('fa-heart-o');
                        },
                        function () {
                            if (!$(this).hasClass('liked')) { // Prevent changing back to fa-heart-o if it's liked
                                $(this).removeClass('fa-heart').addClass('fa-heart-o');
                            }
                        }
                );
            });
// Click event to like the blog
            function likeBlog(event, blogID) {
                event.preventDefault();
                if (${sessionScope.currentUser != null}) {
                    $.ajax({
                        url: 'likeblog',
                        type: 'POST',
                        data: {blogID: blogID},
                        success: function (response) {
                            var likeCount = response.likeCount;
                            var hasLiked = response.hasLiked;
                            var heartIcon = $('#heart-icon-' + blogID);
                            var likeButton = heartIcon.closest('a');

                            if (hasLiked) {
                                heartIcon.removeClass('fa-heart-o').addClass('fa-heart').css('color', 'red').addClass('liked');
                                likeButton.attr('title', 'Không thích bài này');
                            } else {
                                heartIcon.removeClass('fa-heart liked').addClass('fa-heart-o').css('color', '');
                                likeButton.attr('title', 'Thích bài này');
                            }

                            $('#like-count-' + blogID).text(likeCount);
                        },
                        error: function (xhr) {
                            alert('Không thể thích bài này');
                        }
                    });
                } else {
                    window.location.href = 'login';
                }
            }
        </script>
<script>
            let slideIndex = 1;
            showSlides(slideIndex);

            // Next/previous controls
            function plusSlides(n) {
                showSlides(slideIndex += n);
            }

            // Thumbnail image controls
            function currentSlide(n) {
                showSlides(slideIndex = n);
            }

            function showSlides(n) {
                let i;
                let slides = document.getElementsByClassName("item");
                let dots = document.getElementsByClassName("thumbnail-item");
                if (n > slides.length) {
                    slideIndex = 1
                }
                if (n < 1) {
                    slideIndex = slides.length
                }
                for (i = 0; i < slides.length; i++) {
                    slides[i].style.display = "none";
                    slides[i].className = slides[i].className.replace(" active", "");
                }
                for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" active-thumbnail", "");
                }
                slides[slideIndex - 1].style.display = "block";
                slides[slideIndex - 1].className += " active";
                dots[slideIndex - 1].className += " active-thumbnail";
            }

            $(document).ready(function () {
                // Ensure the carousel transitions when a thumbnail is clicked
                $('.thumbnail-item').on('click', function () {
                    var index = $(this).data('slide-to');
                    currentSlide(index + 1); // +1 because slideIndex starts from 1
                });

                // Synchronize the thumbnail active state with the carousel
                $('#myCarousel').on('slide.bs.carousel', function (e) {
                    var nextIndex = $(e.relatedTarget).index();
                    currentSlide(nextIndex + 1); // +1 because slideIndex starts from 1
                });
            });
        </script>

        <!-- Inside the button to trigger bookmark action -->

    </body>

</html>
