<%@page import="com.bruce.designer.front.controller.FrontController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
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

        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/font-awesome.css">
        <link rel="stylesheet" href="./css/animate.css">
        <link rel="stylesheet" href="./css/flexslider.css">
        <link rel="stylesheet" href="./css/style.css">
        
        <link rel="stylesheet"href="./uploadify/uploadify.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="./css/ie8.css" />    
        <![endif]-->
                

        <script src="./js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="./js/vendor/jquery-1.8.3.min.js"></script>
        <script src="./uploadify/jquery.uploadify.min.js" type="text/javascript"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(./img/backgrounds/bg3.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        
        <jsp:include page="../inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                <jsp:include page="../inc/headerBanner.jsp"></jsp:include>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="../inc/headerNav.jsp"></jsp:include>

                    <div class="page-title">
                        <div class="container">
                            <!-- <div class="page-title-avatar">
                                <img src="./img/demo/portraits/portrait-21.png" alt="Page Title" width="80" height="80"/>
                            </div> -->
                            <div class="page-title-content">
                                <h1>Gallery Post Format</h1>
                                <p class="page-description">
                                    With this gallery you can create a blogpost with multiple images. With the FlexSlider or Twitter Bootstrap Carousel you can rotate between these images.
                                </p>
                            </div>
                        </div>
                    </div>
                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="post-gallery.html#">Home</a>/</li>
                        <li><a href="post-gallery.html#">Blog</a>/</li>
                        <li><a href="post-gallery.html#">Gallery Post Format</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
							<div class="content-title">
                                <h2>管理中心</h2>
                            </div>

                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                	<jsp:include page="./settingsTabInc.jsp"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="apply4Designer">
                                        <div class="widget-box widget-contact-form">
                                        	<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/designerApply.art">
												<div class="content-title">
													<h4>请填写设计师申请资料（您的申请已提交，请耐心等待审核结果）</h4>
												</div>
												
												<div class="input-container">
													身份证号: <input type="text" class="contact-form-name" name="idNum"
														value="身份证号"/>
												</div>
												<div class="input-container">
													真实姓名: <input type="text" class="contact-form-name" name="realname"
														value="真实姓名"/>
												</div>
												<div class="input-container">
													手机号: <input type="text" class="contact-form-name" name="mobile"
														value="手机号"/>
												</div>
												
												<div class="input-container">
													公 司: <input type="text" class="contact-form-name" name="company"
														value="公 司"/>
												</div>
												<div class="input-container">
													淘宝店铺主页: <input type="text" class="contact-form-name" name="taobaoHomepage"
														value="淘宝店铺店铺"/>
												</div>
												
												
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
												<div class="input-container">
													<input id="fileUploader" name="image" type="file" multiple="true">
												</div>
												<div id="queue"></div>
												
												<div>
													<ul id="imgPreview" class="clearfix">
														<!-- <li>
															<img src="/designer-front/img/demo/portraits/avatar_middle.jpg">
															设置为封面
															<input type="radio" name="setCover" value=""/>标题<input type="text" class="contact-form-name" name="taobaoHomepage"/>详细描述<textarea class="contact-form-name" name="remark" rows="3"></textarea>
														</li> -->
													</ul>
												</div>
												
												<script type="text/javascript">
													$(function() {
														var counter = 0;
														$('#fileUploader').uploadify({
															'swf' : '/designer-front//uploadify/uploadify.swf',
															//'uploader' : '/designer-front/uploadify/response.json',
															'uploader' : '/designer-front/ajax/uploadImage.art;jsessionid=<%=session.getId()%>',
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
													        	$("<li><img id='img1' src='"+response.data.mediumImage.url+"' width='200'/>设置为封面<input type='radio' name='setCover' value='"+counter+"'/>标题<input type='text' class='contact-form-name' name='title"+counter+"'/>详细描述<textarea class='contact-form-name' name='remark"+counter+"' rows='3'></textarea></li>").appendTo($("#imgPreview"));
															},
														});
													});
												</script>
												
												<input class="contact-submit button" type="submit" value="提 交" disabled="disabled">
												<input class="contact-submit button" type="button" value="返回个人信息">
											</form>
										</div>
                                    </div>
                                </div>
                            </div>
                        </section> 
                        
                        <jsp:include page="../inc/rightSidebar.jsp"></jsp:include>
                    	
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="../inc/footer.jsp"></jsp:include>
           
        </div> <!-- Close Page -->
   </div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="./js/vendor/bootstrap.min.js"></script>
    <script src="./js/jquery.hoverdir.js"></script>
    <script src="./js/superfish.js"></script>
    <!-- <script src="./js/supersubs.js"></script> -->
   <!--  <script src="./js/jquery.tweet.js"></script>  -->
    <script src="./js/jquery.flexslider.js"></script> 
    <script src="./js/retina.js"></script>
    <script src="./js/custom.js"></script>
    
    </body>
</html>
