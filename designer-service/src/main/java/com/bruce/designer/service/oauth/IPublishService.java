package com.bruce.designer.service.oauth;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bruce.designer.service.oauth.publisher.ISharedPublisher;



/**
 * 同步到第三方接口
 * @author liqian
 *
 */
public class IPublishService {
    
//    private ISharedPublisher publisher;
    private Map<String, ISharedPublisher> publisherMap;
    
    /*线程池*/
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    
    public void publish2Thirdparty(SharedContent sharedContent){
        SharedThread sharedThread = new SharedThread(sharedContent);
        //添加至线程中执行
        executorService.execute(sharedThread);
        return;
    }
    
    /**
     * 内部类线程
     * @author liqian
     *
     */
    class SharedThread implements Runnable{
        private SharedContent content;
        
        private SharedThread(SharedContent content){
            this.content = content;
        }
        
        @Override
        public void run() {
            ISharedPublisher publisher = publisherMap.get(content.getThirdpartyType());
            //发布至第三方
            publisher.publishContent(content);
        }
    }

    public Map<String, ISharedPublisher> getPublisherMap() {
        return publisherMap;
    }

    public void setPublisherMap(Map<String, ISharedPublisher> publisherMap) {
        this.publisherMap = publisherMap;
    }
    
}
