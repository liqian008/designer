/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.service;

/**
 * 计数服务
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-8-11 下午12:07:34
 */
public interface IAlbumCounterService { 

    public long incrBrowser(int designerId, int albumId, int userId);
    
    public long incrComment(int designerId, int albumId, int userId);
    
    public long incrLike(int designerId, int albumId, int userId);
    
    public long incrFavorite(int designerId, int albumId, int userId);
    
    
    public long reduceBrowser(int designerId, int albumId, int userId);
    
    public long reduceComment(int designerId, int albumId, int userId);
    
    public long reduceLike(int designerId, int albumId, int userId);
    
    public long reduceFavorite(int designerId, int albumId, int userId);
    
    //删除作品时扣减热门计数
    public boolean removeAlbum(int designer, int albumId);

    //计算专辑的访问数据
    public long getBrowseCount(int albumId);
    
    public long getCommentCount(int albumId);
    
    public long getLikeCount(int albumId);
    
    public long getFavoriteCount(int albumId);
    
}
