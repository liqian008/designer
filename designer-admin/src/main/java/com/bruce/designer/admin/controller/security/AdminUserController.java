package com.bruce.designer.admin.controller.security;

import java.util.Arrays;
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
import com.bruce.designer.admin.utils.AdminStatusEnum;
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
	public String userAdd(Model model, AdminUser adminUser, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		adminUser.setStatus(AdminStatusEnum.OPEN.getStatus());
		model.addAttribute("adminUser", adminUser);
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
		
		String username = adminUser.getUsername();
		if(adminUser==null || StringUtils.isBlank(username)){
			model.addAttribute("message", "用户信息输入有误，请检查！");
			return "forward:/operationResult";
		}
		
		//过滤非法字符
		username = ValidatorUtil.filterUnSafeChar(username).trim();
		adminUser.setUsername(username);
		
		if(adminUser.getId()>0){
			result = adminUserService.updateById(adminUser);
		}else{
			result = adminUserService.save(adminUser);
		}
		
		
		model.addAttribute("redirectUrl", "./users");
		return "forward:/operationRedirect";
	}
	
	@RequestMapping(value = "/delUser")
	public String delUser(Model model, int id, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		//删除单个
		adminUserService.deleteById(id);
		model.addAttribute("redirectUrl", "./users");
		return "forward:/operationRedirect";
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
		AdminUser adminUser = adminUserService.loadById(userId);
		//取所有正常的角色(status=1)
		List<AdminRole> allRoles = adminRoleService.getAvailableRoles();
		//取用户拥有的角色
		List<AdminRole> userRoles = adminRoleService.getRolesByUserId(userId);
		
		model.addAttribute("adminUser", adminUser);
		model.addAttribute("allRoles", allRoles);
		model.addAttribute("userRoles", userRoles);
		
		return "sys/userRoleSet";
	}
	
	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
    public String saveUserRole(Model model, Integer userId, Integer[] roleIds, HttpServletRequest request) {
        String servletPath = request.getRequestURI();
        model.addAttribute("servletPath", servletPath);
        
        int result = 0;
        if(userId!=null && userId>0 && roleIds!=null && roleIds.length>0){
            adminUserService.deleteRolesByUserId(userId);
            List<Integer> roleIdList = Arrays.asList(roleIds);
            result = adminUserService.saveUserRoles(userId, roleIdList);
        }
        
        model.addAttribute("redirectUrl", "./users");
        return "forward:/operationRedirect";
    }
	
//	@RequestMapping(value = "/saveUserRole", method = RequestMethod.POST)
//	public String saveUserRole(Model model, Integer userId, List<Integer> roleIds, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//		
//		adminUserService.deleteRolesByUserId(userId);
//		int result = adminUserService.saveUserRoles(userId, roleIds);
//		
//		model.addAttribute("redirectUrl", "./users");
//		return "forward:/operationRedirect";
//	}
	
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
//			return "forward:/operationResult";
//		}
//		
//		resultStatus = adminUserService.saveUserRole(userInfo);
//		
//		model.addAttribute("redirectUrl", "./users");
//		return "forward:/operationRedirect";
//	}
	
}
