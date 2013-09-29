package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.Album;
import com.bruce.designer.bean.AlbumCriteria;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.AlbumMapper;
import com.bruce.designer.service.IAlbumService;

@Service
public class AlbumServiceImpl implements IAlbumService {

	@Autowired
	private AlbumMapper albumMapper;

	public int save(Album t) {
		return albumMapper.insert(t);
	}

	public List<Album> queryAll() {
		return albumMapper.selectByExample(null);
	}

	public int updateById(Album t) {
		return albumMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return albumMapper.deleteByPrimaryKey(id);
	}

	public Album loadById(Integer id) {
		return albumMapper.selectByPrimaryKey(id);
	}

	public List<Album> queryAlbumByStatus(short status) {
		AlbumCriteria criteria = new AlbumCriteria();
		criteria.createCriteria().andStatusEqualTo(status);
		criteria.setOrderByClause("id desc");
		return albumMapper.selectByExample(criteria);
	}

	public List<Album> queryAlbumByUserId(int userId) {
		AlbumCriteria criteria = new AlbumCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(ConstService.ALBUM_OPEN_STATUS);
		criteria.setOrderByClause("id desc");
		return albumMapper.selectByExample(criteria);
	}

}
