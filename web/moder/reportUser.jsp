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
        <title>Report User</title>
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
                                        <h2></h2>
                                    </div>
                                </div>
                            </div>
                            <!-- row -->
                            <div class="row">
                                <!-- table section -->

                                <div class="white_shd full margin_bottom_30">
                                    <div class="full graph_head">
                                        <div class="heading1 margin_0">
                                            <h2>Thông tin tố cáo của người dùng</h2>
                                        </div>
                                    </div>
                                    <div class="table_section padding_infor_info">
                                        <div class="table-responsive-sm">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Tên hiển thị</th>
                                                        <th>Người báo cáo</th>
                                                        <th>Lí do báo cáo</th>
                                                        <th>Số lượng báo cáo</th>
                                                        <th>Trạng thái</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>${user.displayName}</td>
                                                        <td>
                                                            <ul>
                                                                <c:forEach var="reporter" items="${reporters}">
                                                                    <li>${reporter.displayName}</li>
                                                                    </c:forEach>
                                                            </ul>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${empty reportReasons}">
                                                                    None
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
                                                        <td>${reportCount}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${user.status == 2}">Tài khoản của người dùng đã bị vô hiệu hóa</c:when>
                                                                <c:when test="${user.status == 1}">Hiện tại chưa có báo cáo nào</c:when>
                                                                <c:otherwise>
                                                                    <button onclick="handleUserStatus('${user.id}', 2)">Đồng ý</button>
                                                                    <button onclick="handleUserStatus('${user.id}', 1)">Không chấp nhận</button>
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
                            <div class="container-fluid">
                                <div class="footer">
                                    <p>Copyright © 2018 Designed by html.design. All rights reserved.</p>
                                </div>
                            </div>
                        </div>
                        <!-- end dashboard inner -->
                    </div>
                </div>
                <!-- model popup -->
                <!-- The Modal -->
                <div class="modal fade" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Modal Heading</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <!-- Modal body -->
                            <div class="modal-body">
                                Modal body..
                            </div>
                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
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
            <script>
                            function handleUserStatus(userId, status) {
                                console.log("handleUserStatus called with userId:", userId, "status:", status); // Log for debugging

                                $.ajax({
                                    url: 'user-status', // Ensure this URL matches your servlet mapping
                                    type: 'POST',
                                    data: {
                                        userId: userId,
                                        status: status
                                    },
                                    success: function (response) {
                                        console.log("Success response:", response); // Log for debugging
                                        location.reload(); // Reload page after successful update
                                    },
                                    error: function (xhr, status, error) {
                                        console.error("Failed to handle user status. Status: " + xhr.status + ", Error: " + error);
                                        // Handle error or display error message to the user
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
        </div>
    </body>
</html>
