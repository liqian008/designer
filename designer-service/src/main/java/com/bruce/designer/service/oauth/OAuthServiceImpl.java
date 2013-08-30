package com.bruce.designer.service.oauth;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.service.UserService;
import com.bruce.designer.service.oauth.processor.IOAuthProcessor;
import com.bruce.designer.service.oauth.processor.OAuthWeiboProcessor;

@Service
public class OAuthServiceImpl implements IOAuthService, InitializingBean {

    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private UserService userService;
    
    private IOAuthProcessor oauthProcessor = new OAuthWeiboProcessor();
    
    private Map<String, IOAuthProcessor> processorMap;
    
    /*线程池*/
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    
    public AccessTokenInfo loadTokenByCode(String code, String thirdpartyType) throws WeiboException, JSONException {
        
        if (StringUtils.isNotBlank(code)) {
            // 根据code获取token
        	AccessTokenInfo tokenInfo = oauthProcessor.loadToken(code);
            //直接第三方账户id
        	oauthProcessor.loadThirdpartyUid(tokenInfo);
            //查询本地token表，看第三方用户是否曾在本站绑定过
            AccessTokenInfo accessTokenInfo = accessTokenService.load(tokenInfo.getThirdpartyUid(), tokenInfo.getThirdpartyType());
            if(accessTokenInfo==null){//如果未绑定过（可进行绑定），直接返回
                //加载返回第三方账户基础信息
                return oauthProcessor.loadThirdpartyProfile(tokenInfo);
            }else{//如果绑定过(不可再进行绑定)
                //将第三方的最新token内容更新到db中
                return refreshToken(tokenInfo, accessTokenInfo);
            }
        }
        return null;
    }
    
    public void publish2Thirdparty(SharedContent sharedContent){
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
        dbTokenInfo.setExpiresIn(lastestToken.getExpiresIn());
        accessTokenService.updateById(dbTokenInfo);
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
        	IOAuthProcessor processor = processorMap.get(content.getThirdpartyType());
            //发布至第三方
        	processor.publishContent(content);
        }
    }
    
    
//    private AccessTokenInfo loadToken(String code) throws WeiboException {
//		weibo4j.Oauth weiboOauth = new weibo4j.Oauth();
//		weibo4j.http.AccessToken wbToken = weiboOauth.getAccessTokenByCode(code);
//		AccessTokenInfo tokenInfo = AccessTokenInfo.parseWeibo(wbToken);
//		return tokenInfo;
//	}
//    
//    /**
//     * 根据token获取第三方的用户Id，后续用于在token表中查询其绑定记录
//     * @param tokenInfo
//     * @return
//     * @throws WeiboException
//     * @throws JSONException
//     */
//    private String loadThirdpartyUid(AccessTokenInfo tokenInfo) throws WeiboException, JSONException {
//        //根据token获取第三方用户资料
//        Account am = new Account();
//        am.client.setToken(tokenInfo.getAccessToken());
//        JSONObject uidJson = am.getUid();
//        // 第三方uid
//        String thirdpartyUid = uidJson.getString("uid");
//        tokenInfo.setThirdpartyUid(thirdpartyUid);
//        return thirdpartyUid;
//    }
//    
//    /**
//     * 加载第三方账户的基本信息，如昵称、性别、年龄等（目前只使用了昵称），通常用在前端的注册页面中展示
//     * @param tokenInfo
//     * @return
//     * @throws WeiboException
//     */
//    private AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo) throws WeiboException {
//        weibo4j.Users users = new weibo4j.Users();
//        users.setToken(tokenInfo.getAccessToken());
//        weibo4j.model.User weiboUser = users.showUserById(tokenInfo.getThirdpartyUid());
//        tokenInfo.setThirdpartyUname(weiboUser.getScreenName());
//        return tokenInfo;
//    }
    
    

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
		Assert.notNull(oauthProcessor, "oauthProcessor can't be null");
//		Assert.notNull(processorMap, "processorMap can't be null");
	}

}
