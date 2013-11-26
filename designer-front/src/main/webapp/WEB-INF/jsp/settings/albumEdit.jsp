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
        
        <link rel="stylesheet"href="/designer-front/uploadify/uploadify.css">
                                <!--[if IE 8]>
        <link rel="stylesheet" type="text/css" media="all" href="/designer-front/css/ie8.css" />    
        <![endif]-->
                

        <script src="/designer-front/js/vendor/modernizr-2.6.1-respond-1.1.0.min.js"></script>
        <script src="/designer-front/js/vendor/jquery-1.8.3.min.js"></script>
        <script src="/designer-front/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>

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
                        <li><a href="javascript:void(0)">编辑作品</a></li>
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
                                	<jsp:include page="./settingsTabInc.jsp?settingsMenuFlag=publisher"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="apply4Designer">
                                         <div class="widget-box widget-wrapper-form clearfix">
                                        	
                                        	<%
                                        	Album album = (Album) request.getAttribute("album");
                                        	%>
                                        	<form  class="widget-form" method="post" class="clearfix"
												action="/designer-front/updateAlbum">
												<input type="hidden" id="tags-change" name="tagsChange" value="false"/>
												<input type="hidden" name="albumId" value="<%=album.getId()%>"/>
												
												<div class="content-title">
													<h4>修改作品辑【上限6张】</h4>
												</div>
												
												<div class="infobox info-info info-info-alt clearfix">
					                                <span>i</span>
					                                <div class="infobox-wrap">
					                                    <h4>提示</h4>
					                                    <p>为达到最佳浏览效果，建议使用横竖比为4:3，且分辨率不小于800x600的图片</p>
					                                </div>
					                                <a href="#" class="info-hide"></a>
					                            </div>
												
												<div class="row-container clearfix">
													<div class="row-left">作品主题: </div>
													<div class="row-right">
														<input type="text" class="span6" name="title" value="<%=album.getTitle()%>"/> 
														<span id="album-title-required" class="required">*</span>
														<span id="album-title-prompt" class="text-prompt">作品主题不能为空</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">标签: </div>
													<div class="row-right">
														<%
														StringBuilder sb = new StringBuilder("");
														List<String> tagNameList = album.getTagList();
														if(tagNameList!=null&&tagNameList.size()>0){
															for(String tagName: tagNameList){
																sb.append(tagName);
																sb.append(",");
															}
															sb.setLength(sb.length()-1);
														}
														%>
														<input type="text" id="tags" class="span4" name="tags" value="<%=sb%>"/> 
														<span id="album-tags-required" class="required">*</span>
														<span id="album-tags-prompt" class="text-prompt">作品标签不能为空</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">参考价格: </div>
													<div class="row-right">
														<input type="text" class="span2" name="price" value="<%=album.getPrice()%>"/> 元
														<span id="album-price-required" class="required">*</span>
														<span id="album-price-prompt" class="text-prompt">参考价格不能为空</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">购买链接: </div>
													<div class="row-right">
														<input type="text" class="span10" name="link" value="<%=album.getLink()%>"/> 
														<span id="album-link-required" class="required">*</span>
														<span id="album-link-prompt" class="text-prompt">购买链接不能为空</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">作品内容: </div>
													<div class="row-right" id="previewContainer">
														<%
														List<AlbumSlide> albumSlideList = (List<AlbumSlide>) request.getAttribute("albumSlideList");
														int counter = 0;
														for(AlbumSlide albumSlide: albumSlideList){
															counter++;
														%>
														<div style="margin:10px 0; outline:1px solid #ECECEC">
															<img id='img-<%=albumSlide.getId()%>' src='<%=albumSlide.getSlideLargeImg() %>' width='100%'/><br/>
															<input type='hidden' name='smallImage<%=albumSlide.getId()%>' value='<%=albumSlide.getSlideSmallImg()%>'/>
															<input type='hidden' name='mediumImage<%=albumSlide.getId()%>' value='<%=albumSlide.getSlideMediumImg()%>'/>
															<input type='hidden' name='largeImage<%=albumSlide.getId()%>' value='<%=albumSlide.getSlideLargeImg()%>'/>
															<input type='radio' id='coverId' name='coverId' value='<%=albumSlide.getId()%>'/>设置为封面<br/>
														 </div>
														<%} %>
													</div>
												</div>
												<div class="row-container clearfix">
													<div class="row-left">作品描述: </div>
													<div class="row-right">
														<textarea class='album-slide-remark' name='remark' rows='2'></textarea>
													</div>
												</div>
												
												<input id="submit-button" class="common-submit button" type="submit" value="更 新">
											</form>
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
    <script>
    	$("#tags").change(function(){
    		$('#tags-change').val("true");
    	})
    	
	    $('#submit-button').click(function(){
			$('#album-title-prompt').text('').hide();
			$('#album-tags-prompt').text('').hide();
			$('#album-price-prompt').text('').hide();
			$('#album-link-prompt').text('').hide();
			
			var titleVal = $('#title').val();
			var tagsVal = $('#tags').val();
			var priceVal = $('#price').val();
			var linkVal = $('#link').val();
			if(titleVal==''){
				$('#title').focus();
				$('#album-title-prompt').text('作品标题不能为空').show();
			}else if(tagsVal==''){
				$('#tags').focus();
				$('#album-tags-prompt').text('标签不能为空').show();
			}else if(priceVal==''){
				$('#price').focus(); 
				$('#album-price-prompt').text('作品价格不能为空').show();
			}else if(linkVal==''){//检查链接是否正确
				$('#link').focus();
				$('#album-link-prompt').text('购买链接不能为空').show();
			}else if(false){//检查作品完整性
				//alert('作品不完整');
			}else{//验证通过，可以发布
				alert('publish');
				$("#album-widget-form").submit();
			}
		});
	    </script>    
    </body>
</html>
