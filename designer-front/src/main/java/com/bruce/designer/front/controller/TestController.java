package com.bruce.designer.front.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.front.util.SitemapGenerator;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.IndexSlide;
import com.bruce.designer.service.IAlbumRecommendService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IAlbumCommentService;
import com.bruce.designer.service.IIndexSlideService;
import com.bruce.designer.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TestController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IAlbumCommentService commentService;
	@Autowired
	private IAlbumSlideService albumSlideService;
	@Autowired
	private IAlbumRecommendService albumRecommendService;
	@Autowired
	private IIndexSlideService indexSlideService;

	private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

	/**
	 * 首页slide的测试页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/indexSlideTest", method = RequestMethod.GET)
	public String indexSlideTest(Model model) {
		//系统轮播
		List<IndexSlide> indexSlideList = indexSlideService.queryIndexSlideList(0, AlbumController.INDEX_SLIDE_LIMIT);
		model.addAttribute("indexSlideList", indexSlideList);
		return "testTemplate/indexSlideTest";
	}

	/**
	 * 首页编辑推荐的测试页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/indexRecommendTest", method = RequestMethod.GET)
	public String indexRecommendTest(Model model) {
		//编辑推荐
		List<Album> recommendAlbumList = albumRecommendService.queryRecommendAlbums(AlbumController.INDEX_SLIDE_LIMIT);
		model.addAttribute("recommendAlbumList", recommendAlbumList);
		return "testTemplate/indexRecommendTest";
	}
	
	@RequestMapping(value = "/refreshSitemap.json", method = RequestMethod.GET)
    public ModelAndView buildSitemap(Model model) {
        int result = SitemapGenerator.getInstance().generateSiteMap();
        return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(result));
    }

	@RequestMapping(value = "/carousel", method = RequestMethod.GET)
	public String carousel(Model model) {
		return "carousel";
	}

}
