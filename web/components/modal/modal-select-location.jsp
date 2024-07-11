<%-- 
    Document   : modal-selectlocation
    Created on : May 27, 2024, 5:27:16 PM
    Author     : legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <div class="blog__sidebar__search">
            <h4>Search here</h4><br><!-- comment -->
            <form action="" method="">
                <input oninput="searchByName(this)" type="text" name="txt" placeholder="Search...">
                <button type="" disabled><span class="icon_search"></span></button>
            </form>
        </div>
        <div id="content" style="height: 200px; overflow-y: auto;">
            <!--                        <div>
                                        <div style="display:flex">
                                            <img src="img/user-profile-default-image.png" style="width: 60px; height: auto" alt="Cat">
                                        </div>
                                        <div class="text-wrapper">
                                            <button type="button" data-eatery-name="Name here" data-eatery-location="Location here" onclick="getEateryInfo(this)" style="float:right">Pick</button>
                                            <h3>Name here</h3>
                                            <div>Location here </div>
                                        </div>
                                    </div>-->

        </div><br>
        <h4>.....or write here</h4><br>
        <div class="checkout__input">
            <p>Eatery Name<span>*</span></p>
            <input type="text" name="eateryName" value="" required>
        </div>

        <div class="checkout__input">
            <p>Location<span>*</span></p>
            <input type="text" name="eateryLocation" value="" required>
        </div>
        <div hidden="true" class="checkout__input">
            <p>ID<span>*</span></p>
            <input type="text" name="eateryId" value="" required>
        </div>

        <button type="button" class="site-btn"  onclick="fillInfo()">Confirm</button>
    </div>
</div>