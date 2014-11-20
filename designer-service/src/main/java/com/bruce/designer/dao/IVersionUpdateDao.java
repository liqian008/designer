package com.bruce.designer.dao;

import com.bruce.designer.model.VersionUpdate;
import com.bruce.designer.model.VersionUpdateCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface IVersionUpdateDao extends IFoundationService<VersionUpdate, Integer, VersionUpdateCriteria> {

	public VersionUpdate loadByClientInfo(short clientType,int versionCode, String channel);
	
}
