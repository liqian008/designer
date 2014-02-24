package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.dao.IAlbumCounterDao;
import com.bruce.designer.dao.mapper.AlbumCounterMapper;
import com.bruce.designer.model.AlbumCounter;
import com.bruce.designer.model.AlbumCounterCriteria;
import com.bruce.designer.model.AlbumCriteria;

@Repository
public class AlbumCounterDaoImpl implements IAlbumCounterDao{

	@Autowired
	private AlbumCounterMapper albumCounterMapper;
	
	
	public int save(AlbumCounter t) {
	    return albumCounterMapper.insertSelective(t);
	}

	public int updateById(AlbumCounter t) {
		return albumCounterMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return albumCounterMapper.deleteByPrimaryKey(id);
	}

	public AlbumCounter loadById(Integer id) {
	    return albumCounterMapper.selectByPrimaryKey(id);
	}

	public List<AlbumCounter> queryAll() {
		return albumCounterMapper.selectByExample(null);
	}

	@Override
	public AlbumCounter loadByAlbumId(int albumId) {
		AlbumCounter albumCounter = null;
		AlbumCounterCriteria criteria = new AlbumCounterCriteria();
        criteria.createCriteria().andAlbumIdEqualTo(albumId);
        List<AlbumCounter> albumCounterList = albumCounterMapper.selectByExample(criteria);
        if(albumCounterList!=null&&albumCounterList.size()==1){
        	albumCounter =  albumCounterList.get(0);
        }else{//TODO 无数据，需新增并保存
        	albumCounter = new AlbumCounter();
        	albumCounter.setAlbumId(albumId);
        }
        return albumCounter;
	}
	
	@Override
	public List<AlbumCounter> fallLoadList(Integer tailId, int limit) {
		return null;
	}


}
