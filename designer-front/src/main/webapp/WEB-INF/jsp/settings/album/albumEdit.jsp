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
        <title>修改专辑作品 - 【金玩儿网】</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
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
                                	<jsp:include page="../settingsTabInc.jsp?settingsMenuFlag=publisher"></jsp:include>
                                </ul>
                                <div class="tab-content span9">
                                    <div class="tab-pane widgets-light active" id="apply4Designer">
                                         <div class="widget-box widget-wrapper-form clearfix">
                                        	
                                        	<%
                                        	Album album = (Album) request.getAttribute("album");
                                        	%>
                                        	<form id="album-widget-form" class="widget-form" method="post" class="clearfix"
												action="/designer-front/settings/updateAlbum">
												<input type="hidden" id="tags-change" name="tagsChange" value="false"/>
												<input type="hidden" id="cover-change" name="coverChange" value="false"/>
												<input type="hidden" name="albumId" value="<%=album.getId()%>"/>
												
												<div class="content-title">
													<h4>修改作品辑</h4>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">作品主题: </div>
													<div class="row-right">
														<input type="text" class="span6" id="title" name="title" value="<%=album.getTitle()%>"/> 
														<span id="album-title-required" class="required">*</span>
														<span id="album-title-prompt" class="text-prompt"></span>
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
														<span id="album-tags-prompt" class="text-prompt">多个标签间请用空格分隔</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">参考价格: </div>
													<div class="row-right">
														<input type="text" class="span2" id="price" name="price" value="<%=album.getPrice()%>"/> 元
														<span id="album-price-required" class="required">*</span>
														<span id="album-price-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">购买链接: </div>
													<div class="row-right">
														<input type="text" class="span8" id="link"  name="link" value="<%=album.getLink()%>"/> 
														<span id="album-link-required" class="required">*</span>
														<span id="album-link-prompt" class="text-prompt"></span>
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
															<input type='radio' id='coverId' name='coverId' value='<%=albumSlide.getId()%>' <%=albumSlide.getIsCover()==ConstService.ALBUM_SLIDE_IS_COVER?"checked='checked'":""%>/>设置为封面<br/>
														 </div>
														<%} %>
													</div>
												</div>
												<div class="row-container clearfix">
													<div class="row-left">作品描述: </div>
													<div class="row-right">
														<textarea class='album-slide-remark' name='remark' rows='2'><%=album.getRemark()%></textarea>
													</div>
												</div>
												
												<input id="album-update-button" class="common-submit button" type="button" value="修 改">
												<input id="album-delete-button" class="common-submit button" type="button" value="删除作品">
											</form>
										</div>
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
    <script src="/designer-front/js/vendor/bootstrap.min.js"></script>
    <script src="/designer-front/js/jquery.hoverdir.js"></script>
    <script src="/designer-front/js/superfish.js"></script>
    <!-- <script src="/designer-front/js/supersubs.js"></script> -->
   <!--  <script src="/designer-front/js/jquery.tweet.js"></script>  -->
    <script src="/designer-front/js/jquery.flexslider.js"></script> 
    <script src="/designer-front/js/retina.js"></script>
    <script src="/designer-front/js/custom.js"></script>
    <script src="/designer-front/js/validate.js"></script>
    
    <script>
    	$('#album-delete-button').click(function(){
    		if(confirm('作品删除后将无法恢复，确定删除吗？')){
    			
    		}
    	});
    	
    	$('#title').change(function(){
    		checkTitle();
    	});
    	
    	$("#tags").change(function(){
    		$('#tags-change').val("true");
    		checkTags();
    	});
    	
    	$('#price').change(function(){
    		checkPrice();
    	});
    	
    	$('#link').change(function(){
    		checkLink();
    	});
    	
    	$("#album-widget-form :radio").change(function(){
    		$('#cover-change').val("true");
    	});
    	
    	var titleAvailable = true;
	    var tagsAvailable = true;
		var priceAvailable = true;
		var linkAvailable = true;
		var albumAvailable = true;
		
	    $('#album-update-button').click(function(){
			checkAlbumSlides();
			if(titleAvailable && tagsAvailable && priceAvailable && linkAvailable && albumAvailable){
				$("#album-widget-form").submit();
	    	}
		});
	    
	  	//检查标题&正则
	    function checkTitle(){
	    	var titleVal = $('#title').val();
	    	titleVal = xssFilte(titleVal)
	    	$('#title').val(titleVal);
	    	if(titleVal==''){
				$('#album-title-prompt').text('请填写作品辑的名称').show();
				titleAvailable = false;
			}else{
				titleAvailable = true;
				$('#album-title-prompt').hide();
			}
	    }
	    
		//检查标签&正则
	    function checkTags(){
	    	var tagsVal = $('#tags').val();
	    	tagsVal = xssFilte(tagsVal)
	    	$('#tags').val(tagsVal);
			if(tagsVal==''){
	 			$('#album-tags-prompt').text('请填写作品辑的标签').show();
	 			tagsAvailable = false;
	 		}else{
	 			tagsAvailable = true;
	 			$('#album-tags-prompt').hide();
	 		}
	    }
	    
		//检查价格&正则
	    function checkPrice(){
	    	var priceVal = $('#price').val();
	    	priceVal = xssFilte(priceVal)
	    	$('#price').val(priceVal);
	    	if(priceVal==''){
				$('#album-price-prompt').text('请标注作品辑价格').show();
				priceAvailable = false;
			}else{
				priceAvailable = true;
				$('#album-price-prompt').hide();
			}
		}
		
		//检查标题&正则
	    function checkLink(){
			linkAvailable = true;
			/*
			var linkVal = $('#link').val();
	    	if(linkVal==''){//检查链接是否正确
				$('#album-link-prompt').text('购买链接不能为空').show();
			}else{
				$('#album-link-prompt').hide();
			} */
		}
		
		//检查标题&正则
	    function checkAlbumSlides(){
			var coverIdVal = $('input:radio[name="coverId"]:checked').val();
			if(coverIdVal == null){
				albumAvailable = false;
				alert('请设定作品辑封面图');
			}else{
				albumAvailable = true;
			}
		}
	  
	    </script>    
    </body>
</html>
