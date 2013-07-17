package com.bruce.designer.admin.controller.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.admin.bean.security.AdminMenu;
import com.bruce.designer.admin.bean.security.AdminRole;
import com.bruce.designer.admin.controller.BaseController;
import com.bruce.designer.admin.security.WebUserDetails;
import com.bruce.designer.admin.service.AdminMenuService;
import com.bruce.designer.admin.service.AdminRoleService;
import com.bruce.designer.admin.utils.ValidatorUtil;


@Controller
@RequestMapping("/sys")
public class AdminRoleController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AdminRoleController.class);
	
	@Autowired
	private AdminRoleService adminRoleService;
	@Autowired
	private AdminMenuService adminMenuService;
	
	@RequestMapping("/roles")
	public String roleList(Model model, String roleName, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<AdminRole> adminRoleList = adminRoleService.queryAll();
		model.addAttribute("adminRoleList", adminRoleList);
		return "sys/roleList";
	}
	
	@RequestMapping("/roleAdd")
	public String roleAdd(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		return "sys/roleEdit";
	}
	
	@RequestMapping("/roleEdit")
	public String roleEdit(Model model,int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		AdminRole adminRole = adminRoleService.loadById(id);
		model.addAttribute("adminRole", adminRole);
		return "sys/roleEdit";
	}
	
	
	@RequestMapping(value = "/saveRole", method = RequestMethod.POST)
	public String saveRole(Model model,AdminRole adminRole, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
//		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String roleName = adminRole.getRolename();
		if(adminRole==null || StringUtils.isBlank(roleName)){
			model.addAttribute("message", "角色信息输入有误，请检查！");
			return "forward:/u/operationResult";
		}
		
		//过滤非法字符
		roleName = ValidatorUtil.filterUnSafeChar(roleName).trim();
		adminRole.setRolename(roleName);
		
		result = adminRoleService.save(adminRole);
		
		model.addAttribute("redirectUrl", "./roles");
		return "forward:/u/operationRedirect";
	}
	
	
	@RequestMapping(value = "/delRole")
	public String delRole(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		//删除单个
		adminRoleService.deleteById(id);
		model.addAttribute("redirectUrl", "./roles");
		return "forward:/u/operationRedirect";
	}
	
	
	@RequestMapping("/roleResourceSet")
	public String roleResourceSet(Model model,int roleId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
//		AdminRole adminRole = adminRoleService.loadById(roleId);
		
		//取所有资源
		List<AdminMenu> allMenus = adminMenuService.queryAll();
		//取角色拥有的资源
		List<AdminMenu> roleMenus = adminMenuService.getMenusByRoleId(roleId);
		
//		model.addAttribute("roleInfo", roleInfo);
		model.addAttribute("allMenus", allMenus);
		model.addAttribute("roleMenus", roleMenus);
		
		
		return "sys/roleResourceSet";
	}
	
	@RequestMapping(value = "/saveRoleResource", method = RequestMethod.POST)
	public String saveRoleResource(Model model,  Integer roleId, List<Integer> menuIdList, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = adminRoleService.saveRoleMenus(roleId, menuIdList);
		
		model.addAttribute("redirectUrl", "./roles");
		return "forward:/u/operationRedirect";
	}
	
}