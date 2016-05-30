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
<%@ include file="partial/navtop.jsp" %>

<div class="container" style="margin-top: 60px; margin-bottom: 60px;">

    <!-- left  big background pic-->
    <div class="col-lg-5 " >
        <div class="thumbnail" style="margin-left: 30px;">
            <img src="static/images/signUp.png">
        </div>
    </div>

    <div >
        <!-- row 3 input -->
        <div class="row" style="margin-top: 35px">

            <!-- /signIn?error=true -->
            <c:if test="${param.error == 'true'}">
                <div class= "col-lg-4 col-lg-offset-1" style="color: #bc1a3a;" >
                    <h5>Sign In Failed! </h5>
                    <h5>Please check your Email and Password.</h5>
                </div>
            </c:if>

            <form id="signinData" method="post" role="form" class ="col-lg-4 col-lg-offset-1" style="border:1px solid #dcdcdc;">
                <h3 style="color:#862a3a;">Sign In</h3>
                <div class="form-group">
                    <label for="email">Email address:</label>
                    <input type="email" class="form-control" id="email" name="name">
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" id="pwd" name="password">
                </div>
                <div class="form-group">
                    <button id="signIn" type="submit" class="btn red btn-block">Submit</button>
                </div>
            </form>
        </div>

    </div>

</div> <!-- end of container -->



<%@ include file="partial/footer.jsp" %>

</body>
</html>
