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
        <title>Ogani | Template</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/comment.css" type="text/css">
        <link rel="stylesheet" href="css/newcss.css">
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

        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <style>


            .blog__details__hero__text h2 {
                font-size: 36px;
                font-weight: 600;
                color: #333;
            }

            .blog__details__hero__text ul li {
                display: inline-block;
                margin-right: 15px;
                color: #999;
            }

            .blog__details__content {
                border: 1px solid #eaeaea;
                padding: 15px;
                border-radius: 10px;
                background: #fff;
                display: flex;
                justify-content: center;



            }
            .blog__details__content h3 {
                font-size: 24px;
                font-weight: bold; /* In đậm */
                margin-bottom: 10px;
            }


            .blog__details__content p {
                font-size: 16px;
                line-height: 1.8;
                color: #555;

            }

            .blog__details__description {
                background: white;
                padding: 20px;
                border-radius: 10px;
                margin-top: 20px;
                position: relative;
            }

            .blog__details__description.collapsed {
                max-height: 400px;
                overflow: hidden;
            }

            .details {
                border: 1px solid #eaeaea;
                padding: 20px;
                border-radius: 10px;
                background: #fff;
                text-align: left;
                margin-top: 20px;
            }

            .details h3 {
                font-size: 24px;
                margin-bottom: 10px;
            }



        </style>
    </head>

    <body>

        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!--Turn Header, Footer to a separate jsp file   -->
        <%@include file="components/header.jsp" %>
        <%@include file="components/modal/modal-report-blog.jsp" %>
        <!-- Blog Details Hero Begin -->
        <section class="blog-details-hero set-bg" data-setbg="img/blog/details/details-hero.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="blog__details__hero__text">
                            <h2>${notification.title}</h2>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Blog Details Hero End -->

        <!-- Blog Details Section Begin -->
        <section class="blog-details spad" style="background-color: #F5F4F4">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">

                        <div class="blog__details__description">
                            <div class="row" >
                                <a href="blog-detail?id=${notification.blogId}"><h4>${notification.message}</h4></a>
                            </div>

                        </div>

                        <div class="blog__details__description">
                            <h6>Created on : ${notification.timestamp}   </h6>
                        </div>

                    </div>


                </div>
        </section>
        <div style="display: none">
            <%@include file="components/commentsection.jsp" %>
        </div>

        <!-- Related Blog Section Begin -->
        <section class="related-blog spad" style="background-color: #F5F4F4">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title related-blog-title">
                            <h2>Post You May Like</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic">
                                <img src="img/blog/blog-1.jpg" alt="">
                            </div>
                            <div class="blog__item__text">
                                <ul>
                                    <li><i class="fa fa-calendar-o"></i> May 4,2019</li>
                                    <li><i class="fa fa-comment-o"></i> 5</li>
                                </ul>
                                <h5><a href="#">Cooking tips make cooking simple</a></h5>
                                <p>Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic">
                                <img src="img/blog/blog-2.jpg" alt="">
                            </div>
                            <div class="blog__item__text">
                                <ul>
                                    <li><i class="fa fa-calendar-o"></i> May 4,2019</li>
                                    <li><i class="fa fa-comment-o"></i> 5</li>
                                </ul>
                                <h5><a href="#">6 ways to prepare breakfast for 30</a></h5>
                                <p>Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic">
                                <img src="img/blog/blog-3.jpg" alt="">
                            </div>
                            <div class="blog__item__text">
                                <ul>
                                    <li><i class="fa fa-calendar-o"></i> May 4,2019</li>
                                    <li><i class="fa fa-comment-o"></i> 5</li>
                                </ul>
                                <h5><a href="#">Visit the clean farm in the US</a></h5>
                                <p>Sed quia non numquam modi tempora indunt ut labore et dolore magnam aliquam quaerat </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Related Blog Section End -->

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
                var blogID = "${blog.blogID}";
                var isBookmarked = localStorage.getItem('bookmark_' + blogID);

                // Kiểm tra trạng thái bookmark và cập nhật nút tương ứng
                if (isBookmarked) {
                    $("#bookmarkBtn").text("Unbookmark");
                    $("#bookmarkBtn").addClass("btn-danger").removeClass("btn-primary");
                    ; // Thêm class để đổi màu
                } else {
                    $("#bookmarkBtn").text("Bookmark");
                    $("#bookmarkBtn").addClass("btn-primary").removeClass("btn-danger"); // Loại bỏ class để đổi màu
                }
            });

            function toggleBookmark() {
                var blogID = "${blog.blogID}";
                var isBookmarked = localStorage.getItem('bookmark_' + blogID);

                if (isBookmarked) {
                    // Nếu đã bookmark, gửi yêu cầu unbookmark
                    $.ajax({
                        type: "POST",
                        url: "bookmark",
                        data: {action: "remove", blogID: blogID},
                        success: function (response) {
                            alert("Blog unbookmarked successfully!");
                            // Sau khi unbookmark thành công, cập nhật nút và xóa trạng thái bookmark khỏi Local Storage
                            $("#bookmarkBtn").text("Bookmark");
                            $("#bookmarkBtn").removeClass("btn-danger").addClass("btn-primary"); // Loại bỏ class để đổi màu
                            localStorage.removeItem('bookmark_' + blogID);
                        },
                        error: function (xhr, status, error) {
                            alert("Error occurred while unbookmarking blog.");
                        }
                    });
                } else {
                    // Nếu chưa bookmark, gửi yêu cầu bookmark
                    $.ajax({
                        type: "POST",
                        url: "bookmark",
                        data: {action: "add", blogID: blogID},
                        success: function (response) {
                            alert("Blog bookmarked successfully!");
                            // Sau khi bookmark thành công, cập nhật nút và lưu trạng thái bookmark vào Local Storage
                            $("#bookmarkBtn").text("Unbookmark");
                            $("#bookmarkBtn").addClass("btn-danger").removeClass("btn-primary"); // Thêm class để đổi màu
                            localStorage.setItem('bookmark_' + blogID, true);
                        },
                        error: function (xhr, status, error) {
                            alert("Error occurred while bookmarking blog.");
                        }
                    });
                }
            }

        </script>


        <!-- Inside the button to trigger bookmark action -->

    </body>

</html>
