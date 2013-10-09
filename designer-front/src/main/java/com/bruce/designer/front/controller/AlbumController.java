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
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.ICommentService;
import com.bruce.designer.service.ICounterService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.impl.CounterServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AlbumController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private ICommentService commentService;
	@Autowired
	private IAlbumSlideService albumSlideService;
	@Autowired
	private ICounterService counterService;
	
	private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start( Model model) {
		return index(model);
	}
	
	/**
	 * 首页请求
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index( Model model) {
		PagingData<Album> albumPagingData = albumService.pagingQuery(ConstService.ALBUM_OPEN_STATUS, 1, 8);
		if(albumPagingData!=null&&albumPagingData.getPageData()!=null){
			List<Album> albumList = albumPagingData.getPageData();
			if(albumList!=null&&albumList.size()>0){
				for(Album loopAlbum: albumList){
					int albumId = loopAlbum.getId();
					loopAlbum.setBrowseCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_BROWSE + albumId));
					loopAlbum.setCommentCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_COMMENT + albumId));
					loopAlbum.setLikeCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_LIKE + albumId));
					loopAlbum.setFavoriteCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_FAVORITE + albumId));
				}
				model.addAttribute("albumList", albumList);
			}
			model.addAttribute("albumPagingData", albumPagingData);
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
		PagingData<Album> albumPagingData = albumService.pagingQuery(ConstService.ALBUM_OPEN_STATUS, 1, 16);
		if(albumPagingData!=null&&albumPagingData.getPageData()!=null){
			//List<Album> albumList = albumService.queryAlbumByStatus(ConstService.ALBUM_OPEN_STATUS);
			List<Album> albumList = albumPagingData.getPageData();
			if(albumList!=null&&albumList.size()>0){
				for(Album loopAlbum: albumList){
				    int albumId = loopAlbum.getId();
					loopAlbum.setBrowseCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_BROWSE + albumId));
                    loopAlbum.setCommentCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_COMMENT + albumId));
                    loopAlbum.setLikeCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_LIKE + albumId));
                    loopAlbum.setFavoriteCount(counterService.getCount(ConstRedis.COUNTER_KEY_ALBUM_FAVORITE + albumId));
				}
				model.addAttribute("albumList", albumList);
			}
			model.addAttribute("albumPagingData", albumPagingData);
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
            album.setCoverSmallImg(request.getParameter("smallImage"+coverId));
            album.setCoverMediumImg(request.getParameter("largeImage"+coverId));
            album.setCoverLargeImg(request.getParameter("largeImage"+coverId));
            
            //提交作品专辑，建议使用外部主键生成器
            int result = albumService.save(album);
            if(result>0){
            	for(int loopId: albumNums){
                  String remark = request.getParameter("remark"+loopId);
                  
                  AlbumSlide slide = new AlbumSlide();
                  slide.setAlbumId(album.getId());
                  slide.setSlideSmallImg(request.getParameter("smallImage"+loopId));
                  slide.setSlideMediumImg(request.getParameter("largeImage"+loopId));
                  slide.setSlideLargeImg(request.getParameter("largeImage"+loopId));
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
	
	
}
