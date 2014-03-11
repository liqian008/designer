<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*" %>
<%@ page import="com.bruce.designer.service.oauth.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>
<%@ page import="com.bruce.designer.constants.*" %>
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
        <title>创建专辑作品 - 【金玩儿网】</title>

        <meta name="description" content="金玩儿网-最专业的原创首饰设计网，现代首饰设计师的聚集地，珠宝、翡翠、玉石、金饰、银饰、玛瑙等原创作品的鉴赏、交流平台。">
        <meta name="keywords" content="首饰,珠宝,翡翠,玉石,金饰,银饰,玛瑙,原创,设计,鉴赏,交流,分享,定制">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="<%=contextPath%>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/font-awesome.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/animate.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/flexslider.css">
        <link rel="stylesheet" href="<%=contextPath%>/css/style.css">
        
        <link rel="stylesheet"href="<%=contextPath%>/uploadify/uploadify.css">
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
                        <li><a href="javascript:void(0)">新发布</a></li>
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
                                        	<form id='album-widget-form' class="widget-form" method="post" class="clearfix"
												action="<%=contextPath%>/settings/postAlbum">
												<div class="content-title">
													<h4>发布专辑作品集【上限6张】</h4>
												</div>
												
												<div class="infobox info-info info-info-alt clearfix">
					                                <span>i</span>
					                                <div class="infobox-wrap">
					                                    <h4>小贴士：</h4>
					                                    <p>为达到最佳浏览效果，建议使用横竖比为4:3，且分辨率不小于800x600的图片</p>
					                                </div>
					                                <a href="#" class="info-hide"></a>
					                            </div>
												
												<div class="row-container clearfix">
													<div class="row-left">专辑作品主题: </div>
													<div class="row-right">
														<input type="text" class="span6" id="title"  name="title" value=""/>
														<span id="album-title-required" class="required">*</span>
														<span id="album-title-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">标签: </div>
													<div class="row-right">
														<input type="text" class="span5" id="tags" name="tags" value=""/>
														<span id="album-tags-required" class="required">*</span>
														<span id="album-tags-prompt" class="text-prompt">多个标签间请用空格分隔</span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">参考价格: </div>
													<div class="row-right">
														<input type="text" class="span3" id="price" name="price" value=""/> 元
														<span id="album-price-required" class="required">*</span>
														<span id="album-price-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">购买链接: </div>
													<div class="row-right">
														<input type="text" class="span8" id="link" name="link" value=""/>
														<span id="album-link-required" class="required">*</span>
														<span id="album-link-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">上传作品: </div>
													<div class="row-right" id="previewContainer">
														<input id="fileUploader" name="image" type="file" multiple="true">
														<span id="album-upload-prompt" class="text-prompt" style="display:none"></span>
													</div>
												</div>
												<div id="queue"></div>
												
												<div class="row-container clearfix">
													<div class="row-left">作品描述: </div>
													<div class="row-right">
														<textarea class='album-slide-remark' name='remark' rows='2'></textarea>
													</div>
												</div>
												
												<div class="row-container clearfix">
													<div class="row-left">验证码：</div>
													<div class="row-right">
														<input type="text" id="album-verifyCode" name="verifyCode" class="span2" value="">
														<a href="javascript:void(0)">
														<img src='<%=contextPath%>/verifyCode' id="album-verifyCode-img" width="75px"/>
														</a>
														<span id="album-verifyCode-required" class="required">*</span>
														<span id="album-verifyCode-prompt" class="text-prompt"></span>
													</div>
												</div>
												
												<%-- <div class="row-container clearfix">
													<div class="row-left">分享选项: </div>
													<div class="row-right">
														<%
														short sync2Weibo = 0;
														short sync2Tencent = 0;
														if(currentUser.getAccessTokenMap().get(IOAuthService.OAUTH_WEIBO_TYPE)!=null){
															sync2Weibo = currentUser.getAccessTokenMap().get(IOAuthService.OAUTH_WEIBO_TYPE).getSyncAlbum();
														%>
															<input type="checkbox" name="sync2Weibo" value="1" <%=sync2Weibo==1?"checked='checked'":""%>/>同时分享到Sina微博 &nbsp;<a href="<%=contextPath%>/settings/thirdparty">修改分享设置</a><br/>
														<%}
														
														if(currentUser.getAccessTokenMap().get(IOAuthService.OAUTH_TENCENT_TYPE)!=null){
															sync2Tencent = currentUser.getAccessTokenMap().get(IOAuthService.OAUTH_TENCENT_TYPE).getSyncAlbum();
														%>
														<input type="checkbox" name="sync2Tencent" value="1" <%=sync2Tencent==1?"checked='checked'":""%>/>同时分享到QQ空间&nbsp;<a href="<%=contextPath%>/settings/thirdparty">修改分享设置</a>
														<%}%>
													</div>
												</div> --%>
													
												<!-- <div>
													<ul id="imgPreview" class="clearfix">
													</ul>
												</div> -->
												
												<script type="text/javascript">
													$(function() {
														var counter = 0;
														$('#fileUploader').uploadify({
															'swf' : '<%=contextPath%>/uploadify/uploadify.swf',
															//'uploader' : '<%=contextPath%>/uploadify/response.json',
															'uploader' : '<%=contextPath%>/uploadImage.json;jsessionid=<%=session.getId()%>',
															//'cancelImg' : "uploadify-cancel.png",
															'fileObjName' : 'image',
															'debug' : false,
															'buttonText' : '选择照片',
															'method' : 'post',
															'fileTypeDesc' : '图片文件',
															'fileTypeExts' : '*.*',
															'multi' : true,
															'auto' : true,
															'uploadLimit' : 6,
															'simUploadLimit' : 1,
															'fileSizeLimit' : 2048,
															'onUploadSuccess' : function(file, data, response) {
													            //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);
													            //alert(data);
													            counter = counter + 1;
													            var response = jQuery.parseJSON(data);
													            
													        	$("<div  style='margin:10px 0; outline:1px solid #ECECEC'><img id='img1' src='"+response.data.mediumImage.url+"' width='100%'/><br/><input type='radio' id='coverId' name='coverId' value='"+counter+"'/>设置为封面<br/></div>").appendTo($("#previewContainer"));
													        	$("<input type='hidden' name='albumSlideNums' value='"+counter+"'/>").appendTo($("#previewContainer"));
													        	$("<input type='hidden' name='largeImage"+counter+"' value='"+response.data.largeImage.url+"'/>").appendTo($("#previewContainer"));
													        	$("<input type='hidden' name='mediumImage"+counter+"' value='"+response.data.mediumImage.url+"'/>").appendTo($("#previewContainer"));
													        	$("<input type='hidden' name='smallImage"+counter+"' value='"+response.data.smallImage.url+"'/>").appendTo($("#previewContainer"));
															},
														});
													});
												</script>
												
												<input class="common-submit button" id="submit-button" type="button" value="提 交">
												<input class="common-submit button" id="reset-button" type="reset" value="重 置">
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
    <script src="<%=contextPath%>/js/vendor/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/js/jquery.hoverdir.js"></script>
    <script src="<%=contextPath%>/js/superfish.js"></script>
    <!-- <script src="<%=contextPath%>/js/supersubs.js"></script> -->
   <!--  <script src="<%=contextPath%>/js/jquery.tweet.js"></script>  -->
    <script src="<%=contextPath%>/js/jquery.flexslider.js"></script> 
    <script src="<%=contextPath%>/js/retina.js"></script>
    <script src="<%=contextPath%>/js/custom.js"></script>
    <script src="<%=contextPath%>/js/validate.js"></script>
    
    <script>
    $('#title').focus();
    
    var titleAvailable = false;
    var tagsAvailable = false;
	var priceAvailable = false;
	var linkAvailable = false;
	var albumAvailable = false;
	var albumVerifyCodeAvailable = false;
    
	$('#title').blur(function(){
		checkTitle();
	});
	
	$("#tags").blur(function(){
		checkTags();
	});
	
	$('#price').blur(function(){
		checkPrice();
	});
	
	$('#link').blur(function(){
		checkLink();
	});
	
	$('#album-verifyCode').blur(function(){
		checkAlbumVerifyCode();
	});
	
    $('#submit-button').click(function(){
		checkAlbumSlides();
		if(titleAvailable && tagsAvailable && priceAvailable && linkAvailable && albumAvailable && albumVerifyCodeAvailable){
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
		}else{
			priceAvailable = true;
			$('#album-price-prompt').hide();
		}
	}
	
	//检查链接&正则
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
	
	//检查专辑内容
    function checkAlbumSlides(){
		if($('#coverId').length<=0){
			$('#album-upload-prompt').text('请上传作品内容图').show();
		}else if($('#coverId').length>6){
			$('#album-upload-prompt').text('上传作品内容图超限').show();
		}else{
			var coverIdVal = $('input:radio[name="coverId"]:checked').val();
			if(coverIdVal == null){
				$('#album-upload-prompt').text('请设置封面图片').show();
			}else{
				albumAvailable = true;
			}
		}
	}
	
	//检查验证码是否合法
	function checkAlbumVerifyCode(){
		var verifyCodeVal = $('#album-verifyCode').val();
		if(verifyCodeVal==''){
			$('#album-verifyCode-prompt').text('验证码不能为空').show();
		}else{
			var jsonData = {'verifyCode':verifyCodeVal};
			$.post('<%=contextPath%>/checkVerifyCode.json', jsonData, function(responseData) {
   				var result = responseData.result;
   				if(result==1){
   					//设置verifyCode的标识
   					albumVerifyCodeAvailable = true;
   					$('#album-verifyCode-prompt').text('').hide();
   				}else{
   	    			//设置verifyCode unavailable的标识
   	    			albumVerifyCodeAvailable = false;
   	    			$('#album-verifyCode-prompt').text(responseData.message).show();
   				}
   			});
		}
	}
	
    $('#album-verifyCode-img').click(function(){
		var newUrl = "<%=contextPath%>/verifyCode?" + Math.floor(Math.random()*100);
		$('#album-verifyCode-img').attr("src", newUrl).fadeIn();
    })
    </script>
    </body>
</html>
