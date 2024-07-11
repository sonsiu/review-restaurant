
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
                                        <!--<h2>Tables</h2>-->
                                    </div>
                                </div>
                            </div>
                            <!-- row -->
                            <div class="row">
                                <!-- table section -->

                                <div class="white_shd full margin_bottom_30">
                                    <div class="full graph_head">
                                        <div class="heading1 margin_0">
                                            <h2>Duyệt bài viết</h2>
                                        </div>
                                    </div>
                                    <div class="table_section padding_infor_info">
                                        <div class="table-responsive-sm">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>ID bài viết</th>
                                                        <th>ID người dùng</th>
                                                        <th>Mô tả</th>
                                                        <th>Ảnh hóa đơn</th>
                                                        <th>Đánh giá</th>
                                                        <th>Trạng thái</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="l" items="${blogL}">
                                                        <tr>
                                                            <td>${l.blogID}</td>
                                                            <td>${l.userID}</td>
                                                            <td><button class=""><a href='<c:url value="/blog-detail?id=${l.blogID}"/>'>Xem chi tiết</a></button></td>
                                                    <div class="popup-container">
                                                        <div class="popup-box">
                                                            <p>${l.description}</p> 
                                                            <button class="close-btn"> OK</button>
                                                        </div>
                                                    </div>
                                                    <td><button class="show-image">Xem ảnh hóa đơn</button></td>
                                                    <div class="bill-container" style="position: fixed;top: 60%;left: 60%;transform: translate(-50%, -50%);">
                                                        <div class="bill-box" style="border: 1px solid black ">
                                                            <img src="data:image/*;base64,${l.billLink}"  style="width: 540px;height: 480px"alt="">
                                                            <button class="close-btn-image"> OK</button>
                                                        </div>
                                                    </div>
                                                    <td>${l.rate}</td>
                                                    <td>
                                                        <c:if test="${l.status==1}">Approved</c:if>
                                                        <c:if test="${l.status==2}">Rejected</c:if>
                                                        <c:if test="${l.status==0}">
                                                            <button onclick="approveBlog(this,${l.blogID}, 1)">Chấp nhận</button>
                                                            <button id="rejectBtn" type="button" class="model_bt btn btn-primary" data-toggle="modal" data-target="#myModal${l.blogID}">từ chối</button>
                                                        </c:if>

                                                    </td>

                                                    </tr>
                                                </c:forEach>
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
                <c:forEach var="l" items="${blogL}">
                    <div class="modal fade" id="myModal${l.blogID}">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Modal Heading</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <form action="verifyBlog" method="post">
                                        Reason : <input type="text" name="rejectMessage" id="rejectMessage" value="" requried>
                                        <input type="hidden" name="id" value="${l.blogID}" requried>
                                        <!-- Modal footer -->
                                        <div class="modal-footer">
                                            <button type="submit"> Confirm</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
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
                                                                function approveBlog(btn, bid, s) {
                                                                    var text = btn.textContent;
                                                                    btn.parentElement.innerHTML = text;

                                                                    $.ajax({
                                                                        url: 'verifyBlog',

                                                                        type: 'GET',
                                                                        data: {
                                                                            id: bid,
                                                                            status: s
                                                                        },
                                                                        success: function (response) {
                                                                        }
                                                                    });
                                                                }
//                                   function rejectBlog(bid, s) {
//                                       var text = document.getElementById("rejectMessage").value;
//                                       var btn = document.getElementById("rejectBtn").textContent;
//                                       btn.parentElement.innerHTML = btn;
//                                       $.ajax({
//                                           url: 'rejectBlog',
//
//                                           type: 'GET',
//                                           data: {
//                                               id: bid,
//                                               status: s,
//                                               message: text
//                                           },
//                                           success: function (response) {
//                                           }
//                                       });
//
//
//                                   }
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