package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.TbAlbum;
import com.bruce.designer.bean.TbAlbumCriteria;
<<<<<<< HEAD
import com.bruce.designer.constant.ConstService;
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
		criteria.createCriteria().andStatusEqualTo(status);
		criteria.setOrderByClause("id desc");
		return albumMapper.selectByExample(criteria);
	}

	public List<TbAlbum> queryAlbumByUserId(int userId) {
		TbAlbumCriteria criteria = new TbAlbumCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(ConstService.ALBUM_OPEN_STATUS);
		criteria.setOrderByClause("id desc");
=======
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
>>>>>>> branch 'master' of https://github.com/liqian008/designer.git
		return albumMapper.selectByExample(criteria);
	}

}
