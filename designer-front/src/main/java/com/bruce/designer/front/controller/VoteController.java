package com.bruce.designer.front.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.front.util.ResponseBuilderUtil;
import com.bruce.designer.model.Vote;
import com.bruce.designer.model.VoteOption;
import com.bruce.designer.service.IVoteService;
import com.bruce.foundation.util.JsonUtil;
import com.google.gson.reflect.TypeToken;

/**
 * 投票controller
 * @author liqian
 *
 */
@Controller
public class VoteController {

	private static final Logger logger = LoggerFactory.getLogger(VoteController.class);
	
	private static final String VOTE_KEY= "jinwanr_vote_";
	
	@Autowired
	private IVoteService voteService;

	/**
	 * 投票活动详情 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/vote/{voteId}")
	public String voteInfo(Model model, @PathVariable int voteId, HttpServletRequest request, HttpServletResponse response) {
		if(logger.isDebugEnabled()){
			logger.debug("投票");
		}
		
		Vote vote = voteService.loadById(voteId);
		if(vote==null){
			throw new DesignerException(ErrorCode.VOTE_NOT_EXISTS);
		}
//		if(!Short.valueOf((short)1).equals(vote.getStatus())){
//			throw new DesignerException(ErrorCode.VOTE_CLOSED);
//		}
//		if(!Short.valueOf((short)1).equals(vote.getStatus())){
//			throw new DesignerException(ErrorCode.VOTE_CLOSED);
//		}
			
		//选项列表
		List<VoteOption> voteOptionList = voteService.queryOptionsByVoteId(voteId);
		//遍历，以判断是否投票过
		if(voteOptionList!=null&&voteOptionList.size()>0){
			Set<Integer> votedOptionSet = getVoteOptionSetFromCookie(request, voteId);
			for(VoteOption option: voteOptionList){//选项列表
				Integer optionId = option.getId();
				if(votedOptionSet.contains(optionId)){
					option.setVoted(true);
				}
			}
		}
		model.addAttribute("voteOptionList", voteOptionList);
		
		model.addAttribute("vote", vote);
		return "vote/voteInfo";
	}

	/**
	 * 投票操作
	 * @param model
	 * @param voteOptionId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/vote/vote.json", method = RequestMethod.POST)
	public ModelAndView vote(Model model, int voteOptionId, HttpServletRequest request, HttpServletResponse response) {
		//加载vote对象
		Vote vote = voteService.loadByOptionId(voteOptionId);
		if(vote==null){
			throw new DesignerException(ErrorCode.VOTE_NOT_EXISTS);
		}
		//判断vote状态&时间限制
		if(!Short.valueOf((short)1).equals(vote.getStatus())){
			throw new DesignerException(ErrorCode.VOTE_CLOSED);
		}
		if(vote.getVoteStartTime()!=null&&vote.getVoteStartTime().getTime()>System.currentTimeMillis()){
			throw new DesignerException(ErrorCode.VOTE_NOT_START);
		}
		if(vote.getVoteStartTime()!=null&&vote.getVoteEndTime().getTime()<System.currentTimeMillis()){
			throw new DesignerException(ErrorCode.VOTE_ALREADY_FINISHED);
		}
		
		Set<Integer> votedOptionSet = getVoteOptionSetFromCookie(request, vote.getId());
		
		if(votedOptionSet!=null&&votedOptionSet.size()>0){//之前vote过
			if(vote.getMaxCheckLimit()<=votedOptionSet.size()){
				//vote超限，不能再投票了
				throw new DesignerException(ErrorCode.VOTE_OVER_LIMIT);
			}
			if(votedOptionSet.contains(voteOptionId)){
				//已经投过票，不能重复投票
				throw new DesignerException(ErrorCode.VOTE_REPEAT);
			}
		}
		if(votedOptionSet==null) votedOptionSet = new HashSet<Integer>();
		votedOptionSet.add(voteOptionId);
		
		//增加vote记录
		int result =  voteService.vote(voteOptionId);
		if(result>0){
			//重新写入cookie
			String newCookieValue = JsonUtil.gson.toJson(votedOptionSet);
			Cookie cookie = new Cookie(getVoteCookieKey(vote.getId()), newCookieValue);
			cookie.setMaxAge(999999999);
			response.addCookie(cookie);
			return ResponseBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}
		return ResponseBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
	
	
	/**
	 * 从cookie中获取已投票的optionSet
	 * @param request
	 * @param voteId
	 * @return
	 */
	private Set<Integer> getVoteOptionSetFromCookie(HttpServletRequest request, int voteId) {
		//cookie中vote的option列表
		Set<Integer> votedOptionSet = null;
		String voteCookieKey = getVoteCookieKey(voteId);
		//检查cookie中的
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				if(voteCookieKey.equals(cookie.getName())){
					String votedOptionListStr = cookie.getValue();
					try{
						votedOptionSet = JsonUtil.gson.fromJson(votedOptionListStr, new TypeToken<Set<Integer>>(){}.getType());
					}catch(Exception e){
					}
					break;
				}
			}
		}
		if(votedOptionSet==null) votedOptionSet = new HashSet<Integer>(); 
		return votedOptionSet;
	}
	
	/**
	 * 获取vote的cookieKey
	 * @param voteId
	 * @return
	 */
	private static String getVoteCookieKey(int voteId){
		return VOTE_KEY + voteId;
	}
	
}
