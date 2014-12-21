package com.bruce.designer.front.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

import com.bruce.designer.data.CountCacheBean;
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
		
		Set<Integer> votedOptionSet = getVoteOptionSetFromCookie(request, voteId);
		if(logger.isDebugEnabled()){
			logger.debug("voteInfo中查询vote的cookie数据： "+votedOptionSet);
		}
		//选项列表
		List<VoteOption> voteOptionList = voteService.queryOptionsByVoteId(voteId);
		//遍历，以判断是否投票过
		if(voteOptionList!=null&&voteOptionList.size()>0){
			//获取投票项的统计
			List<CountCacheBean> voteResultList = voteService.queryVoteResultStat(voteId);
			//将统计转为map，并计算总投票数
			int totalVoteCount = 0;
			Map<Integer, Integer> voteResultMap = new HashMap<Integer, Integer>();
			if(voteResultList!=null&&voteResultList.size()>0){
				for(CountCacheBean countBean: voteResultList){
					int itemVoteCount = (int)countBean.getScore();
					totalVoteCount += itemVoteCount;
					voteResultMap.put(countBean.getMember(), itemVoteCount);
				}
			}
			
			for(VoteOption option: voteOptionList){//选项列表
				Integer optionId = option.getId();
				//各选项的投票数量
				int voteNum = voteResultMap.get(optionId)==null?0:voteResultMap.get(optionId);
				option.setVoteNum(voteNum);
				float itemPercent = totalVoteCount<=0f?0f:(((float)100*voteNum)/(float)totalVoteCount);
				option.setPercent(itemPercent);
				
				//计算用户是否投票过
				if(votedOptionSet.contains(optionId)){
					option.setVoted(true);
				}
			}
		}
		model.addAttribute("voteOptionList", voteOptionList);
		
		model.addAttribute("vote", vote);
		return "vote/voteInfo";
	}

//	private Map<Integer, Integer> convertToMap(List<CountCacheBean> voteResultList) {
//		
//		return voteResultMap;
//	}

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
			if(logger.isErrorEnabled()){
				logger.error("vote status 错误" + vote.getStatus());
			}
			throw new DesignerException(ErrorCode.VOTE_CLOSED);
		}
		if(vote.getVoteStartTime()!=null&&vote.getVoteStartTime().getTime()>System.currentTimeMillis()){
			
			throw new DesignerException(ErrorCode.VOTE_NOT_START);
		}
		if(vote.getVoteStartTime()!=null&&vote.getVoteEndTime().getTime()<System.currentTimeMillis()){
			throw new DesignerException(ErrorCode.VOTE_ALREADY_FINISHED);
		}
		
		Set<Integer> votedOptionSet = getVoteOptionSetFromCookie(request, vote.getId());
		if(logger.isDebugEnabled()){
			logger.debug("vote ajax时的cookie数据： "+votedOptionSet);
		}
		if(votedOptionSet!=null&&votedOptionSet.size()>0){//之前vote过
			if(vote.getMaxCheckLimit()<=votedOptionSet.size()){
				if(logger.isErrorEnabled()){
					logger.error("vote 提出超限 错误" + vote.getMaxCheckLimit()+","+votedOptionSet);
				}
				throw new DesignerException(ErrorCode.VOTE_OVER_LIMIT);//vote超限，不能再投票了
			}
			if(votedOptionSet.contains(voteOptionId)){
				if(logger.isErrorEnabled()){
					logger.error("vote 重复投票" + voteOptionId);
				}
				throw new DesignerException(ErrorCode.VOTE_REPEAT);//已经投过票，不能重复投票
			}
		}
		if(votedOptionSet==null) votedOptionSet = new HashSet<Integer>();
		votedOptionSet.add(voteOptionId);
		
		//增加vote记录
		int result =  voteService.vote(vote.getId(), voteOptionId);
		if(result>0){
			//重新写入cookie
			String newCookieValue = JsonUtil.gson.toJson(votedOptionSet);
			Cookie cookie = new Cookie(getVoteCookieKey(vote.getId()), newCookieValue);
			cookie.setMaxAge(999999999);
			response.addCookie(cookie);
			int leftVoteTimes = vote.getMaxCheckLimit() - votedOptionSet.size() ;//剩余的投票机会
			leftVoteTimes = leftVoteTimes<0?0:leftVoteTimes;
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(leftVoteTimes));
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
