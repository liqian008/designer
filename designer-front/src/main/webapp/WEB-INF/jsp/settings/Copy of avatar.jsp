<%@page import="com.bruce.designer.front.controller.FrontController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %> 

<%
SimpleDateFormat ymdSdf = new SimpleDateFormat(ConstFront.YYYY_MM_DD_FORMAT);
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
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

		<link rel="stylesheet" href="/designer-front/css/jcrop/jquery.Jcrop.css">
        <script src="/designer-front/js/jcrop/jquery.Jcrop.js"></script>  

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
                    <jsp:include page="../inc/headerNav.jsp?menuFlag=settings"></jsp:include>

                    <jsp:include page="../inc/ad.jsp"></jsp:include>

                </div> <!-- Close Header Menu -->
            </div> <!-- Close Header Wrapper -->
        <div class="page-top-stripes"></div> <!-- Page Background Stripes -->

        <div class="page"> <!-- Page -->
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="/designer-front">首页</a>/</li>
                        <li><a href="/designer-front/settings">设置</a>/</li>
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
                                	<jsp:include page="./settingsTabInc.jsp?settingsMenuFlag=avatar"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                	<div class="tab-pane widgets-light active" id="avatar">
                                        <div class="widget-box widget-wrapper-form">
											<div class="content-title">
												<h4>修改头像</h4>
											</div>

												
											
											<div class="row-container clearfix">
												<div class="row-left">当前头像:</div>
												<div class="row-right">
													<img src="<%=user.getHeadImg()%>" width="150px"/>
												</div>
											</div>
											
											<%
											String originAvatarUrl = (String)request.getAttribute("originAvatarUrl");
											if(originAvatarUrl==null){
											%>
											<form method="post" class="clearfix"
												action="/designer-front/settings/avatar" enctype="MULTIPART/FORM-DATA">
											<div class="row-container clearfix">
												<div class="row-left">上传新头像:</div>
												<div class="row-right">
													<input type="file" name="avatarImage" value="头 像"/>
												</div>
											</div>
											
											<div class="row-container clearfix">
												<div class="row-left"> </div>
												<div class="row-right">
													<input class="common-submit button" type="submit" value="上 传">
												</div>
											</div>
											</form>

											<%}else{ %>
											<form method="post" class="clearfix" action="/designer-front/settings">
											<div class="row-container clearfix">
												<div class="row-left">选取新头像</div>
												<div class="row-right">
													<table>
														<tr>              
											              <td id="imgTd" style="width:<%=request.getAttribute("imgSrcWidth")%>px;height:<%=request.getAttribute("imgSrcHeight")%>px;" align="center" style="padding-top:5px;">    
											            	<img src="<%=originAvatarUrl%>" id="imgCrop" name="imgCrop"/>
											            </td>               
											           </tr> 
													</table>
												</div>
											</div>
											<div class="row-container clearfix">
												<input type="hidden"  id="x" name="x" />  
											    <input type="hidden"  id="y" name="y" />  
											    <input type="hidden"  id="w" name="w" />  
											    <input type="hidden"  id="h" name="h" />    
												<input type="hidden" name="op" value="avatar">
												<input class="common-submit button" type="submit" value="修 改"/>
											</div>
											</form>
											<%} %>
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
    
    <script type="text/javascript">  
		jQuery(document).ready(function(){        
	        jQuery('#imgCrop').Jcrop({
	        	aspectRatio: 1,
	            onChange: showCoords,
	            onSelect: showCoords  
	        });   
          
		jQuery('#cropButton').click(function(){
			var w = jQuery("#w").val();
			var h = jQuery("#h").val();
			if(w == 0 || h == 0 ){
			    alert("您还没有选择图片的剪切区域,不能进行剪切图片!");  
			    return;
			}
			//alert("你要剪切图片的X坐标: "+x + ",Y坐标: " + y + ",剪切图片的宽度: " + w + ",高度：" + h );  
			if(confirm("确定按照当前大小剪切图片吗")){
			    document.form1.submit();
			}
         });
    });  
      
    function showCoords(c){  
        jQuery('#x').val(c.x);  
        jQuery('#y').val(c.y);  
        jQuery('#w').val(c.w);  
        jQuery('#h').val(c.h);    
        //显示剪切按键  
        jQuery('#cropTd').css("display","");  
                  
    }  
</script>  

    </body>
</html>