package org.bruce.designer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bruce.designer.bean.Feed;
import com.bruce.designer.bean.FeedDTO;
import com.bruce.designer.bean.Tag;
import com.bruce.designer.bean.upload.UploadFileInfo;
import com.bruce.designer.dao.FeedDTOMapper;
import com.bruce.designer.dao.FeedIndexMapper;
import com.bruce.designer.dao.FeedLiveIndexMapper;
import com.bruce.designer.service.IFeedService;


public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext-dao.xml");
//        FeedDTOMapper fm = ctx.getBean(FeedDTOMapper.class);
//        FeedDTO f = new FeedDTO();
//        f.setContent("abcd");
//        f.setCreateTime(1234567L);
//        f.setUpdateTime(new Date());
//        f.setUserId(12345);
//        mapper.insert(f);
//        System.out.println(mapper.getFeedsByUid(12345).size());
        
        
//        List<Long> l = new ArrayList<Long>();
//        l.add(1L);
//        l.add(2L);
//        System.out.println(mapper.getFeedsByIds(l).size());
        
        
        IFeedService fim = ctx.getBean(IFeedService.class);
        List<UploadFileInfo> photos = new ArrayList<UploadFileInfo>();
        UploadFileInfo ui = new UploadFileInfo("abc.jpg", (short) 1, "http://www.baidu.com", 1231243);
        photos.add(ui);
        Feed f = new Feed();
        f.setCreateTime(System.currentTimeMillis());
        f.setUpdateTime(new Date());
        f.setText("test...");
        f.setPhotos(photos);
        f.setUserId(12345);
        Tag t1 = new Tag();
        t1.setId(1);
        t1.setTag("Love");
        Tag t2 = new Tag();
        t2.setId(2);
        t2.setTag("Hate");
        Tag t3 = new Tag();
        t3.setId(3);
        t3.setTag("Die");
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(t1);
        tags.add(t2);
        tags.add(t3);
        f.setTags(tags);
        System.out.println(fim.insert(f));
        
//        System.out.println(fim.getLiveFeed(56789, 0, 5));
    }
}
