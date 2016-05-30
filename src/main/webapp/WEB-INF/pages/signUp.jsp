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

<div class="container" style="margin-top: 60px; margin-bottom: 60px;">

    <!-- left  big background pic-->
        <div class="col-lg-5 " >
            <div class="thumbnail" style="margin-left: 30px;">
                <img src="static/images/signUp.png">
            </div>
        </div>

    <div >
        <!-- row 3 input -->
        <div class="row" style="margin-top: 8px">

            <!-- action="/userlogin/adduser" -->
            <form id="signUpData" method="post" role="form" class ="col-lg-4 col-lg-offset-1" style="border:1px solid #dcdcdc;"
                    onsubmit="return checkPassword(this);" >

                <fieldset>
                <h3 style="color:#862a3a;">Sign Up</h3>
                <div class="form-group">
                    <label for="email">Email address:</label>
                    <input type="email" class="form-control" id="email" name="name">
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" id="pwd" name="password">
                </div>
                <div class="form-group">
                    <label for="pwd">Confirm Password:</label>
                    <input type="password" class="form-control" id="pwdCon" name="password2">
                </div>
                <div class="form-group">
                    <button id="signUp" type="submit" class="btn red btn-block">Submit</button>
                </div>
                </fieldset>
            </form>
        </div>

    </div>

</div> <!-- end of container -->

<script>


    $("form#signUpData").submit(function () {

//        console.log($(this).attr("pwd"));
//        console.log($("#pwdCon").id);

        var formData = new FormData($(this)[0]);
//        console.log(formData.get("name"));
//        console.log(formData.get("password"));
        console.log("submitted");


        $.ajax({
            url: "/userlogin/adduser",
            type: 'POST',
            data: formData,
            async: false,
            success: function (data) {
                alert("We send you a verficaiton link to your Email, please go to your email to click the link.");
                window.location.replace("/singIn");
            },
            cache: false,
            contentType: false,
            processData: false
        });

        return false;
    });
</script>


<%@ include file="partial/footer.jsp" %>
<script>
    function checkPassword(form){

        if(form.name.value ==""){
            alert("Error: Please input valid email address!");
            form.email.focus();
            return false;
        }

        if(form.password.value ==""){
            alert("Error: Please input valid password!");
            form.email.focus();
            return false;
        }

        if(form.password.value != form.password2.value){
            alert("Error: Please check your password!");
            form.pwd1.focus();
            return false;
        }
    }
</script>

</body>
</html>
