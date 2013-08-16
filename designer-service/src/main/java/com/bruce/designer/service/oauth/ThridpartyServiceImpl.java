package com.bruce.designer.service.oauth;

//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import weibo4j.Account;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.bean.User;
import com.bruce.designer.bean.UserSource;
import com.bruce.designer.service.UserService;


/**
 * Comments for ThridPartyServiceImpl.java
 * 
 * @author <a href="mailto:jun.liu1209@gmail.com">刘军</a>
 * @createTime 2013-3-17 下午10:27:10
 */
public class ThridpartyServiceImpl implements IThirdpartyService {

    /**
     * Logger for this class
     */
    //private static final Logger logger = Logger.getLogger(ThridPartyServiceImpl.class);

    private static final String WEIBO_RESPONSE_TYEP = "code";
    
    private static final String THIRDPARTY_SINA_WEIBO = "SINA_WEIBO";
    
    private weibo4j.Oauth weiboOauth;

    private IAccessTokenService accessTokenService;

    private UserService userService;

    private IUserSourceService userSourceService;
   

    @Override
    public User getUserByWeiboCode(String code) throws WeiboException{
        if (StringUtils.isNotBlank(code)) {
            try{
            //根据code获取token
            weibo4j.http.AccessToken token = weiboOauth.getAccessTokenByCode(code);
            //根据accessToken获取第三方uid
            Account am = new Account();
            am.client.setToken(token.getAccessToken());
            JSONObject uidJson = am.getUid();
            //第三方uid
            String thirdpartyUid = uidJson.getString("uid");
            //查询库中是否存在
            AccessTokenInfo accessTokenInfo = accessTokenService.loadAccessTokenInfo(thirdpartyUid, THIRDPARTY_SINA_WEIBO);
            if (accessTokenInfo == null) {
                //生成新账户
                weibo4j.Users users = new weibo4j.Users();
                users.setToken(token.getAccessToken());
                weibo4j.model.User weiboUser = users.showUserById(thirdpartyUid);
                //String username = weiboUser.getName();
                //昵称
                String nickname = weiboUser.getScreenName();
                
                User user = new User();
                user.setNickname(nickname);
                
                userService.save(user);
                
                //没有就创建用户&插入
                accessTokenInfo = new AccessTokenInfo();
                accessTokenInfo.setAccessToken(token.getAccessToken());
                accessTokenInfo.setRefreshToken(token.getRefreshToken());
                accessTokenInfo.setTokenType(THIRDPARTY_SINA_WEIBO);
                accessTokenInfo.setThirdpartyUid(thirdpartyUid);
                accessTokenInfo.setExpiresIn(NumberUtils.toLong(token.getExpireIn()));
                //生成token
                accessTokenService.save(accessTokenInfo);
                
                //发送微博（可以异步）
                return user;
            } else {
//                if (logger.isDebugEnabled()) {
//                    logger.debug("found accesstoken in db!");
//                }
                //存在的话则更新token
                accessTokenInfo.setAccessToken(token.getAccessToken());
                accessTokenInfo.setRefreshToken(token.getRefreshToken());
                accessTokenInfo.setExpiresIn(NumberUtils.toLong(token.getExpireIn()));
                accessTokenService.updateById(accessTokenInfo);
                
                
                User user = userService.loadById(accessTokenInfo.getUserId());
                return user;
            }
        }catch(Exception e){
            return null;
        }
        }
        return null;
    }

    /**
     * 通过微博生成帐号
     * 
     * @param token
     * @return
     * @throws WeiboException
     */
    public User generateNewAccountByWeibo(weibo4j.http.AccessToken token) throws WeiboException {
        //获取第三方名字
        weibo4j.Users users = new weibo4j.Users();
        users.setToken(token.getAccessToken());
        weibo4j.model.User weiboUser = users.showUserById(token.getUid());
        
        int gender = 0 ;

        //生成帐号
        User user = registerService.registerByThridParty(weiboUser.getName(), gender,
                AccessTokenConstant.ACCESS_TOKEN_TYPE_WEIBO);
        //插入绑定关系
        UserSource userSource = new UserSource();
        userSource.setUserId(user.getId());
        userSource.setThirdpartyUid("");
        userSource.setTokenType(THIRDPARTY_SINA_WEIBO);
        userSourceService.save(userSource);

        return user;
    }


    public weibo4j.Oauth getWeiboOauth() {
        return weiboOauth;
    }

    public void setWeiboOauth(weibo4j.Oauth weiboOauth) {
        this.weiboOauth = weiboOauth;
    }

}
