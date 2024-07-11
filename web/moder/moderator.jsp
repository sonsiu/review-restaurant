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
        <title>Dashboard</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
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
        <!--them moi-->
        <link rel="stylesheet" href="css/dataTables.bootstrap4.min.css" />
        <!-------->
        <!-- custom css -->
        <link rel="stylesheet" href="css/custom.css" />
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <!-- Thêm vào phần <head> của tệp HTML -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    </head>

    <body class="dashboard dashboard_1">
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
                                        <div id="notificationBar" class="notification-bar"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row column1">
                                <div class="col-md-6 col-lg-3">
                                    <div class="full counter_section margin_bottom_30">
                                        <div class="couter_icon">
                                            <div> 
                                                <i class="fa fa-user yellow_color"></i>
                                            </div>
                                        </div>
                                        <div class="counter_no">
                                            <div>
                                                <p class="total_no">${sCus}</p>
                                                <p class="head_couter">Người dùng</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3">
                                    <div class="full counter_section margin_bottom_30">
                                        <div class="couter_icon">
                                            <div> 
                                                <i class="fa fa-clock-o blue1_color"></i>
                                            </div>
                                        </div>
                                        <div class="counter_no">
                                            <div>
                                                <p class="total_no">${sBlog}</p>
                                                <p class="head_couter">Bài viết</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3">
                                    <div class="full counter_section margin_bottom_30">
                                        <div class="couter_icon">
                                            <div> 
                                                <i class="fa fa-cloud-download green_color"></i>
                                            </div>
                                        </div>
                                        <div class="counter_no">
                                            <div>
                                                <p class="total_no">${sRate}</p>
                                                <p class="head_couter">Tổng đánh giá</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3">
                                    <div class="full counter_section margin_bottom_30">
                                        <div class="couter_icon">
                                            <div> 
                                                <i class="fa fa-comments-o red_color"></i>
                                            </div>
                                        </div>
                                        <div class="counter_no">
                                            <div>

                                                <p class="total_no">${sCom}</p>
                                                <p class="head_couter">Tổng bình luận</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="row">
                                        <div class="col-12">

                                            <div id="chart"></div>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- graph -->
                            <!--                            <div class="row column2 graph margin_bottom_30">
                                                            <div id="chart"></div>
                                                        </div>-->
                            <!-- end graph -->
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="card">

                                        <div class="card-body">
                                            <h4 class="card-title">Bảng người dùng</h4>
                                            <div class="active-member">
                                                <div class="table-responsive">
                                                    <table class="table table-xs mb-0">
                                                        <thead>
                                                            <tr>
                                                                <th>Tài khoản</th>
                                                                <th>Tên người dùng</th>
                                                                <th>Email</th>
                                                                <th>Trạng thái</th>

                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="u" items="${get5User}">
                                                                <tr>
                                                                    <td>${u.displayName}</td>
                                                                    <td>${u.id}</td>
                                                                    <td>${u.email}</td>

                                                                    <td>
                                                                        <c:choose>
                                                                            <c:when test="${u.status == 0}">
                                                                                <i class="fa fa-circle-o text-danger  mr-2"></i>Chưa xác minh
                                                                            </c:when>
                                                                            <c:when test="${u.status == 1}">
                                                                                <i class="fa fa-circle-o text-success  mr-2"></i>Đã kích hoạt
                                                                            </c:when>
                                                                            <c:when test="${u.status == 2}">
                                                                                <i class="fa fa-circle-o text-danger  mr-2"></i>Cấm hoạt động
                                                                            </c:when>
                                                                        </c:choose>
                                                                    </td>

                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                        
                                </div>
                            </div>
                            <!-- end progress bar -->
                        </div>

                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <h4 class="card-title">Tất cả bài viết</h4>
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered zero-configuration">
                                                    <thead>
                                                        <tr>
                                                            <th>Tiêu đề</th>
                                                            <th>Mô tả</th>
                                                            <th>Địa chỉ quán ăn</th>
                                                            <th>Vị trí</th>
                                                            <th>Trạng thái</th>
                                                            <th>Đánh giá</th>
                                                            <th>Ngày tạo</th>
                                                            <th>Báo cáo</th>
                                                            <th>Báo cáo chi tiết</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="blog" items="${listBlog}">
                                                            <tr>
                                                                <td>${blog.title}</td>
                                                                <td><button class=""><a href='<c:url value="/blog-detail?id=${blog.blogID}"/>'>Xem chi tiết</a></button></td>
                                                                <td>${blog.eateryAddress}</td>
                                                                <td>${blog.eateryLocation}</td>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${blog.status == 0}">
                                                                            <i class="fa fa-circle text-warning  mr-2"></i> Đang chờ duyệt
                                                                        </c:when>
                                                                        <c:when test="${blog.status == 1 || blog.status == 3}">
                                                                            <i class="fa fa-circle text-success  mr-2"></i>Đã được duyệt 
                                                                        </c:when>
                                                                        <c:when test="${blog.status == 2}">
                                                                            <i class="fa fa-circle text-danger  mr-2"></i>Bị từ chối (by Mod)
                                                                        </c:when>
                                                                        <c:when test="${blog.status == 4}">
                                                                            <i class="fa fa-trash text-danger  mr-2"></i>Bị xóa(by User)
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>
                                                                <td>${blog.rate}</td>
                                                                <td>${blog.date}</td>
                                                                <td>${blog.reportSum}</td>
                                                                <td><a href='<c:url value="/moder/report-detail?id=${blog.blogID}"/>'>xem chi tiết</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>

                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- #/ container -->
                    </div>
                    <!-- footer -->
                    <div class="container-fluid">
                        <div class="footer">
                            <p>Copyright © 2018 Designed by html.design. All rights reserved.<br><br>
                                Distributed By: <a href="https://themewagon.com/">ThemeWagon</a>
                            </p>
                        </div>
                    </div>
                </div>
                <!-- end dashboard inner -->
            </div>
        </div>
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
    <script>
        var ps = new PerfectScrollbar('#sidebar');
    </script>
    <!-- custom js -->
    <script src="js/chart_custom_style1.js"></script>
    <script src="js/custom.js"></script>
    <script src="https://www.google.com/jsapi"></script>
    <script>
        google.load("visualization", "1.0", {packages: ["corechart"]});
        google.setOnLoadCallback(drawChart);

        function drawChart() {
            // Get data from servlet
            var jsonData = '${requestScope.dataChart}'; // Accessing the attribute containing the data using EL

            // Parse JSON data
            var javaData = JSON.parse(jsonData);

            // Create data array dynamically
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'month');
            data.addColumn('number', 'Blog Create');
            for (var i = 0; i < javaData.length; i++) {
                data.addRow([javaData[i][0], parseInt(javaData[i][1])]);
            }

            // Create options for the chart
            var options = {
                title: "Blog Creation's Chart by month",
                curveType: "function",
                legend: {position: "bottom"}
            };

            // Draw the chart
            var chart = new google.visualization.LineChart(document.getElementById("chart"));
            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript">
        $(function () {
            "use strict";
            //BAR CHART
            var data = {
//                labels: ["January", "February", "March", "April", "May", "June", "July"],
                datasets: [

                    {
                        label: "My Second dataset",
                        fillColor: "rgba(151,187,205,0.2)",
                        strokeColor: "rgba(151,187,205,1)",
                        pointColor: "rgba(151,187,205,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(151,187,205,1)",
                        data: [28, 48, 40, 19, 86, 27, 90]
                    },
                    {
                        label: "My First dataset",
                        fillColor: "rgba(220,220,220,0.2)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(220,220,220,1)",
                        data: [65, 59, 80, 81, 56, 55, 40]
                    }
                ]
            };
            new Chart(document.getElementById("linechart").getContext("2d")).Line(data, {
                responsive: true,
                maintainAspectRatio: false,
            });

        });
        // Chart.defaults.global.responsive = true;
    </script>
    <script>
//        $(document).ready(function () {
//            toastr.options = {
//                "timeOut": "1000",
//                "extendedTimeOut": "1000", // Thời gian gia hạn thêm sau khi di chuột vào toast
//                "closeButton": true, // Hiển thị nút đóng trên toast
//                "progressBar": true // Hiển thị thanh tiến trình trên toast
//            };
//            // Hiển thị thông báo tự động khi trang web được tải
//            toastr.success('Welcome to the food review!', 'Success');
//            toastr.error('Welcome to the food review!', 'Error');
//            toastr.warning('Welcome to the food review!', 'Warning');
//            toastr.info('Welcome to the food review!, Mọi người đọc kĩ điều khoản nhé ', 'Info');
//
//        });

    </script>
    <script src="../js/jquery.dataTables.min.js"></script>
    <script src="../js/dataTables.bootstrap4.min.js"></script>
    <script src="../js/datatable-basic.min.js"></script>
</body>
</html>