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

import com.bruce.designer.bean.Album;
import com.bruce.designer.bean.AlbumSlide;
import com.bruce.designer.bean.Comment;
import com.bruce.designer.bean.User;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.AlbumService;
import com.bruce.designer.service.AlbumSlideService;
import com.bruce.designer.service.CommentService;
import com.bruce.designer.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AlbumController {

	@Autowired
	private UserService userService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private AlbumSlideService albumSlideService;
	

	private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start( Model model) {
		return index(model);
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index( Model model) {
		List<Album> albumList = albumService.queryAlbumByStatus(ConstService.ALBUM_OPEN_STATUS);
		if(albumList!=null&&albumList.size()>0){
			for(Album loopAlbum: albumList){
				int albumId = loopAlbum.getId(); 
				List<Comment> commentList = commentService.queryCommentsByAlbumId(albumId);
				loopAlbum.setCommentList(commentList);
			}
			model.addAttribute("albumList", albumList);
		}
		return "index";
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
			List<Comment> commentList = commentService.queryCommentsByAlbumId(id);
			albumInfo.setCommentList(commentList);
			
			//读取幻灯片列表
			List<AlbumSlide> slideList = albumSlideService.querySlidesByAlbumId(id);
			albumInfo.setSlideList(slideList);
			
			model.addAttribute("albumInfo", albumInfo);
		}
		return "albumInfo";
	}
	
	@RequestMapping(value = "/editAlbum")
	public String editAlbum(Model model, Integer albumId) { 
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
	@RequestMapping(value = "/publishAlbum", method = RequestMethod.POST)
	public String publishAlbum(Model model,  HttpServletRequest request, String title, int coverId, int[] albumNums) {
        //检查用户登录
        User user = (User) request.getSession().getAttribute(ConstFront.CURRENT_USER);
        int userId = user.getId();
        
        if(albumNums!=null && albumNums.length>0){
            Album album = new Album();
            album.setUserId(userId);
            album.setTitle(title);
            album.setStatus(ConstService.ALBUM_OPEN_STATUS);
            String coverImgUrl = request.getParameter("largeImage"+coverId);
            album.setCoverImg(coverImgUrl);
            
            //提交作品专辑，建议使用外部主键生成器
            int result = albumService.save(album);
            if(result>0){
            	for(int loopId: albumNums){
//                  String smallImageUrl = request.getParameter("smallImage"+loopId);
//                  String mediumImageUrl = request.getParameter("mediumImage"+loopId);
                  String largeImageUrl = request.getParameter("largeImage"+loopId);
                  String remark = request.getParameter("remark"+loopId);
                  
                  AlbumSlide slide = new AlbumSlide();
                  slide.setAlbumId(album.getId());
                  slide.setSlideImg(largeImageUrl);
                  slide.setRemark(remark);
                  slide.setUserId(userId);
                  slide.setStatus(ConstService.ALBUM_OPEN_STATUS);
                  albumSlideService.save(slide);
              }
            }
            
        }
        request.setAttribute(ConstFront.REDIRECT_PROMPT, "您的作品已成功发布，现在将转入首页，请稍候…");
        return "forward:/redirect.art";
    }
	
	@RequestMapping(value = "/{userId}/profile")
	public String userProfile(Model model, @PathVariable("userId") int userId) {
		User tbUser = userService.loadById(userId);
		if(tbUser!=null){
			model.addAttribute("tbUser", tbUser);
			List<Album> albumList = albumService.queryAlbumByUserId(userId);
			if(albumList!=null&&albumList.size()>0){
				for(Album loopAlbum: albumList){
					int albumId = loopAlbum.getId(); 
					List<Comment> commentList = commentService.queryCommentsByAlbumId(albumId);
					loopAlbum.setCommentList(commentList);
				}
				model.addAttribute("albumList", albumList);
			}
		}
		return "userProfile";
	}
}
