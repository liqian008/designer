package com.bruce.designer.service.oauth;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weibo4j.Account;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.bean.User;
import com.bruce.designer.service.UserService;

@Service
public class OAuthServiceImpl implements IOAuthService {

    private static final String THIRDPARTY_SINA_WEIBO = "SINA_WEIBO";

    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private UserService userService;
    
    public AccessTokenInfo loadTokenByWeiboCode(String code) throws WeiboException, JSONException {
        weibo4j.Oauth weiboOauth = new weibo4j.Oauth();
        if (StringUtils.isNotBlank(code)) {
            // 根据code获取token
            weibo4j.http.AccessToken wbToken = weiboOauth.getAccessTokenByCode(code);
            AccessTokenInfo tokenInfo = AccessTokenInfo.parseWeibo(wbToken);
            //第三方账户id
            loadThirdpartyUid(tokenInfo);
            //查询本地token表，看第三方用户是否曾在本站绑定过
            AccessTokenInfo accessTokenInfo = accessTokenService.load(tokenInfo.getThirdpartyUid(), tokenInfo.getThirdpartyType());
            if(accessTokenInfo==null){//如果未绑定过（可进行绑定），直接返回
                //加载返回第三方账户基础信息
                return loadThirdpartyProfile(tokenInfo);
            }else{//如果绑定过(不可再进行绑定)
                //将第三方的最新token内容更新到db中
                return refreshToken(tokenInfo, accessTokenInfo);
            }
        }
        return null;
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
     * 根据token获取第三方的用户Id，后续用于在token表中查询其绑定记录
     * @param tokenInfo
     * @return
     * @throws WeiboException
     * @throws JSONException
     */
    private String loadThirdpartyUid(AccessTokenInfo tokenInfo) throws WeiboException, JSONException {
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
    private AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo) throws WeiboException {
        weibo4j.Users users = new weibo4j.Users();
        users.setToken(tokenInfo.getAccessToken());
        weibo4j.model.User weiboUser = users.showUserById(tokenInfo.getThirdpartyUid());
        tokenInfo.setThirdpartyUname(weiboUser.getScreenName());
        return tokenInfo;
    }
    
    

    public static void main(String[] args) {
        OAuthServiceImpl impl = new OAuthServiceImpl();
        try {
            AccessTokenInfo tokenInfo = impl.loadTokenByWeiboCode("3ca8f15414a8f974454788c38f494b28");
        } catch (WeiboException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
