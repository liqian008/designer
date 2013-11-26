package com.bruce.designer.front.task;


import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.service.IHotService;

@Service
public class TaskJob {
	public static Logger log = Logger.getLogger(TaskJob.class);
	 
	@Autowired
	private IHotService hotService;

	public void execute() {
		try {
			log.info("计算热门Hot任务开始>........");
			// 业务逻辑代码调用
//			System.out.println("时间[" + new java.util.Date().toLocaleString() + "]----->计算热门tag！");
			hotService.calcHotTags(20);
			log.info("计算热门Hot任务结束!");
		} catch (Exception e) {
			log.error("计算热门Hot任务出现异常", e);
		}
	}

	

}
