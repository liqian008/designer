package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.TagAlbum;

public interface ITagAlbumService extends IBaseService<TagAlbum, Long>{ 
    
	
	/**
	 * 
	 * @param albumId
	 * @return
	 */
	public List<Integer> queryTagIdsByAlbumId(int albumId);
	
	/**
	 * 根据tagId加载albumId，瀑布流方式加载
	 * @param tagId
	 * @param albumsTailId
	 * @param limit
	 * @return
	 */
	public List<Integer> fallLoadAlbumIdList(int tagId, int albumsTailId, int limit);

	/**
	 * 根据tagId查询引用数量
	 * @param tagId
	 * @return
	 */
    public int getReferenceNum(int tagId);

    /**
     * 批量创建
     * @param albumId
     * @param tagIdList
     * @return
     */
    public int batchSave(int albumId, List<Integer> tagIdList);
    
    /**
     * 批量创建
     * @param tagAlbumList
     * @return
     */
    @Deprecated
    public int batchSave(List<TagAlbum> tagAlbumList);
    
    /**
     * 删除作品关联的tag
     * @param albumId
     * @return
     */
	int deleteByAlbumId(int albumId);
    

} 
