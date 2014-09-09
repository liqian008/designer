<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.front.constants.*"%>

<%
String contextPath = ConstFront.CONTEXT_PATH;
%>
<div class="breadscrumbs">
	<div class="container">
		<ul class="clearfix">
			<li><a href="<%=contextPath%>/index">首页</a>/</li>
			<li><a href="javascript:void(0)">精品推荐</a></li>
		</ul>
	</div>
</div>

<div class="main fullwidth">
	<section class="content">
		<!-- Content -->
		<div class="container" id="proAlbumContainer">
		</div>
		
		<div class="shortcode-blogpost row-fluid" id="recommendAlbumsContainer">
			<div class="span2 offset5">
				<input class="button-small button button-white btn-block" type="button"
					value="加载中..." />
			</div>
		</div>
	</section>
	<!-- Close Content -->

	<script>
		fallLoadRecommends();
		
    	function fallLoadRecommends(){
    		//置为数据加载状态
    		var jsonData = {};
    		$.post('<%=contextPath%>/recommendAlbums.json', jsonData, function(data) {
    			var result = data.result;
	    		if(result==1){
	    			$("#proAlbumContainer").append(data.data.html);
	    			$("#recommendAlbumsContainer").hide();
	    		}else{
	    			$('#recommendAlbumsContainer').attr("style","display:none");
	    		}
   			});
    	}
    </script>
</div>
<!-- Close Main -->

