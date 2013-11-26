package com.bruce.designer.front.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.model.Tag;
import com.bruce.designer.service.IHotService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TagController {

	@Autowired
	private IHotService hotService;

	private static final Logger logger = LoggerFactory.getLogger(TagController.class);

	@RequestMapping(value = "/tag/{tagName}")
	public String tagAlbums(Model model, HttpServletRequest request, @PathVariable("tagName") String tagName) {
		model.addAttribute("tagName", tagName);
		return "tag";
	}

	@RequestMapping(value = "/tag/hotTags.json")
	public ModelAndView hotTags(Model model, HttpServletRequest request) {
		int limit = 20;
		List<Tag> tagList = hotService.getHotTags(limit);
		tagList = randomList(tagList);
		if (tagList != null && tagList.size() > 0) {
			String responseHtml = buildHotTagHtml(tagList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", responseHtml);
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
	}

	private String buildHotTagHtml(List<Tag> tagList) {
		StringBuilder sb = new StringBuilder();
		for (Tag tag : tagList) {
			sb.append("<a href='/designer-front/tag/" + tag.getName() + "' rel='" + tag.getHotNum() + "'>" + tag.getName() + "</a>");
		}
		return sb.toString();
	}

	/**
	 * 随机排序热门tag，用于tagCloud
	 * @param tagList
	 * @return
	 */
	public static List<Tag> randomList(List<Tag> tagList) {
		Collections.sort(tagList, new Comparator<Tag>() {
			private final int[] vs = { -1, 0, 1 };
			private final Random random = new Random();

			@Override
			public int compare(Tag tag1, Tag tag2) {
				return vs[random.nextInt(vs.length)];
			}
		});
		return tagList;
	}
}
