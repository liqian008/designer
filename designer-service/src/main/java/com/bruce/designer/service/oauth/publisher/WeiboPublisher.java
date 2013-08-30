package com.bruce.designer.service.oauth.publisher;

import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;

import com.bruce.designer.service.oauth.SharedContent;

public class WeiboPublisher implements ISharedPublisher{
    
    public void publishContent(SharedContent sharedContent){
        System.out.println("发布weibo");
        try {
            String content = java.net.URLEncoder.encode(sharedContent.getContent(), "utf-8");
            Timeline tl = new Timeline();
            tl.client.setToken(sharedContent.getAccessToken());// access_token
            Status status = tl.UpdateStatus(content);
            System.out.println("Successfully upload the status to ["+ status.getText() + "].");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
