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

</head>
<body>
<%@ include file="partial/navtopProfile.jsp" %>

<div class="container">
    <!-- row 2 -->
    <div class="row" style="margin-left: 30px;margin-right: 30px; margin-top: 20px; margin-bottom: 20px;" >

            <!-- panel 1 for user profile -->
            <div class="panel panel-default " >
                <div class="panel-heading">
                    <h4  style="margin-top:0px; margin-bottom:0px;">Your Profile</h4>
                </div>
                <!-- panel body1 for email-->
                <div class="panel-body" >
                        <div class="lg-col-2"><h5>Your Email: </h5></div>
                        <div class="lg-col-2"> ${userName}</div>
                </div>
            </div> <!-- end of panel 1-->

           <!-- panel 2 for history order -->
            <div class="panel panel-default ">
                <div class="panel-heading">
                    <h4 id="title" style="margin-top:0px; margin-bottom:0px;">Order History</h4>
                </div>
                <!-- panel body -->

                <!-- loop for each order -->
                <c:forEach var="order" items="${historyOrders}">
                    <div class="row" style="margin-right: 10px;">
                        <!-- left side table -->
                        <div class="col-lg-6">
                            <table class="table" style="margin-left: 10px; ">
                                <thead>
                                    <tr>
                                        <th class="col-lg-1" >Order #</th>
                                        <th class="col-lg-2">Order Date</th>
                                        <th class="col-lg-3">Pick-Up Date Time</th>
                                        <th class="col-lg-1">Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${order.orderId}</td>
                                        <td>${order.orderDate}</td>
                                        <td>${order.pickupDate} &nbsp ${order.pickupTime} </td>
                                        <td id="orderStatus${order.orderId}">${order.orderStatus}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div> <!-- end of left side table -->

                        <!-- Right side table-->
                        <div class="col-lg-6">
                            <table class="table" style="margin-left: 10px; margin-right: 10px;">
                                <thead>
                                <tr>
                                    <th class="col-lg-2">Item Name</th>
                                    <th class="col-lg-2">Quantity</th>
                                    <th class="col-lg-2">Unit Price</th>

                                </tr>
                                </thead>
                                <tbody>
                                    <!-- for loop for items -->
                                    <c:forEach var="item" items="${order.itemList}">
                                        <tr>
                                            <td> ${item.menu.name}</td>
                                            <td> ${item.quantity }</td>
                                            <td> $ ${item.menu.price}</td>
                                        </tr>
                                    </c:forEach>
                                        <tr>
                                            <td>
                                                <!-- Order status is confirm, not fullfill, can be canceled-->
                                                <c:if test="${order.orderStatus == 'Confirmed'}">
                                                    <button id="cancel${order.orderId}" type="button" class="btn smred"
                                                            onclick="cancelOrder(${order.orderId})">Cancel Order </button>
                                                </c:if>
                                            </td>
                                            <td><h4>Total Price:</h4></td>
                                            <td><h4>$ ${order.orderPrice}</h4></td>
                                        </tr>
                                </tbody>
                            </table>
                        </div> <!-- end of right side table -->
                    </div>
                </c:forEach>  <!-- end of for loop for orders -->

    </div>  <!-- end of row2  -->

</div> <!-- end of container -->

<%@ include file="partial/footer.jsp" %>

<script>
    //button to cancel order
    function cancelOrder(orderId) {
        //change the status of this order
        var str1 ="orderStatus";
        var str2 = str1.concat(orderId);
        $.ajax({url: "/order/cancelorder/"+orderId,
            type: "GET",
            DataType: "text",
            error: function(xhr){
                alert("An error occured: " + xhr.status + " " + xhr.statusText);
            },
            success: function(result){
                document.getElementById(str2).innerHTML = "Cancelled";
            }});
    }

</script>


</body>
</html>
