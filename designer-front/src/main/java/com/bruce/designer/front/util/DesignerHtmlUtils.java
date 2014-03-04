package com.bruce.designer.front.util;

import java.util.List;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.Message;
import com.bruce.designer.model.Tag;
import com.bruce.designer.model.User;
import com.bruce.designer.util.UploadUtil;

/**
 * html工具，用于ajax
 * @author liqian
 * TODO 条件允许的话，最好改用freemarker，更灵活
 */
public class DesignerHtmlUtils {
	
	/**
	 * 构造正文页的专辑html
	 * @param albumList
	 * @param numberPerLine
	 * @return
	 */
	public static String buildFallLoadHtml(List<Album> albumList, int numberPerLine) {
		int span = 3;
		if (numberPerLine <= 0) {
			numberPerLine = 4;
		}
		span = 12 / numberPerLine;

		// TODO freemarker template
		if (albumList != null && albumList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (Album album : albumList) {
				i++;
				if (i % numberPerLine == 1) {
					sb.append("<div class='shortcode-blogpost row-fluid'>");
				}
				sb.append("<article class='blog-item span" + span + "'>");
				sb.append("<div class='blog-post-image-wrap'>");
				sb.append("<a class='blog-single-link' href='"+ConstFront.CONTEXT_PATH+"/album/" + album.getId() + "'>");
				sb.append("<img src='" + album.getCoverMediumImg() + "'>");
				sb.append("</a>");
				sb.append("</div>");
				sb.append("<div class='content-wrap span9'>");
				sb.append("<a class='blog-single-link' href='"+ConstFront.CONTEXT_PATH+"/album/" + album.getId() + "'>" +
						"<h5>" + album.getTitle() + "</h5></a>");
				sb.append("<ul>");
				sb.append("<li><span>标 签:&nbsp;</span>");
				List<String> tagNameList = album.getTagList();
				if (tagNameList != null && tagNameList.size() > 0) {
					int m=0;
					for (String tagName : tagNameList) {
						m++;
						sb.append("<a href='"+ConstFront.CONTEXT_PATH+"/tag/");
						sb.append(tagName);
						sb.append("'>");
						sb.append(tagName);
						sb.append("</a>");
						if(m<tagNameList.size()){
							sb.append(",&nbsp;");
						}
					}
				}
				sb.append("</li>");

				sb.append("<li><span>价 格:</span>" + album.getPrice() + " 元</li>");
				sb.append("<li><a href='"+ConstFront.CONTEXT_PATH+"/album/" + album.getId() + "'>" + album.getBrowseCount() + "&nbsp;浏览&nbsp;</a>/&nbsp;");
				sb.append("<a href='"+ConstFront.CONTEXT_PATH+"/album/" + album.getId() + "'>" + album.getCommentCount() + "&nbsp;评论&nbsp;</a>/&nbsp;");
				sb.append("<a href='"+ConstFront.CONTEXT_PATH+"/album/" + album.getId() + "'>" + album.getLikeCount() + "&nbsp;赞&nbsp;</a>/&nbsp;");
				sb.append("<a href='"+ConstFront.CONTEXT_PATH+"/album/" + album.getId() + "'>" + album.getFavoriteCount() + "&nbsp;收藏&nbsp;</a>");
				sb.append("</li> ");
				sb.append("</ul>");
				sb.append("</div>");
				sb.append("<div class='content-avatar'>");
				sb.append("<a href='"+ConstFront.CONTEXT_PATH+"/" + album.getUserId() + "/home'>");
				sb.append("<img src='"+UploadUtil.getAvatarUrl(album.getUserId(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM)+"'/>");
				sb.append("</a>");
				sb.append("</div>");
				sb.append("</article>");
				if (i % numberPerLine == 0) {
					sb.append("</div>");
				}
			}
			// sb.append("</div>");
			return sb.toString();
		}
		return "";
	}

	/**
	 * 生成边栏的专辑html
	 * @param albumList
	 * @return
	 */
	public static String buildSidebarHtml(List<Album> albumList) {
		// TODO freemarker template
		if (albumList != null && albumList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Album album : albumList) {
				sb.append("<div class='flickr_badge_image' id='flickr_badge_image" + album.getId() + "'><a href='"+ConstFront.CONTEXT_PATH+"/album/" + album.getId()
						+ "'><img src='" + album.getCoverSmallImg() + "' title='" + album.getTitle() + "'" + album.getTitle()
						+ "'height='75' width='75'></a></div>");
			}
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 构造设计师的html
	 * @param designerList
	 * @return
	 */
	public static String buildFallLoadHtml(List<User> designerList) {
        if (designerList != null && designerList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (User designer : designerList) {
                sb.append("<li class='social-icons-facebook-icon'><a href='"+ConstFront.CONTEXT_PATH+"/"+designer.getId()+"/home'><img src='"+UploadUtil.getAvatarUrl(designer.getId(), ConstService.UPLOAD_IMAGE_SPEC_LARGE)+"' alt='"+designer.getNickname()+"' /></a></li>");
            }
            return sb.toString();
        }
        return null;
    }
	
	/**
	 * 热门tag
	 * @param tagList
	 * @return
	 */
	public static String buildHotTagHtml(List<Tag> tagList) {
		StringBuilder sb = new StringBuilder();
		for (Tag tag : tagList) {
			sb.append("<a href='"+ConstFront.CONTEXT_PATH+"/tag/" + tag.getName() + "' rel='" + tag.getHotNum() + "'>" + tag.getName() + "</a>");
		}
		return sb.toString();
	}
	
	
	/**
	 * 构造留言消息
	 * @param message
	 * @return
	 */
	public static String getMessageDisplay(Message message) {
		if(message!=null&&message.getMessageType()!=null){
			switch (message.getMessageType()) {
				case ConstService.MESSAGE_TYPE_SYSTEM: {
					return message.getMessage();
				}
				case ConstService.MESSAGE_TYPE_FLOWER: {
					return "";
				}
				case ConstService.MESSAGE_TYPE_COMMENT: {
					return "<a href='"+ConstFront.CONTEXT_PATH+"/"+message.getFromId()+"/home' target='_blank'>"+message.getFromUser().getNickname() + "</a>: " + 
						message.getMessage();
				}
				case ConstService.MESSAGE_TYPE_LIKE: {
					return "<a href='"+ConstFront.CONTEXT_PATH+"/"+message.getFromId()+"/home' target='_blank'>"+message.getFromUser().getNickname() + "</a> " +
						"赞了您的专辑作品";
				}
				case ConstService.MESSAGE_TYPE_FAVORITIES: {
					return "<a href='"+ConstFront.CONTEXT_PATH+"/"+message.getFromId()+"/home' target='_blank'>"+message.getFromUser().getNickname() + "</a> " +
						"收藏了您的专辑作品";
				}
				case ConstService.MESSAGE_TYPE_AT: {
					return "";
				}
				default: {
					return "<a href='"+ConstFront.CONTEXT_PATH+"/"+message.getFromId()+"/home' target='_blank'>"+message.getFromUser().getNickname() + "</a>: " +
						message.getMessage();
				}
			}
		}
		return "";
	}
}
