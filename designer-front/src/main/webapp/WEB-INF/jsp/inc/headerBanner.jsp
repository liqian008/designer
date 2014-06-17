<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.model.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
String contextPath = ConstFront.CONTEXT_PATH;
%>

<%
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
%>

<div id="header-container" class="clearfix">  <!-- Header Container, contains logo and contact button -->
    <header class="clearfix">
        <div class="container"> 
            <div class="row-fluid">
                <div class="span3 logo">
                    <a href="<%=contextPath%>/">
                        <img src="<%=contextPath%>/img/verendus-logo.png" alt="Verendus Logo" title="Verendus Logo" />
                    </a>
                </div>
				
				<div class="header-contact">
					<%if(user==null){%>
					<input class="wb-login-small common-submit button" id="weiboLoginSmallBtn" type="button" onclick="location.href='<%=contextPath%>/connectWeibo'">
					<input class="qq-login-small common-submit button" id="qqLoginSmallBtn" type="button" onclick="location.href='<%=contextPath%>/connectTencent'">
					 
                    <%}else{ %>
                    欢迎您，<a href="<%=contextPath%>/<%=user.getId()%>/info"><%=user.getNickname() %></a>&nbsp;|&nbsp;
                    <a href="<%=contextPath%>/settings/msgbox"><span id="messageCount">消息中心</span></a>&nbsp;|&nbsp;
                    <%if(user.getDesignerStatus()!=null&&user.getDesignerStatus()==ConstService.DESIGNER_APPLY_APPROVED){%>
                    <a href="<%=contextPath%>/settings/newAlbum">发布新作品</a>&nbsp;|&nbsp;
                    <a href="<%=contextPath%>/settings/albums">作品辑管理</a>&nbsp;|&nbsp; 
                    <%}%>
                    <a href="<%=contextPath%>/settings">设置</a>&nbsp;|&nbsp;
                    <a href="<%=contextPath%>/logout">退出</a>
                    
                    <script>
                    //setTimeout(loadUnreadMessage, 5000);
				    loadUnreadMessage();

				    function loadUnreadMessage(){
						//置为数据加载状态 
						$.post('<%=contextPath%>/settings/unreadMessageCount.json', function(responseData) {
							var result = responseData.result;
							if(result==1){
								//$('#messageCount').attr("style", "color:orange");
								$('#messageCount').text('消息('+responseData.data+')');
								setTimeout(flashTitle, 2000);
							}
						});
					}
				    
				    function flashTitle(){
				    	newMsgCount();
				    }
				    
				    //消息提示
					var flag=false;
					var count = 0;
					function newMsgCount(){
						count = count + 1;
					    if(flag){
					        flag=false;
					        document.title='【新消息】';
					    }else{
					        flag=true;
					        document.title='【　　　】';
					    }
					    var interval = 800;
					    if(count%6==0){
					    	interval = 5000;
					    }
					    setTimeout(flashTitle, interval);
					}
				    </script>
                    <%}%>
                </div>
				
				<!-- 
                <div class="header-contact button">
                    <a href="post-gallery.html#">
                        <ul class="clearfix">
                            <li class="phone-number">
                                <i class="icon-phone"></i>0(123) - 45678910
                            </li>
                            <li class="mailto-email">
                                <i class="icon-envelope"></i>info@somnia-themes.com
                            </li>
                        </ul>
                    </a>
                </div>
                 -->
            </div>
        </div>
    </header>
    
</div>