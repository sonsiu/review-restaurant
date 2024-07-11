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
        <style>
             /* Thiết lập kích thước và vị trí cho modal */
   /* Modal styling */
.modal-dialog {
  max-width: 500px;
  margin: 1.75rem auto;
  /* rounded corners */
  border-radius: 5px;
}

.modal-header {
  color: white;
  /* add some padding */
  padding: 15px 20px;
  /* border-radius for top corners */
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}

.modal-title {
  /* center align the title */
  text-align: center;
}

.modal-body {
  padding: 20px;
  /* add some background color */
  background-color: #f5f5f5;
}

.modal-footer {
  /* add some padding */
  padding: 15px 20px;
  /* border-radius for bottom corners */
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
}

/* Reason list styling */
.modal-body ul {
  list-style: none; /* remove default list style */
  padding: 0;
  /* add some margin */
  margin: 10px 0;
}

.modal-body li {
  margin: 5px 0;
  /* allow hover effect */
  cursor: pointer;
}

.modal-body li:hover {
  background-color: #ddd;
}

/* Other reason section styling */
#otherReasonDiv {
  display: none;
  margin-top: 10px;
}

/* Styling for the 'Confirm' button */
.btn-primary {
  background-color: #3498db;
  border-color: #3498db;
}

.btn-primary:hover {
  background-color: #2980b9;
  border-color: #2980b9;
}
        </style>

    </head>
    <body>
     <div class="modal fade" id="modal-report-user">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Reason to report this user?</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal Body -->
                <div class="modal-body">
                    <ul id="reportReasonsListUser">
                        <c:forEach items="${listUserReportReason}" var="report">
                            <li><a href="#" onclick="selectReportReasonUser(event, '${report}')">${report}</a></li>
                        </c:forEach>
                        <li><a href="#" onclick="selectReportReasonUser(event, 'Other reason')">Other reason</a></li>
                    </ul>
                    <div id="otherReasonDivUser" style="display: none; margin-top: 10px;">
                        <label for="otherReasonInputUser">Enter other report reason here:</label>
                        <textarea id="otherReasonInputUser" maxlength="100" rows="3" class="form-control"></textarea>
                    </div>
                </div>
                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="confirmReportUser('${user.id}')">Confirm</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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
                            var selectedReportReason; // Biến lưu trữ lý do báo cáo được chọn

function selectReportReasonUser(event, reportReason) {
    event.preventDefault(); // Ngăn chặn hành động mặc định của thẻ <a>
    selectedReportReason = reportReason; // Lưu lý do báo cáo được chọn

    var reportReasonsListUser = document.getElementById('reportReasonsListUser');
    var listItems = reportReasonsListUser.getElementsByTagName('li');

    // Xóa các lựa chọn trước đó
    for (var i = 0; i < listItems.length; i++) {
        listItems[i].style.backgroundColor = '';
    }

    // Đánh dấu mục đã chọn bằng màu nền khác nhau
    var clickedItem = Array.from(listItems).find(item => item.textContent.trim() === reportReason);
    if (clickedItem) {
        clickedItem.style.backgroundColor = '#e0e0e0';
    }

    // Hiển thị hoặc ẩn phần nhập lý do khác nếu người dùng chọn "Other reason"
    if (reportReason === 'Other reason') {
        document.getElementById('otherReasonDivUser').style.display = 'block';
    } else {
        document.getElementById('otherReasonDivUser').style.display = 'none';
    }
}

function confirmReportUser(userId) {
    if (selectedReportReason) {
        if (selectedReportReason === 'Other reason') {
            var otherReasonInputUser = document.getElementById('otherReasonInputUser').value;
            if (otherReasonInputUser.trim() === '') {
                alert('Please specify the reason.');
                return;
            }
            reportUser(userId, otherReasonInputUser, true);
        } else {
            reportUser(userId, selectedReportReason, false);
        }
    } else {
        alert('Please select a reason to report.');
    }
}

function reportUser(userId, reportReason, isOtherReason) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'reportuser', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                alert('User reported successfully.');
                $('#modal-report-user').modal('hide'); // Ẩn modal khi thành công
                location.reload(); // Tải lại trang để xóa lớp mờ đen
            } else if (xhr.status === 403) {
                alert('You must be logged in to report a user.');
            } else if (xhr.status === 409) {
                alert('You have already reported this user.');
            } else {
                alert('Error reporting user.');
            }
        }
    };
    xhr.send('userId=' + encodeURIComponent(userId) + '&reportReason=' + encodeURIComponent(reportReason) + '&isOtherReason=' + encodeURIComponent(isOtherReason));
}

        </script>
    </body>
</html>
