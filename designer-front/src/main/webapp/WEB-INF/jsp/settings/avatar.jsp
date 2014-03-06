<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.service.oauth.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
String contextPath = ConstFront.CONTEXT_PATH;

User user = (User) session.getAttribute(ConstFront.CURRENT_USER);
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
<title>修改头像 - 【金玩儿网】</title>

<meta name="description"
	content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
<meta name="keywords"
	content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=contextPath%>/css/font-awesome.css">
<link rel="stylesheet" href="<%=contextPath%>/css/animate.css">
<link rel="stylesheet" href="<%=contextPath%>/css/flexslider.css">
<link rel="stylesheet" href="<%=contextPath%>/css/style.css">
<!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/css/ie8.css" />    
        <![endif]-->


<script
	src="<%=contextPath%>/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
<script src="<%=contextPath%>/js/vendor/jquery-1.8.3.min.js"></script>

<link rel="stylesheet" href="<%=contextPath%>/css/jcrop/jquery.Jcrop.css">
<script src="<%=contextPath%>/js/jcrop/jquery.Jcrop.js"></script>

<link href='http://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:700'
	rel='stylesheet' type='text/css'>
	
	<jsp:include page="../inc/baiduAsyncStat.jsp"></jsp:include>
</head>
<body class="body-background"
	style="background-image: url(<%=contextPath%>/img/backgrounds/bg3.jpg);">

	<!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

	<jsp:include page="../inc/topBar.jsp"></jsp:include>


	<div id="wrapper" class="boxed">
		<!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page -->

		<div class="header-background">
			<!-- Header Background -->
			<jsp:include page="../inc/headerBanner.jsp"></jsp:include>

			<div class="header-wrap">
				<!-- Header Wrapper, contains Mene and Slider -->
				<jsp:include page="../inc/headerNav.jsp?menuFlag=settings"></jsp:include>

				<jsp:include page="../inc/ad.jsp"></jsp:include>

			</div>
			<!-- Close Header Menu -->
		</div>
		<!-- Close Header Wrapper -->
		<div class="page-top-stripes"></div>
		<!-- Page Background Stripes -->

		<div class="page">
			<!-- Page -->
			<div class="breadscrumbs">
				<div class="container">
					<ul class="clearfix">
						<li><a href="<%=contextPath%>">首页</a>/</li>
						<li><a href="<%=contextPath%>/settings">设置</a>/</li>
						<li><a href="javascript:void(0)">修改头像</a></li>
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
									<jsp:include
										page="./settingsTabInc.jsp?settingsMenuFlag=avatar"></jsp:include>
								</ul>
								<div class="tab-content span9">
									<div class="tab-pane widgets-light active" id="avatar">
										<div class="widget-box widget-wrapper-form">
											<div class="content-title">
												<h4>修改头像</h4>
											</div>
											
											<div id="altContent"></div>

											<script type="text/javascript"
												src="<%=contextPath%>/faustCplus/swfobject.js"></script>
											<script type="text/javascript">
												function uploadevent(data) {
													alert('头像上传成功!');
													location.href='<%=contextPath%>/settings/avatar';
												}

												var flashvars = {
													"jsfunc" : "uploadevent",
													//"pid" : "75642723",
													"pSize": "300|300|100|100|50|50",
													"uploadSrc" : true,
													"showBrow" : true,
													"showCame" : false,
													"uploadUrl" : "<%=contextPath%>/settings/uploadAvatar.json" 
												};

												var params = {
													menu : "false",
													scale : "noScale",
													allowFullscreen : "true",
													allowScriptAccess : "always",
													wmode : "transparent",
													bgcolor : "#FFFFFF"
												};

												var attributes = {
													id : "FaustCplus"
												};

												swfobject.embedSWF(
														"<%=contextPath%>/faustCplus/avatar.swf",
														"altContent", "600",
														"440", "9.0.0",
														"expressInstall.swf",
														flashvars, params,
														attributes);
											</script>
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
				</div>
				<!-- Close Main -->
			</div>

			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- Close Page -->
	</div>
	<!-- Close wrapper -->


	<!-- Load all Javascript Files -->
	<script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
	<script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
	<script src="<%=contextPath%>/js/superfish.js"></script>
	<!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
	<!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
	<script src="<%=contextPath%>/js/jquery.flexslider.js"></script>
	<script src="<%=contextPath%>/js/retina.js"></script>

	<script src="<%=contextPath%>/js/custom.js"></script>

	<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery('#imgCrop').Jcrop({
				aspectRatio : 1,
				onChange : showCoords,
				onSelect : showCoords
			});

			jQuery('#cropButton').click(function() {
				var w = jQuery("#w").val();
				var h = jQuery("#h").val();
				if (w == 0 || h == 0) {
					alert("您还没有选择图片的剪切区域,不能进行剪切图片!");
					return;
				}
				//alert("你要剪切图片的X坐标: "+x + ",Y坐标: " + y + ",剪切图片的宽度: " + w + ",高度：" + h );  
				if (confirm("确定按照当前大小剪切图片吗")) {
					document.form1.submit();
				}
			});
		});

		function showCoords(c) {
			jQuery('#x').val(c.x);
			jQuery('#y').val(c.y);
			jQuery('#w').val(c.w);
			jQuery('#h').val(c.h);
			//显示剪切按键  
			jQuery('#cropTd').css("display", "");

		}
	</script>

</body>
</html>