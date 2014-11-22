package com.bruce.designer.front.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.bruce.designer.constants.ConstDateFormat;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.front.constants.ConstFront;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumFavorite;
import com.bruce.designer.model.Comment;
import com.bruce.designer.model.Message;
import com.bruce.designer.model.Tag;
import com.bruce.designer.model.User;
import com.bruce.designer.util.DesignerLinkUtil;
import com.bruce.designer.util.UserUtil;

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
				int mod = i % numberPerLine;//取模
				sb.append(buildAlbumItemHtml(album, mod, span));
			}
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * 加载我的收藏
	 * @param favoriteList
	 * @param numberPerLine
	 * @return
	 */
	public static String buildFallLoadFavoriteHtml(List<AlbumFavorite> favoriteList, int numberPerLine) {
		int span = 3;
		if (numberPerLine <= 0) {
			numberPerLine = 4;
		}
		span = 12 / numberPerLine;

		// TODO freemarker template
		if (favoriteList != null && favoriteList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (AlbumFavorite albumFavorite : favoriteList) {
				Album album = albumFavorite.getAlbum();
				if(album!=null){//判断收藏的album实体是否存在
					i++;
					int mod = i % numberPerLine;//取模
					sb.append(buildAlbumItemHtml(albumFavorite.getAlbum(), mod, span));
				}
			}
			return sb.toString();
		}
		return "";
	}
	
	
	
	

	/**
	 * 
	 * @param album
	 * @param mod 模 
	 * @return
	 */
	private static String buildAlbumItemHtml(Album album, int mod, int span){
		StringBuilder sb = new StringBuilder();
		if (mod == 1) {
			sb.append("<div class='shortcode-blogpost row-fluid'>");
		}
		sb.append("<article class='blog-item span" + span + "'>");
		sb.append("<div class='blog-post-image-wrap'>");
		String albumLink = DesignerLinkUtil.getAlbumLink4Web(album.getId());
		sb.append("<a class='blog-single-link' href='"+ albumLink + "'>");
		sb.append("<img src='" + album.getCoverMediumImg() + "'>");
		sb.append("</a>");
		sb.append("</div>");
		sb.append("<div class='content-wrap span9'>");
		sb.append("<a class='blog-single-link' href='"+ albumLink + "'>" +
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
		sb.append("<li><a href='"+DesignerLinkUtil.getAlbumLink4Web(album.getId()) + "'>" + album.getBrowseCount() + "&nbsp;浏览&nbsp;</a>/&nbsp;");
		sb.append("<a href='"+DesignerLinkUtil.getAlbumLink4Web(album.getId()) + "'>" + album.getCommentCount() + "&nbsp;评论&nbsp;</a>/&nbsp;");
		sb.append("<a href='"+DesignerLinkUtil.getAlbumLink4Web(album.getId()) + "'>" + album.getLikeCount() + "&nbsp;赞&nbsp;</a>/&nbsp;");
		sb.append("<a href='"+DesignerLinkUtil.getAlbumLink4Web(album.getId()) + "'>" + album.getFavoriteCount() + "&nbsp;收藏&nbsp;</a>");
		sb.append("</li> ");
		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<div class='content-avatar'>");
		String userLink = DesignerLinkUtil.getUserLink4Web(album.getUserId());
		sb.append("<a href='"+ userLink + "'>"); 
		String designerAvatarUrl = "";
		if(album.getAuthorInfo()!=null&&!StringUtils.isBlank(album.getAuthorInfo().getDesignerAvatar())){
			designerAvatarUrl = album.getAuthorInfo().getDesignerAvatar();
		}
		sb.append("<img src='"+UserUtil.getAvatarUrl(designerAvatarUrl, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM)+"' width='100%'/>");
		sb.append("</a>");
		sb.append("</div>");
		sb.append("</article>");
		if (mod == 0) {
			sb.append("</div>");
		}
		return sb.toString();
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
				
				sb.append("<div class='flickr_badge_image' id='flickr_badge_image" + album.getId() + "'><a href='"+ DesignerLinkUtil.getAlbumLink4Web(album.getId())
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
            	String userLink = DesignerLinkUtil.getUserLink4Web(designer.getId());
                sb.append("<li class='social-icons-facebook-icon'><a href='"+userLink+"'><img src='"+UserUtil.getAvatarUrl(designer, ConstService.UPLOAD_IMAGE_SPEC_LARGE)+"' alt='"+designer.getNickname()+"' /></a></li>");
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
			String userLink = DesignerLinkUtil.getUserLink4Web(message.getFromId());
			switch (message.getMessageType()) {
				case ConstService.MESSAGE_TYPE_SYSTEM: {
					return message.getMessage();
				}
				case ConstService.MESSAGE_TYPE_FOLLOW: {
					return "<a href='"+userLink+"' target='_blank'>"+message.getFromUser().getNickname() + "</a>: " + 
							"关注了您";
				}
				case ConstService.MESSAGE_TYPE_COMMENT: {
					return "<a href='"+userLink+"' target='_blank'>"+message.getFromUser().getNickname() + "</a>: " + 
						message.getMessage();
				}
				case ConstService.MESSAGE_TYPE_LIKE: {
					return "<a href='"+userLink+"' target='_blank'>"+message.getFromUser().getNickname() + "</a> " +
						"赞了您的专辑作品";
				}
				case ConstService.MESSAGE_TYPE_FAVORITIES: {
					return "<a href='"+ userLink+"' target='_blank'>"+message.getFromUser().getNickname() + "</a> " +
						"收藏了您的专辑作品";
				}
				case ConstService.MESSAGE_TYPE_AT: {
					return "";
				}
				default: {
					return "<a href='"+userLink+"' target='_blank'>"+message.getFromUser().getNickname() + "</a>: " +
						message.getMessage();
				}
			}
		}
		return "";
	}
	
	
	/**
	 * 评论ajax html
	 * @param commentList
	 * @param userId
	 * @return
	 */
	public static String buildFallLoadHtml(List<Comment> commentList, Integer userId) {
		// TODO freemarker template
		if (commentList != null && commentList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Comment comment : commentList) {
				String userLink = DesignerLinkUtil.getUserLink4Web(comment.getFromId());
				sb.append("<li class='comment depth-1' id='li-comment-1'>" + 
						"<div class='comment-container' id='comment-1'><div class='comment-avatar'>"
						+ "<div class='comment-author vcard'>" + "<a href='"+userLink+"'>" +
								"<img src='"+UserUtil.getAvatarUrl(comment.getUserHeadImg(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM)+"'/></a>" + "</div></div>"
						+ "<div class='comment-body'><div class='comment-meta commentmetadata'>" + "<h6 class='comment-author'>"
						+ "<a href='"+userLink+"' rel='external nofollow' class='url'>" + comment.getNickname() + "</a> 发表于 "
						+ DateFormatUtils.format(comment.getCreateTime(), ConstDateFormat.YYYYMMDD_HHMM_FORMAT) + "</h6></div>"
						+ "<div class='comment-content'>");
				sb.append(comment.getComment());
				boolean displayReplyBtn = userId!=null&&!userId.equals(comment.getFromId());
				if(displayReplyBtn){
					sb.append("&nbsp;&nbsp;<a href=\"javascript:reply("+comment.getFromId()+",'"+comment.getNickname()+"')\">回复</a>");
				}
				sb.append("</div> </div></div></li>");
			}
			return sb.toString();
		}
		return "";
	}
}
