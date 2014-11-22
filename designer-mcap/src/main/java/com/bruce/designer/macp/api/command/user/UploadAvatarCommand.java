/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.bruce.designer.model.upload.UploadImageInfo;
import com.bruce.designer.model.upload.UploadImageResult;
import com.bruce.designer.service.IAlbumCounterService;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.upload.IUploadService;
import com.bruce.designer.service.upload.impl.UploadQiniuServiceImpl;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 用户上传资料
 * 
 * @author liqian
 * 
 */
@Component
public class UploadAvatarCommand extends AbstractApiCommand implements InitializingBean {

	private static final Log logger = LogFactory.getLog(UploadAvatarCommand.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserGraphService userGraphService;
	@Autowired
	private IAlbumCounterService albumCounterService;
	@Autowired
	private UploadQiniuServiceImpl uploadQiniuService;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(userService, "userService is required!");
	}

	@Override
	public ApiResult onExecute(ApiCommandContext context) {

		int hostId = context.getUserId();

		Map<String, Object> rt = new HashMap<String, Object>();

		System.out.println("用户[" + hostId + "]上传头像");
		if (logger.isDebugEnabled()) {
			logger.debug("用户[" + hostId + "]上传头像");
		}

		Map<String, MultipartFile> multipartMap = context.getBinaryParams();
		if (multipartMap != null && multipartMap.size() > 0) {
			MultipartFile avatarFile = multipartMap.get("image");
			if (avatarFile != null) {
				try {
					System.out.println("上传字节数: "+avatarFile.getBytes()+"字节");
					if (logger.isDebugEnabled()) {
						logger.debug("上传字节数: "+avatarFile.getBytes()+"字节");
					}
					int result = 0;
					UploadImageResult avatarUploadResult = uploadQiniuService.uploadAvatar(avatarFile.getBytes(), String.valueOf(hostId), IUploadService.IMAGE_SPEC_LARGE,
							IUploadService.IMAGE_SPEC_MEDIUM, IUploadService.IMAGE_SPEC_SMALL);
					if(avatarUploadResult!=null){
						UploadImageInfo uploadImageInfo = avatarUploadResult.getUploadImageMap().get("original");
						if(uploadImageInfo!=null&&!StringUtils.isBlank(uploadImageInfo.getUrl())){
							//更新用户头像
							result = userService.updateAvatar(hostId, uploadImageInfo.getUrl());
						}
					}
					System.out.println("上传头像result: "+ result);
					
					if (result>0) {
						rt.put("avatar", avatarUploadResult.getUploadImageMap());
						return ResponseBuilderUtil.buildSuccessResult(rt);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ResponseBuilderUtil.buildErrorResult();
	}

}
