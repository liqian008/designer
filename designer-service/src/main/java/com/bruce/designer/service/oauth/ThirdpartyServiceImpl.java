package com.bruce.designer.service.oauth;

//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.apache.log4j.Logger;

import weibo4j.Account;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.bean.User;
import com.bruce.designer.service.UserService;

@Service
public class ThirdpartyServiceImpl implements IThirdpartyService {

    private static final String THIRDPARTY_SINA_WEIBO = "SINA_WEIBO";

    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private UserService userService;
    
    @Override
    public User getUserByWeiboCode(String code) throws WeiboException {
        weibo4j.Oauth weiboOauth = new weibo4j.Oauth();
        if (StringUtils.isNotBlank(code)) {
            try {
                // 根据code获取token
                weibo4j.http.AccessToken token = weiboOauth.getAccessTokenByCode(code);
                UnifiedOAuth unifiedOAuth = UnifiedOAuth.parse(token);
                //根据token获取第三方用户资料
                
                //根据第三方用户资料查询用户是否在本站绑定过
                
                //如果未绑定过（可进行绑定），直接返回
                
                //如果绑定过(不可进行绑定)，load并返回，同时更新token
                
                
                UnifiedOAuthUser oauthUser = loadWeiboUserInfo(unifiedOAuth.getAccessToken());
                
                User user = genUser(oauthUser, unifiedOAuth);
                return user;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    
    /**
     * 通过oauth获取weibo的用户资料
     * 
     * @throws WeiboException
     * @throws JSONException
     */
    private UnifiedOAuthUser loadWeiboUserInfo(String accessToken)
            throws WeiboException, JSONException {
        // 根据accessToken获取第三方uid
        Account am = new Account();
        am.client.setToken(accessToken);
        JSONObject uidJson = am.getUid();
        // 第三方uid
        String thirdpartyUid = uidJson.getString("uid");

        weibo4j.Users users = new weibo4j.Users();
        users.setToken(accessToken);
        weibo4j.model.User weiboUser = users.showUserById(thirdpartyUid);
        String nickname = weiboUser.getScreenName();
        return new UnifiedOAuthUser(thirdpartyUid, nickname, THIRDPARTY_SINA_WEIBO);
    }
    
    
    /**
     * 构造临时用户（OAuth完毕但未绑定或注册的用户）
     * @param oauthUser
     * @return
     */
    private User genGuestUser(UnifiedOAuthUser oauthUser) {
        User user = new User();
        String name = oauthUser.getThirdpartyUname();
        user.setNickname(name);
        user.setUsername(name);
        user.setStatus((short) 2);// 临时访问用户
        return user;
    }

    private User genUser(UnifiedOAuthUser oauthUser,  UnifiedOAuth oAuth) {
        // 查询库中是否存在token
        User user = null;
        AccessTokenInfo accessTokenInfo = accessTokenService.load(oauthUser.getThirdpartyUid(), oauthUser.getThirdpartyType());
        if (accessTokenInfo == null) {//不存在
//            String nickname = oauthUser.getThirdpartyUname();
            user = genGuestUser(oauthUser);
            // 返回临时访问用户
            return user;
        } else {
            // 存在token记录，更新token
            accessTokenInfo.setAccessToken(oAuth.getAccessToken());
            accessTokenInfo.setRefreshToken(oAuth.getRefreshToken());
            accessTokenInfo.setExpiresIn(NumberUtils.toLong(oAuth.getExpiresIn()));
            accessTokenService.updateById(accessTokenInfo);
            
            user = userService.loadById(accessTokenInfo.getUserId());
            if (user == null) {
                user = genGuestUser(oauthUser);
            }
            return user;
        }
    }


    public static void main(String[] args) {
        ThirdpartyServiceImpl impl = new ThirdpartyServiceImpl();
        try {
            User user = impl.getUserByWeiboCode("3ca8f15414a8f974454788c38f494b28");
        } catch (WeiboException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public long publish2Weibo() {
        // TODO Auto-generated method stub
        return 0;
    }

}
