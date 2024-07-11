<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Notification" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



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
        <title>Add Eatery</title>
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
                <%@include file="components/sidebar.jsp" %>
                <!-- right content -->
                <div id="content">
                    <%@include file="components/topbar.jsp" %>
                    <!-- end topbar -->
                    <!-- dashboard inner -->




                    <div class="container-fluid">
                        <div class="row column_title">
                            <div class="col-md-12">
                                <div class="page_title">
                                    <h2>Add Eatery</h2>
                                    <form action="add-eatery" method="post" enctype='multipart/form-data'>
                                        Name: <input type="text" id="name" name="name" required><br>
                                        Location: <input type="text" id="location" name="location" required><br><br>
                                        Image : <input type="file" accept="image/*" id="image" name="image" onchange="loadFile(event)"><br><br><!-- comment -->
                                        <div id="image-preview-container">
                                            <div id="image-preview-single"></div>
                                        </div>
                                        <p>(Leaving the image box empty will set the image to default)</p>
                                        <input type="submit" value="Create">
                                    </form>
                                </div>

                                <h1>Eateries</h1>
                                <table border="1">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Location</th>
                                            <th>Image</th>
                                            <th>Updated by</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="listEatery" items="${listEatery}">
                                            <tr>
                                                <td>${listEatery.name}</td>
                                                <td>${listEatery.location}</td>
                                                <td><img src="data:image/*;base64,${listEatery.image}" alt="" style="width : 50px ; height:auto"></td>
                                                <td>${listEatery.updatedByUserID}</td>
                                                <td>
                                                    <c:if test="${listEatery.updatedByUserID == sessionScope.currentUser.id}">
                                                        <form method="post" action="remove-eatery?id=${listEatery.eateryID}">
                                                            <button type="submit">Delete</button>
                                                        </form>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>



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
                                            function loadFile(event) {
                                                var images = event.target.files;
                                                var imagePreview = document.getElementById('image-preview-single');
                                                imagePreview.innerHTML = ''; // Clear previous previews

                                                if (images.length > 0) { // Handle potential empty selection
                                                    var reader = new FileReader();
                                                    reader.onload = function (e) {
                                                        var img = document.createElement('img');
                                                        img.src = e.target.result;
                                                        img.classList.add('preview-image-single'); // Add CSS class for styling

                                                        imagePreview.appendChild(img);
                                                    };
                                                    reader.readAsDataURL(images[0]);
                                                }
                                            }

                                            var ps = new PerfectScrollbar('#sidebar');
                    </script>
                    <style>
                        #image-preview-container {
                            width: 360px; /* Adjust width as needed */
                            height: 300px; /* Adjust height as needed */
                            overflow-y: auto; /* Enable vertical scrollbar */
                            border: 1px solid #ccc; /* Optional border for the box */
                        }
                        #image-preview-single {
                            display: flex; /* Arrange images horizontally */
                            flex-wrap: wrap; /* Allow images to wrap to multiple lines */
                            margin: 5px; /* Add some margin for spacing */
                        }
                        .preview-image-single {
                            width: 300px; /* Adjust width as needed */
                            height: auto; /* Maintain aspect ratio */
                            margin: 5px; /* Add some margin between images */
                            /* Centering styles */
                            display: block;
                            margin-left: auto;
                            margin-right: auto;

                            /* Vertical centering */
                            position: relative; /* Establish relative positioning */
                        }
                    </style>
                    <!-- custom js -->
                    <script src="js/chart_custom_style1.js"></script>
                    <script src="js/custom.js"></script>

                    </body>
                    </html>