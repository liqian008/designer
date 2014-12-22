/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstWeixin;
import com.bruce.designer.model.User;
import com.bruce.designer.model.VersionUpdate;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.IVersionUpdateService;
import com.bruce.designer.util.ConfigUtil;
import com.bruce.designer.util.UserUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.entity.VersionCheckResult;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.model.result.ApiResult;
import com.bruce.foundation.model.share.GenericSharedInfo;
import com.bruce.foundation.model.share.GenericSharedInfo.WxSharedInfo;
import com.bruce.foundation.util.JsonUtil;

/**
 * 检查版本更新&用户登录
 * @author liqian
 *
 */
@Component
public class SystemCheckCommand extends AbstractApiCommand implements InitializingBean {
	
	
	private static final String APP_WX_SHARE_TITLE = ConfigUtil.getString("app_wxshare_title");
	private static final String APP_WX_SHARE_CONTENT = ConfigUtil.getString("app_wxshare_content");
	private static final String APP_WX_SHARE_ICON_URL = ConfigUtil.getString("app_wxshare_icon_url");
	private static final String APP_WX_SHARE_LINK = ConfigUtil.getString("app_wxshare_link");
	/*是否显示价格*/
	private static final boolean ALBUM_SHOW_PRICE = BooleanUtils.toBoolean(ConfigUtil.getString("album_show_price"));
	
    private static final Log logger = LogFactory.getLog(SystemCheckCommand.class);
    
    @Autowired
    private PassportService passportService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IVersionUpdateService versionUpdateService;

    @Override
    public void afterPropertiesSet() throws Exception {
//    	Assert.notNull(passportService, "passportService is required!");
        Assert.notNull(versionUpdateService, "versionUpdateService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	int hostId = context.getUserId();
    	
    	Map<String, Object> dataMap = new HashMap<String, Object>();
    	//客户端的基本参数
    	String clientTypeStr = context.getStringParams().get("client_type");
    	String channel = context.getStringParams().get("client_channel");
    	String versionCodeStr = context.getStringParams().get("v_code");
    	
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
    	dataMap.put("versionCheckResult", versionCheckResult);
    	dataMap.put("showPrice", ALBUM_SHOW_PRICE);
    	
    	GenericSharedInfo appSharedInfo = new GenericSharedInfo();
    	appSharedInfo.setWxSharedInfo(new WxSharedInfo(APP_WX_SHARE_TITLE, APP_WX_SHARE_CONTENT, APP_WX_SHARE_ICON_URL, APP_WX_SHARE_LINK));
    	dataMap.put("appSharedInfo", appSharedInfo);
    	if(logger.isDebugEnabled()){
    		logger.debug("应用分享文案："+JsonUtil.gson.toJson(appSharedInfo));
    	}
    	
    	boolean needLogin = true;
    	
    	if(!UserUtil.isGuest(hostId)){//正常登录用户
    		needLogin = false;
    		User hostUser = userService.loadById(hostId, true);
    		dataMap.put("hostUser", hostUser);
    	}
    	dataMap.put("needLogin", needLogin);
    	dataMap.put("wxmpQrcodeUrl", ConstWeixin.WEIXINMP_QRCODE_URL);//微信公众帐号的url
        return ResponseBuilderUtil.buildSuccessResult(dataMap);
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
