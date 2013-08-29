package com.bruce.designer.service.oauth.publisher;

import com.bruce.designer.service.oauth.SharedContent;

public class TencentWbPublisher implements ISharedPublisher{
    
    
    public void publishContent(SharedContent sharedContent){
        System.out.println("发布tencent weibo");
    }

}
