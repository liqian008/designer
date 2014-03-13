package com.bruce.designer.front.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.front.util.ResponseUtil;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.IndexSlide;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumCommentService;
import com.bruce.designer.service.IAlbumRecommendService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IIndexSlideService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.ConfigUtil;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

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

    private static final String sitemapPath = ConfigUtil.getString("sitemap_dir_path");

    /**
     * 首页slide的测试页
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/indexSlideTest", method = RequestMethod.GET)
    public String indexSlideTest(Model model) {
    	if(logger.isDebugEnabled()){
			logger.debug("系统轮播");
		}
    	// 系统轮播
        List<IndexSlide> indexSlideList = indexSlideService
                .queryIndexSlideList(0, AlbumController.INDEX_SLIDE_LIMIT);
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
    	if(logger.isDebugEnabled()){
			logger.debug("编辑推荐");
		}
    	// 编辑推荐
        List<Album> recommendAlbumList = albumRecommendService
                .queryRecommendAlbums(AlbumController.INDEX_SLIDE_LIMIT);
        model.addAttribute("recommendAlbumList", recommendAlbumList);
        return "testTemplate/indexRecommendTest";
    }

    @RequestMapping(value = "/refreshSitemap", method = RequestMethod.GET)
    public String buildSitemap(Model model) {
    	if(logger.isDebugEnabled()){
			logger.debug("更新sitemap");
		}
    	int result = generateSiteMap();
        String sitemapResult = "sitemap生成失败！";
        if(result>0){
            sitemapResult = "sitemap生成成功！";
        }
        model.addAttribute(ConstFront.REDIRECT_PROMPT, sitemapResult);
        model.addAttribute(ConstFront.REDIRECT_URL, ConstFront.CONTEXT_PATH + "/sitemap.xml");
        return ResponseUtil.getForwardReirect();
        
    }

    @RequestMapping(value = "/carousel", method = RequestMethod.GET)
    public String carousel(Model model) {
        return "carousel";
    }

    private int generateSiteMap() {
        int result = 0;
        File dirFile = new File(sitemapPath);

        // If you need gzipped output
        WebSitemapGenerator sitemapGenerator;
        try {
            sitemapGenerator = WebSitemapGenerator
                    .builder(ConstFront.DOMAIN, dirFile).gzip(false).build();

            List<WebSitemapUrl> siteUrlList = buildSiteUrls();
            sitemapGenerator.addUrls(siteUrlList);

            sitemapGenerator.write();
            result = siteUrlList.size();
        } catch (Exception e) {
            // log this
            e.printStackTrace();
        }
        // TODO 移除原sitemap & 重命名最新生成的sitemap
        return result;
    }

    private List<WebSitemapUrl> buildSiteUrls() throws MalformedURLException {
        Date date = new Date();
        List<WebSitemapUrl> urlList = new ArrayList<WebSitemapUrl>();
        // 首页
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN + "/index")
                .lastMod(date).priority(1.0).changeFreq(ChangeFreq.HOURLY)
                .build());
        // 新晋
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN + "/albums")
                .lastMod(date).priority(0.9).changeFreq(ChangeFreq.HOURLY)
                .build());
        // 当日最热
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN
                + "/hot/dailyAlbums").lastMod(date).priority(0.7)
                .changeFreq(ChangeFreq.HOURLY).build());
        // 当周最热
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN
                + "/hot/weeklyAlbums").lastMod(date).priority(0.8)
                .changeFreq(ChangeFreq.DAILY).build());
        // 本月最热
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN
                + "/hot/monthlyAlbums").lastMod(date).priority(0.9)
                .changeFreq(ChangeFreq.WEEKLY).build());
        
        // 所有album内容
        List<Album> albumList = albumService.queryAll();
        if (albumList != null && albumList.size() > 0) {
            for (Album album : albumList) {
                urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN
                        + "/album/" + album.getId())
                        .lastMod(album.getUpdateTime()).priority(0.8)
                        .changeFreq(ChangeFreq.DAILY).build());
            }
        }

        // 新晋
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN + "/designers")
                .lastMod(date).priority(0.6).changeFreq(ChangeFreq.HOURLY)
                .build());
        // 当日最热
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN
                + "/hot/dailyDesigners").lastMod(date).priority(0.7)
                .changeFreq(ChangeFreq.HOURLY).build());
        // 当周最热
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN
                + "/hot/weeklyDesigners").lastMod(date).priority(0.8)
                .changeFreq(ChangeFreq.DAILY).build());
        // 本月最热
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN
                + "/hot/monthlyDesigners").lastMod(date).priority(0.9)
                .changeFreq(ChangeFreq.WEEKLY).build());
        // 所有designer内容
        List<User> userList = userService
                .queryUsersByStatus(ConstService.USER_STATUS_OPEN);
        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                double priority = 0.5f;
                //设计师sitemap的优先级调高
                if(user!=null&&ConstService.DESIGNER_APPLY_APPROVED == user.getDesignerStatus()){
                    priority = 0.8f;
                }
                urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN + "/"
                        + user.getId() + "/home")
                        .lastMod(user.getUpdateTime()).priority(priority)
                        .changeFreq(ChangeFreq.DAILY).build());
            }
        }

        // 关于我们
        urlList.add(new WebSitemapUrl.Options(ConstFront.DOMAIN
                + "/hot/aboutUs").lastMod(date).priority(0.9)
                .changeFreq(ChangeFreq.MONTHLY).build());

        return urlList;
    }

}
