package com.bruce.designer.admin.controller.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.admin.bean.security.AdminMenu;
import com.bruce.designer.admin.controller.BaseController;
import com.bruce.designer.admin.services.AdminMenuService;
import com.bruce.designer.admin.utils.ValidatorUtil;


@Controller
@RequestMapping("/sys")
public class AdminMenuController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AdminMenuController.class);
	
	private AdminMenuService adminMenuService;
	
	
	@RequestMapping(value = "/delMenu")
	public String delMenu(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		//删除单个
		int result = adminMenuService.deleteById(id);
		model.addAttribute("redirectUrl", "./menus");
		//刷新菜单资源
		//adminMenuService.reloadMenusForUser(request);
		return "forward:/u/operationRedirect";
	}
	
	@RequestMapping("/menus")
	public String menuList(Model model, String menuName, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<AdminMenu> adminMenuList = adminMenuService.queryAll();
		model.addAttribute("adminMenuList", adminMenuList);
		return "sys/menuList";
	}
	
	@RequestMapping("/menuAdd")
	public String menuAdd(Model model,AdminMenu adminMenu, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<AdminMenu> parentMenus = adminMenuService.getChildMenus(0);
		AdminMenu rootMenu = new AdminMenu();
		rootMenu.setId(0);
		rootMenu.setMenuName("--顶级菜单--");
		parentMenus.add(0, rootMenu);
		
		model.addAttribute("adminMenu", adminMenu);
		model.addAttribute("parentMenus", parentMenus);
		
		return "sys/menuEdit";
	}
	
	@RequestMapping("/menuEdit")
	public String menuEdit(Model model,int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		AdminMenu adminMenu = adminMenuService.loadById(id);
		
		List<AdminMenu> parentMenus = adminMenuService.getChildMenus(0);
		AdminMenu rootMenu = new AdminMenu();
		rootMenu.setId(0);
		rootMenu.setMenuName("--顶级菜单--");
		parentMenus.add(0, rootMenu);
		
		model.addAttribute("adminMenu", adminMenu);
		model.addAttribute("parentMenus", parentMenus);
		
		return "sys/menuEdit";
	}
	
	@RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
	public String saveMenu(Model model,AdminMenu adminMenu, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
//		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String menuName = adminMenu.getMenuName();
		if(adminMenu==null || StringUtils.isBlank(menuName)){
			model.addAttribute("message", "角色信息输入有误，请检查！");
			return "forward:/u/operationResult";
		}
		
		//过滤非法字符
		menuName = ValidatorUtil.filterUnSafeChar(menuName).trim();
		adminMenu.setMenuName(menuName);
		
		result = adminMenuService.save(adminMenu);
		model.addAttribute("redirectUrl", "./menus");
		
		//刷新菜单资源
		//adminMenuService.reloadMenusForUser(request);
		return "forward:/u/operationRedirect"; 
	}
	
	
	
}