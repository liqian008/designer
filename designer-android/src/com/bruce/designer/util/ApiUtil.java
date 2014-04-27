package com.bruce.designer.util;

import java.util.Map;

import com.bruce.designer.constants.Config;
import com.bruce.designer.model.json.AlbumsResultWrapper;
import com.bruce.designer.model.json.JsonResultBean;

public class ApiUtil {
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getAlbumList(int albumTailId){
		
		String albumListUrl = Config.JINWAN_API_ALBUMS + "?albumsTailId="+albumTailId;
		LogUtil.d("=====albumListUrl======"+albumListUrl);
		String albumListJsonResult = null;
		try {
			albumListJsonResult = HttpClientUtils.httpGet(albumListUrl);
			AlbumsResultWrapper albumsResult = JsonUtils.gson.fromJson(albumListJsonResult, AlbumsResultWrapper.class);
			if(albumsResult!=null&&albumsResult.getResult()==1){
				Map<String, Object> dataMap = (Map<String, Object>) albumsResult.getData();
				return dataMap;
			}else{//请求数据错误
				LogUtil.d("=======responseBean error=======");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogUtil.d("=====albumListJsonResult======"+albumListJsonResult);
		return null;
	}
	

}
