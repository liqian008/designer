package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.ITagDao;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.Tag;
import com.bruce.designer.model.TagAlbum;
import com.bruce.designer.model.TagCriteria;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.ITagAlbumService;
import com.bruce.designer.service.ITagService;
import com.bruce.designer.service.IUserService;

@Service
public class TagServiceImpl implements ITagService, InitializingBean {
	
	@Autowired
	private ITagDao tagDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private ITagAlbumService tagAlbumService;
	
//	private List<Tag> hotTagList = null;

	public int save(Tag t) {
		return tagDao.save(t);
	}

	public List<Tag> queryAll() {
		return tagDao.queryAll();
	}

	public int updateById(Tag t) {
		return tagDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return tagDao.deleteById(id);
	}

	public Tag loadById(Integer id) {
		return tagDao.loadById(id);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(tagDao, "tagDao can't be null");
		Assert.notNull(userService, "userService can't be null");
	}

	/**
	 * 根据作品id查询对应的tag列表
	 */
	@Override
	public List<String> getTagNamesByAlbumId(int albumId) {
		List<String> tagNameList = null;
		List<Integer> tagIdList = tagAlbumService.queryTagIdsByAlbumId(albumId);
		if(tagIdList!=null&&tagIdList.size()>0){
			tagNameList = getTagNamesByIds(tagIdList);
		}
		return tagNameList;
	}
	
	/**
	 * 发布作品时补齐标签
	 */
	@Override
	public int tagAlbum(int albumId, List<String> tagNameList) {
		//删除作品的原tag关联
		tagAlbumService.deleteByAlbumId(albumId);
		// 根据tagNameList获取对应的ids
		List<Integer> tagIdList = getTagIdsByNames(tagNameList);
		return tagAlbumService.batchSave(albumId, tagIdList);
	}
	
//	// TODO 同步处理&优化
//	@Override
//	public List<Tag> getHotTags(int limit) {
//		if(hotTagList==null||hotTagList.size()<=0){
//			List<Tag> calcTagList = calcHotTags(limit);
//			hotTagList = calcTagList;
//		}
//		return hotTagList;
//	}
//	
//	// TODO 同步处理&优化
//	@Override
//	public List<Tag> calcHotTags(int limit) {
//		List<Tag> calcTagList = tagDao.calcHotTags(limit);
//		if(calcTagList!=null){
//			hotTagList = calcTagList;
//		}
//		return hotTagList;
//	}

	private List<String> getTagNamesByIds(List<Integer> tagIdList) {
		if (tagIdList != null && tagIdList.size() > 0) {
			List<String> tagNameList = new ArrayList<String>();
			for (int tagId : tagIdList) {
				// 根据tagId查询tagname
				String tagName = getTagNameById(tagId);
				if(tagName!=null){
					tagNameList.add(tagName);
				}
			}
			return tagNameList;
		} else {
			throw new DesignerException(ErrorCode.TAG_EMPTY);
		}
	}
	
	private List<Integer> getTagIdsByNames(List<String> tagNameList) {
		if (tagNameList != null && tagNameList.size() > 0) {
			List<Integer> tagIdList = new ArrayList<Integer>();
			for (String tagName : tagNameList) {
				// 根据tagname查询tagId
				int tagId = getTagIdByName(tagName, true);
				if(tagId>0){
					tagIdList.add(tagId);
				}
			}
			return tagIdList;
		} else {
			throw new DesignerException(ErrorCode.TAG_EMPTY);
		}
	}

	/**
	 * 根据tagName查找tagId
	 */
	@Override
	public int getTagIdByName(String tagName, boolean createNew) {
		
		int tagId = 0;
		if(StringUtils.isBlank(tagName)){
			//tagName为空，直接返回0
			return tagId;
		}
		// 检查tagName是否存在
		try {
			tagId = tagDao.getTagIdByName(tagName.trim());
		} catch (DesignerException e) {
			// TODO log this
				if(createNew){
				// tagName不存在，需新建
				Tag tag = new Tag();
				tag.setName(tagName);
				tag.setCreateTime(new Date(System.currentTimeMillis()));
				int result = tagDao.save(tag);
				if (result > 0) {
					tagId = tag.getId();
				}
			}
		}
		return tagId;
	}

	@Override
	public String getTagNameById(int tagId) {
		String tagName = tagDao.getTagNameById(tagId);
		return tagName;
	}

}
