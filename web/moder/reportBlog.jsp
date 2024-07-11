
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Verify Blogs</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <!-- site icon -->
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- site css -->
        <link rel="stylesheet" href="style.css" />
        <!-- responsive css -->
        <link rel="stylesheet" href="css/responsive.css" />
        <!-- color css -->
        <link rel="stylesheet" href="css/colors.css" />
        <!-- select bootstrap -->
        <link rel="stylesheet" href="css/bootstrap-select.css" />
        <!-- scrollbar css -->
        <link rel="stylesheet" href="css/perfect-scrollbar.css" />
        <!-- custom css -->
        <link rel="stylesheet" href="css/custom.css" />
        <!-- calendar file css -->
        <link rel="stylesheet" href="js/semantic.min.css" />
        <!-- fancy box js -->
        <link rel="stylesheet" href="css/jquery.fancybox.css" />
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <style>
            .inner_page.tables_page {
                font-family: 'Roboto', sans-serif;
            }
        </style>
    </head>
    <body class="inner_page tables_page">
        <div class="full_container">
            <div class="inner_container">
                <!-- Sidebar  -->
                <%@include file="components/sidebar.jsp" %>
                <!-- end sidebar -->
                <!-- right content -->
                <div id="content">
                    <!-- topbar -->
                    <%@include file="components/topbar.jsp" %>
                    <!-- end topbar -->
                    <!-- dashboard inner -->
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title">

                                    </div>
                                </div>
                            </div>
                            <!-- row -->
                            <div class="row">
                                <!-- table section -->

                                <div class="white_shd full margin_bottom_30">
                                    <div class="full graph_head">
                                        <div class="heading1 margin_0">
                                            <h2>Thông tin tố cáo của bài viết</h2>
                                        </div>
                                    </div>
                                    <div class="table_section padding_infor_info">
                                        <div class="table-responsive-sm">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>ID bài viết</th>
                                                        <th>Người tố cáo</th>
                                                        <th>Thông tin bài viết</th>
                                                        <th>Lý do tố cáo</th>
                                                        <th>Lượt tố cáo</th>
                                                        <th>Trạng thái</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>${blog.blogID}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${empty reporters}">
                                                                    Không có
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <ul>
                                                                        <c:forEach var="reporter" items="${reporters}">
                                                                            <li>- ${reporter.displayName}</li>
                                                                            </c:forEach>
                                                                    </ul>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <button class="show-popup"><a href='<c:url value="/blog-detail?id=${blog.blogID}"/>'>Hiển thị bài viết</a></button>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${empty reportReasons}">
                                                                    Không có
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <ul>
                                                                        <c:forEach var="reportReason" items="${reportReasons}">
                                                                            <li>- ${reportReason}</li>
                                                                            </c:forEach>
                                                                    </ul>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td style="text-align: center;">${blog.reportSum}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${blog.status == 2}">Bài viết đã bị ẩn</c:when>
                                                                <c:when test="${blog.status == 1}">Không bị tố cáo</c:when>
                                                                <c:when test="${blog.status == 4}">Bài viết đã bị xóa</c:when>
                                                                <c:otherwise>
                                                                    <button onclick="handleBlogReport(${blog.blogID}, 2)">Duyệt đơn tố cáo</button>
                                                                    <button onclick="handleBlogReport(${blog.blogID}, 1)">Hủy đơn tố cáo</button>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>


                            </div>
                            <!-- footer -->

                        </div>
                        <!-- end dashboard inner -->
                    </div>
                </div>
                <!-- model popup -->
                <!-- The Modal -->

                <!-- end model popup -->
            </div>
            <!-- jQuery -->
            <script src="js/jquery.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <!-- wow animation -->
            <script src="js/animate.js"></script>
            <!-- select country -->
            <script src="js/bootstrap-select.js"></script>
            <!-- owl carousel -->
            <script src="js/owl.carousel.js"></script> 
            <!-- chart js -->
            <script src="js/Chart.min.js"></script>
            <script src="js/Chart.bundle.min.js"></script>
            <script src="js/utils.js"></script>
            <script src="js/analyser.js"></script>
            <!-- nice scrollbar -->
            <script src="js/perfect-scrollbar.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script>
                                                                        function handleBlogReport(blogID, status) {
                                                                            $.ajax({
                                                                                url: '/FoodEateryReview/approveblogreport', 
                                                                                type: 'POST',
                                                                                data: {
                                                                                    id: blogID,
                                                                                    status: status
                                                                                },
                                                                                success: function (response) {
                                                                                    console.log("Success response:", response); // Log for debugging
                                                                                    // Reload the page or update the UI as needed
                                                                                    location.reload(); // For example, reload the page after successful handling
                                                                                }
                                                                            });
                                                                        }
            </script>
            <script>
                var ps = new PerfectScrollbar('#sidebar');
            </script>
            <!-- fancy box js -->
            <script src="js/jquery-3.3.1.min.js"></script>
            <script src="js/jquery.fancybox.min.js"></script>
            <!-- custom js -->
            <script src="js/custom.js"></script>
            <!-- calendar file css -->    
            <script src="js/semantic.min.js"></script>
            <script src="../js/popup.js"></script>
    </body>
</html>