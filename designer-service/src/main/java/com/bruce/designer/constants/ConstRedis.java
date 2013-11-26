package com.bruce.designer.constants;

/**
 * Redis常量
 * @author liqian
 *
 */
public interface ConstRedis{
    /*Redis全局命名空间*/
    public static final String REDIS_NAMESPACE = "designer";
    
    /*用户粉丝数量*/
    public static final String COUNTER_KEY_FAN = "fan_count_";
    /*用户关注数量*/
    public static final String COUNTER_KEY_FOLLOW = "follow_count_";
    /*用户收藏数量*/
    public static final String COUNTER_KEY_FAVORITE = "favorite_count_";
    
    
    /*用户总计被浏览数的key*/
    public static final String COUNTER_KEY_USER_TOTAL_BROWSE = "user_total_browse_";
    /*用户总计被评论数的key*/
    public static final String COUNTER_KEY_USER_TOTAL_COMMENT = "user_total_comment_";
    /*用户总计被喜欢数的key*/
    public static final String COUNTER_KEY_USER_TOTAL_LIKE = "user_total_like_";
    /*用户总计被收藏数的key*/
    public static final String COUNTER_KEY_USER_TOTAL_FAVORITE = "user_total_favorite_";
    
    /*整个作品辑浏览数的key*/
    public static final String COUNTER_KEY_ALBUM_BROWSE = "album_browse_";
    /*整个作品辑评论数的key*/
    public static final String COUNTER_KEY_ALBUM_COMMENT = "album_comment_";
    /*整个作品辑喜欢数的key*/
    public static final String COUNTER_KEY_ALBUM_LIKE = "album_like_";
    /*整个作品辑收藏数的key*/
    public static final String COUNTER_KEY_ALBUM_FAVORITE = "album_favorite_";
    
//    /*单个作品浏览数的key*/
//    public static final String COUNTER_KEY_ALBUMSLIDE_BROWSE = "albumslide_browse_";
//    /*单个作品评论数的key*/
//    public static final String COUNTER_KEY_ALBUMSLIDE_COMMENT = "albumslide_comment_";
//    /*单个作品喜欢数的key*/
//    public static final String COUNTER_KEY_ALBUMSLIDE_LIKE = "albumslide_like_";
    
	
}
