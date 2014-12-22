<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="org.apache.commons.lang3.time.DateFormatUtils"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.bruce.foundation.util.*"%>


<%
DecimalFormat df =new DecimalFormat("0.00");

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
<link rel="stylesheet" href="<%=contextPath%>/css/bootstrap.min.css?v=${version}">
<link rel="stylesheet" href="<%=contextPath%>/css/font-awesome.css?v=${version}">
<link rel="stylesheet" href="<%=contextPath%>/css/animate.css?v=${version}">
<link rel="stylesheet" href="<%=contextPath%>/css/flexslider.css?v=${version}">
<link rel="stylesheet" href="<%=contextPath%>/css/style.css?v=${version}">
<style>
.btn{
	margin: 0px 6px 6px 0px;
	padding: 8px 16px; 
	text-decoration: none;
	text-align: center;
	display:inline-block;
}
.btn-block{
	color: #fff !important;
	padding: 12px 20px;
	margin:0px 5px 10px; 
	text-decoration: none;
	display:block;
	text-align: center; 
	font-size:14px;
}
.btn-green{ background-color: #08ba05;color: #fff;border-radius: 5px;}
.btn-orange{ background-color: orange;color: #fff;border-radius: 5px;} 
                        </style>
<!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/css/ie8.css?v=${version}" />    
        <![endif]-->

	<script src="<%=contextPath%>/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js?v=${version}"></script>
	<script src="<%=contextPath%>/js/vendor/jquery-1.8.3.min.js?v=${version}"></script>

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

			<jsp:include page="../inc/headerBanner.jsp?displayLogin=0"></jsp:include>

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

						<section class="content span9">
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
								
								<div style="margin:10px 0px">
									<a href="<%=vote.getAdLink1()%>" target="_blank"><img src="<%=vote.getAdPic1Url()%>" width="100%"></a>
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
								int i=0;
								for(VoteOption option: voteOptionList){
									i++;
								%>
								<div class="row-fluid clearfix" style="border-bottom: 1px solid #ececec;margin-bottom: 15px;">
									<img id="opt<%=i%>" src="<%=option.getThumbPicUrl()%>" width="100%"> 
									<blockquote class="normal blockquote-left blockquote-bg">
	                                    <p><%=option.getSort()%>、<%=option.getTitle()%> - <%=option.getDescription()%></p>
	                                </blockquote>
	                                
	                                <div class="single-navigation navigation clearfix">
										<link rel="stylesheet" href="<%=contextPath%>/css/progress.css?v=${version}"> 
										<div class="progress"> 
										<span class="green" style="width: <%=df.format(option.getPercent())%>%"><span><%=df.format(option.getPercent())%>%</span></span>
										</div> 
									</div>
									
	                                <div>
	                                	<%if(voteOnline && !voteExpire){%>
		                                <a href="javascript:void(0)" class="btn-block btn-green <%=option.isVoted()?"":"voteBtn"%>" dataItem=<%=option.getId()%>><%=option.isVoted()?"您已投票":"给Ta投票"%>(已有&nbsp;<%=option.getVoteNum()%>&nbsp;人投票)</a>
										<%}%>
										<a href="javascript:void(0)" class="btn-block btn-orange wxShareBtn">找好友拉票</a> 
	                                </div>
	                                
	                                <!-- 
									<div class="single-navigation navigation clearfix">
										<%if(voteOnline && !voteExpire){%>
											<a href="javascript:void(0)" class="nav-left <%=option.isVoted()?"":"voteBtn"%> " dataItem=<%=option.getId()%>><%=option.isVoted()?"您已投票":"给Ta投票"%>(已有&nbsp;<%=option.getVoteNum()%>&nbsp;人投票)</a>
										<%}%>
										<a href="javascript:void(0)" class="nav-right wxShareBtn">找好友拉票</a>
									</div>
									-->
									
									 
								</div>
								<%}%>
								
							</article>
							<%}
							}%>
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
	
	
	<!-- 微信分享 -->
    <div id="shareModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="cautionModalLabel" aria-hidden="true">
        <div class="modal-header">
            <h4>拉票方法</h4>
        </div>
      <div class="modal-body">
        <p>点击屏幕右上角的【分享到朋友圈】或【发送给好友】，让好友给你投票哦！</p>
      </div>
      <div class="modal-footer">
        <button class="button" data-dismiss="modal" aria-hidden="true">我知道了</button>
      </div>
    </div>
    
    <!-- 投票提示 -->
    <div id="voteModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="cautionModalLabel" aria-hidden="true">
        <div class="modal-header">
            <h4>投票提示</h4>
        </div>
      <div class="modal-body">
        <p id="voteAvailableContainer">您已成功投票，还有<span id="leftVoteTimes">0</span>次投票机会，请认真把握哦！<br/>
        	接下来，您可以：
        </p>
       	<p id="voteUnavailableContainer">您已成功完成<%=vote.getMaxCheckLimit()%>次投票。<br/>
       	接下来，您可以：
       	</p>
       	<p id="voteErrorContainer"><span id="voteErrorText">投票失败</span>！您可以：
       	</p>
      </div>
      <div class="modal-footer">
        <button id="continueBtn" class="button" id="stayVoteBtn"  data-dismiss="modal" aria-hidden="true">【继续浏览】</button>
        <button id="voteAbortBtn" class="button button-blue">【鉴赏精选原创作品】</button>
        <button id="followJinwanrBtn" class="button button-green">【鉴赏精选原创作品】</button>
      </div>
    </div>
    

	<!-- Load all Javascript Files -->
	<script src="<%=contextPath%>/js/vendor/bootstrap.min.js?v=${version}"></script>
	<script src="<%=contextPath%>/js/jquery.hoverdir.js?v=${version}"></script>
	<script src="<%=contextPath%>/js/superfish.js?v=${version}"></script>
	<!-- <script src="<%=contextPath%>/js/supersubs.js?v=${version}"></script> -->
	<!--  <script src="<%=contextPath%>/js/jquery.tweet.js?v=${version}"></script>  -->
	<script src="<%=contextPath%>/js/jquery.flexslider.js?v=${version}"></script>
	<!--<script src="<%=contextPath%>/js/retina.js?v=${version}"></script>-->

	<script src="<%=contextPath%>/js/custom.js?v=${version}"></script>

</body>

<script>
$(".wxShareBtn").click(function(){
	$('#shareModal').modal();
})

$("#continueBtn").click(function(){
	location.reload();
})


$("#followJinwanrBtn").click(function(){
	location.href="http://mp.weixin.qq.com/s?__biz=MzAxNTE1NjAzNg==&mid=202021319&idx=1&sn=b5a4929ecd8538c6cff8d7fc6220c150&key=1507480b90e51e63322c02edfd5db55077ac6b16f1e46fa5d7ecfe7c499e823000342a3bd678155a06076658bd1d00a8&ascene=1&uin=MTgyMDAzOTU%3D&devicetype=webwx&version=70000001&pass_ticket=0RUywMKHu6OSFH1wpV3Oyu7Zqhrrqf4iNMhvO%2BaoedM%3D";
})

$("#voteAbortBtn").click(function(){
	location.href="http://www.jinwanr.com/albums";
})

$("body").delegate('a.voteBtn', 'click', function(){
	var voteBtn = $(this);
	var voteOptionId = voteBtn.attr('dataItem');
	var voteJsonData = {"voteOptionId": voteOptionId};
	$.post("<%=contextPath%>/vote/vote.json", voteJsonData, function(data) {
		if(data.result==1){
			//投票成功
			voteBtn.text("您已投票");
			voteBtn.removeClass("voteBtn");
			var leftVoteTimes = data.data;
			if(leftVoteTimes>0){
				//alert("您已成功投票，还有"+leftVoteTimes+"次投票机会，请认真把握哦");
				$("#leftVoteTimes").text(leftVoteTimes);
				$("#voteAvailableContainer").show();
				$("#voteUnavailableContainer").hide();
				$("#voteErrorContainer").hide();
			}else{
				$("#voteAvailableContainer").hide();
				$("#voteUnavailableContainer").show();
				$("#voteErrorContainer").hide();
			}
		}else{
			$("#voteAvailableContainer").hide();
			$("#voteUnavailableContainer").hide();
			$("#voteErrorText").text(data.message);
			$("#voteErrorContainer").show();
		}
		$("#voteModal").modal();
	 }, "json");
});

</script>


<script>
var imgUrl = "<%=vote.getWxShareIconUrl()%>";
var lineLink = "http://www.jinwanr.com/vote/<%=vote.getId()%>";
var shareTitle = '【金玩儿网】 - <%=vote.getWxShareTitle()%>';
var shareDesc = '【金玩儿网】 - <%=vote.getWxShareContent()%>';

function shareFriend() {
    WeixinJSBridge.invoke('sendAppMessage',{
        "img_url": imgUrl,
        "link": lineLink,
        "desc": shareDesc,
        "title": shareTitle
    }, function(res) {
        //_report('send_msg', res.err_msg);
    })
}
function shareTimeline() {
    WeixinJSBridge.invoke('shareTimeline',{
    	"img_url": imgUrl,
        "link": lineLink,
        "desc": shareDesc,
        "title": shareTitle
    }, function(res) {
           //_report('timeline', res.err_msg);
    });
}
// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
    // 发送给好友
    WeixinJSBridge.on('menu:share:appmessage', function(argv){
        shareFriend();
    });
    // 分享到朋友圈
    WeixinJSBridge.on('menu:share:timeline', function(argv){
        shareTimeline();
    });
}, false);
</script>

<jsp:include page="../inc/ua.jsp"></jsp:include>
<script>
if(!isWeixin()){
	$(".wxShareBtn").hide();
}
</script>


</html>
