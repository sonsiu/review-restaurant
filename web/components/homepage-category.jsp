<%-- 
    Document   : homepage-category
    Created on : Jun 25, 2024, 8:47:46 PM
    Author     : legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .featured__category__item{
        background-color: white; /* Grey background */
        border : 1px solid lightgrey;
        border-radius: 15px; /* Curved edges */
        margin: 15px 0; /* Margin between boxes */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Optional: Adding a subtle shadow for better appearance */
        text-align: center; /* Center align content */
    }
    .featured___category__item__pic img{
        width: 300px; /* Fixed width */
        height: 150px; /* Fixed height */
        object-fit: cover; /* Ensures the image covers the dimensions without distortion */
        border-radius: 15px; /* Rounded corners on all edges */
        filter: brightness(75%);
    }

    .text-overlay {
        position: absolute; /* Make text overlay positioned relative to container */
        bottom: 20px; /* Position at bottom */
        left: 16px; /* Position at left */
        padding: 10px; /* Add padding around text */
        color: white; /* White text color */
        border-radius: 0 0 0 15px ;
        font-weight: bold;
    }
    .text-overlay-count {
        position: absolute; /* Make text overlay positioned relative to container */
        bottom: 0px; /* Position at bottom */
        left: 16px; /* Position at left */
        padding: 10px; /* Add padding around text */
        color: white; /* White text color */
        border-radius: 0 0 0 15px ;
    }
</style>
<!-- Categories Section Begin DO NOT DELETE THIS SECTION !!! -->
<section class="categories" style="background-color: #F5F4F4">
    <div class="container">
        <h2>Danh Mục</h2>
        <div class="row">
            <div class="categories__slider owl-carousel">
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                            <a href="category?type=meal&name=Breakfast" class="category-link" data-overlay="Ăn sáng">
                            <img src="img/homepage-category/breakfast.jpg" alt="">
                            <div class="text-overlay"> Ăn Sáng </div>
                            <div class="text-overlay-count"> ${breakfast} bài viết </div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=meal&name=Lunch"  class="category-link" data-overlay="Ăn trưa">
                            <img src="img/homepage-category/lunch.jpg" alt="">
                            <div class="text-overlay"> Ăn Trưa </div>
                            <div class="text-overlay-count"> ${lunch} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=meal&name=Brunch"  class="category-link" data-overlay="Ăn Giữa Sáng-Trưa">
                            <img src="img/homepage-category/brunch.jpg" alt="">
                            <div class="text-overlay"> Ăn Giữa Sáng-Trưa </div>
                            <div class="text-overlay-count"> ${brunch} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=meal&name=Dinner"  class="category-link" data-overlay="Bữa tối">
                            <img src="img/homepage-category/dinner.jpg" alt="">
                            <div class="text-overlay"> Bữa Tối </div>
                            <div class="text-overlay-count"> ${dinner} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=meal&name=Late Night"  class="category-link" data-overlay="Ăn đêm">
                            <img src="img/homepage-category/late night.jpg" alt="">
                            <div class="text-overlay"> Ăn Đêm </div>
                            <div class="text-overlay-count"> ${latenight} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=meal&name=Drink"  class="category-link" data-overlay="Đồ Uống">
                            <img src="img/homepage-category/drink.jpg" alt="">
                            <div class="text-overlay"> Đồ Uống </div>
                            <div class="text-overlay-count">${drink} bài viết</div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=food&name=Vietnamese"  class="category-link" data-overlay="Ẩm thực Việt">
                            <img src="img/homepage-category/vietnamese.jpg" alt="">
                            <div class="text-overlay"> Ẩm thực Việt </div>
                            <div class="text-overlay-count"> ${vietnamese} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=food&name=Chinese"  class="category-link" data-overlay="Ẩm thực Trung">
                            <img src="img/homepage-category/chinese.jpg" alt="">
                            <div class="text-overlay"> Ẩm thực Trung </div>
                            <div class="text-overlay-count"> ${chinese} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=food&name=Korean"  class="category-link" data-overlay="Ẩm thực Hàn">
                            <img src="img/homepage-category/korean.jpg" alt="">
                            <div class="text-overlay"> Ẩm thực Hàn </div>
                            <div class="text-overlay-count"> ${korean} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic" >
                             <a href="category?type=food&name=Japanese"  class="category-link" data-overlay="Ẩm thực Nhật">
                            <img src="img/homepage-category/japanese.jpg" alt="">
                            <div class="text-overlay"> Ẩm thực Nhật </div>
                            <div class="text-overlay-count"> ${japanese} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=food&name=Thai"  class="category-link" data-overlay="Ẩm thực Thái">
                            <img src="img/homepage-category/thai.jpg" alt="">
                            <div class="text-overlay"> Ẩm thực Thái </div>
                            <div class="text-overlay-count"> ${thai} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=food&name=American" class="category-link" data-overlay="Ẩm thực Mỹ">
                            <img src="img/homepage-category/murica.jpg" alt="">
                            <div class="text-overlay"> Ẩm thực Mỹ </div>
                            <div class="text-overlay-count"> ${american} bài viết</div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=food&name=Europe"  class="category-link" data-overlay="Ẩm thực Âu">
                            <img src="img/homepage-category/european.jpg" alt="">
                            <div class="text-overlay"> Ẩm thực Âu </div>
                            <div class="text-overlay-count"> ${european} bài viết</div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=food&name=Other"  class="category-link" data-overlay="Ẩm thực khác">
                            <img src="img/homepage-category/other.jpg" alt="">
                            <div class="text-overlay"> Ẩm thực khác </div>
                            <div class="text-overlay-count"> ${other} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=special&name=Vegan"  class="category-link" data-overlay="Ăn chay">
                            <img src="img/homepage-category/vegan.jpg" alt="">
                            <div class="text-overlay"> Ăn chay </div>
                            <div class="text-overlay-count"> ${vegan} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=special&name=High-protein"  class="category-link" data-overlay="Giàu protein">
                            <img src="img/homepage-category/highprotein.jpg" alt="">
                            <div class="text-overlay"> Giàu protein </div>
                            <div class="text-overlay-count"> ${highprotein} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=special&name=Low-protein"  class="category-link" data-overlay="Ít protein">
                            <img src="img/homepage-category/lowprotein.jpg" alt="">
                            <div class="text-overlay"> Ít protein </div>
                            <div class="text-overlay-count"> ${lowprotein} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=special&name=Low-fat"  class="category-link" data-overlay="Ít béo">
                            <img src="img/homepage-category/lowfat.jpg" alt="">
                            <div class="text-overlay"> Ít béo </div>
                            <div class="text-overlay-count">${lowfat} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=special&name=Glueten-free"  class="category-link" data-overlay="Không Gluten">
                            <img src="img/homepage-category/gluten free.jpg" alt="">
                            <div class="text-overlay"> Không Gluten </div>
                            <div class="text-overlay-count"> ${glutenfree} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 featured__category__content">
                    <div class="featured__category__item">
                        <div class="featured___category__item__pic">
                             <a href="category?type=special&name=Diabetic Diet"  class="category-link" data-overlay="Chế độ ăn tiểu đường">
                            <img src="img/homepage-category/diabetc.jpeg" alt="">
                            <div class="text-overlay"> Chế độ ăn tiểu đường </div>
                            <div class="text-overlay-count"> ${diabetic} bài viết </div>
                             </a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
<!-- Categories Section End -->
