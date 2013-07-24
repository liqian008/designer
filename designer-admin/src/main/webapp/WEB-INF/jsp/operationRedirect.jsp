<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.designer.admin.bean.security.AdminMenu"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

String message = (String) request.getAttribute("");
String redirectUrl = (String) request.getAttribute("");
if(message==null||message.equals("")){
	message = "操作成功，现在将转入后续页面！";
}
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

	<jsp:include page="./inc/header.jsp"></jsp:include>


	<!-- Content container -->
	<div id="container">

		<jsp:include page="./inc/leftSidebar.jsp"></jsp:include>


		<!-- Content -->
		<div id="content">

		    <!-- Content wrapper -->
		    <div class="wrapper">

			    <jsp:include page="./inc/mainHeader.jsp"></jsp:include>

		    	<!-- Basic inputs -->
	            <h5 class="widget-name"><i class="icon-align-justify"></i>Basic inputs</h5>

	            <form class="form-horizontal" action="forms.html#">
					<fieldset>

						<!-- General form elements -->
						<div class="widget row-fluid">
						    <div class="well">
						    	<div class="alert margin">
						    		<button type="button" class="close" data-dismiss="alert">×</button>
						    		<p/>
						    		<p><strong><%=message%></strong></p>
                					<p>如果您的浏览器没有自动跳转，请点击 <a href='<%=redirectUrl%>'>这里</a></p>
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


	<jsp:include page="./inc/footer.jsp"></jsp:include>

	<script language='Javascript'>
	    setTimeout("location.href='<%=redirectUrl%>'", 3000);
	</script>
</body>
</html>
