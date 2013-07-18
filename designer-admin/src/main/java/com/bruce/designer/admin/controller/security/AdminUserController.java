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

import com.bruce.designer.admin.bean.security.AdminRole;
import com.bruce.designer.admin.bean.security.AdminUser;
import com.bruce.designer.admin.controller.BaseController;
import com.bruce.designer.admin.security.WebUserDetails;
import com.bruce.designer.admin.service.AdminRoleService;
import com.bruce.designer.admin.service.AdminUserService;
import com.bruce.designer.admin.utils.ValidatorUtil;


@Controller
@RequestMapping("/sys")
public class AdminUserController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminRoleService adminRoleService;
	
	@RequestMapping("/users")
	public String userList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<AdminUser> adminUserList = adminUserService.queryAll();
		model.addAttribute("adminUserList", adminUserList);
		return "sys/userList";
	}
	
	@RequestMapping("/userAdd")
	public String userAdd(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		return "sys/userEdit";
	}
	
	@RequestMapping("/userEdit")
	public String userEdit(Model model,int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		AdminUser adminUser = adminUserService.loadById(id);
		model.addAttribute("adminUser", adminUser);
		return "sys/userEdit";
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(Model model, AdminUser adminUser, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		String userName = adminUser.getUsername();
		if(adminUser==null || StringUtils.isBlank(userName)){
			model.addAttribute("message", "用户信息输入有误，请检查！");
			return "forward:/u/operationResult";
		}
		
		//过滤非法字符
		userName = ValidatorUtil.filterUnSafeChar(userName).trim();
		adminUser.setUsername(userName);
		result = adminUserService.save(adminUser);
		
		model.addAttribute("redirectUrl", "./users");
		return "forward:/u/operationRedirect";
	}
	
	@RequestMapping(value = "/delUser")
	public String delUser(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//删除单个
		adminUserService.deleteById(id);
		model.addAttribute("redirectUrl", "./users");
		return "forward:/u/operationRedirect";
	}
	
	
	@RequestMapping("/changePwd")
	public String changePwd(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WebUserDetails userInfo = this.getUserInfo();
		int userId = userInfo.getUserId();
		
		AdminUser adminUser = adminUserService.loadById(userId);
		model.addAttribute("adminUser", adminUser);
		return "sys/pwdEdit";
	}
		
	@RequestMapping("/userRoleSet")
	public String userRoleSet(Model model,int userId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		//取用户
//		AdminUser adminUser = adminUserService.loadById(userId);
		//取所有正常的角色(status=1)
		List<AdminRole> allRoles = adminRoleService.getAvailableRoles();
		//取用户拥有的角色
		List<AdminRole> userRoles = adminRoleService.getRolesByUserId(userId);
		
//		model.addAttribute("userInfo", userInfo);
		model.addAttribute("allRoles", allRoles);
		model.addAttribute("userRoles", userRoles);
		
		return "sys/userRoleSet";
	}
	
	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
	public String saveUserRole(Model model, Integer userId, List<Integer> roleIdList, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = adminUserService.saveUserRoles(userId, roleIdList);
		
		model.addAttribute("redirectUrl", "./users");
		return "forward:/u/operationRedirect";
	}
	
//	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
//	public String saveUserRole(Model model, UserInfo userInfo, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		boolean resultStatus = true;
//		int userId = userInfo.getId();
//		if(userId<=0){
//			resultStatus = false;
//			model.addAttribute("message", "没有指定用户");
//			return "forward:/u/operationResult";
//		}
//		
//		resultStatus = adminUserService.saveUserRole(userInfo);
//		
//		model.addAttribute("redirectUrl", "./users");
//		return "forward:/u/operationRedirect";
//	}
	
}