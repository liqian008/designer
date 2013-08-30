package com.bruce.designer.service.oauth.processor;

import weibo4j.Account;
import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.service.oauth.SharedContent;

public class OAuthWeiboProcessor implements IOAuthProcessor{
	
	public AccessTokenInfo loadToken(String code) throws WeiboException {
		weibo4j.Oauth weiboOauth = new weibo4j.Oauth();
		weibo4j.http.AccessToken wbToken = weiboOauth.getAccessTokenByCode(code);
		AccessTokenInfo tokenInfo = AccessTokenInfo.parseWeibo(wbToken);
		return tokenInfo;
	}
    
    
    /**
     * 根据token获取第三方的用户Id，后续用于在token表中查询其绑定记录
     * @param tokenInfo
     * @return
     * @throws WeiboException
     * @throws JSONException
     */
    public String loadThirdpartyUid(AccessTokenInfo tokenInfo) throws WeiboException, JSONException {
        //根据token获取第三方用户资料
        Account am = new Account();
        am.client.setToken(tokenInfo.getAccessToken());
        JSONObject uidJson = am.getUid();
        // 第三方uid
        String thirdpartyUid = uidJson.getString("uid");
        tokenInfo.setThirdpartyUid(thirdpartyUid);
        return thirdpartyUid;
    }
    
    /**
     * 加载第三方账户的基本信息，如昵称、性别、年龄等（目前只使用了昵称），通常用在前端的注册页面中展示
     * @param tokenInfo
     * @return
     * @throws WeiboException
     */
    public AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo) throws WeiboException {
        weibo4j.Users users = new weibo4j.Users();
        users.setToken(tokenInfo.getAccessToken());
        weibo4j.model.User weiboUser = users.showUserById(tokenInfo.getThirdpartyUid());
        tokenInfo.setThirdpartyUname(weiboUser.getScreenName());
        return tokenInfo;
    }
	
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
