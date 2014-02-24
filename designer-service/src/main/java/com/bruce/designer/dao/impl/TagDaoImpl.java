package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.ITagDao;
import com.bruce.designer.dao.mapper.TagMapper;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.model.Tag;
import com.bruce.designer.model.TagCriteria;

@Repository
public class TagDaoImpl implements ITagDao, InitializingBean {

	@Autowired
	private TagMapper tagMapper;

	public int save(Tag t) {
		return tagMapper.insertSelective(t);
	}

	public List<Tag> queryAll() {
		return tagMapper.selectByExample(null);
	}

	public int updateById(Tag t) {
		return tagMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return tagMapper.deleteByPrimaryKey(id);
	}

	public Tag loadById(Integer id) {
		return tagMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Tag> fallLoadList(Integer tailId, int limit) {
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public int getTagIdByName(String tagName) {
		TagCriteria criteria = new TagCriteria();
		criteria.createCriteria().andNameEqualTo(tagName);
		List<Tag> tagList = tagMapper.selectByExample(criteria);
		if(tagList!=null&&tagList.size()>0){
			return tagList.get(0).getId();
		}
		throw new DesignerException(ErrorCode.TAG_NOT_EXISTS);
	}

	@Override
	public String getTagNameById(int tagId) {
		TagCriteria criteria = new TagCriteria();
		criteria.createCriteria().andIdEqualTo(tagId);
		List<Tag> tagList = tagMapper.selectByExample(criteria);
		if(tagList!=null&&tagList.size()>0){
			return tagList.get(0).getName();
		}
		return null;
	}
	
	/**
	 * 计算热门Tag
	 */
	@Override
	public List<Tag> calcHotTags(int limit){
		return tagMapper.calcHotTags(limit);
	}

}
