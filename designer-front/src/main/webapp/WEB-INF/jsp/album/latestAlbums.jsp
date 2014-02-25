<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
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
<title>新晋专辑作品 - 【金玩儿网】</title>

<meta name="description"
	content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
<meta name="keywords"
	content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="/designer-front/css/bootstrap.min.css">
<link rel="stylesheet" href="/designer-front/css/font-awesome.css">
<link rel="stylesheet" href="/designer-front/css/animate.css">
<link rel="stylesheet" href="/designer-front/css/style.css">
<!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="/designer-front/css/ie8.css" />    
        <![endif]-->


<script src="/designer-front/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
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
				<jsp:include page="../inc/headerNav.jsp?menuFlag=albums"></jsp:include>

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
                        <li><a href="javascript:void(0)">新晋专辑作品</a></li>
                    </ul>
                </div>
            </div>

			<div class="main fullwidth">
				<section class="content">
					<!-- Content -->
					<div class="container" id="albumContainer">
						


						<!--  <div class="blog-navigation">
                            <ul class="clearfix">
                                <li><a class="button button-white" href="#">1</a></li>
                                <li><a class="button button-white" href="#">2</a></li>
                                <li><a class="button button-white" href="#">3</a></li>
                                <li><a class="button button-white" href="#">4</a></li>
                            </ul>
                        </div> -->
						<!-- <div class="shortcode-blogpost row-fluid">
                    		<div class="span4 offset4">
                    			<input id="currentPageNo" type="hidden" value="1"/>
                    			<input id="pageSize" type="hidden" value="4"/>
                    			<input class="button-small button button-white btn-block" id="moreAlbums" type="button" value="查看更多"/>
                    		</div>
                    	</div> -->
					</div>
					<input type="hidden" id="albumsTailId" name="albumsTailId" value="0" />
					<div class="shortcode-blogpost row-fluid" id="moreAlbumsContainer">
						<div class="span2 offset5">
							<input id="moreAlbumsBtn"
								class="button-small button button-white btn-block" type="button"
								value="加载更多..." />
						</div>
					</div>
				</section>
				<!-- Close Content -->
			</div>
			<!-- Close Main -->

			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- Close Page -->
	</div>
	<!-- Close wrapper -->


	<!-- Load all Javascript Files -->
	<script src="/designer-front/js/vendor/bootstrap.min.js"></script>
	<script src="/designer-front/js/superfish.js"></script>
	<!-- <script src="/designer-front/js/supersubs.js"></script> -->
	<script src="/designer-front/js/retina.js"></script>
	<script src="/designer-front/js/custom.js"></script>
	<script>
		fallLoad();
	
    	$('#moreAlbumsBtn').click(function(){
    		fallLoad();
    	});
    	
    	function fallLoad(){
    		//置为数据加载状态
    		$('#moreAlbumsBtn').val("努力加载中...");
    		$('#moreAlbumsBtn').attr("disabled","disabled");
    		var jsonData = {'albumsTailId' : $("#albumsTailId").val(), 'numberPerLine':'4'};
    		$.post('/designer-front/moreAlbums.json', jsonData, function(data) {
    			var result = data.result;
   				if(result==1){
   					$("#albumContainer").append(data.data.html);
	   				var nextTailId = data.data.tailId;
	    			$("#albumsTailId").val(nextTailId);
	    			if(nextTailId<=0){//无更多数据，则隐藏按钮
	   					$('#moreAlbumsContainer').attr("style","display:none");
	   				}else{//还有更多数据，启用加载按钮
	   					$('#moreAlbumsBtn').removeAttr("disabled");
	   					$('#moreAlbumsBtn').val("加载更多...");
	   				}
   				}
   			})
   			/* .success(function() { alert("second success"); })
   		    .error(function() { alert("error"); })
   		    .complete(function() { alert("complete"); }); */
    	}
    </script>
</body>
</html>