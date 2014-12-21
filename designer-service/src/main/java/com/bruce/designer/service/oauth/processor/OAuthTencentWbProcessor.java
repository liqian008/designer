package com.bruce.designer.service.oauth.processor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.service.oauth.SharedInfo;

public class OAuthTencentWbProcessor implements IOAuthProcessor, InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AccessTokenInfo loadToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadThirdpartyUid(AccessTokenInfo tokenInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shareout(SharedInfo sharedContent) {
		// TODO Auto-generated method stub
		
	}
    
//    private com.qq.connect.oauth.Oauth qqOauth = new com.qq.connect.oauth.Oauth();
//    
//    /**
//     * 换取tencent的accessToken
//     * @param request
//     * @return
//     * @throws DesignerException
//     */
//    @Override
//    public AccessTokenInfo loadToken(HttpServletRequest request){
//        com.qq.connect.javabeans.AccessToken token;
//        try {
//            token = qqOauth.getAccessTokenByRequest(request);
//            return parseTencentToken(token);
//        } catch (Exception e) {
//            throw new DesignerException(ErrorCode.OAUTH_ERROR);
//        }
//    }
//
//    /**
//     * 根据token获取第三方的用户Id，后续用于在token表中查询其绑定记录
//     * @param tokenInfo
//     * @return
//     * @throws DesignerException
//     */
//    @Override
//    public String loadThirdpartyUid(AccessTokenInfo tokenInfo){
//        OpenID openId = new OpenID(tokenInfo.getAccessToken());
//        try {
//            String thirdpartyUid = openId.getUserOpenID();
//            tokenInfo.setThirdpartyUid(thirdpartyUid);
//            return thirdpartyUid;
//        } catch (Exception e) {
//            throw new DesignerException(ErrorCode.OAUTH_ERROR);
//        }
//    }
//
//    /**
//     * 加载第三方账户的基本信息，如昵称、性别、年龄等（目前只使用了昵称），通常用在前端的注册页面中展示
//     * @param tokenInfo
//     * @return
//     * @throws DesignerException
//     */
//    @Override
//    public AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo){
//        //获取QQzone中的昵称
//        UserInfo qzoneUserInfo = new UserInfo(tokenInfo.getAccessToken(), tokenInfo.getThirdpartyUid());
//        try {
//            UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
//            //完善第三方的昵称
//            StringBuilder sb = new StringBuilder();
////          StringBuilder sb = new StringBuilder("QQ用户");
//            if(userInfoBean.getRet()==0){//成功响应
////                sb.append("_");
//                sb.append(userInfoBean.getNickname());
//            }
//            tokenInfo.setThirdpartyUname(sb.toString());
//        } catch (QQConnectException e) {
//            throw new DesignerException(ErrorCode.OAUTH_ERROR);
//        }
//        
//        //获取腾讯WB的昵称
////        com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(tokenInfo.getAccessToken(), tokenInfo.getThirdpartyUid());
////        try {
////            com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
////            tokenInfo.setThirdpartyUname(weiboUserInfoBean.getNickName());
////        } catch (QQConnectException e) {
////            throw new DesignerException(e);
////        }
//        return tokenInfo;
//    }
//    
//    /**
//     * 发布
//     * @param sharedContent
//     * @throws DesignerException
//     */
//    public void shareout(SharedInfo sharedContent){
//        System.out.println("发布tencent weibo");
//    }
//    
//    /**
//     * 将wb的accessToken转换为通用accessToken
//     * @param at
//     * @return
//     */
//    private static AccessTokenInfo parseTencentToken( com.qq.connect.javabeans.AccessToken tencentToken) {
//        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
//        accessTokenInfo.setAccessToken(tencentToken.getAccessToken());
//        accessTokenInfo.setRefreshToken("");
//        accessTokenInfo.setExpireIn(tencentToken.getExpireIn());
//        accessTokenInfo.setThirdpartyType(IOAuthService.OAUTH_TENCENT_TYPE);
//        return accessTokenInfo;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        Assert.notNull(qqOauth, "qqOauth must not null");
//    }

}
