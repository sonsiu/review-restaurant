<%-- 
    Document   : profile-header
    Created on : Jun 7, 2024, 4:19:09 PM
    Author     : legion
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- BEGIN profile-header-content -->
<div class="profile-header-content">
    <!-- BEGIN profile-header-img -->
    <div class="profile-header-img">
        <div class="round-image">
            <img src="data:image/*;base64,${user.profilePicture}" alt="Not Available">
        </div>
    </div>
    <style>
        .profile-header-img{
            width: 175px;
            height: 175px;
            margin-right :30px;
        }
        .round-image {
            width: 100%; /* Adjust as desired */
            height: 100%; /* Adjust as desired */
            border-radius: 50%; /* Creates a circle */
            overflow: hidden; /* Hides overflowing parts of the image */
        }

        .round-image img {
            width: 100%; /* Ensures image fills the container */
            height: 100%; /* Ensures image fills the container */
            object-fit: cover; /* Scales image to cover the container */
        }
    </style>
    <!-- END profile-header-img -->
    <!-- BEGIN profile-header-info -->
    <div class="profile-header-info">
        <h2 class="m-t-10 m-b-5" style="color: black;">
            ${user.displayName}
            <c:if test="${isOwner}">
                <i class="fa fa-pencil" data-toggle="modal" data-target="#modal-edit-user"  title="Edit" style="font-size: 15px; cursor: pointer;"></i>
                <small style="font-size: 10px;">Edit</small>
            </c:if>
            <c:if test="${!isOwner && currentUser != null && currentUser.role == 2}">
                <span data-toggle="modal" data-target="#modal-report-user" id="reportUserBtn" title="Report User" style="cursor: pointer;">
                    <i class="fa fa-exclamation-triangle" id="flag-icon-${user.id}" style="font-size: 15px; color: red; margin-left: 30px;"></i>
                    <small style="font-size: 15px; color: red;">Báo cáo người dùng</small>
                </span>
            </c:if>
        </h2>
        <p class="m-b-10">@ ${user.id}</p>
        <p class="m-t-10 m-b-5 text-black-50">
            <span id="blogCount"> ${blogCount} 
                Posts
            </span> 

            <span id="followerCount"> ${follower}
                <a data-target="#modal-follower-list" data-toggle="modal"  
                   href="#modal-follower-list" style="color:black; ">Theo Dõi</a>
            </span> 

            <span id="followingCount"> ${following}
                <a data-target="#modal-following-list" data-toggle="modal" 
                   href="#modal-following-list" style="color:black;">Đang Theo Dõi</a>
            </span> 
        </p>
        <div class="following-follower" style="height: 55px">
            <c:if test ="${sessionScope.currentUser.role == 2}">
                <c:if test="${!isOwner && !isFollowing}">
                    <button id="toggleButton" name="follow" type="button" class="btn btn-sm btn-danger mb-2" style="padding : 5px 80px;">Follow</button>
                </c:if>
                <c:if test="${!isOwner && isFollowing}">
                    <button id="toggleButton" name="follow" type="button" class="btn btn-sm btn-light mb-2" style="padding : 5px 80px;">Following</button>
                </c:if>  
            </c:if>
        </div>
        <style>
            #blogCount, #followerCount, #followingCount {
                font-size: 20px;
                font-weight : bold;
                margin-right : 20px;
            }
        </style>
        <input type="hidden" id="profileID" value="${sessionScope.currentUser.id}" />
        <input type="hidden" id="userID"  value="${user.id}" />

    </div>
    <!-- END profile-header-info -->
</div><hr>
<!-- END profile-header-content -->
