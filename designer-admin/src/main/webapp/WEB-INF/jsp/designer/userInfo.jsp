<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.designer.model.User"%>

<%@ include file="../inc/include_tag.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Pannonia - premium responsive admin template by Eugene Kopyov</title>
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<!--[if IE 8]><link href="css/ie8.css" rel="stylesheet" type="text/css" /><![endif]-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>

<script type="text/javascript"src="../js/plugins/charts/jquery.sparkline.min.js"></script> 

<script type="text/javascript"src="../js/plugins/ui/jquery.easytabs.min.js"></script>
<script type="text/javascript"src="../js/plugins/ui/jquery.collapsible.min.js"></script>
<script type="text/javascript"src="../js/plugins/ui/jquery.mousewheel.js"></script>

<script type="text/javascript"src="../js/plugins/forms/jquery.uniform.min.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.autosize.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.inputlimiter.min.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.tagsinput.min.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.inputmask.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.select2.min.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.listbox.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.validation.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.validationEngine.zh-CN.js"></script>

<script type="text/javascript"src="../js/globalize/globalize.js"></script>
<script type="text/javascript"src="../js/globalize/globalize.culture.de-DE.js"></script>
<script type="text/javascript"src="../js/globalize/globalize.culture.ja-JP.js"></script>

<script type="text/javascript"src="../js/files/bootstrap.min.js"></script>

<script type="text/javascript"src="../js/functions/forms.js"></script>

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

	            <!-- Form validation -->
	            <h5 class="widget-name"><i class="icon-th-list"></i>用户管理</h5>
				
				<form id="validate" action="<s:url value='./saveUser'/>" method="post"  class="form-horizontal">
	                <fieldset>
	                
	                	<div class="row-fluid">
	                    	<!-- Column -->
	                        <div class="span6">
			                    <!-- Form validation -->
			                    <div class="widget">
			                        <div class="navbar"><div class="navbar-inner"><h6>用户资料</h6></div></div>
			                    	<div class="well row-fluid">
										
										<div class="control-group">
	                                        <label class="control-label">头 像: </label>
	                                        <div class="controls">
												<a href='http://localhost:8080/designer-admin/img/demo/sidebar_user_big.png' target="_blank">
													<img src='http://localhost:8080/designer-admin/img/demo/sidebar_user_big.png' width="60px"/>
												</a>
	                                        </div>
	                                    </div>
										
			                            <div class="control-group">
			                                <label class="control-label">登录名: <span class="text-error">*</span></label>
			                                <div class="controls">
			                                    <input type="text" class="validate[required] span8" name="username" id="username" value="${userInfo.username}" readonly="readonly"/>
			                                </div>
			                            </div>
			                            
			                            <div class="control-group">
			                                <label class="control-label">昵 称: <span class="text-error">*</span></label>
			                                <div class="controls">
			                                    <input type="text" class="validate[required] span8" name="nickname" id="nickname" value="${userInfo.nickname}" readonly="readonly"/>
			                                </div>
			                            </div>
			                            
			                            <!-- <div class="control-group">
			                                <label class="control-label">密 码: </label>
			                                <div class="controls">
			                                    <input type="text" class="span8" name="password" id="password" value="" readonly="readonly"/>
			                                </div>
			                            </div> -->
			                            
			                            <div class="form-actions align-left">
			                                <button type="submit" class="btn btn-info">修 改</button>
			                                <button type="reset" class="btn">重 置</button>
			                                <button type="button" class="btn btn-danger">冻结用户</button>
			                            </div>
			                        </div>
			                    </div>
			                    <!-- /Form validation -->
							</div>
							<!-- /Column -->
	                        
							
							<!-- Column -->
	                        <div class="span6">
		                    	<!-- Form validation -->
				                <div class="widget">
									<div class="navbar"><div class="navbar-inner"><h6>设计师资料</h6></div></div>
			                    	<div class="well row-fluid">
			                    		
			                    		<%
			                    		short designerStatus = 1;
			                    		if(designerStatus==1||designerStatus==2){
	                            			if(designerStatus==2){
		                            		%>
				                            <div class="alert margin">
		                            			设计师申请已通过，详细资料如下：
		                            		</div>
		                            		<%}else if(designerStatus==1){
		                            		%>
				                            <div class="alert margin">
	                            				设计师申请已提交，需进行审核，申请资料如下:
		                            		</div>
			                            	<%}%>
	                            		
			                            <div class="control-group">
			                                <label class="control-label">设计师姓名: <span class="text-error">*</span></label>
			                                <div class="controls">
			                                    <input type="text" class="validate[required] span8" name="realname" id="realname" value="${userInfo.designerRealname}" readonly="readonly"/>
			                                </div>
			                            </div>
			                            
			                            <div class="control-group">
			                                <label class="control-label">身份证: <span class="text-error">*</span></label>
			                                <div class="controls">
			                                    <input type="text" class="validate[required] span8" name="identifer" id="identifer" value="${userInfo.designerIdentifer}" readonly="readonly"/>
			                                </div>
			                            </div>
			                            
			                            <div class="control-group">
			                                <label class="control-label">手机号: <span class="text-error">*</span></label>
			                                <div class="controls">
			                                    <input type="text" class="validate[required] span8" name="mobile" id="mobile" value="${userInfo.designerMobile}" readonly="readonly"/>
			                                </div>
			                            </div>
			                            
			                            <div class="control-group">
			                                <label class="control-label">公 司: <span class="text-error">*</span></label>
			                                <div class="controls">
			                                    <input type="text" class="validate[required] span8" name="company" id="company" value="${userInfo.designerCompany}" readonly="readonly"/>
			                                </div>
			                            </div>
			                            
			                            <div class="control-group">
			                                <label class="control-label">主 页: <span class="text-error">*</span></label>
			                                <div class="controls">
			                                    <input type="text" class="validate[required] span8" name="homepage" id="homepage" value="${userInfo.designerTaobaoHomepage}" readonly="readonly"/>
			                                </div>
			                            </div>
			                            
	                                    
	                                    <%if(designerStatus==1) {%>
		                            	<!-- <div class="control-group">
	                                        <label class="control-label">设计师审核: </label>
	                                        <div class="controls">
												<label class="radio">
													<input type="radio" name="designerStatus" class="styled" value="option10" checked="checked">
													通过审核
												</label>
												<label class="radio">
													<input type="radio" name="designerStatus" class="styled" value="option11">
													拒绝审核
												</label>
	                                        </div>
	                                    </div>
	                                     -->
	                                    <div class="navbar"><div class="navbar-inner"><h6>设计师申请专辑内容</h6></div></div>
					                    	<div class="well row-fluid">
			                                    
				                            	<div class="control-group">
			                                        <label class="control-label">专辑图片: </label>
			                                        <div class="controls">
														<a href='http://localhost:8080/designer-admin/img/demo/sidebar_user_big.png' target="_blank">
															<img src='http://localhost:8080/designer-admin/img/demo/sidebar_user_big.png' width="120px"/>
														</a>
														<a href='http://localhost:8080/designer-admin/img/demo/sidebar_user_big.png' target="_blank">
															<img src='http://localhost:8080/designer-admin/img/demo/sidebar_user_big.png' width="120px"/>
														</a>
														<a href='http://localhost:8080/designer-admin/img/demo/sidebar_user_big.png' target="_blank">
															<img src='http://localhost:8080/designer-admin/img/demo/sidebar_user_big.png' width="120px"/>
														</a>
			                                        </div>
			                                    </div>
				                                    
					                            <div class="control-group">
					                                <label class="control-label">标 题: <span class="text-error">*</span></label>
					                                <div class="controls">
					                                    <input type="text" class="span8" name="title" id="title" value="${albumInfo.title}" readonly="readonly"/>
					                                </div>
					                            </div>
					                            
					                            <div class="control-group">
					                                <label class="control-label">价 格: <span class="text-error">*</span></label>
					                                <div class="controls">
					                                	<input type="text" class="span3" name="price" id="price" value="${albumInfo.price}" readonly="readonly"/>&nbsp;元
					                                </div>
					                            </div>
					                            
					                            <div class="control-group">
					                                <label class="control-label">链 接: <span class="text-error">*</span></label>
					                                <div class="controls">
					                                	<input type="text" class="span8" name="link" id="link" value="${albumInfo.link}" readonly="readonly"/>
					                                	&nbsp;<a href='www.baidu.com' target="_blank">点击查看</a>
					                                </div>
					                            </div>
					                            
					                            <div class="control-group">
					                                <label class="control-label">描 述: <span class="text-error">*</span></label>
					                                <div class="controls">
					                                	<input type="text" class="span8" name="remark" id="remark" value="${albumInfo.remark}" readonly="readonly"/>
					                                </div>
					                            </div>
					                            
					                        </div>
	                                    
	                                    
	                                    <%}%>
			                            
			                            <div class="form-actions align-left">
			                                <button type="submit" class="btn btn-info">修 改</button>
			                                <button type="reset" class="btn">重 置</button>
			                                <button type="button" class="btn btn-danger">冻结设计师</button>
			                            </div>
			                            <%}else if(designerStatus==3){ %>
			                    			<div class="alert margin"> 
	                            			设计师申请已拒绝
	                            			</div>
	                            		<%}else{ %>
			                    			<div class="alert margin">
	                            			非设计师身份
	                            			</div>
	                            		<%}%>
			                            
			                        </div>
								</div>
								<!-- /Form validation -->
                           	</div>
							<!-- /Column -->
	                    </div>
	                    <!-- /form validation -->

	                </fieldset>
				</form>
				<!-- /form validation -->
				
		    </div>
		    <!-- /content wrapper -->

		</div>
		<!-- /content -->

	</div>
	<!-- /content container -->

	<jsp:include page="../inc/footer.jsp"></jsp:include>

</body>
</html>