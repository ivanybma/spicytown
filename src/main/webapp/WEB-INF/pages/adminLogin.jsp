<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 4/27/16
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="partial/header.jsp" %>

</head>
<body>
<%@ include file="partial/navtop.jsp" %>

<div class="container" style="margin-top: 10px;">

    <!-- row 2  big background pic-->
    <div class="row" >
        <div class="col-lg-12">
            <div class="thumbnail">
                <img src="static/images/bg2.png">
            </div>
        </div>
    </div>  <!-- end of row2  -->

    <!-- row 3 input -->
    <div class="row" style="margin-bottom: 40px;" >
        <form method="post" class ="col-lg-offset-4
         col-lg-4" role="form" style="border:1px solid #dcdcdc;" action="/admin">
            <h3 style="color:#862a3a;">Admin Sign In</h3>
            <div class="form-group">
                <label for="Name">Admin Name</label>
                <input type="email" class="form-control" id="name">
            </div>
            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd">
            </div>
            <div class="form-group">
                <button id="adminIn" type="submit" class="btn red btn-block">Submit</button>
            </div>
        </form>
    </div>




</div> <!-- end of container -->

<%@ include file="partial/footer.jsp" %>

</body>
</html>

