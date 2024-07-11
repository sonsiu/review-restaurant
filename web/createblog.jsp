<%-- 
    Document   : create_blog
    Created on : May 15, 2024, 8:06:44 AM
    Author     : legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Create Blog</title>

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
        <link rel="stylesheet" href="css/select-location-modal.css" type="text/css">
        <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/badges/">

        <!-- AJAX -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>



    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!--Turn Header, Footer to a seperate jsp file   -->
        <%@include file="components/header.jsp" %>

        <c:if test="${sessionScope.currentUser.role != 2 }">
            <section class="blog-details-hero set-bg content-below-header" data-setbg="img/food-banner-breadcrumb.png">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="blog__details__hero__text">
                                <h2>OOPS! Có chuyện gì xảy ra rồi !!!</h2>
                                <ul>
                                    <li>Truy cập không có phép ! Bạn không có quyền để truy cập phần này của trang web</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </c:if>

        <c:if test="${sessionScope.currentUser.role == 2 }">


            <!-- Breadcrumb Section Begin -->
            <section class="breadcrumb-section set-bg content-below-header" data-setbg="img/food-banner-breadcrumb.png">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 text-center">
                            <div class="breadcrumb__text">
                                <h2>Tạo bài viết</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Breadcrumb Section End -->

            <!-- Checkout Section Begin -->
            <section class="checkout spad">
                <div class="container">
                    <div class="checkout__form">
                        <h4>Chi tiết bài</h4>
                        <form action="createblog" method="post" enctype="multipart/form-data">
                            <div class="row">
                                <div class="col-lg-8 col-md-6">

                                    <div class="checkout__input">
                                        <p>Tiêu đề<span>*</span></p>
                                        <input type="text" name="title" value="${sessionScope.sessionTitle}" required >

                                    </div>

                                    <div class="checkout__input">
                                        <p>Nội Dung<span>*</span></p>
                                        <textarea name="description" id="description" accept="image/*" class="checkout__input__add" style="height: 300px;" rows="4" cols="100" required>
                                            ${sessionScope.sessionDesc}
                                        </textarea>
                                    </div>
                                    <div class="checkout__input">
                                        <p>Địa điểm<span>*</span></p>
                                        <%@include file="components/location-select.jsp" %>
                                    </div>
                                    <div class="checkout__input">
                                        <p>Địa chỉ cụ thể<span>*</span></p>
                                        <div class="address_input">
                                            <input type="text" name="address" id ='addressInput' value=""  required>
                                            <button  type="button" id="showMapButton"">Hiện Map</button>
                                        </div>
                                        <div id="mapContainer" style="display: none;">

                                        </div>

                                        <script>
                                            const addressInput = document.getElementById('addressInput');
                                            const selectCity = document.getElementById('city');
                                            const selectDistrict = document.getElementById('district');
                                            const selectWard = document.getElementById('ward');
                                            const showMapButton = document.getElementById('showMapButton');

                                            showMapButton.addEventListener('click', () => {
                                                const textCity = selectCity.options[selectCity.selectedIndex].text;
                                                const textDistrict = selectDistrict.options[selectDistrict.selectedIndex].text;
                                                const textWard = selectWard.options[selectWard.selectedIndex].text;

                                                const address = addressInput.value + ',' + textWard + ',' + textDistrict + ',' + textCity;

                                                // Encode the full address for URL
                                                const encodedAddress = encodeURIComponent(address);

                                                const mapContainer = document.getElementById('mapContainer');

                                                // Construct the URL with the encoded address
                                                const mapSrc = 'https://www.google.com/maps/embed/v1/place?key=AIzaSyCJnsbQBxAy1goHWDsu5bzOqO4epRMtJqw&q=' + encodedAddress;

                                                // Debugging: Log the map source URLs
                                                console.log('Map Source URL:', mapSrc);


                                                // Update the iframe src and show the map container
                                                mapContainer.innerHTML = `<iframe src=` + mapSrc + `s width="750" height="400" allowfullscreen></iframe>`;
                                                mapContainer.style.display = 'block'; // Show the map container

                                            });
                                        </script>
                                        <style>
                                            .ck-content{
                                                height: 300px;
                                            }
                                            .address_input {
                                                display: flex; /* Make the container a flexbox */
                                                flex-direction: column; /* Arrange elements vertically */
                                                align-items: center; /* Center elements horizontally */
                                                gap: 20px; /* Add spacing between elements */
                                            }

                                        </style>
                                    </div>
                                    <div class="checkout__input">
                                        <p>Tag<sup class="text-danger">(Tùy chọn)</sup></p>
                                        <small><sup class="text-danger">Bắt đầu với "#", chứa 1-16 chữ cái , thêm dấu cách giữa tag và KHÔNG khoảng trống giữa tags</sup></small>

                                        <div class="tag-container">
                                            <input id="input" onclick= "showSearchResults(); searchTag(this);" oninput="searchTag(this)" type="text" name="txt" placeholder="Enter here..." style="width : 500px">
                                            <input id="tags-array" name="tags-array" type="hidden" value="">
                                        </div>
                                        <div class="search-tag-container">
                                            <div id="search-tag-content" style="width: 750px; height: 200px; display: none; overflow-y: auto; border: none;" >

                                            </div><br>

                                        </div>
                                    </div>
                                    <style>
                                        .tag-container {
                                            border-radius: 4px;
                                            display: flex;
                                            width: 750px; /* Set your desired width */
                                            flex-wrap: wrap; /* Allow elements to wrap to new lines */
                                            border : 1px solid lightgrey;
                                        }

                                        .tag-container input {
                                            flex: 1;
                                            outline: none;
                                            border : 0;
                                        }

                                        .tag-container .tag {
                                            padding : 5px;
                                            border : 1px solid lightgrey;
                                            margin: 4px;
                                            display: flex;
                                            align-items: center;
                                            border-radius: 10px;
                                            background: lightgrey;
                                        }

                                        .remove-tag{
                                            font-size: 10px;
                                            margin-left: 7px;

                                        }

                                        .remove-tag {
                                            cursor: pointer;
                                            margin-left: 5px;
                                        }


                                    </style>
                                    <script>
                                        const tagInput = document.querySelector('#input');
                                        const tagContainer = document.querySelector('.tag-container');
                                        const tagArray = document.querySelector('#tags-array');
                                        //Handle the display
                                        const searchInput = document.querySelector('#input'); // Select the search input
                                        const searchTagContent = document.getElementById('search-tag-content'); // Select the content div
                                        const tags = [];

                                        function resetInput() {
                                            tagInput.value = '';
                                            tagInput.focus();
                                        }

                                        const createTag = (tagValue) => {
                                            const value = tagValue.trim();

                                            if (value === '' || tags.includes(value)) {
                                                resetInput();
                                                return;
                                            }

                                            // Additional validation for #, length, and uniqueness
                                            if (!value.startsWith('#') || value.length < 2 || value.length > 17 || tags.includes(value)) {
                                                resetInput();
                                                return;
                                            }

                                            // Validate for single # at the beginning
                                            const hashCount = (value.match(/#/g) || []).length;
                                            if (hashCount !== 1) {
                                                resetInput();
                                                return; // Do not create tag if # count is not 1
                                            }


                                            const tag = document.createElement('span');
                                            const tagContent = document.createTextNode(value);
                                            tag.setAttribute('class', 'tag');
                                            tag.appendChild(tagContent);

                                            const close = document.createElement('span');
                                            close.setAttribute('class', 'remove-tag');
                                            close.innerHTML = '&#10006;'; //Create cross
                                            close.onclick = handleRemoveTag;

                                            tag.appendChild(close);
                                            tagContainer.insertBefore(tag, tagContainer.firstChild);
                                            tags.push(value);
                                            tagArray.value = tags.join(',');

                                            resetInput();
                                        };

                                        const handleRemoveTag = (e) => {
                                            const item = e.target.textContent;
                                            e.target.parentElement.remove();
                                            tags.splice(tags.indexOf(item), 1);
                                            //At (the position of the wanted-deleted item , delete 1 item (that item))
                                            tagArray.value = tags.join(',');
                                        };

                                        tagInput.addEventListener('keyup', (e) => {
                                            const {key} = e;
                                            if (key === ' ' || key === ',' || key === '.') {
                                                createTag(tagInput.value.substring(0, tagInput.value.length - 1));
                                                hideSearchResults();
                                            }
                                        });



                                        function showSearchResults() {
                                            searchTagContent.style.display = 'block'; // Show the content div
                                        }

                                        function hideSearchResults() {
                                            searchTagContent.style.display = 'none'; // Hide the content div
                                        }

                                        document.addEventListener('click', function (event) {
                                            const isClickInsideInput = searchInput.contains(event.target); // Check if click is inside the search input
                                            const isClickInsideContent = searchTagContent.contains(event.target); // Check if click is inside the search input
                                            if (!isClickInsideInput && !isClickInsideContent && searchTagContent.style.display === 'block') { // Hide on click outside only if visible
                                                hideSearchResults();
                                            }
                                        });

                                        //Handle the search AJAX
                                        function searchTag(param) {
                                            var txtSearch = param.value;
                                            $.ajax({
                                                url: "search-tag",
                                                type: "get",
                                                data: {
                                                    txt: txtSearch
                                                },
                                                success: function (data) {
                                                    var row = document.getElementById("search-tag-content");
                                                    row.innerHTML = data;
                                                },
                                                error: function (xhr) {

                                                }
                                            });
                                        }
                                        function getTagInfo(buttonElement) {
                                            const tagInfo = buttonElement.dataset.tagInfo;
                                            createTag(tagInfo.substring(0, tagInfo.length));
                                        }
                                    </script>

                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="checkout__input">
                                                <p>Ảnh<span>*</span></p>
                                                <input type="file" id="imageLink" name="imageLink" multiple="true" onchange="loadMultipleFiles(event)" required>
                                                <div id="image-preview-container-multiple" style="display : none;">
                                                    <div id="image-preview-multiple"></div>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="checkout__input">
                                                <p>Ảnh hóa đơn  <sup class="text-danger">*(Dùng cho xác minh)</sup></p>
                                                <input type="file" accept="image/*" id="billLink" name="billLink" onchange="loadFile(event)" required>
                                                <div id="image-preview-container-bill" style="display : none;">
                                                    <div id="image-preview-single"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <p>(Khi bài viết đã được tạo , vui lòng chờ đội ngũ quán lý xử lí duyệt , quá trình này sẽ mất từ 10p-30p)</p>

                                </div>
                                <div class="col-lg-4 col-md-6">
                                    <div class="">
                                        <p style="font-weight : bold">Danh mục<span>*</span></p>
                                        <div class="meal-type" id="sectionFormMealType">
                                            <p>Loại bữa ăn</p>
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" value="Breakfast"> Ăn sáng<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" value="Lunch"> Ăn trưa<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" value="Dinner"> Ăn tối<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" value="Late Night"> Ăn đêm<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" value="Brunch"> Ăn giữa trưa-sáng<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="meal-type" value="Drink"> Đồ Uống<br>
                                                        </td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                        <br>
                                        <div class="food-type" id="sectionFormFoodType">
                                            <p>Loại ẩm thực</p>
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Vietnamese"> Việt<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Chinese"> Trung<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Korean"> Hàn<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Thai"> Thái<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Europe"> Âu<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="American"> Mỹ<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Japanese"> Nhật<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="food-type" value="Other"> Khác<br>
                                                        </td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div><br>

                                        <div class="diet-type">
                                            <p>Chế độ ăn đặc biệt (Tùy Chọn)</p>
                                            <table>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" value="Vegan"> Ăn chay <br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" value="Glueten-free"> Không Gluten<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" value="Low-fat"> Ít béo<br>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" value="Diabetic Diet"> Chế độ ăn tiểu đường<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" value="High-protein"> Giàu Protein<br>
                                                        </td>
                                                        <td>
                                                            <input type="checkbox" name="special-diet-type" value="Low-protein"> Ít Protein<br>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div><br>

                                        <table border="0" width="300" >
                                            <tbody>
                                                <tr class="category-content">
                                                    <td><p>Giá thành</p></td>
                                                    <td width="200">
                                                        <select class="category-select" name="price-range" title="Select price range" required>
                                                            <option value=""> Chọn giá </option>
                                                            <option value="$ (>10000 vnd)"> $ (>10000 vnd)</option>
                                                            <option value="$+ (10000 - 50000 vnd)"> $+ (10000 - 50000 vnd)</option>
                                                            <option value="$$ (50001 - 200000 vnd)"> $$ (50001 - 200000 vnd)</option>
                                                            <option value="$$+ (200001 - 500000 vnd)"> $$+ (200001 - 500000 vnd)</option>
                                                            <option value="$$$ (<500000 vnd)"> $$$ (<500000 vnd)</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <br><br>
                                    <div class="checkout__input">
                                        <strong>Điểm Rate<span>*</span></strong>
                                        <table border="0" width="300">
                                            <tbody>
                                                <tr>
                                                    <td>Chất lượng</td>
                                                    <td><input type="number" placeholder="From 1 to 5" class="rate-input" name="foodQuality" id="foodQuality" min="0" max="5" required></td>
                                                </tr>
                                                <tr>
                                                    <td>Không gian</td>
                                                    <td><input type="number" placeholder="From 1 to 5" class="rate-input" name="environment" id="environment" min="0" max="5" required></td>
                                                </tr>
                                                <tr>
                                                    <td>Giá cả</td>
                                                    <td><input type="number" placeholder="From 1 to 5" class="rate-input" name ="pricing" id="pricing" min="0" max="5" required></td>
                                                </tr>
                                                <tr>
                                                    <td>Phục vụ</td>
                                                    <td><input type="number" placeholder="From 1 to 5" class="rate-input" name="service" id="service" min="0" max="5" required></td>
                                                </tr>
                                                <tr>
                                                    <td>Vệ sinh</td>
                                                    <td><input type="number" placeholder="From 1 to 5" class="rate-input" name="hygiene" id="hygiene" min="0" max="5" required></td>
                                                </tr>
                                                <tr>
                                                    <td>Điểm Overall</td>
                                                    <td><input type="number" inputmode="decimal" pattern="[0-9]*[.,]?[0-9]*" class="rate-input" id="overall" name="overall" min="0" max="5" readonly></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <br>
                                        <div class="checkout__order" >

                                            <button type="submit" class="site-btn">Tạo bài</button>
                                        </div>
                                        <h4 class="text-danger" style="text-align: center; margin-top: 20px">${message}</h4>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <%@include file="components/modal/modal-select-location.jsp" %>
            </section>
        </c:if>
        <!-- Checkout Section End -->

        <!--Turn Header, Footer to a seperate jsp file   -->
        <%@include file="components/footer.jsp" %>

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>

        <!-- CKEDITOR -->
        <script src="https://cdn.ckeditor.com/ckeditor5/36.0.1/classic/ckeditor.js"></script>
        <script>
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

                                                    const foodQualityInput = document.getElementById('foodQuality');
                                                    const environmentInput = document.getElementById('environment');
                                                    const pricingInput = document.getElementById('pricing');
                                                    const serviceInput = document.getElementById('service');
                                                    const hygieneInput = document.getElementById('hygiene');
                                                    const overallInput = document.getElementById('overall');

                                                    function calculateOverall() {
                                                        const totalRating = parseFloat(foodQualityInput.value) +
                                                                parseFloat(environmentInput.value) +
                                                                parseFloat(pricingInput.value) +
                                                                parseFloat(serviceInput.value) +
                                                                parseFloat(hygieneInput.value);
                                                        const averageRating = totalRating / 5;
                                                        overallInput.value = averageRating.toFixed(1); // Format as float with one decimal place
                                                    }

                                                    // Call the function initially to ensure the overall is updated on page load
                                                    calculateOverall();

                                                    // Add event listeners on all rating inputs to call the function on change
                                                    foodQualityInput.addEventListener('change', calculateOverall);
                                                    environmentInput.addEventListener('change', calculateOverall);
                                                    pricingInput.addEventListener('change', calculateOverall);
                                                    serviceInput.addEventListener('change', calculateOverall);
                                                    hygieneInput.addEventListener('change', calculateOverall);


                                                    ClassicEditor
                                                            .create(document.querySelector('#description'))
                                                            .catch(error => {
                                                                console.error(error);
                                                            });

                                                    function loadMultipleFiles(event) {
                                                        var images = event.target.files;
                                                        var imagePreview = document.getElementById('image-preview-multiple');
                                                        var imagePreviewContainer = document.getElementById('image-preview-container-multiple');
                                                        imagePreview.innerHTML = ''; // Clear previous previews

                                                        for (var i = 0; i < images.length; i++) {
                                                            var reader = new FileReader();

                                                            reader.onload = function (e) {
                                                                var img = document.createElement('img');
                                                                img.src = e.target.result;
                                                                img.classList.add('preview-image-multiple'); // Add a CSS class for styling

                                                                imagePreview.appendChild(img);
                                                                imagePreviewContainer.style.display = "block";
                                                            };

                                                            reader.readAsDataURL(images[i]);
                                                        }
                                                    }

                                                    function loadFile(event) {
                                                        var images = event.target.files;
                                                        var imagePreview = document.getElementById('image-preview-single');
                                                        var imagePreviewContainer = document.getElementById('image-preview-container-bill');
                                                        imagePreview.innerHTML = ''; // Clear previous previews

                                                        if (images.length > 0) { // Handle potential empty selection
                                                            var reader = new FileReader();
                                                            reader.onload = function (e) {
                                                                var img = document.createElement('img');
                                                                img.src = e.target.result;
                                                                img.classList.add('preview-image-single'); // Add CSS class for styling

                                                                imagePreview.appendChild(img);
                                                                imagePreviewContainer.style.display = "block";
                                                            };
                                                            reader.readAsDataURL(images[0]);
                                                        }
                                                    }


                                                    (function () {
                                                        const form = document.querySelector('#sectionFormMealType');
                                                        const checkboxes = form.querySelectorAll('input[type=checkbox]');
                                                        const checkboxLength = checkboxes.length;
                                                        const firstCheckbox = checkboxLength > 0 ? checkboxes[0] : null;

                                                        function init() {
                                                            if (firstCheckbox) {
                                                                for (let i = 0; i < checkboxLength; i++) {
                                                                    checkboxes[i].addEventListener('change', checkValidity);
                                                                }

                                                                checkValidity();
                                                            }
                                                        }

                                                        function isChecked() {
                                                            for (let i = 0; i < checkboxLength; i++) {
                                                                if (checkboxes[i].checked)
                                                                    return true;
                                                            }

                                                            return false;
                                                        }

                                                        function checkValidity() {
                                                            const errorMessage = !isChecked() ? 'At least one checkbox must be selected.' : '';
                                                            firstCheckbox.setCustomValidity(errorMessage);
                                                        }

                                                        init();
                                                    })();

                                                    (function () {
                                                        const form = document.querySelector('#sectionFormFoodType');
                                                        const checkboxes = form.querySelectorAll('input[type=checkbox]');
                                                        const checkboxLength = checkboxes.length;
                                                        const firstCheckbox = checkboxLength > 0 ? checkboxes[0] : null;

                                                        function init() {
                                                            if (firstCheckbox) {
                                                                for (let i = 0; i < checkboxLength; i++) {
                                                                    checkboxes[i].addEventListener('change', checkValidity);
                                                                }

                                                                checkValidity();
                                                            }
                                                        }

                                                        function isChecked() {
                                                            for (let i = 0; i < checkboxLength; i++) {
                                                                if (checkboxes[i].checked)
                                                                    return true;
                                                            }

                                                            return false;
                                                        }

                                                        function checkValidity() {
                                                            const errorMessage = !isChecked() ? 'At least one checkbox must be selected.' : '';
                                                            firstCheckbox.setCustomValidity(errorMessage);
                                                        }

                                                        init();
                                                    })();

        </script>

        <style>

            button {
                background-color: white;
                border: 1px solid black;
                border-radius: 5px;
            }
            td {
                padding: 0 10px; /* Add padding to each cell for spacing (adjust as needed) */
                width : 300px;
            }

            .category-select{
                width: 100%;
                display: inline-table;
                padding: 5px;
                margin: 5px 2%;
                border: solid 1px #686868;
                border-radius: 5px;
            }

            .category-content{
                margin-bottom: 100px;
            }

            .background_image{
                background-image:linear-gradient(to right, transparent, #FFFFFF), url('img/location-default-image.jpeg');
                width: 300px;
                height: 150px;
                background-size: cover;
            }
            #image-preview-container-multiple , #image-preview-containtner-bill {
                width: 360px; /* Adjust width as needed */
                height: 300px; /* Adjust height as needed */
                overflow-y: auto; /* Enable vertical scrollbar */
                border: 1px solid #ccc; /* Optional border for the box */
            }

            #search-tag-content{
                width: 500px; /* Adjust width as needed */
                height: 200px; /* Adjust height as needed */
                overflow-y: auto; /* Enable vertical scrollbar */
                border: 1px solid #ccc; /* Optional border for the box */
            }

            #image-preview-multiple, #image-preview-single {
                display: flex; /* Arrange images horizontally */
                flex-wrap: wrap; /* Allow images to wrap to multiple lines */
                margin: 5px; /* Add some margin for spacing */
            }

            .preview-image-multiple {
                width: 100px; /* Adjust width as needed */
                height: auto; /* Maintain aspect ratio */
                margin: 5px; /* Add some margin between images */
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

            .rate-input {
                width: 60px;
                padding: 5px;
                margin-right: 50px
            }

            .text-wrapper {
                margin-right: 20px; /* Adjust spacing as needed */
            }

        </style>



    </body>

</html>
