<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*"%>
<%@ page import="com.bruce.designer.front.constants.*"%>
<%@ page import="com.bruce.designer.constants.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
User user = (User)session.getAttribute(ConstFront.CURRENT_USER);
%>


<div id="header-container" class="clearfix">  <!-- Header Container, contains logo and contact button -->
    <header class="clearfix">
        <div class="container">
            <div class="row-fluid">
                <div class="span3 logo">
                    <a href="post-gallery.html#">
                        <img src="/designer-front/img/verendus-logo.png" alt="Verendus Logo" title="Verendus Logo" />
                    </a>
                </div>
				
				<div class="header-contact">
					<%if(user==null){%>
                    <a href="/designer-front/login.art">登 录</a> | <a href="/designer-front/register.art">注 册</a>
                    <%}else{ %>
                    欢迎您，<%=user.getNickname() %>&nbsp;|&nbsp;
                    <a href="/designer-front/settings.art">设置</a>&nbsp;|&nbsp;
                    <a href="/designer-front/settings.art?op=inbox&messageType=0">消息</a>&nbsp;|&nbsp;
                    <a href="/designer-front/logout.art">注销</a>
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