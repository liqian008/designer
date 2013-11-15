package com.bruce.designer.front.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.Comment;
import com.bruce.designer.model.User;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.ICommentService;
import com.bruce.designer.service.IUserService;

/**
 * Handles requests for the application home page.
 */
//@Controller
public class FrontController {
    
	//@Autowired
	private IUserService userService;
	//@Autowired
	private IAlbumService albumService;
	//@Autowired
	private ICommentService commentService;
	//@Autowired
	private IAlbumSlideService albumSlideService;
	

	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);

	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start( Model model) {
		return index(model);
	}
	
	@RequestMapping(value = "/carousel", method = RequestMethod.GET)
	public String carousel( Model model) {
		return "carousel";
	}
	
	/**
	 * 首页请求
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index( Model model) {
		List<Album> albumList = albumService.queryAlbumByStatus(ConstService.ALBUM_OPEN_STATUS);
		if(albumList!=null&&albumList.size()>0){
//			for(Album loopAlbum: albumList){
//				int albumId = loopAlbum.getId(); 
//				List<Comment> commentList = commentService.queryCommentsByAlbumId(albumId);
//				loopAlbum.setCommentList(commentList);
//			}
			model.addAttribute("albumList", albumList);
		}
		return "index";
	}
	
	
	/**
	 * 时间轴作品列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/timeline", method = RequestMethod.GET) 
	public String timeline( Model model) {
		List<Album> albumList = albumService.queryAlbumByStatus(ConstService.ALBUM_OPEN_STATUS);
		if(albumList!=null&&albumList.size()>0){
//			for(Album loopAlbum: albumList){
//				int albumId = loopAlbum.getId(); 
//				List<Comment> commentList = commentService.queryCommentsByAlbumId(albumId);
//				loopAlbum.setCommentList(commentList);
//			}
			model.addAttribute("albumList", albumList); 
		}
		return "timeline";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main( Model model) {
		List<Album> albumList = albumService.queryAlbumByStatus(ConstService.ALBUM_OPEN_STATUS);
		if(albumList!=null&&albumList.size()>0){
			model.addAttribute("albumList", albumList);
		} 
		return "main";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/album", method = RequestMethod.GET)
	public String albumInfo(Model model, int id) { 
		Album albumInfo = albumService.loadById(id);
		if(albumInfo!=null){
			//读取评论
//			List<Comment> commentList = commentService.queryCommentsByAlbumId(id);
//			albumInfo.setCommentList(commentList);
			
			//读取幻灯片列表
			List<AlbumSlide> slideList = albumSlideService.querySlidesByAlbumId(id);
			albumInfo.setSlideList(slideList);
			
			model.addAttribute("albumInfo", albumInfo);
		}
		return "albumInfo";
	}
	
	@RequestMapping(value = "/editAlbum")
	public String saveAlbum(Model model, Integer albumId) { 
		if(albumId!=null&&albumId>0){
//			AlbumSlide slide = new AlbumSlide();
			Album album = albumService.loadById(albumId);
			model.addAttribute("album", album);
		}
		return "albumEdit";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/saveAlbum", method = RequestMethod.POST)
	public String saveAlbum(Model model, Album album) { 
		if(album!=null&&album.getSlideList()!=null&&album.getSlideList().size()>0){
			List<AlbumSlide> slideList = album.getSlideList();
			int userId = 3;
			album.setUserId(userId);
			album.setCoverLargeImg(slideList.get(0).getSlideLargeImg());
			
			int result = albumService.save(album);
			if(result==1){
				
				for(AlbumSlide slide: slideList){
					slide.setAlbumId(album.getId());
					slide.setUserId(userId);
					albumSlideService.save(slide);
					
				}
			}
			//System.out.println(result);
		}
		return "redirect:index";
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(Model model) {
		return "settings";
	}
	
//	@RequestMapping(value = "/xxx", method = RequestMethod.GET)
//	public String xxx(Model model) {
//		return "xxx";
//	}
	
	@RequestMapping(value = "/{userId}/profile")
	public String userProfile(Model model, @PathVariable("userId") int userId) {
		User tbUser = userService.loadById(userId);
		if(tbUser!=null){
			model.addAttribute("tbUser", tbUser);
			List<Album> albumList = albumService.queryAlbumByUserId(userId);
			if(albumList!=null&&albumList.size()>0){
				for(Album loopAlbum: albumList){
					int albumId = loopAlbum.getId(); 
//					List<Comment> commentList = commentService.queryCommentsByAlbumId(albumId);
//					loopAlbum.setCommentList(commentList);
				}
				model.addAttribute("albumList", albumList);
			}
		}
		return "userProfile";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(Model model, HttpServletRequest request, String username, String password) {
		User user = userService.authUser(username, password);
		if(user!=null){
			request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(Model model, HttpServletRequest request, String username, String nickname, String password, String repassword) {
		
		User user = new User();
		user.setUsername(username);
		user.setNickname(nickname);
		user.setPassword(password);
		Date currentTime = new Date();
		user.setCreateTime(currentTime);
		user.setUpdateTime(currentTime);
		int result = userService.save(user);
		if(result==1){
			user = userService.authUser(username, password);
			request.getSession().setAttribute(ConstFront.CURRENT_USER, user);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/postComment", method = RequestMethod.POST)
	public String postComment(Model model, HttpServletRequest request, int albumId, int albumPostId, String comment) {
		User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
		if(user!=null){
			Comment commentBean = new Comment();
			commentBean.setComment(comment);
			commentBean.setAlbumId(albumId);
//			commentBean.setAlbumContentId(albumPostId);
//			commentBean.setUserId(user.getId());
			commentBean.setNickname(user.getNickname());
			Date currentTime = new Date();
			commentBean.setCreateTime(currentTime);
			commentBean.setUpdateTime(currentTime);
			int result = commentService.save(commentBean);
		}
		return "redirect:/";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstFront.CURRENT_USER);
		return "forward:/";
	}
	
	
	
	
}
