package com.bruce.designer.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.bruce.designer.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService{

	/*线程池*/
    private ExecutorService executorService = Executors.newCachedThreadPool();
    
    @Override
    public void executeTask(Runnable runnable){
    	executorService.execute(runnable);
    }
    
    
}
