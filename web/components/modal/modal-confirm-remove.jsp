<%-- 
    Document   : modal
    Created on : May 17, 2024, 11:06:13 AM
    Author     : Vinh
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

    <c:forEach items="${listBlog}" var="blog">
        

        <div class="modal fade" id="modal-confirm-remove${blog.blogID}">
            <div class="modal-dialog">
                <div class="modal-content">


                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Xóa bài viết </h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="remove-blog" method="POST" >
                            <h5>Bạn chắc muốn xóa không ? Hành động này không thể hoàn tác</h5><br>

                            <!-- Modal footer -->
                            <div class="modal-footer">
                                <input type="hidden" id="id" name="id" value="${blog.blogID}">
                                <input type="hidden" id="userID" name="userID" value="${sessionScope.currentUser.id}">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                                <button type="submit" class="btn btn-success" >Xác nhận</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </c:forEach>

    <!-- Custom JavaScript to handle the form submission -->
    <script>

    </script>

    <!-- JavaScript Libraries -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>

    <!-- Template Main Javascript File -->
    <script src="js/main.js"></script>

</body>
</html>
