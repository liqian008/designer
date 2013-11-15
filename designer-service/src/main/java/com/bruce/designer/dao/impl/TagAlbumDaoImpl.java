package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.ITagAlbumDao;
import com.bruce.designer.dao.mapper.TagAlbumMapper;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumCriteria;
import com.bruce.designer.model.TagAlbum;
import com.bruce.designer.model.TagAlbumCriteria;

@Repository
public class TagAlbumDaoImpl implements ITagAlbumDao, InitializingBean { 
    
    @Autowired
    private TagAlbumMapper tagAlbumMapper;
    
    public int save(TagAlbum t) {
        return tagAlbumMapper.insertSelective(t);
    }

    public List<TagAlbum> queryAll() {
        return tagAlbumMapper.selectByExample(null);
    }

    public int updateById(TagAlbum t) {
        return tagAlbumMapper.updateByPrimaryKeySelective(t);
    }

    public int deleteById(Long id) {
        return tagAlbumMapper.deleteByPrimaryKey(id);
    }

    public TagAlbum loadById(Long id) {
        return tagAlbumMapper.selectByPrimaryKey(id);
    }

	@Override
	public List<TagAlbum> fallLoadList(Long albumTailId, int limit) {
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
	
	@Override
	public List<TagAlbum> fallLoadDataList(int tagId, int albumsTailId, int limit) {
		TagAlbumCriteria criteria = new TagAlbumCriteria();
		TagAlbumCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andTagIdEqualTo(tagId);
		if(albumsTailId>0){
			subCriteria.andAlbumIdLessThan(albumsTailId);
		}
		criteria.setLimit(limit);
	    criteria.setOrderByClause("id desc");
        List<TagAlbum> albumList = tagAlbumMapper.selectByExample(criteria);
        return albumList;
	}

	@Override
	public List<TagAlbum> queryTagIdsByAlbumId(int albumId) {
		TagAlbumCriteria criteria = new TagAlbumCriteria();
		TagAlbumCriteria.Criteria subCriteria = criteria.createCriteria().andAlbumIdEqualTo(albumId);
	    criteria.setOrderByClause("id desc");
        List<TagAlbum> albumList = tagAlbumMapper.selectByExample(criteria);
        return albumList;
	}

} 
