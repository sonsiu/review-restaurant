<%-- 
    Document   : profile
    Created on : May 16, 2024, 11:26:37 PM
    Author     : Vinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<header>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/newcss.css">
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
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
</header>
<body>
    <%@include file="components/header.jsp" %>
    <%@include file="components/modal/modal-follower-list.jsp" %>
    <%@include file="components/modal/modal-following-list.jsp" %>
    <section class="blog-details spad" style="background-color: #F5F4F4">
        <style>
            .blog-link{
                color: grey;
                font-weight: bold;
            }
            .blog-link:hover{
                color: black;
            }
            .content-box{
                border-radius: 20px 20px 20px 20px;
                border : 1px solid lightgrey;
                width: 230px;
            }
        </style>
        <div class="container">

            <div class="row">
                <div class="col-md-12">
                    <div id="content" class="content content-full-width" style="background : white; border-radius: 20px;">

                        <!-- begin profile -->
                        <div class="profile">
                            <div class="profile-header">
                                <!-- BEGIN profile-info-header-->
                                <%@include file="components/profile-header.jsp" %>
                                <%@include file="components/modal/modal-report-user.jsp" %>
                                <!-- END profile-info-header-->

                                <ul class="profile-header-tab nav nav-tabs">
                                    <li class="nav-item"><a onclick="toTab('post')" id="postBtn" class="nav-link_" style="color:black;">Bài Viết</a></li>
<!--                                    <li class="nav-item"><a onclick="toTab('about')" id="aboutBtn" class="nav-link_">Về Tôi</a></li>-->
                                        <c:if test="${isOwner}">
                                        <li class="nav-item"><a onclick="toTab('bookmark')" id="bookmarkBtn" class="nav-link_">Dấu Trang</a></li>
                                        <li class="nav-item"><a onclick="toTab('notification')" id="notificationBtn" class="nav-link_">Thông Báo</a></li>
                                        </c:if> 
                                    <!--                                    <li class="nav-item"><a href="settings" class="nav-link_">Settings</a></li>-->

                                </ul>

                                <script>
                                    function toTab(tab) {
                                        const postTab = document.getElementById("post-list");
                                        const bookmarkTab = document.getElementById("bookmark-list");
                                        const notificationTab = document.getElementById("notify-list");
                                        const postBtn = document.getElementById("postBtn");
                                        const bookmarkBtn = document.getElementById("bookmarkBtn");
                                        const notificationBtn = document.getElementById("notificationBtn");
                                        switch (tab) {
                                            case "post":
                                                postTab.style.display = '';
                                                bookmarkTab.style.display = 'none';
                                                notificationTab.style.display = 'none';
                                                postBtn.style.color = 'black';
                                                bookmarkBtn.style.color = 'lightgrey';
                                                notificationBtn.style.color = 'lightgrey';
                                                break;
                                            case "bookmark":
                                                postTab.style.display = 'none';
                                                bookmarkTab.style.display = '';
                                                notificationTab.style.display = 'none';
                                                postBtn.style.color = 'lightgrey';
                                                bookmarkBtn.style.color = 'black';
                                                notificationBtn.style.color = 'lightgrey';
                                                break;
                                            case "notification":
                                                postTab.style.display = 'none';
                                                bookmarkTab.style.display = 'none';
                                                notificationTab.style.display = '';
                                                postBtn.style.color = 'lightgrey';
                                                bookmarkBtn.style.color = 'lightgrey';
                                                notificationBtn.style.color = 'black';
                                                break;
                                            default:
                                            // code block
                                        }
                                    }

                                </script>
                            </div>
                            <!-- begin profile-content -->
                            <div class="profile-content">
                                <!-- begin tab-content -->
                                <div class="tab-content p-0">
                                    <!-- begin #profile-post tab -->
                                    <div class="tab-pane fade active show" id="profile-post">
                                        <!-- begin timeline -->                                       
                                        <!-- Blogs -->
                                        <div id="post-list" class="text-center">

                                            <c:if test="${empty listBlog}">
                                                <div id="empty-bookmark-box" style=" text-align: center">
                                                    <p style="font-weight : bold;"> It's quite empty here .....</p>
                                                </div>
                                            </c:if>
                                            <div class="row">
                                                <div class="col-lg-12">

                                                    <div class="row">
                                                        <div class="col-lg-12">

                                                            <div class="featured__controls">
                                                                <div
                                                                    data-v-2551ee82=""
                                                                    style="height: 40px; width: 200px; margin-top: 15px"
                                                                    >
                                                                    <c:choose>
                                                                        <c:when test="${user.id ne sessionScope.currentUser.id}">
                                                                            <style>
                                                                                .featured__controls {
                                                                                    display: none; /* Hide the dropdown */
                                                                                }
                                                                            </style>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="featured__controls">
                                                                                <div data-v-2551ee82="" style="height: 40px; width: 100%; margin-top: 15px">
                                                                                    <div style="width: 100%;">
                                                                                        <select class="form-control" id="menuSelect" onchange="handleSelectChange(this)" style="width: 100%;">
                                                                                            <option value="showblog?ID=${sessionScope.currentUser.id}">Tất Cả</option>
                                                                                            <c:if test="${sessionScope.currentUser != null}">
                                                                                                <option value="showblog?ID=${sessionScope.currentUser.id}&action=approved">Đã duyệt</option>
                                                                                                <option value="showblog?ID=${sessionScope.currentUser.id}&action=unapproved">Đang chờ duyệt</option>
                                                                                                <option value="showblog?ID=${sessionScope.currentUser.id}&action=report">Bị báo cáo</option>
                                                                                            </c:if>
                                                                                        </select>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row text-center">

                                                <c:forEach items="${listBlog}" var="blog">
                                                    <!-- Team item -->
                                                    <div class="col-xl-3 col-sm-6 mb-5 rounded-sm" >
                                                        <div class="shadow-sm content-box"> 
                                                            <c:forEach items="${listImage}" var="image">
                                                                <c:if test="${image.blogID == blog.blogID}">
                                                                    <div class="featured__item__pic">
                                                                        <img src="data:image/*;base64,${image.imageLink}" alt="">
                                                                    </div> 

                                                                </c:if>
                                                            </c:forEach>
                                                            <h5 class="mb-0" style="margin-top : 30px;">
                                                                <a class="blog-link" 
                                                                   <c:if test="${blog.status != 0 }">
                                                                       href="blog-detail?id=${blog.blogID}" 
                                                                   </c:if>>${blog.title}</a>
                                                                <c:if test="${blog.status != 0 }">
                                                                    <i class="fa fa-check-circle-o" title="Verified" aria-hidden="true"></i>
                                                                </c:if>
                                                            </h5> 
                                                            <ul class="social mb-0 list-inline mt-3">
                                                                <li class="list-inline-item">  
                                                                    <c:if test="${sessionScope.currentUser.id == blog.userID}">
                                                                        <span class="stats-total text-danger" style="float: right;"> 
                                                                            <button type="button" class="btn btn-sm btn-danger mb-2" data-toggle="modal" data-target="#modal-confirm-remove${blog.blogID}"><i class="fa fa-trash" aria-hidden="true"></i></button>
                                                                        </span>
                                                                    </c:if>

                                                                    <c:if test="${blog.status == 0 && sessionScope.currentUser.id == blog.userID}">
                                                                        <span class="stats-total text-danger" style="float: right; margin-right: 10px"> 
                                                                            <a href="edit-blog?id=${blog.blogID}">
                                                                                <button type="button" class="btn btn-sm btn-primary mb-2"><i class="fa fa-pencil" aria-hidden="true"></i></button>
                                                                            </a>
                                                                        </span>
                                                                    </c:if>
                                                                    <a href="#" class="social-link"></a>
                                                                </li>

                                                            </ul>
                                                        </div>
                                                    </div><!-- End -->
                                                </c:forEach>

                                            </div>
                                            <ul class="listPage">

                                            </ul>
                                        </div>
                                        <!-- end Blogs -->

                                        <!-- begin Bookmark -->                                       
                                        <div id="bookmark-list" class="text-center" style="display : none">
                                            <c:if test="${empty bookmarks}">
                                                <div id="empty-bookmark-box" style=" text-align: center">
                                                    <p style="font-weight : bold;"> Không có gì ở đây cả ....</p>
                                                </div>
                                            </c:if>
                                            <div class="row text-center">
                                                <c:forEach items="${bookmarks}" var="blog">
                                                    <!-- Team item -->
                                                    <div class="col-xl-3 col-sm-6 mb-5 rounded-sm bookmark-item">
                                                        <div class="shadow-sm content-box"> 
                                                            <c:forEach items="${listImage}" var="image">
                                                                <c:if test="${image.blogID == blog.blogID}">
                                                                    <div class="featured__item__pic">
                                                                        <img src="data:image/*;base64,${image.imageLink}" alt="">
                                                                    </div>
                                                                </c:if>
                                                            </c:forEach>
                                                            <h5 class="mb-0" style="margin-top : 30px;">
                                                                <a class="blog-link" href="blog-detail?id=${blog.blogID}">${blog.title}</a>
                                                            </h5>
<!--                                                            <ul class="social mb-0 list-inline mt-3">
                                                                <li class="list-inline-item">
                                                                    <c:if test="${sessionScope.currentUser.id == blog.userID}">
                                                                        <span class="stats-total text-danger" style="float: right;">
                                                                            <button type="button" class="btn btn-sm btn-danger mb-2" data-toggle="modal" data-target="#modal-confirm-remove${blog.blogID}"><i class="fa fa-trash" aria-hidden="true"></i></button>
                                                                        </span>
                                                                    </c:if>
                                                                    <a href="#" class="social-link"></a>
                                                                </li>
                                                            </ul>-->
                                                        </div>
                                                    </div><!-- End -->
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <!-- end Bookmark List -->

                                        <!-- begin Notification -->                                       
                                        <div id="notify-list" class="" style="display : none"> 
                                            <c:if test="${empty listAllNotif}">
                                                <div id="empty-bookmark-box" style=" text-align: center">
                                                    <p style="font-weight : bold;"> Không có gì ở đây cả ...</p>
                                                </div>
                                            </c:if>
                                            <div class="row">
                                                <c:forEach items="${listAllNotif}" var="notif">
                                                    <div class="col-xl-12" style="margin-bottom : 30px">
                                                        <div class="shadow-sm py-3 px-4"> 
                                                            <span class="time text-danger">${notif.timestamp}</span>
                                                            <h5 class=""><a href="notification-detail?id=${notif.id}" class="text-info">${notif.title}</a></h5> 
                                                            <span class="date">${notif.message}</span>
                                                        </div>
                                                    </div> 
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <!-- end Notifications -->





                                    </div>
                                    <!-- end #profile-post tab -->
                                </div>
                                <!-- end tab-content -->

                            </div>
                            <!-- end profile-content -->

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modal-edit-user">
            <div class="modal-dialog">
                <div class="modal-content">


                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Cập nhật thông tin</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="edit-user" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="name">Tên hiện thị:</label>
                                <input type="text" class="form-control" id="displayname" name="displayname" placeholder="${user.displayName}" required>
                            </div>
                            <div class="form-group">
                                <label for="text">Ảnh đại diện:</label>
                                <input type="file" id="imageLink" name="imageLink" >
                            </div>
                            <div class="form-group">
                                <label for="phone">Số điên thoại liên hệ :</label>
                                <input type="number" class="form-control" id="phone" name="phone" placeholder="${user.phone}" required>
                            </div>
                            <div class="form-group">
                                <label for="phone">Địa điểm :</label>
                                <input type="text" class="form-control" id="location" name="location" placeholder="${user.location}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="phone">Chọn địa điểm mới :</label>
                                <%@include file="components/location-select.jsp" %>
                            </div>
                            <div class="form-group">
                                <label for="phone">Mật khẩu : </label>
                                <a href="update-password"><button type="button" class="btn btn-primary">Cập nhật mật khẩu tại đây</button></a>
                            </div>
                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-success" >Save</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </section>
    <!--Turn Header, Footer to a seperate jsp file   -->
    <%@include file="components/footer.jsp" %>

    <%@include file="components/modal/modal-confirm-remove.jsp" %>


    <style>
        .featured__item__pic img {
            width: 300px; /* Fixed width */
            height: 300px; /* Fixed height */
            object-fit: cover; /* Ensures the image covers the dimensions without distortion */
            border-radius: 15px; /* Rounded corners on all edges */
        }
        .toast-success {
            background-color: green; /* Set the background color to green for success notifications */
        }
        .toast-warning {
            background-color: orange; /* Set the background color to green for success notifications */
        }
        .toast-info {
            background-color: lightblue; /* Set the background color to green for success notifications */
        }
        .toast-error {
            background-color: red; /* Set the background color to green for success notifications */
        }
    </style>
    <!-- AJAX -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>

                                                                                    function saveProfile() {
                                                                                        // Get form data
                                                                                        var name = document.getElementById('name').value;
                                                                                        var birthdate = document.getElementById('birthdate').value;
                                                                                        var phone = document.getElementById('phone').value;
                                                                                        var location = document.getElementById('location').value;
                                                                                        // Here you can add your logic to save the data, e.g., send it to a server

                                                                                        // Close the modal after saving
                                                                                        $('#profileModal').modal('hide');
                                                                                    }
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


                                                                                    function changeStatus(follower, following, action) {
                                                                                        $.ajax({
                                                                                            url: 'follow-user',
                                                                                            type: 'GET',
                                                                                            data: {
                                                                                                follower: follower,
                                                                                                following: following,
                                                                                                action: action
                                                                                            },
                                                                                            success: function (response) {
                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    function addToFollower() {
                                                                                        $.ajax({
                                                                                            url: 'add-to-list',
                                                                                            type: 'GET',
                                                                                            success: function (data) {
                                                                                                var row = document.getElementById("modalContent");
                                                                                                row.innerHTML += data;
                                                                                            },
                                                                                            error: function (xhr) {

                                                                                            }
                                                                                        });
                                                                                    }

                                                                                    const toggleButton = document.getElementById('toggleButton');
                                                                                    const sessionUserID = document.getElementById('profileID');
                                                                                    toggleButton.addEventListener('click', function () {
                                                                                        const isFollowing = toggleButton.classList.contains('btn-danger');
                                                                                        // Toggle class names based on current class
                                                                                        if (toggleButton.classList.contains('btn-danger')) {
                                                                                            toggleButton.classList.remove('btn-danger');
                                                                                            toggleButton.classList.add('btn-light');
                                                                                            toggleButton.textContent = 'Following';
                                                                                            const profileID = document.getElementById('profileID').value;
                                                                                            const userID = document.getElementById('userID').value;
                                                                                            const followerCountSpan = document.getElementById('followerCount');
                                                                                            followerCountSpan.textContent = (parseInt(followerCountSpan.textContent, 10) + 1);
                                                                                            followerCountSpan.innerHTML += `<a data-target="#modal-follower-list" data-toggle="modal" 
                                               href="#modal-follower-list" style="color:black;"> Follower</a>`;
                                                                                            const currentUser = document.getElementById(sessionUserID.value);
                                                                                            if (currentUser === null)
                                                                                                addToFollower();
                                                                                            else {
                                                                                                currentUser.style.display = 'block';
                                                                                            }

                                                                                            changeStatus(profileID, userID, 'follow');
                                                                                        } else {
                                                                                            toggleButton.classList.remove('btn-light');
                                                                                            toggleButton.classList.add('btn-danger');
                                                                                            toggleButton.textContent = 'Follow';
                                                                                            const profileID = document.getElementById('profileID').value;
                                                                                            const userID = document.getElementById('userID').value;
                                                                                            const followerCountSpan = document.getElementById('followerCount');
                                                                                            followerCountSpan.textContent = (parseInt(followerCountSpan.textContent, 10) - 1);
                                                                                            followerCountSpan.innerHTML += `<a data-target="#modal-follower-list" data-toggle="modal" 
                                               href="#modal-follower-list" style="color:black;"> Follower</a>`;
                                                                                            const currentUserModal = document.getElementById(sessionUserID.value);
                                                                                            currentUserModal.style.display = 'none';
                                                                                            changeStatus(profileID, userID, 'unfollow');
                                                                                        }
                                                                                    });
    </script>
    <!--    <script>
    
            let shopPage = 1;
            let shoplimit = 8;
            let shoplist = document.querySelectorAll('.row.text-center .col-xl-3');
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
          
        </script>-->
    <script>
        $(document).ready(function () {
            // Get the current URL
            const urlParams = new URLSearchParams(window.location.search);
            const action = urlParams.get('action');
            // Set the selected option based on the action parameter
            if (action === 'approved') {
                document.querySelector('#menuSelect').value = `showblog?ID=${sessionScope.currentUser.id}&action=approved`;
            } else if (action === 'unapproved') {
                document.querySelector('#menuSelect').value = `showblog?ID=${sessionScope.currentUser.id}&action=unapproved`;
            } else if (action === 'report') {
                document.querySelector('#menuSelect').value = `showblog?ID=${sessionScope.currentUser.id}&action=report`;
            } else {
                document.querySelector('#menuSelect').value = `showblog?ID=${sessionScope.currentUser.id}`;
            }
        });
        function handleSelectChange(select) {
            const selectedValue = select.value;
            window.location.href = selectedValue;
        }
    </script>

    <script>
        let currentPage = 1;
        const itemsPerPage = 4; // Number of items per page

        function showPage(page) {
            const items = document.querySelectorAll('.row.text-center .col-xl-3.col-sm-6.mb-5.rounded-sm');
            const totalItems = items.length;
            const totalPages = Math.ceil(totalItems / itemsPerPage);
            // Hide all items
            items.forEach(item => item.style.display = 'none');
            // Calculate the start and end index of items to show
            const startIndex = (page - 1) * itemsPerPage;
            const endIndex = startIndex + itemsPerPage;
            // Show items for the current page
            for (let i = startIndex; i < endIndex && i < totalItems; i++) {
                items[i].style.display = 'block';
            }

            // Update pagination controls
            document.getElementById('pageNumber').innerText = `Page ${page} of ${totalPages}`;
            document.getElementById('prevPage').disabled = page === 1;
            document.getElementById('nextPage').disabled = page === totalPages;
        }

        function prevPage() {
            if (currentPage > 1) {
                currentPage--;
                showPage(currentPage);
            }
        }

        function nextPage() {
            const items = document.querySelectorAll('.row.text-center .col-xl-3.col-sm-6.mb-5.rounded-sm');
            const totalItems = items.length;
            const totalPages = Math.ceil(totalItems / itemsPerPage);
            if (currentPage < totalPages) {
                currentPage++;
                showPage(currentPage);
            }
        }

        // Initialize the pagination
        document.addEventListener('DOMContentLoaded', function () {
            showPage(currentPage);
        });
    </script>
    <script>
        function checkBookmarkVisibility() {
            const bookmarksSection = document.getElementById('bookmark-list');
            const visibleItems = Array.from(document.querySelectorAll('.row.text-center .col-xl-3'))
                    .filter(item => item.style.display === 'block');
            const bookmarkVisible = visibleItems.some(item => item.classList.contains('bookmark-item'));

            bookmarksSection.style.display = bookmarkVisible ? 'block' : 'none';
        }
        let shopPage = 1;
        let shoplimit = 12;
        let shoplist = document.querySelectorAll('.row.text-center .col-xl-3');

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

// Initialize the pagination
        document.addEventListener('DOMContentLoaded', function () {
            loadItem();
        });

    </script>



</body>
</html>