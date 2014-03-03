package com.bruce.designer.front.util;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;

import com.bruce.designer.util.ConfigUtil;
import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

// Java Code To Generate Sitemap

public class SitemapGenerator {

    private static final String domain = ConfigUtil.getString("domain");
    private static final String sitemapPath = ConfigUtil.getString("sitemap_dir_path");
    
    public static void generateSiteMap(){

        File dirFile = new File(sitemapPath);
        
        // If you need gzipped output
        WebSitemapGenerator sitemapGenerator;
        try {
            sitemapGenerator = WebSitemapGenerator.builder(domain, dirFile).gzip(false).build();
       
            WebSitemapUrl sitemapUrl = new WebSitemapUrl.Options(domain + "/index")
                    .lastMod(new Date()).priority(1.0)
                    .changeFreq(ChangeFreq.HOURLY).build();
    
            // You can add any number of urls here
            sitemapGenerator.addUrl(sitemapUrl);
            sitemapGenerator.addUrl(domain + "/albums");
            sitemapGenerator.write();
        } catch (MalformedURLException e) {
            //log this
            e.printStackTrace();
        }
        
        //TODO 移除原sitemap
        //TODO 重命名最新生成的sitemap
    }

    public static void main(String[] args) throws MalformedURLException  {
        generateSiteMap();
    }
    
}