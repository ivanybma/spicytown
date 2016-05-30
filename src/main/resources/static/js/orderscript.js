/**
 * Created by ivanybma on 4/6/16.
 */
function Refresh_Drink_lst(){

    var name_name = "nname";
    var price_name="price";
    var calo_name="calo";

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
                var newtr = $('<tr id="dy_created_drink_1_'+result[i].id+'">').append(
                    $('<td>').append(result[i].id),
                    $('<td>').append(
                        $('<input type="text" id = "name_'+result[i].id+'" class="form-control" style="border:none; background-color: transparent;" name=' + name_name + ' readonly>').val(result[i].name)),
                    $('<td>').append(
                        $('<input type="text" id = "price_'+result[i].id+'" class="form-control" style="border:none; background-color: transparent;" name=' + price_name + ' readonly>').val(result[i].price)),
                    $('<td>').append(
                        $('<input type="text" id = "calories_'+ result[i].id + '" class="form-control" style="border:none; background-color: transparent;" name=' + calo_name + ' readonly>').val(result[i].calories)),
                    $('<td rowspan="2">').append(
                        $('<img src="'+baseURL+'/images/'+ result[i].id + '" class="img-rounded" onclick="largephoto(this.id)" alt="Image" width="100" height="100" id=img_'+result[i].id+'>')
                    )
                );

                $("#drink_list_tb").append(newtr);

                var newselect = $('<select id = "count_'+result[i].id+'" align="left" class="form-control" style="width:auto;">');
                for(var num=1;num<=100; num++){
                    var o = new Option(num, num);
                    newselect.append(o);
                }
                var newselectdiv = $('<div align="left" class="col-xs-3">').append(newselect);


                    newtr = $('<tr id="dy_created_drink_2_'+result[i].id+'">').append(
                    $('<td align="left" style="border:none;">').append(newselectdiv),
                    $('<td style="border:none;">').append(
                      //  $('<input onclick="addToCart('+result[i].id+',' +result[i].name+',' +$("#count_"+result[i].id).val()+','+result[i].price+')" id = "add_'+result[i].id+'" type="button" value="Add to Cart" class="btn btn-success">')
                        $('<input  type="button" onclick="addToCart('+result[i].id+')" value = "Add to Cart" class="btn" style=" background-color: #bc1a3a; color: #fff;  font-size:14px;" >')
                    )
                    );
                $("#drink_list_tb").append(newtr);
            }


        }});
}

function Refresh_appetizer_lst(){

    var name_name = "nname";
    var price_name="price";
    var calo_name="calo";

    var category = "Appetizer";
    $.ajax({url: "/menu/findmenu/"+category,
        type: "GET",
        DataType: "text",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
            //alert(result.length);
            var url = location.href;
            var baseURL= url.substring(0, url.indexOf('/', 14));
            $("#appetizer_list_tb #dy_created_appetizer").remove();
            for(var i=0; i<result.length; i++){
                var newtr = $('<tr id="dy_created_appetizer_1_'+result[i].id+'">').append(
                    $('<td>').append(result[i].id),
                    $('<td>').append(
                        $('<input type="text" id = "name_'+result[i].id+'" class="form-control" style="border:none; background-color: transparent;" name=' + name_name + ' readonly>').val(result[i].name)),
                    $('<td>').append(
                        $('<input type="text" id = "price_'+result[i].id+'" class="form-control" style="border:none; background-color: transparent;" name=' + price_name + ' readonly>').val(result[i].price)),
                    $('<td>').append(
                        $('<input type="text" id = "calories_'+ result[i].id + '" class="form-control" style="border:none; background-color: transparent;" name=' + calo_name + ' readonly>').val(result[i].calories)),
                    $('<td rowspan="2">').append(
                        $('<img src="'+baseURL+'/images/'+ result[i].id + '" class="img-rounded" onclick="largephoto(this.id)" alt="Image" width="100" height="100" id=img_'+result[i].id+'>')
                    )
                );

                $("#appetizer_list_tb").append(newtr);

                var newselect = $('<select id = "count_'+result[i].id+'" align="left" class="form-control" style="width:auto;">');
                for(var num=1;num<=100; num++){
                    var o = new Option(num, num);
                    newselect.append(o);
                }
                var newselectdiv = $('<div align="left" class="col-xs-3">').append(newselect);


                newtr = $('<tr id="dy_created_appetizer_2_'+result[i].id+'">').append(
                    $('<td align="left">').append(newselectdiv),
                    $('<td>').append(
                        //  $('<input onclick="addToCart('+result[i].id+',' +result[i].name+',' +$("#count_"+result[i].id).val()+','+result[i].price+')" id = "add_'+result[i].id+'" type="button" value="Add to Cart" class="btn btn-success">')
                        $('<input  type="button" onclick="addToCart('+result[i].id+')" value = "Add to Cart" class="btn" style=" background-color: #bc1a3a; color: #fff;  font-size:14px;">')
                    )
                );
                $("#appetizer_list_tb").append(newtr);
            }


        }});

}

function Refresh_main_course_lst(){

    var name_name = "nname";
    var price_name="price";
    var calo_name="calo";

    var category = "Main_Course";
    $.ajax({url: "/menu/findmenu/"+category,
        type: "GET",
        DataType: "text",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
            //alert(result.length);
            var url = location.href;
            var baseURL= url.substring(0, url.indexOf('/', 14));
            $("#course_list_tb #dy_created_course").remove();
            for(var i=0; i<result.length; i++){
                var newtr = $('<tr id="dy_created_course_1_'+result[i].id+'">').append(
                    $('<td>').append(result[i].id),
                    $('<td>').append(
                        $('<input type="text" id = "name_'+result[i].id+'" class="form-control" style="border:none; background-color: transparent;" name=' + name_name + ' readonly>').val(result[i].name)),
                    $('<td>').append(
                        $('<input type="text" id = "price_'+result[i].id+'" class="form-control" style="border:none; background-color: transparent;" name=' + price_name + ' readonly>').val(result[i].price)),
                    $('<td>').append(
                        $('<input type="text" id = "calories_'+ result[i].id + '" class="form-control" style="border:none; background-color: transparent;" name=' + calo_name + ' readonly>').val(result[i].calories)),
                    $('<td rowspan="2">').append(
                        $('<img src="'+baseURL+'/images/'+ result[i].id + '" class="img-rounded" onclick="largephoto(this.id)" alt="Image" width="100" height="100" id=img_'+result[i].id+'>')
                    )
                );

                $("#course_list_tb").append(newtr);

                var newselect = $('<select id = "count_'+result[i].id+'" align="left" class="form-control" style="width:auto;">');
                for(var num=1;num<=100; num++){
                    var o = new Option(num, num);
                    newselect.append(o);
                }
                var newselectdiv = $('<div align="left" class="col-xs-3">').append(newselect);


                newtr = $('<tr id="dy_created_course_2_'+result[i].id+'">').append(
                    $('<td align="left">').append(newselectdiv),
                    $('<td>').append(
                        //  $('<input onclick="addToCart('+result[i].id+',' +result[i].name+',' +$("#count_"+result[i].id).val()+','+result[i].price+')" id = "add_'+result[i].id+'" type="button" value="Add to Cart" class="btn btn-success">')
                        $('<input  type="button" onclick="addToCart('+result[i].id+')" value = "Add to Cart" class="btn" style=" background-color: #bc1a3a; color: #fff;  font-size:14px;">')
                    )
                );
                $("#course_list_tb").append(newtr);
            }


        }});

}

function Refresh_desert_lst(){

    var name_name = "nname";
    var price_name="price";
    var calo_name="calo";

    var category = "Desert";
    $.ajax({url: "/menu/findmenu/"+category,
        type: "GET",
        DataType: "text",
        error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(result){
            //alert(result.length);
            var url = location.href;
            var baseURL= url.substring(0, url.indexOf('/', 14));
            $("#desert_list_tb #dy_created_desert").remove();
            for(var i=0; i<result.length; i++){
                var newtr = $('<tr id="dy_created_desert_1_'+result[i].id+'">').append(
                    $('<td>').append(result[i].id),
                    $('<td>').append(
                        $('<input type="text" id = "name_'+result[i].id+'" class="form-control" style="border:none; background-color: transparent;" name=' + name_name + ' readonly>').val(result[i].name)),
                    $('<td>').append(
                        $('<input type="text" id = "price_'+result[i].id+'" class="form-control" style="border:none; background-color: transparent;" name=' + price_name + ' readonly>').val(result[i].price)),
                    $('<td>').append(
                        $('<input type="text" id = "calories_'+ result[i].id + '" class="form-control" style="border:none; background-color: transparent;" name=' + calo_name + ' readonly>').val(result[i].calories)),
                    $('<td rowspan="2">').append(
                        $('<img src="'+baseURL+'/images/'+ result[i].id + '" class="img-rounded" onclick="largephoto(this.id)" alt="Image" width="100" height="100" id=img_'+result[i].id+'>')
                    )
                );

                $("#desert_list_tb").append(newtr);

                var newselect = $('<select id = "count_'+result[i].id+'" align="left" class="form-control" style="width:auto;">');
                for(var num=1;num<=100; num++){
                    var o = new Option(num, num);
                    newselect.append(o);
                }
                var newselectdiv = $('<div align="left" class="col-xs-3">').append(newselect);


                newtr = $('<tr id="dy_created_desert_2_'+result[i].id+'">').append(
                    $('<td align="left">').append(newselectdiv),
                    $('<td>').append(
                        //  $('<input onclick="addToCart('+result[i].id+',' +result[i].name+',' +$("#count_"+result[i].id).val()+','+result[i].price+')" id = "add_'+result[i].id+'" type="button" value="Add to Cart" class="btn btn-success">')
                        $('<input  type="button" onclick="addToCart('+result[i].id+')" value = "Add to Cart" class="btn" style=" background-color: #bc1a3a; color: #fff;  font-size:14px;">')
                    )
                );
                $("#desert_list_tb").append(newtr);
            }


        }});

}

function addToCart(itemId) {
    //add row to table of order when click button
    var qty = $("#count_"+itemId).val();
    var name = $("#name_"+itemId).val();
    var price = $("#price_"+itemId).val();
    //alert(itemId+ " "+name+" "+qty+" "+price);

    //TODO need check the item already add to cart
    var table = document.getElementById("summary");
    var r = table.rows.length;
    var row = table.insertRow(r);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    cell1.innerHTML = itemId;
    cell2.innerHTML = name;
    cell3.innerHTML = qty;
    cell4.innerHTML = price;

    //TODO, need add together of all item
    //count subtotal, tax and total

    var total = parseFloat(document.getElementById("total").innerHTML);
    //var subtotal = sub + qty * price;
    total = total + qty * price;
    //var tax = (subtotal * 0.09);
    //var total = subtotal + tax;
    //var tax2 = tax.toFixed(2);
    //var total2 = total.toFixed(2);

    //document.getElementById("subtotal").innerHTML = subtotal;
    //document.getElementById("tax").innerHTML = tax2;
    document.getElementById("total").innerHTML = total;


}

function largephoto(id){

    var modal = document.getElementById('myModal');
    var img = document.getElementById(id);
    var modalImg = document.getElementById("img01");
    modal.style.display = "block";
    modalImg.src = img.src;
    modalImg.alt = img.alt;
}


function Attach_Event() {

    $('#mlayout_bt').click(function(){
        if($('#mlayout_bt').html()=="Cancel")
        return true;
        else
        {
            $('#summary_bd').empty();
            document.getElementById("total").innerHTML = "0.0";
        }
        return true;
    });

    $('#submit').click(function(){

        var datetime="";
        var date="";
        var time="";
        var hr="";
        var min="";
        var flag="";

        if($('#pickup_time').val()!="") {
            datetime = $('#pickup_time').val().split(" ");
            date = datetime[0];
            time = datetime[1].split(":");
            hr = time[0];
            min = time[1];
            flag = datetime[2];

           // alert(datetime + " " + date + " " + time + " " + hr + " " + min + " " + flag);
            if (min != "00" && min != "30")
            {
                alert("We only accept 30mins as a minimum time slot and the first slot start at 6:30 AM.");
                return false;
            }
            var timenumber = hr + min;
            if (flag == "AM") {
                if (parseInt(timenumber) < 630 || hr=="12")
                {
                    alert("Our pickup service only starts at 6:30 AM, please set your pickup time after this time.");
                    return false;
                }

            }
            else {
                if (parseInt(timenumber) > 900 && hr != "12")
                {
                    alert("Our pickup service end at 9:00 PM, please set your pickup time before this time.");
                    return false;
                }
            }

        }

        var table = document.getElementById("summary_bd");
        var itemlst = [];
        for(var ri= 0, currow; currow = table.rows[ri]; ri++)
        {
            //for(var ci= 0,col; col = currow.cells[ci]; ci++ )
            //{
            //    alert(col.innerHTML);
            //}
           // alert(currow.cells[0]+" "+currow.cells[1].innerHTML+" "+currow.cells[2].innerHTML+" "+currow.cells[3].innerHTML+" ");
            var curmenu = new menu(currow.cells[0].innerHTML,currow.cells[1].innerHTML,currow.cells[3].innerHTML,0,0,"");
            var itementry = new item(curmenu,currow.cells[2].innerHTML);
            itemlst.push(itementry);
        }

        if(datetime=="")
        {
            hr="00";
            min="-1";
        }

        if(flag=="PM"&&hr!="12")  hr=parseInt("12")+parseInt(hr);
        else if(flag=="AM"&&hr=="12")  hr="00";

        //alert(hr+min);

        var ordera = new userorder(itemlst,date,parseInt(hr+min),document.getElementById("total").innerHTML);

        var myJsonString = JSON.stringify(ordera);


        $.ajax({url: "/order/addorder/",
            type: "POST",
            DataType: "json",
            contentType: 'application/json',
            data: myJsonString,
            error: function(xhr){
                alert("An error occured: " + xhr.status + " " + xhr.statusText);
                return false;
            },
            success: function(result){

                //alert(result);

                //initialize modal before adding the new items:
                var mlayout_msg="";
                if(result=="-1")
                {
                    mlayout_msg="Too busy to prepare order at the scheduled time, please pick another time slot. "
                    $("#mlayout_msg").html(mlayout_msg).css("color","red");
                    $("#mlayout_bt").html("Cancel");
                }
                else
                {
                    mlayout_msg="Order has been set, please come and pick up at: "+result;
                    $("#mlayout_msg").html(mlayout_msg).css("color","green");
                    $("#mlayout_bt").html("Confirm");
                }
                $("#mlayout_total").html("0.0");
                $("#mlayout_bd").empty();

                var totallayout = document.getElementById("total");
                        //=document.getElementById("order_gen_info").innerHTML;
                var table = document.getElementById("summary_bd");
                var hmap={};

                for(var ri= 0, currow; currow = table.rows[ri]; ri++)
                {
                    if(currow.cells[0].innerHTML in hmap)
                    {
                        var sbitemobj = hmap[currow.cells[0].innerHTML];
                        sbitemobj.qty+=parseInt(currow.cells[2].innerHTML);
                        hmap[currow.cells[0].innerHTML]=sbitemobj;
                    }
                    else
                    {
                        var sbitemobj = new sbitem(currow.cells[1].innerHTML,parseInt(currow.cells[2].innerHTML),currow.cells[3].innerHTML)
                        hmap[currow.cells[0].innerHTML]=sbitemobj;
                    }

                }

                for(var e in hmap){
                    var eid = e;
                    var tmpobj = hmap[e];
                    var newtr = $('<tr>').append(
                        $('<td>').append(eid),
                        $('<td>').append(tmpobj.name),
                        $('<td>').append(tmpobj.qty),
                        $('<td>').append(tmpobj.price)
                    );

                    $("#mlayout_bd").append(newtr);
                }

                $("#mlayout_total").html(totallayout.innerHTML);
                return true;

            }});
    });


    //var menua = new menu(1,"test name",12.5,300,20,"Drink");
    //var itema = new item(menua,4);
    //var itemb = new item(menua,9);
    //var itemlst = [];
    //itemlst.push(itema);
    //itemlst.push(itemb);
    //var ordera = new userorder(itemlst,2016/5/2,1000,100);
    //
    //console.log(ordera);
    //
    //var listOfObjects = [];
    //var a = ["car", "bike", "scooter"];
    //a.forEach(function(entry) {
    //    var singleObj = {}
    //    singleObj['type'] = 'vehicle';
    //    singleObj['value'] = entry;
    //    listOfObjects.push(singleObj);
    //});
    //
    //console.log(listOfObjects);

//[{type: "vehicle", value: "car"}, {type: "vehicle", value: "bike"}, {type: "vehicle", value: "scooter"}]
}


