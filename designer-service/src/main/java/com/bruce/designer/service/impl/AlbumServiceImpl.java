package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.TbAlbum;
import com.bruce.designer.bean.TbAlbumCriteria;
import com.bruce.designer.dao.TbAlbumMapper;
import com.bruce.designer.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private TbAlbumMapper albumMapper;

	public int save(TbAlbum t) {
		return albumMapper.insert(t);
	}

	public List<TbAlbum> queryAll() {
		return albumMapper.selectByExample(null);
	}

	public int updateById(TbAlbum t) {
		return albumMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return albumMapper.deleteByPrimaryKey(id);
	}

	public TbAlbum loadById(Integer id) {
		return albumMapper.selectByPrimaryKey(id);
	}

	public List<TbAlbum> queryAlbumByStatus(short status) {
		TbAlbumCriteria criteria = new TbAlbumCriteria();
		criteria.setOrderByClause("create_time desc");
		criteria.createCriteria().andStatusEqualTo(status);
		return albumMapper.selectByExample(criteria);
	}

}
