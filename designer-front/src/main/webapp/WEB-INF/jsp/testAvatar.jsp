<%@page import="com.bruce.designer.front.controller.FrontController"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*" %>
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

        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/font-awesome.css">
        <link rel="stylesheet" href="./css/animate.css">
        <link rel="stylesheet" href="./css/flexslider.css">
        <link rel="stylesheet" href="./css/style.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="./css/ie8.css" />    
        <![endif]-->
                

        <script src="./js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="./js/vendor/jquery-1.8.3.min.js"></script>

		<link rel="stylesheet" href="./css/jcrop/jquery.Jcrop.css">
        <script src="./js/jcrop/jquery.Jcrop.js"></script>  

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
                                <img src="./img/demo/portraits/portrait-21.png" alt="Page Title" width="80" height="80"/>
                            </div> -->
                            <div class="page-title-content">
                                <h1>Gallery Post Format</h1>
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
            <div class="breadscrumbs">
                <div class="container">
                    <ul class="clearfix">
                        <li><a href="post-gallery.html#">Home</a>/</li>
                        <li><a href="post-gallery.html#">Blog</a>/</li>
                        <li><a href="post-gallery.html#">Gallery Post Format</a></li>
                    </ul>
                </div>
            </div>
            <div class="main fullwidth">
                <div class="container">
                    <div class="row-fluid">
                        <section class="content span9">
							<div class="content-title">
                                <h2>管理中心</h2>
                            </div>

                            <div class="shortcode-tabs shortcode-tabs-vertical clearfix">
                                <ul class="tabs-nav tabs clearfix span3">
                                    <li class="active"><a class="button button-white" href="#avatar" data-toggle="tab">修改头像</a></li>
                                    <%if(user.getDesignerStatus()==ConstService.DESIGNER_APPLY_PASSED){%>
                                    <li><a class="button button-white" href="./myFlowers.art">我的粉丝</a></li>
                                    <li><a class="button button-white" href="./designerProfile.art">设计师基本信息</a></li>
                                    <li><a class="button button-white" href="#syncSettings" data-toggle="tab">作品分享器（推荐）</a></li>
                                    <%}else{%>
                                    <li><a class="button button-white" href="./applyDesigner.art">申请成为设计师</a></li>
                                    <%}%>
                                </ul>
                                <div class="tab-content span8">
                                	<div class="tab-pane widgets-light active" id="avatar">
                                        <div class="widget-box widget-contact-form">
											<div class="content-title">
												<h4>修改头像</h4>
											</div>

											我当前的头像

											<img src="<%=user.getHeadImg()%>" width="200px"/>
											<%
											String originAvatarUrl = (String)request.getAttribute("originAvatarUrl");
											if(originAvatarUrl==null){
											%>

											<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/uploadAvatar.art" enctype="MULTIPART/FORM-DATA">
												<div class="input-container">
													头 像: <input type="file" class="contact-form-name" name="avatarImage"
														value="头 像"/>
												</div>
												<input class="contact-submit button" type="submit" value="上 传">
											</form>
											<%}else{ %>
												设置我的新头像
												<form id="contact-form-widget" method="post" class="clearfix"
												action="/designer-front/updateAvatarGo.art">

												<table>
												<tr>              
									              <td id="imgTd" style="width:<%=request.getAttribute("imgSrcWidth")%>px;height:<%=request.getAttribute("imgSrcHeight")%>px;" align="center" style="padding-top:5px;">    
									            	<img src="<%=originAvatarUrl%>" id="imgCrop" name="imgCrop"/>
									            </td>               
									           </tr> 
											</table>

												<input type="hidden"  id="x" name="x" />  
											    <input type="hidden"  id="y" name="y" />  
											    <input type="hidden"  id="w" name="w" />  
											    <input type="hidden"  id="h" name="h" />    

												<input class="contact-submit button" type="submit" value="修 改"/>
												</form>
											<%} %>
										</div>
                                    </div>
                                    
                                </div>
                            </div>
                        </section> 
                        
                        <jsp:include page="./inc/rightSidebar.jsp"></jsp:include>
                    	
                    </div>                        
                </div> <!-- Close Main -->
            </div> 
           
           <jsp:include page="./inc/footer.jsp"></jsp:include>
           
        </div> <!-- Close Page -->
   </div> <!-- Close wrapper -->

        
    <!-- Load all Javascript Files -->
    <script src="./js/vendor/bootstrap.min.js"></script>
    <script src="./js/jquery.hoverdir.js"></script>
    <script src="./js/superfish.js"></script>
    <!-- <script src="./js/supersubs.js"></script> -->
   <!--  <script src="./js/jquery.tweet.js"></script>  -->
    <script src="./js/jquery.flexslider.js"></script> 
    <script src="./js/retina.js"></script>

    <script src="./js/custom.js"></script>
    
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