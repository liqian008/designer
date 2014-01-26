<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
User queryUser = (User)request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);

boolean isDesigner = queryUser.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED;
boolean isMe =  currentUser!=null&&queryUser!=null&&currentUser.getId().equals(queryUser.getId());
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

        <link rel="stylesheet" href="/designer-front/css/bootstrap.min.css">
        <link rel="stylesheet" href="/designer-front/css/font-awesome.css">
        <link rel="stylesheet" href="/designer-front/css/animate.css">
        <link rel="stylesheet" href="/designer-front/css/flexslider.css">
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
        
        <jsp:include page="../inc/topBar.jsp"></jsp:include>
           

        <div id="wrapper" class="boxed"> <!-- Page Wrapper: Boxed class for boxed layout - Fullwidth class for fullwidth page --> 
            
            <div class="header-background"> <!-- Header Background -->
                <jsp:include page="../inc/headerBanner.jsp"></jsp:include>

                <div class="header-wrap"> <!-- Header Wrapper, contains Mene and Slider -->
                	
                	<%
                	String menuFlag = "";
                	if(isMe){
                		menuFlag = "myHome";
                	}
                	%>
                    <jsp:include page="../inc/headerNav.jsp?menuFlag=<%=menuFlag%>"></jsp:include>
					<jsp:include page="../inc/ad.jsp"></jsp:include>
                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page --> 
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="./">首页</a>/</li>
                        <li><a href="javascript:void(0)"><%=queryUser.getNickname()%></a>/</li>
                        <li><a href="javascript:void(0)">作品辑</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">            
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
							<%-- <a href="/designer-front/<%=queryUser.getId()%>/home" class="button button-blue">作品辑</a>
							<a href="/designer-front/<%=queryUser.getId()%>/info" class="button button-white">个人资料</a> --%>
											
                        	<%if(!isDesigner){%>
                        	<div class="infobox info-warning info-warning-alt clearfix">
                                <span>!</span>
                                <div class="infobox-wrap">
                                    <h4>提示</h4>
                                    <p>
                                    非设计师身份，暂无作品展示! 
                                    </p>
                                </div>
                            </div>
                        	<%}else{%>
                        	
                        	<div id="infoboxContainer" class="infobox info-warning info-warning-alt clearfix" style="display:none">
                                <span>!</span>
                                <div class="infobox-wrap">
                                    <h4>提示</h4>
                                    <p id="infoboxMessage">
                                   	无更多数据!
                                    </p>
                                </div>
                            </div>
	                    	<div id="albumContainer">
	                    	</div>
	                    	<div>
	                    		<input type="hidden" id="albumsTailId" name="albumsTailId" value="0" />
								<input type="hidden" id="designerId" name="designerId" value="<%=queryUser.getId()%>" />
								<div class="shortcode-blogpost row-fluid" id="moreAlbumsContainer">
									<div class="span2 offset5">
										<input id="moreAlbumsBtn"
											class="button-small button button-white btn-block" type="button"
											value="加载更多..." />
									</div>
								</div>
	                    	</div>
							<%}%>
                        </section>
                       	
                       	<!-- right slidebar -->
						<aside class="sidebar widgets-light span3"> 
                       		<jsp:include page="../inc/right/sidebar.jsp"></jsp:include> 
                    	</aside>
                    </div>
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="../inc/footer.jsp"></jsp:include>
           
        </div> <!-- Close Page -->
   </div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="/designer-front/js/vendor/bootstrap.min.js"></script>
    <script src="/designer-front/js/jquery.hoverdir.js"></script>
    <script src="/designer-front/js/superfish.js"></script>
    <!-- <script src="/designer-front/js/supersubs.js"></script> -->
   <!--  <script src="/designer-front/js/jquery.tweet.js"></script>  -->
    <script src="/designer-front/js/jquery.flexslider.js"></script> 
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
			var jsonData = {'designerId':$("#designerId").val(), 'albumsTailId' : $("#albumsTailId").val(), 'numberPerLine':'3'};
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
				}else{
					$('#moreAlbumsContainer').hide();
					$('#infoboxMessage').text(data.message);
					$('#infoboxContainer').show();
				}
			});
		}
	</script>
    </body>
</html>
