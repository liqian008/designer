package com.bruce.designer.service.oauth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.exception.oauth.OAuthException;
import com.bruce.designer.service.UserService;
import com.bruce.designer.service.oauth.processor.IOAuthProcessor;
import com.bruce.designer.service.oauth.processor.OAuthTencentWbProcessor;
import com.bruce.designer.service.oauth.processor.OAuthWeiboProcessor;

@Service
public class OAuthServiceImpl implements IOAuthService, InitializingBean {

    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private UserService userService;
//    @Autowired
//    private IOAuthProcessor oauthProcessor;
    
    private Map<String, IOAuthProcessor> processorMap;
    
    public OAuthServiceImpl(){
        init();
    }
    
    private void init() {
        processorMap = new HashMap<String, IOAuthProcessor>();
        processorMap.put(IOAuthService.OAUTH_WEIBO_TYPE, new OAuthWeiboProcessor());
        processorMap.put(IOAuthService.OAUTH_TENCENT_TYPE, new OAuthTencentWbProcessor());
    }


    /*线程池*/
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    
    public AccessTokenInfo loadTokenByCallback(HttpServletRequest request, String thirdpartyType) throws OAuthException {
        if(StringUtils.isBlank(thirdpartyType)){
            String errorMessage = "参数thirdpartyType为空，无法处理";
            //log this
            throw new OAuthException(errorMessage);
        }else {
            IOAuthProcessor oauthProcessor = processorMap.get(thirdpartyType);
            // 根据code获取token
        	AccessTokenInfo tokenInfo = oauthProcessor.loadToken(request);
            //直接第三方账户id
        	oauthProcessor.loadThirdpartyUid(tokenInfo);
            //查询本地token表，看第三方用户是否曾在本站绑定过
            AccessTokenInfo dbTokenInfo = accessTokenService.load(tokenInfo.getThirdpartyUid(), tokenInfo.getThirdpartyType());
            if(dbTokenInfo==null){//如果未绑定过（可进行绑定），直接返回
                //加载返回第三方账户基础信息
                return oauthProcessor.loadThirdpartyProfile(tokenInfo);
            }else{//如果绑定过(不可再进行绑定)
                //将第三方的最新token内容更新到db中
                return refreshToken(dbTokenInfo, tokenInfo);
            }
        }
    }
    
    
    public void shareout(SharedContent sharedContent){
        SharedThread sharedThread = new SharedThread(sharedContent);
        //添加至线程中执行
        executorService.execute(sharedThread);
        return;
    }

    /**
     * 将第三方的最新token内容更新到db中
     * @param dbTokenInfo
     * @param lastestToken
     */
    private AccessTokenInfo refreshToken(AccessTokenInfo dbTokenInfo, AccessTokenInfo lastestToken) {
        dbTokenInfo.setAccessToken(lastestToken.getAccessToken());
        dbTokenInfo.setRefreshToken(lastestToken.getRefreshToken());
        dbTokenInfo.setExpireIn(lastestToken.getExpireIn());
        try{
            //容错处理，即使数据库更新失败，token依然可以使用
            accessTokenService.updateById(dbTokenInfo);
        }catch(Exception e){
            e.printStackTrace();
            //log this
        }
        return dbTokenInfo;
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
        	IOAuthProcessor oauthProcessor = processorMap.get(content.getThirdpartyType());
        	//发布至第三方
        	try {
        		oauthProcessor.shareout(content);
            } catch (OAuthException e) {
                e.printStackTrace();
                //log this
            }
        }
    }
    

    public static void main(String[] args) {
    	
    	System.out.println(new Date(157679999));
//        OAuthServiceImpl impl = new OAuthServiceImpl();
//        try {
//            AccessTokenInfo tokenInfo = impl.loadTokenByCode("3ca8f15414a8f974454788c38f494b28", "");
//        } catch (WeiboException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    	
    	
    }

	@Override
	public void afterPropertiesSet() throws Exception {
//		Assert.notNull(oauthProcessor, "oauthProcessor can't be null");
		Assert.notNull(processorMap, "processorMap can't be null");
	}

}
