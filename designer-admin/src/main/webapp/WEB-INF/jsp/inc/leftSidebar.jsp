<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.designer.admin.bean.security.AdminMenu"%> 


<%!
public String isCurrent(String servletPath, String menuUrl){
	System.err.println("======================"+servletPath);
	System.err.println("============111=========="+menuUrl);  
    if(servletPath!=null&&servletPath.contains("/designer-admin"+menuUrl)){
         return " class='current'";
     }
    return "";
}
%>


<%
String current = request.getParameter("current");

//菜单加载，我放在session中，如果你需要考虑session过期的问题，也可以放在一个缓存或静态对象中
//或者每次都去数据库读取也行，但是不推荐每次去读取
List<AdminMenu> menus = (List<AdminMenu>)request.getSession().getAttribute("menus");
if(menus==null){
	menus = new ArrayList<AdminMenu>();
}
%>

<!-- Sidebar -->
		<div id="sidebar">

			<div class="sidebar-tabs">

		        <div id="general">

			        <!-- Sidebar user -->
			        <div class="sidebar-user widget">
						<div class="navbar"><div class="navbar-inner"><h6>Wazzup, Eugene!</h6></div></div>
			            <a href="index.html#" title="" class="user"><img src="/designer-admin/img/demo/sidebar_user_big.png" alt="" /></a>
			        </div>
			        <!-- /sidebar user -->
					
					<!-- 
			        <div class="general-stats widget">
				        <ul class="head">
				        	<li><span>Users</span></li>
				        	<li><span>Orders</span></li>
				        	<li><span>Visits</span></li>
				        </ul>
				        <ul class="body">
				        	<li><strong>116k+</strong></li>
				        	<li><strong>1290</strong></li>
				        	<li><strong>554</strong></li>
				        </ul>
				    </div>
				    -->

				    <!-- Main navigation -->
			        <ul class="navigation widget">
			        
			        	<%
				        	String servletPath = (String)request.getAttribute("servletPath");
			            	for(AdminMenu menu : menus){
				        %>  
			        
			            <li class="active"><a href="#" class="active" title="" class="expand"><i class="icon-reorder"></i><%=menu.getMenuName()%><strong>3</strong></a>
			                <ul>
			                	<%
					             for(AdminMenu childMenu : menu.getChildMenus()){
					            %>
<<<<<<< HEAD
			                    <li><a href="/designer-admin/<%=childMenu.getMenuUrl()%>" <%=isCurrent(servletPath, childMenu.getMenuUrl())%> title="<%=childMenu.getMenuName()%>"><%=childMenu.getMenuName()%></a></li>
=======
			                    <li><a href="<%=childMenu.getMenuUrl()%>" <%=isCurrent(servletPath, childMenu.getMenuUrl())%> title="<%=childMenu.getMenuName()%>"><%=childMenu.getMenuName()%></a></li>
>>>>>>> branch 'master' of https://github.com/liqian008/designer.git
			                    <%}%>
			                </ul>
			            </li>
			            <%}%>
			        </ul>
			        <!-- /main navigation -->

		        </div>
		    </div>
		</div>
		<!-- /sidebar -->
