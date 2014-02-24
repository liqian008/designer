package com.bruce.designer.front.util;

import java.util.List;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.Album;
import com.bruce.designer.util.UploadUtil;

public class HtmlUtils {
	
	/**
	 * 
	 * @param albumList
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
				sb.append("<a class='blog-single-link' href='/designer-front/album/" + album.getId() + "'>");
				sb.append("<img src='" + album.getCoverMediumImg() + "'>");
				sb.append("</a>");
				sb.append("</div>");
				sb.append("<div class='content-wrap span9'>");
				sb.append("<a class='blog-single-link' href='/designer-front/album/" + album.getId() + "'>" +
						"<h5>" + album.getTitle() + "</h5></a>");
				sb.append("<ul>");
				sb.append("<li><span>标 签:&nbsp;</span>");
				List<String> tagNameList = album.getTagList();
				if (tagNameList != null && tagNameList.size() > 0) {
					int m=0;
					for (String tagName : tagNameList) {
						m++;
						sb.append("<a href='/designer-front/tag/");
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
				sb.append("<li><a href='/designer-front/album/" + album.getId() + "'>" + album.getBrowseCount() + "&nbsp;浏览&nbsp;</a>/&nbsp;");
				sb.append("<a href='/designer-front/album/" + album.getId() + "'>" + album.getCommentCount() + "&nbsp;评论&nbsp;</a>/&nbsp;");
				sb.append("<a href='/designer-front/album/" + album.getId() + "'>" + album.getFavoriteCount() + "&nbsp;收藏&nbsp;</a>");
				sb.append("</li> ");
				sb.append("</ul>");
				sb.append("</div>");
				sb.append("<div class='content-avatar'>");
				sb.append("<a href='/designer-front/" + album.getUserId() + "/home'>");
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
	 * 
	 * @param albumList
	 * @return
	 */
	public static String buildSidebarHtml(List<Album> albumList) {
		// TODO freemarker template
		if (albumList != null && albumList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (Album album : albumList) {
				sb.append("<div class='flickr_badge_image' id='flickr_badge_image" + album.getId() + "'><a href='/designer-front/album/" + album.getId()
						+ "'><img src='" + album.getCoverSmallImg() + "' title='" + album.getTitle() + "'" + album.getTitle()
						+ "'height='75' width='75'></a></div>");
			}
			return sb.toString();
		}
		return "";
	}
}
