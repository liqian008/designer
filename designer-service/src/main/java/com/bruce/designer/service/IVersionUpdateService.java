package com.bruce.designer.service;

import com.bruce.designer.model.VersionUpdate;
import com.bruce.designer.model.VersionUpdateCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface IVersionUpdateService extends IFoundationService<VersionUpdate, Integer, VersionUpdateCriteria> {

//	public int deleteByAlbumId(int albumId);
//	
//	public int setCover(int userId, int albumId, int albumSlideId);
//	 
//	public List<VersionUpdate> querySlidesByAlbumId(int albumId);
	
	public VersionUpdate loadByClientInfo(short clientType,int versionCode, String channel);
	
	
}
