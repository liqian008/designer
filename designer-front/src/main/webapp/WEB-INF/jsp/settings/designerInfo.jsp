<%@page import="com.bruce.designer.front.controller.FrontController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <title>Verendus - Multipurpose Business Template</title>

        <meta name="description" content="Verendus - A HTML5 / CSS3 Multipurpose Business Template">
        <meta name="keywords" content="Bootstrap, Verendus, HTML5, CSS3, Business, Multipurpose, Template">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="/designer-front/css/bootstrap.min.css">
        <link rel="stylesheet" href="/designer-front/css/font-awesome.css">
        <link rel="stylesheet" href="/designer-front/css/animate.css">
        <link rel="stylesheet" href="/designer-front/css/flexslider.css">
        <link rel="stylesheet" href="/designer-front/css/style.css">
        
        <link rel="stylesheet"href="./uploadify/uploadify.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="/designer-front/css/ie8.css" />    
        <![endif]-->
                

        <script src="/designer-front/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="/designer-front/js/vendor/jquery-1.8.3.min.js"></script>
        <script src="./uploadify/jquery.uploadify.min.js" type="text/javascript"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(/designer-front/img/backgrounds/bg3.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        
        <jsp:include page="../inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                <jsp:include page="../inc/headerBanner.jsp"></jsp:include>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="../inc/headerNav.jsp?menuFlag=settings"></jsp:include>

                    <jsp:include page="../inc/ad.jsp"></jsp:include>

                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="/designer-front">首页</a>/</li>
                        <li><a href="/designer-front/settings">设置</a>/</li>
                        <li><a href="javascript:void(0)">设计师资料</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
							<div class="content-title">
                                <h2>个人设置</h2>
                            </div>

                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                	<jsp:include page="./settingsTabInc.jsp?settingsMenuFlag=designerInfo"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="apply4Designer">
                                        <div class="widget-box widget-wrapper-form">
                                        	<form  class="widget-form clearfix" method="post" 
												action="/designer-front/settings/designerApply">
												<div class="content-title">
													<h4>设计师资料</h4>
												</div>
												<div class="infobox info-info info-info-alt clearfix">
					                                <span>i</span>
					                                <div class="infobox-wrap">
					                                    <h4>设计师状态：</h4>
					                                    <p>
														<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														您目前还不是设计师，申请设计师需完整填写以下资料，审核通过后即可发布作品!
														<%}else if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_SENT){%>
														您的设计师申请已提交，请耐心等待审核结果!
														<%}else if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED){%>
														您的设计师申请已经审核通过，可以发布作品了!
														<%}%>
														</p>
					                                </div>
					                            </div>
					                            
					                            <div class="row-container clearfix">
													<div class="row-left">身份证号: </div>
													<div class="row-right">
													 <%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span6" name="idNum" value=""/> 
													<%}else{%>
														<%=currentUser.getDesignerIdentifer()%>
													<%} %>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">真实姓名: </div>
													<div class="row-right">
													<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span6" name="realname" value=""/> 
													<%}else{%>
														<%=currentUser.getDesignerRealname()%>
													<%} %>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">手机号: </div>
													<div class="row-right">
													<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span6" name="mobile" value=""/> 
													<%}else{%>
														<%=currentUser.getDesignerMobile()%>
													<%} %>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">公 司: </div>
													<div class="row-right">
													<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span6" name="company" value=""/> 
													<%}else{%>
														<%=currentUser.getDesignerCompany()%>
													<%} %>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">淘宝店铺主页: </div>
													<div class="row-right">
													<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
														<input type="text" class="span6" name="taobaoHomepage" value=""/> 
													<%}else{%>
														<%=currentUser.getDesignerTaobaoHomepage()%>
													<%} %>
													</div>
												</div>
												
												<%if(currentUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_NONE){%>
												<div class="content-title">
													<h4>并附带一组作品集【上限6张】</h4>
												</div>
												
												<div class="infobox info-info info-info-alt clearfix">
					                                <span>i</span>
					                                <div class="infobox-wrap">
					                                    <h4>小贴士：</h4>
					                                    <p>为达到最佳浏览效果，建议使用横竖比为4:3，且分辨率不小于800x600的图片</p>
					                                </div>
					                                <a href="#" class="info-hide"></a>
					                            </div>
					                            
					                            <div class="row-container clearfix">
													<div class="row-left">作品主题: </div>
													<div class="row-right">
														<input type="text" class="span6"  name="title" value=""/> 
													</div>
												</div>
												<div class="row-container clearfix">
													<div class="row-left">参考价格: </div>
													<div class="row-right">
														<input type="text" class="span6"  name="price" value=""/> 
													</div>
												</div>
												<div class="row-container clearfix">
													<div class="row-left">购买链接: </div>
													<div class="row-right">
														<input type="text" class="span6"  name="link" value=""/> 
													</div>
												</div>
					                            
												<div class="row-container clearfix">
													<div class="row-left">上传作品: </div>
													<div class="row-right">
														<input id="fileUploader" name="image" type="file" multiple="true">
													</div>
												</div>
												<div id="queue"></div>
												
												<div>
													<ul id="imgPreview" class="clearfix">
													</ul>
												</div>
												
												<script type="text/javascript">
													$(function() {
														var counter = 0;
														$('#fileUploader').uploadify({
															'swf' : '/designer-front//uploadify/uploadify.swf',
															//'uploader' : '/designer-front/uploadify/response.json',
															'uploader' : '/designer-front/uploadImage.json;jsessionid=<%=session.getId()%>',
															//'cancelImg' : "uploadify-cancel.png",
															'fileObjName' : 'image',
															'debug' : false,
															'buttonText' : '选择照片',
															'method' : 'post',
															'fileTypeDesc' : '图片文件',
															'fileTypeExts' : '*.*',
															'multi' : true,
															'auto' : true,
															'uploadLimit' : 6,
															'simUploadLimit' : 1,
															'fileSizeLimit' : 2048,
															'onUploadSuccess' : function(file, data, response) {
													            //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);
													            //alert(data);
													            counter = counter + 1;
													            var response = jQuery.parseJSON(data);
													            
													        	$("<li><img id='img1' src='"+response.data.mediumImage.url+"' width='200'/>设置为封面详细描述<input type='radio' name='coverId' value='"+counter+"'/><textarea class='contact-form-name' name='remark"+counter+"' rows='3'></textarea></li>").appendTo($("#imgPreview"));
													        	$("<input type='hidden' name='albumNums' value='"+counter+"'/>").appendTo($("#imgPreview"));
													        	$("<input type='hidden' name='largeImage"+counter+"' value='"+response.data.largeImage.url+"'/>").appendTo($("#imgPreview"));
													        	$("<input type='hidden' name='mediumImage"+counter+"' value='"+response.data.mediumImage.url+"'/>").appendTo($("#imgPreview"));
													        	$("<input type='hidden' name='smallImage"+counter+"' value='"+response.data.smallImage.url+"'/>").appendTo($("#imgPreview"));
															},
														});
													});
												</script>
												
												<input class="common-submit button" type="submit" value="修 改">
												<input class="common-submit button" type="button" value="返回个人信息">
												<%}%>
											</form>
										</div>
                                    </div>
                                </div>
                            </div>
                        </section> 
                        
                        <!-- right slidebar -->
						<aside class="sidebar widgets-light span3">
                       		<jsp:include page="../inc/right/sidebar_settings.jsp"></jsp:include> 
                    	</aside>
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="../inc/footer.jsp"></jsp:include>
           
        </div> <!-- Close Page -->
   </div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="/designer-front/js/vendor/bootstrap.min.js"></script>
    <script src="/designer-front/js/jquery.hoverdir.js"></script>
    <script src="/designer-front/js/superfish.js"></script>
    <!-- <script src="/designer-front/js/supersubs.js"></script> -->
   <!--  <script src="/designer-front/js/jquery.tweet.js"></script>  -->
    <script src="/designer-front/js/jquery.flexslider.js"></script> 
    <script src="/designer-front/js/retina.js"></script>
    <script src="/designer-front/js/custom.js"></script>
    
    </body>
</html>
