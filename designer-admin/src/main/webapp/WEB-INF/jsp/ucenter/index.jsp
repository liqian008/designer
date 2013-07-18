<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Pannonia - premium responsive admin template by Eugene Kopyov</title>
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css" />
<!--[if IE 8]><link href="css/ie8.css" rel="stylesheet" type="text/css" /><![endif]-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&amp;sensor=false"></script>

<script type="text/javascript" src="../js/plugins/charts/excanvas.min.js"></script>
<script type="text/javascript" src="../js/plugins/charts/jquery.flot.js"></script>
<script type="text/javascript" src="../js/plugins/charts/jquery.flot.resize.js"></script>
<script type="text/javascript" src="../js/plugins/charts/jquery.sparkline.min.js"></script>

<script type="text/javascript" src="../js/plugins/ui/jquery.easytabs.min.js"></script>
<script type="text/javascript" src="../js/plugins/ui/jquery.collapsible.min.js"></script>
<script type="text/javascript" src="../js/plugins/ui/prettify.js"></script>
<script type="text/javascript" src="../js/plugins/ui/jquery.colorpicker.js"></script>
<script type="text/javascript" src="../js/plugins/ui/jquery.timepicker.min.js"></script>
<script type="text/javascript" src="../js/plugins/ui/jquery.fancybox.js"></script>
<script type="text/javascript" src="../js/plugins/ui/jquery.fullcalendar.min.js"></script>

<script type="text/javascript" src="../js/plugins/forms/jquery.uniform.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/jquery.tagsinput.min.js"></script>

<script type="text/javascript" src="../js/plugins/tables/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="../js/files/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/functions/index.js"></script>

<script type="text/javascript" src="../js/charts/graph.js"></script>
<script type="text/javascript" src="../js/charts/chart1.js"></script>
<script type="text/javascript" src="../js/charts/chart2.js"></script>
<script type="text/javascript" src="../js/charts/chart3.js"></script>

</head>

<body>

	<jsp:include page="../inc/header.jsp"></jsp:include>


	<!-- Content container -->
	<div id="container">

		<jsp:include page="../inc/leftSidebar.jsp"></jsp:include>


		<!-- Content -->
		<div id="content">

		    <!-- Content wrapper -->
		    <div class="wrapper">

			    <!-- Breadcrumbs line -->
			    <div class="crumbs">
		            <ul id="breadcrumbs" class="breadcrumb"> 
		                <li><a href="http://demo.kopyov.com/pannonia/index.html">Dashboard</a></li>
		                <li class="active"><a href="calendar.html" title="">Calendar</a></li>
		            </ul>
			        
		            <ul class="alt-buttons">
		                <li><a href="index.html#" title=""><i class="icon-signal"></i><span>Stats</span></a></li>
		                <li><a href="index.html#" title=""><i class="icon-comments"></i><span>Messages</span></a></li>
		                <li class="dropdown"><a href="index.html#" title="" data-toggle="dropdown"><i class="icon-tasks"></i><span>Tasks</span> <strong>(+16)</strong></a>
		                	<ul class="dropdown-menu pull-right">
		                        <li><a href="index.html#" title=""><i class="icon-plus"></i>Add new task</a></li>
		                        <li><a href="index.html#" title=""><i class="icon-reorder"></i>Statement</a></li>
		                        <li><a href="index.html#" title=""><i class="icon-cog"></i>Settings</a></li>
		                	</ul>
		                </li>
		            </ul>
			    </div>
			    <!-- /breadcrumbs line -->

			    <!-- Page header -->
			    <div class="page-header">
			    	<div class="page-title">
				    	<h5>Dashboard</h5>
				    	<span>Good morning, Eugene!</span>
			    	</div>

			    	<ul class="page-stats">
			    		<li>
			    			<div class="showcase">
			    				<span>New visits</span>
			    				<h2>22.504</h2>
			    			</div>
			    			<div id="total-visits" class="chart">10,14,8,45,23,41,22,31,19,12, 28, 21, 24, 20</div>
			    		</li>
			    		<li>
			    			<div class="showcase">
			    				<span>My balance</span>
			    				<h2>$16.290</h2>
			    			</div>
			    			<div id="balance" class="chart">10,14,8,45,23,41,22,31,19,12, 28, 21, 24, 20</div>
			    		</li>
			    	</ul>
			    </div>
			    <!-- /page header -->

			    <!-- Action tabs -->
			    <div class="actions-wrapper">
				    <div class="actions">

				    	<div id="user-stats">
					        <ul class="round-buttons">
					            <li><div class="depth"><a href="index.html" title="Add new post" class="tip"><i class="icon-plus"></i></a></div></li>
					            <li><div class="depth"><a href="index.html" title="View statement" class="tip"><i class="icon-signal"></i></a></div></li>
					            <li><div class="depth"><a href="index.html" title="Media posts" class="tip"><i class="icon-reorder"></i></a></div></li>
					            <li><div class="depth"><a href="index.html" title="RSS feed" class="tip"><i class="icon-rss"></i></a></div></li>
					            <li><div class="depth"><a href="index.html" title="Create new task" class="tip"><i class="icon-tasks"></i></a></div></li>
					            <li><div class="depth"><a href="index.html" title="Layout settings" class="tip"><i class="icon-cogs"></i></a></div></li>
					        </ul>
				    	</div>

				    	<div id="quick-actions">
				    		<ul class="statistics">
				    			<li>
				    				<div class="top-info">
					    				<a href="index.html#" title="" class="blue-square"><i class="icon-plus"></i></a>
					    				<strong>12,476</strong>
					    			</div>
									<div class="progress progress-micro"><div class="bar" style="width: 60%;"></div></div>
									<span>User registrations</span>
				    			</li>
				    			<li>
				    				<div class="top-info">
					    				<a href="index.html#" title="" class="red-square"><i class="icon-hand-up"></i></a>
					    				<strong>621,873</strong>
					    			</div>
									<div class="progress progress-micro"><div class="bar" style="width: 20%;"></div></div>
									<span>Total clicks</span>
				    			</li>
				    			<li>
				    				<div class="top-info">
					    				<a href="index.html#" title="" class="purple-square"><i class="icon-shopping-cart"></i></a>
					    				<strong>562</strong>
					    			</div>
									<div class="progress progress-micro"><div class="bar" style="width: 90%;"></div></div>
									<span>New orders</span>
				    			</li>
				    			<li>
				    				<div class="top-info">
					    				<a href="index.html#" title="" class="green-square"><i class="icon-ok"></i></a>
					    				<strong>$45,360</strong>
					    			</div>
									<div class="progress progress-micro"><div class="bar" style="width: 70%;"></div></div>
									<span>General balance</span>
				    			</li>
				    			<li>
				    				<div class="top-info">
					    				<a href="index.html#" title="" class="sea-square"><i class="icon-group"></i></a>
					    				<strong>562K+</strong>
					    			</div>
									<div class="progress progress-micro"><div class="bar" style="width: 50%;"></div></div>
									<span>Total users</span>
				    			</li>
				    			<li>
				    				<div class="top-info">
					    				<a href="index.html#" title="" class="dark-blue-square"><i class="icon-facebook"></i></a>
					    				<strong>36,290</strong>
					    			</div>
									<div class="progress progress-micro"><div class="bar" style="width: 93%;"></div></div>
									<span>Facebook fans</span>
				    			</li>
				    		</ul>
				    	</div>

				    	<ul class="action-tabs">
				    		<li><a href="index.html#user-stats" title="">Quick actions</a></li>
				    		<li><a href="index.html#quick-actions" title="">Website statistics</a></li>
				    	</ul>
				    </div>
				</div>
			    <!-- /action tabs -->

		    	<h5 class="widget-name"><i class="icon-th"></i>Media table</h5>

                <!-- Media datatable -->
                <div class="widget">
                	<div class="navbar">
                    	<div class="navbar-inner">
                        	<h6>Media table</h6>
                            <div class="nav pull-right">
                                <a href="index.html#" class="dropdown-toggle navbar-icon" data-toggle="dropdown"><i class="icon-cog"></i></a>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="index.html#"><i class="icon-plus"></i>Add new option</a></li>
                                    <li><a href="index.html#"><i class="icon-reorder"></i>View statement</a></li>
                                    <li><a href="index.html#"><i class="icon-cogs"></i>Parameters</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="table-overflow">
                        <table class="table table-striped table-bordered table-checks media-table">
                            <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>Description</th>
                                    <th>Date</th>
                                    <th>File info</th>
                                    <th class="actions-column">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
			                        <td><a href="img/demo/big.jpg" title="" class="lightbox"><img src="img/demo/users/face1.png" alt="" /></a></td>
			                        <td><a href="index.html#" title="">Image2 description</a></td>
			                        <td>Feb 12, 2012. 12:28</td>
			                        <td class="file-info">
			                        	<span><strong>Size:</strong> 215 Kb</span>
			                        	<span><strong>Format:</strong> .jpg</span>
			                        	<span><strong>Dimensions:</strong> 120 x 120</span>
			                        </td>
			                        <td>
		                                <ul class="navbar-icons">
		                                    <li><a href="index.html#" class="tip" title="Add new option"><i class="icon-plus"></i></a></li>
		                                    <li><a href="index.html#" class="tip" title="View statistics"><i class="icon-reorder"></i></a></li>
		                                    <li><a href="index.html#" class="tip" title="Parameters"><i class="icon-cogs"></i></a></li>
		                                </ul>
			                        </td>
                                </tr>
                                <tr>
			                        <td><a href="img/demo/big.jpg" title="" class="lightbox"><img src="img/demo/users/face2.png" alt="" /></a></td>
			                        <td><a href="index.html#" title="">Image1 description</a></td>
			                        <td>Feb 12, 2012. 12:28</td>
			                        <td class="file-info">
			                        	<span><strong>Size:</strong> 215 Kb</span>
			                        	<span><strong>Format:</strong> .jpg</span>
			                        	<span><strong>Dimensions:</strong> 120 x 120</span>
			                        </td>
			                        <td>
		                                <ul class="navbar-icons">
		                                    <li><a href="index.html#" class="tip" title="Add new option"><i class="icon-plus"></i></a></li>
		                                    <li><a href="index.html#" class="tip" title="View statistics"><i class="icon-reorder"></i></a></li>
		                                    <li><a href="index.html#" class="tip" title="Parameters"><i class="icon-cogs"></i></a></li>
		                                </ul>
			                        </td>
                                </tr>
                                <tr>
			                        <td><a href="img/demo/big.jpg" title="" class="lightbox"><img src="img/demo/users/face3.png" alt="" /></a></td>
			                        <td><a href="index.html#" title="">Image1 description</a></td>
			                        <td>Feb 12, 2012. 12:28</td>
			                        <td class="file-info">
			                        	<span><strong>Size:</strong> 215 Kb</span>
			                        	<span><strong>Format:</strong> .jpg</span>
			                        	<span><strong>Dimensions:</strong> 120 x 120</span>
			                        </td>
			                        <td>
		                                <ul class="navbar-icons">
		                                    <li><a href="index.html#" class="tip" title="Add new option"><i class="icon-plus"></i></a></li>
		                                    <li><a href="index.html#" class="tip" title="View statistics"><i class="icon-reorder"></i></a></li>
		                                    <li><a href="index.html#" class="tip" title="Parameters"><i class="icon-cogs"></i></a></li>
		                                </ul>
			                        </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- /media datatable -->




		    </div>
		    <!-- /content wrapper -->

		</div>
		<!-- /content -->

	</div>
	<!-- /content container -->


	<jsp:include page="../inc/footer.jsp"></jsp:include>


</body>
</html>
