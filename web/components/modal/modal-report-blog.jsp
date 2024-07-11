<%-- 
    Document   : modal-report-blog
    Created on : Jun 4, 2024, 10:51:45 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/modal-report.css" type="text/css">

    </head>
    <body>
        <!-- The Modal -->
        <div class="modal fade" id="modal-report-blog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Lý do tố cáo bài viết?</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- Modal Body -->
                    <div class="modal-body">
                        <ul id="reportReasonsList">
                            <c:forEach items="${listBlogReportReason}" var="report">
                                <li><a href="#" onclick="selectReportReason(event, '${report}')">${report}</a></li>
                                </c:forEach>
                            <li><a href="#" onclick="selectReportReason(event, 'Other reason')">Lý do khác</a></li>
                        </ul>
                        <div id="otherReasonDiv" style="display: none; margin-top: 10px;">
                            <label for="otherReasonInput">Nhập lý do khác ở đây:</label>
                            <textarea id="otherReasonInput" maxlength="100" rows="3" class="form-control"></textarea>
                        </div>
                    </div>
                    <!-- Modal Footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="confirmReport(${blog.blogID})">Xác nhận</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
                            var selectedReportReason = '';

                            function selectReportReason(event, reportReason) {
                                event.preventDefault(); // Prevents default anchor tag behavior
                                selectedReportReason = reportReason;

                                var reportReasonsList = document.getElementById('reportReasonsList');
                                var listItems = reportReasonsList.getElementsByTagName('li');

                                for (var i = 0; i < listItems.length; i++) {
                                    listItems[i].style.backgroundColor = ''; // Clear previous selections
                                }

                                var clickedItem = Array.from(listItems).find(item => item.textContent.trim() === reportReason);
                                if (clickedItem) {
                                    clickedItem.style.backgroundColor = '#e0e0e0'; // Set background color for selected item
                                }

                                if (reportReason === 'Other reason') {
                                    document.getElementById('otherReasonDiv').style.display = 'block';
                                } else {
                                    document.getElementById('otherReasonDiv').style.display = 'none';
                                }
                            }

                            function confirmReport(blogID) {
                                if (selectedReportReason) {
                                    if (selectedReportReason === 'Other reason') {
                                        var otherReasonInput = document.getElementById('otherReasonInput').value;
                                        if (otherReasonInput.trim() === '') {
                                            alert('Bạn phải điền lý do tố cáo.');
                                            return;
                                        }
                                        reportBlog(blogID, otherReasonInput, true);
                                    } else {
                                        reportBlog(blogID, selectedReportReason, false);
                                    }
                                } else {
                                    alert('Bạn phải chọn lý do tố cáo.');
                                }
                            }

                            function reportBlog(blogID, reportReason, isOtherReason) {
                                var xhr = new XMLHttpRequest();
                                xhr.open('POST', 'reportblog', true);
                                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                                xhr.onreadystatechange = function () {
                                    if (xhr.readyState === XMLHttpRequest.DONE) {
                                        if (xhr.status === 200) {
                                            alert('Đã tố cáo bài viết thành công');
                                            $('#modal-report-blog').modal('hide'); // Hide modal on success
                                            location.reload(); // Reload the page to remove the dark overlay
                                        }  else {
                                            alert('Có lỗi xảy ra, vui lòng tải lại trang web');
                                        }
                                    }
                                };
                                xhr.send('blogID=' + encodeURIComponent(blogID) + '&reportReason=' + encodeURIComponent(reportReason) + '&isOtherReason=' + encodeURIComponent(isOtherReason));
                            }

        </script>
    </body>
</html>
