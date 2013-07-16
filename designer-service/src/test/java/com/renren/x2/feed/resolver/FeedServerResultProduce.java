package com.renren.x2.feed.resolver;

import java.util.List;

import com.renren.x2.common.BaseResult;
import com.renren.x2.common.ErrorCode;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;
import com.renren.x2.feedapi.result.FeedListResult;
import com.renren.x2.feedapi.result.FeedResult;
import com.renren.x2.feedapi.result.UgcListResult;
import com.renren.x2.feedapi.result.UgcPostResult;

/**
 * @author <a href="mailto:yongshuai.yan@renren-inc.com">阎勇帅</a>
 * @version 2012-10-25 下午02:46:17
 */
public class FeedServerResultProduce {

    public BaseResult produceBaseResult(BaseResult result) {
        result.setCode(ErrorCode.SystemSuccess);
        result.setErrorMessage(ErrorCode.SystemSuccess.name());
        return result;

    }

    public UgcPostResult producePostResult(long feedId, long ugcId, long createTime) {
        UgcPostResult result = new UgcPostResult();
        result = (UgcPostResult) produceBaseResult(result);
        result.setFeedId(feedId);
        result.setUgcId(ugcId);
        result.setCreateTime(createTime);
        return result;

    }
    
    public UgcPostResult producePostResult(Feed feed) {
        UgcPostResult result = new UgcPostResult();
        result = (UgcPostResult) produceBaseResult(result);
        result.setFeedId(feed.getFeedId());
        result.setUgcId(feed.getNativeUgc().getUgcId());
        result.setCreateTime(feed.getCreateTime());
        return result;

    }

    public FeedResult produceFeedResult(Feed feed) {
        FeedResult result = new FeedResult();
        result = (FeedResult) produceBaseResult(result);
        result.setFeed(feed);
        return result;
    }

    public FeedListResult produceFeedListResult(List<Feed> feeds) {
        FeedListResult result = new FeedListResult();
        result = (FeedListResult) produceBaseResult(result);
        result.setFeedList(feeds);
        return result;

    }

    public UgcListResult produceUgcListResult(List<Ugc> ugcs) {
        UgcListResult result = new UgcListResult();
        result = (UgcListResult) produceBaseResult(result);
        result.setUgcList(ugcs);
        return result;

    }
}
