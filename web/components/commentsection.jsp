<%-- 
    Document   : userdetail.jsp
    Created on : May 21, 2024, 3:38:34 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
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
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
<div class="container bootdey" style="background-color: #F5F4F4">
    <div class="col-md-12 bootstrap snippets">
        <div class="panel">
            <h4 style="margin-left : 30px; padding-top: 20px;">Bình luận</h4>
            <div class="panel-body" style="border-radius : 15px">
                <c:if test="${sessionScope.currentUser != null}">

                    <form id="createCommentForm">
                        <input type="hidden" name="id" value="${blog.blogID}">
                        <textarea name="content" class="form-control" rows="2" placeholder="Bạn nghĩ sao về bài viết này?"></textarea>
                        <div class="mar-top clearfix">
                            <button class="btn btn-sm btn-primary pull-right" type="submit"><i class="fa fa-pencil fa-fw"></i> Chia sẻ</button>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
        <div class="panel">
            <div class="panel-body">
                <div id="commentsSection">
                    <c:forEach items="${listComment}" var="comment">
                        <div class="media-block" id="comment-${comment.commentID}">
                            <a class="media-left" href="#"><img class="img-circle img-sm" alt="Profile Picture" src="data:image/*;base64,${comment.userPicture}"></a>
                            <div class="media-body">
                                <div class="mar-btm">
                                    <a href="showblog?ID=${comment.userID}" class="btn-link text-semibold media-heading box-inline">${comment.userName}</a>
                                    <p class="text-muted text-sm">${comment.commentDate}</p>
                                </div>
                                <p>${comment.content}</p>
                                <div class="pad-ver">
                                    <span class="tag tag-sm" id="like-count-${comment.commentID}">
                                        <i class="fa fa-heart text-danger"></i> ${comment.commentLike} Lượt thích
                                    </span>
                                    <c:if test="${sessionScope.currentUser != null}">
                                        <div class="btn-group">
                                            <a class="btn btn-sm btn-default btn-hover-success" href="#" onclick="likeComment(event, ${comment.commentID})">
                                                <i class="fa fa-thumbs-up"></i>
                                            </a>
                                        </div>
                                    </c:if>
                                    <c:if test="${sessionScope.currentUser != null && sessionScope.currentUser.id == comment.userID}">
                                        <a class="btn btn-sm btn-default btn-hover-primary" href="#" 
                                           onclick="confirmDeleteComment(event, ${comment.commentID}, ${blog.blogID})">
                                            <i class="fa fa-trash-o" style="font-size:12px; color:red;"></i>
                                        </a>  
                                    </c:if>
                                    <c:if test="${sessionScope.currentUser != null}">
                                        <a class="btn btn-sm btn-default btn-hover-primary" href="#" onclick="showReplyForm(event, ${comment.commentID})">Phàn hồi</a>
                                    </c:if>
                                </div>
                                <div id="replyForm-${comment.commentID}" style="display:none;">
                                    <form class="replyForm" data-comment-id="${comment.commentID}">
                                        <input type="hidden" name="parentId" value="${comment.commentID}">
                                        <textarea name="content" class="form-control" rows="2" placeholder="Write a reply..."></textarea>
                                        <div class="mar-top clearfix">
                                            <button class="btn btn-sm btn-primary pull-right" type="submit"><i class="fa fa-pencil fa-fw"></i> Chia sẻ</button>
                                        </div>
                                    </form>
                                </div>
                                <div id="replies-${comment.commentID}">
                                    <hr>
                                    <c:forEach items="${comment.replies}" var="reply">
                                        <div class="media-block" id="reply-${reply.replyID}">

                                            <a class="media-left" href="#"><img class="img-circle img-sm" alt="Profile Picture" src="data:image/*;base64,${reply.userPicture}"></a>

                                            <div class="media-body">
                                                <div class="mar-btm">
                                                    <a href="showblog?ID=${reply.userID}" class="btn-link text-semibold media-heading box-inline">${reply.userName}</a>
                                                    <p class="text-muted text-sm">${reply.replyDate}</p>
                                                </div>
                                                <p>${reply.content}</p>
                                                <div class="pad-ver">
                                                    <span class="tag tag-sm" id="like-count-${reply.replyID}">
                                                        <i class="fa fa-heart text-danger"></i> ${reply.replyLike} Lượt thích
                                                    </span>
                                                    <c:if test="${sessionScope.currentUser != null}">
                                                        <div class="btn-group">
                                                            <a class="btn btn-sm btn-default btn-hover-success" href="#" onclick="likeReply(event, ${reply.replyID})">
                                                                <i class="fa fa-thumbs-up"></i>
                                                            </a>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${sessionScope.currentUser != null && sessionScope.currentUser.id == reply.userID}">
                                                        <a class="btn btn-sm btn-default btn-hover-primary" href="#" 
                                                           onclick="confirmDeleteReply(event, ${reply.replyID}, ${comment.commentID}, ${blog.blogID})">
                                                            <i class="fa fa-trash-o" style="font-size:12px; color:red;"></i>
                                                        </a> 
                                                    </c:if>
                                                </div>
                                                <hr>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <hr>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

                                                               function confirmDeleteComment(event, commentID, blogID) {
                                                                   event.preventDefault();
                                                                   if (confirm('Bạn có chắc chắn muốn xóa bình luận này không?')) {
                                                                       deleteComment(event, commentID, blogID);
                                                                   }
                                                               }
                                                               function deleteComment(event, commentID, blogID) {
                                                                   event.preventDefault();

                                                                   $.ajax({
                                                                       url: 'deletecomment',
                                                                       type: 'POST',
                                                                       data: {
                                                                           id: commentID,
                                                                           blogID: blogID
                                                                       },
                                                                       success: function (response) {
                                                                           $('#comment-' + commentID).remove();
                                                                       },
                                                                       error: function (xhr) {
                                                                           console.error('Có lỗi xảy ra: ' + xhr.status);
                                                                           alert('Có lỗi xảy ra.');
                                                                       }
                                                                   });
                                                               }
                                                               $(document).ready(function () {
                                                                   $('#createCommentForm').on('submit', function (event) {
                                                                       event.preventDefault();
                                                                       $.ajax({
                                                                           url: 'createcomment',
                                                                           type: 'POST',
                                                                           data: $(this).serialize(),
                                                                           success: function (response) {
                                                                               $.get('blog-detail', {id: ${blog.blogID}}, function (data) {
                                                                                   $('#commentsSection').html($(data).find('#commentsSection').html());
                                                                               });
                                                                               $('#createCommentForm')[0].reset();
                                                                           },
                                                                           error: function () {
                                                                               alert('Có lỗi xảy ra.');
                                                                           }
                                                                       });
                                                                   });

                                                                   $(document).on('submit', '.replyForm', function (event) {
                                                                       event.preventDefault();
                                                                       var form = $(this);
                                                                       var commentID = form.data('comment-id');
                                                                       $.ajax({
                                                                           url: 'createreply',
                                                                           type: 'POST',
                                                                           data: form.serialize() + '&blogID=' + ${blog.blogID} + '&commentID=' + commentID,
                                                                           success: function (response) {
                                                                               // Prepend the new reply to the top of the replies section
                                                                               $('#replies-' + commentID).prepend(response);
                                                                               form[0].reset();
//                form.hide();
                                                                           },
                                                                           error: function () {
                                                                               alert('Có lỗi xảy ra.');
                                                                           }
                                                                       });
                                                                   });
                                                               });

                                                               function likeComment(event, commentID) {
                                                                   event.preventDefault();

                                                                   $.ajax({
                                                                       url: 'likecomment',
                                                                       type: 'POST',
                                                                       data: {commentID: commentID},
                                                                       success: function (response) {
                                                                           var likeCount = response.likeCount;
                                                                           $('#like-count-' + commentID).html('<i class="fa fa-heart text-danger"></i> ' + likeCount + ' Lượt thích');
                                                                       },
                                                                       error: function (xhr) {
                                                                           alert('Có lỗi xảy ra');
                                                                       }
                                                                   });
                                                               }

                                                               function showReplyForm(event, commentID) {
                                                                   event.preventDefault();
                                                                   $('#replyForm-' + commentID).toggle();
                                                               }

                                                               function confirmDeleteReply(event, replyID, commentID, blogID) {
                                                                   event.preventDefault();
                                                                   if (confirm('Bạn có chắc chắn muốn xóa bình luận này không?')) {
                                                                       deleteReply(event, replyID, commentID, blogID);
                                                                   }
                                                               }
                                                               function deleteReply(event, replyID, commentID, blogID) {
                                                                   event.preventDefault();

                                                                   $.ajax({
                                                                       url: 'deletereply',
                                                                       type: 'POST',
                                                                       data: {
                                                                           id: replyID,
                                                                           commentID: commentID,
                                                                           blogID: blogID
                                                                       },
                                                                       success: function (response) {
                                                                           $('#reply-' + replyID).remove();
                                                                       },
                                                                       error: function (xhr) {
                                                                           console.error('Có lỗi xảy ra: ' + xhr.status);
                                                                           alert('Có lỗi xảy ra.');
                                                                       }
                                                                   });
                                                               }
                                                               function likeReply(event, replyID) {
                                                                   event.preventDefault();

                                                                   $.ajax({
                                                                       url: 'likereply',
                                                                       type: 'POST',
                                                                       data: {replyID: replyID},
                                                                       success: function (response) {
                                                                           var likeCount = response.likeCount;
                                                                           $('#like-count-' + replyID).html('<i class="fa fa-heart text-danger"></i> ' + likeCount + ' Lượt thích');
                                                                       },
                                                                       error: function (xhr) {
                                                                           alert('Có lỗi xảy ra.');
                                                                       }
                                                                   });
                                                               }

</script>

</html>
