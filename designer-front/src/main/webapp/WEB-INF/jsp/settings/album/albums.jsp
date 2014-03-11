<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.data.*" %>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.front.util.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="com.bruce.designer.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
String contextPath = ConstFront.CONTEXT_PATH;

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
        <title>我的专辑作品 - 【金玩儿网】</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
        <script src="<%=contextPath%>/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(<%=contextPath%>/img/backgrounds/bg.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        
        <jsp:include page="../../inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                <jsp:include page="../../inc/headerBanner.jsp"></jsp:include> 

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="../../inc/headerNav.jsp?menuFlag=settings"></jsp:include>

                    <jsp:include page="../../inc/ad.jsp"></jsp:include>

                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="<%=contextPath%>">首页</a>/</li>
                        <li><a href="<%=contextPath%>/settings">设置</a>/</li>
                        <li><a href="javascript:void(0)">作品管理</a></li>
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
                                	<jsp:include page="../settingsTabInc.jsp?settingsMenuFlag=publisher"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="apply4Designer">
                                         <div class="widget-box widget-wrapper-form clearfix">
											<div class="content-title">
												<h4>作品管理</h4>
											</div>
											
											<%
											PagingData<Album> albumPagingData = (PagingData<Album>)request.getAttribute("albumPagingData");
											List<Album> albumList = albumPagingData.getPagingData();
											if(albumList!=null&&albumList.size()>0){
												int i=0;
												for(Album album: albumList){
													i++;
											%>
											<%if(i%2==1){%>
											<div class="shortcode-blogpost row-fluid">
											<%}%>
												<article class="blog-item span6">
													<div class="blog-post-image-wrap">
														<a class="blog-single-link"
															href="<%=contextPath%>/album/<%=album.getId()%>"><img
															src="<%=album.getCoverMediumImg()%>"></a>
													</div>
													<div class="content-wrap span9">
														<a href="#">
															<h5><%=album.getTitle()%></h5>
														</a>
														<ul>
															<li>
																<span>标 签:&nbsp;</span>
																<a href="<%=contextPath%>/tag/素组">素组</a>,&nbsp;
																<a href="<%=contextPath%>/tag/moto">moto</a>
															</li>
															<li><span>价 格:</span><%=album.getPrice()%>&nbsp;元</li>
															<li>
															<a href="javascript:void(0)"><%=album.getBrowseCount()%>&nbsp;浏览&nbsp;</a>/&nbsp;
															<a href="javascript:void(0)"><%=album.getCommentCount()%>&nbsp;评论&nbsp;</a>/&nbsp;
															<a href="javascript:void(0)"><%=album.getFavoriteCount()%>&nbsp;收藏&nbsp;</a>
															</li>
														</ul>
													</div>
													<div class="content-avatar">
														<%-- <a href="<%=contextPath%>/settings/editAlbum?albumId=<%=album.getId()%>">编 辑</a> --%>
														<a href="#" onclick="editAlbum(<%=album.getId()%>)">编 辑</a>
														<br/>
														<a href="#" onclick="delAlbum(<%=currentUser.getId()%>, <%=album.getId()%>)">删 除</a>
													</div>
												</article>
											<%if(i%2==0){%>
											</div>
											<%}%>
											<%}
											}%>
										</div>
										
										<%=PagingUtil.getPagingHtml(albumPagingData, contextPath + "/settings/albums")%>
										<!-- <div class="paging-navigation">
			                                <ul class="clearfix">
			                                </ul>
			                            </div> -->
			                            
                                    </div>
                                </div>
                            </div>
                        </section> 
                        
                        <!-- right slidebar -->
						<aside class="sidebar widgets-light span3">
                       		<jsp:include page="../../inc/right/sidebar_settings.jsp"></jsp:include> 
                    	</aside>
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="../../inc/footer.jsp"></jsp:include>
           
        </div> <!-- Close Page -->
   </div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
    <script src="<%=contextPath%>/js/superfish.js"></script>
    <!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
   <!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
    <script src="<%=contextPath%>/js/jquery.flexslider.js"></script> 
    <script src="<%=contextPath%>/js/retina.js"></script>
    <script src="<%=contextPath%>/js/custom.js"></script>
    <script>
    function editAlbum(albumId){
    	if(confirm("确定要编辑该作品辑吗？")){
    		location.href="<%=contextPath%>/settings/editAlbum?albumId="+albumId;
    	}
    }
    
    function delAlbum(userId, albumId){
    	if(confirm("作品删除后将无法恢复，确定删除吗？")){
    		location.href="<%=contextPath%>/settings/deleteAlbum?ownerId="+userId+"&albumId="+albumId;
    	}
    }
    </script>
    </body>
</html>
