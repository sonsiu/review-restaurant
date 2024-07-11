<%-- 
    Document   : userinformation
    Created on : May 21, 2024, 4:34:42 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>HomePage</title>

<!-- Google Font -->
<link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
<style>.nav-link_.active {
    color: #007bff;
    font-weight: bold;
}
</style>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">

<ul class="profile-header-tab nav nav-tabs">
    <li class="nav-item"><a href="showblog" class="nav-link_">Posts</a></li>
    <li class="nav-item"><a href="userpage" class="nav-link_">About</a></li>
    <li class="nav-item"><a href="bookmarklist" class="nav-link_">BookMark</a></li>
    <li class="nav-item"><a href="show-notification" class="nav-link_">Notifications</a></li>
    <li class="nav-item"><a href="settings" class="nav-link_">Settings</a></li>
</ul>
<script>
document.addEventListener("DOMContentLoaded", function() {
    const tabs = document.querySelectorAll('.nav-link_');
    const currentUrl = window.location.href;
    const activeTab = localStorage.getItem('activeTab');

    tabs.forEach(tab => {
        tab.addEventListener('click', function(e) {
            // Prevent default action and manually navigate
            e.preventDefault();
            const targetUrl = this.getAttribute('href');
            setActiveTab(this);
            window.location.href = targetUrl;
        });

        // Check if the tab's href matches the current URL or localStorage value
        if (currentUrl.includes(tab.getAttribute('href')) || tab.getAttribute('href') === activeTab) {
            setActiveTab(tab);
        }
    });

    function setActiveTab(tab) {
        removeActiveClass();
        tab.classList.add('active');
        localStorage.setItem('activeTab', tab.getAttribute('href'));
    }

    function removeActiveClass() {
        tabs.forEach(tab => {
            tab.classList.remove('active');
        });
    }
});
</script>




<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>
