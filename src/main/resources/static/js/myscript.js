/**
 * Created by ivanybma on 4/6/16.
 */
function Refresh_Drink_lst(){

    var id_name = "id";
    var name_name = "nname";
    var picture_name="pname";
    var price_name="price";
    var calo_name="calo";
    var pre_time_name="pre_time"

    var category = "Drink";
    $.ajax({url: "/menu/findmenu/"+category,
        type: "GET",
        DataType: "text",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
            //alert(result);
            var url = location.href;
            var baseURL= url.substring(0, url.indexOf('/', 14));
            $("#drink_list_tb #dy_created_drink").remove();
            for(var i=0; i<result.length; i++){
                //alert(result[i].name);
                var newtr = $('<tr id="dy_created_drink">').append(
                    $('<td>').append(result[i].id),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + name_name + ' readonly>').val(result[i].name)),
                    $('<td>').append(
                      $('<img src="'+baseURL+'/images/'+ result[i].id + '" class="img-rounded" onclick="largephoto(this.id)" alt="Image" width="40" height="40" id=img_'+result[i].id+'>')
                    ),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + price_name + ' readonly>').val(result[i].price)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + calo_name + ' readonly>').val(result[i].calories)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + pre_time_name + ' readonly>').val(result[i].prepTime)),
                    $('<td>').append($('<a id='+result[i].id+' class="btn btn-large" onclick="menudelete(this.id)">Delete</a>'))
                );
                $("#drink_list_tb").append(newtr);
            }


        }});
}

function menudelete(id){
    $.ajax({url: "/menu/deletemenu"+"/"+id,
        type: "DELETE",
        DataType: "text",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
               // alert("Menu item id: "+id+ " was deleteded successfully.");
            var curobj = document.getElementById(id);
            if( $(curobj).closest('tbody').attr('id')=="drink_list_tb")
            {
                $('#drink_errmsg').css("color","green");
                $('#drink_errmsg').html("Menu item id: "+id+ " was deleteded successfully.").show();
                Refresh_Drink_lst();
            }
            else if ( $(curobj).closest('tbody').attr('id')=="appetizer_list_tb")
            {
                $('#appetizer_errmsg').css("color","green");
                $('#appetizer_errmsg').html("Menu item id: "+id+ " was deleteded successfully.").show();
                Refresh_appetizer_lst();
            }
            else if ( $(curobj).closest('tbody').attr('id')=="main_course_list_tb")
            {
                $('#main_course_errmsg').css("color","green");
                $('#main_course_errmsg').html("Menu item id: "+id+ " was deleteded successfully.").show();
                Refresh_main_course_lst();
            }
            else if ( $(curobj).closest('tbody').attr('id')=="desert_list_tb")
            {
                $('#desert_errmsg').css("color","green");
                $('#desert_errmsg').html("Menu item id: "+id+ " was deleteded successfully.").show();
                Refresh_desert_lst();
            }


        }});
    return true;
}

function Refresh_appetizer_lst(){


    var name_name = "nname";
    var price_name="price";
    var calo_name="calo";
    var pre_time_name="pre_time"

    var category = "Appetizer";
    $.ajax({url: "/menu/findmenu/"+category,
        type: "GET",
        DataType: "text",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
            var url = location.href;
            var baseURL= url.substring(0, url.indexOf('/', 14));
            $("#appetizer_list_tb #dy_created_appetizer").remove();
            for(var i=0; i<result.length; i++){
                var newtr = $('<tr id="dy_created_appetizer">').append(
                    $('<td>').append(result[i].id),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + name_name + ' readonly>').val(result[i].name)),
                    $('<td>').append(
                        $('<img src="'+baseURL+'/images/'+ result[i].id + '" class="img-rounded" onclick="largephoto(this.id)" alt="Image" width="40" height="40" id=img_'+result[i].id+'>')
                    ),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + price_name + ' readonly>').val(result[i].price)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + calo_name + ' readonly>').val(result[i].calories)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + pre_time_name + ' readonly>').val(result[i].prepTime)),
                    $('<td>').append($('<a id='+result[i].id+' class="btn btn-large" onclick="menudelete(this.id)">Delete</a>'))
                );
                $("#appetizer_list_tb").append(newtr);
            }


        }});
}

function Refresh_main_course_lst(){

    var name_name = "nname";
    var price_name="price";
    var calo_name="calo";
    var pre_time_name="pre_time"

    var category = "Main_Course";
    $.ajax({url: "/menu/findmenu/"+category,
        type: "GET",
        DataType: "text",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
            var url = location.href;
            var baseURL= url.substring(0, url.indexOf('/', 14));
            $("#main_course_list_tb #dy_created_main_course").remove();
            for(var i=0; i<result.length; i++){
                var newtr = $('<tr id="dy_created_main_course">').append(
                    $('<td>').append(result[i].id),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + name_name + ' readonly>').val(result[i].name)),
                    $('<td>').append(
                        $('<img src="'+baseURL+'/images/'+ result[i].id + '" class="img-rounded" onclick="largephoto(this.id)" alt="Image" width="40" height="40" id=img_'+result[i].id+'>')
                    ),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + price_name + ' readonly>').val(result[i].price)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + calo_name + ' readonly>').val(result[i].calories)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + pre_time_name + ' readonly>').val(result[i].prepTime)),
                    $('<td>').append($('<a id='+result[i].id+' class="btn btn-large" onclick="menudelete(this.id)">Delete</a>'))
                );
                $("#main_course_list_tb").append(newtr);
            }
        }});
}

function Refresh_desert_lst(){

    var name_name = "nname";
    var price_name="price";
    var calo_name="calo";
    var pre_time_name="pre_time"

    var category = "Desert";
    $.ajax({url: "/menu/findmenu/"+category,
        type: "GET",
        DataType: "text",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
            var url = location.href;
            var baseURL= url.substring(0, url.indexOf('/', 14));
            $("#desert_list_tb #dy_created_desert").remove();
            for(var i=0; i<result.length; i++){
                var newtr = $('<tr id="dy_created_desert">').append(
                    $('<td>').append(result[i].id),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + name_name + ' readonly>').val(result[i].name)),
                    $('<td>').append(
                        $('<img src="'+baseURL+'/images/'+ result[i].id + '" class="img-rounded" onclick="largephoto(this.id)" alt="Image" width="40" height="40" id=img_'+result[i].id+'>')
                    ),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + price_name + ' readonly>').val(result[i].price)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + calo_name + ' readonly>').val(result[i].calories)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + pre_time_name + ' readonly>').val(result[i].prepTime)),
                    $('<td>').append($('<a id='+result[i].id+' class="btn btn-large" onclick="menudelete(this.id)">Delete</a>'))
                );
                $("#desert_list_tb").append(newtr);
            }


        }});
}


function reportrst(){

    var order_date = "order_date";
    var fullfill_start="fullfill_start";
    var ready_time="ready_time";
    var pickup_time="pickup_time"
    var order_status="order_status";
    var customer_email="customer_email";

    var strdate = $('#dtp_input2').val();
    var enddate = $('#dtp_input3').val();
    var sortby = $('#sortby option:selected').val();

    //alert(strdate + " " + enddate + " "+ sortby);
    if(strdate=="") strdate="_";
    if(enddate=="") enddate="_";


    //alert("/order/testfindorder/"+strdate+"/"+enddate+"/"+sortby);

    $.ajax({url: "/order/findorder/"+strdate+"/"+enddate+"/"+sortby,
        type: "GET",
        DataType: "json",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
            $("#function_report_list_tb #dy_created_function_report").remove();
            for(var i=0; i<result.length; i++){

                var newtr = $('<tr id="dy_created_function_report">').append(
                    $('<td>').append(result[i].orderId),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + order_date + ' readonly>').val(result[i].orderDateTime)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + fullfill_start + ' readonly>').val(result[i].fullfillmentDateTime)
                    ),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + ready_time + ' readonly>').val(result[i].readyDatetime)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + pickup_time + ' readonly>').val(result[i].requiredPickupDateTime)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + order_status + ' readonly>').val(result[i].status)),

                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + customer_email + ' readonly>').val(result[i].customerEmail)),

                    $('<td>').append($('<button id =' + result[i].orderId +
                    ' type="button" class="btn btn-primary" data-toggle="modal" data-target="#OrderDetailModal" onclick="orderdetailinfo(this.id)">Order Detail</button>')),
                    $('<input type="hidden" id=totalprice_' + result[i].orderId + ' value="' + result[i].totalPrice + '">')
                );

                var orderdetail = result[i].itemList;
               // alert(orderdetail);
                var sublst = $('<select id="hiddenlst_'+result[i].orderId+'" style="display:none;">');
                for (var ix = 0; ix < orderdetail.length; ix++) {
                    var o = new Option(orderdetail[ix].name, orderdetail[ix].quantity+"|"+orderdetail[ix].price)
                    sublst.append(o);
                }

                newtr.append(sublst);
                $("#function_report_list_tb").append(newtr);

            }


        }});
}


function catstatrst(){

    var id_name = "id";
    var name_name = "nname";
    var picture_name="pname";
    var price_name="price";
    var calo_name="calo";
    var pre_time_name="pre_time"
    var strdate = $('#dtp_input4').val();
    var enddate = $('#dtp_input5').val();

    //alert(strdate + " " + enddate + " "+ sortby);
    if(strdate=="") strdate="_";
    if(enddate=="") enddate="_";

    var category = $('#catstatlst option:selected').val();
    $.ajax({url: "/order/findpopularity/"+strdate+"/"+enddate+"/"+category,
        type: "GET",
        DataType: "json",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
            //alert(result);
            var url = location.href;
            var baseURL= url.substring(0, url.indexOf('/', 14));
            $("#catstat_list_tb #dy_created_catstat").remove();
            for(var i=0; i<result.length; i++){
                //alert(result[i].name);
                var newtr = $('<tr id="dy_created_catstat">').append(
                    $('<td>').append(result[i].id),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + name_name + ' readonly>').val(result[i].name)),
                    $('<td>').append(
                        $('<img src="'+baseURL+'/images/'+ result[i].id + '" class="img-rounded" onclick="largephoto(this.id)" alt="Image" width="40" height="40" id=img_'+result[i].id+'>')
                    ),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + price_name + ' readonly>').val(result[i].price)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + calo_name + ' readonly>').val(result[i].calories)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + pre_time_name + ' readonly>').val(result[i].prepTime)),
                    $('<td>').append(
                        $('<input type="text" class="form-control" name=' + pre_time_name + ' readonly>').val(result[i].totalOrderNum))
                );
                $("#catstat_list_tb").append(newtr);
            }


        }});

}

function orderdetailinfo(id){

    $('#ttprice').html($('#totalprice_'+id).val());
    $("#hiddenlst_"+id+" > option").each(function() {
        var splitrst = this.value.split("|");
        var newtr = $('<tr>').append(
            $('<td>').append(this.text),
            $('<td>').append(splitrst[0]),
            $('<td>').append(splitrst[1])
        );

        $("#orderlayout_bd").append(newtr);
    });

}

function largephoto(id){

    var modal = document.getElementById('myModal');
    var img = document.getElementById(id);
    var modalImg = document.getElementById("img01");
    modal.style.display = "block";
    modalImg.src = img.src;
    modalImg.alt = img.alt;
}


function Attach_Event(){

    $('#drink_add').click(function(){
        var fname = $('#new_drink_photo').val();
        var type = fname.substring(fname.lastIndexOf('.')+1);

        if($('#new_drink_name').val()==""||!$.isNumeric($('#new_drink_price').val())||!$.isNumeric($('#new_drink_cal').val())||!$.isNumeric($('#new_drink_pretime').val()))
        {
            $('#drink_errmsg').css("color","red");
            $('#drink_errmsg').html("Please make sure to fill all the fields.  Only number can be accepted by fiels: Unit Price/Calories/Preparation Time").show();
            return false;
        }
        else if(type != "jpg" && type != "png" && type != "jpeg")
        {
            $('#drink_errmsg').css("color","red");
            $('#drink_errmsg').html("Please make sure the photo type belong to one of PNG, JPG or JPEG").show();
            return false;
        }
        else {
            //var newmenu = new menu("", $('#new_drink_name').val(), $('#new_drink_picture').val(), $('#new_drink_price').val(), $('#new_drink_cal').val(), $('#new_drink_pretime').val(), "Drink");
            return true;
        }

    });

    $('#appetizer_add').click(function(){
        var fname = $('#new_appetizer_photo').val();
        var type = fname.substring(fname.lastIndexOf('.')+1);

        if($('#new_appetizer_name').val()==""||!$.isNumeric($('#new_appetizer_price').val())||!$.isNumeric($('#new_appetizer_cal').val())||!$.isNumeric($('#new_appetizer_pretime').val()))
        {
            $('#appetizer_errmsg').css("color","red");
            $('#appetizer_errmsg').html("Please make sure to fill all the fields.  Only number can be accepted by fiels: Unit Price/Calories/Preparation Time").show();
            return false;
        }
        else if(type != "jpg" && type != "png" && type != "jpeg")
        {
            $('#appetizer_errmsg').css("color","red");
            $('#appetizer_errmsg').html("Please make sure the photo type belong to one of PNG, JPG or JPEG").show();
            return false;
        }
        else {
            return true;
        }

    });

    $('#main_course_add').click(function(){
        var fname = $('#new_main_course_photo').val();
        var type = fname.substring(fname.lastIndexOf('.')+1);

        if($('#new_main_course_name').val()==""||!$.isNumeric($('#new_main_course_price').val())||!$.isNumeric($('#new_main_course_cal').val())||!$.isNumeric($('#new_main_course_pretime').val()))
        {
            $('#main_course_errmsg').css("color","red");
            $('#main_course_errmsg').html("Please make sure to fill all the fields.  Only number can be accepted by fiels: Unit Price/Calories/Preparation Time").show();
            return false;
        }
        else if(type != "jpg" && type != "png" && type != "jpeg")
        {
            $('#main_course_errmsg').css("color","red");
            $('#main_course_errmsg').html("Please make sure the photo type belong to one of PNG, JPG or JPEG").show();
            return false;
        }
        else {
            return true;
        }

    });

    $('#desert_add').click(function(){
        var fname = $('#new_desert_photo').val();
        var type = fname.substring(fname.lastIndexOf('.')+1);

        if($('#new_desert_name').val()==""||!$.isNumeric($('#new_desert_price').val())||!$.isNumeric($('#new_desert_cal').val())||!$.isNumeric($('#new_desert_pretime').val()))
        {
            $('#desert_errmsg').css("color","red");
            $('#desert_errmsg').html("Please make sure to fill all the fields.  Only number can be accepted by fiels: Unit Price/Calories/Preparation Time").show();
            return false;
        }
        else if(type != "jpg" && type != "png" && type != "jpeg")
        {
            $('#desert_errmsg').css("color","red");
            $('#desert_errmsg').html("Please make sure the photo type belong to one of PNG, JPG or JPEG").show();
            return false;
        }
        else {
            return true;
        }

    });

    $("form[name*=data]").submit(function () {

        var formData = new FormData($(this)[0]);
        $.ajax({
            url: "/menu/addmenu",
            type: 'POST',
            data: formData,
            async: false,
            success: function (data) {
                //alert(data)
            },
            cache: false,
            contentType: false,
            processData: false
        });

        if( $(this).attr('name')=="drink_data")
        {
            $('#new_drink_name').val("");
            $('#new_drink_price').val("");
            $('#new_drink_cal').val("");
            $('#new_drink_pretime').val("");
            $('#drink_errmsg').hide();
            Refresh_Drink_lst();
        }

        else if($(this).attr('name')=="appetizer_data")
        {
            $('#new_appetizer_name').val("");
            $('#new_appetizer_price').val("");
            $('#new_appetizer_cal').val("");
            $('#new_appetizer_pretime').val("");
            $('#appetizer_errmsg').hide();
            Refresh_appetizer_lst();
        }
        else if($(this).attr('name')=="main_course_data")
        {
            $('#new_main_course_name').val("");
            $('#new_main_course_price').val("");
            $('#new_main_course_cal').val("");
            $('#new_main_course_pretime').val("");
            $('#main_course_errmsg').hide();
            Refresh_main_course_lst();
        }
        else if($(this).attr('name')=="desert_data")
        {
            $('#new_desert_name').val("");
            $('#new_desert_price').val("");
            $('#new_desert_cal').val("");
            $('#new_desert_pretime').val("");
            $('#desert_errmsg').hide();
            Refresh_desert_lst();
        }
        return false;
    });



    $('#reset_order_queue').click(function(){
        $.ajax({url: "/order/deleteall",
            type: "DELETE",
            DataType: "text",
            error: function(xhr){
                alert("An error occured: " + xhr.status + " " + xhr.statusText);
            },
            success: function(result){

               // alert("Order queue has been cleared successfully.");
                $('#order_msg').css("color","green");
                $('#order_msg').html("Order queue has been cleared successfully.").show();

            }});
        return false;

    });

    $('#myModal').click(function(){this.style.display = "none";});


}



