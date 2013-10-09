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
        <title>Verendus - Multipurpose Business Template</title>

        <meta name="description" content="Verendus - A HTML5 / CSS3 Multipurpose Business Template">
        <meta name="keywords" content="Bootstrap, Verendus, HTML5, CSS3, Business, Multipurpose, Template">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/font-awesome.css">
        <link rel="stylesheet" href="./css/animate.css">
        <link rel="stylesheet" href="./css/style.css">
        <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="./css/ie8.css" />    
        <![endif]-->
                

        <script src="./js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="./js/vendor/jquery-1.8.3.min.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lato:700' rel='stylesheet' type='text/css'>
    </head>
    <body class="body-background" style="background-image: url(./img/backgrounds/bg3.jpg); ">

        <!--[if lt IE 8]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->
        
        <jsp:include page="./inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                
                <jsp:include page="./inc/headerBanner.jsp"></jsp:include>
				
                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                    <jsp:include page="./inc/headerNav.jsp"></jsp:include>
                    
                    <div class="page-title">
                        <div class="container">
                            <!-- <div class="page-title-avatar">
                                <img src="/designer-front/img/demo/portraits/avatar_middle.jpg" alt="Page Title" width="80" height="80"/>
                            </div> -->
                            <div class="page-title-content">
                                <h2>此处放置广告作品</h2>
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
            <jsp:include page="./inc/breadcrumb.jsp"></jsp:include>
            
            <div class="main fullwidth">
            	<section class="content"> <!-- Content -->
                    <div class="container">
                    	<%
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
                                    <a class="blog-single-link" href="/designer-front/album.art?id=<%=album.getId()%>">
                                        <img src="<%=album.getCoverLargeImg()%>" alt="Portfolio Image">
                                    </a>
                                </div>
                                <div class="content-wrap span9">
                                   	<a href="#"><h5><%=album.getTitle()%></h5></a>
                                    <ul>
                                        <li><span>标 签:&nbsp;</span>翡翠, 珠宝, 玉石</li>
                                        <li><span>价 格:</span>1w~2w</li>
                                        <li>
                                        	<%=album.getBrowseCount()%>&nbsp;浏览&nbsp;/&nbsp;
                                        	<%=album.getCommentCount()%>&nbsp;评论&nbsp;/&nbsp;
                                        	<%=album.getFavoriteCount()%>&nbsp;收藏&nbsp;
                                        </li> 
                                    </ul>
                                </div>
                                <div class="content-avatar">
                                     <div class="content-author vcard">
                                         <img src="/designer-front/img/demo/portraits/avatar_middle.jpg" alt="Blogpost Comment">
                                     </div>
                                 </div>
                            </article>
                        <%if(endline){%>
                    	</div>
                        <%}
                        }
	                    }%>
	                    
	                    <div class="blog-navigation">
                            <ul class="clearfix">
                                <li><a class="button button-white" href="#">1</a></li>
                                <li><a class="button button-white" href="#">2</a></li>
                                <li><a class="button button-white" href="#">3</a></li>
                                <li><a class="button button-white" href="#">4</a></li>
                            </ul>
                        </div>
	                    
                    	<!-- <div class="shortcode-blogpost row-fluid">
                    		<div class="span4 offset4">
                    			<input id="currentPageNo" type="hidden" value="1"/>
                    			<input id="pageSize" type="hidden" value="4"/>
                    			<input class="button-small button button-white btn-block" id="moreAlbums" type="button" value="查看更多"/>
                    		</div>
                    	</div> -->
                    </div>
                </section> <!-- Close Content -->
			</div> <!-- Close Main -->

			<jsp:include page="./inc/footer.jsp"></jsp:include>
           
		</div> <!-- Close Page -->
	</div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="./js/vendor/bootstrap.min.js"></script>
    <script src="./js/superfish.js"></script>
    <!-- <script src="./js/supersubs.js"></script> -->
    <script src="./js/retina.js"></script>
    <script src="./js/custom.js"></script>
    <script>
    	$('#moreAlbums').click(function(){
    		//onclick="location.href='/designer-front/ajax/moreAlbums.art?pageNo=2&pageSize=4'"
   			$.post('/designer-front/ajax/moreAlbums.art?pageNo=2&pageSize=4', function(data) {
   				//alert(data);
   			});
    	})
    </script>

    </body>
</html>
