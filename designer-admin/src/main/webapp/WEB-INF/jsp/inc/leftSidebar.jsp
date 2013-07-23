<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bruce.designer.admin.bean.security.AdminMenu"%> 


<%!
public String isCurrentMenu(String servletPath, String menuUrl){
    if(servletPath!=null&&servletPath.contains("/designer-admin"+menuUrl)){
         return " id='current'";
     }
    return "";
}

public String isCurrentSubmenu(String servletPath, String menuUrl){
	//System.err.println("======================"+servletPath);
	//System.err.println("============111=========="+menuUrl);  
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
				<ul class="tabs-nav two-items">
		            <li><a href="forms.html#general" title=""><i class="icon-reorder"></i></a></li>
		            <li><a href="forms.html#stuff" title=""><i class="icon-cogs"></i></a></li>
		        </ul>
		        
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
			        
			            <li class="active"><a href="#" title="" class="expand" id="current"><i class="icon-reorder"></i><%=menu.getMenuName()%><strong>3</strong></a>
			                <ul>
			                	<%
					             for(AdminMenu childMenu : menu.getChildMenus()){
					            %>
			                    <li><a href="/designer-admin/<%=childMenu.getMenuUrl()%>" <%=isCurrentSubmenu(servletPath, childMenu.getMenuUrl())%> title="<%=childMenu.getMenuName()%>"><%=childMenu.getMenuName()%></a></li>
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
