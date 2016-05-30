<html>
<head>
	<%@ include file="partial/header.jsp" %>
	<link href="/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" type="text/css" href="/static/css/cs.css">
	<script type="text/javascript" src="/static/js/myscript.js"></script>
	<script type="text/javascript" src="/static/js/entity.js"></script>
	<script src="/static/js/vendor/jquery.ui.widget.js"></script>
	<script src="/static/js/jquery.iframe-transport.js"></script>
	<script src="/static/js/jquery.fileupload.js"></script>
	<script type="text/javascript" src="/static/js/moment.min.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="/static/js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
	<title>Admin Menu</title>

</head>
<body>
<div class="wrapper">
	<%@ include file="partial/navtopAdmin.jsp" %>
	<%@ include file="partial/orderdetail.jsp" %>

	<div id="myModal" class="modal">
		<img class="modal-content" id="img01">
	</div>
	<div class="container">

		<ul class="nav nav-tabs ">
			<li class="active"><a data-toggle="tab" href="#Drink">Drink</a></li>
			<li><a data-toggle="tab" href="#Appetizer">Appetizer</a></li>
			<li><a data-toggle="tab" href="#Main_Course">Main Course</a></li>
			<li><a data-toggle="tab" href="#Desert">Desert</a></li>
			<li><a data-toggle="tab" id="report" href="#function_report">Report Function</a></li>
			<li><a data-toggle="tab" id="catstat" href="#catstat_function">Category statistic</a></li>
		</ul>

		<div class="tab-content">
			<div id="Drink" class="tab-pane fade in active">
				<div class="table_div">
					<div class="table-responsive">
						<table class="table table-hover" id="drink_list">
							<thead>
							<tr>
								<th>Item ID</th>
								<th>Name</th>
								<th>Picture</th>
								<th>Unit Price</th>
								<th>Calories</th>
								<th>Preparation Time</th>
								<th>Action</th>
							</tr>
							</thead>
							<tbody id="drink_list_tb">
							</tbody>
						</table>
					</div>
				</div>
				<div class="add_div">

						<form id="data" name="drink_data" method="post" class="form-inline" enctype="multipart/form-data">
							<fieldset>
									<input class="form-control" type="text" id="new_drink_name" name="name" contenteditable="true" placeholder="Name">
									<input type="hidden" name="category" value="Drink"/>
									<input class="form-control" name="file" type="file" id="new_drink_photo"/>
									<input class="form-control" type="text" name="price" id="new_drink_price" placeholder="Price"/>
									<input class="form-control" type="text" id="new_drink_cal"  name="calories" contenteditable="true" placeholder="Calories"/>
									<input class="form-control" type="text" id="new_drink_pretime"  name="prepTime" contenteditable="true" placeholder="Prepare Time"/>
									<button id = "drink_add" type="submit" class="btn smred">Add</button>
							</fieldset>
							<span id="drink_errmsg">&nbsp</span>
						</form>

				</div>
			</div>
			<div id="Appetizer" class="tab-pane fade">
				<div class="table_div">
					<div class="table-responsive">
						<table class="table table-hover" id="appetizer_list">
							<thead>
							<tr>
								<th>Item ID</th>
								<th>Name</th>
								<th>Picture</th>
								<th>Unit Price</th>
								<th>Calories</th>
								<th>Preparation Time</th>
								<th>Action</th>
							</tr>
							</thead>
							<tbody id="appetizer_list_tb">
							</tbody>
						</table>
					</div>
				</div>
				<div class="add_div">

					<form id="appetizer_data" name="appetizer_data" method="post" class="form-inline" enctype="multipart/form-data">
						<fieldset>
							<input class="form-control" type="text" id="new_appetizer_name" name="name" contenteditable="true" placeholder="Name">
							<input type="hidden" name="category" value="Appetizer"/>
							<input class="form-control" name="file" type="file" id="new_appetizer_photo"/>
							<input class="form-control" type="text" name="price" id="new_appetizer_price" placeholder="Price"/>
							<input class="form-control" type="text" id="new_appetizer_cal"  name="calories" contenteditable="true" placeholder="Calories"/>
							<input class="form-control" type="text" id="new_appetizer_pretime"  name="prepTime" contenteditable="true" placeholder="Prepare Time"/>
							<button id = "appetizer_add" type="submit" class="btn btn-primary">Add</button>
						</fieldset>
						<span id="appetizer_errmsg">&nbsp</span>
					</form>

				</div>

			</div>
			<div id="Main_Course" class="tab-pane fade">
				<div class="table_div">
					<div class="table-responsive">
						<table class="table table-hover" id="main_course_list">
							<thead>
							<tr>
								<th>Item ID</th>
								<th>Name</th>
								<th>Picture</th>
								<th>Unit Price</th>
								<th>Calories</th>
								<th>Preparation Time</th>
								<th>Action</th>
							</tr>
							</thead>
							<tbody id="main_course_list_tb">
							</tbody>
						</table>
					</div>
				</div>
				<div class="add_div">

					<form id="main_course_data" name="main_course_data" method="post" class="form-inline" enctype="multipart/form-data">
						<fieldset>
							<input class="form-control" type="text" id="new_main_course_name" name="name" contenteditable="true" placeholder="Name">
							<input type="hidden" name="category" value="Main_Course"/>
							<input class="form-control" name="file" type="file" id="new_main_course_photo"/>
							<input class="form-control" type="text" name="price" id="new_main_course_price" placeholder="Price"/>
							<input class="form-control" type="text" id="new_main_course_cal"  name="calories" contenteditable="true" placeholder="Calories"/>
							<input class="form-control" type="text" id="new_main_course_pretime"  name="prepTime" contenteditable="true" placeholder="Prepare Time"/>
							<button id = "main_course_add" type="submit" class="btn btn-primary">Add</button>
						</fieldset>
						<span id="main_course_errmsg">&nbsp</span>
					</form>

				</div>
			</div>
			<div id="Desert" class="tab-pane fade">
				<div class="table_div">
					<div class="table-responsive">
						<table class="table table-hover" id="desert_list">
							<thead>
							<tr>
								<th>Item ID</th>
								<th>Name</th>
								<th>Picture</th>
								<th>Unit Price</th>
								<th>Calories</th>
								<th>Preparation Time</th>
								<th>Action</th>
							</tr>
							</thead>
							<tbody id="desert_list_tb">
							</tbody>
						</table>
					</div>
				</div>
				<div class="add_div">

					<form id="desert_data" name="desert_data" method="post" class="form-inline" enctype="multipart/form-data">
						<fieldset>
							<input class="form-control" type="text" id="new_desert_name" name="name" contenteditable="true" placeholder="Name">
							<input type="hidden" name="category" value="Desert"/>
							<input class="form-control" name="file" type="file" id="new_desert_photo"/>
							<input class="form-control" type="text" name="price" id="new_desert_price" placeholder="Price"/>
							<input class="form-control" type="text" id="new_desert_cal"  name="calories" contenteditable="true" placeholder="Calories"/>
							<input class="form-control" type="text" id="new_desert_pretime"  name="prepTime" contenteditable="true" placeholder="Prepare Time"/>
							<button id = "desert_add" type="submit" class="btn btn-primary">Add</button>
						</fieldset>
						<span id="desert_errmsg">&nbsp</span>
					</form>


				</div>
			</div>
			<div id="function_report" class="tab-pane fade">
                <div class="row">
					<div class="input-group" style="padding-left: 14px; width: 100%;">
						<form action="" class="form-inline"  role="form">
							<fieldset>
									<div class="input-group date form_date col-sm-4" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
										<input class="form-control" size="16" type="text" value="" readonly placeholder="StartDate">
										<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>
									<div class="input-group date form_date col-sm-4" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input3" data-link-format="yyyy-mm-dd">
										<input class="form-control" size="16" type="text" value="" readonly placeholder="EndDate">
										<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
										<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>
										<div class="input-group col-sm-2">
										<select id="sortby" class="selectpicker form-control">
											<option value="ordertime">Sorted By Order Time</option>
											<option value="fullfillstr">Sorted By Fullfillment Start</option>
										</select>
										</div>
										<div class="input-group col-sm-1">
											<button id="sortbt" type="button" class="btn btn-default" onclick="reportrst()">Submit</button>
										</div>
										<input type="hidden" id="dtp_input2" value="" />
										<input type="hidden" id="dtp_input3" value="" />

							</fieldset>
						</form>
					</div>
                </div>
				<div class="table_div">
					<div class="table-responsive">
						<table class="table table-hover" id="function_report_list">
							<thead>
							<tr>
								<th>Order ID</th>
								<th>Order Time</th>
								<th>Fullfillment Start</th>
								<th>Ready Time</th>
								<th>Pickup Time</th>
								<th>Status</th>
								<th>Customer Email</th>
								<th>Action</th>
							</tr>
							</thead>
							<tbody id="function_report_list_tb">
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div id="catstat_function" class="tab-pane fade">
				<div class="row">
				<div class="input-group" style="padding-left: 14px; width: 100%;">
					<form action="" class="form-inline"  role="form">
						<fieldset>

					<div class="input-group date form_date col-sm-4" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input4" data-link-format="yyyy-mm-dd">
						<input class="form-control" size="16" type="text" value="" readonly placeholder="StartDate">
						<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<div class="input-group date form_date col-sm-4" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input5" data-link-format="yyyy-mm-dd">
						<input class="form-control" size="16" type="text" value="" readonly placeholder="EndDate">
						<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<div class="input-group col-sm-2">
				<select id="catstatlst" class="selectpicker form-control">
					<option value="Drink">Drink</option>
					<option value="Appetizer">Appetizer</option>
					<option value="Main_Course">Main Course</option>
					<option value="Desert">Desert</option>
				</select>
						</div>
					<div class="input-group col-sm-1">
					<button id="catstatbt" type="button" class="btn btn-default" onclick="catstatrst()">Submit</button>
						</div>
					<input type="hidden" id="dtp_input4" value="" />
					<input type="hidden" id="dtp_input5" value="" />

						</fieldset>
					</form>
				</div>
				</div>
				<div class="table_div">
					<div class="table-responsive">
						<table class="table table-hover" id="catstat_list">
							<thead>
							<tr>
								<th>Item ID</th>
								<th>Name</th>
								<th>Picture</th>
								<th>Unit Price</th>
								<th>Calories</th>
								<th>Preparation Time</th>
								<th>Count</th>
							</tr>
							</thead>
							<tbody id="catstat_list_tb">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		<br/>
		<div><button type="button" class="btn lgred" id = "reset_order_queue">Reset Order</button>&nbsp&nbsp<span id="order_msg"></span></div>
		<br/>
	</div>

	<div class="push">
		<%@ include file="partial/footer.jsp" %>
	</div>
</div>

<script>
	$(document).ready(function(){
//alert("dd");
		Refresh_Drink_lst();
		Refresh_appetizer_lst();
		Refresh_main_course_lst();
		Refresh_desert_lst();
		reportrst();
		catstatrst();
		Attach_Event();


		$('.form_date').datetimepicker({
//			language:  'fr',
			weekStart: 1,
			todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
		});
	});
</script>
</body>
</html>