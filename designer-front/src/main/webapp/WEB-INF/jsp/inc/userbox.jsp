<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.bruce.designer.bean.*" %>
<%@ page import="com.bruce.designer.front.constants.*" %>

<div class="well">
       		<%
       		User currentUser = (User)session.getAttribute(ConstFront.CURRENT_USER);
       		if(currentUser==null){
       		%>
       		<style type="text/css">
		     .form-signin {
		       max-width: 300px;
		     }
		     .form-signin input[type="text"],
		     .form-signin input[type="password"] {
		       font-size: 13px;
		       height: auto;
		       padding: 7px 9px;
		     }
		   </style>
       		
               <form class="form-signin" method="post" action="./login">
      <h3 class="form-signin-heading">用户登录</h3>
      <input type="text" name="username" class="input-block-level" placeholder="用户名">
      <input type="password" name="password" class="input-block-level" placeholder="密码">
      <label class="checkbox">
        <input type="checkbox" value="remember-me">记住密码
      </label>
      <button class="btn btn-danger" type="submit">登 录</button>
    </form>
    <%}else{%>
      <h3 class="form-signin-heading">用户中心</h3>
      <p class="text-center">用 户 名: <%=currentUser.getUsername()%></p>
      <a href="./settings" class="btn btn-primary">进入个人中心</a>
      <a href="./logout" class="btn btn-danger">注销</a>
      <%}%>
</div>