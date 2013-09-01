package com.bruce.designer.service.oauth.processor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import weibo4j.Account;
import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.exception.oauth.OAuthException;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.service.oauth.SharedContent;

@Component
public class OAuthWeiboProcessor implements IOAuthProcessor{
	
    /**
     * 用code换取weibo的accessToken
     * @param code
     * @return
     * @throws OAuthException
     */
	public AccessTokenInfo loadToken(HttpServletRequest request)  throws OAuthException {
		weibo4j.Oauth weiboOauth = new weibo4j.Oauth();
		weibo4j.http.AccessToken wbToken;
        try {
            wbToken = weiboOauth.getAccessTokenByCode(request.getParameter("code"));
            AccessTokenInfo tokenInfo = parseWeiboToken(wbToken);
            return tokenInfo;
        } catch (Exception e) {
            throw new OAuthException(e);
        }
	}
    
    
    /**
     * 根据token获取第三方的用户Id，后续用于在token表中查询其绑定记录
     * @param tokenInfo
     * @return
     * @throws OAuthException
     */
    public String loadThirdpartyUid(AccessTokenInfo tokenInfo) throws OAuthException {
        //根据token获取第三方用户资料
        Account am = new Account();
        am.client.setToken(tokenInfo.getAccessToken());
        JSONObject uidJson;
        try {
            uidJson = am.getUid();
            String thirdpartyUid = uidJson.getString("uid");
            // 第三方uid
            tokenInfo.setThirdpartyUid(thirdpartyUid);
            return thirdpartyUid;
        } catch (Exception e) {
            throw new OAuthException(e);
        }
    }
    
    /**
     * 加载第三方账户的基本信息，如昵称、性别、年龄等（目前只使用了昵称），通常用在前端的注册页面中展示
     * @param tokenInfo
     * @return
     * @throws OAuthException
     */
    public AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo) throws OAuthException {
        weibo4j.Users users = new weibo4j.Users();
        users.setToken(tokenInfo.getAccessToken());
        weibo4j.model.User weiboUser;
        try {
            weiboUser = users.showUserById(tokenInfo.getThirdpartyUid());
            tokenInfo.setThirdpartyUname(weiboUser.getScreenName());
            return tokenInfo;
        } catch (WeiboException e) {
            throw new OAuthException(e);
        }
    }
	
    /**
     * 发布
     * @param sharedContent
     * @throws OAuthException
     */
    public void shareout(SharedContent sharedContent) throws OAuthException{
        System.out.println("发布weibo");
        try {
//            String content = java.net.URLEncoder.encode(sharedContent.getContent(), "utf-8");
            Timeline tl = new Timeline();
            tl.client.setToken(sharedContent.getAccessToken());// access_token
            Status status = tl.UpdateStatus(sharedContent.getContent());
            System.out.println("Successfully upload the status to ["+ status.getText() + "].");
        } catch (Exception e1) {
            throw new OAuthException(e1);
        }
    }
    
    /**
     * 将wb的accessToken转换为通用accessToken
     * @param at
     * @return
     */
    private static AccessTokenInfo parseWeiboToken(weibo4j.http.AccessToken wbToken) {
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
        accessTokenInfo.setAccessToken(wbToken.getAccessToken());
        accessTokenInfo.setRefreshToken(wbToken.getRefreshToken());
        accessTokenInfo.setExpireIn(Long.parseLong(wbToken.getExpireIn()));
        accessTokenInfo.setThirdpartyType(IOAuthService.OAUTH_WEIBO_TYPE);
        return accessTokenInfo;
    }
}
