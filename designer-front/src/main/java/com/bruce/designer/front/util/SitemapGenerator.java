package com.bruce.designer.front.util;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.ConfigUtil;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

public class SitemapGenerator {
    
    private static final String domain = ConstFront.DOMAIN;
    private static final String sitemapPath = ConfigUtil.getString("sitemap_dir_path");
    
    @Autowired
    private IAlbumService albumService;
    @Autowired
    private IUserService userService;
    
    private static SitemapGenerator instance = new SitemapGenerator();
    
    private SitemapGenerator(){
    }
    
    public static SitemapGenerator getInstance(){
        if(instance==null){
            instance = new SitemapGenerator();
        }
        return instance;
    }

    public int generateSiteMap() {
        int result = 0;
        File dirFile = new File(sitemapPath);

        // If you need gzipped output
        WebSitemapGenerator sitemapGenerator;
        try {
            sitemapGenerator = WebSitemapGenerator.builder(domain, dirFile)
                    .gzip(false).build();

            List<WebSitemapUrl> siteUrlList = buildSiteUrls();
            sitemapGenerator.addUrls(siteUrlList);
            
            // WebSitemapUrl sitemapUrl = new WebSitemapUrl.Options(domain +
            // "/index")
            // .lastMod(new Date()).priority(1.0)
            // .changeFreq(ChangeFreq.HOURLY).build();
            //
            // // You can add any number of urls here
            // sitemapGenerator.addUrl(sitemapUrl);
            // sitemapGenerator.addUrl(domain + "/albums");
            sitemapGenerator.write();
            result = siteUrlList.size();
        } catch (Exception e) {
            // log this
            e.printStackTrace();
        }
        // TODO 移除原sitemap & 重命名最新生成的sitemap
        return result;
    }

    private List<WebSitemapUrl> buildSiteUrls()
            throws MalformedURLException {
        Date date = new Date();
        List<WebSitemapUrl> urlList = new ArrayList<WebSitemapUrl>();
        // 首页
        urlList.add(new WebSitemapUrl.Options(domain + "/index")
                .lastMod(date).priority(1.0)
                .changeFreq(ChangeFreq.HOURLY).build());
        // 新晋
        urlList.add(new WebSitemapUrl.Options(domain + "/albums")
        .lastMod(date).priority(0.9).changeFreq(ChangeFreq.HOURLY).build());
        // 当日最热
        urlList.add(new WebSitemapUrl.Options(domain + "/hot/dailyAlbums")
        .lastMod(date).priority(0.7).changeFreq(ChangeFreq.HOURLY).build());
        // 当周最热
        urlList.add(new WebSitemapUrl.Options(domain + "/hot/weeklyAlbums")
        .lastMod(date).priority(0.8).changeFreq(ChangeFreq.DAILY).build());
        // 本月最热
        urlList.add(new WebSitemapUrl.Options(domain + "/hot/monthlyAlbums")
        .lastMod(date).priority(0.9).changeFreq(ChangeFreq.WEEKLY).build());
        //所有album内容
//        List<Album> albumList = albumService.queryAll();
//        if(albumList!=null&&albumList.size()>0){
//            for(Album album: albumList){
//                urlList.add(new WebSitemapUrl.Options(domain + "/album/"+album.getId())
//                .lastMod(album.getUpdateTime()).priority(0.8).changeFreq(ChangeFreq.DAILY).build());
//            }
//        }
        
        // 新晋
        urlList.add(new WebSitemapUrl.Options(domain + "/designers")
        .lastMod(date).priority(0.6).changeFreq(ChangeFreq.HOURLY).build());
        // 当日最热
        urlList.add(new WebSitemapUrl.Options(domain + "/hot/dailyDesigners")
        .lastMod(date).priority(0.7).changeFreq(ChangeFreq.HOURLY).build());
        // 当周最热
        urlList.add(new WebSitemapUrl.Options(domain + "/hot/weeklyDesigners")
        .lastMod(date).priority(0.8).changeFreq(ChangeFreq.DAILY).build());
        // 本月最热
        urlList.add(new WebSitemapUrl.Options(domain + "/hot/monthlyDesigners")
        .lastMod(date).priority(0.9).changeFreq(ChangeFreq.WEEKLY).build());
        //所有designer内容
//        List<User> userList = userService.queryDesignersByStatus(ConstService.DESIGNER_APPLY_APPROVED);
//        if(userList!=null&&userList.size()>0){
//            for(User designer: userList){
//                urlList.add(new WebSitemapUrl.Options(domain + "/"+designer.getId()+"/home")
//                .lastMod(designer.getUpdateTime()).priority(0.7).changeFreq(ChangeFreq.DAILY).build());
//            }
//        }

        // 关于我们
        urlList.add(new WebSitemapUrl.Options(domain + "/hot/aboutUs")
        .lastMod(date).priority(0.9).changeFreq(ChangeFreq.MONTHLY).build());

        return urlList;
    }

    public static void main(String[] args) throws MalformedURLException {
        SitemapGenerator.getInstance().generateSiteMap();
    }
}
