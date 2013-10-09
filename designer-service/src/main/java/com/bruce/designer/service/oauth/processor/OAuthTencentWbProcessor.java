package com.bruce.designer.service.oauth.processor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.exception.oauth.OAuthException;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.service.oauth.SharedContent;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.qzone.UserInfoBean;

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
            throw new OAuthException(ErrorCode.OAUTH_ERROR, e);
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
            tokenInfo.setThirdpartyUid(thirdpartyUid);
            return thirdpartyUid;
        } catch (Exception e) {
            throw new OAuthException(ErrorCode.OAUTH_ERROR, e);
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
        //获取QQzone中的昵称
        UserInfo qzoneUserInfo = new UserInfo(tokenInfo.getAccessToken(), tokenInfo.getThirdpartyUid());
        try {
            UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
            //完善第三方的昵称
            StringBuilder sb = new StringBuilder();
//          StringBuilder sb = new StringBuilder("QQ用户");
            if(userInfoBean.getRet()==0){//成功响应
//                sb.append("_");
                sb.append(userInfoBean.getNickname());
            }
            tokenInfo.setThirdpartyUname(sb.toString());
        } catch (QQConnectException e) {
            throw new OAuthException(ErrorCode.OAUTH_ERROR, e);
        }
        
        //获取腾讯WB的昵称
//        com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(tokenInfo.getAccessToken(), tokenInfo.getThirdpartyUid());
//        try {
//            com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
//            tokenInfo.setThirdpartyUname(weiboUserInfoBean.getNickName());
//        } catch (QQConnectException e) {
//            throw new OAuthException(e);
//        }
        return tokenInfo;
    }
    
    /**
     * 发布
     * @param sharedContent
     * @throws OAuthException
     */
    public void shareout(SharedContent sharedContent) throws OAuthException {
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
