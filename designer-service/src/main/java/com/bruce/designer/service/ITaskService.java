package com.bruce.designer.service;

import org.springframework.stereotype.Service;

@Service
public interface ITaskService {

    public void executeTask(Runnable runnable);
    
}
