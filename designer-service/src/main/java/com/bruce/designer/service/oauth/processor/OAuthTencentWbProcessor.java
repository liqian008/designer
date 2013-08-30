package com.bruce.designer.service.oauth.processor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.exception.oauth.OAuthException;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.service.oauth.SharedContent;
import com.qq.connect.api.OpenID;

public class OAuthTencentWbProcessor implements IOAuthProcessor, InitializingBean {
    
    private com.qq.connect.oauth.Oauth qqOauth = new com.qq.connect.oauth.Oauth();
    
    /**
     * 换取tencent的accessToken
     * @param request
     * @return
     * @throws OAuthException
     */
    @Override
    public AccessTokenInfo loadToken(HttpServletRequest request) throws OAuthException {
        com.qq.connect.javabeans.AccessToken token;
        try {
            token = qqOauth.getAccessTokenByRequest(request);
            return parseTencentToken(token);
        } catch (Exception e) {
            throw new OAuthException();
        }
    }

    /**
     * 根据token获取第三方的用户Id，后续用于在token表中查询其绑定记录
     * @param tokenInfo
     * @return
     * @throws OAuthException
     */
    @Override
    public String loadThirdpartyUid(AccessTokenInfo tokenInfo) throws OAuthException {
        OpenID openId = new OpenID(tokenInfo.getAccessToken());
        try {
            String thirdpartyUid = openId.getUserOpenID();
            return thirdpartyUid;
        } catch (Exception e) {
            throw new OAuthException();
        }
    }

    /**
     * 加载第三方账户的基本信息，如昵称、性别、年龄等（目前只使用了昵称），通常用在前端的注册页面中展示
     * @param tokenInfo
     * @return
     * @throws OAuthException
     */
    @Override
    public AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo) throws OAuthException {
        //腾讯open api中暂不获取用户详细资料，原值返回
        return tokenInfo;
    }
    
    /**
     * 发布
     * @param sharedContent
     * @throws OAuthException
     */
    public void publishContent(SharedContent sharedContent) throws OAuthException {
        System.out.println("发布tencent weibo");
    }
    
    /**
     * 将wb的accessToken转换为通用accessToken
     * @param at
     * @return
     */
    private static AccessTokenInfo parseTencentToken( com.qq.connect.javabeans.AccessToken tencentToken) {
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
        accessTokenInfo.setAccessToken(tencentToken.getAccessToken());
        accessTokenInfo.setRefreshToken("");
        accessTokenInfo.setExpireIn(tencentToken.getExpireIn());
        accessTokenInfo.setThirdpartyType(IOAuthService.OAUTH_TENCENT_TYPE);
        return accessTokenInfo;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(qqOauth, "qqOauth must not null");
    }

}
