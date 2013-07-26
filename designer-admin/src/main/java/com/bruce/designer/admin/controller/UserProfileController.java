package com.bruce.designer.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.admin.bean.security.AdminUser;
import com.bruce.designer.admin.security.WebUserDetails;
import com.bruce.designer.admin.service.AdminResourceService;
import com.bruce.designer.admin.service.AdminUserService;
import com.bruce.designer.admin.utils.ValidatorUtil;


@Controller
public class UserProfileController extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(UserProfileController.class);
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminResourceService adminResourceService;
	
	@RequestMapping("/myProfile")
	public String profileEdit(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WebUserDetails userDetail = getUserInfo();
		int userId = userDetail.getUserId();
		
		AdminUser adminUser = adminUserService.loadById(userId);
		model.addAttribute("adminUser", adminUser);
		return "profile/myProfile";
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String updateProfile(Model model, AdminUser adminUser, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
//		Map<String, Object> resMap = new HashMap<String, Object>();
		
		String userName = adminUser.getUsername();
		if(adminUser==null || StringUtils.isBlank(userName)){
			model.addAttribute("message", "用户信息输入有误，请检查！");
			return "forward:/operationResult";
		}
		
		//过滤非法字符
		userName = ValidatorUtil.filterUnSafeChar(userName).trim();
		adminUser.setUsername(userName);
		
		WebUserDetails userinfo = getUserInfo();
		int userId = userinfo.getUserId();
		adminUser.setId(userId);
		
		result = adminUserService.save(adminUser);
		
		model.addAttribute("redirectUrl", "./myProfile");
		return "forward:/operationRedirect";
	}
	
}
