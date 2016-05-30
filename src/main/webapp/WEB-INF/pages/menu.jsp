<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 4/27/16
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="partial/header.jsp" %>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap-datetimepicker.css">
    <script type="text/javascript" src="/static/js/orderscript.js"></script>
    <script type="text/javascript" src="/static/js/entity.js"></script>
    <script type="text/javascript" src="/static/js/moment.min.js"></script>
    <script type="text/javascript" src="/static/js/bootstrap-datetimepicker.min.js"></script>



</head>
<body>
<%@ include file="partial/navtopSignIn.jsp" %>
<%@ include file="partial/message.jsp" %>


<div class="container" style="margin-top: 10px;">

    <!-- row 2  map-->
    <div class="row" >
        <div class="col-lg-12">
            <div class="thumbnail">
                <img src="static/images/bg4.png">
            </div>
        </div>
    </div>  <!-- end of row2  -->


    <!-- row 3  menu tap-->
    <div class="row" style="margin-bottom: 10px;">

        <!-- left side of menu tab -->
        <div class="col-lg-8">
            <ul class="nav nav-tabs nav-justified">
                <li class="active"><a data-toggle="tab" href="#drink">Drink</a></li>
                <li><a data-toggle="tab" href="#appetizer">Appetizer</a></li>
                <li><a data-toggle="tab" href="#course">Main Course</a></li>
                <li><a data-toggle="tab" href="#desert">Desert</a></li>
            </ul>
            <div class="tab-content">

                <!-- tab for drink -->
                <div id="drink" class="tab-pane fade in active">

                    <div class="table_div">
                        <div class="table-responsive">
                            <table class="table table-striped" id="drink_list">
                                <thead>
                                <tr>
                                    <th>Item ID</th>
                                    <th>Name</th>
                                    <th>Unit Price</th>
                                    <th>Calories</th>
                                    <th>Picture</th>
                                </tr>
                                </thead>
                                <tbody id="drink_list_tb">

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div> <!-- end of tab drink-->


                <!-- tab for appetizer -->
                <div id="appetizer" class="tab-pane fade">

                    <div class="table_div">
                        <div class="table-responsive">
                            <table class="table table-hover" id="appetizer_list">
                                <thead>
                                <tr>
                                    <th>Item ID</th>
                                    <th>Name</th>
                                    <th>Unit Price</th>
                                    <th>Calories</th>
                                    <th>Picture</th>
                                </tr>
                                </thead>
                                <tbody id="appetizer_list_tb">

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div> <!-- end of tab for appetizer -->


                <!-- tab for course -->
                <div id="course" class="tab-pane fade">

                    <div class="table_div">
                        <div class="table-responsive">
                            <table class="table table-hover" id="course_list">
                                <thead>
                                <tr>
                                    <th>Item ID</th>
                                    <th>Name</th>
                                    <th>Unit Price</th>
                                    <th>Calories</th>
                                    <th>Picture</th>
                                </tr>
                                </thead>
                                <tbody id="course_list_tb">

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div> <!-- end of tab for course -->

                <!-- tab for desert -->
                <div id="desert" class="tab-pane fade">

                    <div class="table_div">
                        <div class="table-responsive">
                            <table class="table table-hover" id="desert_list">
                                <thead>
                                <tr>
                                    <th>Item ID</th>
                                    <th>Name</th>
                                    <th>Unit Price</th>
                                    <th>Calories</th>
                                    <th>Picture</th>
                                </tr>
                                </thead>
                                <tbody id="desert_list_tb">

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div> <!-- end of tab desert-->

            </div> <!-- end of tab content -->
        </div>  <!-- end of left side of menu tab -->

        <!-- right side of order summary -->
        <div id ="order_gen_info" class=" col-lg-4"  >
            <div class="panel panel-default " style=" border:1px solid #f9d6dc;">
                <div class="panel-heading">
                    <h4 class="text-center" style="color: #8f142c;">Your Order Summary </h4>
                </div> <!-- end of panel heading -->

                <div class="panel-body ">

                    <table id="order" class="table" style="font-size: 14px;">
                        <thead>
                        <tr>
                            <td class="col-lg-1">Item#</td>
                            <td class="col-lg-5">Name</td>
                            <td class="col-lg-1">Qty</td>
                            <td class="col-lg-2">Price</td>
                        </tr>
                        </thead>
                    </table>
                    <div class="table_div_order">
                    <table id="summary" class="table">
                        <tbody id = "summary_bd">
                        </tbody>
                    </table>
                    </div>
                    <div>
                        <table>
                            <tr>
                                <th class="col-lg-7"></th>
                                <th class="col-lg-3">Total $</th>
                                <th class="col-lg-2" id ="total" align="right">0.0</th>
                            </tr>
                        </table>
                    </div>

                    <h5>Pick up time :</h5>
                    <div class="input-group date" id="example">
                        <input id = "pickup_time" type="text" class="form-control" />
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>

                    <!-- Two button -->
                    <div class="row"  >
                        <button id="cancel" type="button" class="col-lg-4  btn smgray" style="margin-top: 15px; margin-left: 20px;"
                                onclick="cancelOrder()">Cancel</button>
                        <button id="submit" type="button" class="col-lg-6  btn smred" style="margin-top: 15px; margin-left: 22px;"
                                data-toggle="modal" data-target="#basicModal">
                            Check Out Your Order </button>
                    </div><!-- end of two button -->
                </div> <!-- end of panel body -->

            </div> <!-- end of panel -->

        </div> <!-- end of right side of order summary -->

    </div>  <!-- end of row2  -->


</div> <!-- end of container -->

<%@ include file="partial/footer.jsp" %>


<script>
    //pick up date from today to 30 days
    var today= new Date();

    var end = new Date();
    end.setDate(end.getDate() +29);
    var endd = end.getDate();
    var endm = end.getMonth()+1;
    var endy = end.getFullYear();
    if (endm < 10) endm = "0" + endm;
    if (endd < 10) endd = "0" + endd;
    var endday = endy + "-" + endm + "-" + endd;


    $(document).ready(function(){
        Attach_Event();
        Refresh_Drink_lst();
        Refresh_appetizer_lst();
        Refresh_main_course_lst();
        Refresh_desert_lst();

        //pick up date from tday to 30 days and stepping for 30 minutes
        $('#example').datetimepicker({
            stepping :30,
            minDate: today,
            maxDate: endday,
            sideBySide:true,
        });

    });

    <!-- function when click cancel button -->
    function cancelOrder() {

        // delete the rows in table order
        var table = document.getElementById("summary");
        var r = table.rows.length;
        while(r--) table.deleteRow(r);

        //totol # to 0
        document.getElementById("total").innerHTML = "0.0";
    }
</script>


</body>
</html>
