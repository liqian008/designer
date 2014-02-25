package com.bruce.designer.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IUserService;

@Controller
@RequestMapping("/designer")
public class DesignerAdminController {

	@Autowired
	private IUserService userService = null;
	@Autowired
	private IAlbumService albumService = null;
	
	@RequestMapping("/users")
	public String userList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<User> userList = userService.queryUsersByStatus(ConstService.USER_STATUS_OPEN);
		model.addAttribute("userList", userList);
		return "designer/userList";
	}
	
	/**
	 * 设计师详情
	 * @param model
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping("/userInfo")
	public String userInfo(Model model, HttpServletRequest request, int userId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		User userInfo = userService.loadById(userId);
		model.addAttribute("userInfo", userInfo);
		return "designer/userInfo";
	}
	
	/**
	 * 设计师列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/designers")
	public String designerList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<User> designerList = userService.queryDesignersByStatus(ConstService.USER_STATUS_OPEN);
		model.addAttribute("designerList", designerList);
		return "designer/designerList";
	}
	
	
	@RequestMapping("/albums")
	public String albumList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<Album> albumList = albumService.queryAll();
		model.addAttribute("albumList", albumList);
		return "designer/albumList";
	}
	
	/**
	 * 专辑详情
	 * @param model
	 * @param request
	 * @param albumId
	 * @return
	 */
	@RequestMapping("/albumInfo")
	public String albumInfo(Model model, HttpServletRequest request, int albumId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		Album albumInfo = albumService.loadById(albumId);
		model.addAttribute("albumInfo", albumInfo);
		
		//加载计数（浏览、评论）
		albumService.initAlbumWithCount(albumInfo);
		
		//加载标签
		albumService.initAlbumWithTags(albumInfo);
		
		User userInfo = userService.loadById(albumInfo.getUserId());
		model.addAttribute("userInfo", userInfo);
		return "designer/albumInfo";
	}

}
