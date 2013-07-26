<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.designer.admin.bean.security.AdminResource"%>

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

		    	<h5 class="widget-name"><i class="icon-th"></i>资源管理</h5>

                <!-- Media datatable -->
                <div class="widget">
                	<div class="navbar">
                    	<div class="navbar-inner">
                        	<h6>资源列表</h6>
                            <a href="./resourceAdd" class="btn btn-primary pull-right">新增资源</a>
                        </div>
                    </div>
                    <div class="table-overflow">
                        <table class="table table-striped table-bordered table-checks media-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>资源名</th>
                                    <th>父ID</th>
                                    <th>资源链接</th>
                                    <th>排序</th>
                                    <th>状态</th>
                                    <th>最后登录</th>
                                    <th class="actions-column">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%
                            	List<AdminResource> adminResourceList = (List<AdminResource>)request.getAttribute("adminResourceList");
                            	if(adminResourceList!=null&&adminResourceList.size()>0){
                            		for(AdminResource adminResource: adminResourceList){
                            	%>
                            	<tr>
			                        <td><%=adminResource.getId()%></td>
			                        <td><%=adminResource.getResourceName()%></td>
			                        <td><%=adminResource.getParentId()%></td>
			                        <td><%=adminResource.getUrl()%></td>
			                        <td><%=adminResource.getSort()%></td>
			                        <td>正常</td>
			                        <td><%=sdf.format(adminResource.getUpdateTime())%></td>
			                        <td>
		                                <ul class="navbar-icons">
		                                    <li><a href="./resourceEdit?id=<%=adminResource.getId()%>" class="tip" title="修改"><i class="ico-edit"></i></a></li>
		                                    <li><a href="./delResource?id=<%=adminResource.getId()%>" class="tip" title="删除"><i class="ico-remove"></i></a></li>
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
