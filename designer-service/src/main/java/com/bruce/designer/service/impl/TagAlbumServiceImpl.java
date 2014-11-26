package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.dao.ITagAlbumDao;
import com.bruce.designer.model.TagAlbum;
import com.bruce.designer.model.TagAlbumCriteria;
import com.bruce.designer.service.ITagAlbumService;
import com.bruce.designer.service.IUserService;

@Service
public class TagAlbumServiceImpl implements ITagAlbumService, InitializingBean {

	@Autowired
	private ITagAlbumDao tagAlbumDao;
	@Autowired
	private IUserService userService;

	public int save(TagAlbum t) {
		return tagAlbumDao.save(t);
	}

	public List<TagAlbum> queryAll() {
		return tagAlbumDao.queryAll();
	}

	public int updateById(TagAlbum t) {
		return tagAlbumDao.updateById(t);
	}

	public int deleteById(Long id) {
		return tagAlbumDao.deleteById(id);
	}

	public TagAlbum loadById(Long id) {
		return tagAlbumDao.loadById(id);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(tagAlbumDao, "tagAlbumDao can't be null");
		Assert.notNull(userService, "userService can't be null");
	}
	@Override
	public int deleteByAlbumId(int albumId) {
		return tagAlbumDao.deleteByAlbumId(albumId);
	}
	
	/**
	 * 查询专辑对应的tagIds
	 */
	@Override
	public List<Integer> queryTagIdsByAlbumId(int albumId) {
		List<Integer> tagIdList = null;
		List<TagAlbum> dataList = tagAlbumDao.queryTagIdsByAlbumId(albumId);
		if(dataList!=null&&dataList.size()>0){
			tagIdList = new ArrayList<Integer>();
			for(TagAlbum tagAlbum: dataList){
				tagIdList.add(tagAlbum.getTagId());
			}
		}
		return tagIdList;
	}
	
	@Override
	public List<Integer> fallLoadAlbumIdList(int tagId, int albumsTailId, int limit) {
		List<Integer> albumIdList = null;
		List<TagAlbum> dataList = tagAlbumDao.fallLoadDataList(tagId, albumsTailId, limit);
		if(dataList!=null&&dataList.size()>0){
			albumIdList = new ArrayList<Integer>();
			for(TagAlbum tagAlbum: dataList){
				albumIdList.add(tagAlbum.getAlbumId());
			}
		}
		return albumIdList;
	}
	

	@Override
	public int getReferenceNum(int tagId) {
		return 0;
	}

	
	@Override
	public int batchSave(int albumId, List<Integer> tagIdList) {
		//创建新tag关联
		if(tagIdList!=null&&tagIdList.size()>0){
			for(Integer tagId: tagIdList){
				TagAlbum tagAlbum = new TagAlbum();
				tagAlbum.setAlbumId(albumId);
				tagAlbum.setTagId(tagId);
				tagAlbum.setCreateTime(new Date(System.currentTimeMillis()));
				save(tagAlbum);
			}
			return tagIdList.size();
		}
		return 0;
	}
	
	@Deprecated
	@Override
	public int batchSave(List<TagAlbum> tagAlbumList) {
		return 0;
	}
	

	
	@Override
	public int updateByCriteria(TagAlbum t, TagAlbumCriteria criteria) {
		return tagAlbumDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(TagAlbumCriteria criteria) {
		return tagAlbumDao.deleteByCriteria(criteria);
	}

	@Override
	public List<TagAlbum> queryAll(String orderByClause) {
		return tagAlbumDao.queryAll(orderByClause);
	}

	@Override
	public List<TagAlbum> queryByCriteria(TagAlbumCriteria criteria) {
		return tagAlbumDao.queryByCriteria(criteria);
	}
	
	@Override
	public int countByCriteria(TagAlbumCriteria criteria) {
		return tagAlbumDao.countByCriteria(criteria);
	}
}
