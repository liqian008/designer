<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.designer.model.Album"%>
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

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>

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
				
				<h5 class="widget-name"><i class="icon-th-list"></i>专辑管理</h5>
				<form class="form-horizontal" action="/designer-admin/designer/users" method="post">
	                <fieldset>
	                	<!-- Form components -->
	                    <div class="row-fluid">
	                        <!-- Column -->
	                        <div class="span6 offset3">
	                            <!-- Form related buttons -->
	                            <div class="widget">
	                            	<div class="well">
	                            		<div class="control-group">
			                                <label class="control-label">专辑状态: <span class="text-error">*</span></label>
			                                <div class="controls">
												<!-- <label class="radio inline">
													<input type="radio" id="d" class="styled" value="option1">
													Unchecked
												</label> -->
												
												<select data-placeholder="Choose a Country..." id="clear-results">
	                                                <option value="-1">所有</option>
	                                                <option value="1">未审核</option>
	                                                <option value="2">已通过审核</option>
	                                                <option value="3">已封禁</option>
	                                            </select>
			                                </div>
			                            </div>
	                            	
	                                    <div class="form-actions">
	                                        <button type="submit" class="btn btn-primary">查 询</button>
	                                        <button type="reset" class="btn">重 置</button>
	                                    </div>
	                                </div>
	                            </div>
	                            <!-- /form related buttons -->
	                        </div>
	                        <!-- /column -->                     
	                    </div>
	                    <!-- /form components -->

	                </fieldset>
				</form>
				
                <!-- Media datatable -->
                <div class="widget">
                	<div class="navbar">
                    	<div class="navbar-inner">
                        	<h6>专辑列表</h6>
                        	<!-- <a href="./userAdd" class="btn btn-primary pull-right">新增专辑</a> -->
                        </div>
                    </div>
                    <div class="table-overflow">
                        <table class="table table-striped table-bordered table-checks media-table">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>封面</th>
                                    <th>专辑名</th>
                                    <th>价格</th>
                                    <th>购买链接</th>
                                    <th>状态</th>
                                    <th class="actions-column">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<%
                            	List<Album> albumList = (List<Album>)request.getAttribute("albumList");
                            	if(albumList!=null&&albumList.size()>0){
                            		int i=0;
                            		for(Album album: albumList){
                            			i++;
                            	%>
                            	<tr>
			                        <td><%=i%></td>
			                        <td>
			                        	<img src='http://localhost:8080/designer-admin/img/demo/sidebar_user_big.png' width="60px"></img>
			                        </td>
			                        <td><%=album.getTitle()%></td>
			                        <td><%=album.getPrice()%>元</td>
			                        <td><a href='<%=album.getLink()%>'>点击查看</a></td>
			                        <td>正常</td>
			                        <td>
		                                <ul class="navbar-icons">
		                                    <li><a href="./albumInfo?albumId=<%=album.getId()%>" class="tip" title="屏蔽"><i class="ico-edit"></i></a></li>
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
