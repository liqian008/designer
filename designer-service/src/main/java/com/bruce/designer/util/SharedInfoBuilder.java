package com.bruce.designer.util;


import org.apache.commons.lang3.StringUtils;

import com.bruce.designer.model.Album;
import com.bruce.foundation.model.share.GenericSharedInfo;

public class SharedInfoBuilder {
	
	/**
	 * 初始化通用的分享对象
	 * @param album
	 * @return
	 */
	public static GenericSharedInfo buildGenericSharedInfo(Album album) {
		if(album!=null){
			GenericSharedInfo generalSharedInfo = new GenericSharedInfo();
			//微信分享对象
			if(!StringUtils.isBlank(album.getTitle())&&!StringUtils.isBlank(album.getRemark())&&!StringUtils.isBlank(album.getLink())&&!StringUtils.isBlank(album.getCoverSmallImg())){
//				GenericSharedInfo.WxShareInfo generalWxShareInfo = new GenericSharedInfo.WxShareInfo(album.getTitle(), album.getRemark(),album.getCoverSmallImg(), album.getItemMobileUrl());
				GenericSharedInfo.WxSharedInfo generalWxShareInfo = new GenericSharedInfo.WxSharedInfo(album.getTitle(), album.getRemark(),album.getCoverSmallImg(), DesignerLinkUtil.getAlbumLink4Mobile(album.getId()));
				generalSharedInfo.setWxSharedInfo(generalWxShareInfo);
			}
			//微博分享对象
			if(!StringUtils.isBlank(album.getTitle())&&!StringUtils.isBlank(album.getRemark())&&!StringUtils.isBlank(album.getLink())&&!StringUtils.isBlank(album.getCoverSmallImg())){
				GenericSharedInfo.WeiboSharedInfo generalWeiboShareInfo = new GenericSharedInfo.WeiboSharedInfo(album.getTitle(), album.getRemark(),album.getCoverSmallImg(), DesignerLinkUtil.getAlbumLink4Mobile(album.getId()));
				generalSharedInfo.setWeiboSharedInfo(generalWeiboShareInfo);
			}
			return generalSharedInfo;
		}
		return null;
	}


}
