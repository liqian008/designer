<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
	SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
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
<title>Verendus - Multipurpose Business Template</title>

<meta name="description"
	content="Verendus - A HTML5 / CSS3 Multipurpose Business Template">
<meta name="keywords"
	content="Bootstrap, Verendus, HTML5, CSS3, Business, Multipurpose, Template">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/designer-front/css/bootstrap.min.css">
<link rel="stylesheet" href="/designer-front/css/font-awesome.css">
<link rel="stylesheet" href="/designer-front/css/animate.css">
<link rel="stylesheet" href="/designer-front/css/flexslider.css">
<link rel="stylesheet" href="/designer-front/css/style.css">
<!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="/designer-front/css/ie8.css" />    
        <![endif]-->


<script
	src="/designer-front/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
<script src="/designer-front/js/vendor/jquery-1.8.3.min.js"></script>

<link href='http://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:700'
	rel='stylesheet' type='text/css'>
</head>
<body class="body-background"
	style="background-image: url(/designer-front/img/backgrounds/bg3.jpg);">

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
				<jsp:include page="../inc/headerNav.jsp"></jsp:include>
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
						<li><a href="./">首页</a>/</li>
						<li><a href="javascript:void(0)">热门设计师</a></li>
					</ul>
				</div>
			</div>
			<div class="main fullwidth">
				<div class="container">
					<div class="row-fluid">
						<section class="content span9">
							<%
								List<User> designerList = (List<User>)request.getAttribute("designerList"); 
								if(designerList!=null&&designerList.size()>0){
							%>

							<div id="designer-Container" class="designer-container">
								<div class="widget-box widget-designer">
									<div class="designer-wrap clearfix">
										<%for(User designer: designerList){%>
										<div class="designer_badge_image" id="designer_badge_image<%=designer.getId()%>">
											<a href="/designer-front/<%=designer.getId()%>/home"><img
												src="<%=designer.getHeadImg()%>"
												height="100%" width="100%"></a>
										</div>
										<%} %>
									</div>
								</div>
								<%
									}else{
								%>
								<div id="infoboxContainer"
									class="infobox info-warning info-warning-alt clearfix"
									style="display: none">
									<span>!</span>
									<div class="infobox-wrap">
										<h4>提示</h4>
										<p id="infoboxMessage">暂无数据!</p>
									</div>
								</div>
								<%
									}
								%>
							</div>
						</section>
						
						<!-- right slidebar -->
						<aside class="sidebar widgets-light span3">
                       		<jsp:include page="../inc/right/sidebar.jsp"></jsp:include> 
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
	<script src="/designer-front/js/vendor/bootstrap.min.js"></script>
	<script src="/designer-front/js/jquery.hoverdir.js"></script>
	<script src="/designer-front/js/superfish.js"></script>
	<!-- <script src="/designer-front/js/supersubs.js"></script> -->
	<!--  <script src="/designer-front/js/jquery.tweet.js"></script>  -->
	<script src="/designer-front/js/retina.js"></script>
	<script src="/designer-front/js/custom.js"></script>
</body>
</html>
