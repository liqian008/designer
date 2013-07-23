<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.designer.admin.bean.security.AdminMenu"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>


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

			    

		    	<h5 class="widget-name"><i class="icon-th"></i>资源管理</h5>

                <!-- Media datatable -->
                <div class="widget">
                	<div class="navbar">
                    	<div class="navbar-inner">
                        	<h6>资源列表</h6>
                            <div class="nav pull-right">
                                <a href="index.html#" class="dropdown-toggle navbar-icon" data-toggle="dropdown"><i class="icon-cog"></i></a>
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="./menuAdd"><i class="icon-plus"></i>新增资源</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="table-overflow">
                        <table class="table table-striped table-bordered table-checks media-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>资源名</th>
                                    <th>资源Code</th>
                                    <th>资源链接</th>
                                    <th>状态</th>
                                    <th>最后登录</th>
                                    <th class="actions-column">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%
                            	List<AdminMenu> adminMenuList = (List<AdminMenu>)request.getAttribute("adminMenuList");
                            	if(adminMenuList!=null&&adminMenuList.size()>0){
                            		for(AdminMenu adminMenu: adminMenuList){
                            	%>
                            	<tr>
			                        <td>&nbsp;<%=adminMenu.getId()%>&nbsp;</td>
			                        <td><%=adminMenu.getMenuName()%></td>
			                        <td><%=adminMenu.getMenuCode()%></td>
			                        <td><%=adminMenu.getMenuUrl()%></td>
			                        <td>正常</td>
			                        <td><%=sdf.format(adminMenu.getUpdateTime())%></td>
			                        <td>
		                                <ul class="navbar-icons">
		                                    <li><a href="./menuEdit?id=<%=adminMenu.getId()%>" class="tip" title="修改"><i class="ico-edit"></i></a></li>
		                                    <li><a href="./delMenu?id=<%=adminMenu.getId()%>" class="tip" title="删除"><i class="ico-remove"></i></a></li>
		                                </ul>
			                        </td>
                                </tr>
                            	<%}
                            	}%>
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
