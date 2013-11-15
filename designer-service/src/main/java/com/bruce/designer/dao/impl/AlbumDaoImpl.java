package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumCriteria;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IAlbumDao;
import com.bruce.designer.dao.mapper.AlbumMapper;

@Repository
public class AlbumDaoImpl implements IAlbumDao{

	@Autowired
	private AlbumMapper albumMapper;
	
	
	public int save(Album t) {
	    return albumMapper.insertSelective(t);
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

	public List<Album> queryAll() {
		return albumMapper.selectByExample(null);
	}
	
	public int deleteUserAlbum(int userId, int albumId) {
	    AlbumCriteria criteria = new AlbumCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId).andIdEqualTo(albumId);
        return albumMapper.deleteByExample(criteria);
    }
	
	public List<Album> queryList(int start, int limit){
	    AlbumCriteria criteria = new AlbumCriteria();
        criteria.createCriteria();
        criteria.setStart(start);
        criteria.setLimit(limit);
        criteria.setOrderByClause("id desc");
        List<Album> albumList = albumMapper.selectByExample(criteria);
        return albumList;
	}
	
	
	
	
//	public PagingData<Album> pagingQuery(short status, int pageNo, int pageSize){
//		if(pageNo<0) pageNo = 1;
//		int offset = (pageNo-1) * pageSize;
//		AlbumCriteria criteria = new AlbumCriteria();
//		criteria.createCriteria().andStatusEqualTo(status);
//		criteria.setOffset(offset);
//		criteria.setLimit(pageSize);
//		criteria.setOrderByClause("id desc");
//		List<Album> albumList = albumMapper.selectByExample(criteria);
//		int totalCount = albumMapper.countByExample(criteria);//总条数
//		PagingData<Album> pagingData = new PagingData<Album>(albumList, totalCount, pageNo, pageSize);
//		return pagingData;
//	}
	
	@Override
	public java.util.List<Album> queryAlbumByIds(java.util.List<Integer> idList) {
		AlbumCriteria criteria = new AlbumCriteria();
		criteria.createCriteria().andStatusEqualTo(ConstService.ALBUM_OPEN_STATUS).andIdIn(idList);
		//criteria.setOrderByClause("id desc");
		return albumMapper.selectByExample(criteria);
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
	
	/**
	 * 瀑布流方式加载更多数据
	 */
	@Override
	public List<Album> fallLoadList(Integer tailId, int limit) {
		return fallLoadDesignerAlbums(null, tailId, limit);
	}
	
	
	/**
	 * 瀑布流方式加载更多更多关注作品
	 */
	@Override
	public List<Album> fallLoadDesignerAlbums(List<Integer> designerIdList, int albumsTailId, int limit) {
		AlbumCriteria criteria = new AlbumCriteria();
		AlbumCriteria.Criteria subCriteria = criteria.createCriteria();
		if(designerIdList!=null&&designerIdList.size()>0){
			subCriteria.andUserIdIn(designerIdList);
		}
		if(albumsTailId>0){
			subCriteria.andIdLessThan(albumsTailId);
		}
		criteria.setLimit(limit);
	    criteria.setOrderByClause("id desc");
        List<Album> albumList = albumMapper.selectByExample(criteria);
        return albumList;
	}


}
