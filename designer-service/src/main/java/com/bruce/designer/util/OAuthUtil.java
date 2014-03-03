package com.bruce.designer.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.Album;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.service.oauth.SharedInfo;

public class OAuthUtil {
    
    public static String getOAuthDisplayName(short thirdpartyType, String thirdpartyUname){
        return getSourceNameByType(thirdpartyType) +"_"+ thirdpartyUname;
    }

	public static String getSourceNameByType(short thirdpartyType) {
		String thirdpartyName;
		switch (thirdpartyType) {
		case 1: {
			thirdpartyName = "微博用户";
			break;
		}
		case 2: {
			thirdpartyName = "QQ用户";
			break;
		}
		case 3: {
			thirdpartyName = "人人用户";
			break;
		}
		default: {
			thirdpartyName = "未知";
			break;
		}
		}
		return thirdpartyName;
	}
	
	
	

	/**
	 * 根据配置构造分享对象列表
	 * @param album
	 * @param accessTokenMap
	 * @return
	 */
	public static List<SharedInfo> buildSharedInfoList(Album album, Map<Short, AccessTokenInfo> accessTokenMap) {
		//判断数据合法，可以分享内容
		if (album!=null && album.getCoverLargeImg()!=null && accessTokenMap != null && accessTokenMap.size() > 0) {
			List<SharedInfo> sharedInfoList = new ArrayList<SharedInfo>();
			for (Map.Entry<Short, AccessTokenInfo> entry : accessTokenMap.entrySet()) {
				short thirdpartyType = entry.getKey();
				
				//判断是否需要同步
				boolean sharedOut = false; 
				if(thirdpartyType==IOAuthService.OAUTH_WEIBO_TYPE){
					sharedOut = BooleanUtils.toBoolean(ConfigUtil.getString("album_weibo_shareout_flag"), "true", "false");
				}else if(thirdpartyType==IOAuthService.OAUTH_TENCENT_TYPE){
					sharedOut = BooleanUtils.toBoolean(ConfigUtil.getString("album_tencent_shareout_flag"), "true", "false");
				}
				
				AccessTokenInfo accessTokenObj =  entry.getValue();
				if(sharedOut && accessTokenObj!=null && !StringUtils.isBlank(accessTokenObj.getAccessToken())){//有合法token
					//根据作品构造分享对象
					SharedInfo sharedInfo = genSharedInfo(album, thirdpartyType, accessTokenObj.getAccessToken());
					if(sharedInfo!=null){
						sharedInfoList.add(sharedInfo);
					}
				}
			}
			return sharedInfoList;
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param album
	 * @param thirdpartyType
	 * @param accessToken
	 * @return
	 */
	private static SharedInfo genSharedInfo(Album album, short thirdpartyType, String accessToken) {
		String albumCoverUrl = album.getCoverLargeImg();
		File coverFile = UploadUtil.fileExists(albumCoverUrl);
		if(coverFile.exists()&&coverFile.isFile()){
			SharedInfo sharedInfo = new SharedInfo();
			//发布文案
			String contentTemplate = ConfigUtil.getString("album_shareout_content");
			//格式化发布模板
			String content = String.format(contentTemplate, album.getTitle(), album.getId());  
			sharedInfo.setContent(content);
			sharedInfo.setAlbumId(album.getId());
			try {
				sharedInfo.setImgBytes(UploadUtil.file2bytes(coverFile));
			} catch (Exception e){
				return null;
			}
			sharedInfo.setThirdpartyType(thirdpartyType);
			sharedInfo.setAccessToken(accessToken);
			return sharedInfo;
		}
		return null;
	}
	
	public static void main(String[] args) {
		//http://www.jinwanr.com/album/100024
		String nameTemplate = "我刚刚在 #金玩儿网 发布了新作品【%s】，作品详情：%s";
		System.out.println(String.format(nameTemplate, "test", new Integer(1)));
	}

}