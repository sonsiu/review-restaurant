<%-- 
    Document   : modal-follow-list
    Created on : Jun 1, 2024, 3:24:19 PM
    Author     : legion
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="modal fade" id="modal-following-list">
            <div class="modal-dialog">
                <div class="modal-content">


                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Đang theo dõi </h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body" id="modalContent">
                        <c:forEach items="${followingList}" var="follower">
                                <div class="timeline-header" id="${follower.id}">
                                    <span class="userimage"><img src="data:image/*;base64,${follower.profilePicture}" alt=""></span>
                                    <span class="username"><a href="showblog?ID=${follower.id}">${follower.displayName}</a> <small></small></span>
                                </div>
                        </c:forEach>
                            
                    </div>
                </div>
            </div>
        </div>
        <!-- JavaScript Libraries -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>

        <!-- Template Main Javascript File -->
        <script src="js/main.js"></script>
    </body>
</html>
