<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="com.bruce.designer.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%!String getActive(HttpServletRequest request, int mode){
	if(mode == ((Integer)request.getAttribute("mode")).intValue()){
		return "class='active'";
	}else{
		return "";
	}
}%>

<%
String contextPath = ConstFront.CONTEXT_PATH;
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
<title>热门设计师 - 【金玩儿网】</title>

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

<link href='http://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:700'
	rel='stylesheet' type='text/css'>
	
	<jsp:include page="../inc/baiduAsyncStat.jsp"></jsp:include>
</head>
<body class="body-background"
	style="background-image: url(<%=contextPath%>/img/backgrounds/bg.jpg);">

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
				<jsp:include page="../inc/headerNav.jsp?menuFlag=designers"></jsp:include>
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
						<li><a href="<%=contextPath%>/">首页</a>/</li>
						<li><a href="javascript:void(0)">热门设计师</a></li>
					</ul>
				</div>
			</div>
			<div class="main fullwidth">
				<div class="container">
					<div class="row-fluid">
						<section class="content span9">
							<div class="shortcode-tabs">
	                            <ul class="tabs-nav tabs clearfix">
	                                <li <%=getActive(request, 1)%>><a class="button button-white" href="<%=contextPath%>/hot/dailyDesigners">本日热门设计师</a></li>
	                                <li <%=getActive(request, 2)%>><a class="button button-white" href="<%=contextPath%>/hot/weeklyDesigners">本周热门设计师</a></li>
	                                <li <%=getActive(request, 3)%>><a class="button button-white" href="<%=contextPath%>/hot/monthlyDesigners">本月热门设计师</a></li>
	                            </ul>
                            </div>
						
							
							
							<div id="designer-Container" class="designer-container">
								<% 
								List<User> designerList = (List<User>)request.getAttribute("designerList"); 
								if(designerList!=null&&designerList.size()>0){
									Map<Integer, Boolean> followMap = (Map<Integer, Boolean>)request.getAttribute("followMap"); 
								%>
								<div class="widget-box widget-designer">
									<div class="designer-wrap clearfix">
										<%for(User designer: designerList){
										%>
										<div class="designer_badge_image" id="designer_badge_image<%=designer.getId()%>">
											<a href="<%=contextPath%>/<%=designer.getId()%>/home">
												<img src="<%=UploadUtil.getAvatarUrl(designer.getId(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM)%>" width="100%" title="<%=designer.getNickname()%>">
											</a>
											<%
											if(currentUser!=null&&designer.getId().equals(currentUser.getId())){
											%>
											<a href="<%=contextPath%>/<%=designer.getId()%>/home" class="button button-small button-blue">我</a>
											<%
											}else{	
												boolean hasFollowed = followMap.get(designer.getId())!=null&&followMap.get(designer.getId());
												String hideStr = "style='display:none'";
												%>
												<a href="javascript:void(0)" dataItem="<%=designer.getId()%>" class="listFollowBtn button button-small button-green" <%=hasFollowed?hideStr:""%>>
													关 注
												</a>
												<a href="javascript:void(0)" dataItem="<%=designer.getId()%>" class="listUnfollowBtn button button-small button-white" <%=hasFollowed?"":hideStr%>">
													已关注
												</a>
											<%}%>
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
								<%}%>
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
	<script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
	<script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
	<script src="<%=contextPath%>/js/superfish.js"></script>
	<!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
	<!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
	<script src="<%=contextPath%>/js/retina.js"></script>
	<script src="<%=contextPath%>/js/custom.js"></script>
	<script>
	$("body").delegate('a.listFollowBtn', 'click', function(){
		var listFollowBtn = $(this);
		var followId = listFollowBtn.attr('dataItem');
		listFollowBtn.attr("disabled", "disabled");
		var followJsonData = {"uid": followId};
		$.post("<%=contextPath%>/follow.json", followJsonData, function(data) {
			listFollowBtn.removeAttr("disabled");
			if(data.result==1){
				listFollowBtn.next().show();
				listFollowBtn.hide();
			}else{
				alert(data.message);
			}
		 }, "json");
	});
	
	
	$("body").delegate('a.listUnfollowBtn', 'click', function(){
		var listUnfollowBtn = $(this);
		var unfollowId = listUnfollowBtn.attr('dataItem');
		listUnfollowBtn.attr("disabled", "disabled");
		var unfollowJsonData = {"uid": unfollowId};
		$.post("<%=contextPath%>/unfollow.json", unfollowJsonData, function(data) {
			listUnfollowBtn.removeAttr("disabled");
			if(data.result==1){
				listUnfollowBtn.prev().show();
				listUnfollowBtn.hide();
			}else{
				alert(data.message);
			}
		 }, "json");
	});
	</script>
</body>
</html>
