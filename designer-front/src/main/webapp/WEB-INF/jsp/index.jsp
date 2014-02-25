<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
%>

<!DOCTYPE html> 
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]--> 
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <title>金玩儿网 - 最专业的原创首饰设计网</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="/designer-front/css/bootstrap.min.css">
        <link rel="stylesheet" href="/designer-front/css/font-awesome.css">
        <link rel="stylesheet" href="/designer-front/css/animate.css">
        <!-- <link rel="stylesheet" href="/designer-front/css/layerslider.css"> -->
        <link rel="stylesheet" href="/designer-front/css/jquery.onebyone.css">
        <link rel="stylesheet" href="/designer-front/css/style.css"> 
        <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="/designer-front/css/ie8.css" />    
        <![endif]-->
                

        <script src="/designer-front/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="/designer-front/js/vendor/jquery-1.8.3.min.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(/designer-front/img/backgrounds/bg3.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        
        <jsp:include page="./inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                
                <jsp:include page="./inc/headerBanner.jsp"></jsp:include>
				
                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="./inc/headerNav.jsp"></jsp:include>
                    
                    <jsp:include page="./inc/indexSlide.jsp"></jsp:include>
                     
                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
			<div class="breadscrumbs">
			    <div class="container">
			        <ul class="clearfix">
			            <li><a href="/designer-front/index">首页</a>/</li> 
			            <li><a href="#">精品推荐</a></li>
			        </ul> 
			    </div>
			</div>
			            
            <div class="main fullwidth">
            	<section class="content"> <!-- Content -->
                    <div class="container" id="proAlbumContainer">
                    	<%-- <%
						List<Album> albumList = (List<Album>)request.getAttribute("albumList");
						if(albumList!=null){
							int count =0;
	                        for(Album album: albumList){
	                        	count++;
	                        	boolean newline = count%4==1;
	                        	boolean endline = count%4==0;
	                    %>
	                    <%if(newline){%>
                    	<div class="shortcode-blogpost row-fluid">
                        <%}%>
                            <article class="blog-item span3">
                                <div class="blog-post-image-wrap">
                                    <a class="blog-single-link" href="/designer-front/album/<%=album.getId()%>">
                                        <img src="<%=album.getCoverLargeImg()%>">
                                    </a>
                                </div>
                               	<div class="content-wrap span9">
                                   	<a href="#"><h5><%=album.getTitle()%></h5></a>
                                    <ul>
                                        <li><span>标 签:&nbsp;</span>翡翠, 珠宝, 玉石</li>
                                        <li><span>价 格:</span><%=album.getPrice()%></li>
                                        <li>
                                        	<a href="/designer-front/album/<%=album.getId()%>"><%=album.getBrowseCount()%>&nbsp;浏览</a>&nbsp;/&nbsp;
                                        	<a href="/designer-front/album/<%=album.getId()%>"><%=album.getCommentCount()%>&nbsp;评论</a>&nbsp;/&nbsp;
                                        	<a href="/designer-front/album/<%=album.getId()%>"><%=album.getFavoriteCount()%>&nbsp;收藏</a>&nbsp;
                                        </li> 
                                    </ul>
                                </div>
                                <div class="content-avatar">
                                     <div class="content-author vcard">
                                     	<a href="/designer-front/<%=album.getUserId()%>/home">
	                                     	<img src="/designer-front/img/demo/portraits/avatar_middle.jpg">
                                     	</a>
                                      </div>
                                 </div>
                            </article>
                        <%if(endline){%>
                    	</div>
                        <%}
                        }
	                    }%> --%>
	                    
                    	<!-- <div class="shortcode-blogpost row-fluid">
                    		<div class="span2 offset5">
                    			<input class="button-small button button-white btn-block" type="button" value="查看更多" onclick="location.href='/designer-front/albums'"/>
                    		</div>
                    	</div> -->
                    </div>
                </section> <!-- Close Content -->
			</div> <!-- Close Main -->
			
			<div class="breadscrumbs">
			    <div class="container">
			        <ul class="clearfix">
			            <li><a href="/designer-front/index">首页</a>/</li> 
			            <li><a href="/designer-front/albums">最新发布</a></li>
			        </ul> 
			    </div>
			</div>
			
			<div class="main fullwidth">
				<section class="content">
					<!-- Content -->
					<div class="container" id="latestAlbumContainer">
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

			<jsp:include page="./inc/footer.jsp"></jsp:include>
           
		</div> <!-- Close Page -->
	</div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="/designer-front/js/jquery-easing-1.3.js"></script>
    <script src="/designer-front/js/vendor/bootstrap.min.js"></script>
    <script src="/designer-front/js/jquery.onebyone.min.js"></script>
    <script src="/designer-front/js/superfish.js"></script>
    <script src="/designer-front/js/retina.js"></script>
    <!-- <script src="/designer-front/js/layerslider.kreaturamedia.jquery.js"></script> -->
    <!-- <script src="/designer-front/js/supersubs.js"></script> -->
   <!--  <script src="/designer-front/js/jquery.tweet.js"></script>  -->
	<!-- <script src="/designer-front/js/jquery.flexslider.js"></script>
	<script src="/designer-front/js/jquery.jcarousel.min.js"></script> -->

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
	    			$("#latestAlbumContainer").append(data.data.html);
	    			$("#proAlbumContainer").append(data.data.html);
	   				var nextTailId = data.data.tailId;
	    			$("#albumsTailId").val(nextTailId);
	    			if(nextTailId<=0){//无更多数据，则隐藏按钮
	   					$('#moreAlbumsContainer').attr("style","display:none");
	   				}else{//还有更多数据，启用加载按钮
	   					$('#moreAlbumsBtn').removeAttr("disabled");
	   					$('#moreAlbumsBtn').val("加载更多...");
	   				}
	    		}else{
	    			$('#moreAlbumsContainer').attr("style","display:none");
	    		}
   			});
    	}
    </script>
    </body>
</html>
