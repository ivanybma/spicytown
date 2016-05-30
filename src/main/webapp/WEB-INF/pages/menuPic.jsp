<html>
<head>
    <%@ include file="partial/header.jsp" %>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script type="text/javascript" src="/static/js/myscript.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/cs.css">

</head>
<body>
<img id="myImg" src="images/5" alt="Image" width="40" height="40">
<div id="myModal" class="modal">
    <span class="close">×</span>
    <img class="modal-content" id="img01">
    <div id="caption"></div>
</div>

<div class="add_div">
    <form id="data" method="post" class="form-inline" enctype="multipart/form-data">
        <fieldset>
            <input class="form-control" type="text" id="new_drink_name" name="name" contenteditable="true" placeholder="Name">
            <input type="hidden" name="category" value="Drink"/>
            <input class="form-control" name="file" type="file"/>
            <input class="form-control" type="text" name="price" id="new_drink_price" placeholder="Price"/>
            <input class="form-control" type="text" id="new_drink_cal"  name="calories" contenteditable="true" placeholder="Calories"/>
            <input class="form-control" type="text" id="new_drink_pretime"  name="pretime" contenteditable="true" placeholder="Prepare Time"/>
            <button id = "drink_add" type="submit" class="btn btn-primary">Submit</button>
        </fieldset>
        <span id="errmsg">&nbsp</span>
    </form>

</div>

<script>
    $("form#data").submit(function () {

        var formData = new FormData($(this)[0]);
        console.log(formData.get("name"));
        console.log(formData.get("category"));
        console.log("submitted");

        $.ajax({
            url: "/menu/addmenu",
            type: 'POST',
            data: formData,
            async: false,
            success: function (data) {
                alert(data)
            },
            cache: false,
            contentType: false,
            processData: false
        });

        return false;
    });

    // Get the modal
    var modal = document.getElementById('myModal');

    // Get the image and insert it inside the modal - use its "alt" text as a caption
    var img = document.getElementById('myImg');
    var modalImg = document.getElementById("img01");
    var captionText = document.getElementById("caption");
    img.onclick = function(){
        modal.style.display = "block";
        modalImg.src = this.src;
        modalImg.alt = this.alt;
        captionText.innerHTML = this.alt;
    }

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }
</script>
</body>
</html>