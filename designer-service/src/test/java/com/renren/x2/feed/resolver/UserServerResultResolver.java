package com.renren.x2.feed.resolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.renren.x2.common.BaseResult;
import com.renren.x2.common.ErrorCode;
import com.renren.x2.feed.constants.FeedErrorEnum;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feedapi.model.UgcUser;
import com.renren.x2.user.BaseUserInfo;
import com.renren.x2.user.HeadUrlInfo;
import com.renren.x2.user.SimpleUser;
import com.renren.x2.user.SimpleUserInfo;
import com.renren.x2.user.SimpleUserInfosResult;
import com.renren.x2.user.UserIdsResult;
import com.renren.x2.user.UserInfo;
import com.renren.x2.user.UserInfoResult;

/**
 * @author <a href="mailto:yongshuai.yan@renren-inc.com">阎勇帅</a>
 * @version 2012-10-25 下午12:16:05
 */
public class UserServerResultResolver {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(UserServerResultResolver.class);

    /**
     * 处理BaseResult
     * 
     * @throws FeedException
     */
    public void resolveBaseResult(BaseResult baseResult) throws FeedException {
        if (baseResult.getCode() != ErrorCode.SystemSuccess) {
            ErrorCode errorCode = baseResult.getCode();
            String errorMsg = baseResult.getErrorMessage();
            logger.error("User system error! errorCode = " + errorCode.name() + " errorMsg = " + errorMsg);
            switch (errorCode) {
                case SystemError:
                    ;
                default:
                    ;
            }
            throw new FeedException(FeedErrorEnum.USER_SYSTEM_ERROR);
        }
    }

    public UgcUser resolveUserInfoResult2User(UserInfoResult result) throws FeedException {
        resolveBaseResult(result);
        UgcUser user = new UgcUser();
        UserInfo userInfo = result.getUser();
        BaseUserInfo baseUserInfo = userInfo.getBaseInfo();
        userInfo.getSchoolInfos();
        if (null != baseUserInfo) {
            user.setUserId(baseUserInfo.getUserId());
            user.setName(baseUserInfo.getName());
            user.setGender(baseUserInfo.getGender());
        }
        HeadUrlInfo urlInfo = userInfo.getHeadUrl();
        if (null != urlInfo) {
           String headUrl = userInfo.getHeadUrl().getSmallUrl();
            user.setHeadUrl(headUrl);
        }
        return user;
    }
    
    public List<UgcUser> resolveSimpleUserInfosResult2User(SimpleUserInfosResult result) throws FeedException {
        resolveBaseResult(result);
        List<SimpleUserInfo> list = result.getUserList();
        List<UgcUser> ugcUserList = new ArrayList<UgcUser>();
        for(SimpleUserInfo simpleUserInfo : list){
            SimpleUser simpleUser = simpleUserInfo.getUserProfile();
            int userId = simpleUser.getUserId();
            int gender = simpleUser.getGender();
            String name = simpleUser.getName();
            String smallUrl = simpleUser.getHeadUrl();
            UgcUser user = new UgcUser(userId, name, gender, smallUrl);
            ugcUserList.add(user);
        }
        return ugcUserList;
    }

    public List<Integer> resolveSimpleUserInfosResult2User(UserIdsResult result) throws FeedException {
        resolveBaseResult(result);
        List<Integer> userIds = result.getUserIds();
        Iterator<Integer> itr = userIds.iterator();
        while(itr.hasNext()){
            Integer userId = itr.next();
            if(userId == null || userId <= 0)
                itr.remove();
        }
        return userIds;
    }

}
