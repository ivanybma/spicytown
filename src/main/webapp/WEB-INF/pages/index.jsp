<%--
    Created by IntelliJ IDEA.
    User: qi
    Date: 4/27/16
    Time: 11:16 AM
    To change this template use File | Settings | File Templates.
    --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="partial/header.jsp"/>

</head>
<body>

<!-- verify user signin or not -->

<c:if test="${userRole == null}">
        <jsp:include page="partial/navtop.jsp"/>
</c:if>

<c:if test="${userRole == 'ROLE_ADMIN'}">
    <jsp:include page="partial/navtopAdmin.jsp"/>
</c:if>

<c:if test="${userRole == 'ROLE_USER'}">
    <jsp:include page="partial/navtopSignIn.jsp"/>
</c:if>


<div class="container">


    <!-- row 2  big background pic-->

    <div class="jumbotron bg-img">
        </br> </br> </br></br></br>
        <h2 style="color:#ffffff;text-align: center;">The best spicy food in the town. </h2> </br>
        <p style="color:#ffffff; text-align: center;"> If you like spicy food, don't wait, order online now.</p>
        </br></br></br></br>
        <p style="text-align: center;"><a href="menu"><button type="button" class="btn red">&nbsp Goto Menu >> </button></a></p>
    </div>

    <!-- row 3  for Dishes Pic-->
    <div class="row" >
        <div class="col-lg-3">
            <div class="thumbnail">
                <img src="static/images/ck.jpg"/>
            </div>
        </div>

        <div class="col-lg-3">
            <div class="thumbnail">
                <img src="static/images/ou.jpg">
            </div>
        </div>

        <div class="col-lg-3">
            <div class="thumbnail">
                <img src="static/images/pork.jpg">
            </div>
        </div>

        <div class="col-lg-3">
            <div class="thumbnail">
                <img src="static/images/fh.jpg">
            </div>
        </div>
    </div>  <!-- end of row3  -->

</div> <!-- end of container -->

<jsp:include page="partial/footer.jsp"/>

</body>
</html>
