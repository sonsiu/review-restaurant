<%-- 
    Document   : modal
    Created on : May 17, 2024, 11:06:13 AM
    Author     : Vinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

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
                                <input type="number" class="form-control" id="phone" name="phone" placeholder="${user.phone}">
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

        <!-- Custom JavaScript to handle the form submission -->
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
        </script>

        <!-- JavaScript Libraries -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>

        <!-- Template Main Javascript File -->
        <script src="js/main.js"></script>

    </body>
</html>
