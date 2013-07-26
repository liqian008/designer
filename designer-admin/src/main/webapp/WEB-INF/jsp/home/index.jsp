<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../inc/include_tag.jsp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>

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

<script type="text/javascript" src="../js/plugins/charts/excanvas.min.js"></script>
<script type="text/javascript" src="../js/plugins/charts/jquery.sparkline.min.js"></script>

<script type="text/javascript" src="../js/plugins/ui/jquery.easytabs.min.js"></script>
<script type="text/javascript" src="../js/plugins/ui/jquery.collapsible.min.js"></script>
<script type="text/javascript" src="../js/plugins/ui/prettify.js"></script>
<script type="text/javascript" src="../js/plugins/ui/jquery.fancybox.js"></script>

<script type="text/javascript" src="../js/plugins/forms/jquery.uniform.min.js"></script>
<script type="text/javascript" src="../js/plugins/forms/jquery.tagsinput.min.js"></script>

<script type="text/javascript" src="../js/plugins/tables/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="../js/files/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/functions/index.js"></script>

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

			    <jsp:include page="../inc/mainHeader.jsp"></jsp:include>

		    	<!-- Basic inputs -->
	            <h5 class="widget-name"><i class="icon-align-justify"></i>欢迎页</h5>

	            <form class="form-horizontal" action="forms.html#">
					<fieldset>
						<!-- General form elements -->
						<div class="widget row-fluid">
						    <div class="well">
						    	<div class="alert margin">
						    		<button type="button" class="close" data-dismiss="alert">×</button>
						    		<p/>
						    		<p><strong>您好, <sec:authentication property="name" />, 欢迎登录后台管理系统！</strong></p>
                					<p>您的角色是：</p>
						    	</div>
						    </div>
						</div>
						<!-- /general form elements -->
					</fieldset> 
				</form>
				<!-- /basic inputs -->
		    </div>
		    <!-- /content wrapper -->
		</div>
		<!-- /content -->
	</div>
	<!-- /content container -->
	
	<jsp:include page="../inc/footer.jsp"></jsp:include>


</body>
</html>
