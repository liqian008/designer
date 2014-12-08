<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="org.apache.commons.lang3.time.DateFormatUtils"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.bruce.foundation.util.*"%>


<%
String contextPath = ConstFront.CONTEXT_PATH;
Vote vote = (Vote)request.getAttribute("vote");
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
<title><%=vote.getTitle()%> - 【金玩儿网】</title>

<meta name="description"
	content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
<meta name="keywords"
	content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="icon" href="<%=contextPath%>/img/favicon.ico">
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=contextPath%>/css/font-awesome.css">
<link rel="stylesheet" href="<%=contextPath%>/css/animate.css">
<link rel="stylesheet" href="<%=contextPath%>/css/flexslider.css">
<link rel="stylesheet" href="<%=contextPath%>/css/style.css">
<!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/css/ie8.css" />    
        <![endif]-->

	<script src="<%=contextPath%>/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
	<script src="<%=contextPath%>/js/vendor/jquery-1.8.3.min.js"></script>

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
						<li><a href="<%=contextPath%>/">首页</a>/</li>
						<li><a href="javascript:void(0)">投票活动</a></li>
					</ul>
				</div>
			</div>
			<div class="main fullwidth">
				<div class="container">
					<div class="row-fluid">

						<section class="content span12">
							<%
							if(vote!=null){
								short voteStatus = vote.getStatus()==null?0:vote.getStatus();//投票状态
								boolean voteOnline = voteStatus!=(short)0;
								boolean voteExpire = vote.getVoteEndTime()!=null&&vote.getVoteEndTime().getTime()<=System.currentTimeMillis();
								
								List<VoteOption> voteOptionList = (List<VoteOption>)request.getAttribute("voteOptionList");
								if(voteOptionList!=null&&voteOptionList.size()>0){
							%>

							<article class="format-blogpost blogpost-single clearfix">
								<div class="divider-text divider-mid">
									<span><%=vote.getTitle()%></span>
								</div>
								
								<blockquote class="normal blockquote-left blockquote-bg">
                                    <p>
                                    	<%//投票活动已关闭
                                    	if(!voteOnline){%>
	                                    	<span class="blockquote-author">
	                                    		<a href="javascript:void(0)" class="button button-small button-orange">抱歉，本次投票活动已关闭</a>
	                                    	</span>
                                    	<%}else if(voteExpire){%>
                                    		<span class="blockquote-author">
                                    			<a href="javascript:void(0)" class="button button-small button-orange">抱歉，本次投票活动已结束</a>
                                    		</span>
                                    	<%}%>
                                    	<span class="blockquote-author">
                                   			<a href="javascript:void(0)">投票时间&nbsp;</a>:&nbsp;&nbsp;<%=DateUtil.date2YMD(vote.getVoteStartTime())%>～<%=DateUtil.date2YMD(vote.getVoteEndTime())%>
                                   		</span>
                                    	<span class="blockquote-author">
                                   			<a href="javascript:void(0)">活动介绍&nbsp;</a>:&nbsp;&nbsp;<%=vote.getDescription()%>
                                   		</span>
                                   		<span class="blockquote-author">
                                   			<a href="javascript:void(0)">投票规则&nbsp;</a>:&nbsp;&nbsp;每人最多可投&nbsp;[<a href="javascript:void(0)"><%=vote.getMaxCheckLimit()%></a>]&nbsp;票
                                   		</span>
                                    </p>
                                </blockquote> 
								
								<%
								for(VoteOption option: voteOptionList){
								%>
								<img src="<%=option.getPicUrl()%>" width="100%"> 

								<div class="single-navigation navigation clearfix">
									<%if(voteOnline && !voteExpire){%>
										<a href="javascript:void(0)" class="nav-left <%=option.isVoted()?"":"voteBtn"%> " dataItem=<%=option.getId()%>><%=option.isVoted()?"您已投票":"我要投票"%></a>
									<%}%>
									<a href="javascript:void(0)" class="nav-right">已有XXX人投票</a>
								</div>
								<%}%>
								
							</article>
							<%}
							}%>
						</section>

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
	<!--<script src="<%=contextPath%>/js/retina.js"></script>-->

	<script src="<%=contextPath%>/js/custom.js"></script>

</body>

<script>
$("body").delegate('a.voteBtn', 'click', function(){
	var voteBtn = $(this);
	var voteOptionId = voteBtn.attr('dataItem');
	var voteJsonData = {"voteOptionId": voteOptionId};
	$.post("<%=contextPath%>/vote/vote.json", voteJsonData, function(data) {
		if(data.result==1){
			//投票成功
			alert("您已成功投票");
			voteBtn.removeClass("voteBtn");
			voteBtn.text("您已投票");
		}else{
			alert(data.message);
		}
	 }, "json");
});

</script>
</html>
