<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 4/27/16
  Time: 11:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default navbar-static-top" style="background-color: #fbf1fc">

    <div class="container" >
        <ul class="nav navbar-nav">
            <li class="navbar-left">
                <a href="/">
                    <img alt="Brand" src="/static/images/logosmall.png" style="height:60px">
                </a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>

                <div>
                    <%--<a href="/adminlogin"><button type="button" class="btn red-Sign ">Administrator</button></a>--%>
                    <a href="/signUp"><button type="button" class="btn red-Sign ">Sign Up</button></a>
                    <a href="/signIn"><button type="button" class="btn red-Sign">Sign In</button></a>
                </div>

            </li>
        </ul>
    </div> <!-- end of container -->
</nav>
