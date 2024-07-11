
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
        <!-- Thêm vào phần <head> của tệp HTML -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
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
                        <!-- row -->
                        <div class="row">
                            <!-- table section -->
                            <div class="col-md-12">
                                <div class="white_shd full margin_bottom_30">
                                    <div class="full inbox_inner_section">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="full padding_infor_info">
                                                    <div class="mail-box">
                                                        <aside class="lg-side">
                                                            <div class="inbox-head">
                                                                <h3 id="userCount">Users (${count})</h3>
                                                                <form action="#" class="pull-right position search_inbox">
                                                                    <div class="input-append">
                                                                        <input type="text" class="sr-input" oninput="searchByName(this)" type="text" name="txt" placeholder="Search User's Name">
                                                                        <button class="btn sr-btn" type="button"><i class="fa fa-search"></i></button>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                            <div class="inbox-body" style="overflow-y :scroll; height:  475px">
                                                                <div class="mail-option">
                                                                    <div class="chk-all">
                                                                        <div class="btn-group">
                                                                            <select class="" id="statuslist" onchange="changeListStatus()">
                                                                                <!--                                                                                     <option value=""> Select range</option>
                                                                                                                                                                    <li id="verified"><a href="#"> Verified</a></li>
                                                                                                                                                                    <li id="unverified"><a href="#"> Unverified</a></li>-->
                                                                                <option value="all"> All</option>
                                                                                <option value="activated"> Activated</option>
                                                                                <option value="deactivated"> Deactivated/Unverified</option>

                                                                            </select>

                                                                            <input type="hidden" id="status" name="status" value="all">
                                                                        </div>
                                                                    </div>
                                                                    <div class="btn-group">
                                                                        <a data-original-title="Refresh" data-placement="top" data-toggle="dropdown" href="#" class="btn mini tooltips">
                                                                            <i class=" fa fa-refresh"></i>
                                                                        </a>
                                                                    </div>
                                                                    <ul class="unstyled inbox-pagination">
                                                                        <li><span>1-50 of 234</span></li>
                                                                        <li>
                                                                            <a class="np-btn" href="#"><i class="fa fa-angle-left  pagination-left"></i></a>
                                                                        </li>
                                                                        <li>
                                                                            <a class="np-btn" href="#"><i class="fa fa-angle-right pagination-right"></i></a>
                                                                        </li>
                                                                    </ul>
                                                                </div>
                                                                <table class="table table-inbox table-hover">
                                                                    <thead>
                                                                        <tr class="unread">
                                                                            <td class="inbox-small-cells">Status</td>
                                                                            <td class="inbox-small-cells">Image</td>
                                                                            <td class="view-message  dont-show">User ID</td>
                                                                            <td class="view-message ">Display Name</td>
                                                                            <td class="view-message  inbox-small-cells">Contact Email</td>
                                                                            <td class="view-message  text-right">Created On</td>
                                                                            <td>
                                                                                Action
                                                                            </td>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody id="user-content">
                                                                        <c:forEach var="user" items="${users}">
                                                                            <tr class="user-info">
                                                                                <td class="inbox-small-cells">${user.status}</td>
                                                                                <td class="inbox-small-cells">
                                                                                    <img src="data:image/*;base64,${user.profilePicture}" style="width:30px;">
                                                                                </td>
                                                                                <td id="userID" class="view-message dont-show">${user.id}</td>
                                                                                <td class="view-message">${user.displayName} </td>
                                                                                <td class="view-message inbox-small-cells">${user.email}</td>
                                                                                <td class="view-message text-right">Date Add Later</td>
                                                                                <td>
                                                                                    <a href="<c:url value="/showblog?ID=${user.id}"/>" >
                                                                                        <button type="button" class="btn btn-primary btn-xs action-btn">
                                                                                            <i class="fa fa-user"> </i> View
                                                                                        </button>
                                                                                    </a>
                                                                                    <input type="hidden" id="userIDInfo" value="${user.id}">
                                                                                    <c:if test ="${user.status == 1}">
                                                                                        <button id="rejectBtn" type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#myModal${user.id}">
                                                                                            Deactivate
                                                                                        </button>
                                                                                    </c:if>
                                                                                    <c:if test ="${user.status == 2}">
                                                                                        <button type="button" class="btn btn-success btn-xs" id="toggleButton" onclick="changeUserStatus('${user.id}'); changeButton(this)">
                                                                                            Activate
                                                                                        </button>
                                                                                    </c:if>
                                                                                    <c:if test ="${user.status == 0}">
                                                                                        <button type="button" class="btn btn-warning btn-xs" id="toggleButton"">
                                                                                            Unverified
                                                                                        </button>
                                                                                    </c:if>
                                                                                </td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </aside>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end dashboard inner -->
                    </div>
                </div>
                <!-- model popup -->
                <!-- The Modal -->
                <c:forEach var="user" items="${users}">
                    <div class="modal fade" id="myModal${user.id}">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Send mail content</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <form action="set-mod-status" method="post">
                                        Account Deactivation Reason : <input type="text" name="deactivaedReason" id="deactivaedReason" value="" requried>
                                        <input type="hidden" name="userID" value="${user.id}" requried>
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
                                                                                            $(document).ready(function () {
                                                                                                toastr.options = {
                                                                                                    "timeOut": "5000",
                                                                                                    "extendedTimeOut": "10000", // Thời gian gia hạn thêm sau khi di chuột vào toast
                                                                                                    "closeButton": true, // Hiển thị nút đóng trên toast
                                                                                                    "progressBar": true // Hiển thị thanh tiến trình trên toast
                                                                                                };
                                                                                                // Hiển thị thông báo tự động khi trang web được tải
                <c:if test ="${not empty title && not empty message}">
                                                                                                toastr.success('${title}', '${message}', 'Success');
                </c:if>

                                                                                            });


                                                                                            function changeButton(btn) {
                                                                                                if (btn.classList.contains('btn-success')) {
                                                                                                    // Activate logic (if applicable)...
                                                                                                    btn.classList.remove('btn-success'); // Assuming this is for another button state
                                                                                                    btn.classList.add('btn-danger');
                                                                                                    btn.textContent = 'Deactivate';
                                                                                                }
                                                                                            }



                                                                                            function searchByName(param) {
                                                                                                var txtSearch = param.value;
                                                                                                var statusName = document.getElementById("status").value;
                                                                                                $.ajax({
                                                                                                    url: "search-mod",
                                                                                                    type: "get",
                                                                                                    data: {
                                                                                                        txt: txtSearch,
                                                                                                        status: statusName,
                                                                                                        action: "search"
                                                                                                    },
                                                                                                    success: function (data) {
                                                                                                        var row = document.getElementById("user-content");
                                                                                                        row.innerHTML = data;
                                                                                                        const tableRows = document.querySelectorAll('tr.user-info').length;
                                                                                                        const countDisplay = document.getElementById('userCount'); // Replace with actual ID
                                                                                                        countDisplay.textContent = 'Users ' + '(' + tableRows + ')'; // Set the text content
                                                                                                    },
                                                                                                    error: function (xhr) {

                                                                                                    }
                                                                                                });
                                                                                            }

                                                                                            function changeListStatus() {
                                                                                                var status = document.getElementById("status");
                                                                                                const statusList = document.getElementById('statuslist');
                                                                                                status.value = statusList.options[statusList.selectedIndex].value;

                                                                                                var statusName = document.getElementById("status").value;
                                                                                                $.ajax({
                                                                                                    url: "search-mod",
                                                                                                    type: "get",
                                                                                                    data: {
                                                                                                        status: statusName,
                                                                                                        action: "all"
                                                                                                    },
                                                                                                    success: function (data) {
                                                                                                        var row = document.getElementById("user-content");
                                                                                                        row.innerHTML = data;
                                                                                                        const tableRows = document.querySelectorAll('tr.user-info').length;
                                                                                                        const countDisplay = document.getElementById('userCount'); // Replace with actual ID
                                                                                                        countDisplay.textContent = 'Users ' + '(' + tableRows + ')'; // Set the text content
                                                                                                    },
                                                                                                    error: function (xhr) {

                                                                                                    }
                                                                                                });
                                                                                            }

                                                                                            function changeUserStatus(userID) {
                                                                                                $.ajax({
                                                                                                    url: 'set-mod-status',

                                                                                                    type: 'GET',
                                                                                                    data: {
                                                                                                        userID: userID
                                                                                                    },
                                                                                                    success: function (response) {
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