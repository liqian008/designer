package com.bruce.designer.front.util;

import java.util.List;

import com.bruce.designer.data.PagingData;

public class PagingUtil {

	/**
	 * 构造分页html
	 * @param albumPagingData
	 * @param baseUrl
	 * @return
	 */
	public static String getPagingHtml(PagingData<?> albumPagingData, String baseUrl) {
		if (albumPagingData != null) {
			List<?> dataList = albumPagingData.getPagingData();
			if (dataList != null && dataList.size() > 0) {
				int totalCount = albumPagingData.getTotalCount();
				int totalPage = albumPagingData.getTotalPage();
				int currentPage = albumPagingData.getCurrentPage();

				if (totalPage >= 1) {
					// 当前页两侧的分页偏移，避免因为页数过多导致内容过长
					int pageOffset = 2;
					int startPage = currentPage - pageOffset;
					int endPage = currentPage + pageOffset;
					if (startPage < 1) {
						endPage = endPage + (1 - startPage);
						startPage = 1;
					}
					if (endPage > totalPage) {
						endPage = totalPage;
					}

					StringBuilder sb = new StringBuilder();
					sb.append("<div class='paging-navigation'><ul class='clearfix'>");
					sb.append("<li><a class='button button-blue' href='javascript:void(0)'>共" + totalCount + "条, 第" + currentPage + "/" + totalPage + "页</a></li>");
					if (currentPage > 1) {
						sb.append("<li><a class='button button-white' href='" + getPagingUrl(baseUrl, 1) + "'>首 页</a></li>");
						if (currentPage > 1) {
							sb.append("<li><a class='button button-white' href='" + getPagingUrl(baseUrl, currentPage - 1) + "'>上一页</a></li>");
						}
					}
					for (int pageNo = startPage; pageNo <= endPage; pageNo++) {
						String pageClass = " button-white";
						if (currentPage == pageNo) {// 当前页
							pageClass = "";
						}
						sb.append("<li><a class='button" + pageClass + "' href='" + getPagingUrl(baseUrl, pageNo) + "'>" + pageNo + "</a></li>");
					}
					if (currentPage < totalPage) {
						if (currentPage < totalPage) {
							sb.append("<li><a class='button button-white' href='" + getPagingUrl(baseUrl, currentPage + 1) + "'>下一页</a></li>");
						}
						sb.append("<li><a class='button button-white' href='" + getPagingUrl(baseUrl, totalPage) + "'>尾 页</a></li>");
					}
					sb.append("</ul></div>");
					return sb.toString();
				}
			}
		}
		return "";
	}

	public static String getPagingUrl(String baseUrl, int pageNo) {
		return UrlUtil.joinUrl(baseUrl, "pageNo=" + pageNo);
	}
}
