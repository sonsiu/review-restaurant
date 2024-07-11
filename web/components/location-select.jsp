<%-- 
    Document   : location-select
    Created on : Jun 6, 2024, 3:02:33 PM
    Author     : legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://esgoo.net/scripts/jquery.js"></script>
<style type="text/css">
    .css_select_div{
        text-align: center;
    }
    .css_select{
        display: inline-table;
        width: 25%;
        padding: 5px;
        margin: 5px 2%;
        border: solid 1px #686868;
        border-radius: 5px;
    }
</style>
<script>
    $(document).ready(function () {
        //Lấy tỉnh thành
        $.getJSON('https://esgoo.net/api-tinhthanh/1/0.htm', function (data_tinh) {
            if (data_tinh.error == 0) {
                $.each(data_tinh.data, function (key_tinh, val_tinh) {
                    $("#city").append('<option value="' + val_tinh.id + '">' + val_tinh.full_name + '</option>');
                });
                $("#city").change(function (e) {
                    var idtinh = $(this).val();
                    //Lấy quận huyện
                    $.getJSON('https://esgoo.net/api-tinhthanh/2/' + idtinh + '.htm', function (data_quan) {
                        if (data_quan.error == 0) {
                            $("#district").html('<option value="">Quận Huyện</option>');
                            $("#ward").html('<option value="">Phường Xã</option>');
                            $.each(data_quan.data, function (key_quan, val_quan) {
                                $("#district").append('<option value="' + val_quan.id + '">' + val_quan.full_name + '</option>');
                            });
                            //Lấy phường xã  
                            $("#district").change(function (e) {
                                var idquan = $(this).val();
                                $.getJSON('https://esgoo.net/api-tinhthanh/3/' + idquan + '.htm', function (data_phuong) {
                                    if (data_phuong.error == 0) {
                                        $("#ward").html('<option value="">Phường Xã</option>');
                                        $.each(data_phuong.data, function (key_phuong, val_phuong) {
                                            $("#ward").append('<option value="' + val_phuong.id + '">' + val_phuong.full_name + '</option>');
                                        });
                                    }
                                });
                            });

                        }
                    });
                });

            }
        });
    });

    function changeCity() {
        var selectCity = document.getElementById('city');
        var textCity = selectCity.options[selectCity.selectedIndex].text;
        document.getElementById("textCity").value = textCity;
    }
    function changeDistrict() {
        var selectDistrict = document.getElementById('district');
        var textDistrict = selectDistrict.options[selectDistrict.selectedIndex].text;
        document.getElementById("textDistrict").value = textDistrict;
    }
    function changeWard() {
        var selectWard = document.getElementById('ward');
        var textWard = selectWard.options[selectWard.selectedIndex].text;
        document.getElementById("textWard").value = textWard;
    }
</script>
<div class="css_select_div">
    <select class="css_select" id="city" name="city" title="Chọn Tỉnh Thành" onchange="changeCity()" required>
        <option value="" >Tỉnh Thành</option>
    </select> 
    <input type="hidden" id="textCity" name="textCity" value="">
    <select class="css_select" id="district" name="district" title="Chọn Quận Huyện" onchange="changeDistrict()" required>
        <option value=""  >Quận Huyện</option>
    </select> 
    <input type="hidden" id="textDistrict" name="textDistrict" value="">
    <select class="css_select" id="ward" name="ward" title="Chọn Phường Xã" onchange="changeWard()" required>
        <option value="" >Phường Xã</option>
    </select>
    <input type="hidden" id="textWard" name="textWard" value="">
</div>

