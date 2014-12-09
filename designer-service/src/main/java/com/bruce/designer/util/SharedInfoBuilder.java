package com.bruce.designer.util;


import org.apache.commons.lang3.StringUtils;

import com.bruce.designer.constants.ConstConfig;
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
			if(!StringUtils.isBlank(album.getWxShareTitle())&&!StringUtils.isBlank(album.getWxShareContent())){
				GenericSharedInfo.WxSharedInfo generalWxShareInfo = new GenericSharedInfo.WxSharedInfo(ConstConfig.SHARE_PREFIX + album.getWxShareTitle(), ConstConfig.SHARE_PREFIX + album.getWxShareContent(),album.getWxShareIconUrl(), DesignerLinkUtil.getAlbumLink4Mobile(album.getId()));
				generalSharedInfo.setWxSharedInfo(generalWxShareInfo);
			}
			//微博分享对象
			if(!StringUtils.isBlank(album.getWxShareTitle())&&!StringUtils.isBlank(album.getWxShareContent())){
				GenericSharedInfo.WeiboSharedInfo generalWeiboShareInfo = new GenericSharedInfo.WeiboSharedInfo(ConstConfig.SHARE_PREFIX + album.getWxShareTitle(), ConstConfig.SHARE_PREFIX + album.getWxShareContent(),album.getWxShareIconUrl(), DesignerLinkUtil.getAlbumLink4Mobile(album.getId()));
				generalSharedInfo.setWeiboSharedInfo(generalWeiboShareInfo);
			}
			return generalSharedInfo;
		}
		return null;
	}


}
