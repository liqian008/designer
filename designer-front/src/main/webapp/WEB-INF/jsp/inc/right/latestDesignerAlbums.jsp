<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>

<%
String contextPath = ConstFront.CONTEXT_PATH;

User queryUser = (User)request.getAttribute(ConstFront.REQUEST_USER_ATTRIBUTE);
%>

<div class="widget-box widget-flickr">
	<div class="content-title">
		<h4 class="widget-title">设计师作品辑</h4>
	</div>
	<div class="flickr-wrap clearfix" id="slideLatestDesignerAlbumsContainer">
		<!-- <div class="flickr_badge_image" id="flickr_badge_image2">
			<a href="http://www.flickr.com/photos/we-are-envato/10323793146/"><img
				src="http://farm4.staticflickr.com/3801/10323793146_66337e58d1_s.jpg"
				alt="Flickr 上的一張相片"
				title="Selina (Internal Comms) Deciding Between iPhone &amp; Samsung"
				height="75" width="75"></a>
		</div> -->
	</div>
</div>


<script>
fallLoad();

function fallLoad(){
	var jsonData = {'designerId':'<%=queryUser.getId()%>'};
	$.get('<%=contextPath%>/sideLatestAlbums.json', function(data) {
		var result = data.result;
		if(result==1){
			$("#slideLatestDesignerAlbumsContainer").append(data.data.html);
		}else{
			alert("error");
		}
	});
}
</script>