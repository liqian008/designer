package com.bruce.designer.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bruce.designer.constants.Config;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.json.JsonResultBean;
import com.google.gson.reflect.TypeToken;

public class ApiUtil {
	
	@SuppressWarnings("unchecked")
	public static JsonResultBean getAlbumList(int albumTailId){
		
		String albumListUrl = Config.JINWAN_API_ALBUMS + "?albumsTailId="+albumTailId;
		LogUtil.d("=====albumListUrl======"+albumListUrl);
		String albumListJsonResult = null;
		JsonResultBean jsonResult = null;
		try {
			albumListJsonResult = HttpClientUtils.httpGet(albumListUrl);
			JSONObject jsonObject = new JSONObject(albumListJsonResult);
			int result = jsonObject.getInt("result");
			if(result==1){//成功响应
				JSONObject jsonData = jsonObject.getJSONObject("data");
				int resTailId = jsonData.getInt("albumTailId");
				String albumListStr = jsonData.getString("albumList");
				List<Album> albumList = JsonUtil.gson.fromJson(albumListStr, new TypeToken<List<Album>>(){}.getType());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("albumTailId", resTailId);
				map.put("albumList", albumList);
				jsonResult = new JsonResultBean(result, map, 0, null);
			}else{//错误响应
				int errorcode = jsonObject.getInt("errorcode");
				String message = jsonObject.getString("message");
				jsonResult = new JsonResultBean(result, null, errorcode, message);
			}
			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogUtil.d("=====albumListJsonResult======"+albumListJsonResult);
		return null;
	}
	
	
	
//	@SuppressWarnings("unchecked")
//	public static Map<String, Object> getAlbumList(int albumTailId){
//		
//		String albumListUrl = Config.JINWAN_API_ALBUMS + "?albumsTailId="+albumTailId;
//		LogUtil.d("=====albumListUrl======"+albumListUrl);
//		String albumListJsonResult = null;
//		try {
//			albumListJsonResult = HttpClientUtils.httpGet(albumListUrl);
//			AlbumsResultWrapper albumsResult = JsonUtil.gson.fromJson(albumListJsonResult, AlbumsResultWrapper.class);
//			if(albumsResult!=null&&albumsResult.getResult()==1){
//				Map<String, Object> dataMap = (Map<String, Object>) albumsResult.getData();
//				return dataMap;
//			}else{//请求数据错误
//				LogUtil.d("=======responseBean error=======");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		LogUtil.d("=====albumListJsonResult======"+albumListJsonResult);
//		return null;
//	}

}
