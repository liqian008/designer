/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.macp.api.Config;
import com.bruce.designer.model.VersionUpdate;
import com.bruce.designer.service.IVersionUpdateService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.entity.VersionCheckResult;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.model.result.ApiResult;

/**
 * @author liqian
 * 
 */
@Component
public class SystemCheckCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(SystemCheckCommand.class);
    
    private PassportService passportService;
    @Autowired
    private IVersionUpdateService versionUpdateService;

    @Override
    public void afterPropertiesSet() throws Exception {
//    	Assert.notNull(passportService, "passportService is required!");
        Assert.notNull(versionUpdateService, "versionUpdateService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	String channel = context.getStringParams().get("channel");
    	String clientTypeStr = context.getStringParams().get("clientType");
    	String versionCodeStr = context.getStringParams().get("versionCode");
    	
    	short clientType = NumberUtils.toShort(clientTypeStr, (short)0);
    	int versionCode = NumberUtils.toInt(versionCodeStr, 0);
    	
    	VersionUpdate versionUpdate = versionUpdateService.loadByClientInfo(clientType, versionCode, channel);
    	
    	VersionCheckResult versionCheckResult = null;//版本检查结果
    	if(versionUpdate!=null){
			versionCheckResult = new VersionCheckResult(
					versionUpdate.getUpdateStatus(),
					versionUpdate.getVersionTitle(),
					versionUpdate.getVersionDescription(),
					versionUpdate.getUpdateUrl(), versionUpdate.getAgreeText(),
					versionUpdate.getDeniedText());
    	}else{
    		versionCheckResult = new VersionCheckResult(0, null, null, null, null,null);
    	}
    	rt.put("versionCheckResult", versionCheckResult);

    	boolean needLogin = true;
    	int userId = context.getUserId();
    	if(userId>0 && userId!=Config.GUEST_ID){//游客身份
    		needLogin = false;
    	}
    	rt.put("needLogin", needLogin);
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

	public PassportService getPassportService() {
		return passportService;
	}

	public void setPassportService(PassportService passportService) {
		this.passportService = passportService;
	}

	public IVersionUpdateService getVersionUpdateService() {
		return versionUpdateService;
	}

	public void setVersionUpdateService(IVersionUpdateService versionUpdateService) {
		this.versionUpdateService = versionUpdateService;
	}
    
	
	
}
